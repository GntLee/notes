
----------------------------
Scheduler					|
----------------------------
	# �ӿڷ���
		Calendar getCalendar(String calName)
		boolean deleteCalendar(String calName) 
		void addCalendar(String calName, Calendar calendar, boolean replace, boolean updateTriggers)
			* ���/ɾ��һ�����ڶ���

		void addJob(JobDetail jobDetail, boolean replace)
		void addJob(JobDetail jobDetail, boolean replace, boolean storeNonDurableWhileAwaitingScheduling)
			* ���һ��trigger��job����
			* replace: 
			* storeNonDurableWhileAwaitingScheduling: 

		boolean checkExists(JobKey jobKey) 
		boolean checkExists(TriggerKey triggerKey)

		void clear() 
		
		boolean deleteJob(JobKey jobKey)
		boolean deleteJobs(List<JobKey> jobKeys)

		

		List<String> getCalendarNames()

		SchedulerContext getContext()
			* ��ȡ�����Ķ���, ����һ��Map�ṹ, String ΪKey

		void start()
			* ����

		void startDelayed(int seconds) 
			* ��ָ���������

		boolean isStarted()
			* �ж��Ƿ�����

		void standby()
			* ��ͣ�����ִ��, �����Ҫ����ִ��, ִ�з���: start();

		boolean isInStandbyMode()
			* �жϵ�ǰ��״̬�Ƿ�����ͣ��

		void shutdown()
		void shutdown(boolean waitForJobsToComplete)
			* �ر�Ӧ��, waitForJobsToComplete �����Ƿ�Ҫ�ȴ�����ִ�е�����ִ�����

		boolean isShutdown()
			* �ж��Ƿ��Ѿ��ر�

		SchedulerMetaData getMetaData()
		List<JobExecutionContext> getCurrentlyExecutingJobs()
		void setJobFactory(JobFactory factory)
			* ���� Job ʵ���Ĵ���������

		ListenerManager getListenerManager()
			* ���ؼ�����������

		Date scheduleJob(JobDetail jobDetail, Trigger trigger)
		Date scheduleJob(Trigger trigger)
		void scheduleJobs(Map<JobDetail, Set<? extends Trigger>> triggersAndJobs, boolean replace) 
		void scheduleJob(JobDetail jobDetail, Set<? extends Trigger> triggersForJob, boolean replace)

		boolean unscheduleJob(TriggerKey triggerKey)
		boolean unscheduleJobs(List<TriggerKey> triggerKeys)
			* �Ƴ�ָ����Trigger�Լ�job

		Date rescheduleJob(TriggerKey triggerKey, Trigger newTrigger)

		void addJob(JobDetail jobDetail, boolean replace)
		void addJob(JobDetail jobDetail, boolean replace, boolean storeNonDurableWhileAwaitingScheduling)
			* ���job�� Scheduler, �Ա�ʹ��

		boolean deleteJob(JobKey jobKey)
		boolean deleteJobs(List<JobKey> jobKeys)
			* �Ƴ�ָ����join

		void triggerJob(JobKey jobKey)
		void triggerJob(JobKey jobKey, JobDataMap data)
		void pauseJob(JobKey jobKey)
		void pauseJobs(GroupMatcher<JobKey> matcher)
		void pauseTrigger(TriggerKey triggerKey)
		void pauseTriggers(GroupMatcher<TriggerKey> matcher) 
		void resumeJob(JobKey jobKey)
		void resumeJobs(GroupMatcher<JobKey> matcher)
		void resumeTrigger(TriggerKey triggerKey)
		void resumeTriggers(GroupMatcher<TriggerKey> matcher) 
		void pauseAll()
		void resumeAll()
		List<String> getJobGroupNames() 
		Set<JobKey> getJobKeys(GroupMatcher<JobKey> matcher)
		List<? extends Trigger> getTriggersOfJob(JobKey jobKey)
		List<String> getTriggerGroupNames()
		Set<TriggerKey> getTriggerKeys(GroupMatcher<TriggerKey> matcher)
		Set<String> getPausedTriggerGroups()
		JobDetail getJobDetail(JobKey jobKey)
		Trigger getTrigger(TriggerKey triggerKey)
		TriggerState getTriggerState(TriggerKey triggerKey)
		void resetTriggerFromErrorState(TriggerKey triggerKey)
		void addCalendar(String calName, Calendar calendar, boolean replace, boolean updateTriggers)
		boolean deleteCalendar(String calName)
		Calendar getCalendar(String calName)
		List<String> getCalendarNames()
		boolean interrupt(JobKey jobKey)
		boolean interrupt(String fireInstanceId)
		boolean checkExists(JobKey jobKey)
		boolean checkExists(TriggerKey triggerKey)
		void clear()



----------------------------
SchedulerFactory			|
----------------------------
	# ������ӿ�,  ���𴴽�Schedulerʵ������
		Scheduler getScheduler() throws SchedulerException;
		Scheduler getScheduler(String schedName) throws SchedulerException;
		Collection<Scheduler> getAllSchedulers() throws SchedulerException;

	# ʵ������
		DirectSchedulerFactory
		StdSchedulerFactory
	
	# StdSchedulerFactory ʵ������
		void initialize()
		void initialize(InputStream propertiesStream)
		void initialize(String filename) 
		void initialize(Properties props)
			* �������ó�ʼ��
	


	
----------------------------
StdSchedulerFactory			|
----------------------------
	# ��̬����
		Scheduler getDefaultScheduler();
			* ����Ĭ�ϵ� Scheduler
			* new StdSchedulerFactory().getScheduler();

		
----------------------------
DirectSchedulerFactory		|
----------------------------