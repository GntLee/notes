----------------------------
StopWatch					|
----------------------------
	# �����������������ʱ�Ĺ���
		* �̲߳���ȫ, ����Ӧ���ǲ��Ի���ʹ��, ��������������

	# ���캯��
		StopWatch()
		StopWatch(String id)

		id
			* ÿ��ʵ����������һ��Ψһ��id
	
	# ʵ������
		String getId()
		TaskInfo getLastTaskInfo()
			* ��ȡ���һ��ִ�е�����

		String getLastTaskName()
		long getLastTaskTimeMillis()
		int getTaskCount()
			* ��ȡִ������Ĵ���

		TaskInfo[] getTaskInfo()
		long getTotalTimeMillis()
			* ��ȡ��������ִ�е��ܺ�ʱ, ����

		double getTotalTimeSeconds()
			* ��ȡ��������ִ�е��ܺ�ʱ, �� 

		boolean isRunning()
		String prettyPrint()
		void setKeepTaskList(boolean keepTaskList)
			* �Ƿ񱣴�ÿ��ִ�й�������

		String shortSummary()
		void start()
		void start(String taskName)
		void stop() 
		String toString()
	
	# �ڲ���̬��:TaskInfo

		* ��װ��ÿ�����������, �Լ���ʱ��Ϣ

		TaskInfo(String taskName, long timeMillis)

		 String getTaskName()
			* ��ȡ���������

		 long getTimeMillis()
		 double getTimeSeconds()
			* ��ǰ����ĺķ�ʱ��
		
----------------------------
Demo						|
----------------------------

import org.springframework.util.StopWatch;

public class Main {
	public static void main(String[] args) throws Throwable{
		StopWatch stopWatch = new StopWatch("id");

		// ��ʼ��һ������
		stopWatch.start("task1");
		Thread.sleep(2000L);
		stopWatch.stop();

		// ��ʼ�ڶ�������
		stopWatch.start("task2");
		Thread.sleep(3000L);
		stopWatch.stop();

		String prettyPrint = stopWatch.prettyPrint();

		System.out.println(prettyPrint);

		System.out.println(stopWatch);

	}
}
/*

StopWatch 'id': running time (millis) = 5000
-----------------------------------------
ms     %     Task name
-----------------------------------------
02000  040%  task1
03000  060%  task2


StopWatch 'id': running time (millis) = 5001; [task1] took 2000 = 40%; [task2] took 3001 = 60%
*/