----------------------------------------
websockets socketJS
----------------------------------------
	# SockJsServiceRegistration 

	# ʵ��WebSocketConfigurer�����࣬��Ӷ˵㷽���᷵�� WebSocketHandlerRegistration ����
		public interface WebSocketHandlerRegistry {
			WebSocketHandlerRegistration addHandler(WebSocketHandler webSocketHandler, String... paths);
		}

	
	# WebSocketHandlerRegistration
		SockJsServiceRegistration withSockJS();
			* ֧�� SocketJS
	
	# ����
	# ���ӶϿ�
	# CORS����


----------------------------------------
websockets socketJS Clientt
----------------------------------------
