-------------------
Promise
-------------------
	# �첽�ӿڣ�interface Promise<T> extends Handler<AsyncResult<T>> 
		* ֧����ʽ����
	
-------------------
����
-------------------
	default void handle(AsyncResult<T> asyncResult)

	default void complete(T result)
	default void complete()

	default void fail(Throwable cause)
	default void fail(String message)
	boolean tryComplete(T result)
	default boolean tryComplete()
	boolean tryFail(Throwable cause)
	default boolean tryFail(String message)
	Future<T> future();



-------------------
��̬
-------------------
	static <T> Promise<T> promise()
