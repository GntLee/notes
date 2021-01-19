------------------------
ConsumerRecord<K, V>	|
------------------------
	# ���������ѵ���Ϣ����
	# ��̬����
		public static final long NO_TIMESTAMP = RecordBatch.NO_TIMESTAMP;
		public static final int NULL_SIZE = -1;
		public static final int NULL_CHECKSUM = -1;
	
	# ���캯��
		ConsumerRecord(String topic,int partition,long offset,K key,V value)
		ConsumerRecord(String topic,int partition,long offset,long timestamp,TimestampType timestampType,Long checksum,int serializedKeySize,int serializedValueSize,K key,V value,Headers headers)
		ConsumerRecord(String topic,int partition,long offset,long timestamp,TimestampType timestampType,Long checksum,int serializedKeySize,int serializedValueSize,K key,V value,Headers headers,Optional<Integer> leaderEpoch)
		ConsumerRecord(String topic,int partition,long offset,long timestamp,TimestampType timestampType,long checksum,int serializedKeySize,int serializedValueSize,K key,V value)

		timestampType
			* ʱ���������,ö��ֵ
				NO_TIMESTAMP_TYPE(-1, "NoTimestampType") // ûʱ���
				CREATE_TIME(0, "CreateTime")			//  ��Ϣ����ʱ��
				LOG_APPEND_TIME(1, "LogAppendTime");	//  ׷�ӵ���־�ļ���ʱ��
		
		headers
			* ��Ϣͷ,�������ڴ���ҵ�����������������Ϣ
			* Headers��һ���ӿ�,ʵ����:RecordHeaders

	# ʵ������
		private final String topic;						// ����
		private final int partition;					// ������
		private final long offset;						// ����ƫ����
		private final long timestamp;					// ʱ���
		private final TimestampType timestampType;		// ʱ���������,����ʱ��,��׷��ʱ��
		private final int serializedKeySize;			// key�Ĵ�С
		private final int serializedValueSize;			// value�Ĵ�С
		private final Headers headers;					// ��Ϣͷ
		private final K key;							// ��
		private final V value;							// ֵ
		private final Optional<Integer> leaderEpoch;
		private volatile Long checksum;					// CRC32У��ֵ

	# ʵ������
		Headers headers()
		K key()
		Optional<Integer> leaderEpoch()
		long offset()
		int partition()

		int serializedKeySize()
		int serializedValueSize()
			* ����key/value�Ĵ�С,���Ϊ null,���� -1

		long timestamp()
		TimestampType timestampType()
		String topic()
		V value()
