--------------------------------
ChannelFutureListener			|
--------------------------------
	# interface ChannelFutureListener extends GenericFutureListener<ChannelFuture>
	# �ص��¼�������
	# Ԥ������N����¼��ص��Ŀ��ʵ��
		CLOSE
			* ��ɺ�ر�����
		CLOSE_ON_FAILURE
			* ����׳����쳣,�ر�����
		FIRE_EXCEPTION_ON_FAILURE

--------------------------------
����							|
--------------------------------
	public void operationComplete(ChannelFuture future) 
		* ��д��ɲ������¼�