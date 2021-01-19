-----------------------------
ChannelHandler				 |
-----------------------------
	# �ṩ������Handler�¼�
	# ���
		ChannelHandler
			|-ChannelHandlerAdapter(����)
			|-ChannelInboundHandler(���ӿ�)
			|-ChannelOutboundHandler(д�ӿ�)
				|-ChannelDuplexHandler(��д��ʵ��)

	# Handler����������(�������¼�)
		handlerAdded	ChannelHandler ��ӵ� ChannelPipeline
		handlerRemoved	ChannelHandler �� ChannelPipeline �Ƴ�
		exceptionCaught	ChannelPipeline ִ���׳��쳣
	
	# һЩ���������
		SimpleUserEventChannelHandler
			* ������SimpleChannelInboundHandler
			* ��Handler���ڴ���ָ������(����), ���û��¼�handler
			public class StringEventHandler extends SimpleUserEventChannelHandler<String> {
				@Override
				 protected void eventReceived(ChannelHandlerContext ctx, String evt) throws Exception {
					 System.out.println(evt);
				 }
			 }

-----------------------------
ChannelInboundHandler		 |
-----------------------------
	# ��ȡHandler
	# ���
		|-ChannelInboundHandler
			|-ChannelInboundHandlerAdapter
				* ��ʵ���� ChannelInboundHandler �����з���
				* Ĭ�ϵ����þ��Ǵ�����Ϣ(�¼�)������Ϣת���� ChannelPipeline �е���һ�� ChannelHandler

				|-ByteToMessageDecoder
					|-ReplayingDecoder<S> 
					|-LineBasedFrameDecoder
					|-LengthFieldBasedFrameDecoder
					|-DelimiterBasedFrameDecoder
					|-FixedLengthFrameDecoder
					|-SslHandler
				|-MessageToMessageDecoder
					|-StringDecoder
				|-ChannelInitializer
				|-SimpleChannelInboundHandler<I>

	# �¼�(�ӿڷ���)
		channelRegistered	channel	ע�ᵽһ�� EventLoop,��״̬���Գ��ֶ��,�����ظ���ע��ȡ��ע��
		channelActive		channel	��Ϊ��Ծ״̬(���ӵ���Զ������),���ڿ��Խ��պͷ���������,��״ֻ̬�����һ��
		channelInactive		channel	���ڷǻ�Ծ״̬(�����Ѿ��ر�),û�����ӵ�Զ������,��״ֻ̬�����һ��
		channelUnregistered	channel	�Ѵ�����δע�ᵽһ�� EventLoop	(���ߴ�EventLoop���Ƴ�),��״̬���Գ��ֶ��,�����ظ���ע��ȡ��ע��

		channelReadComplete			channel ��ȡ���
		channelRead					channel ���Զ�ȡ
		userEventTriggered			channel �û��Զ����¼�
		channelWritabilityChanged	channel ��д״̬�ı�,����ʹ�� Channel.isWritable()���
		exceptionCaught				channel �쳣�¼�

	# SimpleChannelInboundHandler<T>
		* ������,��Ҫ��д���󷽷�: channelRead0(ChannelHandlerContext ctx, T msg)
		* �Զ�ǿ��ת������,���ҿ����Զ����ͷ�buf��Դ
			 ReferenceCountUtil.release(msg);
	
	# һ��ʹ�õĺͳ���
		ChannelInboundHandlerAdapter	�������¼�����״̬�ı�
		SimpleChannelInboundHandler		������Ϣ

-----------------------------
SimpleChannelInboundHandler	 |
-----------------------------
	# �̳���:ChannelInboundHandlerAdapter �ķ��ͳ�����
	# ���캯��
		SimpleChannelInboundHandler() 
		SimpleChannelInboundHandler(boolean autoRelease)
		SimpleChannelInboundHandler(Class<? extends I> inboundMessageType)
		
		* autoRelease �Ƿ��Զ��ͷ���Դ,Ĭ�� true
		* inboundMessageType �ô������ᴦ�����Ϣ����(�����Ƿ��ͻ���������)
			if (inboundMessageType.isInstance(msg)){
				// ����
			}
	
	# �ṩ�Ŀɸ�д�ķ���
		abstract void channelRead0(ChannelHandlerContext ctx, I msg)
			* Ψһ�ĳ�����,���븲д
	
		void channelRead(ChannelHandlerContext ctx, Object msg)
			* �����ȡ�¼�
			* Դ��
				@Override
				public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
					boolean release = true;
					try {
						if (acceptInboundMessage(msg)) {
							@SuppressWarnings("unchecked")
							I imsg = (I) msg;
							channelRead0(ctx, imsg);
						} else {
							release = false;
							ctx.fireChannelRead(msg);
						}
					} finally {
						if (autoRelease && release) {
							ReferenceCountUtil.release(msg);	// �Զ��ͷ���Դ
						}
					}
				}
		
		boolean acceptInboundMessage(Object msg)
			* �жϵ�ǰHandler�Ƿ���Դ������Ϣ����
			* ִ�� channelRead()��ʱ��,����ø÷���,�÷���Ĭ�ϵ����� TypeParameterMatcher ʵ���� match(); ����
			* ������� true,�ͽ���ǿ������ת��,���Ҵ����󷽷� channelRead0 
			* ������� false,�ͻᴥ����һ��Handler�� channelRead �¼�
			

	# TypeParameterMatcher
		* ����ƥ����
		* ��SimpleChannelInboundHandler�ڲ�ά����һ������(������)
		* �ܶ�����͵ı������(�ڲ���ά��)���ǿ����������ж��Ƿ�Ҫ���н���/�����
		* �ṩ�˾�̬�Ĺ�������
			static TypeParameterMatcher find(final Object object, final Class<?> parametrizedSuperclass, final String typeParamName);
			static TypeParameterMatcher get(final Class<?> parameterType)
		
		*  Ψһ�ĳ��󷽷�
			abstract boolean match(Object msg);
		
		

-----------------------------
ChannelOutboundHandler		 |
-----------------------------
	# д��Handler
	# ���
		|-ChannelOutboundHandler
			|-ChannelOutboundHandlerAdapter
				|-MessageToByteEncoder<I>
				|-MessageToMessageEncoder<I>
					|-LengthFieldPrepender
					|-StringEncoder
	
	# ChannelOutboundHandler ���󷽷�
		void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception;
		void connect( ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception;
		void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception;
		void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception;
		void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception;
		void read(ChannelHandlerContext ctx) throws Exception;
		void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception;
		void flush(ChannelHandlerContext ctx) throws Exception;

-----------------------------
ChannelDuplexHandler		 |
-----------------------------
	# ��дHandler
	# ���̳� ChannelInboundHandlerAdapter ʵ�� ChannelOutboundHandler

-----------------------------
ChannelPromise ����			 |
-----------------------------
	# ChannelPromise
		* ���̳���ChannelFuture
	
	# ����
		* ����� ChannelFuture,������� ChannelPromise ���� ���� �ɹ���ʧ��
		* �����κ�ʱ��������� Channel.write(...) һ���µ� ChannelPromise ���ᴴ������ͨ�� ChannelPipeline ����
		* ���д���������᷵�� ChannelFuture, ����ֻ������õ�һ�β�����ɵ�֪ͨ
		* Netty ����ʹ�� ChannelPromise ��Ϊ���ص� ChannelFuture ��֪ͨ,��ʵ���ڴ����ʱ����� ChannelPromise ����
		* ChannelPromise ��չ�� ChannelFuture
		* ��ǰ����,ChannelOutboundHandlerAdapter �ṩ��һ��ʵ���� ChannelOutboundHandler ���л���������ʵ�ֵĿ�ܡ�
		* ��Щ���¼�ת������һ�� ChannelOutboundHandler �ܵ�ͨ������ ChannelHandlerContext ��صĵ�Ч����,���Ը�����Ҫ�Լ�ʵ����Ҫ�ķ���


-----------------------------
�����Handler				 |
-----------------------------
	# ChannelHandler ʵ��������� @Sharable ע������Ա���ӵ����ChannelPipeline
		* Ҳ����˵���� ChannelHandler ʵ�������ж�� ChannelHandlerContext
		* ��˿��Ե��ò�ͬ ChannelHandlerContext ��ȡͬһ�� ChannelHandler 
		* �����Ӳ��� @Sharable ע��� ChannelHandler ʵ������� ChannelPipeline ����׳��쳣
		* ʹ�� @Sharable ע���� ChannelHandler �����ڲ�ͬ���̺߳Ͳ�ͬ��ͨ���ϰ�ȫʹ��
	
	# ʹ�� @Sharable ע�⹲��һ�� ChannelHandler ��һЩ�����л����кܺõ����õ�
		* ��ʹ��һ�� ChannelHandler ��ͳ����������������һЩȫ�����ݵȵ�

-----------------------------
�¼��ܽ�					 |
-----------------------------
	void handlerAdded(ChannelHandlerContext ctx) throws Exception;
	void handlerRemoved(ChannelHandlerContext ctx) throws Exception;
	void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception;

	void channelRegistered(ChannelHandlerContext ctx) throws Exception;
	void channelUnregistered(ChannelHandlerContext ctx) throws Exception;

	void channelActive(ChannelHandlerContext ctx) throws Exception;
	void channelInactive(ChannelHandlerContext ctx) throws Exception;

	void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception;
	void channelReadComplete(ChannelHandlerContext ctx) throws Exception;
	void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception;
	void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception;

-----------------------------
�쳣�Ĵ��������ʵ��		 |
-----------------------------
	# �쳣�Ĵ���
		* �쳣������,���ȴ�����ǰhandler exceptionCaught() ����
		* �����ǰ�ڵ�,������������һ���ڵ㴦��
		* ����� pipeline �����˳��(��Handler�����޹�)������һ�� handler �� exceptionCaught() ����

	# ��ѵ��쳣����ʵ��
		* �� pipeline �����,���һ���ռ��� ExceptionHandler 
		* �̳�:ChannelInboundHandlerAdapter/ChannelHandlerAdapter,��д exceptionCaught() ����������ȫ�ֵ��쳣
		* ���ݲ�ͬ���쳣������������


-----------------------------
Handlerͳ��					 |
-----------------------------
	ResolveAddressHandler 
	SslClientHelloHandler 