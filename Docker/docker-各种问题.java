------------------------
ʱ������				|
------------------------
	# ������ʱ������� Dockerfile ��������
		ENV TZ=Asia/Shanghai
		RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
	
	# �Ѿ������õ�����,���Խ����ڲ�ȥִ�������޸�
		* �� /etc/profile ����ӱ���
			export TZ=Asia/Shanghai
			
		* ִ������
			ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

		* ��������