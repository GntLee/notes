---------------------------
CompositeFuture
---------------------------
	# interface CompositeFuture extends Future<CompositeFuture> 
		* ���� Future Э��

---------------------------
static
---------------------------
	static <T1, T2> CompositeFuture all(Future<T1> f1, Future<T2> f2) 
	static <T1, T2, T3> CompositeFuture all(Future<T1> f1, Future<T2> f2, Future<T3> f3)
	static <T1, T2, T3, T4> CompositeFuture all(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4) 
	static <T1, T2, T3, T4, T5> CompositeFuture all(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5)
	static <T1, T2, T3, T4, T5, T6> CompositeFuture all(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5, Future<T6> f6)
	static CompositeFuture all(List<Future> futures)
		* ���гɹ�����������ʧ�ܺ�ͻ᷵�ص����� Future
		* ������ȫ�����񶼳ɹ������յĽ�����ǳɹ�������һ��ʧ�ܣ����յĽ������ʧ��

	static <T1, T2> CompositeFuture any(Future<T1> f1, Future<T2> f2) 
	static <T1, T2, T3> CompositeFuture any(Future<T1> f1, Future<T2> f2, Future<T3> f3)
	static <T1, T2, T3, T4> CompositeFuture any(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4)
	static <T1, T2, T3, T4, T5> CompositeFuture any(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5)
	static <T1, T2, T3, T4, T5, T6> CompositeFuture any(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5, Future<T6> f6)
	static CompositeFuture any(List<Future> futures)
		* �κ�һ������ɹ�������ȫ��ʧ�ܣ��ͻ᷵�ص����� Future 
		* �κ�һ������ɹ������ս�����ǳɹ���ȫ������ʧ�ܣ����յĽ������ʧ��

	static <T1, T2> CompositeFuture join(Future<T1> f1, Future<T2> f2)
	static <T1, T2, T3> CompositeFuture join(Future<T1> f1, Future<T2> f2, Future<T3> f3)
	static <T1, T2, T3, T4> CompositeFuture join(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4)
	static <T1, T2, T3, T4, T5> CompositeFuture join(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5)
	static <T1, T2, T3, T4, T5, T6> CompositeFuture join(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5, Future<T6> f6)
	static CompositeFuture join(List<Future> futures)
		* �ȴ����е� Future ��ɣ����۳ɰ�
		* ȫ���ɹ��������ս�����ǳɹ�������ʧ�ܣ����ս������ʧ�ܵ�


---------------------------
Method
---------------------------
	 CompositeFuture onComplete(Handler<AsyncResult<CompositeFuture>> handler);
	 default CompositeFuture onSuccess(Handler<CompositeFuture> handler)
	 default CompositeFuture onFailure(Handler<Throwable> handler)
	 Throwable cause(int index);
	 boolean succeeded(int index);
	 boolean failed(int index);
	 boolean isComplete(int index);
	 <T> T resultAt(int index);
	 int size();
	 default <T> List<T> list()
	 default List<Throwable> causes()


