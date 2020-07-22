----------------------------
oshi - ��ȡ����ϵͳ��Ϣ
----------------------------
	OperatingSystem os = systemInfo.getOperatingSystem();

		String String getFamily();				// ����ϵͳ���ͣ�Windows/Linux��
		String String getManufacturer();
		getSystemBootTime();					// ϵͳ����ʱ��
		getSystemUptime();						// ϵͳʹ��ʱ��
		isElevated();							// �Ƿ������Ȩ��
		OSVersionInfo getVersionInfo();			// �汾����Ϣ
		
		FileSystem getFileSystem();				// �ļ�ϵͳ

		OSProcess[] getProcesses();				// ������Ϣ
		OSProcess[] getProcesses(int limit, ProcessSort sort);
		OSProcess[] getProcesses(int limit, ProcessSort sort, boolean slowFields);
		List<OSProcess> getProcesses(Collection<Integer> pids);
		List<OSProcess> getProcesses(Collection<Integer> pids, boolean slowFields);
		OSProcess getProcess(int pid);
		OSProcess getProcess(int pid, boolean slowFields);
		OSProcess[] getChildProcesses(int parentPid, int limit, ProcessSort sort);
		int getProcessCount();		// ��ȡ��������
			* limit		���ƽ������
			* sort		����ö��
				CPU, MEMORY, OLDEST, NEWEST, PID, PARENTPID, NAME
			* slowFields	���Ϊ true, ��������̵������ֶ�(��Ƚ���)

		long getProcessAffinityMask(int processId);		// ����ָ�����̵Ľ��̹�������
			
		int getProcessId();								// ��ȡ��ǰ���̵Ľ���id
		int getThreadCount();							// ��ȡ��ǰ����ִ�е��߳�����
		int getBitness();								// ��ȡϵͳλ����32/64��
		NetworkParams getNetworkParams();				// ��ȡ�������
		OSService[] getServices();						// ��ȡϵͳ����


	OSVersionInfo �汾��Ϣ
		private final String version;				// �汾��				10
        private final String codeName;				// ���					HOME
        private final String buildNumber;			// �����汾				build 18362
        private final String versionStr;			// ƴ�ӵ��ַ���			10 (Home) build 18362

	FileSystem �ļ�ϵͳ
		OSFileStore[] getFileStores();			// ��ȡ�̷�
		OSFileStore[] getFileStores(boolean localOnly); // ������ȡ�����̷�
		long getOpenFileDescriptors();		// �Ѵ򿪵��ļ�������
		long getMaxFileDescriptors();		// һ�����Դ򿪵��ļ�������
	
	OSFileStore �̷���������
		private String name;				// ��������
		private String volume;				// 
		private String logicalVolume = "";
		private String mount;				// ����
		private String description;			// ����
		private String fsType;				// �ļ�ϵͳ����
		private String options;				// 
		private String uuid;				
		private long freeSpace;				// ���еĿռ�
		private long usableSpace;			// δʹ�õĿռ��С
		private long totalSpace;			// �ܿռ��С
		private long freeInodes;			// �ļ�ϵͳ����������
		private long totalInodes;			// �ļ�ϵͳ�������������


		boolean updateAtrributes()			// ��������
	
	OSProcess ������Ϣ
		private String name = "";
		private String path = "";
		private String commandLine = "";
		private String currentWorkingDirectory = "";
		private String user = "";
		private String userID = "";
		private String group = "";
		private String groupID = "";
		private State state = State.OTHER;
		private int processID;
		private int parentProcessID;
		private int threadCount;
		private int priority;
		private long virtualSize;
		private long residentSetSize;
		private long kernelTime;
		private long userTime;
		private long startTime;
		private long upTime;
		private long bytesRead;
		private long bytesWritten;
		private long openFiles;
		private int bitness;
		// cache calculation for sorting
		private double cpuPercent = -1d;
	
	
	NetworkParams �������
		String getHostName();				// ��������
		String getDomainName();				// ��������
		String[] getDnsServers();			// dns��������ַ
		String getIpv4DefaultGateway();		// ipv4���ص�ַ
		String getIpv6DefaultGateway();		// ipv6���ص�ַ
	

	OSService ϵͳ����
		private final String name;		// ��������
		private final int processID;	// ����id
		private final State state;		// ״̬ö��
			RUNNING,	����
			STOPPED,	ֹͣ
			OTHER		����
	

