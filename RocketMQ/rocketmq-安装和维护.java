-----------------------
������װ			   |
-----------------------
	# ����,��ѹ
	# ���û�������
		export ROCKETMQ_HOME=/usr/local/rocketmq/alibaba-rocketmq

	# ����nameserver
		nohup bin/mqnamesrv &

		* ��־�ļ���: ~/logs/rocketmqlogs/namesrv.log
	
	# ����broker
		nohup bin/mqbroker &
			-n 
				* ����ָ��namesrv�ĵ�ַ�Ͷ˿�(Ĭ����127.0.0.1:9876)
					-n 127.0.0.1:9876
				* ���namesrv�Ǽ�Ⱥ�Ļ�, ����ʹ�÷ֺ�(;)�ָ�
					-n 192.168.1.1:9876;192.161.2:9876
			-c
				* ָ��properties�����ļ�
					-c conf/broker.properties
			
			autoCreateTopicEnable
				* �Ƿ�����ͻ����Զ��Ĵ��������ڵ�Topic, Ĭ��Ϊ: false
					autoCreateTopicEnable=true

			
		* ��־�ļ���: ~/logs/rocketmqlogs/broker.log 
	

-----------------------
����ر�			   |
-----------------------
	# namesrv �ر�
		./bin/mqshutdown namesrv
	
	# broker�ر�
		./bin/mqshutdown broker


-----------------------
��������			   |
-----------------------
	# Ĭ�϶˿�
		mqnamesrv <-> client	:9876
		mqbroker <-> mqnamesrv	:10909
		mqbroker <-> client		:10911
	
	# �޸��ڴ��С
		* rocketmqĬ�����õ�jvm�ڴ�Ƚϴ�, �����������С��, ������ʧ��
		* �����޸Ľű�, ���޸�������jvm�ڴ�

		* �༭: runbroker.sh
			JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx256m -Xmn128m"

		* �༭: runserver.sh
			JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx256m -Xmn128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"

	
	# Windows �������쳣
		* �޸Ľű�:runbroker.cmd
			set CLASSPATH=.;%BASE_DIR%conf;"%CLASSPATH%"

