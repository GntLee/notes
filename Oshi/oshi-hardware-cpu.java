CentralProcessor CPU
	ProcessorIdentifier getProcessorIdentifier(); 
	long getMaxFreq();			// ���Ƶ�ʣ���λ��HZ����֧�ַ��� -1
	long[] getCurrentFreq();			// ����ÿ���˵����Ƶ�ʣ���λ��HZ
	LogicalProcessor[] getLogicalProcessors();		// �����߼���Ԫ

	long[] getSystemCpuLoadTicks();		// ��ȡϵͳ��Χ��CPU���ؼ�ʱ�����������ذ����߸�Ԫ�ص�����
	double getSystemCpuLoadBetweenTicks(long[] oldTicks);	// ��ȡ������Ϣ
	double[] getSystemLoadAverage(int nelem);		// ϵͳƽ������
	double[] getProcessorCpuLoadBetweenTicks(long[][] oldTicks); // cpu������ʹ�����
	long[][] getProcessorCpuLoadTicks();  // CPU���ؼ�ʱ��

	int getLogicalProcessorCount();			// �߼���������
	int getPhysicalProcessorCount();		// �����������
	int getPhysicalPackageCount();			// ϵͳ�׽�������
	long getContextSwitches();				// �������л�����
	long getInterrupts();					// �жϴ���

ProcessorIdentifier ��������ʶ��
	private final String cpuVendor;
	private final String cpuName;
	private final String cpuFamily;
	private final String cpuModel;
	private final String cpuStepping;
	private final String processorID;
	private final String cpuIdentifier;
	private final boolean cpu64bit;
	private final long cpuVendorFreq;

LogicalProcessor �߼���������Ϣ
	private final int processorNumber;
	private final int physicalProcessorNumber;
	private final int physicalPackageNumber;
	private final int numaNode;
	private final int processorGroup;


TickType ��ö��
	USER(0),			�û�
	NICE(1),			
	SYSTEM(2),			ϵͳ
	IDLE(3),			����
	IOWAIT(4),			
	IRQ(5),				Ӳ���ж�
	SOFTIRQ(6),			����ж�
	STEAL(7);			