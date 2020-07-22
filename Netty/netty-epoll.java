------------------------
epoll					|
------------------------
	# ֻ����Linuxϵͳ�����еĸ�����ioģʽ
	# �ο�
		https://github.com/netty/netty/wiki/Native-transports
	
	# ��Ҫ�滻�����
		NioEventLoopGroup		�� EpollEventLoopGroup
		NioEventLoop			�� EpollEventLoop

		NioServerSocketChannel	�� EpollServerSocketChannel
		NioSocketChannel		�� EpollSocketChannel
	
	# ��Ҫ������
		<dependency>
			<groupId>io.netty</groupId>
			<artifactId>netty-transport-native-epoll</artifactId>
			<version>4.1.34.Final</version>
		</dependency>
		<build>
			<extensions>
				<extension>
					<groupId>kr.motd.maven</groupId>
					<artifactId>os-maven-plugin</artifactId>
					<version>1.6.2</version>
				</extension>
			</extensions>
		</build>
	
	# ����ͨ����̬������:Epoll �ж��Ƿ�֧��epoll
		boolean isAvailable();	
			* �ж�Epoll�Ƿ����
		
		void ensureAvailability();
		Throwable unavailabilityCause();