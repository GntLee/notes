----------------------------
ChannelPipeline				|
---------------------------
	# �̳�:ChannelInboundInvoker ChannelOutboundInvoker
	# һ�� ChannelPipeline ���������������һ�� Channel ��ChannelHandler
		* �����޸� ChannelPipeline ͨ����̬��Ӻ�ɾ�� ChannelHandler
		* ChannelPipeline ���ŷḻ��API���ö�������Ӧ��վ�ͳ�վ�¼�
	
	# �ڲ�ά����һ��˫������
		* InBound�¼��Ĵ���: --->
		* OutBound�¼��Ĵ���: <---
		* Exception�¼��Ĵ���: --->

	# ��Ҳ���Ը��ͻ�����Ӧ����
		ChannelPipeline pipeline = ctx.pipeline();
		pipeline.write(Unpooled.copiedBuffer("netty in action", CharsetUtil.UTF_8));

		* ��api������Ϣ�� ChannelPipeline ��β��(�ұ�)��ʼ,Ҳ�Ǵ�ͷ��ʼ�����������ִ����
	
	# ��Ҳ���������Ĵ����¼�
		ChannelPipeline pipeline = ctx.pipeline();
		pipeline.fireXxxxx();

		* ��ͷ��(���)��ʼִ�д��� ChannelInboundHandler ���¼�
		* ���̳��˽ӿ�:
			ChannelInboundInvoker			�����˴�����վ�¼���fireXxxx ����
			ChannelOutboundInvoker			�����˴�����վ�¼���fireXxxx ����
	
	
	
	# �������handler��api
		ChannelPipeline addFirst(String name, ChannelHandler handler);
			* ���handler������,������������

		ChannelPipeline addLast(ChannelHandler... handlers);
			* ���handler������
			* ����Ĭ��: ����#���(��0��ʼ)
				ServerMessageHandler#0
		
		ChannelPipeline addLast(EventExecutorGroup group, ChannelHandler... handlers);
			* ���handler������,��������ִ����Щhandler�¼��������̳߳�
			
