----------------------
Partition			  |
----------------------
	# controller����ִ��partition�ķ���,����leader��ѡ��

	# ���ڶั���������,�ᾡ���Ѷ������,���䵽��ͬ��broker��
	
	# kafka��Ϊpartitionѡ��һ��leader,֮�����и�partition������,ʵ�ʲ����Ķ���leader,Ȼ����ͬ����������follower
		
	# ���broker崻�,����leader�ڸ�broker�ϵ�partition��������ѡ��,ѡ��һ��leader
		* ����ֲ�ʽ�ļ��洢ϵͳ�������Զ����и��Ʊ��ָ�����
	
	# Controller��ô����partition
		* ������Broker(���蹲n��Broker)�ʹ������Partition����
		* ����i��Partition���䵽��(i mod n)��Broker��(�������leader)
		* ����i��Partition�ĵ�j��Replica���䵽��((i + j) mode n)��Broker��

	# Controller��ôѡleader
		* controller����Zookeeper��'/brokers/ids'�ڵ���ע��Watch,һ����broker崻�,������֪��
		* roker崻���,controller�ͻ���ܵ�Ӱ���partitionѡ����leader
		* controller��zk�� '/brokers/topics/[topic]/partitions/[partition]/state'��,��ȡ��Ӧpartition��ISR(in-sync replica��ͬ���ĸ���)�б�
		* ��ISR�б���ѡһ��������leader
		* ѡ��leader��,����zk,Ȼ����'LeaderAndISRRequest'����Ӱ���broker,�����Ǹı�֪������
			* ����ʹ��zk֪ͨ,����ֱ�Ӹ�broker����rpc����(Ҳ������Ϊ�������� )
		* ��� ISR�б��ǿ�,��ô���������,���ѡһ��replica��leader,���߸ɴ����partition����崻�(������)
		* ���ISR�б���л���,����Ҳ崻���,��ô�����Ե�ISR�Ļ��������
		* ԭ��崻���leader�ָ���,controller�����°�������Ϊleader?????
			
	# ���������ͬ��
		* ����˴�����follower��leader������ȡ������ͬ��
		*  ���Ǿ���Ŀɿ���,������������������
		* ������������Ϣ��ʱ��,ͨ��'request.required.acks'�������������ݵĿɿ���
			0
				* ����ȥ��������,������broker�Ƿ���ɹ�,���ܶ�����
			1
				* дLeader�ɹ���ͷ���
				* ������replica����ͨ��fetcherȥͬ����,����kafka���첽д,�����л����ܶ�����
			-1
				* Ҫ�ȵ�isr�����л���ͬ���ɹ�,���ܷ��سɹ�
				* ��ʱȡ���������Ļ���,ǿһ��,���ᶪ����
				* ���ISR����'min.insync.replicas'ָ������Ŀ,��ô�ͻ᷵�ز�����
		
		* ISR�б��еĻ����ǻ�仯��,��������'replica.lag.time.max.ms',���ûͬ���ͻ��ISR�б����޳�

		* ��ISA��ѡ��leader��,follower��Ӱ��Լ���־����һ����ˮλ����ļ�¼ȥ��,Ȼ��ȥ��leader���µ�����
			* �µ�leaderѡ������,follower���������,���ܱ���leader��,����Ҫ��ȡ
			* ��ˮλ����˼,����partition��leader,'��������ISR�ж��е�����һ����¼',���������ֻ�ܶ�����ˮλ
		
		* ��leader�ĽǶ���˵��ˮλ�ĸ��»��ӳ�һ��
			* ����д����һ������Ϣ,ISR�е�broker��fetch����,����ISR�е�brokerֻ������һ�ֵ�fetch�в��ܸ���leader
			* �������������ˮλ�ӳ�һ��,��һЩ�����,kafka����ֶ����ݺ��������ݲ�һ�µ����
			* '0.11��ʼ,ʹ��leader epoch�������ˮλ'

		
		
		
