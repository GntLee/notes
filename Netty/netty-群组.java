------------------------------
ChannelGroup				  |
------------------------------
	# ���
		ChannelGroup
			|-DefaultChannelGroup
		ChannelGroupException
		ChannelGroupFuture
		ChannelGroupFutureListener
		ChannelMatcher
		ChannelMatchers
			ALL_MATCHER

	
	# ChannelGroup
		* ��һ���ӿ�,ʵ���� Set �ӿ�
		* ��ʾһ�� Channel
	
	# ChannelGroupFuture 
		* ��ʾһ��ִ�н��,�ý��������:д��,����,�رյȵȶ���Ⱥ���һ������
	
	# ChannelGroupFutureListener
		* �¼������ص��Ľӿ�,�̳���GenericFutureListener,�ռ̳ж���,û�������߸�д������κγ��󷽷�
		* �����޶��˻ص�����ķ���:GenericFutureListener<ChannelGroupFuture>
			channelGroupFuture.addListener(new ChannelGroupFutureListener() {
				@Override
				public void operationComplete(ChannelGroupFuture future) throws Exception {
					// TODO
				}
			});

	# ChannelMatcher
		* �ӿ�,����Channel������,������һ��Ⱥ����ƥ���ָ����Channel
		* ֻ��һ�����󷽷�
			boolean matches(Channel channel);
	
	# ChannelMatchers
		* �ṩ��һЩ����/���� ChannelMatcher ʵ���ľ�̬����
			ChannelMatcher all()
				* ƥ�����е�Channel�� ChannelMatcher
			ChannelMatcher isNot(Channel channel)
			ChannelMatcher is(Channel channel)
			ChannelMatcher isInstanceOf(Class<? extends Channel> clazz)
			ChannelMatcher isNotInstanceOf(Class<? extends Channel> clazz)
			ChannelMatcher isServerChannel()
			ChannelMatcher isNonServerChannel()
			ChannelMatcher invert(ChannelMatcher matcher)
				* ��ת������matcher
			ChannelMatcher compose(ChannelMatcher... matchers)

------------------------------
DefaultChannelGroup			  |
------------------------------
	# ChannelGroup �ӿڵ�Ψһʵ��
	# ���캯��
		DefaultChannelGroup(EventExecutor executor)
		DefaultChannelGroup(EventExecutor executor, boolean stayClosed)
		DefaultChannelGroup(String name, EventExecutor executor)
		DefaultChannelGroup(String name, EventExecutor executor, boolean stayClosed)

		executor
			* ִ����
		name
			* Ⱥ�������
		stayClosed
			* �����ֵΪ true, ��ô�ڵ�ǰȺ�鱻�رյ�ʱ��, �¼���� channel �ᱻִ�� close
				if (stayClosed && closed) {
					channel.close();
				}
				return added;
					

	# ����ʹ�� GlobalEventExecutor.INSTANCE ������
		ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	# ʵ������(����ʵ����Set�ӿ� Set<Channel>)
		ChannelGroupFuture close();
		ChannelGroupFuture close(ChannelMatcher matcher);
			* �ر�Ⱥ��,���ٽ����µ�channel
			* ����Ͽ�Ⱥ���е���������

		ChannelGroupFuture disconnect();
		ChannelGroupFuture disconnect(ChannelMatcher matcher);
			* �Ͽ�Ⱥ��������channle������

		Channel find(ChannelId id);

		ChannelGroup flush();
		ChannelGroup flush(ChannelMatcher matcher);

		String name();

		ChannelGroupFuture newCloseFuture();
		ChannelGroupFuture newCloseFuture(ChannelMatcher matcher);
			* ����һ���µĹر�֪ͨ�ص�
			* ���Group�����κ�channel(���߷���������channle)������close()��ʱ��,�����ûص�

		ChannelGroupFuture write(Object message);
		ChannelGroupFuture write(Object message, ChannelMatcher matcher);
		ChannelGroupFuture write(Object message, ChannelMatcher matcher, boolean voidPromise);

		ChannelGroupFuture writeAndFlush(Object message);
		ChannelGroupFuture writeAndFlush(Object message, ChannelMatcher matcher);
		ChannelGroupFuture writeAndFlush(Object message, ChannelMatcher matcher, boolean voidPromise);
	
	# Channel �ر�,���Զ��Ĵ� Group ���Ƴ�
		@Override
		public boolean add(Channel channel) {
			...
			boolean added = map.putIfAbsent(channel.id(), channel) == null;
			if (added) { // �����ӳɹ�,������channel��close�������߼�����Ϊ���Լ���group���Ƴ�
				channel.closeFuture().addListener(remover);
			}
			...
		}
	
	# һ�� Channel �������ڶ�� Group
	# ���ServerChannel��channel������ͬһ����ChannelGroup��,��Ը���ִ�е��κ������I / O����ServerChannel������ִ��
		* ��һ���Թرշ�����ʱ,�˹���ǳ�����

		ChannelGroup allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

		 public static void main(String[] args) throws Exception {
			 ServerBootstrap b = new ServerBootstrap(..);

			 // ���Գ��������....
			 b.childHandler(new MyHandler());

			// ����������
			 b.getPipeline().addLast("handler", new MyHandler());
			 Channel serverChannel = b.bind(..).sync();
			 allChannels.add(serverChannel);

			 // �ȴ��ر�����

			 //���ȹر� serverChannel Ȼ���ٹر������� channel
			 allChannels.close().awaitUninterruptibly();
		 }

		 public class MyHandler extends ChannelInboundHandlerAdapter {
			  @Override
			 public void channelActive(ChannelHandlerContext ctx) {
				 // closed on shutdown.
				 allChannels.add(ctx.channel());
				 super.channelActive(ctx);
			 }
		 }