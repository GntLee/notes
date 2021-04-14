--------------------------
EventBus
--------------------------
	# ��Ϣ���߽ӿڣ� interface EventBus extends Measured 


--------------------------
����
--------------------------
	EventBus send(String address, @Nullable Object message);
	EventBus send(String address, @Nullable Object message, DeliveryOptions options);
		* ���͵�Ե���Ϣ��ֻ��һ����������ִ�У�����ָ��DeliveryOptions������Ϣ

	default <T> EventBus request(String address, @Nullable Object message, Handler<AsyncResult<Message<T>>> replyHandler)
	default <T> Future<Message<T>> request(String address, @Nullable Object message)
	default <T> EventBus request(String address, @Nullable Object message, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler)
	<T> Future<Message<T>> request(String address, @Nullable Object message, DeliveryOptions options)
		* ������Ϣ������ע����Ӧ����
		* ��Ե���Ϣ�������߻�Ӧ�����ͨ�� replyHandler ��ȡ�����ص���Ϣ

	EventBus publish(String address, @Nullable Object message)
	EventBus publish(String address, @Nullable Object message, DeliveryOptions options)
		* ������Ϣ�������ַ�����д���������ִ��

	<T> MessageConsumer<T> consumer(String address)
	<T> MessageConsumer<T> consumer(String address, Handler<Message<T>> handler)
		* ͨ�����ص�MessageConsumer����ע�ᴦ����
		* Ҳ����ֱ��ע�ᴦ����
			vertx.eventBus().consumer("news.uk.sport", message -> {
			  System.out.println("I have received a message: " + message.body());
			});

	<T> MessageConsumer<T> localConsumer(String address)
	<T> MessageConsumer<T> localConsumer(String address, Handler<Message<T>> handler)
		* ������Ϣ�������ټ�Ⱥ�д���

	<T> MessageProducer<T> sender(String address)
	<T> MessageProducer<T> sender(String address, DeliveryOptions options)
	<T> MessageProducer<T> publisher(String address)
	<T> MessageProducer<T> publisher(String address, DeliveryOptions options)
		* ʹ�õ�ַ�����ã�������Ϣ���Ͷ���

	EventBus registerCodec(MessageCodec codec)
	EventBus unregisterCodec(String name)
		* ע����Ϣ�������
		* ͨ���������������ȡ��ע��
		
	<T> EventBus registerDefaultCodec(Class<T> clazz, MessageCodec<T, ?> codec)
	EventBus unregisterDefaultCodec(Class clazz);
		* Ϊָ���࣬ȡ��ע��Ĭ�ϵ���Ϣ������������͵�ʱ�򣬲���Ҫָ�����������������
		* Ҳ����ͨ��ָ���࣬ȡ�����������ע��

	<T> EventBus addOutboundInterceptor(Handler<DeliveryContext<T>> interceptor);
	<T> EventBus removeOutboundInterceptor(Handler<DeliveryContext<T>> interceptor);
	<T> EventBus addInboundInterceptor(Handler<DeliveryContext<T>> interceptor);
	<T> EventBus removeInboundInterceptor(Handler<DeliveryContext<T>> interceptor);


--------------------------
��̬
--------------------------