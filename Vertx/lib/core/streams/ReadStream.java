--------------------
ReadStream
--------------------
	# ��ȡ���ӿڣ� interface ReadStream<T> extends StreamBase

	ReadStream<T> exceptionHandler(Handler<Throwable> handler);
		* �쳣������
		
	ReadStream<T> handler(@Nullable Handler<T> handler);
		* ��Ϣ������

	ReadStream<T> pause();
		* ��ͣ��ȡ������ReadStream Ϊ fetch ģʽ ������demandֵΪ0

	ReadStream<T> resume();
		* ������ȡ������ReadStream Ϊ flowing ģʽ
		* �ָ������������κζ��󵽴�Ŀ�ĵ���handler�����������ȼ��� fetch(Long.MAX_VALUE)

	ReadStream<T> fetch(long amount);
		* ����ָ��������streamԪ�ز����������ӵ�Ŀǰ��demandֵ����
		* ��stream��ץȡָ�������Ķ����������ִ�streamʱ�����ᴥ��handler�� fetch�������ۻ��ġ�

	ReadStream<T> endHandler(@Nullable Handler<Void> endHandler);
		* ����������������ȡ����EOF

	default Pipe<T> pipe()
		* ��ȡ�ܵ���

	default Future<Void> pipeTo(WriteStream<T> dst)
	default void pipeTo(WriteStream<T> dst, Handler<AsyncResult<Void>> handler
		* IO��ָ���������Զ�����ѹ����