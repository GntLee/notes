---------------------------------
RoutingContext
---------------------------------
	# �����Ľӿڣ� interface RoutingContext


	HttpServerRequest request();
	HttpServerResponse response();
	void next();

	void fail(int statusCode);
	void fail(Throwable throwable);
	void fail(int statusCode, Throwable throwable);
		* ���ٵĴ���ʧ�ܣ��ᴥ���� Route �� failureHandler ����

	RoutingContext put(String key, Object obj);
	<T> T get(String key);
	<T> T remove(String key);
	Map<String, Object> data();
		* context�����������ݣ�������Servet�е� requestScope

	Vertx vertx();
	@Nullable String mountPoint();
	Route currentRoute();
		* ��ȡ��ǰ·�ɶ���

	@Deprecated
	default String normalisedPath()
	String normalizedPath();

	@Nullable Cookie getCookie(String name);
	RoutingContext addCookie(io.vertx.core.http.Cookie cookie);
	default @Nullable Cookie removeCookie(String name)
	@Nullable Cookie removeCookie(String name, boolean invalidate)
	int cookieCount();
	Map<String, io.vertx.core.http.Cookie> cookieMap();
		* cookie����/��ȡ/ɾ��

	@Nullable String getBodyAsString();
	@Nullable String getBodyAsString(String encoding);
	@Nullable JsonObject getBodyAsJson(int maxAllowedLength);
	@Nullable JsonArray getBodyAsJsonArray(int maxAllowedLength);
	default @Nullable JsonObject getBodyAsJson() 
	default @Nullable JsonArray getBodyAsJsonArray()
		* ����bodyΪjson

	@Nullable Buffer getBody();
		* ��ȡԭʼ��body

	Set<FileUpload> fileUploads();
		* ��ȡ���ϴ����ļ�

	@Nullable Session session();
	boolean isSessionAccessed();
	

	@Nullable User user();
	Throwable failure();
		* ��ȡfail�׳����쳣

	int statusCode();
		* ��ȡ�Ѿ����õ�״̬��

	@Nullable String getAcceptableContentType();
		* ����Acceptͷ��ȡ�ͻ���֧�ֵ�ContentType
		* ˭��qֵ�ߣ�����˭

	ParsedHeaderValues parsedHeaders();
	int addHeadersEndHandler(Handler<Void> handler);
	boolean removeHeadersEndHandler(int handlerID);
	int addBodyEndHandler(Handler<Void> handler);
	boolean removeBodyEndHandler(int handlerID);
	int addEndHandler(Handler<AsyncResult<Void>> handler);
	default Future<Void> addEndHandler()
	boolean removeEndHandler(int handlerID);
	boolean failed();
	void setBody(Buffer body);
	void setSession(Session session);
	void setUser(User user);
	void clearUser();
	void setAcceptableContentType(@Nullable String contentType);

	default void reroute(String path)
	void reroute(HttpMethod method, String path)
		* ��д·�ɣ��൱��Servlet�е�forward��
		* ���¿�����Ӷ���Ĳ�ѯ�������������ɵĲ�ѯ�����ᱻ����
			router.get().handler(ctx -> ctx.reroute("/final-target?variable=value"));
		
	default List<LanguageHeader> acceptableLanguages()
	default LanguageHeader preferredLanguage()
		* ��ȡ�ͻ���֧�ֵ��������ԣ����ߵ�һ������


	Map<String, String> pathParams()
	String pathParam(String name)
		* ��ȡ·�ɲ���������path����������ƥ�䵽�Ĳ���

	MultiMap queryParams()
	MultiMap queryParams(Charset encoding)
	List<String> queryParam(String name)

	default RoutingContext attachment(String filename)
		* ���ø������ƣ���Ҫ�ڽ��������ͻ���д������

	default Future<Void> redirect(String url
	default RoutingContext redirect(String url, Handler<AsyncResult<Void>> handler)
		* �ض���
		* "back" ��һ������ֵ�����ض�������� referrer ��ַ
		* ���ûreferrer�����ض��򵽸�·��: /

	default Future<Void> json(Object json)
	default RoutingContext json(Object json, Handler<AsyncResult<Void>> handler)
		* ���ٵ���Ӧjson�����Զ�ε���(а��)

	default boolean is(String type)
		* �ж������ContentType�Ƿ�Ϊָ������
			is("html")
			is("text/html")

	default boolean isFresh()
	default RoutingContext etag(String etag)

	default RoutingContext lastModified(Instant instant)
	default RoutingContext lastModified(String instant)
		* etagͷ���
		
	default Future<Void> end(String chunk)
	default RoutingContext end(String chunk, Handler<AsyncResult<Void>> handler)
	default Future<Void> end(Buffer buffer)
	default RoutingContext end(Buffer buffer, Handler<AsyncResult<Void>> handler)
	default Future<Void> end()
	default RoutingContext end(Handler<AsyncResult<Void>> handler)