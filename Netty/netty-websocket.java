---------------------------------
websocket						 |
---------------------------------
	# ���
		WebSocketFrame
			|-BinaryWebSocketFrame			
			|-CloseWebSocketFrame
			|-ContinuationWebSocketFrame
			|-PingWebSocketFrame
			|-PongWebSocketFrame
			|-TextWebSocketFrame
		WebSocketProtocolHandler(�Զ�����webSocket��ping pong����Ϣ)
			|-WebSocketServerProtocolHandler(�򻯿����ṩ��handler)
		WebSocketServerCompressionHandler(WebSocket��Ϣѹ��)
		WebSocketServerProtocolHandshakeHandler(WebSocketЭ�������ʵ��)
		WebSocketServerHandshaker(���ִ�����)
		WebSocketServerHandshakerFactory(�����������Ĺ�����)
		WebSocketDecoderConfig(������ص�����)
		WebSocketCloseStatus(WebSocket��״̬���װ)
		
			
	# WebSocketFrame
		* ws��Ϣ���͵ĳ�����,�ṩ��N��ʵ��,��ʾ��ͬ����Ϣ����
		* ������,������֪����ɶ��Ϣ��ʵ����
	
	# WebSocketServerCompressionHandler
		* �ṩ�˶�websocket��Ϣ��ѹ��
	
	# WebSocketDecoderConfig
		* websocket������ص�����
		* ʵ��Builderģʽ����
			WebSocketDecoderConfig webSocketDecoderConfig = WebSocketDecoderConfig.newBuilder()
				.allowExtensions(true)				 // �Ƿ�������չ
				.allowMaskMismatch(false)			// �Ƿ�Ҫ����δʵ�ֱ�׼�淶������֡
				.closeOnProtocolViolation(true)		// �Ƿ���Э�����(��ͻ)������¹ر�(Ĭ��true)
				.expectMaskedFrames(true)			// ����˱�������Ϊtrue(����ǿͻ���, ��������Ϊfalse)
				.maxFramePayloadLength(1024)		// ������Ϣ�����
				.build();
			
			private int maxFramePayloadLength = 65536;
			private boolean expectMaskedFrames = true;
			private boolean allowMaskMismatch;
			private boolean allowExtensions;
			private boolean closeOnProtocolViolation = true;

			
	# WebSocketCloseStatus
		* websocket״̬��ķ�װ
			private final int statusCode;			// ״̬��
			private final String reasonText;		// ԭ��
		    private String text;					// this.text = text = code() + " " + reasonText();

		* ���캯��
			public WebSocketCloseStatus(int statusCode, String reasonText)
		
		* ��̬����
			public static WebSocketCloseStatus valueOf(int code)
				* ����code��ȡ���еľ�̬ʵ��
				* ���������, �򴴽�:
					return new WebSocketCloseStatus(code, "Close status #" + code);
				
			public static boolean isValidStatusCode(int code)
				* �ж�ָ����״̬���Ƿ���һ�����Ϸ���״̬��


		* �ṩ��N��ĵľ�̬ʵ��
			public static final WebSocketCloseStatus NORMAL_CLOSURE = new WebSocketCloseStatus(1000, "Bye");
			...
		

-------------------------------------
WebSocketServerProtocolHandler		 |
-------------------------------------
	# ���ص�һ����װ,�����N��Ĺ���
	# ������:����,����֡(ping/pong/close),�ı���Ϣ,��������Ϣ....
	# ���캯��(�汾����, ���캯�����β�Ҳ����ȷ��, �����β��б��ʼ����ȷ)
		WebSocketServerProtocolHandler(String websocketPath)
		WebSocketServerProtocolHandler(String websocketPath, long handshakeTimeoutMillis)
		WebSocketServerProtocolHandler(String websocketPath, boolean checkStartsWith)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions) 
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions, int maxFrameSize)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions, int maxFrameSize, boolean allowMaskMismatch)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions, int maxFrameSize, boolean allowMaskMismatch, boolean checkStartsWith)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions, int maxFrameSize, boolean allowMaskMismatch, boolean checkStartsWith, boolean dropPongFrames)
		
		websocketPath
			* �ṩ�����uri

		subprotocols
			* ֧�ֵ���Э���б�

		allowExtensions
			* �Ƿ�������չ

		maxFrameSize
			* ��Ϣ֡����ֽ���

		allowMaskMismatch
			* �Ƿ�Ҫ����δʵ�ֱ�׼�淶������֡

		checkStartsWith
			* ���Ϊtrue, ��ʹ��startWith ��� uri, ����ʹ��quals
			* ���Ϊfalse, �����Ҫ��uri��websocketһ��һ��
			
		dropPongFrames
			* ����pong��Ϣ
		
		handshakeTimeoutMillis
			* websocket���ֳ�ʱ,
			* Ĭ��: private static final long DEFAULT_HANDSHAKE_TIMEOUT_MS = 10000L;
		
		decoderConfig
			* WebSocketDecoderConfig ��
			* WebSocket������ص�����
	
	# �����websocket�����ݱ���Ϊ WebSocketFrame ����,������һ��Handlerȥ����

		

	
---------------------------------
�����							 |
---------------------------------
import java.util.Locale;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class Server {

	static final String ENDPOINT = "/channel";

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup);
			serverBootstrap.channel(NioServerSocketChannel.class);
			serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					ChannelPipeline pipeline = socketChannel.pipeline();
					pipeline.addLast(new HttpServerCodec());
					pipeline.addLast(new ChunkedWriteHandler());
					pipeline.addLast(new HttpObjectAggregator(65536));
					pipeline.addLast(new ChannelInboundHandlerAdapter() {
						@Override
						public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
							if(msg instanceof FullHttpRequest) {
								FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
								String uri = fullHttpRequest.uri();
								if (!uri.equals(ENDPOINT)) {
									// ���ʵ�·������ websocket�Ķ˵��ַ����Ӧ404
									ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND))
										.addListener(ChannelFutureListener.CLOSE);
									return ;
								}
							}
							super.channelRead(ctx, msg);
						}
					});
					pipeline.addLast(new WebSocketServerCompressionHandler());
					pipeline.addLast(new WebSocketServerProtocolHandler(ENDPOINT, null, true));	// uri,��Э��,�Ƿ�֧����չ
					pipeline.addLast(new SimpleChannelInboundHandler<WebSocketFrame>() {
						@Override
						protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
							if (msg instanceof TextWebSocketFrame) { // �ı���Ϣ����
								String request = ((TextWebSocketFrame) msg).text();
								System.out.println("���յ���Ϣ:" + request);
								ctx.channel().writeAndFlush(new TextWebSocketFrame(request.toUpperCase(Locale.CHINA)));
							} else {	// �������͵���Ϣ
								String message = "��֧�ֵ���Ϣ����: " + msg.getClass().getName();
								throw new UnsupportedOperationException(message);
							}
						}
						// ���ֽ���¼�֪ͨ
						@Override
						public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
							if(evt instanceof HandshakeComplete) {
								HandshakeComplete handshakeComplete = (HandshakeComplete) evt;
								String uri = handshakeComplete.requestUri();
								HttpHeaders httpHeaders = handshakeComplete.requestHeaders();
								String selectedSubprotocol = handshakeComplete.selectedSubprotocol();
								LOGGER.debug("HandshakeComplete uri={},headers={},selectedSubprotocol={}",uri,httpHeaders,selectedSubprotocol);
							}
							super.userEventTriggered(ctx, evt);
						}
					});
				}
			});
			Channel channel = serverBootstrap.bind(1024).sync().channel();
			channel.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}

	
---------------------------------
������ǰ����uri�����ȵ�			 |
---------------------------------
	# ˼·
		* �� HttpObjectAggregator ֮�����һ��Handler,���ڻ�ȡ��������http������Ϣ:FullHttpRequest
		* ����ͨ��FullHttpRequest��ȡ��uri,header,method,query param����Ϣ
		* �Լ����Ծ�������Ӧ�쳣��Ϣ,����ִ����һ��handler,Ҳ����ws����

	# ����
		pipeline.addLast(new HttpObjectAggregator(65536));
		pipeline.addLast(new ChannelInboundHandlerAdapter() {
			@Override
			public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
				if(msg instanceof FullHttpRequest) {
					FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
					
					// ��ȡ�������uri,�������������·�����������
					String uri = fullHttpRequest.uri();

					fullHttpRequest.setUri(ENDPOINT); // һ��Ҫ����uri��websocket�Ķ˵�ƥ��
				}
				super.channelRead(ctx, msg);
			}
		});
		pipeline.addLast(new WebSocketServerCompressionHandler());
		pipeline.addLast(new WebSocketServerProtocolHandler(ENDPOINT, null, true));	// uri,��Э��,�Ƿ�֧����չ
