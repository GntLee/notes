----------------
logger
----------------
	# logger��־�ӿ�
		type Interface interface {
			LogMode(LogLevel) Interface
				* ��־����
					Silent
					Error
					Warn
					Info

			Info(context.Context, string, ...interface{})
			Warn(context.Context, string, ...interface{})
			Error(context.Context, string, ...interface{})
			Trace(ctx context.Context, begin time.Time, fc func() (string, int64), err error)
		}



	# ����logger
		newLogger := logger.New(log.New(os.Stdout, "", log.LstdFlags), logger.Config{
			SlowThreshold: time.Second,		// ��ӡ��SQL��ִ��ʱ��
			Colorful: false,				// �Ƿ��ɫ��ӡ
			LogLevel:logger.Silent,			// ��־����
		})

	
	# Ĭ�ϵ�logger
		Default = New(log.New(os.Stdout, "\r\n", log.LstdFlags), Config{
			SlowThreshold: 200 * time.Millisecond,
			LogLevel:      Warn,
			Colorful:      true,
		})
	
	# ȫ������
		db, err := gorm.Open(sqlite.Open("test.db"), &gorm.Config{
		  Logger: newLogger,
		})
	
	# �Ự����
		tx := db.Session(&Session{Logger: newLogger})
	
	# gorm����debug��־
		func (db *DB) Debug() (tx *DB)

		* �����Ͼ��ǰѵ�ǰdb��logger��level����Ϊ��info�����ҿ���һ��Session
			return db.Session(&Session{
				Logger: db.Logger.LogMode(logger.Info),
			})