-------------------
viper
-------------------
	# Doc
		https://github.com/spf13/viper
		
		https://www.liwenzhou.com/posts/Go/viper_tutorial/
	

	
	# ��ȡ����
		v := viper.New()

		// ���ü���Ŀ¼�������ж��
		v.AddConfigPath("./config")
		v.AddConfigPath("D:\\config")

		// ���������ļ�����
		v.SetConfigName("app")
		// �����ļ���׺
		v.SetConfigType("yaml")

		// ��ȡ����
		if err := v.ReadInConfig(); err != nil {
			log.Fatal(err)
		}