-----------------------
�˵�					|
-----------------------
	# ��ص�������
		WebEndpointProperties

	# ִ�ж˵�����˺ܶ����õ�, Ҳ�����Զ���˵���Ϣ	
		* ÿ���˵㶼���Խ��û�������
		* Ϊ�˿���Զ�̷���, �˵㻹����ͨ��: JMX/HTTP����

		* �����Ӧ�ó���ѡ��HTTP, ���Ҷ˵��id���Ƿ��ʵ�url
		* ���úͱ�¶����������


-----------------------
�˵�					|
-----------------------
	ID					����																															Ĭ������
	auditevents			��ʾ��ǰӦ�ó��������¼���Ϣ																									Yes
	beans				��ʾһ��Ӧ��������Spring Beans�������б�																						Yes
	caches
	conditions			��ʾ��������Զ�������(configuration and auto-configuration classes)��״̬�����Ǳ�Ӧ�û�δ��Ӧ�õ�ԭ��							Yes
	configprops			��ʾһ���� @ConfigurationProperties �ļ����б�																					Yes
	env					��ʾ����Spring�� ConfigurableEnvironment������																					Yes
	flyway				��ʾ���ݿ�Ǩ��·��,����еĻ�																									Yes
	health				��ʾӦ�õĽ�����Ϣ(��ʹ��һ��δ��֤���ӷ���ʱ��ʾһ���򵥵�'status',ʹ����֤���ӷ�������ʾȫ����Ϣ����)							Yes
	httptrace			��ʾHTTP������Ϣ(Ĭ�������, ���100��HTTP����-��Ӧ����)																		Yes
		* ��Ҫ����һ�� HttpTraceRepository bean
	info				��ʾ�����Ӧ����Ϣ																												Yes
	integrationgraph	��ʾSpring Integrationͼ
	loggers				��ʾ�����޸ĳ����е���־��¼��
	liquibase			չʾ�κ�Liquibase���ݿ�Ǩ��·��������еĻ�																						Yes
	metrics				չʾ��ǰӦ�õ�metrics��Ϣ																										Yes
	mappings			��ʾһ������@RequestMapping·���ļ����б�																						Yes
	scheduledtasks		��ʾӦ�ó����еļƻ�����																										Yes
	sessions			�����Spring�Ự֧�ֵĻỰ�洢�м�����ɾ��(retrieval and deletion)�û��Ự														Yes
	shutdown			����Ӧ�������ŵķ�ʽ�ر�(Ĭ������²�����)																						No
	threaddump			ִ��һ���߳�dump																												Yes

	heapdump			����һ��GZipѹ����hprof��dump�ļ�																								Yes
	jolokia				ͨ��HTTP��¶JMX beans(��Jolokia����·����ʱ��WebFlux������)																		Yes
	logfile				������־�ļ�����(���������logging.file��logging.path���ԵĻ�),֧��ʹ��HTTP Rangeͷ������־�ļ����ݵĲ�����Ϣ					Yes
	prometheus			�Կ��Ա�Prometheus������ץȡ�ĸ�ʽ��ʾmetrics��Ϣ																				Yes

