-----------------------------
ssl							 |
-----------------------------
	#��һ��ʹ�� SslHandler ��������
		* ���ܵ���վ���ݱ�����,��������Ϊƽ������
		* ƽ�����ݴ��� SslHandler
		* SslHandler �������ݲ������ݳ�վ
	
	# �ڴ���������,SslHandler ����Ϊ ChannelPipeline �еĵ�һ�� ChannelHandler 
		* �⽫ȷ���������� ChannelHandler Ӧ�����ǵ��߼������ݺ���ܺ�ŷ���,�Ӷ�ȷ�����ǵı仯�ǰ�ȫ��
	
	# �漰���
		SslHandler

	#  ����˵�Handler����
		protected void initChannel(SocketChannel ch) throws Exception {
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
			SslHandler sslHandler = sslCtx.newHandler(ch.alloc());
			ch.pipeline().addLast(sslHandler); // ��sslHandler��ӵ���һ��
		}
	
	# �ͻ��˵�Handler����
		String host = "127.0.0.1";	// ��������ַ
		int port = 1024;			// �������˿�
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
			SslHandler sslHandler = sslContext.newHandler(ch.alloc(), host, port);
			ch.pipeline().addLast(sslHandler);	 // ��sslHandler��ӵ���һ��
		}
	
	# SslHandler ��һЩ���÷���
		void setHandshakeTimeout(long handshakeTimeout, TimeUnit unit)
			* �������ֳ�ʱʱ��,ChannelFuture ���õ�֪ͨ
		
		void setHandshakeTimeoutMillis(long handshakeTimeoutMillis)
			* �������ֳ�ʱʱ��,ChannelFuture ���õ�֪ͨ
		
		long getHandshakeTimeoutMillis()
			* ��ȡ���ֳ�ʱʱ��ֵ


		void setHandshakeTimeoutMillis(long handshakeTimeoutMillis)
			* ���ùر�֪ͨ��ʱʱ��,����ʱ, ChannelFuture ��ر�ʧ��

		Future<Channel> handshakeFuture()
			* ����������ֺ�� ChannelFuture(���ֽ��)

