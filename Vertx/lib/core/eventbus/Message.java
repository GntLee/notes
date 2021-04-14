---------------------
Message
---------------------
	# ��Ϣ����ӿڣ� interface Message<T> 

---------------------
����
---------------------
	String address();
		* ��Ϣ��ַ

	MultiMap headers();
		* header��Ϣ

	T body();
		* body����

	String replyAddress();
	boolean isSend();
	default void reply(@Nullable Object message)
	void reply(@Nullable Object message, DeliveryOptions options)
		* ��һ��Ӧ����Ϣ���ظ������߲����÷����ߵ�Ӧ������

	default <R> void replyAndRequest(@Nullable Object message, Handler<AsyncResult<Message<R>>> replyHandler)
	default <R> Future<Message<R>> replyAndRequest(@Nullable Object message) 
	default <R> void replyAndRequest(@Nullable Object message, DeliveryOptions options, Handler<AsyncResult<Message<R>>> replyHandler)
	<R> Future<Message<R>> replyAndRequest(@Nullable Object message, DeliveryOptions options)
		* ����Ӧ����Ϣ�������ߣ����Ҽ��������ߵĻظ�

	default void fail(int failureCode, String message)
		* ָ�������Ϣ�쳣������״̬�����Ϣ
		* �ᴥ�������ߵļ�������ʧ���߼����쳣�����ǣ�io.vertx.core.eventbus.ReplyException
			
			MessageConsumer<Message<String>> consumer = eventBus.consumer("demo");
			consumer.handler(message -> {
				message.fail(999, "ϵͳ�쳣");
			});

			eventBus.request("demo", "Hello", reply -> {
				Message<Object> result = reply.result();
				if (reply.succeeded()) {
					System.out.println("�յ��ظ�:" + result.body());
				} else {
					ReplyException throwable = (ReplyException) reply.cause();
					System.out.println("�ظ��쳣�ˣ�" + throwable.failureCode() + ":" + throwable.getMessage() + ":" + throwable.failureType());
				}
				
			});