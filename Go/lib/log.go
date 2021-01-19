---------------------
log
---------------------

---------------------
����
---------------------
	# ����־��¼���������ӵ��ı���Ϣ
		const (
			Ldate         = 1 << iota     // ����ʱ���е����ڣ�2009/01/23
			Ltime                         // ����ʱ����ʱ�䣺01:23:23
			Lmicroseconds                 // ΢��ֱ��ʣ�01��23��23.123123������Ltime��
			Llongfile                     // �������ļ�·�����кţ�/a/b/c/d.go:23
			Lshortfile                    // �����ļ���Ԫ�غ��кţ�d.go��23 ����Llongfile
			LUTC                          // ���������Ldate��Ltime����ʹ��UTC�����Ǳ���ʱ��
			Lmsgprefix                    // ����ǰ׺�����еĿ�ͷ������Ϣ֮ǰ
			LstdFlags     = Ldate | Ltime // ��׼��¼���ĳ�ʼֵ
		)

---------------------
type
---------------------
	# type Logger struct 
		func New(out io.Writer, prefix string, flag int) *Logger
			* �����µ���־��¼��
			* ָ���������ǰ׺����ʶ

		func (l *Logger) Fatal(v ...interface{})
		func (l *Logger) Fatalf(format string, v ...interface{})
		func (l *Logger) Fatalln(v ...interface{})
			* �൱�ڵ��� Print()/Printf()/Println() ֮����� os.Exit(1) 

		func (l *Logger) Flags() int
			* ���ر�׼��¼���������־

		func (l *Logger) Output(calldepth int, s string) error
			* ���һ����־��Ϣ
			* s ָ����־��Ϣ�����s���ǻ��з���β����ôlogger���Լ����һ����¼��
			* ���������Llongfile/Lshortfile����ôcalldepth �����ڼ����ļ������к�ʱҪ������֡����ֵΪ1ʱ����ΪOutput�ĵ����ߴ�ӡ��ϸ��Ϣ��

		func (l *Logger) Panic(v ...interface{})
		func (l *Logger) Panicf(format string, v ...interface{})
		func (l *Logger) Panicln(v ...interface{})
			* �൱�� Print()/Printf()/Println() ֮�����panic()��

		func (l *Logger) Prefix() string
			* �������õ�ǰ׺��Ϣ

		func (l *Logger) Print(v ...interface{})
		func (l *Logger) Printf(format string, v ...interface{})
		func (l *Logger) Println(v ...interface{})
			* �����־

		func (l *Logger) SetFlags(flag int)
			* �������ñ�ʶ
		func (l *Logger) SetOutput(w io.Writer)
			* �����������Writer
		func (l *Logger) SetPrefix(prefix string)
			* ��ȡ���õ�ǰ׺��Ϣ
		func (l *Logger) Writer() io.Writer
			* ������־�����Writer

---------------------
����
---------------------
	func Fatal(v ...interface{})
	func Fatalf(format string, v ...interface{})
	func Fatalln(v ...interface{})
	func Flags() int
	func Output(calldepth int, s string) error
	func Panic(v ...interface{})
	func Panicf(format string, v ...interface{})
	func Panicln(v ...interface{})
	func Prefix() string
	func Print(v ...interface{})
	func Printf(format string, v ...interface{})
	func Println(v ...interface{})
	func SetFlags(flag int)
	func SetOutput(w io.Writer)
	func SetPrefix(prefix string)
	func Writer() io.Writer


	* ȫ��Ĭ�ϵ���־��¼�����������׼�쳣��
		var std = New(os.Stderr, "", LstdFlags)
