-----------------------------
WriteStream
-----------------------------
	# д���� interface WriteStream<T> extends StreamBase

	WriteStream<T> exceptionHandler(Handler<Throwable> handler);
		* �쳣����

	Future<Void> write(T data);
	void write(T data, Handler<AsyncResult<Void>> handler);	
		* д�����ݣ���Զ��������

	default Future<Void> end()
	void end(Handler<AsyncResult<Void>> handler);
	default Future<Void> end(T data)
	default void end(T data, Handler<AsyncResult<Void>> handler)
		* ���д�룬д�����һ����Ϣ�����;ͶϿ�

	WriteStream<T> setWriteQueueMaxSize(int maxSize);
		* ����д���д�С

	boolean writeQueueFull();
		* �ж�д���У��Ƿ�����

	WriteStream<T> drainHandler(@Nullable Handler<Void> handler);
		* ���м������������п���λ�ú󣬻ᴥ��
