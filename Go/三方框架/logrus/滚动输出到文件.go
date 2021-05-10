-----------------------
file-rotatelogs
-----------------------
	# logrus����֧�ֹ����ļ�����Ҫ����������
	# file-rotatelogs
		https://github.com/lestrrat-go/file-rotatelogs
	
		"github.com/lestrrat-go/file-rotatelogs"
	
	
	# һЩҪ��
		* ��󱣴�����/��󱣴�������ͻ������ͬʱ����
	
	# Demo
		var logFile, err = rotatelogs.New(
			"log/app.%Y%m%d%H%M",						// ��־�ļ������ʽ
			rotatelogs.ForceNewFile(),					// ǿ�ƴ������ļ�
			rotatelogs.WithClock(rotatelogs.Local),		// ��ȡʱ�亯��
			rotatelogs.WithLocation(time.Local),		// ʱ��
			rotatelogs.WithLinkName("log/app.log"),		// ���µ���־�ļ�������
			rotatelogs.WithMaxAge(-1),					// �ļ������ڱ���
			rotatelogs.WithRotationCount(3),			// ���˵�ǰ������ļ�����ʷ�ļ���ౣ��3��
			rotatelogs.WithRotationSize(1024),			// �����ļ����1���ֽڣ��ͻ��и�
			rotatelogs.WithRotationTime(time.Hour * 24),// �����ļ����1��Сʱ���ͻ��и�
			rotatelogs.WithHandler(rotatelogs.HandlerFunc(func(event rotatelogs.Event) {
				if rotatelogs.FileRotatedEventType == event.Type() {
					// ��־�и��¼�
				}
			})),
		)
		if err != nil {
			log.Fatalf("������־�ļ��쳣��%s\n", err.Error())
		}
		log.SetOutput(io.MultiWriter(logFile, os.Stdout))  // ������������������־�����������׼���

	
-----------------------
var
-----------------------
	var Local = clockFn(time.Now)
		* ��ȡ����ʱ��Ľӿ�ʵ�֣�Ĭ�ϣ�

	var UTC = clockFn(func() time.Time { return time.Now().UTC() })
		* ��ȡUTCʱ��Ľӿ�ʵ��

-----------------------
type
-----------------------
	# type Clock interface {
			Now() time.Time
		}
		
		* �ļ���ʽ��ʹ�õģ���ȡʱ��ӿ�

	# type Event interface {
			Type() EventType
		}

	# type EventType int 
		const (
			InvalidEventType EventType = iota		// ������쳣����
			FileRotatedEventType					// �ļ������¼�
		)

	# type FileRotatedEvent struct {
			// contains filtered or unexported fields
		}
		func (e *FileRotatedEvent) CurrentFile() string
		func (e *FileRotatedEvent) PreviousFile() string
		func (e *FileRotatedEvent) Type() EventType

	# type Handler interface {
			Handle(Event)
		}

	# type HandlerFunc func(Event)
		func (h HandlerFunc) Handle(e Event)
	
	# type Option interface {
			Name() string
			Value() interface{}
		}
		
		* �ļ��и��������

		func ForceNewFile() Option
			* ǿ�ƴ������ļ�

		func WithClock(c Clock) Option
		func WithHandler(h Handler) Option
			* ����handler

		func WithLinkName(s string) Option
			* ���������ӵ�ַ����Զָ�����µ��ļ�

		func WithLocation(loc *time.Location) Option
			* ����ʱ��

		func WithMaxAge(d time.Duration) Option
			* �����ļ�����ǰ�������ʱ��
			* Ĭ��7�죬����Ϊ-1����ʾ������

		func WithRotationCount(n uint) Option
			* �����ļ�����ǰ��ౣ��ĸ���
			* ����Ϊ-1��ʾ������

		func WithRotationSize(s int64) Option
			* �����ļ���������

		func WithRotationTime(d time.Duration) Option
			* ������־�ָ��ʱ�䣬����÷ָ�һ��
			* ��СΪ1������ѯ��Ĭ��60s  ����1���ӾͰ�1������

	# type RotateLogs struct {
		}
		func New(p string, options ...Option) (*RotateLogs, error)
			* �����µ���־��ָ������ļ����Լ�������Ϣ
				 "/path/to/access_log.%Y%m%d%H%M",

		func (rl *RotateLogs) Close() error
			* �ر�

		func (rl *RotateLogs) CurrentFileName() string
			* ���ص�ǰ�ļ�����

		func (rl *RotateLogs) Rotate() error
		func (rl *RotateLogs) Write(p []byte) (n int, err error)
			* ʵ��Writer�ӿڣ�д������

-----------------------
func
-----------------------