-----------------------------
admin					     |
-----------------------------
	# Maven
		<dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka_2.12</artifactId>
			<version>2.1.1</version>
		</dependency>
	
	# ������ shell �ű�ִ������ʱʵ��ִ�е���
	# ����ֱ��ִ����Щ���main����,���ڶ�kafka��ά��
		String commands[] = new String[] {
			"--zookeeper","192.168.2.102:2181",
			"--create",
			"--partitions","1",
			"--replication-factor","1",
			"--topic","topic-demo"
		};
		TopicCommand.main(commands);
	
	# ��ص����
		AclCommand
			* kafka-acls.sh �ű�ʹ�õ���
		
		TopicCommand
			* kafka-topics.sh �ű�ʹ�õ���
		
		KafkaAdminClient
			* һ�������������,�������ڹ���broker,topic,acl
