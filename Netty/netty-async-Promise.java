-----------------------
Promise
-----------------------
	# ʵ���� io.netty.util.concurrent.Future<V> �Ľӿ�
		

	# ���󷽷�
		// ��ǵ�ǰFuture�ɹ������ý����������óɹ�����֪ͨ���еļ����������Future�Ѿ��ɹ�����ʧ�ܣ����׳�IllegalStateException
		Promise<V> setSuccess(V result);

		// ��ǵ�ǰFuture�ɹ������ý����������óɹ�����֪ͨ���еļ��������ҷ���true�����򷵻�false
		boolean trySuccess(V result);

		// ��ǵ�ǰFutureʧ�ܣ����ý��Ϊ�쳣ʵ����������óɹ�����֪ͨ���еļ����������Future�Ѿ��ɹ�����ʧ�ܣ����׳�IllegalStateException
		Promise<V> setFailure(Throwable cause);

		// ��ǵ�ǰFutureʧ�ܣ����ý��Ϊ�쳣ʵ����������óɹ�����֪ͨ���еļ��������ҷ���true�����򷵻�false
		boolean tryFailure(Throwable cause);
		
		// ��ǵ�ǰ��Promiseʵ��Ϊ����ȡ�������óɹ�����true�����򷵻�false
		boolean setUncancellable();

		// ����ķ�����io.netty.util.concurrent.Future�еķ�������һ�£�ֻ���޸��˷�������ΪPromise

		@Override
		Promise<V> addListener(GenericFutureListener<? extends Future<? super V>> listener);

		@Override
		Promise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... listeners);

		@Override
		Promise<V> removeListener(GenericFutureListener<? extends Future<? super V>> listener);

		@Override
		Promise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... listeners);

		@Override
		Promise<V> await() throws InterruptedException;

		@Override
		Promise<V> awaitUninterruptibly();

		@Override
		Promise<V> sync() throws InterruptedException;

		@Override
		Promise<V> syncUninterruptibly();
	
	# ʵ����
		* Promise��ʵ����Ϊ io.netty.util.concurrent.DefaultPromise����ʵDefaultPromise���кܶ����࣬ĳЩʵ����Ϊ�˶����ض��ĳ���������չ��
		* DefaultPromise �̳��� io.netty.util.concurrent.AbstractFuture
	
	# ģ��ʹ�ó���
		public class PromiseMain {

		public static void main(String[] args) throws Exception {
			String url = "http://xxx.yyy.zzz";
			EventExecutor executor = GlobalEventExecutor.INSTANCE;
			Promise<DownloadResult> promise = new DefaultPromise<>(executor);
			promise.addListener(new DownloadResultListener());
			Thread thread = new Thread(() -> {
				try {
					System.out.println("��ʼ������Դ,url:" + url);
					long start = System.currentTimeMillis();
					// ģ�����غ�ʱ
					Thread.sleep(2000);
					String location = "C:\\xxx\\yyy\\z.md";
					long cost = System.currentTimeMillis() - start;
					System.out.println(String.format("������Դ�ɹ�,url:%s,���浽:%s,��ʱ:%d ms", url, location, cost));
					DownloadResult result = new DownloadResult();
					result.setUrl(url);
					result.setFileDiskLocation(location);
					result.setCost(cost);
					// ֪ͨ���
					promise.setSuccess(result);
				} catch (Exception ignore) {

				}
			}, "Download-Thread");
			thread.start();
			Thread.sleep(Long.MAX_VALUE);
		}

		@Data
		private static class DownloadResult {

			private String url;

			private String fileDiskLocation;

			private long cost;
		}

		private static class DownloadResultListener implements GenericFutureListener<Future<DownloadResult>> {

			@Override
			public void operationComplete(Future<DownloadResult> future) throws Exception {
				if (future.isSuccess()) {
					DownloadResult downloadResult = future.getNow();
					System.out.println(String.format("�������֪ͨ,url:%s,�ļ�����·��:%s,��ʱ:%d ms", downloadResult.getUrl(),
							downloadResult.getFileDiskLocation(), downloadResult.getCost()));
				}
			}
		}
	}