--------------------
Application			|
--------------------
	# ��̬��ȫ�ֱ���
		String STYLESHEET_CASPIAN = "CASPIAN";
		String STYLESHEET_MODENA = "MODENA";

	# ��̬����
		void launch(Class<? extends Application> appClass, String... args)
			* ָ�� Application �ļ̳���, ����
		
		void launch(String... args)
			* Դ���Ǳ���ִ����ջ, �ҵ�ִ�� Application.launch ����, �Ҳ������쳣
			* ����������Ѿ��̳���, Application, ������, �����쳣

	# ���췽��
		public Application()
		String getUserAgentStylesheet()
		void setUserAgentStylesheet(String url)

	# ʵ������
		abstract void start(Stage primaryStage)
			* �����าд����������

		void init()
		void stop()
			* �������ڷ���, ��app�������͹ر�֮ǰִ��
		
		HostServices getHostServices()
			* ���� HostServices
		
		Parameters getParameters()
		void notifyPreloader(PreloaderNotification info)

--------------------
Parameters			|
--------------------
	# Application �ڲ��ྲ̬������
		public Parameters()
		List<String> getRaw()
		List<String> getUnnamed()
		Map<String, String> getNamed()


	