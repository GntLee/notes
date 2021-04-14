------------------
Verticles
------------------
	# Verticle ��ʵ�������ʵ�� Verticle �ӿ�,����ͨ��ֱ�Ӽ̳г����� AbstractVerticle 

		interface Verticle
			Vertx getVertx();
				* ��ȡ����ǰVerticle��Vertxʵ��

			void init(Vertx vertx, Context context);
				* ��ʼ��

			void start(Promise<Void> startPromise) throws Exception;
				* Vert.x ���� Verticle ʱ������ start �����������ã��������ִ����ɺ� Verticle �ͱ��������״̬��

			void stop(Promise<Void> stopPromise) throws Exception;
				* ��Vert.x ����һ�� Verticle ʱ���ᱻ���ã� �������ִ����ɺ� Verticle �ͱ����ֹͣ״̬�ˡ�
	
		abstract class AbstractVerticle implements Verticle 
			public Vertx getVertx() 
			public void init(Vertx vertx, Context context)
			public String deploymentID()
				* ����ID
			public JsonObject config()
			public List<String> processArgs(
			public void start(Promise<Void> startPromise) throws Exception
				* �첽�汾 �� start ������ʵ�֣�������һ�� Promise ������
				* ����ִ����ʱ��Verticle ʵ����û�в���ã�״̬���� deployed����ֻ�� startPromise.complete(); ��ʱ�򣬲Ų������

			public void stop(Promise<Void> stopPromise) throws Exception
				* �첽�汾��stop������ֻ�� startPromise.complete(); ��ʱ�򣬲�stop��

			public void start() throws Exception
			public void stop() throws Exception
	
	# ����ʽ
		* ֱ�Ӳ���ʵ������
			vertx.deployVerticle(mainVerticle);
		
		* ����ʵ����
			vertx.deployVerticle(MainVerticle.class, new DeploymentOptions());
		
		* ����������
			vertx.deployVerticle("com.mycompany.MainVerticle");
		
		* ������������
			vertx.deployVerticle("verticles/myverticle.js");
			vertx.deployVerticle("verticles/my_verticle.rb");
	
	# Context 
		* ÿ�� Verticle �ڲ����ʱ�򶼻ᱻ����һ�� Context
			* �������ò�ͬ��������Event Loop Context ���� Worker Context
		
		* ֮��� Verticle �����е���ͨ���붼���ڴ� Context ��ִ�У�����Ӧ�� Event Loop ��Worker �̣߳�
		* һ�� Context ��Ӧһ�� Event Loop �̣߳��� Worker �̣߳�����һ�� Event Loop ���ܶ�Ӧ��� Context


------------------
�ܽ�
------------------
	# Verticle ����һ���࣬һ��Vertx�����ɶ��Verticle���
	# ������һ��main Verticle ��Ϊ��������
	# ��Verticle�п������ⲿ��ж��Verticle���ᴥ���������ڷ���
	# �����ڲ����ʱ�򴫵ݲ�����Ҳ����ͨ��EventBus������Verticle���շ���Ϣ
	# Verticle�Ĵ��룬��Զֻ�ᱻһ���̷߳��ʣ����ᱻ����ִ�У�����
		1. �Լ��ڴ������½��߳�ִ��  new Thread()
		2. ����Verticle��ʱ�������ˣ� setMultiThread(true)���Ѿ�����ʶΪ����
		3. ͨ�� vertx.executeBlocking(..) ִ����������ʱ������ false ����
	
	# ����Vertx�Ĺ淶��д���룬�������̰߳�ȫ������
	# Verticle��Ϊ2��
		1. ��ͨ�汾
			* �̳߳�ʹ�õ��� EventLoop�������ʱ�򣬷���һ���̸߳������Ժ󶼲���䣬һֱ����ִ��

		2. Work�汾
			* �̳߳�ʹ�õ��� Work-Thread ��ÿ��ִ�ж�������񽻸��̳߳���ĳ���߳�ִ��
			* �ȽϺ�ʱ�Ĵ��룬Ӧ����Work�汾��ִ��

	
