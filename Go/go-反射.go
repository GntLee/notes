-----------------
����
-----------------
	# ������Ҫ�� interface �ӿ��йأ�ֻ��interface���Ͳ��з���һ˵��
		* ÿ��interface��������һ����Ӧpair��pair�м�¼��ʵ�ʱ�����ֵ������
			(value, type)

			tty, err := os.OpenFile("/dev/tty", os.O_RDWR, 0)
			var r io.Reader	
			r = tty
			// r��pair = (tty, *os.File)
		
		* ���pair�ڽӿڱ�����������ֵ�������ǲ���ģ����ӿڱ���r������һ���ӿڱ���w:
			var w io.Writer
			w = r.(io.Writer)
			// w��pair = (tty, *os.File)
		

		* ��������������洢�ڽӿڱ����ڲ�(ֵvalue������concrete type) pair�Ե�һ�ֻ��ơ�
	
	# reflect.TypeOf() ��ȡ pair �е�type�������һ��interface
		func TypeOf(i interface{}) Type


	# reflect.ValueOf() ��ȡ pair �е�value������һ��struct
		func ValueOf(i interface{}) Value 

		* ���������nil�����ؿ� Value
	
	# �Ƚϳ��õ�һЩ����
		* ����������
			type Method struct {
				Name    string
				PkgPath string
				Type  Type
				Func  Value
				Index int 
			}
		
		* ��������tag��
			type StructTag string {}
			func (tag StructTag) Get(key string) string 
			func (tag StructTag) Lookup(key string) (value string, ok bool)
		
		* ���������ֶε�
			type StructField struct {
				PkgPath string
				Type      Type		
				Tag       StructTag		
				Offset    uintptr 
				Index     []int			
				Anonymous bool		
			}
		

	# Go�ķ���Ƚ���
		* �漰���ڴ�����Լ�������GC
		* reflectʵ�������д�����ö�٣�Ҳ����forѭ������������֮��ġ�