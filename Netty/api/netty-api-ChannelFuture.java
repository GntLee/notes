--------------------------------
ChannelFuture					|
--------------------------------
	# interface ChannelFuture extends Future<Void>
	# �¼��Ļص�


--------------------------------
����							|
--------------------------------
	Channel channel();
		* ���ع�����channel

    @Override
    ChannelFuture addListener(GenericFutureListener<? extends Future<? super Void>> listener);
		* ���һ������

    @Override
    ChannelFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... listeners);
		* ��Ӷ������

    @Override
    ChannelFuture removeListener(GenericFutureListener<? extends Future<? super Void>> listener);
		* �Ƴ�һ������

    @Override
    ChannelFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... listeners);
		* �Ƴ��������

	ChannelFuture sync()
		* ͬ��,�߳�����,ֱ���������
	
	Throwable cause()
		* �����쳣��Ϣ
	
	boolean isSuccess();
		* �Ƿ�����ɹ�
		
