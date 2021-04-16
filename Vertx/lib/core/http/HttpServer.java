----------------------
HttpServer
----------------------
	# http�������ӿڣ�  interface HttpServer extends Measured


----------------------
this
----------------------
	ReadStream<HttpServerRequest> requestStream();

	HttpServer requestHandler(Handler<HttpServerRequest> handler);
	Handler<HttpServerRequest> requestHandler();
		* ��ȡ/���ô�����
		* ��Header��ȡ��Ϻ�ͻ�����������

	HttpServer connectionHandler(Handler<HttpConnection> handler);	
		* �����ͻ��������¼�

	HttpServer exceptionHandler(Handler<Throwable> handler);
		* �쳣������
		* ���� ���Ӵ��ݸ� requestHandler ֮ǰ�������쳣�� �����Ǵ��ݸ�  webSocketHandler ֮ǰ�������쳣
		* ��TLS�����ڼ䷢�����쳣

	ReadStream<ServerWebSocket> webSocketStream();

	HttpServer webSocketHandler(Handler<ServerWebSocket> handler);
	Handler<ServerWebSocket> webSocketHandler();
		* Websocket������
		
	Future<HttpServer> listen();
	Future<HttpServer> listen(int port, String host);
	HttpServer listen(int port, String host, Handler<AsyncResult<HttpServer>> listenHandler);
	HttpServer listen(SocketAddress address, Handler<AsyncResult<HttpServer>> listenHandler);
	Future<HttpServer> listen(SocketAddress address);
	Future<HttpServer> listen(int port);
	HttpServer listen(int port, Handler<AsyncResult<HttpServer>> listenHandler);
	HttpServer listen(Handler<AsyncResult<HttpServer>> listenHandler);
	Future<Void> close();
	void close(Handler<AsyncResult<Void>> completionHandler);
	int actualPort();