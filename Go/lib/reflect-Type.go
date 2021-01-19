----------------------
����
----------------------
		
	

----------------------
type
----------------------
	# type Type interface
		Align() int
		FieldAlign() int
		Method(int) Method
			* ��ȡָ���ķ���

		MethodByName(string) (Method, bool)
			* �������ƻ�ȡ����

		NumMethod() int
			* ����������

		Name() string
			* ��ȡ���͵�����

		PkgPath() string
		Size() uintptr
		String() string
		Kind() Kind
		Implements(u Type) bool
		AssignableTo(u Type) bool
		ConvertibleTo(u Type) bool
		Comparable() bool
		Bits() int
		ChanDir() ChanDir
		IsVariadic() bool
		Elem() Type
		Field(i int) StructField
		FieldByIndex(index []int) StructField
		FieldByName(name string) (StructField, bool)
		FieldByNameFunc(match func(string) bool) (StructField, bool)
			* �����±꣬��ȡ��ָ���Ľṹ���ֶ�

		In(i int) Type
		Key() Type
		Len() int
		NumField() int
			* �ֶε�����

		NumIn() int
		NumOut() int
		Out(i int) Type
	
	# type Method struct
		type Method struct {
			Name    string
			PkgPath string

			Type  Type
			Func  Value
			Index int 
		}

		* ��������
	
	
	# type Kind uint
		* �������ͱ�ʶ
		* Ԥ�����˵ı���
			const (
				Invalid Kind = iota	��ֵ
				Bool
				Int
				Int8
				Int16
				Int32
				Int64
				Uint
				Uint8
				Uint16
				Uint32
				Uint64
				Uintptr
				Float32
				Float64
				Complex64
				Complex128
				Array
				Chan				Channel
				Func				����
				Interface			�ӿ�
				Map
				Ptr
				Slice
				String
				Struct
				UnsafePointer
			)

		func (k Kind) String() string

	# type ChanDir int
		* ��ʶͨ�����͵ķ���

	# type StructField struct 
		PkgPath string
		Type      Type			�ֶ�����
		Tag       StructTag		�ֶ������tag
		Offset    uintptr 
		Index     []int			
		Anonymous bool			�Ƿ���������

		* �ṹ���ֶ�
	
	# type StructTag string
		* �ṹ���ϵı�ʶ

		func (tag StructTag) Get(key string) string 
		func (tag StructTag) Lookup(key string) (value string, ok bool)





----------------------
����
----------------------
	func DeepEqual(x, y interface{}) bool
	func MakeFunc(typ Type, fn func(args []Value) (results []Value)) Value 
	func Swapper(slice interface{}) func(i, j int)

	func PtrTo(t Type) Type 

	func TypeOf(i interface{}) Type
		* ����ָ�����ݵ�Type�������ص���ʵ�ʵ�����
			var w, _ = os.Open("C:\\temp.txt")
			var t = reflect.TypeOf(w) // *os.File


	func ChanOf(dir ChanDir, t Type) Type
	func MapOf(key, elem Type) Type
	func FuncOf(in, out []Type, variadic bool) Type
	func SliceOf(t Type) Type
	func StructOf(fields []StructField) Type
	func ArrayOf(count int, elem Type) Type

