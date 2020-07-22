-----------------
Container
-----------------
	# ͨ�� ContainerProvider SPI��ȡ��Container
		ContainerProvider
			public static WebSocketContainer getWebSocketContainer()
				* ��̬��������SPI����, ��ȡ�� Container

			protected abstract WebSocketContainer getContainer();
	
	# WebSocketContainer
		long getDefaultAsyncSendTimeout();
		void setAsyncSendTimeout(long timeoutmillis);

		Session connectToServer(Object annotatedEndpointInstance, URI path) throws DeploymentException, IOException;             
		Session connectToServer(Class<?> annotatedEndpointClass, URI path) throws DeploymentException, IOException;
		Session connectToServer(Endpoint endpointInstance, ClientEndpointConfig cec, URI path) throws DeploymentException, IOException;
		Session connectToServer(Class<? extends Endpoint> endpointClass, ClientEndpointConfig cec, URI path) throws DeploymentException, IOException;
			* ���ӵ�ָ���ķ�����

		void setDefaultMaxSessionIdleTimeout(long timeout);
		long getDefaultMaxSessionIdleTimeout();
		
		int getDefaultMaxBinaryMessageBufferSize();
		void setDefaultMaxBinaryMessageBufferSize(int max);

		int getDefaultMaxTextMessageBufferSize();
		void setDefaultMaxTextMessageBufferSize(int max);

		Set<Extension> getInstalledExtensions();
	

	# ServerContainer extends WebSocketContainer
		* ר�Ÿ����������ṩ��Container

		public void addEndpoint(Class<?> endpointClass) throws DeploymentException;
		public void addEndpoint(ServerEndpointConfig serverConfig) throws DeploymentException;
	


	
