
-------------------------
ApplicationPidFileWriter |
-------------------------
	# ��sbӦ��������, ���ڰѽ���idд�뵽�ļ��ļ�����

	# ���ü�����
		SpringApplication springApplication = new SpringApplication(MyApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
	
	# ��������
		spring.pid.file=D://app.pid
			* д��pid���ļ�

		spring.pid.fail-on-write-error=true
			* ���޷�д��pid�ļ���ʱ��, �Ƿ��׳��쳣
		

		
	