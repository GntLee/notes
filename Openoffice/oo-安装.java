-------------------------
openoffice��װ			 |
-------------------------
	# ׼����װ����
		yum -y install libXext.x86_64
		yum -y groupinstall "X Window System"

	# ���ص�ַ��http://www.openoffice.org/download/index.html
		wget https://jaist.dl.sourceforge.net/project/openofficeorg.mirror/4.1.5/binaries/zh-CN/Apache_OpenOffice_4.1.5_Linux_x86-64_install-rpm_zh-CN.tar.gz

	# ��ѹ
		tar -zxvf Apache_OpenOffice_4.1.5_Linux_x86-64_install-rpm_zh-CN.tar.gz

	# �����ѹ���Ŀ¼zh-CN/RPMSִ�а�װ
		yum -y localinstall *.rpm

	# �����ѹ���Ŀ¼zh-CN/RPMS/desktop-integrationִ�а�װ
		yum -y localinstall openoffice4.1.5-redhat-menus-4.1.5-9789.noarch.rpm

	# ��װ�ɹ��󣬳���ᱻ��װ��:/opt/openoffice4
	# ��������
		nohup soffice -headless -accept="socket,host=0.0.0.0,port=8100;urp;" -nofirststartwizard &

	# �鿴�����Ƿ�����:netstat -anop | grep 8100
		tcp        0      0 127.0.0.1:8100          0.0.0.0:*               LISTEN      1115/soffice.bin     off (0.00/0/0)

	#ɱ������:ps -ef | grep openoffice
		kill -9 [pid]