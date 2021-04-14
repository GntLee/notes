----------------------
MessageConsumer
----------------------
	# ��Ϣ�����߽ӿڣ� interface MessageConsumer<T> extends ReadStream<Message<T>>


----------------------
���󷽷�
----------------------
	MessageConsumer<T> exceptionHandler(Handler<Throwable> handler);
	MessageConsumer<T> handler(Handler<Message<T>> handler);
		* ���һ�����������ظ����ûḲ��

	MessageConsumer<T> pause();
	MessageConsumer<T> resume();
	MessageConsumer<T> fetch(long amount);
	MessageConsumer<T> endHandler(Handler<Void> endHandler);
	ReadStream<T> bodyStream();
	boolean isRegistered();
	String address();
	MessageConsumer<T> setMaxBufferedMessages(int maxBufferedMessages);
	int getMaxBufferedMessages();
	void completionHandler(Handler<AsyncResult<Void>> completionHandler);
		* �ڼ�Ⱥ�Ļ����£�ע�ᴦ������ҪһЩʱ��
		* ����������ڴ�����ע��ɹ���ص�

	Future<Void> unregister();
		* ע��������

	void unregister(Handler<AsyncResult<Void>> completionHandler);

	default Pipe<T> pipe()
	default Future<Void> pipeTo(WriteStream<T> dst) 
	default void pipeTo(WriteStream<T> dst, Handler<AsyncResult<Void>> handler)


