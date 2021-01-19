------------------------
json
------------------------
	# json�ĺ��Ŀ� encoding/json
	
		func Marshal(v interface{}) ([]byte, error)
		func MarshalIndent(v interface{}, prefix, indent string) ([]byte, error)
			* ��ָ�����ݽ���json���룬�����ֽ�����
				prefix	����е�ÿһ��JSONԪ�ض�����һ���µ��п�ʼ����prefix��ʼ
				indent	ÿһ��Ƕ���������
		
		func Unmarshal(data []byte, v interface{}) error
			* ���ֽ����飬����json���룬��װ������v
	
	# ���л��뷴���л��Ľӿ�
		# Marshaler
			type Marshaler interface {
				MarshalJSON() ([]byte, error)
			}

		# Unmarshaler
			type Unmarshaler interface {
				UnmarshalJSON([]byte) error
			}
		
		* �������һЩ�Զ�������ͣ�����ͨ���Լ�ʵ�ֽӿ������

------------------------
json ���л�
------------------------
	# ���л�����������������ͣ�������ת��Ϊjson
		* nil���ᱻת��Ϊnull�ַ���
		* ���ݽṹ�г���ָ�룬��ô����ת��ָ����ָ���ֵ
		* []byte���ᱻת��Ϊbase64������ַ���
		* �ṹ���У�ֻ�д�д��ͷ�������ֶβŻᱻ���л�
		* ���л�Map��ʱ��Map�����ǣ� map[string]T�� T���ǿ������л�������
		* ����ת��Ϊjson������
			channel
			complex
	
	# ע��
		* �޸Ľṹ���е�json����ֶ�����
			type Foo struct {
				Name string `json:"name"`		// �����Name�ֶΣ����л���json��json�ֶ����ƽ�����name
			}
		
		* ��ֵ/0ֵ��ʱ�������ֶ�
			type Foo struct {
				Name string `json:",omitempty"`		// ���name�ֶΣ�Ϊ���ַ�����������Name����ֶ�
			}
		
		* �����л�
			json:"-"
		
		* Ҫ�����л�
			json:"-,"
		
		* ���ַ�������Ϊ����
			type TestObject struct {
				Field1 int    `json:",string"`  // ��Ӧ��json =>  {"Field1": "100"}
			}
		
		* �ܽ�
			`json:"[filedName],[omitempty]"`
		


------------------------
json �����л�
------------------------
	# �����л�
		* �����л���ʱ����ֵ�ᱻת��ΪGo�е�float64����

------------------------
json ͨ�õĶ���
------------------------
	# δ֪��json��ʽ��������ͨ�õĶ�����
		* ����
			map[string]interface{}
		
		* ����
			[]interface{}
		
	



------------------------
json ��ʽ��д
------------------------
	# Decoder
		func NewDecoder(r io.Reader) *Decoder

		func (dec *Decoder) UseNumber() 
		func (dec *Decoder) DisallowUnknownFields() 
		func (dec *Decoder) Decode(v interface{}) error
		func (dec *Decoder) Buffered() io.Reader 
		func (dec *Decoder) Token() (Token, error) 
		func (dec *Decoder) More() bool
		func (dec *Decoder) InputOffset() int64
	
	# Encoder
		func NewEncoder(w io.Writer) *Encoder

		func (enc *Encoder) Encode(v interface{}) error 
		func (enc *Encoder) SetIndent(prefix, indent string)
		func (enc *Encoder) SetEscapeHTML(on bool) 