---------------------
kafka-����			 |
---------------------
	# ����
		http://kafka.apache.org
	
	# �����ĵ�
		http://kafka.apachecn.org/intro.html

	# �ο�
		https://blog.csdn.net/lizhitao/article/details/39499283
		http://www.jasongj.com/tags/Kafka/
		https://www.jianshu.com/p/d3e963ff8b70

	
	# �ص�
		* ��������,�����۵Ļ�����,��������֧��100w/s��Ϣ�Ķ�д
		* ��Ϣ�־û�,������Ϣ���ᱻ�־û���Ӳ��,����Ϣ��ʧ,֧����Ϣ�ط�
		* ��ȫ�ֲ�ʽ:Producer,Broker,Consumer��֧��ˮƽ����չ
		* ͬʱ������Ӧ���������������������
		
		
---------------------
kafka-Ŀ¼�ṹ		 |
---------------------
	bin
		windows
		kafka-run-class.sh
		kafka-server-start.sh
		kafka-server-stop.sh
	logs(����ʱ�������ļ���)
		controller.log
			* KafkaController ����ʱ��־,Ĭ�� TRACE
		kafka-authorizer.log
			* Ȩ����֤��ز�����־,Ĭ�� WARN
		kafka-requests.log
			* ����������־,Ĭ�� WARN
		kafkaServer-gc.log
			* ���й���,GC����־,Ĭ�� INFO
		log-cleaner.log
			* ��־����������ͳ����Ϣ,Ĭ�� INFO
		server.log
			* KafkaServer ������־,Ĭ�� INFO
		state-change.log
			* ������ɫ�л���״̬ת����־,Ĭ�� TRACE
		
	config
		connect-console-sink.properties
		connect-console-source.properties
		connect-distributed.properties
		connect-file-sink.properties
		connect-file-source.properties
		connect-log4j.properties
		connect-standalone.properties
		consumer.properties
		log4j.properties
		producer.properties
		server.properties
		tools-log4j.properties
		trogdor.conf
		zookeeper.properties
	libs
	site-docs
		kafka_2.12-2.1.1-site-docs.tgz


---------------------
kafka ��ϢЭ��		 |
---------------------

|offset|length|CRC32|Magic|timestamp|attributes|key length|key|value length|value|

|4�ֽ�ƫ����|4�ֽڳ���|4�ֽ�CRC32|1�ֽ�ħ������|8�ֽ�ʱ���|1�ֽ�attributes(ö��)|4�ֽ�keylength|key|4�ֽ�valuelength|value|
