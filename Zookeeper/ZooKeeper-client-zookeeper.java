-------------------------------------
zookeeper							 |
-------------------------------------
	# zookeeper�Դ��Ŀͻ����ǹٷ��ṩ��,�Ƚϵײ�
		<dependency>
		    <groupId>org.apache.zookeeper</groupId>
		    <artifactId>zookeeper</artifactId>
		    <version>3.4.13</version>
		</dependency>

-------------------------------------
������API							 |
-------------------------------------
	Zookeeper
		* �����������˵�����,�����ṩ�˷������в���

	Watcher
		* һ����׼���¼��������ӿ�
	

-------------------------------------
���ӵĴ���							 |
-------------------------------------
	# ���������Ӵ���
		ZooKeeper(String connectString, int sessionTimeout, Watcher watcher)
		ZooKeeper(String connectString, int sessionTimeout, Watcher watcher,boolean canBeReadOnly)
		ZooKeeper(String connectString, int sessionTimeout, Watcher watcher,long sessionId, byte[] sessionPasswd)
		ZooKeeper(String connectString, int sessionTimeout, Watcher watcher,long sessionId, byte[] sessionPasswd, boolean canBeReadOnly)

		* connectString ���ӵĵ�ַ�����Ǽ�Ⱥ�еĶ������һ��
			"127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002"

			* ����Ƕ�����ӵĻ�,����һ���޷�����ʱ,�᳢����һ������
			* Ҳ���Ի���ĳ���ڵ�Ŀ¼��������
				"127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002/app/a"
		* sessionTimeout ��ʱʱ�䵥λΪ����
		* Watcher
			* ��һ���¼�����,����������������¼�
		* canBeReadOnly 
			* ���ڱ�ʶ��ǰ�Ự�Ƿ�֧��"read-only"ģʽ
			* Ĭ�������,��ZK��Ⱥ��,һ����������ͼ�Ⱥ�еİ����Լ��������ϵĻ���ʧȥ����,��ô������������ٴ���ͻ�������(������+д�����������)
			* ������ʱ������ϣ���ڷ����������ʱ��Ӱ���ȡ����Ĵ���,�������zk�� read-only ģʽ
		* sessionId
		* sessionPasswd
			* �Ựid�ͻỰ��Կ
		
-------------------------------------
�ڵ�Ĳ���							 |
-------------------------------------
	# �����ڵ�
		String create(final String path, byte data[], List<ACL> acl,CreateMode createMode)
		void create(final String path, byte data[], List<ACL> acl,CreateMode createMode,  StringCallback cb, Object ctx)

		path 
			* �ڵ��ַ
		data
			*  ����
		acl
			* ACLȨ��,ZooDefs.Ids ��Ԥ����һ��ѵ�Ȩ��,����:OPEN_ACL_UNSAFE ��ʾ��������Ȩ��

		createMode
			* ��������,CreateMode ö��

	

	# ��ȡ�ڵ�
		byte[] getData(String path, boolean watch, Stat stat)
		void getData(String path, boolean watch, DataCallback cb, Object ctx)
		
		watch
			* �Ƿ�Ҫ�����¼�,�����ֵΪ true,�¼�����,������ Zookeeper ʵ����Watcherʵ��
			* �¼�ֻ�ᴥ��һ��

		byte[] getData(final String path, Watcher watcher, Stat stat);
		void getData(final String path, Watcher watcher, DataCallback cb, Object ctx)

		Watcher
			* ����path�ڵ��¼�������,��������¼�,��֪ͨ�� Watcher ʵ��,����֪ͨZookeeper��Watcherʵ��
			* �¼�������ʱ��,ÿ����������ִ��
				zooKeeper.getData("/root", new DefaultWatcher("root0"), null);
				zooKeeper.getData("/root", new DefaultWatcher("root1"), null);
				zooKeeper.getData("/root", new DefaultWatcher("root2"), null);
				zooKeeper.getData("/root", new DefaultWatcher("root3"), null);
				zooKeeper.setData("/root", "new".getBytes(), -1);		//�޸��¼�����,����������������ִ��,����ִ��˳��һ��
		

	# �޸Ľڵ�
		Stat setData(final String path, byte data[], int version)
		void setData(final String path, byte data[], int version,StatCallback cb, Object ctx)
		
		path
			* ·��
		data
			* ����
		version
			* �ֹ����汾��(���ֵΪ -1 ��ʾ��ϵͳά��,���򲻲���)

	# ɾ���ڵ�
		void delete(final String path, int version)
		void delete(final String path, int version, VoidCallback cb,Object ctx)

		path
			* ·��
		version
			*  �ֹ����汾��(���ֵΪ -1 ��ʾ��ϵͳά��,���򲻲���)
	
	
	# ��ȡ�ӽڵ�
		List<String> getChildren(String path, boolean watch);
		void getChildren(String path, boolean watch, Children2Callback cb,Object ctx)
		void getChildren(String path, boolean watch, ChildrenCallback cb,Object ctx)
		List<String> getChildren(String path, boolean watch, Stat stat)

		List<String> getChildren(final String path, Watcher watcher)
		void getChildren(final String path, Watcher watcher,Children2Callback cb, Object ctx)
		void getChildren(final String path, Watcher watcher,ChildrenCallback cb, Object ctx)
		List<String> getChildren(final String path, Watcher watcher,Stat stat)
	
	# �жϽڵ��Ƿ����
		Stat exists(String path, boolean watch) 
		void exists(String path, boolean watch, StatCallback cb, Object ctx)
		Stat exists(final String path, Watcher watcher)
		void exists(final String path, Watcher watcher,StatCallback cb, Object ctx)



-------------------------------------
Ȩ��								 |
-------------------------------------
	# ��Ҫ��
		Id{
			private String scheme;
			private String id;
		}
			
		* ��Ȩ���ͺͶ���

		ZooDefs.Perms{
			int READ = 1 << 0;
			int WRITE = 1 << 1;
			int CREATE = 1 << 2;
			int DELETE = 1 << 3;
			int ADMIN = 1 << 4;
			int ALL = READ | WRITE | CREATE | DELETE | ADMIN;
		}
			
		* Ȩ�޳���

		ACL{
			private int perms;
			private Id id;
		}
		
		* ACL����
			
	
	# ��schemaΪdigestʱACLȨ�޵Ĵ���
		//��Ȩ����
		String digest = DigestAuthenticationProvider.generateDigest("poype:123456");
		//��Ȩ������
		Id id = new Id("digest", digest);
		//������Ȩ�޵���Ϣ
		ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, id);

		* ��schemaΪdigestʱ,��Ȩ������username:Base64(Sha1(username:password))��ʽ���ַ���
		* Ϊ�˼򻯱��,ZooKeeper�ṩ�˶�Ӧ�Ĺ�����
		* DigestAuthenticationProvider.generateDigest ����������ɶ�Ӧ����Ȩ�����ַ���
	
	# ��schemaΪipʱACLȨ�޵Ĵ���
		ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("ip","192.168.1.110"));
	
	# �ͻ�����������Ϣ
		zooKeeper.addAuthInfo("digest","poype:123456".getBytes());
