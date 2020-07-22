----------------------------
spring-websocket
----------------------------
	# �ĵ�
		https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#websocket
	
	# Maven
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-websocket -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-websocket</artifactId>
			<version>5.2.6.RELEASE</version>
		</dependency>

	# �������
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
		
		WebSocketMessage
			|-AbstractWebSocketMessage
				|-BinaryMessagee<ByteBuffer>
				|-PingMessage<ByteBuffer>
				|-PongMessage<ByteBuffer>
				|-TextMessage<String>

		ServletServerContainerFactoryBean
		


----------------------------
spring-websocket
----------------------------
	# Maven
		<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-websocket -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-websocket</artifactId>
			<version>2.3.0.RELEASE</version>
		</dependency>
	
	# ����ע��
		@EnableWebSocket
	
	# ���ýӿ���
		import org.springframework.web.socket.config.annotation.EnableWebSocket;
		import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
		import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

		@Configuration
		@EnableWebSocket
		public class WebSocketConfig implements WebSocketConfigurer {

			// ���handler �Լ����ü���·��
			@Override
			public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
				WebSocketHandlerRegistration webSocketHandlerRegistration = registry.addHandler(myHandler(), "/myHandler");

			}

			// ע��Handler
			@Bean
			public WebSocketHandler myHandler() {
				return new MyHandler();
			}

		}