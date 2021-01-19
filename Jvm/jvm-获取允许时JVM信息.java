-----------------------
��ȡ�������Ϣ
-----------------------
RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

// ����
runtimeMXBean.getVmName();
// ��Ӧ��
runtimeMXBean.getVmVendor();
// �汾��
runtimeMXBean.getVmVersion();
// ����ʱ��
runtimeMXBean.getStartTime();
// ����ʱ��
runtimeMXBean.getUptime();


-----------------------
��ȡ�ڴ���Ϣ
-----------------------

MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

// ���ڴ�ʹ����Ϣ
MemoryUsage headMemory = memoryMXBean.getHeapMemoryUsage();
headMemory.getInit(); // ��ʼ���ڴ�
headMemory.getMax(); // ����ڴ�
headMemory.getUsed(); // ��ʹ�õ��ڴ�
headMemory.getCommitted(); // �Ѿ�������ڴ�

// �Ƕ��ڴ��ʹ����Ϣ
MemoryUsage nonheadMemory = memoryMXBean.getNonHeapMemoryUsage();
nonheadMemory.getInit(); // ��ʼ���ڴ�
nonheadMemory.getMax(); // ����ڴ�
nonheadMemory.getUsed(); // ��ʹ�õ��ڴ�
nonheadMemory.getCommitted(); // �Ѿ�������ڴ�

// ���ڴ��и���������Ϣ
List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
for (MemoryPoolMXBean memoryPoolMXBean : memoryPoolMXBeans) {
	memoryPoolMXBean.getName();						// ��������
	memoryPoolMXBean.getUsage();					// ʹ����Ϣ
	memoryPoolMXBean.getMemoryManagerNames();		// �ڴ��������GC��
	memoryPoolMXBean.getType();						// ����ö�١�HEAP��NON_HEAP
}