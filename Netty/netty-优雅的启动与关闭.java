-------------------------
���������ŵ�����		 |
-------------------------
	EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();
	EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
	
	ServerBootstrap serverBootstrap = new ServerBootstrap();
	// ʡ��N������
	ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(1024)).sync();
	// �ڹرջص����洦��ѻ���Ϣ�Լ��ͷ��̳߳���Դ
	channelFuture.channel().closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
		@Override
		public void operationComplete(Future<? super Void> future) throws Exception {
			System.out.println("������ֹͣ...");
			bossEventLoopGroup.shutdownGracefully();
			workerEventLoopGroup.shutdownGracefully();
		}
	});
	
	// �̲߳�����,��������ִ��
	System.out.println("�����������ɹ�....");


-------------------------
���������ŵ��˳�		 |
-------------------------
	