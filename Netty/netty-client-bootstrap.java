------------------------
Bootstrap				|
-----------------------
	# �ͻ���һ�����ô���
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup);
			bootstrap.channel(NioSocketChannel.class);
			// ���ӵ�Զ�˵�ַ
			bootstrap.remoteAddress(new InetSocketAddress("127.0.0.1", 1024));
			bootstrap.option(ChannelOption.SO_REUSEADDR, true);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					
				}
			});
			// ִ������
			ChannelFuture channelFuture = bootstrap.connect().sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}

		* û��childOption��childHandler����
		* channelΪ:NioSocketChannel