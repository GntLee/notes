-------------------------
ServerBootstrap			 |
-------------------------
	# ServerBootstrap extends AbstractBootstrap<ServerBootstrap, ServerChannel>
	# ���캯��
		public ServerBootstrap()


-------------------------
����					 |
-------------------------
	ServerBootstrap handler(ChannelHandler handler)
		* ��ӷ���˵�handler

	ServerBootstrap childHandler(ChannelHandler childHandler)
		* ����һ�����߶���ͻ��˴�����
		* һ��������� ChannelInitializer ʵ��,���һ��handler������
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override		// Ψһ�ĳ��󷽷�
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeServerHandler());	// �ڴ����һ�����߶��Handler����
				}
			});

	<T> ServerBootstrap childOption(ChannelOption<T> childOption, T value)
		* ���ÿͻ������ӵ�����

	ServerBootstrap group(EventLoopGroup parentGroup, EventLoopGroup childGroup)
		* parentGroup���������¼�
		* childGroup ����IO�¼�
	
	ChannelFuture bind()
	ChannelFuture bind(int inetPort)
	ChannelFuture bind(String inetHost, int inetPort)
	ChannelFuture bind(InetAddress inetHost, int inetPort)
	ChannelFuture bind(SocketAddress localAddress)
		* �󶨲���		

	B channel(Class<? extends C> channelClass)
		* ����io����

	<T> B option(ChannelOption<T> option, T value)
		* ���÷������˵�����

