------------------------
kafka-manager			|
------------------------
	# github
		https://github.com/yahoo/kafka-manager

	# ����
		git clone git@github.com:yahoo/kafka-manager.git
	
	# ����
		./sbt clean dist
	
		* ����~·���´���һ�� .sbtĿ¼,�������ļ�����ڸ�Ŀ¼��Ӧ��Ŀ¼��
		* �����ʾsbt-launch.jar ����ʧ��,��ô��Ҫ�Լ��ֶ���ȥ���ظ�����,�����ϴ���ָ����Ŀ¼��
			~/.sbt/launchers/0.13.9/	
	
	# ��ѹ�ļ�:kafka-manager-1.3.3.22.zip
		unzip /kafka-manager/target/universal/kafka-manager-1.3.3.22.zip


	# ����:conf/application.conf
		kafka-manager.zkhosts="my.zookeeper.host.com:2181"
	
	# ����binĿ¼����
		nohup ./bin/kafka-manager -Dconfig.file=./conf/application.conf & 
	

	# ���ܵ����úͽ���
		application.features=["KMClusterManagerFeature","KMTopicManagerFeature","KMPreferredReplicaElectionFeature","KMReassignPartitionsFeature"]

		KMClusterManagerFeature
			* �����Kafka Manager���,����,ɾ����Ⱥ

		KMTopicManagerFeature
			* �����KafkaȺ�����,����,ɾ������

		KMPreferredReplicaElectionFeature
			* ����ΪKafkaȺ��������ѡ����ѡ��

		KMReassignPartitionsFeature
			* �������ɷ�����������·������
	
	# ���ʶ˿�Ĭ��:9000
		* ��������ʱ�޸�
		-Dhttp.port=1024
	
	# �ر�
		* ֱ��kill������,jps��������:ProdServerStart
