--------------------------------
ChannelInboundHandlerAdapter	|
--------------------------------
	# class ChannelInboundHandlerAdapter extends ChannelHandlerAdapter implements ChannelInboundHandler
	# �����ʵ�ֵĶ�ȡ�¼�������

--------------------------------
����							|
--------------------------------
	public void channelActive(ChannelHandlerContext ctx)
		* ���ӱ���������׼������ͨ��ʱ������
	
	public void channelRead(ChannelHandlerContext ctx, Object msg)
		* ��ȡ�¼�

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
		* �쳣ʱ����
	
	public void handlerAdded(ChannelHandlerContext ctx) 
		* handler�����ʱ����
	
	public void handlerRemoved(ChannelHandlerContext ctx) 
		* handler���Ƴ�ʱ����
	
	void channelInactive(ChannelHandlerContext ctx) 
		* �ǻ�Ծ״̬ʱ����

	void channelReadComplete(ChannelHandlerContext ctx)
		* �ڶ�ȡ��ɺ����
	
	void handlerAdded(ChannelHandlerContext ctx)
		* ����ӵ� ChannelPipeline ����
	
	void handlerRemoved(ChannelHandlerContext ctx)
		* �� ChannelPipeline �Ƴ�ʱ����
	
	void userEventTriggered(ChannelHandlerContext ctx, Object evt)
		* ���������û��Զ������ʱ,����
	
	void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception;
		* ��channel��Ϊ��д״̬��ʱ�򴥷�
		* ����ʹ�� ctx.channel().isWritable(); ���ж�