----------------------------
ProducerInterceptor<K, V> 	|
----------------------------
	# ��Ϣ�������ӿ�
	# ���󷽷�
		void configure(Map<String, ?> configs);
		public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record);
			* ��Ϣ����֮ǰִ��

		public void onAcknowledgement(RecordMetadata metadata, Exception exception);
			* ��Ϣ�������,�õ���Ӧ֮����api����֮ǰִ�� 

		public void close();