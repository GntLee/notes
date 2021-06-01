import (
	"bytes"
	"encoding/json"
	"fmt"
	"go.uber.org/zap"
	"go.uber.org/zap/zapcore"
	"gopkg.in/natefinch/lumberjack.v2"
	"io"
	"os"
	"path/filepath"
	"strings"
	"time"
)

var Loggers = make(map[string] *zap.Logger)

// loggers
var (
	// App appȫ����־��¼��
	App *zap.Logger
)

// writers
var (
	// AppLogWriter Appȫ����־���
	AppLogWriter = NewLogFileWriter(filepath.Join("log", "app.log"), 100, 0, 10, true, true)

	// ErrorLogWriter �쳣��־���
	ErrorLogWriter = NewLogFileWriter(filepath.Join("log", "error", "error.log"), 100, 0, 10, true, true)
)



// Close �ͷ�������Դ
func Close () {
	for name, logger := range Loggers {
		if err := logger.Sync(); err != nil {
			fmt.Fprintf(os.Stderr, "loggerͬ���쳣, logger=%s, err=%s\n", name, err.Error())
		}
	}
	var CloseLogger = func(logger *lumberjack.Logger) (){
		if err := logger.Close(); err != nil {
			fmt.Fprintf(os.Stderr, "��־�ļ��ر��쳣, logger=%s, err=%s\n", logger.Filename, err.Error())
		}
	}
	CloseLogger(AppLogWriter)
	CloseLogger(ErrorLogWriter)
}


// ErrorEvent �쳣��־ʱ�䴦��
func ErrorEvent (entry zapcore.Entry) error {
	if entry.Level < zapcore.ErrorLevel {
		return nil
	}

	var writer io.Writer

	switch entry.Level {
		case zapcore.ErrorLevel, zapcore.PanicLevel, zapcore.DPanicLevel, zapcore.FatalLevel: writer = ErrorLogWriter
		// TODO �����ò�ͬ�������־���������ͬ���ļ�
		default: return nil
	}

	// ����ΪJSON
	jsonData, err := json.Marshal(entry)
	if err != nil {
		return err
	}

	buffer := bytes.NewBuffer(jsonData)
	buffer.WriteString("\n")  // д�뻻�з�

	// �������־�ļ�
	if _, err := buffer.WriteTo(writer); err != nil {
		return err
	}

	go func() {
		// TODO �����ʼ�
		// var content = buffer.String()
	}()

	return nil
}

// NewLogFileWriter �����µ���־����ļ�
func NewLogFileWriter(file string, maxSize, maxAge, maxBackups int, localTime, compress bool) *lumberjack.Logger {
	return &lumberjack.Logger {
		Filename:   file,
		MaxSize:    maxSize,
		MaxAge:     maxAge,
		MaxBackups: maxBackups,
		LocalTime:  localTime,
		Compress:   compress,
	}
}

// NewLogger �����µ�logger
func NewLogger (name string, level zap.AtomicLevel, writer zapcore.WriteSyncer) *zap.Logger {
	// ��Ϣ����������
	encodeConfig := zap.NewProductionEncoderConfig()
	encodeConfig.MessageKey = "message"
	encodeConfig.TimeKey = "time"
	encodeConfig.EncodeLevel = func(level zapcore.Level, encoder zapcore.PrimitiveArrayEncoder) {
		encoder.AppendString(strings.ToUpper(level.String()))
	}
	encodeConfig.CallerKey = "file"
	// ʱ���ʽ��
	encodeConfig.EncodeTime = func(time time.Time, encoder zapcore.PrimitiveArrayEncoder) {
		encoder.AppendString(time.Format("2006-01-02 15:04:05"))
	}

	// ������������
	core := zapcore.NewCore(zapcore.NewJSONEncoder(encodeConfig), writer, level)

	// ��־��¼����һЩѡ��
	options := []zap.Option {
		zap.AddCaller(),						// ��־����ӵ�����Ϣ
		zap.AddStacktrace(zapcore.ErrorLevel),  // �쳣�������ϣ���ӵ���ջ��Ϣ
		zap.Hooks(ErrorEvent),
	}

	// ������־��¼��, ��������
	return zap.New(core, options...).Named(name)
}


func Init (){
	var appLogFile = zapcore.Lock(zapcore.AddSync(AppLogWriter))
	// App��־���������׼������ļ�
	App = NewLogger("app", zap.NewAtomicLevelAt(zapcore.DebugLevel), zapcore.NewMultiWriteSyncer(zapcore.AddSync(os.Stdout), appLogFile))

	Loggers["app"] = App
}