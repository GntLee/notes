------------------------
Listener				|
------------------------
	# ͨ�� Scheduler ��ȡ�� ListenerManager �����������
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        ListenerManager listenerManager = scheduler.getListenerManager();
	
	# ListenerҲ����ͨ�� quartz.properties ��ʽ����

------------------------
ListenerManager			|
------------------------
	# ����
		public void addJobListener(JobListener jobListener);
		public void addJobListener(JobListener jobListener, Matcher<JobKey> matcher);
		public void addJobListener(JobListener jobListener, Matcher<JobKey> ... matchers);
		public void addJobListener(JobListener jobListener, List<Matcher<JobKey>> matchers);

		public boolean addJobListenerMatcher(String listenerName, Matcher<JobKey> matcher);
		public boolean removeJobListenerMatcher(String listenerName, Matcher<JobKey> matcher);
		public boolean setJobListenerMatchers(String listenerName, List<Matcher<JobKey>> matchers);
		public List<Matcher<JobKey>> getJobListenerMatchers(String listenerName);
		public boolean removeJobListener(String name);
		public List<JobListener> getJobListeners();
		public JobListener getJobListener(String name);
			* ����JobListener

		public void addTriggerListener(TriggerListener triggerListener);
		public void addTriggerListener(TriggerListener triggerListener, Matcher<TriggerKey> matcher);
		public void addTriggerListener(TriggerListener triggerListener, Matcher<TriggerKey> ... matchers);
		public void addTriggerListener(TriggerListener triggerListener, List<Matcher<TriggerKey>> matchers);

		public boolean addTriggerListenerMatcher(String listenerName, Matcher<TriggerKey> matcher);
		public boolean removeTriggerListenerMatcher(String listenerName, Matcher<TriggerKey> matcher);
		public boolean setTriggerListenerMatchers(String listenerName, List<Matcher<TriggerKey>> matchers);
		public List<Matcher<TriggerKey>> getTriggerListenerMatchers( String listenerName);
		public boolean removeTriggerListener(String name);
		public List<TriggerListener> getTriggerListeners();
		public TriggerListener getTriggerListener(String name);
			* ����TriggerListener

		public void addSchedulerListener(SchedulerListener schedulerListener);
		public boolean removeSchedulerListener(SchedulerListener schedulerListener);
		public List<SchedulerListener> getSchedulerListeners();
			* ����SchedulerListener
	
	# Matcher, ���ڹ��˼����Ķ���: Matcher<T extends Key<?>>
		boolean isMatch(T key);
		public int hashCode();
		public boolean equals(Object obj);
	
	# Matcher �߱��ܶ�����, �������ڸ�������
		AndMatcher				and ��ϵ
		EverythingMatcher		����
		KeyMatcher				����keyƥ��
		NotMatcher				not ƥ��
		OrMatcher				or ƥ��
		StringMatcher			
			|-GroupMatcher		����groupƥ��
			|-NameMatcher		����nameƥ��

------------------------
JobListener				|
------------------------
	# ����
		String getName();
		void jobToBeExecuted(JobExecutionContext context);
		void jobExecutionVetoed(JobExecutionContext context);
		void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException);

------------------------
TriggerListener			|
------------------------
	# ����
		String getName();
		void triggerFired(Trigger trigger, JobExecutionContext context);
		boolean vetoJobExecution(Trigger trigger, JobExecutionContext context);
		void triggerMisfired(Trigger trigger);
		void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode);

------------------------
SchedulerListener		|
------------------------
	# ����
		void jobScheduled(Trigger trigger);
		void jobUnscheduled(TriggerKey triggerKey);
		void triggerFinalized(Trigger trigger);
		void triggerPaused(TriggerKey triggerKey);
		void triggersPaused(String triggerGroup);
		void triggerResumed(TriggerKey triggerKey);
		void triggersResumed(String triggerGroup);
		void jobAdded(JobDetail jobDetail);
		void jobDeleted(JobKey jobKey);
		void jobPaused(JobKey jobKey);
		void jobsPaused(String jobGroup);
		void jobResumed(JobKey jobKey);
		void jobsResumed(String jobGroup);
		void schedulerError(String msg, SchedulerException cause);
		void schedulerInStandbyMode();
		void schedulerStarted();
		void schedulerStarting();
		void schedulerShutdown();
		void schedulerShuttingdown();
		void schedulingDataCleared();
