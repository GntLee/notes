------------------------------
ProducerRecord				  |
------------------------------
	# ��������������Ϣ����
	# ���캯��
		ProducerRecord(String topic, Integer partition, Long timestamp, K key, V value)
		ProducerRecord(String topic, Integer partition, Long timestamp, K key, V value, Iterable<Header> headers)
		ProducerRecord(String topic, Integer partition, K key, V value)
		ProducerRecord(String topic, Integer partition, K key, V value, Iterable<Header> headers)
		ProducerRecord(String topic, K key, V value)
		ProducerRecord(String topic, V value)
	

	# ʵ������
		private final String topic;			// ����
		private final Integer partition;	// ����
		private final Headers headers;		// ��Ϣͷ
		private final K key;				// k
		private final V value;				// v
		private final Long timestamp;		// ʱ���
