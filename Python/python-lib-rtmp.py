-----------------------
Linux ��װ����			|
-----------------------
	# ��װgcc��������
		yum install gcc* python-devel libffi-dev* -y 
	
	# ��װ librtmp 
		git clone git://git.ffmpeg.org/rtmpdump
		cd rtmpdump/librtmp/
		make && make install
	
	# ��װ setuptools
		* ��:https://pypi.org/ ����:setuptools
		* ������ҳ��,���� zip ��
			wget -S https://files.pythonhosted.org/packages/b0/d1/8acb42f391cba52e35b131e442e80deffbb8d0676b93261d761b1f0ef8fb/setuptools-40.6.2.zip
		* ��ѹ & ��װ
			unzip setuptools-40.6.2.zip
			cd setuptools-40.6.2
			python setup.py install
		* ֱ�� pip ��װҲ��
	
	# ��װ cffi
		* ��:https://pypi.org/ ����:cffi
		* ������ҳ��,���� tar.gz ��
			wget -S https://files.pythonhosted.org/packages/e7/a7/4cd50e57cc6f436f1cc3a7e8fa700ff9b8b4d471620629074913e3735fb2/cffi-1.11.5.tar.gz
		* ��ѹ & ��װ
			tar -zxvf cffi-1.11.5.tar.gz
			cd cffi-1.11.5
			python setup.py install
		* ֱ��pip��װҲ��
	
	# ��װ librtmp
		pip install python-librtmp
		
	# ���� import librtmp
	
	# �����쳣:ImportError: librtmp.so.1: cannot open shared object file: No such file or directory
		* ���ҵ�librtmp.so.1·��,���Ƶ�lib64Ŀ¼�¼���
		* ���밲װ librtmp ��ʱ��,��־���� librtmp.so.1 ��·����Ϣ

		find / -name librtmp.so.1
		cp /usr/local/lib/librtmp.so.1 /usr/lib64/

-----------------------
windows ��װ����		|
-----------------------
	# ִ��
		pip install python-librtmp
	
	# ��װ�쳣
		error: Microsoft Visual C++ 14.0 is required. Get it with "Microsoft Visual C++ Build Tools": https://visualstudio.microsoft.com/downloads/

		* ���ڱ�����װ: visualcppbuildtools_full.exe

	
	# ����ʧ��:
		Can not open include file: 'librtmp/rtmp.h': No such file or directory
