----------------------
SockJSHandler
----------------------
	# Socketjs�������ӿڣ� interface SockJSHandler extends Handler<RoutingContext> 

	static SockJSHandler create(Vertx vertx)
	static SockJSHandler create(Vertx vertx, SockJSHandlerOptions options)
		* ����SocketjsHandler

	Router socketHandler(Handler<SockJSSocket> handler)
		* ��������

	default Router bridge(SockJSBridgeOptions bridgeOptions)
	Router bridge(AuthorizationProvider authorizationProvider, SockJSBridgeOptions bridgeOptions, Handler<BridgeEvent> bridgeEventHandler);
	default Router bridge(SockJSBridgeOptions bridgeOptions, Handler<BridgeEvent> bridgeEventHandler)


	void handle(RoutingContext routingContext);
		* ��ʱ����

