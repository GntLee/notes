--------------------
Route
--------------------
	# ·�ɽӿ� interface Route 

	Route method(HttpMethod method);
		* ��������ķ��������Զ�ε���

	Route path(String path);
	Route pathRegex(String path);
		* ����·��·����֧������

	Route produces(String contentType);
	Route consumes(String contentType);
		* ����Э�̣�����ʹ��ͨ���: *
			consumes("*/json");
			consumes("text/*");

	Route virtualHost(String hostnamePattern);
		* ����·����·��
			router.route().virtualHost("*.vertx.io").handler(ctx -> {
			  // do something if the request is for *.vertx.io
			});

	Route order(int order);
		* ����·�ɵ�ִ��˳��Ĭ��˳���ǰ������˳��
		* ·���ڴ���ʱ������һ������ӵ�·������˳�����Ӧ��˳�򣬵�һ��·�ɱ��0���ڶ���·�ɱ��1���������ơ�
		* ���Ը�д��Ҳ��������Ϊ����ԽСԽ��ִ�У����api������handler����֮ǰ����

	Route last();

	Route handler(Handler<RoutingContext> requestHandler);
		* ������������

	Route blockingHandler(Handler<RoutingContext> requestHandler);
		* ������������Ĭ������£�������������������˳��ִ�е�

	Route subRouter(Router subRouter);
	Route blockingHandler(Handler<RoutingContext> requestHandler, boolean ordered);
		* �������������Լ�����Ҫ��Ҫ����˳��ִ�С�
		* ������ִ�е�˳�򣬲��Ҳ���������ʽ�������Բ��еķ�ʽִ�У�������������ʽ�������� ordered Ϊ false��
		* �����Ҫ��һ�������������д���һ�� multipart ���͵ı����ݣ���Ҫ����ʹ��һ���������Ĵ����������� setExpectMultipart(true) 
			router.post("/some/endpoint").handler(ctx -> {
			  ctx.request().setExpectMultipart(true);
			  ctx.next();
			}).blockingHandler(ctx -> {
			  // ... ִ��ĳЩ��������
			});

	Route failureHandler(Handler<RoutingContext> failureHandler);
		* ��ǰ·�ɵ��쳣��������ͨ�� failureHandler.failure() ��ȡ���쳣��Ϣ
		* ���Զ�ε�����Ӷ��
		* ����ִ��˳�򣬻����˵�route��failureHandler����ִ��
		* �����failureHandler��ִ����next()�������¼���ִ������route��failureHandler()

	Route remove();
		* �Ƴ�·��

	Route disable();
	Route enable();
		* ����/����·��

	@Deprecated
	default Route useNormalisedPath(boolean useNormalizedPath)
	Route useNormalizedPath(boolean useNormalizedPath);
	String getPath();
	boolean isRegexPath();
	boolean isExactPath();
	Set<HttpMethod> methods();
	Route setRegexGroupsNames(List<String> groups);
	Route setName(String name);
	String getName();

	default <T> Route respond(Function<RoutingContext, Future<@Nullable T>> function)
		* ��ݵĴ���������� Future �ķ��ؽ������Ϊjson
		* �����������з������󣬻���Ӧ���ʵ�״̬��
		router.get("/").respond(ctx -> {
			return Future.succeededFuture(new JsonObject().put("success", true));
		});

