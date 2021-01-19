----------------------------
Spring-boot websocket		|
----------------------------
	# ����WebSocket���Զ����ð�
		org.springframework.boot.autoconfigure.websocket
	
	# �����war��ʽ���е�websocket,��ô endpoint ��Ӧ�ý�����������,������ ServerEndpointExporter

----------------------------
Spring-boot websocket��1	|
----------------------------
	# ��������
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
        </dependency>
	
	# ע��Bean
		* ServerEndpointExporter
		@Configuration  
		public class WebSocketConfiguration {  
			@Bean  
			public ServerEndpointExporter serverEndpointExporter (){  
				return new ServerEndpointExporter();  
			}  
		}  
	
	# ʹ��ע�⿪��WebSocket�˵�
		 
		@Component 
		@ServerEndpoint("/websocket")		//������б�߿�ͷ,��Ȼ��������
		public WebSocketEndpoint{
			@OnMessage
			@OnOpen
			@OnClose
			@OnError
		}

	# ע��
		* @OnError Ҫ��Ӳ���:Throwable ,��Ȼ�����쳣
	
	# Demo
		@Component
		@ServerEndpoint(value = "/channel/test")
		public class TestEndpoint {

			private static final Logger LOGGER = LoggerFactory.getLogger(TestEndpoint.class);

			private Session session;

			@OnMessage(maxMessageSize = 10)
			public void onMessage(byte[] message){
				//skip
			}

			@OnOpen
			public void onOpen(Session session, EndpointConfig endpointConfig){
				LOGGER.info("�µ�����,id={}",session.getId());
				session.setMaxIdleTimeout(0);
				this.session = session;
			}

			@OnClose
			public void onClose(CloseReason closeReason){
				LOGGER.info("���ӶϿ�,id={} reason={}",this.session.getId(),closeReason);
			}

			@OnError
			public void onError(Throwable throwable) throws IOException {
				LOGGER.info("�����쳣,id={},throwable={}",this.session.getId(),throwable);
				this.session.close();
				throwable.printStackTrace();
			}

		}
	
	# �����ֶ���ע��˵�, ��ʹ�� Spring ɨ��
		@Configuration
		public class WebSocketConfiguration {
			
			@Bean  
			public ServerEndpointExporter serverEndpointExporter (){  
				ServerEndpointExporter serverEndpointExporter =  new ServerEndpointExporter();
				// ��Ӷ����ʶ�� @ServerEndpoint ע��Ķ˵���
				serverEndpointExporter.setAnnotatedEndpointClasses(TestChannel.class);
				return serverEndpointExporter;
			}  
		}

		* ����Ҫ�� TestChannel �������� @Component