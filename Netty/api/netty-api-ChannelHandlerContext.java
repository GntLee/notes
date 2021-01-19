--------------------------------
ChannelHandlerContext			|
--------------------------------
	# interface ChannelHandlerContext extends AttributeMap, ChannelInboundInvoker, ChannelOutboundInvoker
	# ��ʾ�ͻ��˵�����

--------------------------------
�ӿڷ���						|
--------------------------------

    ChannelFuture write(Object msg);
		* д�����ݵ�ͨ��,���ǻᱻ���浽������

    ChannelFuture write(Object msg, ChannelPromise promise);

    ChannelFuture writeAndFlush(Object msg);
		* д�벢��ˢ��������
	
    ChannelFuture writeAndFlush(Object msg, ChannelPromise promise);
		* ���� futrue

	ChannelOutboundInvoker flush();
		* �ѻ������е�����ˢ��
	
	ByteBufAllocator alloc();
		* �õ�һ����ǰ��ByteBufAllocator,�Ӷ�����һ��Buffer
			ByteBuf intBuf = ctx.alloc().buffer(4);

	ChannelHandlerContext fireChannelRead(Object msg)
		* ������һ�� ChannelInboundHandler �� channelRead() ����,���Ҹ���msg����

	ChannelHandlerContext fireUserEventTriggered(Object evt);
		* ���������û��Զ�����¼�
	
	String name();
		* ��ȡ���ǵ�ǰ handler ��name(��ӵ� pipeline ʱ���õ�name����)

    ChannelHandler handler();
		* ���صľ��ǵ�ǰ��handlerʵ������

	ChannelProgressivePromise newProgressivePromise();
	ChannelFuture newFailedFuture(Throwable cause);
	ChannelFuture newSucceededFuture();
	ChannelPromise newPromise();
	ChannelPromise voidPromise();