----------------------
Partitioner			  |
----------------------
	# �������ӿ�
	# ���󷽷�
		void configure(Map<String, ?> configs)
			* ��ȡ������Ϣ�Լ���ʼ������

		int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster);
			* ��������ķ����߼�

		void close();
	
	# �ṩ��ʵ����
		DefaultPartitioner