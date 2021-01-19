--------------------------------
zkCli.sh						|
--------------------------------
	# ����
		-server [ip]:[port]

		* ipĬ��Ϊlocalhost
		* portĬ��Ϊ2181
	
	# ����
		help
			* ������Ϣ
				ZooKeeper -server host:port cmd args
				stat path [watch]
				set path data [version]
				ls path [watch]
				delquota [-n|-b] path
				ls2 path [watch]
				setAcl path acl
				setquota -n|-b val path
				history 
				redo cmdno
				printwatches on|off
				delete path [version]
				sync path
				listquota path
				rmr path
				get path [watch]
				create [-s] [-e] path data acl
				addauth scheme auth
				quit 
				getAcl path
				close 
				connect host:port

--------------------------------
�����Ľڵ����					|
--------------------------------
	# �ڵ��CRUD
		create [-s] [-e] [-c] [-t ttl] path data acl
			* �����µĽڵ�
				-s ��ʾ˳��ڵ�
				-e ��ʾ��ʱ�ڵ� 
				-c ��ʾ�����ڵ�
				-t ��ʾttl�ڵ�, ttl ��ʾttlֵ
				path Znode
				data ��������
				acl Ȩ�޿���

			* �����༶Ŀ¼��ʱ��,����Ŀ¼�������
			* ����������������(�ո�),��ô������˫���Ű�������������
			* ��������dataֵ,��������õĻ�,�ڵ�ᴴ��ʧ��
		
		ls path
			* �鿴ָ��Ŀ¼�µ������ӽڵ�(ֻ�ܲ鿴һ��)
				- �鿴���ڵ�: ls /
			* ����
				-s �������г��ӽڵ�, ����ʾ��ǰ���ڵ����Ϣ
				
				
		ls2 path
			* �鿴ָ��Ŀ¼�µ������ӽڵ�(ֻ�ܲ鿴һ��)
			* ����ʶΪ����,���Ƽ�ʹ����, ����ʹ��: ls -s [node]
			* ���������г��ӽڵ�,����չʾ���ڵ����Ϣ
				[�ӽڵ�1, �ӽڵ�n]
				cZxid = 0x200000009
				ctime = Thu Jan 03 09:32:01 CST 2019
				mZxid = 0x200000009
				mtime = Thu Jan 03 09:32:01 CST 2019
				pZxid = 0x20000000e
				cversion = 2
				dataVersion = 0
				aclVersion = 0
				ephemeralOwner = 0x0
				dataLength = 4
				numChildren = 2

		get path
			* �鿴ָ���Ľڵ���Ϣ
				data(�Ҿ��ǽڵ������)
				cZxid = 0x20000000d
				ctime = Thu Jan 03 09:39:15 CST 2019
				mZxid = 0x20000000d
				mtime = Thu Jan 03 09:39:15 CST 2019
				pZxid = 0x20000000d
				cversion = 0
				dataVersion = 0
				aclVersion = 0
				ephemeralOwner = 0x0
				dataLength = 11
				numChildren = 0
		
		stat path
			* �鿴ָ���ڵ����Ϣ
			* �� getһ��,���������˽ڵ��������Ϣ

		set path data [version] 
			* ����ָ���Ľڵ�
				pah �ڵ�
				data �µ�����
				version �ֹ���(dataVersion)
			
			* �ֹ����������Ǳ����,��������ֹ���,���жϰ汾��,����汾������,�����ʧ��
		
		delete path [version]
			* ɾ��ָ���Ľڵ�
				path �ڵ�
				version �ֹ���(dataVersion)
			* �ֹ����������Ǳ����,��������ֹ���,���жϰ汾��,����汾������,��ɾ��ʧ��
			* ���ɾ���Ľڵ�����ӽڵ�Ļ�,�Ǳ���Ҫ��ɾ��ȫ���ӽڵ�


		rmr path
			* ���Եݹ��ɾ��ָ���ڵ�
			* ����!!!
	
	# �ڵ������ quota
		setquota -n|-b val path
			* ���ýڵ������
				path �ڵ�
				-n �ӽڵ���������(���ֵΪ-1���������ӽڵ�����)
				-b ���ݵ���󳤶�(���ֵΪ-1���ʾ��������������С???)
				val �ӽڵ���������/���ݵ���󳤶� ֵ
			* ����root�ڵ�ֻ����10���ӽڵ� :setquota -n 10 /root

		listquota path
			* �鿴ָ���ڵ�� quota
				absolute path is /zookeeper/quota/root0000000000/zookeeper_limits
				Output quota for /root0000000000 count=10,bytes=-1
					* �ڵ��Լ��
				Output stat for /root0000000000 count=3,bytes=21
					* ��ǰ�ڵ����Ϣ(count�ǰ������Լ���,���Ǹ�ֵ����Ϊ1)
				
				count �ӽڵ���������
				bytes ��ǰ�ڵ��������ݴ洢�ռ�
		
		delquota -n|-b path
			* ɾ��ָ���ڵ��ָ������
				path ·��
				-n ɾ���ӽ�����������
				-b ɾ���ڵ����ݵĴ�С����
		
		* quota �����Ʋ���ǿ�Ƶ�
		* ����:�������ӽڵ�ֻ����2��,ʵ�������3�����߶���ӽڵ�,���ǻ�ɹ�
		* ֻ�ǻ�����־���о�����Ϣ,��Ҫ�����ֶ����񾯸���Ϣ,�Լ�����


--------------------------------
Watcher ����					|
--------------------------------
	stat path [watch]
		* ����ָ���ڵ��ɾ��,����,�����޸��¼�

		* NodeCreated(�ڵ㱻����)
			* ����ڵ㲻����,����ʾ:Node does not exist,������Ȼ���Լ���
		* NodeDeleted(�ڵ㱻ɾ��)
		* NodeDataChanged(���ݷ����˱仯)

	ls path [watch]
	ls2 path [watch]
		* �����ӽڵ���ɾ���¼�(���ܼ����ӽڵ�����ݱ仯�¼�)

		* NodeChildrenChanged(�ӽڵ����/ɾ��)

	get path [watch]
		* �����ڵ����ݵı仯�¼�
		* ���ýڵ����ݷ����仯,�ü������յ��¼�֪ͨ

		* NodeDataChanged(���ݷ����˱仯)
		* NodeDeleted(�ڵ㱻ɾ��)

	* watch ��ʵ�����������һ���ַ�
		


--------------------------------
����							|
--------------------------------
	# ��ʷ����
		history
			* �ڿͻ���ִ�е������¼
				49 - delquota -n /root0000000000
				50 - delquota -n /root0000000000
				51 - setquota -n 1 /root0000000000
				52 - setquota -n 1 /root0000000000
				53 - history
				54 - listquota /root0000000000
				55 - setquota -n 10 /root0000000000
				56 - setquota -n -b 4~10 /root0000000000
				57 - setquota -n -b 10 /root0000000000
				
			* ǰ������ֱ�ʾ�����ı��

		redo number
			* �ظ�ִ��ָ����ŵ�����
				number ����ı��
			