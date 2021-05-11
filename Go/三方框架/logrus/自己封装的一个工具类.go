package log

// ���е���־��¼�������м�����־���� appLogWriter �����־
// ������־��¼���ġ��쳣��������־����������ͬ��ר����־ͨ�����

import (
	"github.com/lestrrat-go/file-rotatelogs"
	"github.com/sirupsen/logrus"
	"io"
	"log"
	"os"
	"time"
)

func init (){
	log.Default().SetFlags(log.Ldate | log.Ltime | log.Llongfile)
}

// loggerName ��־��¼������
const loggerName = "logger"

// Loggers ������־��¼��
var Loggers = map[string] *logrus.Entry {}


var (
	// Default Ĭ�ϵ�ȫ�ּ�¼��
	Default *logrus.Entry

	// Sql SQL��־��¼��
	Sql *logrus.Entry
)

var (
	// appLogWriter ������־��������м������־���������������
	appLogWriter io.Writer
	// warnLogWriter ������־ר�����
	warnLogWriter io.Writer
	// errorLogWriter �쳣��־ר�����
	errorLogWriter io.Writer
	// fatalLogWriter �쳣�˳���־ר�����
	fatalLogWriter io.Writer
	// panicLogWriter ������־ר�����
	panicLogWriter io.Writer
)



// Init ��ʼ����־ϵͳ
func Init (){
	// ��ʼ����־���Ŀ�ĵ�
	appLogWriter =   io.MultiWriter(newFileLogWriter("logs/app.%Y%m%d%H%M", 	"logs/app.log"), os.Stdout)
	warnLogWriter =  newFileLogWriter("logs/warn.%Y%m%d%H%M", 	"logs/warn.log")
	errorLogWriter = newFileLogWriter("logs/error.%Y%m%d%H%M", 	"logs/error.log")
	fatalLogWriter = newFileLogWriter("logs/fatal.%Y%m%d%H%M", 	"logs/fatal.log")
	panicLogWriter = newFileLogWriter("logs/panic.%Y%m%d%H%M", 	"logs/panic.log")

	// Ĭ����־
	Default = newLogger(func() *logrus.Entry {
		return logrus.NewEntry(logrus.New()).WithField(loggerName, "default")
	}, logrus.DebugLevel)

	//SQL��־��¼��
	Sql = newLogger(func() *logrus.Entry {
		return logrus.NewEntry(logrus.New()).WithField(loggerName, "sql")
	}, logrus.DebugLevel)

	// TODO ������Բ�ͬ����־��¼�������ò�ͬ�����Ŀ�ĵ�

	// ��ӵ�����
	Loggers[(Default.Data[loggerName]).(string)] = Default
	Loggers[(Sql.Data[loggerName]).(string)] = Sql
}

// errorLogHook ������warn������error������fatal������panic����־��
// ��ר�ŵ���־ͨ��������־�����ҷ����ʼ�����
type errorLogHook struct {
	levels []logrus.Level
}
func (h errorLogHook) Levels() []logrus.Level {
	return h.levels
}
func (h errorLogHook) Fire(entry *logrus.Entry) error {
	data, err := entry.Bytes()
	if err != nil {
		return err
	}

	var writer io.Writer

	switch entry.Level {
		case logrus.WarnLevel:  writer = warnLogWriter
		case logrus.ErrorLevel: writer = errorLogWriter
		case logrus.FatalLevel: writer = fatalLogWriter
		case logrus.PanicLevel: writer = panicLogWriter
		default: {}
	}

	// TODO ����֪ͨ�ʼ�

	// ��׼�쳣�����
	// _, err = os.Stderr.Write(data) // ���ϻ��ڱ�׼�����������Ҫ�ظ����쳣�����
	_, err = writer.Write(data)
	return err
}
var errHook = &errorLogHook{levels: []logrus.Level {logrus.WarnLevel, logrus.ErrorLevel, logrus.FatalLevel, logrus.PanicLevel}}

// newLogger �����µ���־��¼��
func newLogger (creator func() *logrus.Entry, level logrus.Level) *logrus.Entry {
	var logger = creator()
	logger.Logger.SetFormatter(&logrus.JSONFormatter{
		TimestampFormat:   "2006-01-02 15:04:05",
		DisableHTMLEscape: true,
	})
	logger.Logger.SetReportCaller(true)
	logger.Logger.SetLevel(level)
	logger.Logger.SetOutput(appLogWriter) // ����Logger���� appLogWriter ���
	logger.Logger.Hooks.Add(errHook)
	return logger
}

// newFileLogWriter �����µ���־����ļ�
func newFileLogWriter(logFile, linkFile string) *rotatelogs.RotateLogs {
	var logWriter, err = rotatelogs.New(
		logFile,														// ��־�ļ����Ƹ�ʽ��
		rotatelogs.ForceNewFile(),										// ǿ�ƴ������ļ�
		rotatelogs.WithClock(rotatelogs.Local),							// ��ȡʱ�亯��
		rotatelogs.WithLocation(time.Local),							// ʱ��
		rotatelogs.WithLinkName(linkFile),								// ���µ���־�ļ�������
		rotatelogs.WithMaxAge(-1),										// �ļ������ڱ���
		rotatelogs.WithRotationCount(10),								// ���˵�ǰ����ļ�����ʷ�ļ���ౣ��10��
		rotatelogs.WithRotationSize(1024 * 1024 * 100),					// �����ļ����100MB���ͻ��и�
		rotatelogs.WithRotationTime(time.Hour * 24),					// �����ļ����24��Сʱ���ͻ��и�
		//rotatelogs.WithHandler(rotatelogs.HandlerFunc(func(event rotatelogs.Event) {
		//	if rotatelogs.FileRotatedEventType == event.Type() {
		//	}
		//})),
	)
	if err != nil {
		log.Fatalf("������־Writer�쳣: %s\n", err.Error())
	}
	return logWriter
}

