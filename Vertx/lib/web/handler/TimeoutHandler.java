------------------------
TimeoutHandler
------------------------
	# ��ʱ�������� interface TimeoutHandler extends Handler<RoutingContext> 

	long DEFAULT_TIMEOUT = 5000;
	int DEFAULT_ERRORCODE = 503;
		* Ĭ�ϵĳ�ʱʱ�����Ӧ״̬��
	
	static TimeoutHandler create()
	static TimeoutHandler create(long timeout)
	static TimeoutHandler create(long timeout, int errorCode)
