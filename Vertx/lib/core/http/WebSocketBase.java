--------------------
WebSocketBase
--------------------
	# WebSocket ������� interface WebSocketBase extends ReadStream<Buffer>, WriteStream<Buffer> 

	WebSocketBase exceptionHandler(Handler<Throwable> handler);
	WebSocketBase handler(Handler<Buffer> handler);
	WebSocketBase pause();
	WebSocketBase resume();
	WebSocketBase fetch(long amount);
	WebSocketBase endHandler(Handler<Void> endHandler);
	WebSocketBase setWriteQueueMaxSize(int maxSize);
	WebSocketBase drainHandler(Handler<Void> handler);
	String binaryHandlerID();
	String textHandlerID();
		* WebSocket�������¼��������Զ�ע�����������������˴������н��յ��κ�����ʱ�� ���Ὣ����д��WebSocket��
		* �������������Ǳ��ض��ģ�����·�ɵ���Ⱥ��
		* ���������ǻ�ȡ�����ĵ�ַ


	String subProtocol();
	Short closeStatusCode();
	String closeReason();
	MultiMap headers();

	Future<Void> writeFrame(WebSocketFrame frame);
	WebSocketBase writeFrame(WebSocketFrame frame, Handler<AsyncResult<Void>> handler);
	Future<Void> writeFinalTextFrame(String text);
	WebSocketBase writeFinalTextFrame(String text, Handler<AsyncResult<Void>> handler);
	Future<Void> writeFinalBinaryFrame(Buffer data);
	WebSocketBase writeFinalBinaryFrame(Buffer data, Handler<AsyncResult<Void>> handler);
	Future<Void> writeBinaryMessage(Buffer data);
	WebSocketBase writeBinaryMessage(Buffer data, Handler<AsyncResult<Void>> handler);
	Future<Void> writeTextMessage(String text);
	WebSocketBase writeTextMessage(String text, Handler<AsyncResult<Void>> handler);
	WebSocketBase writePing(Buffer data, Handler<AsyncResult<Void>> handler);
	Future<Void> writePing(Buffer data);
	WebSocketBase writePong(Buffer data, Handler<AsyncResult<Void>> handler);
	Future<Void> writePong(Buffer data);

	WebSocketBase closeHandler(@Nullable Handler<Void> handler);
	WebSocketBase frameHandler(@Nullable Handler<WebSocketFrame> handler);
	WebSocketBase textMessageHandler(@Nullable Handler<String> handler);
	WebSocketBase binaryMessageHandler(@Nullable Handler<Buffer> handler);
	WebSocketBase pongHandler(@Nullable Handler<Buffer> handler);

	Future<Void> end();
	void end(Handler<AsyncResult<Void>> handler);

	Future<Void> close();
	void close(Handler<AsyncResult<Void>> handler);
	Future<Void> close(short statusCode);
	void close(short statusCode, Handler<AsyncResult<Void>> handler);
	Future<Void> close(short statusCode, @Nullable String reason);
	void close(short statusCode, @Nullable String reason, Handler<AsyncResult<Void>> handler);
		* �ر����ӣ�����״̬���ԭ��

	SocketAddress remoteAddress();
	SocketAddress localAddress();
		* ��ȡ������Զ�̵ĵ�ַ

	boolean isSsl();
	boolean isClosed();
	SSLSession sslSession();
	X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;
