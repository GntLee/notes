--------------------------------
quartz	����					|
--------------------------------
	# ��������
		<dependency>
		   <groupId>org.springframework.boot</groupId>
		   <artifactId>spring-boot-starter-quartz</artifactId>
		</dependency>
	
	# ����:QuartzProperties(������)
		spring:
		  quartz:
			job-store-type:
				* �洢����
				* ö��:MEMORY(Ĭ��),JDBC
			scheduler-name:
				* ��Ⱥ������(������������ν)
			auto-startup: true
				* ��ʼ��������ִ��
			startup-delay: 0
				* ϵͳ�������ӳٶ�ÿ�ʼִ�ж�ʱ����
			wait-for-jobs-to-complete-on-shutdown: false
				* ��ϵͳ�رյ�ʱ��, �Ƿ�ȴ�����ִ�����
			overwrite-existing-jobs: false
				* �Ƿ�Ҫ�����Ѿ����ڵ������¼
			properties: 
				* �Զ���ĸ߼�Quartz��������
			jdbc:
			  schema: classpath:org/quartz/impl/jdbcjobstore/tables_@@platform@@.sql
				* �����Ľ������, Ĭ��ֵ: classpath:org/quartz/impl/jdbcjobstore/tables_@@platform@@.sql
				* ��jar��Ŀ¼: org/quartz/impl/jdbcjobstore ��, ����DB�Ĳ�ͬ, SQL�ļ�Ҳ��ͬ
			  initialize-schema: EMBEDDED
				* �����Ĵ�������(ö��):
					EMBEDDED(Ĭ��)	���ݿ��Ѿ��ֶ���ʼ��, ����Ҫ������г�ʼ��
					ALWAYS			��ÿ������ʱ��ʼ��
					NEVER			����ʼ��
			  comment-prefix: --
				* ����SQLע�͵�ǰ׺
	

	# ����ͨ����������, ʵ�ֽӿ�: SchedulerFactoryBeanCustomizer
		void customize(SchedulerFactoryBean schedulerFactoryBean);

	
	# �������Դ��ʱ��, ��ҪΪָ�����ڴ���ʱ���������Դ�����ʶ: @QuartzDataSource
		@Bean
		@QuartzDataSource
		public Datasource datasource(){}
	
	# ���ݱ��˵��
		qrtz_blob_triggers
			* ��Blob ���ʹ洢�Ĵ�������
		qrtz_calendars
			* ���������Ϣ, quartz������һ��������ָ��һ��ʱ�䷶Χ
		qrtz_cron_triggers
			* ���cron���͵Ĵ�����
		qrtz_fired_triggers
			* ����Ѵ����Ĵ�����
		qrtz_job_details
			* ���һ��jobDetail��Ϣ
		qrtz_locks
			* �洢����ı���������Ϣ(����ʹ���˱�����)
		qrtz_paused_trigger_graps
			* �����ͣ���Ĵ�����
		qrtz_scheduler_state
			* ������״̬
		qrtz_simple_triggers
			* �򵥴���������Ϣ
		qrtz_triggers
			* �������Ļ�����Ϣ
	
	# �Զ���ĸ߼�Quartz��������
org:
  quartz:
	scheduler:
	  instanceName: clusteredScheduler 
		* ������ʵ������
	  instanceId: AUTO 
		* ������ʵ������Զ�����
	jobStore:
	  class: org.quartz.impl.jdbcjobstore.JobStoreTX 
		* �־û���ʽ����
	  driverDelegateClass: org.quartz.impl.jdbcjobstore.StdJDBCDelegate 
		* �־û���ʽ��������������MySQL���ݿ�
	  tablePrefix: qrtz_ 
		* quartz������ݱ�ǰ׺��
	  isClustered: true 
		* �����ֲ�ʽ����
	  clusterCheckinInterval: 10000 
		* �ֲ�ʽ�ڵ���Ч�Լ��ʱ��������λ������
	  useProperties: false 
		* ��ʾJDBCJobStore JobDataMaps�е�����ֵ�����ַ���

	threadPool:
	  class: org.quartz.simpl.SimpleThreadPool 
		* �̳߳�ʵ����
	  threadCount: 10 
		* ִ����󲢷��߳�����
	  threadPriority: 5 
		* �߳����ȼ�
	  threadsInheritContextClassLoaderOfInitializingThread: true 
		* �����Ƿ������Զ��������ݿ��ڵĶ�ʱ����Ĭ��true

--------------------------------
quartz	����					|
--------------------------------
	# ������̳�:QuartzJobBean 
		
	# JobDetail
	# Calendar
	# Trigger
