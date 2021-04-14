---------------------------
AsyncFile 
---------------------------
	# �첽���ļ�����ӿ�
		interface AsyncFile extends ReadStream<Buffer>, WriteStream<Buffer> 
	
---------------------------
���� 
---------------------------
	AsyncFile handler(Handler<Buffer> handler);
	AsyncFile pause();
	AsyncFile resume();
	AsyncFile endHandler(Handler<Void> endHandler);
	AsyncFile setWriteQueueMaxSize(int maxSize);
	AsyncFile drainHandler(Handler<Void> handler);
	AsyncFile exceptionHandler(Handler<Throwable> handler);
	AsyncFile fetch(long amount);

	Future<Void> close();
	void close(Handler<AsyncResult<Void>> handler);
		* �رգ��ͷ���Դ

	void write(Buffer buffer, long position, Handler<AsyncResult<Void>> handler);
	Future<Void> write(Buffer buffer, long position);
		* ���д����λ�ô��ڻ�����ļ���С�� �ļ�������չ����Ӧƫ�Ƶ�λ��

	AsyncFile read(Buffer buffer, int offset, long position, int length, Handler<AsyncResult<Buffer>> handler);
	Future<Buffer> read(Buffer buffer, int offset, long position, int length);
		* �����

	Future<Void> flush();
	AsyncFile flush(Handler<AsyncResult<Void>> handler);

	AsyncFile setReadPos(long readPos);
		* ��λ��

	AsyncFile setReadLength(long readLength);
	long getReadLength();

	AsyncFile setWritePos(long writePos);
	long getWritePos();
		* дλ��
		
	AsyncFile setReadBufferSize(int readBufferSize);

	default Pipe<T> pipe()
	default Future<Void> pipeTo(WriteStream<T> dst)
	default void pipeTo(WriteStream<T> dst, Handler<AsyncResult<Void>> handler)

	default Future<Void> end() 
	void end(Handler<AsyncResult<Void>> handler);
	default Future<Void> end(T data)
	default void end(T data, Handler<AsyncResult<Void>> handler)
	boolean writeQueueFull();



---------------------------
��̬ 
---------------------------