---------------------
reflect
---------------------

---------------------
����
---------------------

---------------------
type
---------------------
	# type ChanDir int
		const (
			RecvDir ChanDir             = 1 << iota // ֻдͨ��
			SendDir                                 // ֻ��ͨ��
			BothDir = RecvDir | SendDir             // ��дͨ��
		)

		* ͨ��������

		func (d ChanDir) String() string
	
	# type Kind uint
		const (
			Invalid Kind = iota
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
			Chan
			Func
			Interface
			Map
			Ptr
			Slice
			String
			Struct
			UnsafePointer
		)

		func (k Kind) String() string
	
	# type MapIter struct {}
		func (it *MapIter) Key() Value
		func (it *MapIter) Next() bool
		func (it *MapIter) Value() Value
	
	# type Method struct {
			Name    string
			PkgPath string

			Type  Type  // method type
			Func  Value // func with receiver as first argument
			Index int   // index for Type.Method
		}
	
	# type SelectCase struct {
			Dir  SelectDir // direction of case
			Chan Value     // channel to use (for send or receive)
			Send Value     // value to send (for send)
		}
	
	# type SelectDir int
		const (
			SelectSend    SelectDir // case Chan <- Send
			SelectRecv              // case <-Chan:
			SelectDefault           // default
		)
	
	# type SliceHeader struct {
			Data uintptr
			Len  int
			Cap  int
		}
		* ��Ƭ�ĵز�ṹ

	# type StringHeader struct {
			Data uintptr
			Len  int
		}

		* �ַ����ĵĵײ�ṹ��һ��ָ�룬һ������
	
	# type StructField struct {
			Name string
			PkgPath string
			Type      Type      // field type
				* ����
			Tag       StructTag // field tag string
				* tag��������Java�е�ע��
			
			Offset    uintptr   // offset within struct, in bytes
			Index     []int     // index sequence for Type.FieldByIndex
			Anonymous bool      // is an embedded field
		}
		
		* �ṹ����ֶ�

		func (tag StructTag) Get(key string) string
			* ��ȡע�⣬��������ڷ��� ""
				v, _ := tag.Lookup(key)
				return v
			
		func (tag StructTag) Lookup(key string) (value string, ok bool)
			* ��ȡע��
	
	# type StructTag string
		* �ṹ����ֶ�ע��
	
	# type Type interface {
			Align() int
				* ���ش���������ʱ����Ҫ������ڴ��С

			FieldAlign() int
				* �������ڽṹ����Ϊ�ֶ�ʹ��ʱ���������Ķ��뷽ʽ
				
			Method(int) Method
				* ��ȡ������͵ĵ�n������������������׳��쳣

			MethodByName(string) (Method, bool)
				* ���ݷ������ƻ�ȡ����

			NumMethod() int
				* ���ظ�����public�����ĸ���

			Name() string
				* ����ʵ�����͵����ƣ���������

			PkgPath() string
				* �������͵İ�·����import ·�������û�з��ؿ��ַ���

			Size() uintptr
				* �洢��������Ҫ�Ŀռ��С��������unsafe.Sizeof

			String() string
				* ���ַ�����ʽ����

			Kind() Kind
				* ��������Kindö��

			Implements(u Type) bool
				* �Ƿ�ʵ����ָ��������u

			AssignableTo(u Type) bool
				* ��ǰ�����Ƿ���Ը�ֵ��u

			ConvertibleTo(u Type) bool
				* �Ƿ����ת��Ϊָ��������u

			Comparable() bool
				* �Ƿ��ǿɱȽϵ�
				* ��鵱ǰ�����ܲ������Ƚ����㣬��ʵ���ǿ�������͵ײ���û�а� typeAlg �� equal ����

			Bits() int
				* �Ա���Ϊ��λ�������͵Ĵ�С�������������Ͷ��ܵ�������������ܵ��Ļ� panic

			ChanDir() ChanDir
				* ����һ��ͨ�����͵ķ��������ǰtype����ͨ�������׳��쳣

			IsVariadic() bool
				* ���ص�ǰ���͵ĺ��������һ�������ǲ��ǿɱ䳤�����������ǰ���Ͳ��Ǻ��������׳��쳣

			Elem() Type
				* ���ص�ǰ���͵�Ԫ�����ͣ�������Ͳ���Array��Chan��Map��Ptr��Slice���׳��쳣

			Field(i int) StructField
				* ���� struct ���͵ĵ� i ���ֶΣ����� struct �� panic��i Խ��Ҳ�� panic

			FieldByIndex(index []int) StructField
				* ���ϱ�һ����������Ƕ�׵��õģ����� [1, 2] ����˵���ص�ǰ struct �ĵ�1��struct �ĵ�2���ֶΣ������� struct ����Ƕ�׵�����

			FieldByName(name string) (StructField, bool)
				* �������� struct �ֶΣ��ڶ�������ֵ ok ��ʾ��û��
			
			FieldByNameFunc(match func(string) bool) (StructField, bool)
				* ���������� struct �ֶΣ���Ϊ struct ��Ҳ������������ func 
				 
			In(i int) Type
				* ���ص�ǰ���ͺ�����ָ���±���������ͣ������ǰ���Ͳ��Ǻ��������׳��쳣

			Key() Type
				* ���ص�ǰԪ�����͵�Key���ͣ������ǰԪ�����Ͳ���Map�����׳��쳣
				
			Len() int
				* ���ص�ǰ���͵ĳ��ȣ������ǰ���Ͳ��������飬���쳣

			NumField() int
				* ���ص�ǰ���͵��ֶ�������������Ͳ���struct�����쳣

			NumIn() int
			NumOut() int
				* ���ص�ǰ�������͵��β�/���ز�����������������Ͳ��Ƿ��������쳣

			Out(i int) Type
				* ���ص�ǰ�������ͣ�����ֵ���͵ĵ�i�����ͣ�������Ͳ��Ƿ������쳣
		}
		

		func ArrayOf(count int, elem Type) Type
		func ChanOf(dir ChanDir, t Type) Type
		func FuncOf(in, out []Type, variadic bool) Type
		func MapOf(key, elem Type) Type
		func PtrTo(t Type) Type
		func SliceOf(t Type) Type
		func StructOf(fields []StructField) Type
		func TypeOf(i interface{}) Type
	
	# type Value struct {}
		func Append(s Value, x ...Value) Value
		func AppendSlice(s, t Value) Value
		func Indirect(v Value) Value
		func MakeChan(typ Type, buffer int) Value
		func MakeFunc(typ Type, fn func(args []Value) (results []Value)) Value
		func MakeMap(typ Type) Value
		func MakeMapWithSize(typ Type, n int) Value
		func MakeSlice(typ Type, len, cap int) Value
		func New(typ Type) Value
			* ����type����ʵ�������䴴��

		func NewAt(typ Type, p unsafe.Pointer) Value
		func Select(cases []SelectCase) (chosen int, recv Value, recvOK bool)
		func ValueOf(i interface{}) Value
		func Zero(typ Type) Value

		func (v Value) Addr() Value
		func (v Value) Bool() bool
		func (v Value) Bytes() []byte
		func (v Value) Call(in []Value) []Value
			* ǰ�� v ��һ�� func��Ȼ����� v�������� in ��������һ�������� in[0]���ڶ����� in[1]���Դ�����

		func (v Value) CallSlice(in []Value) []Value
		func (v Value) CanAddr() bool
		func (v Value) CanInterface() bool
		func (v Value) CanSet() bool
		func (v Value) Cap() int
		func (v Value) Close()
		func (v Value) Complex() complex128
		func (v Value) Convert(t Type) Value
		func (v Value) Elem() Value
			* ����ָ��ָ��Ķ���
				i := 1
				v := reflect.ValueOf(&i)
				v.Elem().SetInt(10)
				fmt.Println(i)
		
		func (v Value) Field(i int) Value
			* ǰ�� v ��һ�� struct�����ص� i ���ֶΣ������Ҫ���ڱ���

		func (v Value) FieldByIndex(index []int) Value
		func (v Value) FieldByName(name string) Value
			*  ǰ�� v ��һ�� struct�������ֶ���ֱ�Ӷ�λ����
		
		func (v Value) FieldByNameFunc(match func(string) bool) Value
		func (v Value) Float() float64
		func (v Value) Index(i int) Value
			* ǰ�� v �� Array, Slice, String ֮һ�����ص� i ��Ԫ�أ���ҪҲ�����ڱ�����ע�ⲻ��Խ��

		func (v Value) Int() int64
		func (v Value) Interface() (i interface{})
			* ת��Ϊ�ӿ�����

		func (v Value) InterfaceData() [2]uintptr
		func (v Value) IsNil() bool
			* �ж� v �ǲ��� nil��ֻ�� chan, func, interface, map, pointer, slice �����ã��������ͻ� panic

		func (v Value) IsValid() bool
			*  �ж� v �Ƿ�Ϸ���������� false����ô���� String() ����������������ö��� panic����ǰ����Ǳ�Ҫ��

		func (v Value) IsZero() bool
		func (v Value) Kind() Kind
		func (v Value) Len() int
		func (v Value) MapIndex(key Value) Value
			* ǰ�� v �Ǹ� map�����ض�Ӧ value

		func (v Value) MapKeys() []Value
			* ǰ�� v �Ǹ� map���������� key ��ɵ�һ�� slice
			

		func (v Value) MapRange() *MapIter
		func (v Value) Method(i int) Value
		func (v Value) MethodByName(name string) Value
		func (v Value) NumField() int
			*  ǰ�� v �Ǹ� struct�������ֶθ���

		func (v Value) NumMethod() int
		func (v Value) OverflowComplex(x complex128) bool
		func (v Value) OverflowFloat(x float64) bool
		func (v Value) OverflowInt(x int64) bool
		func (v Value) OverflowUint(x uint64) bool
		func (v Value) Pointer() uintptr
		func (v Value) Recv() (x Value, ok bool)
		func (v Value) Send(x Value)
		func (v Value) Set(x Value)
			* ��ֵ
		
		func (v Value) SetBool(x bool)
		func (v Value) SetBytes(x []byte)
		func (v Value) SetCap(n int)
		func (v Value) SetComplex(x complex128)
		func (v Value) SetFloat(x float64)
		func (v Value) SetInt(x int64)
		func (v Value) SetLen(n int)
		func (v Value) SetMapIndex(key, elem Value)
		func (v Value) SetPointer(x unsafe.Pointer)
		func (v Value) SetString(x string)
		func (v Value) SetUint(x uint64)
		func (v Value) Slice(i, j int) Value
		func (v Value) Slice3(i, j, k int) Value
		func (v Value) String() string
		func (v Value) TryRecv() (x Value, ok bool)
		func (v Value) TrySend(x Value) bool
		func (v Value) Type() Type
			* ����
		func (v Value) Uint() uint64
		func (v Value) UnsafeAddr() uintptr
	
	# type ValueError struct {
			Method string
			Kind   Kind
		}
		func (e *ValueError) Error() string
	

---------------------
func
---------------------
	func Copy(dst, src Value) int
	func DeepEqual(x, y interface{}) bool
		* ��Ƚ�2������

	func Swapper(slice interface{}) func(i, j int)