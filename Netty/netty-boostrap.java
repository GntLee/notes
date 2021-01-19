----------------------------
Netty����˵�����			|
----------------------------
	# ����˵�һ�����ô���
		// �������ӵ��̳߳�
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// ����IO�¼����̳߳�
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// ��������˶���
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			// �����̳߳�
			serverBootstrap.group(bossGroup, workerGroup);
			// ����ioģʽ
			serverBootstrap.channel(NioServerSocketChannel.class);
			// �����������Ͷ˿�
			serverBootstrap.localAddress(new InetSocketAddress("0.0.0.0", 1024));
			// ������־handler
			serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
			// ���ÿͻ���handlder
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				// ��ʼ����Ϣ����
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeServerHandler());
				}
			});
			// ���÷��������
			serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
			serverBootstrap.option(ChannelOption.SO_REUSEADDR, true);

			// ���ÿͻ�������
			serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

			// �󶨵����õĶ˿ں�����,����ͬ��(����)����������
			ChannelFuture channelFuture = serverBootstrap.bind().sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}

----------------------------
ChannelInitializer			|
----------------------------
	# ChannelInitializer ����Ҳ��һ��ChannelHandler,������������� handlers ֮����Զ��� ChannelPipeline ��ɾ���Լ�
