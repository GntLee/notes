--------------------------------
���ؾ���						|
--------------------------------
	# Producer�ĸ��ؾ���
		* Producer���ڷ�����Ϣ��ʱ�򣬻��ȸ���Topic�ҵ�ָ����TopicPublishInfo
		* �ڻ�ȡ��TopicPublishInfo·����Ϣ��RocketMQ�Ŀͻ�����Ĭ�Ϸ�ʽ�� selectOneMessageQueue() �������TopicPublishInfo�е�messageQueueList��ѡ��һ�����У�MessageQueue�����з�����Ϣ��
			public MessageQueue selectOneMessageQueue() {
				int index = this.sendWhichQueue.getAndIncrement();
				int pos = Math.abs(index) % this.messageQueueList.size();
				if (pos < 0)
					pos = 0;
				return this.messageQueueList.get(pos);
			}
		
		* ������ݴ���Ծ��� MQFaultStrategy ������ж���
		* ������һ�� "latencyFaultTolerance" ���ر������������(Ĭ�� false)�����������ȡģ�Ļ����ϣ��ٹ��˵�not available(������)��Broker
		* ��ν��"latencyFaultTolerance"����ָ��֮ǰʧ�ܵģ���һ����ʱ�����˱�
			* ���磬����ϴ������ latency(�ӳ�) ����550Lms�����˱�3000Lms
			* ����1000L�����˱�60000L

		* ����رգ������������ȡģ�ķ�ʽѡ��һ�����У�MessageQueue����������Ϣ��latencyFaultTolerance������ʵ����Ϣ���͸߿��õĺ��Ĺؼ����ڡ�
	

	# Consumer�ĸ��ؾ���
		
