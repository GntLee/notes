------------------------
��ͨ����Ϣ����			|
------------------------
	# ͬ������
		DefaultMQProducer producer = new DefaultMQProducer("PRODUCER-GROUP-NAME");
		producer.setNamesrvAddr("localhost:9876");
		producer.start();

		Message message = new Message("TopicTest", "TagA", "HelloWorld".getBytes());

		// ִ��ͬ�����ͣ���ȡ�����ͽ��
		SendResult sendResult = producer.send(message);
		System.out.printf("%s%n", sendResult);
		producer.shutdown();
	

	# �첽����
		DefaultMQProducer producer = new DefaultMQProducer("PRODUCER-GROUP-NAME");
		producer.setNamesrvAddr("localhost:9876");
		producer.start();

		// �����첽ģʽ�£���Ϣ����ʧ�ܺ�����Դ���
		producer.setRetryTimesWhenSendAsyncFailed(0);

		Message message = new Message("TopicTest", "TagA", "OrderID188", "Hello world".getBytes());

		producer.send(message, new SendCallback() {
			@Override
			public void onSuccess(SendResult sendResult) {
				System.out.printf("�첽���ͳɹ�");
			}
			@Override
			public void onException(Throwable e) {
				System.out.printf("ͬ�����ͳɹ�");
				e.printStackTrace();
			}
		});

		producer.shutdown();
	
	# ������
		* ֻ�ܷ������ܳɹ����������UDPЭ��
		* ��������������������������ݿ��ܻᶪʧ
		* �����ڲ����ر���ķ��ͽ���ĳ��������磺��־

		DefaultMQProducer producer = new DefaultMQProducer("PRODUCER-GROUP-NAME");
		producer.setNamesrvAddr("localhost:9876");
		producer.start();
		
		Message message = new Message("TopicTest", "TagA", "HelloWorld".getBytes());
		// ���͵�����Ϣ��û���κη��ؽ��
		producer.sendOneway(message);
		
		producer.shutdown();

------------------------
��ͨ����Ϣ����			|
------------------------
	DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("CONSUMER-GROP-NAME");
	consumer.setNamesrvAddr("localhost:9876");

	// ����ĳ��Topic�����Լ������
	consumer.subscribe("TopicTest", "*");

	consumer.registerMessageListener(new MessageListenerConcurrently() {
		@Override
		public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
			System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
			// ������Ϣ�����ѽ��
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
	});

	consumer.start();

	System.out.printf("Consumer Started.%n");