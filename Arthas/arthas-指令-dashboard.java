----------------------------
dashboard					|
----------------------------
	# �ĵ�
		https://alibaba.github.io/arthas/dashboard.html
	
	# ��ǰϵͳ��ʵʱ�������
		* �� ctrl+c �˳�
	
	# ���
		ID             NAME                                          GROUP                          PRIORITY       STATE          %CPU           TIME            INTERRUPTED    DAEMON         
		21             Timer-for-arthas-dashboard-61567f15-5bbc-4f8e system                         10             RUNNABLE       100            0:0             false          true           
		10             AsyncAppender-Worker-arthas-cache.result.Asyn system                         9              WAITING        0              0:0             false          true           
		8              Attach Listener                               system                         9              RUNNABLE       0              0:0             false          true           
		3              Finalizer                                     system                         8              WAITING        0              0:0             false          true           
		2              Reference Handler                             system                         10             WAITING        0              0:0             false          true           
		4              Signal Dispatcher                             system                         9              RUNNABLE       0              0:0             false          true           
		20             as-command-execute-daemon                     system                         10             TIMED_WAITING  0              0:0             false          true           
		12             job-timeout                                   system                         9              TIMED_WAITING  0              0:0             false          true           
		1              main                                          main                           5              TIMED_WAITING  0              0:0             false          false          
		13             nioEventLoopGroup-2-1                         system                         10             RUNNABLE       0              0:0             false          false          
		17             nioEventLoopGroup-2-2                         system                         10             RUNNABLE       0              0:0             false          false          
		14             nioEventLoopGroup-3-1                         system                         10             RUNNABLE       0              0:0             false          false          
		15             pool-1-thread-1                               system                         5              TIMED_WAITING  0              0:0             false          false          
		16             pool-2-thread-1                               system                         5              WAITING        0              0:0             false          false          
																																																   
																																															   
		Memory                                 used          total        max          usage        GC                                                                                         
		heap                                   17M           35M          224M         8.00%        gc.copy.count                                 25                                           
		eden_space                             1M            9M           61M          1.67%        gc.copy.time(ms)                              129                                          
		survivor_space                         1M            1M           7M           15.45%       gc.marksweepcompact.count                     3                                            
		tenured_gen                            15M           24M          154M         10.16%       gc.marksweepcompact.time(ms)                  103                                          
		nonheap                                19M           20M          -1           95.69%                                                                                                  
		code_cache                             4M            4M           240M         1.69%                                                                                                   
		metaspace                              13M           14M          -1           95.74%                                                                                                  
		compressed_class_space                 1M            1M           1024M        0.16%                                                                                                   
		direct                                 0K            0K           -            Infinity%                                                                                               
		mapped                                 0K            0K           -            NaN%                                                                                                    
																																															   
																																															   
		Runtime                                                                                                                                                                                
		os.name                                                                                     Linux                                                                                      
		os.version                                                                                  3.10.0-957.1.3.el7.x86_64                                                                  
		java.version                                                                                1.8.0_211                                                                                  
		java.home                                                                                   /usr/local/java/jdk1.8.0_211/jre                                                           
		systemload.average                                                                          0.00                                                                                       
		processors                                                                                  1                                                                                          
		uptime                                                                                      385s                                                  
		
		* �̵߳�ID, ����, �߳���, ���ȼ�(Խ���ʾ���ȼ�Խ��), ״̬, CPUռ�ðٷֱ�(�������߳�����100ms�ڵ�cpuʹ�������, �����ÿ���̵߳�cpuʹ��ռ��), �̵߳�ִ��ʱ��, �Ƿ��ж�, �Ƿ���daemon�߳�

		* �ڴ�����, ��ʹ��, �ܴ�С, ���, ʹ����, GC��Ϣ
													gc�Ŀ�������,�������ѵ�ʱ��, ����������, ���������ʱ��
		
		* ����ʱ��Ϣ
			
