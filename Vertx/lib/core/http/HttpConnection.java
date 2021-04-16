---------------------------
HttpConnection
---------------------------
	# Http���ӣ��ӿڣ� interface HttpConnection 

	default int getWindowSize()
	default HttpConnection setWindowSize(int windowSize) 

	default HttpConnection goAway(long errorCode)
	default HttpConnection goAway(long errorCode, int lastStreamId)

	HttpConnection goAway(long errorCode, int lastStreamId, Buffer debugData)
	HttpConnection goAwayHandler(@Nullable Handler<GoAway> handler)

	HttpConnection shutdownHandler(@Nullable  Handler<Void> handler)

	default void shutdown(Handler<AsyncResult<Void>> handler)
	default Future<Void> shutdown()

	void shutdown(long timeout, Handler<AsyncResult<Void>> handler)
	Future<Void> shutdown(long timeoutMs)

	HttpConnection closeHandler(Handler<Void> handler);
		* �����Ͽ��¼�


	Future<Void> close();
	default void close(Handler<AsyncResult<Void>> handler)
		* �Ͽ�����

	Http2Settings settings()

	Future<Void> updateSettings(Http2Settings settings);
	HttpConnection updateSettings(Http2Settings settings, Handler<AsyncResult<Void>> completionHandler);
		* ������������

	Http2Settings remoteSettings();
	HttpConnection remoteSettingsHandler(Handler<Http2Settings> handler);

	HttpConnection ping(Buffer data, Handler<AsyncResult<Buffer>> pongHandler);
	Future<Buffer> ping(Buffer data);
		* ����ping
		* �˹��ܽ������� HTTP/2 Э�顣


	HttpConnection pingHandler(@Nullable Handler<Buffer> handler);
		* ����ping

	HttpConnection exceptionHandler(Handler<Throwable> handler);
		* �����쳣

	SocketAddress remoteAddress();
	SocketAddress localAddress();

	boolean isSsl();
	SSLSession sslSession();

	X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;
	String indicatedServerName();


