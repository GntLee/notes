-------------------------
Pipe
-------------------------
	# �ܵ����ӿڣ� interface Pipe<T> 

	Pipe<T> endOnFailure(boolean end);
	Pipe<T> endOnSuccess(boolean end);
	Pipe<T> endOnComplete(boolean end);
		* ���/�쳣/�ɹ� ʱ���� Write���� end()

	default Future<Void> to(WriteStream<T> dst)
	void to(WriteStream<T> dst, Handler<AsyncResult<Void>> completionHandler)
		* ����д�뵽Ŀ�ĵ�

	void close();
