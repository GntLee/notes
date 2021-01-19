------------------------
KafkaProducer<K, V>		|
------------------------
	# ��Ϣ������
		* ����һ���̰߳�ȫ�Ķ���
	
	# ���캯��
		KafkaProducer(final Map<String, Object> configs)
		KafkaProducer(Map<String, Object> configs, Serializer<K> keySerializer, Serializer<V> valueSerializer)
		KafkaProducer(Properties properties)
		KafkaProducer(Properties properties, Serializer<K> keySerializer, Serializer<V> valueSerializer)
		
	# ʵ������
		void close()
		void close(long timeout, TimeUnit timeUnit)
			* �ر�,�ͷ���Դ
			* �����ָ����ʱʱ��,��ô������,ֱ�����е���Ϣ���������
			* ��������˳�ʱʱ��,һ����ʱ�ͻ�ǿ��ִ���˳�

		void flush()
		
		Map<MetricName, ? extends Metric> metrics()
		List<PartitionInfo> partitionsFor(String topic)
		Future<RecordMetadata> send(ProducerRecord<K, V> record)
			* send �������첽��,����ͨ�� Future ����ȡ��ִ�еĽ��(RecordMetadata),���������߳�ֱ���ɹ�

		Future<RecordMetadata> send(ProducerRecord<K, V> record, Callback callback)
			* ͬ��,�������һ���ص� Callback �ӿ�ʵ��:void onCompletion(RecordMetadata metadata, Exception exception);
			* ����쳣��metadataΪnull,exception��Ϊnull,��֮metadata��Ϊnull,exceptionΪnull
			* ����ͬһ����������,�ص�����(Callback)�ĵ��ÿ��Ա�֤��������,��ִ�е��õ� Callback �϶�����ִ��
		
		void initTransactions()
		void abortTransaction() 
		void beginTransaction()
		void commitTransaction()
		void sendOffsetsToTransaction(Map<TopicPartition, OffsetAndMetadata> offsets,String consumerGroupId)

------------------------
RecordMetadata			|
------------------------
	# ��¼��Ԫ����
	# ��̬����
		int UNKNOWN_PARTITION = -1;

	# ���췽��
		RecordMetadata(TopicPartition topicPartition, long baseOffset, long relativeOffset, long timestamp,Long checksum, int serializedKeySize, int serializedValueSize)
	
	# ʵ������
		private final long offset;
		private final long timestamp;
		private final int serializedKeySize;
		private final int serializedValueSize;
		private final TopicPartition topicPartition;

	# ʵ������
		boolean hasOffset()
		boolean hasTimestamp()
		String topic()
