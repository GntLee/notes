-----------------------------
FaviconHandler
-----------------------------
	# ��վͼ��handler�� FaviconHandler extends Handler<RoutingContext> 
		* Ĭ�ϻ��classpathĿ¼�¼��أ� favicon.ico �ļ�


	long DEFAULT_MAX_AGE_SECONDS = 86400;
		* Ĭ�ϻ���ʱ�䣬1��
	
	static FaviconHandler create(Vertx vertx) 
	static FaviconHandler create(Vertx vertx, String path)
	static FaviconHandler create(Vertx vertx, String path, long maxAgeSeconds)
	static FaviconHandler create(Vertx vertx, long maxAgeSeconds)
