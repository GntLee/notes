GlobalMemory �ڴ�
	long getTotal();		// �ܴ�С
	long getAvailable();	// ���õ�
	long getPageSize();		// ҳ��С
	VirtualMemory getVirtualMemory(); // �����ڴ���Ϣ
	PhysicalMemory[] getPhysicalMemory(); // �����ڴ���Ϣ

VirtualMemory
    long getSwapTotal();		// �ܴ�С
    long getSwapUsed();			// �Ѿ�ʹ�õ�
    long getSwapPagesIn();
    long getSwapPagesOut();

PhysicalMemory
	private final String bankLabel;
    private final long capacity;
    private final long clockSpeed;
    private final String manufacturer;
    private final String memoryType;

