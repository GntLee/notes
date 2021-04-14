------------------------
Context
------------------------
	# Verticle���������Ľӿڣ� interface Context

------------------------
����
------------------------
	void runOnContext(Handler<Void> action)
		* ��context���첽ִ�д���

	<T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, boolean ordered, Handler<AsyncResult<@Nullable T>> resultHandler)
	<T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, Handler<AsyncResult<@Nullable T>> resultHandler)
	<T> Future<@Nullable T> executeBlocking(Handler<Promise<T>> blockingCodeHandler, boolean ordered)
	<T> Future<T> executeBlocking(Handler<Promise<T>> blockingCodeHandler)
	String deploymentID()
	@Nullable JsonObject config()
	List<String> processArgs()

	boolean isEventLoopContext()
	boolean isWorkerContext()
		* �ж�Context����
	
	<T> T get(String key)
	void put(String key, Object value)
	boolean remove(String key)
		* ��ͬһ��Context�д���/��ȡ/ɾ������

	<T> T getLocal(String key)
	void putLocal(String key, Object value)
	boolean removeLocal(String key)
		
	Vertx owner();
	int getInstanceCount();
	Context exceptionHandler(@Nullable Handler<Throwable> handler);
	Handler<Throwable> exceptionHandler();

------------------------
��̬
------------------------
	static boolean isOnWorkerThread() 
	static boolean isOnEventLoopThread() 
	static boolean isOnVertxThread()
		* �ж��Ƿ��ǹ�����Vertx��Context
