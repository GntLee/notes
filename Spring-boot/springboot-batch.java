-------------------------
SpringBoot batch		 |
-------------------------
	# �ṹ
	  Step
		��
	  Tasklet
		��
	  Chunk
		��
	  read
	  process
	  write
	 
	
	# �ṹ
		JobRepository	
			* ����ע��job������,�������,�����־û�Job��Ԫ����,Ĭ��ʹ���ڴ�
		JobLauncher
			* ��������Job�Ľӿ�,�������
		Job
			* ʵ��ִ�е�����,����һ������Step,��Batch�����Ļ���ִ�е�Ԫ
		Step    
			* ��ʾJob��һ���׶�,Job��һ��Step���
			* Step����ItemReader��ItemProcessor��ItemWriter
		Item
			* ������Դ��������д���һ����¼
		Chunk
			* ����������Item����(Item����)
		ItemReader
			* ������ȡ���ݵĽӿ�
		ItemProcessor
			* �����������ݵĽӿ�(Consumer)
		ItemWriter
			* ����������ݵĽӿ�(��Chunk����������д������Դ)

	# ע�⿪��
		@EnableBatchProcessing
			boolean modular() default false;
			* Ҫʵ�ֶ�Job,��Ҫ��modular����Ϊtrue
			* ��ÿ��Jobʹ���Լ���ApplicationConext
		
	
		

		