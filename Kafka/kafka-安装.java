
--------------------------------
kafka �����İ�װ				|
--------------------------------
	# ����,��ѹ
	# ����ZKServer
		* kafka�Դ���һ��Zookeeper
			lib
			|-zookeeper-3.4.13.jar
			config
			|-zookeeper.properties
		
		zookeeper-server-start.bat ../../config/zookeeper.properties
	
	# ����Kafka
		kafka-server-start.bat ../../config/server.properties
		

--------------------------------
kafka ��װ����					|
--------------------------------
	# ����: �Ҳ������޷��������� Files\Java\jdk1.8.0_171\lib\dt.jar;C:\Program
		* ԭ������Ϊ��ǰ��Java����ʹ�õ���jdk������jre(����˵��...)
		* �޸��ļ�:kafka-run-class.bat(Linux�Ļ��޸� sh�ļ�)
		* �޸� COMMAND ����ֵ(ʹ��remע��ԭ����,Ȼ����������,���ǰ� %CLASSPATH% �����һ��˫����)

		rem set COMMAND=%JAVA% %KAFKA_HEAP_OPTS% %KAFKA_JVM_PERFORMANCE_OPTS% %KAFKA_JMX_OPTS% %KAFKA_LOG4J_OPTS% -cp %CLASSPATH% %KAFKA_OPTS% %*
		set COMMAND=%JAVA% %KAFKA_HEAP_OPTS% %KAFKA_JVM_PERFORMANCE_OPTS% %KAFKA_JMX_OPTS% %KAFKA_LOG4J_OPTS% -cp "%CLASSPATH%" %KAFKA_OPTS% %*

