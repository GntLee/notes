--------------------------------
ribbon����						|
--------------------------------
	# feginʹ��ribbon����ɸ��ؾ���
	# ����ֱ������ribbon�ĸ�������

	# ȫ�ֵ����÷�ʽ
		ribbon.ConnectTimeout=500
		ribbon.ReadTimeout=5000

		* �����﷨:ribbon.<key>=<value>
	
	# ��Է�������÷�ʽ
		USER-SERVICE.ribbon.ConnectTimeout=500
		USER-SERVICE.ribbon.ReadTimeout=500
		USER-SERVICE.ribbon.MaxAutoRetriesNextServer=2
		USER-SERVICE.ribbon.MaxAutoRetires=1

		* �����﷨:<��������>.ribbon.<key>=<value>