---------------------
�����������		 |
---------------------
	# ���������������:partition.assignment.strategy
		* ����ֵΪ:PartitionAssignor �ӿڵ�ʵ����ȫ·��
		* ϵͳ�ṩ��Ĭ��ʵ��
			org.apache.kafka.clients.consumer.RangeAssignor(Ĭ��)
			org.apache.kafka.clients.consumer.RoundRobinAssignor
			org.apache.kafka.clients.consumer.StickyAssignor
	
	# PartitionAssignor �ӿڳ��󷽷�
		Subscription subscription(Set<String> topics);
		Map<String, Assignment> assign(Cluster metadata, Map<String, Subscription> subscriptions);
		void onAssignment(Assignment assignment);
		String name();
	
	
	# RangeAssignor
		* ���������������ͷ������������������������һ�����,Ȼ�󽫷������տ�Ƚ���ƽ������,�Ա�֤���������ܾ��ȵط�������е�������


	# RoundRobinAssignor
		* �������������������߼������߶��ĵ���������ķ��������ֵ�������
		* Ȼ��ͨ����ѯ��ʽ������������η����ÿ��������
		* ���ͬһ�������������е������ߵĶ�����Ϣ������ͬ��,��ô RoundRobinAssignor ������Եķ���������Ǿ��ȵ�

	# StickyAssignor
		* ����Ҫ������Ŀ�� 
			�����ķ���Ҫ�����ܾ��� 
			�����ķ��価�������ϴη���ı�����ͬ
		
		* �����߷�����ͻʱ,��һ��Ŀ�������ڵڶ���Ŀ��
	
	
	# ���� Kafka Ĭ�ϵ������߼��趨, һ������ ֻ�ܱ�ͬһ��������( ConsumerGroup ) �ڵ�һ������������
		* ����һ�趨���Ǿ��Ե�,����ͨ���Զ�������������ʹһ���������Է�����������������
		* ��ʵ�ַ�ʽ����һ�����ص�����,Ĭ�ϵ�����λ�Ƶ��ύ��ʧЧ
		* ���е������߶����ύ�����������λ�Ƶ� consumer_offsets ��,���ύ������λ�ƻḲ��ǰ���ύ������λ��
