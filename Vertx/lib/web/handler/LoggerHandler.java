--------------------------
LoggerHandler
--------------------------
	# ������־�ӿڣ� interface LoggerHandler extends Handler<RoutingContext>

	LoggerFormat DEFAULT_FORMAT = LoggerFormat.DEFAULT;
		* Ĭ�ϵĸ�ʽ��
	
	static LoggerHandler create()
	static LoggerHandler create(LoggerFormat format)
	static LoggerHandler create(boolean immediate, LoggerFormat format) 
		* ����������
			immediate	��������󵽴�ʱ�������м�¼����Ϊtrue��
			format		�����ƶ�format

	
	LoggerHandler customFormatter(Function<HttpServerRequest, String> formatter)
		* ��ʽ����־���󷽷�
	
--------------------------
LoggerFormat
--------------------------
	# ��ʽ��ö��
		DEFAULT			Ĭ��
		SHORT			��
		TINY			��С
		CUSTOM			�Զ���
	

	