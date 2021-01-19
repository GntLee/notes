-----------------------------
hash
-----------------------------
	# hash

-----------------------------
var
-----------------------------


-----------------------------
type
-----------------------------
	# type Hash interface {
			io.Writer
			Sum(b []byte) []byte
				* ��������յ�hash����ѽ��append��b

			Reset()
				* ����

			Size() int
				* hash������ֽڳ���

			BlockSize() int
				* ���ع�ϣ�ĵײ���С
		}

		* Hash�㷨�Ľӿ�
	
	# type Hash32 interface {
			Hash
			Sum32() uint32
		}
	
	# type Hash64 interface {
			Hash
			Sum64() uint64
		}

-----------------------------
func
-----------------------------

-----------------------------
hash����
-----------------------------
	# md5
		import (
			"crypto/md5"
			"fmt"
		)

		func main(){
			// ����hashʵ�������� hash.Hash �ӿ�ʵ��
			md5 := md5.New()
			// д������
			md5.Write([]byte("123"))
			md5.Write([]byte("456"))

			// ��������append����Ƭ��
			buffer := md5.Sum(make([]byte, 0))
			// ת��Ϊ16�����ַ���
			hex := fmt.Sprintf("%x", buffer)
			fmt.Println(hex) // e10adc3949ba59abbe56e057f20f883e
		}

		* sha256/sha512 ��һ����ֻ�ǿⲻͬ
		* md5/sha256/sha512 ���ṩ��ģ�鼶��Ŀ�ݼ���hash����
			import (
				"crypto/md5"
				"encoding/hex"
				"fmt"
			)
			func main() {
				data := md5.Sum([]byte("123456"))
				fmt.Println(hex.EncodeToString(data[:]))  // e10adc3949ba59abbe56e057f20f883e
			}
	
