-------------------------------
Future<V>						|
-------------------------------
	# �첽ִ�н���Ľӿ�
	# ����
		boolean cancel(boolean mayInterruptIfRunning);
			* ȡ��ִ��
			* mayInterruptIfRunning �����Ƿ�Ҫ�׳��߳��ж��쳣

		boolean isCancelled();
			* �Ƿ�ȡ��ִ��

		boolean isDone();
			* �Ƿ�ִ�����

		V get() throws InterruptedException, ExecutionException;
			* ��ȡ��ִ�еĽ��,��������ǰ���߳�

		V get(long timeout, TimeUnit unit)throws InterruptedException, ExecutionException, TimeoutException;
			* ��ȡ��ִ�еĽ��,��������ǰ���߳�
			* ��������һ����ʱʱ��,��ʱ���׳� TimeoutException

