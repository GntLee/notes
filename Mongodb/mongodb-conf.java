---------------------------
mongod.conf
---------------------------
	# �ļ�����
		mongod.conf

	# ������Ŀ�ο�
		https://docs.mongodb.com/manual/reference/configuration-options/

	
	# ע��
		* mongodb���Զ��������ļ��е�˫���ŵ����ݽ���ת��
			

---------------------------
mongod.conf - ����
---------------------------
systemLog:
  destination: file # ָ����־�����Ŀ��Ϊ�ļ�
  path: <string> # ָ����־�ļ��ĵ�ַ
  logAppend: <boolean> # ʵ����������־��ĩβ�Ƿ�ʼд�루���򣬴�ͷ��ʼ��

processManagement:
  fork: true	# �Ƿ��ں�̨����
  pidFilePath: /var/run/mongodb/mongod.pid  # pid �ļ�·��
  
net:
  port: 27017		# �����˿�
  bindIp: 127.0.0.1  # �󶨵�ip
  maxIncomingConnections: 65535 # ������������������ Ĭ��ֵΪ65536
  wireObjectCheck: true # ���ͻ���д������ʱ ������ݵ���Ч��(BSON) Ĭ��ֵΪtrue
  ipv6: false 			# Ĭ��ֵΪfalse
 
storage:
  dbPath: /var/lib/mongo # ָ���洢Ŀ¼
  journal:
    enabled: <boolean> # ����/���ó־�����־����ȷ���ļ�������Ч�Ϳɻָ�
