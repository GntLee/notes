------------------------
rabbitmq
------------------------
	# ��ַ
		http://www.rabbitmq.com/download.html
		http://rabbitmq.mr-ping.com/
		https://github.com/rabbitmq
	
	# Ĭ�϶˿���Ϣ
		amqp		:5672			//MQЭ��
		web			:15672			//WEB����˿�
		dustering	:25672			//��Ⱥ�˿�


------------------------
���ĵĸ���
------------------------
	RabbitMQ Server
		* ���Ľ�ɫ����ά��һ����Producer��Consumer��·�ߣ���֤�����ܹ�����ָ���ķ�ʽ���д��䡣���������֤Ҳ����100%�ı�֤�����Ƕ�����ͨ��Ӧ����˵���Ѿ��㹻�ˡ�
		* ����ģʽ
			producer -> connection(channels) -> Broker(exchanges[queues]) -> connection(channels) -> consumer
	
	Producer(core)
		* ��Ϣ������
	
	Consumer(core)
		* ��Ϣ������
	
	Message(core)
		* һ����Ϣ��������
			1, payload ���������
			2, label ��ǩ,��������
	
	Queues(core)
		* ����
		
	Exchanges(core)
		* ������
		
	Connection
		* ����һ��TCP����,�����ߺ������߶�ͨ��TCP���ӵ�RabbitMQ������
		* �������ʼ,���Ǵ����������
	
	Channels
		* ͨ��,��������.���ǽ�����TCP������,�������������� Channel �н��е�
		* ������ʼ,�Ƚ���TCP����,Ȼ��ͽ������ͨ��
		* Ϊʲô��ֱ��ʹ��TCP,����Ҫ����TCPʹ�� Channel (ͨ��)?
			> ����ϵͳ��˵,�����͹ر�TCP���д��۵�,����TCP��������������.����,��TCP�Ͻ���ͨ�� Channel ��û�д��۵�
			> ���Բ���ʹ�ö�� Producer ���� Push �� Consumer ���� Receive
		
		* ͨ�������ǣ�ͨ��һ��TCP���ӿ�����N��channel��������
	

	Bindings 
		* ��

	
	Virtual Host
		* ���⻧��������namespaceһ����һ��Server���Ի��ֳ����Virtual Host
		* �����ò�ͬ���û�ά���Լ���exchange/queue
	
