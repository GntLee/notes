--------------------
lumberjack
--------------------
	# ��־�и���
		https://github.com/natefinch/lumberjack
		https://pkg.go.dev/gopkg.in/natefinch/lumberjack.v2
	
		import "gopkg.in/natefinch/lumberjack.v2"
	
--------------------
type
--------------------	

	type Logger struct {
		Filename string `json:"filename" yaml:"filename"`
			* ��־����ļ�
			* ���Ϊ�գ��������ʱ�ļ�·��ʹ��: <processname>-lumberjack.log ���
			* ���ݵ���־Ҳ����ͬһ��Ŀ¼�б���

		MaxSize int `json:"maxsize" yaml:"maxsize"`
			* ������־�ļ����������������������ͻᱻ�и��λ��Mb
			* Ĭ���� 100Mb

		MaxAge int `json:"maxage" yaml:"maxage"`
			* ��־�������������(ֻ����������������־)
			* Ĭ�ϲ���ɾ�����ļ�

		MaxBackups int `json:"maxbackups" yaml:"maxbackups"`
			* ������ı����ļ�����
			* ���������MaxAge���򳬹�MaxAge���ᱻɾ����������MaxBackups

		LocalTime bool `json:"localtime" yaml:"localtime"`
			* �Ƿ�ʹ�ñ���ʱ�䣬Ĭ��ʹ��UTCʱ��

		Compress bool `json:"compress" yaml:"compress"`
			* �Ƿ�Ҫѹ���ļ���Ĭ�ϲ�ѹ��
	}

	func (l *Logger) Close() error
		* �ر���Դ 

	func (l *Logger) Rotate() error
		* �����и��ļ����
		
	func (l *Logger) Write(p []byte) (n int, err error)
		* д����־��Ϣ