-----------------------------
Channel ���ܽ�				 |
-----------------------------
	# ����������ChannelInboundHandler
	# ��Ӧ������ChannelOutboundHandler

-----------------------------
�¼�						 |
-----------------------------
	# �¼�����
		* ChannelInboundHandler ���Ը�дN����¼�����
		* ChannelOutboundHandler ֻ�л����� handler �¼�,��Ҳ�ǿ�ʵ��
		* ChannelHandlerContext ���������ĵ��� fireXxxxx(),������һ��(�ұ�) ChannelInboundHandler ���¼�
		* ChannelPipeline Ҳ���������ĵ��� fireXxxxx(),�����¼�,�������Ǵӵ�һ�� ChannelInboundHandler ��ʼ����
		
	# ʹ�� ChannelPipeline ִ����Ӧ���ߴ����¼�,���Ǵ�ͷ��ʼ
		* ִ������ write(),���ChannelPipeline ���ұ߿�ʼ������Ϣ,Ҳ�Ǵ�ͷ��ʼ�����������ִ����
		* ִ������ fireXxxxx(),�������ȴ�����ߵ� ChannelInboundHandler �¼�

-----------------------------
�쳣						 |
-----------------------------

-----------------------------
������Ӧ					 |
-----------------------------
		
	# ������Ӧ���ݵķ���������
		* ����ԭʼ Channel ��write() api,Ҳ���ChannelPipeline �����ұ߿�ʼ������Ϣ,Ҳ�Ǵ�ͷ��ʼ�����������ִ����
		* ���� ChannelHandlerContext �� write() ����,�����һ��(��������һ��) ChannelOutboundHandler ��ʼִ��
		* ChannelPipeline Ҳ����ʹ�� write() api,����һ�����һ��