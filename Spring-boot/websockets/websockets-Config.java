----------------------------------------
websockets ����
----------------------------------------
	# ʵ��WebSocketConfigurer�����࣬��Ӷ˵㷽���᷵�� WebSocketHandlerRegistration ����
		public interface WebSocketHandlerRegistry {
			WebSocketHandlerRegistration addHandler(WebSocketHandler webSocketHandler, String... paths);
		}

	
	# WebSocketHandlerRegistration
		WebSocketHandlerRegistration addHandler(WebSocketHandler handler, String... paths);
			* ���һ�����߶��handler

		WebSocketHandlerRegistration setHandshakeHandler(HandshakeHandler handshakeHandler);
			* ������ִ�����
		
		WebSocketHandlerRegistration addInterceptors(HandshakeInterceptor... interceptors);
			* ���������
		
		WebSocketHandlerRegistration setAllowedOrigins(String... origins);
			* �����������

		SockJsServiceRegistration withSockJS();
			* ֧�� socketJs


		
		
----------------------------------------
websockets HandshakeInterceptor
----------------------------------------
	# HandshakeInterceptor ����������
	
	# ���󷽷�
		boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception;
			* �����ִ���֮ǰִ�У�beforeHandshake ���� false, ����ֹ����

		void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, @Nullable Exception exception);
	
	# ������֮ǰִ��, ��������һЩ����, У��ȵ�
	# ϵͳԤ����� Interceptor
		HttpSessionHandshakeInterceptor
			* ���ڰ�httpSession�󶨵�WebSocketSession��������
		
		OriginHandshakeInterceptor
			* �������ƿͻ�����Դ��������

----------------------------------------
websockets HandshakeHandler
----------------------------------------
	# HandshakeHandler ���ִ�����
		HandshakeHandler
			|-AbstractHandshakeHandler
			|-WebSocketHandlerDecorator

	# ���󷽷�
		boolean doHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws HandshakeFailureException;
			* ���������Ƿ�ɹ�


