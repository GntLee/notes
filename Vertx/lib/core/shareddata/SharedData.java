--------------------
SharedData
--------------------
	# ���ݹ���ӿڣ� interface SharedData 
		


--------------------
����
--------------------
	<K, V> void getClusterWideMap(String name, Handler<AsyncResult<AsyncMap<K, V>>> resultHandler);
	<K, V> Future<AsyncMap<K, V>> getClusterWideMap(String name);

	<K, V> void getAsyncMap(String name, Handler<AsyncResult<AsyncMap<K, V>>> resultHandler);
	<K, V> Future<AsyncMap<K, V>> getAsyncMap(String name);
		* ��ȡ�첽�����Map�������ڷֲ�ʽ������ʹ��

	<K, V> void getLocalAsyncMap(String name, Handler<AsyncResult<AsyncMap<K, V>>> resultHandler);
	<K, V> Future<AsyncMap<K, V>> getLocalAsyncMap(String name);
	<K, V> LocalMap<K, V> getLocalMap(String name);
		* ��ȡ����Map��������ͬ��Map��Ҳ�������첽Map

	void getLock(String name, Handler<AsyncResult<Lock>> resultHandler);
	Future<Lock> getLock(String name);
		* ��ȡ���������ڼ�Ⱥ�й���

	void getLockWithTimeout(String name, long timeout, Handler<AsyncResult<Lock>> resultHandler);
	Future<Lock> getLockWithTimeout(String name, long timeout);
		* ��ȡ�����������ó�ʱʱ�䣬�����ʱ���������쳣����

	void getLocalLock(String name, Handler<AsyncResult<Lock>> resultHandler);
	Future<Lock> getLocalLock(String name);
		* ��ȡ������

	void getLocalLockWithTimeout(String name, long timeout, Handler<AsyncResult<Lock>> resultHandler);
	Future<Lock> getLocalLockWithTimeout(String name, long timeout);
		* ��ȡ���صĳ�ʱ��

	 void getCounter(String name, Handler<AsyncResult<Counter>> resultHandler);
	 Future<Counter> getCounter(String name);
	 	* ��ȡ�첽������

	 void getLocalCounter(String name, Handler<AsyncResult<Counter>> resultHandler);
	 Future<Counter> getLocalCounter(String name);
	 	* ��ȡ�첽�����������ص�

	 

