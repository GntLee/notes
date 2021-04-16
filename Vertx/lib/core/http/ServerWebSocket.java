------------------------------
ServerWebSocket
------------------------------
	# �����WebSocket��  interface ServerWebSocket extends WebSocketBase 

	ServerWebSocket exceptionHandler(Handler<Throwable> handler);
		* �쳣������

	ServerWebSocket handler(Handler<Buffer> handler);
		* ��Ϣ������

	ServerWebSocket pause();
	ServerWebSocket resume();
	ServerWebSocket fetch(long amount);

	ServerWebSocket endHandler(Handler<Void> endHandler);
	ServerWebSocket setWriteQueueMaxSize(int maxSize);
		* д���д�С

	ServerWebSocket drainHandler(Handler<Void> handler);

	ServerWebSocket writeFrame(WebSocketFrame frame, Handler<AsyncResult<Void>> handler);
	ServerWebSocket writeFinalTextFrame(String text, Handler<AsyncResult<Void>> handler);
	ServerWebSocket writeFinalBinaryFrame(Buffer data, Handler<AsyncResult<Void>> handler);
	ServerWebSocket writeBinaryMessage(Buffer data, Handler<AsyncResult<Void>> handler);
	ServerWebSocket writeTextMessage(String text, Handler<AsyncResult<Void>> handler);
		* �����ض�����Ϣ
		
	ServerWebSocket closeHandler(Handler<Void> handler);
		* ���ӹر��¼�

	ServerWebSocket frameHandler(Handler<WebSocketFrame> handler);

	String scheme();
	String host();
	String uri();
	String path();
	String query();
	void accept();

	void reject();
	void reject(int status);
		* �ܾ����ӣ���Ӧ״̬��

	void setHandshake(Future<Integer> future, Handler<AsyncResult<Integer>> handler);
	Future<Integer> setHandshake(Future<Integer> future);
		* �����첽���ִ���
		* �����ֶ�������WebSocket���ִ�������������ã�webSocketHandler����ģ��������󣬽��Զ�����WebSocket���֡�

	Future<Void> close();
	SSLSession sslSession();
	X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;
