--------------------------
gob
--------------------------

--------------------------
����
--------------------------

--------------------------
type
--------------------------
	# type CommonType struct {
			Name string
			Id   typeId
		}
	
	# type Decoder struct {
		}
		
		* ���������Ѷ����Ʊ���ΪGo����

		func NewDecoder(r io.Reader) *Decoder
		func (dec *Decoder) Decode(e interface{}) error
		func (dec *Decoder) DecodeValue(v reflect.Value) error
	
	# type Encoder struct {
		}

		* ����������Go���ݱ���Ϊ������

		func NewEncoder(w io.Writer) *Encoder
		func (enc *Encoder) Encode(e interface{}) error
		func (enc *Encoder) EncodeValue(value reflect.Value) error
	
	# type GobDecoder interface {
			GobDecode([]byte) error
		}

	# type GobEncoder interface {
			GobEncode() ([]byte, error)
		}
	
--------------------------
func
--------------------------
	func Register(value interface{})
	func RegisterName(name string, value interface{})

--------------------------
Demo
--------------------------
# ���л��뷴���л�
	import (
		"bytes"
		"encoding/gob"
		"fmt"
		"os"
	)

	func main(){

		type User struct {
			Name string
			Age uint
			Skill []string
		}

		// 1Kb��С�Ĵ洢
		buffer := bytes.NewBuffer(make([]byte, 0, 1024))

		// �������л���
		encoder := gob.NewEncoder(buffer)

		// ���л�
		err := encoder.Encode(&User{"KevinBlandy", 27, []string{"Java", "Python", "Javascript", "Go"}})
		if err != nil {
			fmt.Fprintf(os.Stderr, "���л��쳣:%v\n", err)
			os.Exit(1)
		}

		// ���շ����л��Ķ���
		var user *User

		// ���������л���
		decoder := gob.NewDecoder(buffer)
		// ִ�з����л�
		decoder.Decode(&user)
		// ���յ�����
		fmt.Println(user)  // &{KevinBlandy 27 [Java Python Javascript Go]}
	}