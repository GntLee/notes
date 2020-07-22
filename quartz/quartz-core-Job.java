

--------------------------------
Job								|
--------------------------------
	# �����ִ�нӿ�
		void execute(JobExecutionContext context)   throws JobExecutionException;
	
	# ��Ҫʵ�����ṩһ���޲ι�����

	# ��״̬��Job
		* ÿ��ִ��, ���ᴴ���µ� Job �����Լ� JobDataMap ����ִ��
		* ��Ҫ�洢����ʵ������
	
	# ��״̬��Job
		* �� Job ��ʵ�ֽӿ������ע��: @PersistJobDataAfterExecution
		* ÿ��ִ��, ���ᴴ���µ�Job����, ���ǲ��ᴴ���µ� JobDataMap
		* ��ô����ͨ�� JobDataMap �洢һЩ״̬ 
		
	# Jobʵ�ֵ��Զ�ע��
		* ʹ��Ĭ�ϵ�JobFactory: 'org.quartz.simpl.PropertySettingJobFactory' 
		* ����Job���󴴽���Ϻ�, ������setter����, ע���� JobDataMap �е����Ե�ʵ��

--------------------------------
JobExecutionContext				|
--------------------------------
	# jobִ��ʱ�������Ļ���

	# �ӿڷ���
		public Scheduler getScheduler();
		public Calendar getCalendar();
		public boolean isRecovering();
			* ���һ��job�ǿɻָ���, ��������ִ�е�ʱ��, scheduler����Ӳ�ر�(hard shutdown), �������еĽ��̱����ˣ����߹ػ���)
			* ��scheduler����������ʱ��, ��job�ᱻ����ִ��
			* ��ʱ, ��job�� JobExecutionContext.isRecovering() ����true


		public TriggerKey getRecoveringTriggerKey() throws IllegalStateException;
		public int getRefireCount();
		public JobDataMap getMergedJobDataMap();
			* ��JobDetail�е�JobDataMap �� Trigger�е�JobDataMap �ϲ��󷵻�
			* ���������ͬ������, ����߻Ḳ��ǰ�ߵ�ֵ

		public JobDetail getJobDetail();
		public Job getJobInstance();
		public Date getFireTime();
		public Date getScheduledFireTime();
		public Date getPreviousFireTime();
		public Date getNextFireTime();
		public String getFireInstanceId();
		public Object getResult();
		public void setResult(Object result);
		public long getJobRunTime();
		public void put(Object key, Object value);
		public Object get(Object key);

--------------------------------
JobFactory						|
--------------------------------
	# ���� Job ʵ���Ĺ�����ӿ�
	# Ĭ��ʹ�õĹ���ʵ��
		* ����ֻ�ǵ����� newInstance() ����
		* Ȼ��ӳ��Ե���ʵ����JobDataMap�е�key��setter����

	# �����ӿڷ���
		Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException;
	
	# ���Դ� TriggerFiredBundle �����ȡ��һЩ����
		private JobDetail job;
		private OperableTrigger trigger;
		private Calendar cal;
		private boolean jobIsRecovering;
		private Date fireTime;
		private Date scheduledFireTime;
		private Date prevFireTime;
		private Date nextFireTime;