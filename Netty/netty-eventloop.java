------------------------
EventLoop				|
------------------------
	# ���
		EventLoopGroup 
			* �̳нӿ�:ScheduledExecutorService, Iterable
			|-MultithreadEventLoopGroup
				|-EpollEventLoopGroup
				|-NioEventLoopGroup
			
	
	# EpollEventLoopGroup
		* ��jni������epoll()�ͷ�����IO
		* ֻ����Linuxϵͳ��ʹ��,��NIO�������
		* ���ʹ��epoll(),��ôServerBootstrap��channelҪʹ��:EpollServerSocketChannel

			NioEventLoopGroup		�� EpollEventLoopGroup
			NioEventLoop			�� EpollEventLoop

			NioServerSocketChannel	�� EpollServerSocketChannel
			NioSocketChannel		�� EpollSocketChannel
				
	# �ܽ�
		* һ��EventLoopGroup���л����һ�����߶��EventLoop
		* һ��EventLoop�������������������ж�ֻ����Ψһһ��Thread���а�
		* ������EventLoop������ĸ���I/O�¼������������������Ǹ�Thread�Ͻ��д���
		* һ��Channel��������������������ֻ�ᱻע��һ��EventLoop��
		* һ��EventLoop�����й��̵���,�ᱻ�����һ�����߶��Cahnnel
		
		* ��Netty��,Channel��ʵ��һ�����̰߳�ȫ��,���ڴ�,���Դ洢һ��Channel������,��������Ҫ��Զ�̶˵㷢������ʱ,ͨ�������õ���Cahnnel��Ӧ�ķ���
		* ���㵱ʱ�ڶ���̶߳���ͬʱ������ʱ��,Ҳ��������̰߳�ȫ����,������Ϣһ���ᰴ��˳���ͳ�ȥ
	
		* ��ʵ�ʿ�����,��Ҫ��ִ��ʱ�䳤��������뵽EventLoop��ִ�ж�����,��Ϊ����һֱ�������߳�����Ӧ������Channel�ϵ�����ִ������
		* �����Ҫ�����������û����Ǻ�ʱ�Ĳ���,�ǿ���ר��׼��һ��EventExecutor(ҵ���̳߳�)
			
			// ʵ�����̳߳�
			ExecutorService executorService = Executors.newCachedThreadPool();

			@Override
			protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
				// ͨ���Լ�������̳߳��첽��ȥִ������
				executorService.execute(() -> {
					
				});
			}
		
		* ���� Netty �ṩ���� ChannelPipeline ,��� ChannelHandlerʱ���õ�addLst()����������EventExecutor

			ChannelPipeline addLast(EventExecutorGroup group, String name, ChannelHandler handler);
			ChannelPipeline addLast(EventExecutorGroup group, ChannelHandler... handlers);

			* ͨ�����ַ�ʽ,������ָ��Handler�лص�����ִ��ʹ�õ��̳߳�(EventExecutorGroup)
			* EventExecutorGroup ����Ҳ̫TM���˰�?


