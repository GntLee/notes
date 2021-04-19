-----------------------
Vertx
-----------------------
	# ʵ���� io.vertx.core.metrics.Measured �Ľӿ�
		interface Vertx extends Measured

-----------------------
��̬
-----------------------
	static Vertx vertx()
	static Vertx vertx(VertxOptions options) 
	static void clusteredVertx(VertxOptions options, Handler<AsyncResult<Vertx>> resultHandler)
	static Future<Vertx> clusteredVertx(VertxOptions options)
	static @Nullable Context currentContext() 

-----------------------
ʵ��
-----------------------
	Context getOrCreateContext()
		* ���Ѿ���һ��context�͵�ǰ�̹߳�������ô��ֱ���������context���� ���û���򴴽�һ���µķ���

	NetServer createNetServer(NetServerOptions options)
	NetServer createNetServer()
	NetClient createNetClient(NetClientOptions options)
	NetClient createNetClient()
	HttpServer createHttpServer(HttpServerOptions options)
	HttpServer createHttpServer()
	HttpClient createHttpClient(HttpClientOptions options)
	HttpClient createHttpClient()
	DatagramSocket createDatagramSocket(DatagramSocketOptions options)
	DatagramSocket createDatagramSocket()
	@CacheReturn
	FileSystem fileSystem();
	@CacheReturn
	EventBus eventBus();
	DnsClient createDnsClient(int port, String host);
	DnsClient createDnsClient();
	DnsClient createDnsClient(DnsClientOptions options);
	@CacheReturn
	SharedData sharedData();

	long setTimer(long delay, Handler<Long> handler);
		* ����һ�����ӳٲ���

	TimeoutStream timerStream(long delay);
	long setPeriodic(long delay, Handler<Long> handler);
		* ÿ��delay����󣬾ͻ�ִ�� handlerһ��

	TimeoutStream periodicStream(long delay);
	boolean cancelTimer(long id);
		* �����ʱ��

	void runOnContext(Handler<Void> action);
	Future<Void> close();
	void close(Handler<AsyncResult<Void>> completionHandler);
	@GenIgnore(GenIgnore.PERMITTED_TYPE)

	Future<String> deployVerticle(Verticle verticle);
	@GenIgnore(GenIgnore.PERMITTED_TYPE)
	void deployVerticle(Verticle verticle, Handler<AsyncResult<String>> completionHandler);
	@GenIgnore(GenIgnore.PERMITTED_TYPE)
	Future<String> deployVerticle(Verticle verticle, DeploymentOptions options);
	@GenIgnore
	Future<String> deployVerticle(Class<? extends Verticle> verticleClass, DeploymentOptions options);
	@GenIgnore(GenIgnore.PERMITTED_TYPE)
	Future<String> deployVerticle(Supplier<Verticle> verticleSupplier, DeploymentOptions options);
	@GenIgnore(GenIgnore.PERMITTED_TYPE)
	void deployVerticle(Verticle verticle, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler);
	@GenIgnore
	void deployVerticle(Class<? extends Verticle> verticleClass, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler);
	@GenIgnore(GenIgnore.PERMITTED_TYPE)
	void deployVerticle(Supplier<Verticle> verticleSupplier, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler);
	Future<String> deployVerticle(String name);
	void deployVerticle(String name, Handler<AsyncResult<String>> completionHandler);
	Future<String> deployVerticle(String name, DeploymentOptions options);
	void deployVerticle(String name, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler);
		* ����Verticle���������������ƣ���ȫ·����������Ϣ�ȵ�

	Future<Void> undeploy(String deploymentID);
	void undeploy(String deploymentID, Handler<AsyncResult<Void>> completionHandler);
	Set<String> deploymentIDs();
	@GenIgnore(GenIgnore.PERMITTED_TYPE)
	void registerVerticleFactory(VerticleFactory factory);
	@GenIgnore(GenIgnore.PERMITTED_TYPE)
	void unregisterVerticleFactory(VerticleFactory factory);
	@GenIgnore(GenIgnore.PERMITTED_TYPE)
	Set<VerticleFactory> verticleFactories();
	boolean isClustered();

	<T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, boolean ordered, Handler<AsyncResult<@Nullable T>> resultHandler);
	<T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, Handler<AsyncResult<@Nullable T>> resultHandler);
	<T> Future<@Nullable T> executeBlocking(Handler<Promise<T>> blockingCodeHandler, boolean ordered);
	<T> Future<T> executeBlocking(Handler<Promise<T>> blockingCodeHandler);
		* ������ Event Loop ��ֱ�ӵ�������ʽ��������Ϊ����������ֹ Event Loop ִ���������õ�����
		* Ӧ���� Vert.x Ӧ���а�ȫ����"��ͳ"����API�ķ���
		* �������ĵ��� executeBlocking ��˳�� ���Խ� ordered ������ֵ��Ϊ false�������κ� executeBlocking ������ Worker Pool �в���ִ�С�
			vertx.executeBlocking(promise -> {
				try {
					Thread.sleep(1000);
					promise.complete("ok");
				} catch (InterruptedException e) {
					promise.fail(e);
				}
			}).onComplete(result -> {
				if (result.succeeded()) {
					System.out.println("�첽���:" + result.result());
				} else {
					System.out.println("�첽�쳣:" + result.cause().getMessage() );
				}
			});
		
		* ��һ�������Ĳ�����������10�룬blocked thread checker�����ڿ���̨�ϴ�ӡһ����Ϣ�� 
		* ��ʱ�������Ĳ���Ӧ���ɳ���ʹ��һ��ר�õ��̹߳��� ����Ҫ�ܹ�ʹ��event-bus �� runOnContext ��verticles����




	@GenIgnore(GenIgnore.PERMITTED_TYPE)
	EventLoopGroup nettyEventLoopGroup();

	WorkerExecutor createSharedWorkerExecutor(String name);
	WorkerExecutor createSharedWorkerExecutor(String name, int poolSize);
	WorkerExecutor createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime);
	WorkerExecutor createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime, TimeUnit maxExecuteTimeUnit);
		* ����һ����������ʽ����ķ�ʽ��ʹ�� worker verticle
		* һ�� Worker Verticle ʼ�ջ�ʹ�� Worker Pool �е�ĳ���߳���ִ�С�
		* Ĭ������£�����ʽ������� Vert.x �� Worker Pool ��ִ�У�ͨ�� setWorkerPoolSize ���á�
			WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool");
			executor.executeBlocking(promise -> {
			  // ���������ġ���Ҫ��������ִ��ʱ���API
			  String result = someAPI.blockingMethod("hello");
			  promise.complete(result);
			}, res -> {
			  System.out.println("The result is: " + res.result());
			});
		
		* ��ʹ��ͬһ�����ִ�������� worker ʱ�����ǽ�����ͬһ�� pool
		* Worker Executor �ڲ���Ҫ��ʱ����뱻�رգ������е� worker executor ������ close �������رչ��󣬶�Ӧ�� worker pool �ᱻ���١�
			executor.close();
		* ��� Worker Executor �� Verticle �д�������ô Verticle ʵ�����ٵ�ͬʱ Vert.x �����Զ��ر���� Worker Executor��
		* Worker Executor �����ڴ�����ʱ������
			int poolSize = 10;
			// 2����
			long maxExecuteTime = 2;
			TimeUnit maxExecuteTimeUnit = TimeUnit.MINUTES;
			WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool", poolSize, maxExecuteTime, maxExecuteTimeUnit);
			
		
	
	@CacheReturn
	boolean isNativeTransportEnabled();
	@Fluent
	Vertx exceptionHandler(@Nullable Handler<Throwable> handler);
	@GenIgnore
	@Nullable Handler<Throwable> exceptionHandler();