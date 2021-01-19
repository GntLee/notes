-------------------
Flow
-------------------
	# ��������, ��Ӧʽ�������������
	# JDK9���¶���, ͬ��������Doug Lea����
	# Դ��
		public final class Flow {
			@FunctionalInterface
			public static interface Publisher<T> {
				public void subscribe(Subscriber<? super T> subscriber);
			}

			public static interface Subscriber<T> {
				public void onSubscribe(Subscription subscription);
				public void onNext(T item);
				public void onError(Throwable throwable);
				public void onComplete();
			}

			public static interface Subscription {
				public void request(long n);
				public void cancel();
			}

			public static interface Processor<T,R> extends Subscriber<T>, Publisher<R> {}

			static final int DEFAULT_BUFFER_SIZE = 256;

			public static int defaultBufferSize() {
				return DEFAULT_BUFFER_SIZE;
			}
		}

	# Publisher
		* �������������ݺͿ����¼��ķ���

	# Subscriber
		* �������������ݺ��¼��ķ���

	# Subscription
		* ����������Publisher��Subscriber�ķ���
		
	# Processor
		* ������ת��Publisher��Subscriber�ķ���
			Publisher(T) -> Processor -> Subscriber(R)
		

	# �����, ʵ����
		* Publisher�ӿڵļ�ʵ��, ���Խ������ڼ򵥵���������չ�������Լ�������
		* ��ΪӦ�ó��򿪷���Ա, �ᷢ��ʵ����Щ�ӿںܸ���
		* RxJava����Ӧʽ����Javaʵ��֮һ



-------------------
SubmissionPublisher
-------------------
	# Flow.Publisher ��ʵ��
		class SubmissionPublisher<T> implements Publisher<T>, AutoCloseable
	
		* ������������������, ͬʱ��Reactive Stream����


	
	