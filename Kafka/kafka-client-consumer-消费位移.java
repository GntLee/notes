------------------------
λ���ύ				|
------------------------
	# ÿ��poll���ص��ǻ�û�б����ѹ�����Ϣ
		* ��Ϊ��һ��,������Ҫ��¼��������һ�����ѵ�λ��,���ұ���־û�����
		* ������־û�����,���ܻᶪʧ,�����¼��������������Ҳ��֪�������￪ʼ����

		* �ɰ汾�������߿ͻ���,����λ�ƴ���� zookeeper��
		* �°汾�������߿ͻ���,����λ�ƴ����Kafka�ڲ������� _consumer_offsets ��

		* ����Ϣ�־û��Ķ�����֮Ϊ�ύ,����������������Ϣ֮��,��Ҫִ������λ�Ƶ��ύ
	
	# ģ��ͼ
		   0     1     2     3     4     5     6     7
		+-----+-----+-----+-----+-----+-----+-----+-----+
		|  A  |  B  |  C  |  D  |  E  |  F  |  G  |  x  |
		+-----+-----+-----+-----+-----+-----+-----+-----+

		�Ѿ����ѵ���Ϣ(���ѵ�D)
		������������������������������������������������>  ^ ����ƫ����Ϊ 4
							
	
	# ��ȡ������λ��ֵ
		long position(TopicPartition partition)
		long position(TopicPartition partition, final Duration timeout)
			* ��ȡ����һ����Ҫ��ȡ����Ϣλ��
			* ��ʵ�����Լ����ѵ����һ����¼ֵ + 1
		
		OffsetAndMetadata committed(TopicPartition partition)
		OffsetAndMetadata committed(TopicPartition partition, final Duration timeout)
			* ��ȡ�Ѿ��ύ��������λ��
			* �����Լ����ѵ����һ����¼ֵ
		
		* ��ЩAPI����Ҫ�Ƚ��� poll()����,�ɹ��Ļ���˷��������ִ�гɹ�
	
	# KafkaĬ�ϵ�����λ���ύ��ʽΪ�Զ��ύ
		* ����ͨ������:enable.auto.commit ����,Ĭ��ֵΪ true
			properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
		
		* ��������λ�Ƽ�¼�ύ�ļ��:auto.commit.interval.ms,Ĭ��ֵΪ: 50000 (ms )
			properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"50000");

		* Ĭ�ϵķ�ʽ��,������ÿ��5s�ͻ����ȡ����ÿ�������е��������λ�ƽ����ύ
		* �����������poll()�������߼�������ɵ�,ÿ���������������ȡ����֮ǰ�������Ƿ���Խ���λ���ύ
		* �������,��ô�ͻ��ύ��һ����ѯ��λ��

		* �Զ��ύ�ķ�ʽ�ǳ���,ʡ��,���ǿ��ܻ�����ظ����ѵ�����(����λ��δ�ύʱ,ϵͳ���)
		* �����ʵ��ļ���auto.commit.interval.msֵ,�������ظ����ѵ�����,���ǲ�����ȫ����,���һ���ʹ����λ�Ƶ��ύ���ӵ�Ƶ��

		* ��Ϣ�Ķ�ʧ���ܻᷢ���ڶ��߳����ѵ������
			1. A�̴߳�topic��ȡ��Ϣ���浽���صĶ���: BlockingQueue,�����Զ��ύ����λ��
			2. �߳�B�ӱ��ض���������Ϣ
			3. ϵͳ崻�,�߳�B��û������ϱ��ض����е���Ϣ,�����߳�A�Ѿ��ύ������λ��
	

	# �ر��Զ��ύ��,����ʹ���ֶ��ύ����λ�Ƶ�API
		void commitAsync()
		void commitAsync(final Map<TopicPartition, OffsetAndMetadata> offsets, OffsetCommitCallback callback)
		void commitAsync(OffsetCommitCallback callback)
			* �첽�ύ
			* OffsetCommitCallback ���ύ�ɹ���Ļص�
				void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception);

		void commitSync()
		void commitSync(Duration timeout)
			* ͬ���ύ
			* ֻҪû�з������ɻָ��Ĵ���,���ͻ������������߳�,ֱ���ύ�ɹ�
			* ���ڲ��ɻָ��Ĵ���,������Ҫ�����Ҵ���

		void commitSync(final Map<TopicPartition, OffsetAndMetadata> offsets)
		void commitSync(final Map<TopicPartition, OffsetAndMetadata> offsets, final Duration timeout)
			* �ṩ�˸���ϸ���ȵ�����λ��
			* ���Ծ�׼������ָ�������ָ������,������λ��ֵ
		
		* �ޅ��ύ,�����poll()��ȡ������λ�ƽ����ύ
		* �ޅ��ύ,���ύ����λ�Ƶ�Ƶ�ʺ���ȡ������Ϣ,����������Ϣ��Ƶ����һ����

		* offsets �ṩ�˸���ϸ���ȵ�����λ�ƿ���
			* TopicPartition ��ʾtopic�ͷ�����Ϣ
				private int hash = 0;
				private final int partition;
				private final String topic;

			* OffsetAndMetadata ��ʾλ����Ϣ
				private final long offset;
				private final String metadata;
				private final Integer leaderEpoch;

		
	# �첽�ύҲ���ܵ����ظ����ѵ�����
		* ����첽�ύʧ��,һ����ȡ���Եķ���
		* ����ڶ��̻߳�����,Ҳ���ܻᵼ���ظ����ѵ�����
			1. A�߳��첽�ύʧ��,��������
			2. B�߳̽����첽�ύ,�ɹ�
			3. A�߳����Գɹ�,������B�̵߳�����λ���ύ,�����ظ�����
		
		* ��������һ�����������к�(AtomicLong)��ά���첽�ύ��˳��
		* ÿ��λ���ύ֮��͵������к�,���Ҽ�¼
		* ���λ���ύʧ��,��Ҫ����,�ͼ�鵱ǰ���к��Ƿ�����һ���ύ���¼��ֵһ��
		* �����ǰֵ������һ���ύ���ֵ��,˵���и����λ�������ύ��,��ǰ����Ҫ����
		* ������,˵�������Խ��������ύ
	
	# λ���ύʧ�ܵ����һ�㼫�ٷ���
		* ����������Ҳû��ϵ,������ύ,Ҳ���гɹ���
		* �������,�����ӱ����Ѷ�,�������ֻ������ظ����ѵĿ���

		* ����������쳣�˳�,��ô�ظ����ѵ�����Ͳ��ɱ���
		* ��������������˳������پ�������,��ô�������˳����پ���ִ��֮ǰʹ��ͬ���ύ�ķ�ʽ�����İѹ�
			try{
				while(true) {
					// ��ȡ��Ϣ���Ѻ�ִ���첽���ύ
					kafkaConsumer.commitAsync();
				}
			}finally {
				try {
					// ���׵���ͬ���ύһ��
					kafkaConsumer.commitSync();
				}finally {
					kafkaConsumer.close();
				}
			}
	

	# ÿ������һ����¼���ύһ������λ��(ʮ����������)
		// ��Ϣ������
		String topic = consumerRecord.topic();
		// ��Ϣ�ķ���
		int partition = consumerRecord.partition();
		kafkaConsumer.commitSync(Collections.singletonMap(new TopicPartition(topic, partition), new OffsetAndMetadata(consumerRecord.offset() + 1)));
	
	# ���շ�������ͬ���ύ����λ��(�������һ����������Ϣ����ύһ��)
		ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));
		// ������Ϣ�������������
		for(TopicPartition topicPartition : consumerRecords.partitions()) {
			// ��ȡ�����µ���Ϣ����
			List<ConsumerRecord<String,String>> topicPartitionConsumerRecords = consumerRecords.records(topicPartition);
			// ����������Ϣ
			for(ConsumerRecord<String,String> consumerRecord : topicPartitionConsumerRecords) {
				// TODO ������Ϣ
			}
			// ��ȡ�����һ����Ϣ������λ��
			long offset =  topicPartitionConsumerRecords.get(topicPartitionConsumerRecords.size() - 1).offset();
			// ͬ�����ύ��ǰ����������λ��
			kafkaConsumer.commitSync(Collections.singletonMap(topicPartition, new OffsetAndMetadata(offset + 1)));
		}
	

------------------------
ָ��λ������			|
------------------------
	# ��������λ�Ƶĳ־û�,��ʹ�������ڹر�,���������������پ����ʱ��,�����ý�����������ܹ����ݴ洢������λ�Ƽ�����������

	# ÿ�������߲��Ҳ�������¼������λ��ʱ,�ͻ���������߿ͻ��˲��� auto.offset.reset �������������Ӻδ���ʼ��������
		auto.offset.reset
			* ���������Ҳ�������ƫ������¼��ʱ��,�����￪ʼ��������
			* ö��ֵ:
				earliest	����Ϊ�����ƫ����,��ͷ��ʼ����
				latest		��ƫ������Ϊ����ƫ����,ͨ�׵�˵���ǲ�������ǰ����Ϣ��,��������Ϣ��ʼ����(Ĭ��)
				none		���û���ҵ�ƫ������¼,�׳��쳣
		
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "none");
	
	# ͨ����������������ƫ����
		void seek(TopicPartition partition, long offset)
		void seekToBeginning(Collection<TopicPartition> partitions)
		void seekToEnd(Collection<TopicPartition> partitions)

		* ��ִ�� seek ϵ�еķ���֮ǰ��Ҫ��ִ��һ�� poll()����
		* ȷ�����䵽����֮��ſ�����������λ��
			try(KafkaConsumer<Void, String> kafkaConsumer = new KafkaConsumer<>(properties)){
				// ��������
				kafkaConsumer.subscribe(Arrays.asList("test"));
				
				// ����ȷ���������Ѿ������˷�����Ϣ
				while(kafkaConsumer.assignment().size() == 0) {
					// ִ��poll() ��ȡ��������Ϣ
					kafkaConsumer.poll(Duration.ofMillis(1000L));	
				}
				
				// ��������,������������ƫ����
				for (TopicPartition topicPartition : kafkaConsumer.assignment()) {
					kafkaConsumer.seek(topicPartition, 0);
				}
				
				// ��ʼ����
				while(true) {
					ConsumerRecords<Void, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(Long.MAX_VALUE));
					for(ConsumerRecord<Void, String> consumerRecord : consumerRecords ) {
						System.out.println(consumerRecord);
					}
				}
			}
		
		* �������ƫ����Խ����(������һ�����ڵ�ֵ),��ô����� auto.offset.reset ��������������ƫ����

	# ��ȡƫ������Ϣ
		Map<TopicPartition, Long> beginningOffsets(Collection<TopicPartition> partitions)
		Map<TopicPartition, Long> beginningOffsets(Collection<TopicPartition> partitions, Duration timeout)
			* ��ȡָ�������Ŀ�ʼ����ƫ����
			* ���ƫ������һ����0,��Ϊkafka��־���������ܻ�����ɵ���־

		Map<TopicPartition, Long> endOffsets(Collection<TopicPartition> partitions)
		Map<TopicPartition, Long> endOffsets(Collection<TopicPartition> partitions, Duration timeout)
			* ��ȡָ��������ĩβ����ƫ����(�����´δ�д����Ϣ��λ��)
			* timeoutָ����ʱʱ��,�����ָ��,ʹ��:request.timeout.ms ����
		
		Map<TopicPartition, OffsetAndTimestamp> offsetsForTimes(Map<TopicPartition, Long> timestampsToSearch)
		Map<TopicPartition, OffsetAndTimestamp> offsetsForTimes(Map<TopicPartition, Long> timestampsToSearch, Duration timeout)
			* ��ȡָ��ʱ���ָ������������ƫ����
			* Map ����� value �ֶξͱ�ʾʱ���ֵ
			* OffsetAndTimestamp
				private final long timestamp;			// ʱ���
				private final long offset;				// ����ƫ����
				private final Optional<Integer> leaderEpoch;
		
		long position(TopicPartition partition)
		long position(TopicPartition partition, final Duration timeout)
			* ��ȡ�Լ���ָ������������ƫ����
			* ��ʵ�����Լ����ѵ����һ����¼ֵ + 1
	
