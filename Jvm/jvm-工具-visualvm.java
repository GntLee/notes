---------------------
visualvm			 |
---------------------
	# ������ 1.6Update���״η���, ������Sun(Oracle)�����ƶ��Ķ��һ���ϴ�����
	# �Ѿ���JDK�������, ��Ϊ��������Ŀ
		https://visualvm.github.io/
	
	# ����Ӧ�ó����ʵ������Ӱ���С, ���Կ���ֱ��Ӧ��������������


---------------------
���� jstatd Զ�̼�� |
---------------------
	# �ڷ����JDK��binĿ¼���½�  jstatd.all.policy �ļ�
		* ��ʵĿ¼�����Լ�ѡ��
		* ���������������JSTATD:

	����grant codebase "file:${java.home}/../lib/tools.jar" {
	��������permission java.security.AllPermission;
	����};

	# ��������� jstatd 
		jstatd -J-Djava.security.policy=jstatd.all.policy -p 1024 &

		jstatd -J-Djava.security.policy=jstatd.all.policy -J-Djava.rmi.server.hostname=172.26.13.178 -J-Djava.rmi.server.logCalls=true -p 1024 &
		
		-J-Djava.security.policy=jstatd.all.policy 
			* ָ��·��
		-J-Djava.rmi.server.logCalls=true
			* ��ӡ��־
		-J-Djava.rmi.server.hostname=192.168.19.114
			* ָ����������������Ӧ�ú��� hostname -i ����ִ�г����Ľ��һ��


	# ѡ��
		-nr
			* ��û���ҵ����е�RMIע���ʱ, ���᳢����jstatd�����д����ڲ�RMIע���

		-p [port]	
			* ϣ���ҵ�RMIע���Ķ˿ں�, �������δ�ҵ�, ����δָ��-nr������´���RMIע���

		-n [rminame]
			* Զ��RMI������RMIע����а󶨵�����
			* Ĭ��������JStatRemoteHost
			* ������jstatd��������ͬһ����������,ͨ��ָ����ѡ��,����ʹÿ��������������RMI���������Ψһ, ����,����������Ҫ��֤�ڼ��ӿͻ��˵�hostid��vmid�д���Ψһ�ķ���������

		-J [option]
			* ��ѡ��ݸ�java����
			* ����:-J-Xms48m�������ڴ�����Ϊ48���ֽ�, -Jͨ����һ��ͨ�õ�Լ��ִ����Java��д��Ӧ�ó���ĵײ�VMѡ��

