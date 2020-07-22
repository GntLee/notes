-------------------------
aclȨ�޹���				 |
-------------------------
	# �ο�
		https://www.jianshu.com/p/147ca2533aff

	# zookeeper֧�ֵ�Ȩ����5�ֱַ���
		CREATE: ���Դ����ӽڵ�
		READ: ���Ի�ȡ�ڵ������Լ���ǰ�ڵ���ӽڵ��б�
		WRITE: ����Ϊ�ڵ���������
		DELETE: ����ɾ���ӽڵ�
		ADMIN: ��Ϊ�ڵ�����Ȩ��

	# Zookeeper��ACL,���Դ�����ά�������,һ�� scheme; ���� user; ���� permission
		* scheme ��ʾȨ�޵Ŀ�������
		* user ��ʾ��Ȩ�Ķ���
		* permission ��ʾ����Ȩ��

	# ͨ����ʾΪ:scheme:id:permissions

	# scheme
		* scheme��Ӧ�ڲ������ַ���������Ȩ�޹���zookeeperʵ����һ��pluggable��ACL����
		* ����ͨ����չscheme,����չACL�Ļ���
		* zookeeper-3.4.4ȱʡ֧�����漸��scheme

		world
			* ������ֻ��һ��id, �� anyone, world:anyone �����κ���
			* zookeeper�ж���������Ȩ�޵Ľ���������world:anyone��

		auth:
			* ������Ҫid, ֻҪ��ͨ��authentication��user����Ȩ��(zookeeper֧��ͨ��kerberos������authencation, Ҳ֧��username/password��ʽ��authentication)
			* ��������Ӧ����expression����ʾ,��(scheme:expression:permissions)

		digest:
			* ����Ӧ��idΪusername:BASE64(SHA1(password)),����Ҫ��ͨ��username:password��ʽ��authentication
			*  digest:kevin:BASE64(SHA1('123456'))
		
		host 
			* ʹ���û���������Ϊ���ʿ����б��id
			* ����������Ҫע����Ǳ��ʽ�õ����������ĺ�׺����,ֻҪ�Ƿ��Ϻ�׺�Ķ༶����������

		ip
			* ����Ӧ��idΪ�ͻ�����IP��ַ,���õ�ʱ���������һ��ip��
			* ���� ip:192.168.1.0/16, ��ʾƥ��ǰ16��bit��IP��
			
		super:
			* ������scheme�����,��Ӧ��idӵ�г���Ȩ��,�������κ�����(cdrwa)

-------------------------
aclȨ�޹���	- auth		 |
-------------------------
	# ������Ȩ��ʽ,�ǰ�һ���ڵ���Ȩ��ϵͳ������֤���û�
		* һ�Զ�
	# ��ִ����Ȩ֮ǰ,����������û�
		addauth digest user:pass

		* user:pass �û���������(����)
	
	#  ����auth��Ȩ
		setAcl path auth::rwadc

		* path �ڵ�
		* auth ��Ȩ��ʽ
		* rwadc ��ʾȨ��:read,write,admin,delete,create

		* ����Ȩ��ʾ,��Ȩ�Ľڵ�,����ϵͳ��ӵ��û�����
	
	# �鿴acl��Ȩ
		getAcl path
		'digest,'zookeeper:4lvlzsipXVaEhXMd+2qMrLc0at8=
		: cdrwa
		'digest,'root:qiTlqPLK7XM2ht3HMn02qRpkKIE=
		: cdrwa


-------------------------
aclȨ�޹���	- digest	 |
-------------------------
	# ������Ȩ��ʽ,�ǰ�һ���ڵ���Ȩ��ϵͳָ�����û�
		* һ��һ
	# �Ȱ�����û���ZK
		addauth digest user:pass

		* user:pass �û���������(����)
	
	#  ����digest��Ȩ
		setAcl path digest:user:pass:rwadc

		* path �ڵ�
		* user:pass �û���������,ע��,�������Ⱦ��� sha1����,Ȼ��base64����Ľ��
		* auth ��Ȩ��ʽ
		* rwadc ��ʾȨ��:read,write,admin,delete,create
	

	#  �鿴acl��Ȩ
		'digest,'zookeeper:4lvlzsipXVaEhXMd+2qMrLc0at8=
		: cdrwa
	
	# ���������ʵ���ǰ�ϵͳ���Ѿ����ڵ��û�,��Ȩ��һ��ָ���Ľڵ�
		* ִ�� digest ��Ȩ��ʱ��,������������,���ӵİ�ȫ
		* ��ȡ�û�������,�����֪������,����ͨ��:getAcl ȥ��ȡ�û���Ȩ�޵Ľڵ��Ȩ����Ϣ�л�ȡ
		* ���֪������,�Լ����Լ������

	# �ٰ��û���ӵ�ϵͳ
	# ��ִ����Ȩ,���õ�������Ҫ�Լ�ȥ���� sha1 �� base64����
	# ����ʹ��zk�ṩ�Ŀͻ��˹���������ȡ�����ܺ������
		String digest = DigestAuthenticationProvider.generateDigest("kevin:123456");
		//kevin:GSivD5W51c7Wm5vFWnFp1IYOVTY= 
		//ð�ź������������ܺ������
	