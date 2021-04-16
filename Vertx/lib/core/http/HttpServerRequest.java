---------------------
HttpServerRequest
---------------------
	# HttpRequest ����ӿڣ� interface HttpServerRequest extends ReadStream<Buffer>

---------------------
����
---------------------

---------------------
ʵ��
---------------------
	HttpServerRequest exceptionHandler(Handler<Throwable> handler);
	HttpServerRequest handler(Handler<Buffer> handler);
		* �����������嵽���ʱ������ִ��
		* ���ܻᱻ���ö��

	HttpServerRequest pause();
	HttpServerRequest resume();
	HttpServerRequest fetch(long amount);
	HttpServerRequest endHandler(Handler<Void> endHandler);
		* ���������󣨰������������壩�Ѿ�����ȫ��ȡʱ�����ᱻ����

	HttpVersion version();
	HttpMethod method();
		* HTTP�汾�ţ�����

	default boolean isSSL()
	String scheme();
		* Э��
	String uri();
		* ����URI��������ѯ�ַ���
	String path();
		* ����·������������ѯ�ַ���
	String query();
		* ��ѯ�ַ�����������?
	String host();
		* �������������
		* ���� HTTP/1.x ���󷵻�����ͷ�е� host ֵ������ HTTP/2 �����򷵻�αͷ�е� :authority ��ֵ��
	long bytesRead();

	HttpServerResponse response();
		* ��ȡ��Ӧ����

	MultiMap headers();
	default String getHeader(String headerName)
	default String getHeader(CharSequence headerName)
		* ��ȡHeader

	MultiMap params();
	default String getParam(String paramName)
		* ��ѯ���������������Body����

	default SocketAddress remoteAddress()
	default SocketAddress localAddress()
		* Զ�̵�ַ�ͱ��ص�ַ

	default SSLSession sslSession()
	X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;
	String absoluteURI();
		* ��ȡ�����к����URI��Ӧ�ľ���URI

	default HttpServerRequest bodyHandler(@Nullable Handler<Buffer> bodyHandler)
	default HttpServerRequest body(Handler<AsyncResult<Buffer>> handler)
	Future<Buffer> body();
		* ���ھۺ����������壬�����������嵽�����������ᱻ����
		* �����������󣬿��ܻᵼ���ڴ����
		* bodyHandler ֻ���ڳɹ���ʱ����ã�body ��Ҫ�Լ��ж��Ƿ�ɹ�

	default void end(Handler<AsyncResult<Void>> handler)
	Future<Void> end();

	default void toNetSocket(Handler<AsyncResult<NetSocket>> handler)
	Future<NetSocket> toNetSocket();

	HttpServerRequest setExpectMultipart(boolean expect);
	boolean isExpectMultipart();
		* �ڶ�ȡ��֮ǰ�����ã���ʾ���������ύ��

	HttpServerRequest uploadHandler(@Nullable Handler<HttpServerFileUpload> uploadHandler);
		* ����ಿ�������ϴ�������

	MultiMap formAttributes();
	String getFormAttribute(String attributeName);
		* body��������ֻ����body��ȡ���֮�����ʹ��

	default int streamId() 
	default void toWebSocket(Handler<AsyncResult<ServerWebSocket>> handler)
	Future<ServerWebSocket> toWebSocket();
	boolean isEnded();
	HttpServerRequest customFrameHandler(Handler<HttpFrame> handler);
		* ����http2���Զ���֡

	HttpConnection connection();
	default StreamPriority streamPriority()
	HttpServerRequest streamPriorityHandler(Handler<StreamPriority> handler)
	default @Nullable Cookie getCookie(String name)
	default int cookieCount()
	Map<String, Cookie> cookieMap()
	default HttpServerRequest routed(String route)

---------------------
��̬
---------------------