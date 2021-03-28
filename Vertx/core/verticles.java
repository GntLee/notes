------------------
Verticles
------------------
	# Verticle ��ʵ�������ʵ�� Verticle �ӿ�,����ͨ��ֱ�Ӽ̳г����� AbstractVerticle 

		interface Verticle
			Vertx getVertx();
			void init(Vertx vertx, Context context);
			void start(Promise<Void> startPromise) throws Exception;
				* Vert.x ���� Verticle ʱ������ start �����������ã��������ִ����ɺ� Verticle �ͱ��������״̬��

			void stop(Promise<Void> stopPromise) throws Exception;
				* ��Vert.x ����һ�� Verticle ʱ���ᱻ���ã� �������ִ����ɺ� Verticle �ͱ����ֹͣ״̬�ˡ�
	
		abstract class AbstractVerticle implements Verticle 
			public Vertx getVertx() 
			public void init(Vertx vertx, Context context)
			public String deploymentID()
			public JsonObject config()
			public List<String> processArgs(
			public void start(Promise<Void> startPromise) throws Exception
				* �첽�汾 �� start ������ʵ�֣�������һ�� Promise ������
				* ����ִ����ʱ��Verticle ʵ����û�в���ã�״̬���� deployed����ֻ�� startPromise.complete(); ��ʱ�򣬲Ų������

			public void stop(Promise<Void> stopPromise) throws Exception
				* �첽�汾��stop������ֻ�� startPromise.complete(); ��ʱ�򣬲�stop��

			public void start() throws Exception
			public void stop() throws Exception

	
