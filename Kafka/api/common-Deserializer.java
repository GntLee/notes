--------------------
Deserializer<T>		|
--------------------
	# �����ߵĽ���ӿ�
	# ���󷽷�
		void configure(Map<String, ?> configs, boolean isKey);
			* ����,�ڴ���������ʵ����ʱ�����

		T deserialize(String topic, byte[] data);
			* ������Ϣ

		default T deserialize(String topic, Headers headers, byte[] data) {
			return deserialize(topic, data);
		}	
			* ���Ի�ȡ����Ϣͷ�Ľ��뷽��

		@Override
		void close();
	
	# �ṩ��ʵ����
		ByteArrayDeserializer
		ByteBufferDeserializer
		BytesDeserializer
		DoubleDeserializer
		FloatDeserializer
		IntegerDeserializer
		LongDeserializer
		ShortDeserializer
		StringDeserializer
		UUIDDeserializer
