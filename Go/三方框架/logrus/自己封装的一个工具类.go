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

	// Access ������־
	Access *logrus.Entry
)

var (
	// appLogWriter ������־��������м������־���������������
	appLogWriter io.Writer

	// accessLogWriter ������־ר�����
	accessLogWriter io.Writer

	// warnLogWriter ������־ר�����
	warnLogWriter io.Writer
	// errorLogWriter �쳣��־ר�����
	errorLogWriter io.Writer
	// fatalLogWriter �쳣�˳���־ר�����
	fatalLogWriter io.Writer
	// panicLogWriter ������־ר�����
	panicLogWriter io.Writer

)


// fileLogOptions �����ļ���־������
func fileLogOptions (linkName string, maxAge time.Duration, rotationCount uint, rotationSize int64, rotationTime time.Duration, handlerFunc rotatelogs.HandlerFunc) []rotatelogs.Option {
	return []rotatelogs.Option{rotatelogs.ForceNewFile(), // ǿ�ƴ������ļ�
		rotatelogs.WithClock(rotatelogs.Local),         // ��ȡʱ�亯��
		rotatelogs.WithLocation(time.Local),            // ʱ��
		rotatelogs.WithLinkName(linkName),				// �����ļ�
		rotatelogs.WithMaxAge(maxAge),                  // �ļ�����ʱ��
		rotatelogs.WithRotationCount(rotationCount),    // ��ʷ�ļ�����
		rotatelogs.WithRotationSize(rotationSize), 		// �ļ������������ᱻ�и�
		rotatelogs.WithRotationTime(rotationTime),    	// �����ļ����ʱ�䣬�������ʱ��ᱻ�и�
		rotatelogs.WithHandler(handlerFunc),			// ������
	}
}

func intiWriter (){
	appLogWriter	=	newFileLogWriter("logs/app.%Y%m%d%H%M", 	fileLogOptions("log/app.log", -1, 10, 1024 * 1024 * 100, time.Hour * 24, nil)...)
	appLogWriter = io.MultiWriter(accessLogWriter, os.Stdout)

	accessLogWriter	=	newFileLogWriter("logs/access.%Y%m%d%H%M", 	fileLogOptions("logs/access.log", -1, 10, 1024 * 1024 * 100, time.Hour * 24, nil)...)

	// �쳣��־
	warnLogWriter	=	newFileLogWriter("logs/warn.%Y%m%d%H%M", 	fileLogOptions("logs/warn.log", -1, 10, 1024 * 1024 * 100, time.Hour * 24, nil)...)
	errorLogWriter	=	newFileLogWriter("logs/error.%Y%m%d%H%M", 	fileLogOptions("logs/error.log", -1, 10, 1024 * 1024 * 100, time.Hour * 24, nil)...)
	fatalLogWriter	=	newFileLogWriter("logs/fatal.%Y%m%d%H%M", 	fileLogOptions("logs/fatal.log", -1, 10, 1024 * 1024 * 100, time.Hour * 24, nil)...)
	panicLogWriter	=	newFileLogWriter("logs/panic.%Y%m%d%H%M", 	fileLogOptions("logs/panic.log", -1, 10, 1024 * 1024 * 100, time.Hour * 24, nil)...)

}


// Init ��ʼ����־ϵͳ
func Init (){
	// ��ʼ����־���Ŀ�ĵ�
	intiWriter()

	// Ĭ����־�������app
	Default = newLogger(appLogWriter, "default", logrus.DebugLevel, errHook)

	//SQL��־��¼���������app
	Sql = newLogger(appLogWriter, "sql", logrus.DebugLevel, errHook)

	// ������־�������app��accessLog
	Access = newLogger(io.MultiWriter(accessLogWriter, appLogWriter), "access", logrus.DebugLevel, errHook)

	// ��ӵ�����
	Loggers[(Default.Data[loggerName]).(string)] = Default
	Loggers[(Sql.Data[loggerName]).(string)] = Sql
	Loggers[(Access.Data[loggerName]).(string)] = Access
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
func newLogger(out io.Writer, name string, level logrus.Level, hooks... logrus.Hook) *logrus.Entry {
	logger := logrus.NewEntry(logrus.New()).WithField(loggerName, name)
	logger.Logger.SetFormatter(&logrus.JSONFormatter{
		TimestampFormat:   "2006-01-02 15:04:05",
		DisableHTMLEscape: true,
	})
	logger.Logger.SetReportCaller(true)
	logger.Logger.SetLevel(level)
	logger.Logger.SetOutput(out)
	for _, hook := range hooks {
		logger.Logger.Hooks.Add(hook) // ��¼�쳣��־
	}
	return logger
}

// newFileLogWriter �����µ���־����ļ�
func newFileLogWriter(logFile string, options... rotatelogs.Option) *rotatelogs.RotateLogs {
	var logWriter, err = rotatelogs.New(
		logFile,														// ��־�ļ����Ƹ�ʽ��
		options...
	)
	if err != nil {
		log.Fatalf("������־Writer�쳣: %s\n", err.Error())
	}
	return logWriter
}

