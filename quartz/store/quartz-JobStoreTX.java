-------------------------
JobStoreTX				 |
-------------------------
	# ʹ��jdbc��JobStroe


-------------------------
JobStor����				 |
-------------------------
org.quartz.jobStore.driverDelegateClass				yes		string	null
	* ָ��jdbc������, ��ѡ��������
		org.quartz.impl.jdbcjobstore.StdJDBCDelegate(������ȫ����JDBC����������)
		org.quartz.impl.jdbcjobstore.MSSQLDelegate(����Microsoft SQL Server��Sybase)
		org.quartz.impl.jdbcjobstore.PostgreSQLDelegate
		org.quartz.impl.jdbcjobstore.WebLogicDelegate(����WebLogic��������)
		org.quartz.impl.jdbcjobstore.oracle.OracleDelegate
		org.quartz.impl.jdbcjobstore.oracle.WebLogicOracleDelegate(����Weblogic��ʹ�õ�Oracle��������)
		org.quartz.impl.jdbcjobstore.oracle.weblogic.WebLogicOracleDelegate(����Weblogic��ʹ�õ�Oracle��������)
		org.quartz.impl.jdbcjobstore.CloudscapeDelegate
		org.quartz.impl.jdbcjobstore.DB2v6Delegate
		org.quartz.impl.jdbcjobstore.DB2v7Delegate
		org.quartz.impl.jdbcjobstore.DB2v8Delegate
		org.quartz.impl.jdbcjobstore.HSQLDBDelegate
		org.quartz.impl.jdbcjobstore.PointbaseDelegate
		org.quartz.impl.jdbcjobstore.SybaseDelegate

org.quartz.jobStore.dataSource						yes		string	null
	* ָ������Դ������

org.quartz.jobStore.tablePrefix						no		string	"QRTZ_"
	* ���ݱ��ǰ׺

org.quartz.jobStore.useProperties					no		boolean	false
	* ��ʾJDBCJobStore JobDataMaps�е�����ֵ�����ַ���, ��˿��Խ���洢Ϊ����-ֵ��, �����ǽ������ӵĶ����������л���ʽ�洢��BLOB����
	* ��ܷ���, ��Ϊ�����˽���String�����л�ΪBLOB�����������汾��������

org.quartz.jobStore.misfireThreshold				no		int		60000
	* ���ȳ����ڱ���Ϊ��"�󴥷�"֮ǰ��"����"������������һ�δ���ʱ��ĺ�����

org.quartz.jobStore.isClustered						n		boolean	false
	* ����Ϊ "true" �Դ�Ⱥ������
	* ������Quartzʵ��ʹ��ͬһ�����ݿ��, ����뽫����������Ϊ: true

org.quartz.jobStore.clusterCheckinInterval			no		long	15000
	* �ڵ�֮ǰ�ļ����

org.quartz.jobStore.maxMisfiresToHandleAtATime		no		int		20
org.quartz.jobStore.dontSetAutoCommitFalse			no		boolean	false
	* ���������Ƿ��Զ��ύ

org.quartz.jobStore.selectWithLockSQL				no		string	"SELECT * FROM {0}LOCKS WHERE SCHED_NAME = {1} AND LOCK_NAME = ? FOR UPDATE"
	* ʹ�õļ������

org.quartz.jobStore.txIsolationLevelSerializable	no		boolean	false
	* �Ƿ�����������뼶��Ϊ: ���л�

org.quartz.jobStore.acquireTriggersWithinLock		no		boolean	false (or true - see doc below)
	* ����һ��Trigger��ʱ��, �Ƿ����

org.quartz.jobStore.lockHandler.class				no		string	null
org.quartz.jobStore.driverDelegateInitString		no		string	null
	* ���ø�����Դ�����Ӳ���, �Ƿ� | �ָ�
		"settingName=settingValue|otherSettingName=otherSettingValue|..."



-------------------------
����Դ����				 |
-------------------------
org.quartz.jobStore.dataSource = NAME
	* ����Դ������, ��Ҫ������ 'org.quartz.jobStore.dataSource' ������

org.quartz.dataSource.NAME.driver							yes	String	null
	* jdbc����

org.quartz.dataSource.NAME.URL								yes	String	null
	* ���ӵ�url

org.quartz.dataSource.NAME.user								no	String	""
org.quartz.dataSource.NAME.password							no	String	""
	* �û���������

org.quartz.dataSource.NAME.maxConnections					no	int	10
	* �������������

org.quartz.dataSource.NAME.validationQuery					no	String	null
	* ���Եļ������

org.quartz.dataSource.NAME.idleConnectionValidationSeconds	no	int	50
	* �������Ӳ���֮�������
	* ������������֤��ѯ����(validationQuery)�������

org.quartz.dataSource.NAME.validateOnCheckout				no	boolean	false
	* ʹ��ǰ, �Ƿ������ӵĿ�����

org.quartz.dataSource.NAME.discardIdleConnectionsSeconds	no	int	0 (disabled)
	* ���ӵĿ����ͷ�ʱ��, Ĭ��0, ��ʾ���ͷ�

