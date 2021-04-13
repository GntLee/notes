----------------------
Future
----------------------
	# �첽����ӿڣ� interface Future<T> extends io.vertx.core.AsyncResult<T> 



----------------------
Future ��̬����
----------------------
	static <T> Future<T> future(Handler<Promise<T>> handler) 

	static <T> Future<T> succeededFuture()
	static <T> Future<T> succeededFuture(T result)
		* ����һ���ɹ���Future
		
	static <T> Future<T> failedFuture(Throwable t)
	static <T> Future<T> failedFuture(String failureMessage) 
		* ����һ��ʧ�ܵ�Future

	static <T> Future<T> fromCompletionStage(CompletionStage<T> completionStage)
	static <T> Future<T> fromCompletionStage(CompletionStage<T> completionStage, Context context)
		* ��װJDK��CompletionStageΪvertx��Future
		* ����Vert.x�� Future ͨ������Vert.x�Ĵ��롢���Լ��ͻ��˵�һ��ʹ�ã�Ϊ����Vert.x���߳�ģ�͸��õ���ϣ� �󲿷ֳ�����Ӧʹ�����

----------------------
Future ���󷽷�
----------------------
	boolean isComplete();
	Future<T> onComplete(Handler<AsyncResult<T>> handler);
	default Future<T> onSuccess(Handler<T> handler)
	default Future<T> onFailure(Handler<Throwable> handler)
	T result();
	Throwable cause();
	boolean succeeded();
	boolean failed();
	default <U> Future<U> flatMap(Function<T, Future<U>> mapper)
	default <U> Future<U> compose(Function<T, Future<U>> mapper)
		* ˳����� future
		* ����ǰfuture�ɹ���ִ�� compose ����ָ���ķ������÷��������µ�future�������ص���future���ʱ��future��ϳɹ���
		* ����ǰfutureʧ�ܣ���future���ʧ��
		
	default Future<T> recover(Function<Throwable, Future<T>> mapper)
	<U> Future<U> compose(Function<T, Future<U>> successMapper, Function<Throwable, Future<U>> failureMapper)
	<U> Future<U> transform(Function<AsyncResult<T>, Future<U>> mapper)
	<U> Future<T> eventually(Function<Void, Future<U>> mapper)
	<U> Future<U> map(Function<T, U> mapper);
	<V> Future<V> map(V value);
	default <V> Future<V> mapEmpty()
	Future<T> otherwise(Function<Throwable, T> mapper);
	Future<T> otherwise(T value);
	default Future<T> otherwiseEmpty()
	default CompletionStage<T> toCompletionStage()
		* ת��vertx��FutureΪJDK��CompletionStage
	

