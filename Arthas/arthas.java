-----------------------
arthas				   |
-----------------------
	#  ���￪Դ��java��⹤��
	# ��վ
		https://github.com/alibaba/arthas
		https://alibaba.github.io/arthas/

	# ��װ
		wget https://alibaba.github.io/arthas/arthas-boot.jar
		
		* ���ظ�java��������
		* ��������java��ȥִ��һЩָ��, �����java����


	# ������ʽ
		arthas-boot [-h] [--target-ip <value>] [--telnet-port <value>]
		   [--http-port <value>] [--session-timeout <value>] [--arthas-home <value>]
		   [--use-version <value>] [--repo-mirror <value>] [--versions] [--use-http]
		   [--attach-only] [-c <value>] [-f <value>] [--height <value>] [--width
		   <value>] [-v] [pid]
		
	# ������صķ�ʽ
		1, ֱ��ִ�� java -jar arthas-boot.jar
			* ���г������е�java����
			* ������̵����, ��ʼ���ָ����Java����
		
		2, ����ʱֱ��ָ�����̵�pid
			* java -jar arthas-boot.jar [pid]


-----------------------
 �������			  |
-----------------------
	-h
		* ��ӡ������Ϣ