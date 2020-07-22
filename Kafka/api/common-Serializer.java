-------------------
Serializer<T>	   |
-------------------
	# ��Ϣ�����л��ӿ�,�Ѷ������л�Ϊ�ֽ�����(������)
	# ���󷽷�
		void configure(Map<String, ?> configs, boolean isKey);
			* ���õ�ǰ��,��KafkaProducerʵ��������ʱ�����

		byte[] serialize(String topic, T data);
			*  ִ�����л�����

		default byte[] serialize(String topic, Headers headers, T data) {
			return serialize(topic, data);
		}

		@Override
		void close();
			* ִ�йر�,һ����˵���ǿ�ʵ��
			* ���Ҫ�Լ�ʵ�ֵĻ�,����ȷ���÷�����Ļ����
			* ��Ϊclose()���ܻᱻKafkaProducer���ö��
	
	# �ṩ��N�����л�ʵ����
		ByteArraySerializer
			* byte[]
		
		ByteBufferSerializer
			* ByteBuffer
		
		BytesSerializer
			* Bytes
		
		DoubleSerializer
		FloatSerializer
		IntegerSerializer
		LongSerializer
		ShortSerializer
			* ������������(��װ��)�ı�����

		StringSerializer
			* String,Ĭ�ϱ��� UTF-8

		UUIDSerializer
			* uuid
			* ��ʵ������Ҳ�ǰ� UUID ���� toStirng()�� getBytes(),Ĭ��ʹ��UTF-8����

