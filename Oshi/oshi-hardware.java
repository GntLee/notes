------------------------
Hardware
------------------------
	HardwareAbstractionLayer hardware = systemInfo.getHardware();

		ComputerSystem getComputerSystem();		// ϵͳ
		CentralProcessor getProcessor();		// CPU
		GlobalMemory getMemory();				// �ڴ�
		PowerSource[] getPowerSources();		// ��Դ��Ϣ
		HWDiskStore[] getDiskStores();			// ����
		NetworkIF[] getNetworkIFs();			// ����
		Sensors getSensors();					// ������
		UsbDevice[] getUsbDevices(boolean tree);// USB�豸�� tree�� ��ʾ�Ƿ�����������֯���ؽ��
		SoundCard[] getSoundCards();			// ���� 
	

	ComputerSystem ����ϵͳ
		String getManufacturer();		// ������ ��HP��
		String getModel();				// �����ϵͳ���� ��OMEN by HP Laptop 17-an0xx��
		String getSerialNumber();		// ���к�
		Firmware getFirmware();			// �̼���Ϣ
		Baseboard getBaseboard();		// ������Ϣ
	
	Firmware �̼���Ϣ��BIOS��
		String getManufacturer();		// ������
		String getName();				// ����
		String getDescription();		// ����
		String getVersion();			// �汾��
		String getReleaseDate();		// ��������
	
	Baseboard ������Ϣ��BIOS��
		String getManufacturer();		// ������
		String getModel();				// ��������
		String getVersion();			// �汾��
		String getSerialNumber();		// ���л�
	
	
	
		