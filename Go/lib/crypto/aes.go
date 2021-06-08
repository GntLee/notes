-------------------
aes
-------------------
	# AES�㷨��ʵ��

-------------------
var
-------------------
	# ��Կ��С
		const BlockSize = 16


-------------------
type
-------------------
	# type KeySizeError int
		func (k KeySizeError) Error() string

-------------------
func
-------------------
	func NewCipher(key []byte) (cipher.Block, error)
		* ������Կ(16��24��32���ֽ�)����һ�� cipher
		* �������Կ�ĳ��ȷ���AES-128, AES-192, or AES-256 ��chipher
	

	
-------------------
aes��ʵ��
-------------------
	# AES�¸���ģʽ(CBC/ECB/CFB)�ļ��ܽ���ʵ��
		import (
			"bytes"
			"crypto/aes"
			"crypto/cipher"
			"crypto/rand"
			"encoding/base64"
			"encoding/hex"
			"io"
			"log"
		)

		func main(){
			origData := []byte("Go����") // �����ܵ�����
			key := []byte("1234567899986596") // ���ܵ���Կ

			log.Println("------------------ CBCģʽ --------------------")
			encrypted := AesEncryptCBC(origData, key)
			log.Println("����(hex)��", hex.EncodeToString(encrypted))
			log.Println("����(base64)��", base64.StdEncoding.EncodeToString(encrypted))
			decrypted := AesDecryptCBC(encrypted, key)
			log.Println("���ܽ����", string(decrypted))

			log.Println("------------------ ECBģʽ --------------------")
			encrypted = AesEncryptECB(origData, key)
			log.Println("����(hex)��", hex.EncodeToString(encrypted))
			log.Println("����(base64)��", base64.StdEncoding.EncodeToString(encrypted))
			decrypted = AesDecryptECB(encrypted, key)
			log.Println("���ܽ����", string(decrypted))

			log.Println("------------------ CFBģʽ --------------------")
			encrypted = AesEncryptCFB(origData, key)
			log.Println("����(hex)��", hex.EncodeToString(encrypted))
			log.Println("����(base64)��", base64.StdEncoding.EncodeToString(encrypted))
			decrypted = AesDecryptCFB(encrypted, key)
			log.Println("���ܽ����", string(decrypted))
		}
		// CBC ģʽ����
		func AesEncryptCBC(origData []byte, key []byte) (encrypted []byte) {
			block, _ := aes.NewCipher(key)
			blockSize := block.BlockSize()                              // ��ȡ��Կ��ĳ���
			origData = pkcs5Padding(origData, blockSize)                // ��ȫ��
			blockMode := cipher.NewCBCEncrypter(block, key[:blockSize]) // ����ģʽ
			encrypted = make([]byte, len(origData))                     // ��������
			blockMode.CryptBlocks(encrypted, origData)                  // ����
			return encrypted
		}

		// CBC ģʽ����
		func AesDecryptCBC(encrypted []byte, key []byte) (decrypted []byte) {
			block, _ := aes.NewCipher(key)                              // ������Կ
			blockSize := block.BlockSize()                              // ��ȡ��Կ��ĳ���
			blockMode := cipher.NewCBCDecrypter(block, key[:blockSize]) // ����ģʽ
			decrypted = make([]byte, len(encrypted))                    // ��������
			blockMode.CryptBlocks(decrypted, encrypted)                 // ����
			decrypted = pkcs5UnPadding(decrypted)                       // ȥ����ȫ��
			return decrypted
		}
		func pkcs5Padding(cipherText []byte, blockSize int) []byte {
			padding := blockSize - len(cipherText)%blockSize
			padText := bytes.Repeat([]byte{byte(padding)}, padding)
			return append(cipherText, padText...)
		}
		func pkcs5UnPadding(origData []byte) []byte {
			length := len(origData)
			unpadding := int(origData[length-1])
			return origData[:(length - unpadding)]
		}

		// ECSģʽ����
		func AesEncryptECB(origData []byte, key []byte) (encrypted []byte) {
			cipher, _ := aes.NewCipher(generateKey(key))
			length := (len(origData) + aes.BlockSize) / aes.BlockSize
			plain := make([]byte, length*aes.BlockSize)
			copy(plain, origData)
			pad := byte(len(plain) - len(origData))
			for i := len(origData); i < len(plain); i++ {
				plain[i] = pad
			}
			encrypted = make([]byte, len(plain))
			// ����ֿ����
			for bs, be := 0, cipher.BlockSize(); bs <= len(origData); bs, be = bs+cipher.BlockSize(), be+cipher.BlockSize() {
				cipher.Encrypt(encrypted[bs:be], plain[bs:be])
			}

			return encrypted
		}
		// ECBģʽ����
		func AesDecryptECB(encrypted []byte, key []byte) (decrypted []byte) {
			cipher, _ := aes.NewCipher(generateKey(key))
			decrypted = make([]byte, len(encrypted))
			for bs, be := 0, cipher.BlockSize(); bs < len(encrypted); bs, be = bs+cipher.BlockSize(), be+cipher.BlockSize() {
				cipher.Decrypt(decrypted[bs:be], encrypted[bs:be])
			}
			trim := 0
			if len(decrypted) > 0 {
				trim = len(decrypted) - int(decrypted[len(decrypted)-1])
			}

			return decrypted[:trim]
		}

		func generateKey(key []byte) (genKey []byte) {
			genKey = make([]byte, 16)
			copy(genKey, key)
			for i := 16; i < len(key); {
				for j := 0; j < 16 && i < len(key); j, i = j+1, i+1 {
					genKey[j] ^= key[i]
				}
			}
			return genKey
		}

		// CFBģʽ����
		func AesEncryptCFB(origData []byte, key []byte) (encrypted []byte) {
			block, err := aes.NewCipher(key)
			if err != nil {
				panic(err)
			}
			encrypted = make([]byte, aes.BlockSize+len(origData))
			iv := encrypted[:aes.BlockSize]
			if _, err := io.ReadFull(rand.Reader, iv); err != nil {
				panic(err)
			}
			stream := cipher.NewCFBEncrypter(block, iv)
			stream.XORKeyStream(encrypted[aes.BlockSize:], origData)
			return encrypted
		}
		// CFBģʽ����
		func AesDecryptCFB(encrypted []byte, key []byte) (decrypted []byte) {
			block, _ := aes.NewCipher(key)
			if len(encrypted) < aes.BlockSize {
				panic("cipher text too short")
			}
			iv := encrypted[:aes.BlockSize]
			encrypted = encrypted[aes.BlockSize:]

			stream := cipher.NewCFBDecrypter(block, iv)
			stream.XORKeyStream(encrypted, encrypted)
			return encrypted
		}

	
	# ������
		package aes

		import (
			"bytes"
			"crypto/aes"
			"crypto/cipher"
			"crypto/rand"
			"io"
		)


		// EncryptCBC CBC ģʽ����
		func EncryptCBC(origData []byte, key []byte) (encrypted []byte) {
			block, _ := aes.NewCipher(key)
			blockSize := block.BlockSize()                              // ��ȡ��Կ��ĳ���
			origData = pkcs5Padding(origData, blockSize)                // ��ȫ��
			blockMode := cipher.NewCBCEncrypter(block, key[:blockSize]) // ����ģʽ
			encrypted = make([]byte, len(origData))                     // ��������
			blockMode.CryptBlocks(encrypted, origData)                  // ����
			return encrypted
		}

		// DecryptCBC CBC ģʽ����
		func DecryptCBC(encrypted []byte, key []byte) (decrypted []byte) {
			block, _ := aes.NewCipher(key)                              // ������Կ
			blockSize := block.BlockSize()                              // ��ȡ��Կ��ĳ���
			blockMode := cipher.NewCBCDecrypter(block, key[:blockSize]) // ����ģʽ
			decrypted = make([]byte, len(encrypted))                    // ��������
			blockMode.CryptBlocks(decrypted, encrypted)                 // ����
			decrypted = pkcs5UnPadding(decrypted)                       // ȥ����ȫ��
			return decrypted
		}
		func pkcs5Padding(cipherText []byte, blockSize int) []byte {
			padding := blockSize - len(cipherText)%blockSize
			padText := bytes.Repeat([]byte{byte(padding)}, padding)
			return append(cipherText, padText...)
		}
		func pkcs5UnPadding(origData []byte) []byte {
			length := len(origData)
			unPadding := int(origData[length-1])
			return origData[:(length - unPadding)]
		}

		// EncryptECB ECSģʽ����
		func EncryptECB(origData []byte, key []byte) (encrypted []byte) {
			cipher, _ := aes.NewCipher(generateKey(key))
			length := (len(origData) + aes.BlockSize) / aes.BlockSize
			plain := make([]byte, length*aes.BlockSize)
			copy(plain, origData)
			pad := byte(len(plain) - len(origData))
			for i := len(origData); i < len(plain); i++ {
				plain[i] = pad
			}
			encrypted = make([]byte, len(plain))
			// ����ֿ����
			for bs, be := 0, cipher.BlockSize(); bs <= len(origData); bs, be = bs+cipher.BlockSize(), be+cipher.BlockSize() {
				cipher.Encrypt(encrypted[bs:be], plain[bs:be])
			}

			return encrypted
		}
		// DecryptECB ECBģʽ����
		func DecryptECB(encrypted []byte, key []byte) (decrypted []byte) {
			cipher, _ := aes.NewCipher(generateKey(key))
			decrypted = make([]byte, len(encrypted))
			for bs, be := 0, cipher.BlockSize(); bs < len(encrypted); bs, be = bs+cipher.BlockSize(), be+cipher.BlockSize() {
				cipher.Decrypt(decrypted[bs:be], encrypted[bs:be])
			}
			trim := 0
			if len(decrypted) > 0 {
				trim = len(decrypted) - int(decrypted[len(decrypted)-1])
			}

			return decrypted[:trim]
		}

		func generateKey(key []byte) (genKey []byte) {
			genKey = make([]byte, 16)
			copy(genKey, key)
			for i := 16; i < len(key); {
				for j := 0; j < 16 && i < len(key); j, i = j+1, i+1 {
					genKey[j] ^= key[i]
				}
			}
			return genKey
		}

		// EncryptCFB CFBģʽ����
		func EncryptCFB(origData []byte, key []byte) (encrypted []byte) {
			block, err := aes.NewCipher(key)
			if err != nil {
				panic(err)
			}
			encrypted = make([]byte, aes.BlockSize+len(origData))
			iv := encrypted[:aes.BlockSize]
			if _, err := io.ReadFull(rand.Reader, iv); err != nil {
				panic(err)
			}
			stream := cipher.NewCFBEncrypter(block, iv)
			stream.XORKeyStream(encrypted[aes.BlockSize:], origData)
			return encrypted
		}
		// DecryptCFB CFBģʽ����
		func DecryptCFB(encrypted []byte, key []byte) (decrypted []byte) {
			block, _ := aes.NewCipher(key)
			if len(encrypted) < aes.BlockSize {
				panic("cipher text too short")
			}
			iv := encrypted[:aes.BlockSize]
			encrypted = encrypted[aes.BlockSize:]

			stream := cipher.NewCFBDecrypter(block, iv)
			stream.XORKeyStream(encrypted, encrypted)
			return encrypted
		}
