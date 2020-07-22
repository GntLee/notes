---------------------------
KafkaConsumer<K, V>		   |
---------------------------
	# ��Ϣ������
	# ���캯��
		KafkaConsumer(Map<String, Object> configs)
		KafkaConsumer(Map<String, Object> configs,Deserializer<K> keyDeserializer,Deserializer<V> valueDeserializer)
		KafkaConsumer(Properties properties)
		KafkaConsumer(Properties properties,Deserializer<K> keyDeserializer,Deserializer<V> valueDeserializer) 
	
	# ʵ������
		void close()
		void close(Duration timeout)
			* �رտͻ���,�ͷ���Դ
			* timeout ���ó�ʱʱ��,Ĭ���� 30s

		void commitAsync()
		void commitAsync(final Map<TopicPartition, OffsetAndMetadata> offsets, OffsetCommitCallback callback)
		void commitAsync(OffsetCommitCallback callback)
			* �첽�ύ����λ��
			* offsets ��������ָ������,ָ��������λ��ֵ
			* OffsetCommitCallback ���ûص�
				void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception);

		void commitSync()
		void commitSync(Duration timeout)
		void commitSync(final Map<TopicPartition, OffsetAndMetadata> offsets)
		void commitSync(final Map<TopicPartition, OffsetAndMetadata> offsets, final Duration timeout)
			* offsets ��������ָ������,ָ��������λ��ֵ
			* ͬ���ύ����λ��

		OffsetAndMetadata committed(TopicPartition partition)
		OffsetAndMetadata committed(TopicPartition partition, final Duration timeout)
			* ��ȡ�Ѿ��ύ��������λ��
			* OffsetAndMetadata
				private final long offset;
				private final String metadata;
				private final Integer leaderEpoch;
	
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

		Map<String, List<PartitionInfo>> listTopics()
		Map<String, List<PartitionInfo>> listTopics(Duration timeout)

		Map<MetricName, ? extends Metric> metrics()


		List<PartitionInfo> partitionsFor(String topic)
		List<PartitionInfo> partitionsFor(String topic, Duration timeout)
			* ����ָ����������з�����Ϣ,timeout ָ���ȴ�����Ԫ���ݵĳ�ʱʱ��
			* PartitionInfo ����һ��������Ϣ
					private final String topic;					// ����
					private final int partition;				// ��ǰ�ڵ���
					private final Node leader;					// leader�ڵ�
					private final Node[] replicas;				// ar�б�
					private final Node[] inSyncReplicas;		// isr�б�
					private final Node[] offlineReplicas;		// osr�б�
			* Node �����ڵ���Ϣ
				private final int id;
				private final String idString;
				private final String host;			//������
				private final int port;				// �˿�
				private final String rack;


		Set<TopicPartition> paused()
		void pause(Collection<TopicPartition> partitions)
		void resume(Collection<TopicPartition> partitions)
			* ��ͣ/�ָ���ĳЩ����,����������

		ConsumerRecords<K, V> poll(final Duration timeout)
			* ��broker��ȡ��Ϣ
			* timeout��������ʱ��

		long position(TopicPartition partition)
		long position(TopicPartition partition, final Duration timeout)
			* ��ȡ����һ����Ҫ��ȡ����Ϣλ��
			* timeout��������ʱ��,��ΪҪ��ȡԪ����,���ܻ�����

		void seek(TopicPartition partition, long offset)
		void seekToBeginning(Collection<TopicPartition> partitions)
		void seekToEnd(Collection<TopicPartition> partitions)
			* �ƶ�ָ��topic��ָ��partition������ƫ����
		
		Set<String> subscription()
		void subscribe(Collection<String> topics)
		void subscribe(Collection<String> topics, ConsumerRebalanceListener listener)
		void subscribe(Pattern pattern)
		void subscribe(Pattern pattern, ConsumerRebalanceListener listener)
			* ��������,����������ظ��ĵ���,��ô�����һ�ε��õ�Ϊ׼
			* ���ʹ��������ʽ�ķ�������������,���������ⲻ����Ҳ����,�����ⱻ������,��������������ᱻ�Զ��Ķ���
			* ���ؾ��������:ConsumerRebalanceListener 
				 void onPartitionsRevoked(Collection<TopicPartition> partitions);
				 void onPartitionsAssigned(Collection<TopicPartition> partitions);

			* ʹ�����ַ�ʽ���ж�����Ϣ�����Զ����ؾ���Ĺ���
			* �ڶ�������ߵ������,���Ը��ݷ�������������Զ��������������������Ĺ�ϵ
			* ���������������ߵ�����/����,���������ϵ���Զ�����ת,�Լ�ʵ�ֹ��ϵ��Զ�ת��

		
		void assign(Collection<TopicPartition> partitions)
			* ������ֱ�Ӷ���ָ�������ָ������
			* TopicPartition ����������������������
				private int hash = 0;			//hashֵ
				private final int partition;	// �������
				private final String topic;		// ������Ϣ
				TopicPartition(String topic, int partition)
			* ���ַ�ʽ����,���߱��Զ��ĸ��ؾ��⹦��

		Set<TopicPartition> assignment()
			* ��ȡ���ĵ�����ͷ����partition

		void unsubscribe()
			* ȡ����������
			* Ҳ����ͨ�� subscribe ȥ����һ���յ����⼯�����ﵽȡ�����ĵ�Ч��
				subscribe(new ArrayList<String>());
		
		void wakeup()
			* �÷�����Ψһ���Դ������߳�������õİ�ȫ�ķ���
			* �÷��������ú�,�����˳� poll() �߼�,�����׳�:WakeupException
			* ������Ҫ������쳣,��ֻ���˳� poll() ��һ�ֻ���
			* ����ѭ���Ժ�һ��Ҫ��ʽ��ִ�йرն������ͷ����й�����ռ�õĸ���ϵͳ��Դ
