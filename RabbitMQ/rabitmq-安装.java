-------------------------------
RabbitMQ-��װ Windows			|
-------------------------------
	# ��װע��
		* ����ʹ��Ĭ��·��
		* ϵͳ�û���������Ӣ��
		* ��װ·�����ܳ���Ӣ��
		* �������������Ӣ��
		* ��ǰ��װ�û������ǹ���Ա

	1,�����ص�ַ,����Windos�汾(exe����)
		https://github.com/rabbitmq/rabbitmq-server/releases

		* �ҵ�EXE�ļ�����
			https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.8.18/rabbitmq-server-3.8.18.exe

	2,��װErlang����
		* ���ص�ַ http://www.erlang.org/downloads(����windows�汾,exeִ�г���)
		* ����ϵ��,ȫ��Ĭ��.һֱ��һ��
		* ���ܳ��ֵ��쳣
			Erlang OPT XX Setup
			Error opening file for writing
			...
			* �������,���Լ���
	
	4,��װRabbitMQ
		* ��һ������,ȫ����(Ĭ��)
		* �ڶ�������Ĭ��·��
		* ɵ��ʽ����һ��
	
	5,�򿪿���̨
		* �����Wind7,ֱ�ӽ��밲װĿ¼.�ҵ�:RabbitMQ Command Prompt ���cmd,���м���
		* �����Win8--��
		* ���˵û�����ҵ�?(���鷽��)
			1,���밲װ·��
				C:\Program Files\RabbitMQ Server\rabbitmq_server-3.6.5\sbin
				* �����Ŀ¼,�Թ���Ա��ݴ�CMD,���CMD�������Ǹ�Ҳ��һ����
	
	6,ִ������
		rabbitmq-plugins enable rabbitmq_management
		* ��������
		* ������������OK
			Applying plugin configuration to rabbit@DESKTOP-UHNU5C4... started 6 plugins.
	
	
	7,�������
		http://127.0.0.1:15672
		
	8,ʹ��Ĭ���˻���¼
		guest/guest
		* �������Ǹ�����Ȩ��,��ʵ��RabbitMQ������Ȩ��

	9,�����¼�ɹ�,���ʾ��װ�ɹ�

	# ���˵ Windows ��װʧ��,�ͳ����� Linux,����ʹ�ñ����ṩ��MQ����

	
-------------------------------
RabbitMQ-��װ Linux				|
-------------------------------
	# ���Ƽ���ʹ��packagecloud��װ
		curl -s https://packagecloud.io/install/repositories/rabbitmq/rabbitmq-server/script.rpm.sh | sudo bash
			* ����Package Cloud�ṩ��RabbitMQ Server���ٰ�װ�ű�

		curl -s https://packagecloud.io/install/repositories/rabbitmq/erlang/script.rpm.sh | sudo bash
			* ����Package Cloud�ṩErlang�������ٰ�װ�ű�

		yum  -y install erlang
			* ʹ��yum��װErlang����

		yum install socat logrotate -y
			* ��װsocat, logrotate����

		yum install -y rabbitmq-server
			* ʹ��yum��װRabbitMQ Server
	
	# �����ļ�
		* ��Ҫ�Լ�����
		* �����ļ�����Ŀ¼: /etc/rabbitmq
		
		* �����ļ�
			rabbitmq.conf
			advanced.config
			rabbitmq-env.conf(rabbitmq-env.conf.bat(windows))
		
		
		* �ο�
			https://rabbitmq.com/configure.html
		
	
	# ά��
		systemctl start rabbitmq-server
		systemctl stop rabbitmq-server
		systemctl restart rabbitmq-server
		systemctl status rabbitmq-server
		systemctl enable rabbitmq-server
		systemctl disable rabbitmq-server
	
	# ��װWEB���Ʋ��
		rabbitmq-plugins enable rabbitmq_management


		* ��װ�󣬿�������һ�·���
		* �����ַ
			http://host:15672

		* Ĭ���˻�������
			guest
			guest
		
		* Ĭ��ֻ����ͨ��localhost��¼

		* ����µ��˻����е�¼
			* ����û�������
				rabbitmqctl add_user admin 123456

			* ����administrator(��������Ա)��ɫ
				rabbitmqctl set_user_tags admin administrator
			
			* ����Ȩ�ޣ�����virtual hosts="/" ��������Դ���õĶ�дȨ��
				rabbitmqctl set_permissions -p "/" admin ".*" ".*" ".*"

				* �鿴����Ȩ�޵�������ϸ��Ϣ	
					rabbitmqctl help set_permissions  
		

---------------------------------
Docker ��װ
---------------------------------
	# Docker
		https://registry.hub.docker.com/_/rabbitmq/
	

