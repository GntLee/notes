---------------------
LocalSessionStore
---------------------
	# ����Session�洢�ӿڣ� interface LocalSessionStore extends SessionStore

	# Ĭ��ʹ�� vertx.sharedData().getLocalMap() �洢����

---------------------
static
---------------------
	static LocalSessionStore create(Vertx vertx) 
	static LocalSessionStore create(Vertx vertx, String sessionMapName)
	static LocalSessionStore create(Vertx vertx, String sessionMapName, long reaperInterval)


	long DEFAULT_REAPER_INTERVAL = 1000;
		* ���ɨ��һ��session���Ƴ����ڵĻỰ

	String DEFAULT_SESSION_MAP_NAME = "vertx-web.sessions";
		* sharedData�е�map����
