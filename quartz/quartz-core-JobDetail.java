--------------------------------
JobDetail						|
--------------------------------
	# �ӿڷ���
		public JobKey getKey();
		public String getDescription();
		public Class<? extends Job> getJobClass();
		public JobDataMap getJobDataMap();
		public boolean isDurable();
		public boolean isPersistJobDataAfterExecution();
		public boolean isConcurrentExectionDisallowed();
		public boolean requestsRecovery();
		public Object clone();
		public JobBuilder getJobBuilder();

--------------------------------
JobBuilder						|
--------------------------------
	# ���ڹ��� JobDetail �Ĺ�����

	# ��̬����
		static JobBuilder newJob()
		static JobBuilder newJob(Class <? extends Job> jobClass)
	
	# ʵ������
		JobDetail build()
		JobBuilder ofType(Class <? extends Job> jobClazz)
			* ָ��Jobʵ����

		JobBuilder requestRecovery()
		JobBuilder requestRecovery(boolean jobShouldRecover)
			* ���һ��job�ǿɻָ���, ��������ִ�е�ʱ��, scheduler����Ӳ�ر�(hard shutdown), �������еĽ��̱����ˣ����߹ػ���)
			* ��scheduler����������ʱ��, ��job�ᱻ����ִ��
			* Ĭ�� true
			* ��ʱ, ��job�� JobExecutionContext.isRecovering() ����true


		JobBuilder storeDurably()
		JobBuilder storeDurably(boolean jobDurability)
			* ���һ��job�Ƿǳ־õ�, ��û�л�Ծ��trigger��֮������ʱ��, �ᱻ�Զ��ش�scheduler��ɾ��
			* Ҳ����˵, �ǳ־õ�job������������trigger�Ĵ�����������
			* Ĭ��: true


		JobBuilder setJobData(JobDataMap newJobDataMap)

		JobBuilder usingJobData(JobDataMap newJobDataMap)
		JobBuilder usingJobData(String dataKey, Boolean value)
		JobBuilder usingJobData(String dataKey, Double value)
		JobBuilder usingJobData(String dataKey, Float value)
		JobBuilder usingJobData(String dataKey, Integer value)
		JobBuilder usingJobData(String dataKey, Long value)
		JobBuilder usingJobData(String dataKey, String value)
		
		JobBuilder withDescription(String jobDescription)

		JobBuilder withIdentity(JobKey jobKey)

		JobBuilder withIdentity(String name)
		JobBuilder withIdentity(String name, String group)
			* name, ��ʾjobΨһ������, �����������, ���Ĭ������һ��Ĭ�ϵ�: 6da64b5bd2ee-05f824d5-50e9-438b-b72b-a2350c08ee65
			* group, ��ʾjob�����ķ���
			
			* ���û���� group, Ĭ��Ϊ: DEFAULT



--------------------------------
JobKey							|
--------------------------------
	# ����������һ��job��name��groups����, ���̳���: Key
		class JobKey extends Key<JobKey> 

	# ���췽��
		public JobKey(String name) {
		public JobKey(String name, String group) {
	
	# ��̬�Ĺ�������
		public static JobKey jobKey(String name) {
		public static JobKey jobKey(String name, String group) 
	
	# ʵ������(��Key�̳�)
		String getName()
		String getGroup()
	
	# Key�ľ�̬����
		static String createUniqueName(String group)
			* ����һ��Ψ��group����
			* Դ��
			    public static String createUniqueName(String group) {
					if(group == null)
						group = DEFAULT_GROUP;
					
					String n1 = UUID.randomUUID().toString();
					String n2 = UUID.nameUUIDFromBytes(group.getBytes()).toString();
					
					return String.format("%s-%s", n2.substring(24), n1);
				}
		
