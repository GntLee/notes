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
		
