--------------------
zap
--------------------
	# Uber��Դ����־��ܣ��������ܺ�ţ��
		https://github.com/uber-go/zap

	
	# ������logger, ��ȫ, ����
		logger, err := zap.NewProduction()
		if err != nil {
			log.Fatalf("logger create err: %s\n", err.Error())
		}
		logger.Info("Hello", zap.String("name", "world"))
		// {"level":"info","ts":1622097413.9677129,"caller":"go-project/main.go:14","msg":"Hello","name":"world"}
	
	# SugaredLogger
		* ֧�ֽṹ����printf��ʽ����־

		logger, err := zap.NewProduction()
		if err != nil {
			log.Fatalf("logger create err: %s\n", err.Error())
		}
		sugar := logger.Sugar()
		sugar.Info("---")
		sugar.Infof("Hello %s", "world")
	
	# Ĭ������£���¼�����޻����
		* ����zap�ĵͼ�API�����壬������˳�����֮ǰ����Sync��һ����ϰ��
			logger, err := zap.NewProduction()
			defer func() {
				if err := logger.Sync(); err != nil {
					fmt.Printf("logger sync err: %s\n", err.Error())
				}
			}()
		
	
	# ��־��¼�Ĳ㼶��ϵ
		* ����ʹ�� zap.Namespace(key string) Field ����һ�������ռ䣬������Field����¼�ڴ������ռ���

		* �����ʱ�����������ռ�
			  logger := zap.NewExample()
			  defer logger.Sync()

			  logger.Info("tracked some metrics",
				zap.Namespace("metrics"),  // �����ռ�
				zap.Int("counter", 1),
			  )
			  // {"level":"info","msg":"tracked some metrics","metrics":{"counter":1}}
		
		* �����µ�loggerʱ���������ռ�
			  logger2 := logger.With(
				zap.Namespace("metrics"),
				zap.Int("counter", 1),
			  )
			  logger2.Info("tracked some metrics")
			  // {"level":"info","msg":"tracked some metrics","metrics":{"counter":1}}

	
	# ȫ��logger
		* zap�ṩ������ȫ�ֵ�Logger��һ����*zap.Logger���ɵ���zap.L()��ã���һ����*zap.SugaredLogger���ɵ���zap.S()��á�
		* ��Ҫע����ǣ�ȫ�ֵ�LoggerĬ�ϲ������¼��־������һ����ʵ��Ч����Logger��
			// go.uber.org/zap/global.go
			var (
			  _globalMu sync.RWMutex
			  _globalL  = NewNop()
			  _globalS  = _globalL.Sugar()
			)
		
		* ����ʹ��ReplaceGlobals(logger *Logger) func()��logger����Ϊȫ�ֵ�Logger���ú�������һ���޲κ��������ڻָ�ȫ��Logger����
			func main() {
			  zap.L().Info("global Logger before")
			  zap.S().Info("global SugaredLogger before")  // û�����

			  logger := zap.NewExample()
			  defer logger.Sync()

			  zap.ReplaceGlobals(logger)				// ʹ��ָ����logger����ȫ�ֵ�

			  zap.L().Info("global Logger after")
			  zap.S().Info("global SugaredLogger after")
			}


	
	# ����
		import (
			"fmt"
			"go.uber.org/zap"
			"go.uber.org/zap/zapcore"
			"os"
			"time"
		)


		func main() {
			// ��Ϣ����������
			encodeConfig := zap.NewProductionEncoderConfig()
			// ʱ���ʽ��
			encodeConfig.EncodeTime = func(time time.Time, encoder zapcore.PrimitiveArrayEncoder) {
				encoder.AppendString(time.Format("2006-01-02 15:04:05"))
			}

			// ��Ϣ������
			jsonEncode := zapcore.NewJSONEncoder(encodeConfig)
			jsonEncode.AddString("foo", "bar") // ���ȫ�ֵ�KEY/VALUE

			// ��־���Ŀ�ĵ�
			writer := zapcore.NewMultiWriteSyncer(zapcore.AddSync(os.Stdout))

			// ��̬����־����
			level := zap.NewAtomicLevelAt(zapcore.DebugLevel)

			// ������������
			core := zapcore.NewCore(jsonEncode, writer, level)

			// ��־��¼����һЩѡ��
			options := []zap.Option {
				zap.AddCaller(),		// ��־����ӵ�����Ϣ
				zap.Hooks(func(entry zapcore.Entry) error {  // ��ӹ��Ӻ���
					if entry.Level == zapcore.DebugLevel {
						fmt.Println("Debug����")
					}
					return nil
				}),
			}

			// ������־��¼��
			logger := zap.New(core, options...)
			defer logger.Sync() // ���ռǵ�ͬ��һ��

			logger.Fatal("Hello")
		}
	
