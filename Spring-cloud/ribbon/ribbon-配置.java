------------------------
ribbon����				|
------------------------
	# ȫ�ֵ����÷�ʽ
		ribbon.ConnectTimeout=500
		ribbon.ReadTimeout=5000

		* �����﷨:ribbon.<key>=<value>
	
	# ��Է�������÷�ʽ
		USER-SERVICE.ribbon.ConnectTimeout=500
		USER-SERVICE.ribbon.ReadTimeout=500

		* �����﷨:<��������>.ribbon.<key>=<value>

------------------------
ribbon����-��			|
------------------------

ribbon:
  eureka:
    # �Ƿ����Eureka��Ribbon����ʵ����ʵ��
    enabled: true