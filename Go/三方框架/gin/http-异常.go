--------------
�쳣����
--------------
	# ��ؽӿ�
		* �쳣��Ϣ���ϣ��Ǹ�˽��type���ṩ�˹����ķ���
			type errorMsgs []*Error
			
			func (a errorMsgs) ByType(typ ErrorType) errorMsgs
			func (a errorMsgs) Last() *Error 
			func (a errorMsgs) Errors() []string
			func (a errorMsgs) JSON() interface{} 
			func (a errorMsgs) MarshalJSON() ([]byte, error)
			func (a errorMsgs) String() string

		* �쳣��Ϣ
			type Error struct {
				Err  error
				Type ErrorType
				Meta interface{}
			}

			func (msg Error) Error() string
			func (msg *Error) IsType(flags ErrorType) bool
			func (msg *Error) JSON() interface{}
			func (msg *Error) MarshalJSON() ([]byte, error)
			func (msg *Error) SetMeta(data interface{}) *Error
			func (msg *Error) SetType(flags ErrorType) *Error
		
		* Ԥ�������͵�����
			type ErrorType uint64
			const (
				ErrorTypeBind ErrorType = 1 << 63			// Context.Bind()ʧ��
				ErrorTypeRender ErrorType = 1 << 62			// Context.Render() ʧ��
				ErrorTypePrivate ErrorType = 1 << 0			// ˽�д���?
				ErrorTypePublic ErrorType = 1 << 1			// ��������?
				ErrorTypeAny ErrorType = 1<<64 - 1			// ��ʾ�κ���������
				ErrorTypeNu = 2								// ��ʾ�κ���������
			)

	# Ԥ����Ĵ�����
		func ErrorLogger() HandlerFunc
			* �����쳣������������쳣����ΪJSON��Ӧ���ͻ���
				return ErrorLoggerT(ErrorTypeAny)
		func ErrorLoggerT(typ ErrorType) HandlerFunc
			* ��������ָ���쳣