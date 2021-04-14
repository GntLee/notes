--------------------------
DeploymentOptions
--------------------------
	# Verticle��������


--------------------------
����
--------------------------
	public DeploymentOptions()
	public DeploymentOptions(DeploymentOptions other)
	public DeploymentOptions(JsonObject json) 


--------------------------
ʵ��
--------------------------
	public JsonObject getConfig()
	public DeploymentOptions setConfig(JsonObject config)
		* ���ø�verticle������

	public boolean isWorker()
	public DeploymentOptions setWorker(boolean worker)
		* ����Ϊ worker verticle

	public String getIsolationGroup() 
	public DeploymentOptions setIsolationGroup(String isolationGroup)

	public boolean isHa()
	public DeploymentOptions setHa(boolean ha)
		* ���ø߿��÷�ʽ��HA�����������ַ�ʽ�£���
		* ����һ�������� Vert.x ʵ���е� Verticle ͻȻ�ҵ������ Verticle �����ڼ�Ⱥ�����е���һ�� Vert.x ʵ�������²���


	public List<String> getExtraClasspath()
	public DeploymentOptions setExtraClasspath(List<String> extraClasspath)

	public int getInstances()
	public DeploymentOptions setInstances(int instances) 
		* ������ʾ������

	public List<String> getIsolatedClasses()
	public DeploymentOptions setIsolatedClasses(List<String> isolatedClasses)

	public String getWorkerPoolName()
	public DeploymentOptions setWorkerPoolName(String workerPoolName)

	public int getWorkerPoolSize()
	public DeploymentOptions setWorkerPoolSize(int workerPoolSize)

	public long getMaxWorkerExecuteTime()
	public DeploymentOptions setMaxWorkerExecuteTime(long maxWorkerExecuteTime) 

	public TimeUnit getMaxWorkerExecuteTimeUnit()
	public DeploymentOptions setMaxWorkerExecuteTimeUnit(TimeUnit maxWorkerExecuteTimeUnit)

	public ClassLoader getClassLoader()
	public DeploymentOptions setClassLoader(ClassLoader classLoader)

	public void checkIsolationNotDefined()
	public JsonObject toJson()




--------------------------
��̬
--------------------------
	public static final boolean DEFAULT_WORKER = false;
	public static final boolean DEFAULT_HA = false;
	public static final int DEFAULT_INSTANCES = 1;