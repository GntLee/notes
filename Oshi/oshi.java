----------------------------
oshi
----------------------------
	# Github
		https://github.com/oshi/oshi
	
	# maven
		<!-- https://mvnrepository.com/artifact/com.github.oshi/oshi-core -->
		<dependency>
			<groupId>com.github.oshi</groupId>
			<artifactId>oshi-core</artifactId>
			<version>4.4.2</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/net.java.dev.jna/jna -->
		<dependency>
			<groupId>net.java.dev.jna</groupId>
			<artifactId>jna</artifactId>
			<version>5.5.0</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/net.java.dev.jna/jna-platform -->
		<dependency>
			<groupId>net.java.dev.jna</groupId>
			<artifactId>jna-platform</artifactId>
			<version>5.5.0</version>
		</dependency>
	
	# ��ʼ��
		// �ж��Ƿ�֧�ֵ�ǰ�Ĳ���ϵͳ
		if (PlatformEnum.UNKNOWN.equals(SystemInfo.getCurrentPlatformEnum())) {
    		System.out.println("δ֪�Ĳ���ϵͳ");
    		return ;
    	}

    	// ��ʼ��ϵͳ��Ϣ
        SystemInfo systemInfo = new SystemInfo();


----------------------------
oshi - ��ȡCPU��Ϣ
----------------------------

----------------------------
oshi - ��ȡ�ڴ���Ϣ
----------------------------


----------------------------
oshi - ��ȡӲ����Ϣ
----------------------------


----------------------------
oshi - ��ȡ����ϵͳ��Ϣ
----------------------------

----------------------------
oshi - ��ȡ������Ϣ
----------------------------

----------------------------
oshi - ��ȡJVM��Ϣ
----------------------------