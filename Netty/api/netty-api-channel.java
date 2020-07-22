-----------------------------
Channel						 |
-----------------------------
	# ʵ�ֵĽӿ�
		Channel extends AttributeMap, ChannelOutboundInvoker, Comparable<Channel

	ChannelId id();
		* ͨ��id

    EventLoop eventLoop();
		* eventLoop

    Channel parent();
    ChannelConfig config();
		* ����
    
    boolean isOpen();
		* �Ƿ���

    boolean isRegistered();
		* �Ƿ��Ѿ�ע��

    boolean isActive();
		* �Ƿ��Ѿ�ע��
 
    ChannelMetadata metadata();
		* Ԫ����
  
    SocketAddress localAddress();
		* ����addr

    SocketAddress remoteAddress();
		* Զ��addr

    ChannelFuture closeFuture();
		* ��ȡ��closeFuture
  
    boolean isWritable();
		* �Ƿ��д
  
    long bytesBeforeUnwritable();
    long bytesBeforeWritable();
    
    Unsafe unsafe();
    
    ChannelPipeline pipeline();

    ByteBufAllocator alloc();

    @Override
    Channel read();

    @Override
    Channel flush();

-----------------------------
Unsafe						 |
-----------------------------
	SocketAddress localAddress();

	SocketAddress remoteAddress();

	void register(EventLoop eventLoop, ChannelPromise promise);

	void bind(SocketAddress localAddress, ChannelPromise promise);

	void connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise);

	void disconnect(ChannelPromise promise);

	void close(ChannelPromise promise);

	void closeForcibly();

	void deregister(ChannelPromise promise);
	void beginRead();

	void write(Object msg, ChannelPromise promise);

	void flush();

	ChannelPromise voidPromise();

	ChannelOutboundBuffer outboundBuffer();