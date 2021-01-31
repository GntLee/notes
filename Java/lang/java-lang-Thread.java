---------------------------
Thread						|
---------------------------
	# ���߳���������
	# ���췽��
		Thread t = new Thread();
		Thread t = new Thread(Runnable r);
	
	# �¹�����̶߳���������parent�߳������пռ�����
		* child�̼̳߳���parent�Ƿ�ΪDaemon�����ȼ��ͼ�����Դ��contextClassLoader�Լ��ɼ̳е�ThreadLocal
		* ͬʱ�������һ��Ψһ��ID����ʶ���child�߳�


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
			* �����̵߳����ȼ��� 1-0,�߳����ȼ����Ǿ����߳���Ҫ������ٷ���һЩ��������Դ���߳�����
			* Thread ���ṩ��N��ľ�̬����ֵ
				Thread.MAX_PRIORITY = 10;
				Thread.MIN_PRIORITY = 1;
				Thread.NORM_PRIORITY = 5;
			* ��Щ����ϵͳ��������Զ��߳����ȼ����趨
		
		int getPriority()
			* ��ȡ�̵߳����ȼ�
		
		void interrupt()
			* �ж��߳�
		
		boolean isInterrupted()
			* �߳��Ƿ��ж�
		
		void join();
		void join(long millis)
		void join(long millis, int nanos)
			* ���ø÷������̻߳�һֱ����,ֱ�����߳�(join ������ Thread �߳�)ִ����Ϻ������ִ��
			* Ҳ�������ó�ʱʱ��

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
	
	# ���ŵ�֪ͨ�߳̽���
		import java.util.concurrent.TimeUnit;
		public class Main {
			public static void main(String[] args) throws InterruptedException {
				Thread t = new Thread(() -> {
					int i = 0;
					while (!Thread.currentThread().isInterrupted()) { // �ж��߳��Ƿ��жϹ�������жϹ����������ʶλ
						System.out.println(i ++);
					}
				});
				t.start();
				
				TimeUnit.SECONDS.sleep(3L);
				
				t.interrupt(); // �ж��߳�
				
				System.out.println("����");
			}
		}

---------------------------
Thread ״̬
---------------------------
	NEW
		* �´��������ǻ�û���� start(); ����
	
	RUNNABLE
		* ����״̬��Java�̰߳Ѳ���ϵͳ�еľ���������״̬����ΪRUNNABLE
	
	BLOCKED
		* ����״̬����ʾ�߳���������
	
	WAITING
		* �ȴ�״̬����ʾ�߳̽���ȴ�״̬�������״̬��ʾ��ǰ�߳���Ҫ�ȴ������߳�����һЩ�ض�������֪ͨ/�жϣ�
	
	TIME_WAITING
		* ��ʱ�ȴ������״̬��WAITING��ͬ����������ָ��ʱ�����з��أ����磺Sleep

	TERMINATED
		* ��ֹ״̬���߳��Ѿ�ִ�����
	
---------------------------
Thread �ȴ����ѻ���
---------------------------
	# ���ж��󶼿��Ե����������м�������
		void notify();
		void notifyAll();
			* ����һ��/�����߳�
			* �߳�״̬����: BLOCKED

		void wait() throws InterruptedException
		void wait(long timeout)
		void wait(long timeout, int nanos)
			* ����ȴ�״̬���������ó�ʱʱ��
			* �߳�״̬����: WAITING
	
		
		* ������Щ��������Ҫ��������sync...������е���
	
	# һ�㹤��ģʽ
		* ������
			synchronized ([lock]) {
				while ([condition������]) {
					[lock].wait();
				}
				// ����ҵ���߼�
			}
		
		* ������
			synchronized ([lock]) {
				// �ı�����
				[lock].notifyAll();
			}