----------------------------
˳����Ϣ					|
----------------------------
	# ��Ĭ�ϵ��������Ϣ���ͻ��ȡRound Robin��ѯ��ʽ����Ϣ���͵���ͬ��queue(��������)
		* ��������Ϣ��ʱ��Ӷ��queue����ȡ��Ϣ������������ͺ������ǲ��ܱ�֤˳��
		* ����������Ϣ������ļ������ܻᱻ�����ѣ���Ϊ���ǲ���һ��Queue
	
	# ���Ʒ��͵�˳����Ϣ(������Ϣ)ֻ���η��͵�ͬһ��queue�У����ѵ�ʱ��ֻ�����queue��������ȡ����ͱ�֤��˳��
		* �����ͺ����Ѳ����queueֻ��һ��������ȫ������
		* ������queue���룬��Ϊ�������򣬼����ÿ��queue����Ϣ���������
	
	# ���ĵ�˼��
		* һ����Ҫ��˳�����ѵ���Ϣ����֤�����ڷ��͵�ʱ���Ⱥ󶼷��͵���ͬһ�� MessageQueue
		* ��������һ����ͬ�ı�ʶ��ID��Ȼ��ͨ��ID % MessageQueue.size() ����֤���͵���ͬ��MessageQueue

----------------------------
˳����Ϣ������				|
----------------------------
	DefaultMQProducer producer = new DefaultMQProducer("CONSUMER-GROUP-NAME");
	producer.setNamesrvAddr("127.0.0.1:9876");
	producer.start();

	// ����˳����Ϣ����һ����ͬ�Ķ���id
	Integer orderId = 1;

	// ��Ϣ1
	Message message1 = new Message("TopicTest", "����������Ϣ", "Hello".getBytes());
	// �Լ�ʵ��MessageQueueSelector��ѡ������Ϣ��MessageQueue
	SendResult sendResult1 = producer.send(message1, new MessageQueueSelector() {
		@Override
		public MessageQueue select(List<MessageQueue> messageQueues, Message msg, Object arg) {
			// ͨ��ȡģ����֤����id��ͬ����Ϣ�����͵�ͬһ��Queue
			Integer id = (Integer) arg;
			int index = id % messageQueues.size();
			return messageQueues.get(index);
		}
	}, orderId);
	System.out.println(sendResult1);


	// ��Ϣ2
	Message message2 = new Message("TopicTest", "������Ϣ", "Hello".getBytes());
	SendResult sendResult2 = producer.send(message2, new MessageQueueSelector() {
		@Override
		public MessageQueue select(List<MessageQueue> messageQueues, Message msg, Object arg) {
			Integer id = (Integer) arg;
			int index = id % messageQueues.size();
			return messageQueues.get(index);
		}
	}, orderId);
	System.out.println(sendResult2);


	// ��Ϣ3
	Message message3 = new Message("TopicTest", "������Ϣ", "Hello".getBytes());
	SendResult sendResult3 = producer.send(message3, new MessageQueueSelector() {
		@Override
		public MessageQueue select(List<MessageQueue> messageQueues, Message msg, Object arg) {
			Integer id = (Integer) arg;
			int index = id % messageQueues.size();
			return messageQueues.get(index);
		}
	}, orderId);
	System.out.println(sendResult3);

	producer.shutdown();