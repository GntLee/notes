----------------------------
ZAB�㷨						|
----------------------------
	# Zookeeper Atomic Broadcast (Zookeeperԭ�ӹ㲥)
		* ���е�д����������ͨ��Leader���,Leaderд�뱾����־���ٸ��Ƶ����е�Follower�ڵ�
		* һ��Leader�ڵ��޷�����,ZABЭ���ܹ��Զ���Follower�ڵ�������ѡ��һ�����ʵ������,���µ�Leader
		* �ù��̼�Ϊ�쵼ѡ��,���쵼ѡ�ٹ���,��ZABЭ������Ϊ��Ҫ�͸��ӵĹ���

	# Leader����д����,��Ҫ��Ϊ�岽
		* �ͻ�����Leader����д����
		* Leader��д������Proposal����ʽ��������Follower���ȴ�ACK
		* Follower�յ�Leader��Proposal�󷵻�ACK
		* Leader�õ���������ACK(Leader���Լ�Ĭ����һ��ACK)�������е�Follower��Observer����Commmit
		* Leader�����������ظ��ͻ���

		* Leader������Ҫ�õ�Observer��ACK,��Observer��ͶƱȨ
		* Leader����Ҫ�õ�����Follower��ACK,ֻҪ�յ������ACK����,ͬʱLeader������Լ���һ��ACK
		* Observer��Ȼ��ͶƱȨ,������ͬ��Leader�����ݴӶ��ڴ��������ʱ���Է��ؾ������µ�����
	
	# δCommit������Ϣ�Կͻ��˲��ɼ�

	# ֧�ֵ�leaderѡ���㷨
		* ��ͨ��electionAlg����������ZooKeeper�����쵼ѡ�ٵ��㷨

		0 ����UDP��LeaderElection

		1 ����UDP��FastLeaderElection

		2 ����UDP����֤��FastLeaderElection

		3 ����TCP��FastLeaderElection(Ĭ��,���������㷨�Ѿ�������,�����мƻ���֮��İ汾�н����ǳ���ɾ��������֧��)


	# FastLeaderElectionԭ��
		* myid
			
			* ÿ��ZooKeeper������,����Ҫ�������ļ����´���һ����Ϊmyid���ļ�
			* ���ļ���������ZooKeeper��ȺΨһ��ID(����)


		* zxid
			
			* ������RDBMS�е�����ID,���ڱ�ʶһ�θ��²�����Proposal ID
			* Ϊ�˱�֤˳����,��zkid���뵥������
			* ���ZooKeeperʹ��һ��64λ��������ʾ,��32λ��Leader��epoch,��1��ʼ,ÿ��ѡ���µ�Leader,epoch��һ
			* ��32λΪ��epoch�ڵ����,ÿ��epoch�仯,������32λ���������,������֤��zkid��ȫ�ֵ�����
		



	# ������״̬
		LOOKING ��ȷ��Leader״̬,��״̬�µķ�������Ϊ��ǰ��Ⱥ��û��Leader,�ᷢ��Leaderѡ��

		FOLLOWING ������״̬,������ǰ��������ɫ��Follower,������֪��Leader��˭

		LEADING �쵼��״̬,������ǰ��������ɫ��Leader,����ά����Follower�������

		OBSERVING �۲���״̬,������ǰ��������ɫ��Observer,��FolowerΨһ�Ĳ�ͬ���ڲ�����ѡ��,Ҳ�����뼯Ⱥд����ʱ��ͶƱ