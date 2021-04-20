---------------------------
SessionStore
---------------------------
	# Session�洢ʵ�ֽӿڣ� static SessionStore create(Vertx vertx)
	
	int DEFAULT_SESSIONID_LENGTH = 16;
	long retryTimeout();
	static SessionStore create(Vertx vertx, JsonObject options) 

	SessionStore init(Vertx vertx, JsonObject options)

	Session createSession(long timeout);
	Session createSession(long timeout, int length);
		* �����µ�session

	void get(String cookieValue, Handler<AsyncResult<@Nullable Session>> resultHandler);
	default Future<@Nullable Session> get(String cookieValue)
		* ����cookieֵ��ȡsession

	void delete(String id, Handler<AsyncResult<Void>> resultHandler)
	default Future<Void> delete(String cookieValue)
		* ɾ��session

	void put(Session session, Handler<AsyncResult<Void>> resultHandler)
	default Future<Void> put(Session session)
		* ����session

	void clear(Handler<AsyncResult<Void>> resultHandler)
	default Future<Void> clear()
		* ɾ������session

	void size(Handler<AsyncResult<Integer>> resultHandler)
	default Future<Integer> size()
		* ��ȡ�洢��session��������

	void close();