---------------------------
WorkerExecutor
---------------------------
	# �첽����ӿڣ� interface WorkerExecutor extends Measured 


---------------------------
����
---------------------------
	<T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, boolean ordered, Handler<AsyncResult<@Nullable T>> resultHandler);
	default <T> void executeBlocking(Handler<Promise<T>> blockingCodeHandler, Handler<AsyncResult<@Nullable T>> resultHandler)
	<T> Future<@Nullable T> executeBlocking(Handler<Promise<T>> blockingCodeHandler, boolean ordered)
	default <T> Future<T> executeBlocking(Handler<Promise<T>> blockingCodeHandler)
		* ִ����������

	void close(Handler<AsyncResult<Void>> handler)
	Future<Void> close()
		* �ͷ���Դ
	


