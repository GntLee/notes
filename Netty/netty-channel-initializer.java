----------------------------
ChannelInitializer			|
----------------------------
	# ChannelInitializer<C extends Channel> extends ChannelInboundHandlerAdapter
	# ������,����һ������� ChannelInboundHandler
	# �ṩ�ĳ��󷽷�

		protected abstract void initChannel(C ch) throws Exception;
	
		* �����ӽ�����ʱ��,ͨ������������󷽷������ø���handler(i/o)
		* ������˳�ʼ����(initChannel ����),������Լ��� pipeline �����Ƴ�

	# �������Ϊ:@Sharable,���Ա���� ServerBoostrap ʹ��
		* ����,��������ʵ��,����Ҫע���̰߳�ȫ������
	