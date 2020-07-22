------------------------------------
WebSocketHandler
------------------------------------
	# �ӿڷ���
		void afterConnectionEstablished(WebSocketSession session) throws Exception;
			* ���Ӵ�ʱִ��

		void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception;
			* ��Ϣ����

		void handleTransportError(WebSocketSession session, Throwable exception) throws Exception;
			* �����쳣

		void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception;
			* ���ӹرպ�ִ��

		boolean supportsPartialMessages();
			* �Ƿ�֧�ַ�Ƭ��Ϣ
			* ������� true, �ᵼ�� WebSocketSession �� ServletServerContainerFactoryBean �ж�����Ϣ��С������ʧЧ
				void setTextMessageSizeLimit(int messageSizeLimit);
				void setBinaryMessageSizeLimit(int messageSizeLimit);

				void setMaxTextMessageBufferSize(8192);
				void setMaxBinaryMessageBufferSize(8192);
	
	# ���Ĺ�ϵ
		WebSocketHandler
			|-AbstractWebSocketHandler
				|-BinaryWebSocketHandler
				|-TextWebSocketHandler
					|-SockJsWebSocketHandler
			|-PerConnectionWebSocketHandler
			|-SubProtocolWebSocketHandler
			|-WebSocketHandlerDecorator
				|-ExceptionWebSocketHandlerDecorator
				|-LoggingWebSocketHandlerDecorator

------------------------------------
WebSocketHandlerDecorator
------------------------------------
	# ����һ�� wrapper �࣬ͨ�����캯������һ�������ɻ���� WebSocketHandler
		public WebSocketHandlerDecorator(WebSocketHandler delegate)
	
	
	# ʵ������
		public WebSocketHandler getDelegate()
			* ��ȡ�������ɻ����handler
			* Դ��ܼ�

		WebSocketHandler getLastHandler()
			* ��ȡ�����Ĵ������
			* ����˿, �޳���������� WebSocketHandlerDecorator 
			* Դ��ܼ�


	# ��̬����
		public static WebSocketHandler unwrap(WebSocketHandler handler)
			* ���Զ�һ�� WebSocketHandler ���а�˿�������������ɻ����handler
			* Դ��ܼ�

	
	
	# ϵͳ�ṩ��ʵ��
		ExceptionWebSocketHandlerDecorator
			* ���е��¼��������� try catch 
			* ���� afterConnectionClosed �������⡣�����ķ����������� catch �г��Թر�session����Ӧ��CloseStatus.SERVER_ERROR

		LoggingWebSocketHandlerDecorator
			* ��¼��־�������¼���־
		
