--------------------------
thread					  |
--------------------------
	# �鿴��ǰ�߳���Ϣ, �鿴�̵߳Ķ�ջ

	# ���
		Threads Total: 13, NEW: 0, RUNNABLE: 6, BLOCKED: 0, WAITING: 4, TIMED_WAITING: 3, TERMINATED: 0                                                                                        
		ID             NAME                                          GROUP                          PRIORITY       STATE          %CPU           TIME            INTERRUPTED    DAEMON         
		22             as-command-execute-daemon                     system                         10             RUNNABLE       100            0:0             false          true           
		10             AsyncAppender-Worker-arthas-cache.result.Asyn system                         9              WAITING        0              0:0             false          true           
		8              Attach Listener                               system                         9              RUNNABLE       0              0:0             false          true           
		3              Finalizer                                     system                         8              WAITING        0              0:0             false          true           
		2              Reference Handler                             system                         10             WAITING        0              0:0             false          true           
		4              Signal Dispatcher                             system                         9              RUNNABLE       0              0:0             false          true           
		12             job-timeout                                   system                         9              TIMED_WAITING  0              0:0             false          true           
		1              main                                          main                           5              TIMED_WAITING  0              0:0             false          false          
		13             nioEventLoopGroup-2-1                         system                         10             RUNNABLE       0              0:0             false          false          
		17             nioEventLoopGroup-2-2                         system                         10             RUNNABLE       0              0:0             false          false          
		14             nioEventLoopGroup-3-1                         system                         10             RUNNABLE       0              0:0             false          false          
		15             pool-1-thread-1                               system                         5              TIMED_WAITING  0              0:0             false          false          
		16             pool-2-thread-1                               system                         5              WAITING        0              0:0             false          false       
		
	
	# ����
		id
			* �鿴ָ��id�̵߳Ķ�ջ
				thread 16
		-n
			* ָ����æ��ǰN���߳�, ����ӡ��ջ
				thread -n 3
		-b
			* �ҳ���ǰ���������̵߳��߳�

			* ��ʱ����Ӧ�ÿ�ס��, ͨ��������ĳ���߳���ס��ĳ����, ���������̶߳��ڵȴ��������ɵ�
				thread -b
			* �ò�������һ���ҳ������������̵߳��߳�

		-i
			* ָ��cpuռ��ͳ�ƵĲ������, ��λΪ����
				thread -n 3 -i 1000
	

	