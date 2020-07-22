---------------
Future
---------------
	# ʵ����JDK�� Future
		public interface Future<V> extends java.util.concurrent.Future<V> 
	
	# ���󷽷�

		// I/O�����Ƿ�ִ�гɹ�
		boolean isSuccess();

		// ����Ƿ����ͨ�������cancel(boolean mayInterruptIfRunning)ȡ��I/O����
		boolean isCancellable();

		// ����I/O�������쳣ʵ�� - ���I/O���������ǳɹ��ģ��˷�������null
		Throwable cause();

		// Ϊ��ǰFutureʵ����Ӽ���Future������ɵļ����� - isDone()��������֮�����м�����ʵ����õ��ص�
		Future<V> addListener(GenericFutureListener<? extends Future<? super V>> listener);
		Future<V> addListeners(GenericFutureListener<? extends Future<? super V>>... listeners);
		
		// Ϊ��ǰFuture�Ƴ�����Future������ɵļ�����
		Future<V> removeListener(GenericFutureListener<? extends Future<? super V>> listener);
		Future<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... listeners);

		// ͬ���ȴ�Future��ɵõ����ս�����ɹ��������׳��쳣��ʧ�ܣ�����Ӧ�ж�
		Future<V> sync() throws InterruptedException;

		// ͬ���ȴ�Future��ɵõ����ս�����ɹ��������׳��쳣��ʧ�ܣ�������Ӧ�ж�
		Future<V> syncUninterruptibly();

		// �ȴ�Future��ɣ���Ӧ�ж�
		Future<V> await() throws InterruptedException;

		// �ȴ�Future��ɣ�����Ӧ�ж�
		Future<V> awaitUninterruptibly();

		// ����ʱʱ�޵ĵȴ�Future��ɣ���Ӧ�ж�
		boolean await(long timeout, TimeUnit unit) throws InterruptedException;
		boolean await(long timeoutMillis) throws InterruptedException;
		
		// ����ʱʱ�޵ĵȴ�Future��ɣ�����Ӧ�ж�
		boolean awaitUninterruptibly(long timeout, TimeUnit unit);
		boolean awaitUninterruptibly(long timeoutMillis);

		// ���������Ϸ���Future�Ľ�������Futureδ��ɣ��˷���һ������null����Щ���������Future�ɹ���ȡ���Ľ����null����Ҫ���μ��isDone()�����Ƿ�Ϊtrue
		V getNow();

		// ȡ����ǰFutureʵ����ִ�У����ȡ���ɹ����׳�CancellationException�쳣
		@Override
		boolean cancel(boolean mayInterruptIfRunning);
	
	* sync()��await()��������, ֻ��sync()�����쳣ִ�е����, һ������ִ���쳣���ϰ��쳣ʵ����װ�׳�, ��await()�������쳣�޸�֪
