---------------------------
Thread						|
---------------------------
	# ���߳���������
	# ���췽��
		Thread t = new Thread();
		Thread t = new Thread(Runnable r);

---------------------------
Thread-����					|
---------------------------
	# ��̬����
	
	# ʵ������

---------------------------
Thread-����					|
---------------------------
	# ��̬����
		Thread currentThread();
			* ���ص�ǰ���̶߳���
		sleep(long l);
			* ��ǰ�߳�ֹͣ l ����
		
		Map<Thread, StackTraceElement[]> getAllStackTraces()
			* ��ȡ��JVM�������̵߳�ִ��ջ

	# ʵ������
		
		getName();
			* �����߳�����
		
		void setPriority(int newPriority)
			* �����̵߳����ȼ�
			* Thread ���ṩ��N��ľ�̬����ֵ
		
		int getPriority()
			* ��ȡ�̵߳����ȼ�
		
		void interrupt()
			* �ж��߳�
		
		boolean isInterrupted()
			* �߳��Ƿ��ж�
		
		void join();
			* ���ø÷������̻߳�һֱ����,ֱ�����߳�(join ������ Thread �߳�)ִ����Ϻ������ִ��
		
		void setDaemon(boolean on)
			* ����Ϊ��ǰ�̵߳��ػ��߳�
			* �����ڵ��� start() ����֮ǰ����
		
		void stop();
			* ����ֹͣ���߳�
		
		boolean isAlive()
			* �ж��߳��Ƿ񻹴��
		
		void setContextClassLoader(ClassLoader cl)
		ClassLoader getContextClassLoader()
			* ����/��ȡ��ǰ�̳߳�����ʹ�õ� classloader

---------------------------
Thread ���жϻ���			|
---------------------------
	# ÿ���̶߳���һ�� "�ж�" ��־,������������
	
	# �߳���sleep��wait(����),join ....
		* ��ʱ�����Ľ��̵��ô˽���(Thread ����)�� interrupt()����,���̻߳ᱻ���Ѳ���Ҫ���� InterruptedException
		* (thread����IO����ʱҲ������������Ϊ,��java thread api)
		* �쳣������,�����������ʶλΪ false
	
	# ���߳���������
		* ��ʱ�����Ľ��̵��ô˽���(Thread ����)�� interrupt()����,�����յ�����,���Ǵ��̵߳� "�ж�" �ᱻ����Ϊ true
		* ����ͨ�� isInterrupted() �鿴����������

	# �ܽ�
		interrupt()		ʵ������	���� void		�жϵ��ø÷����ĵ�ǰ�߳�
		interrupted()	��̬����	���� boolean	��⵱ǰ�߳��Ƿ��жϣ����ѱ��жϹ�������ж�״̬
			* ע��, ����һ����̬������ֻ�ܶԵ�ǰ���̽��в���

		isInterrupted()	ʵ������	���� boolean	�����ø÷������߳��Ƿ��жϣ�������жϱ��