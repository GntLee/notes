--------------------------
Kafka���ĸ���			  |
--------------------------
	# Broker 
		* һ̨Kafka������,������Ϣ�Ķ�,д,��
		* һ����Ⱥ�ɶ��Broker���,һ��Broker�������ɶ��Topic
		* Broker��Broker֮�䲻����M/S(����)�Ĺ�ϵ

	# Topic
		* ͬһ��Topic����Ϣ���Էֲ���һ�����߶���ڵ���
		* һ��Topic����һ�����߶�� Partition
		* ÿ����Ϣ�������ҽ�����һ��Topic
		* Producer������Ϣ����Ҫָ����������һ��Topic
		* Consumer������Ϣ,Ҳ����Ҫָ�������ĸ�Topic����Ϣ
		* ���԰������Ϊһ�� Queue
		* topic��ɾ����,Ĭ�ϻ���洢һ�����,�ڴ��ڼ仹���Խ�������

	# Partition
		* һ��Topic��Ϊ���Partition(������ʱ��ָ��)
		* һ��Partitionֻ�ֲ���һ��Broker��
		* һ��Partition�����϶�Ӧһ���ļ���
		* һ��Partition�������Segment,һ��Segment��Ӧһ���ļ�
		* Segment����һ�������ɱ��¼���
		* ��¼ֻ�ᱻappend��Segement��,���ᱻ������ɾ�������޸�
		* ���������־,ֱ��ɾ��һ�����߶��Segment
		* �ڲ���Ϣǿ����,�Ҷ���һ�����������

	
	# Producer 
		* ��Ϣ������,������broker������Ϣ
		* �����Ծ������ĸ�Partitionд��Ϣ,��������ѯ,����hash
		* ������Ϣ��ʱ�����û������key,��ô���ѡ��һ��Partition
		* ���������Ϣ������key,��ô�����hashѡ��һ��Partition
	
	# Consumer
		* ��Ϣ������,�����broker��ȡ��Ϣ����
		* һ��Consumerһ��ֻ�ܴ�һ��Partition����������,����ά������offset(ʹ��zookeeper)
		* ÿ��Consumer����������һ����,�����ָ��,��ôϵͳĬ������һ����
	
	# Consumer Group(CG)
		* ������Ⱥ��,һ��Ⱥ����һ�����߶��Consumer���
		* ����kafkaʵ�ֹ㲥�͵�����һ���ֶ�
		* һ��Topic�����ж��CG,topic����Ϣ�Ḵ��(������ĸ���,�Ǹ����ϵ�)�����е�CG
		* ��ÿ��CGֻ�����Ϣ������CG�е�һ��Consumer
		* ʵ�ֹ㲥
			* ���е������߶������ĳ�Ϊһ��CG
		
		* ʵ�ֵ���
			* ���е������߶���һ��CG
		
		* ʵ����һ��CG,����һ��������,���԰����'������'���Ϊһ����Ⱥ,��Ⱥ�е�ÿ��Consumer����һ���ڵ�

		* һ��CG�л�������Consumer,���������������Topic����Ϣ�Ĳ�����������,���һ������"�����ݴ�"��
		* ���CG�е�ĳ��ConsumerʧЧ,��ô�����ѵ�Partitions����������Consumer�Զ��ӹ�
		* Kafka�����ԭ�����,����һ��Topic,ͬһ��Group�в����ж���Partitions������Consumerͬʱ����
		* ������ζ��ĳЩConsumer���޷��õ���Ϣ(��������״̬)

		* һ��partition,ֻ�ܱ����������һ������������

	# Partition
		* Ϊ��ʵ����չ��,һ���ǳ����Topic���Էֲ������Broker(��������)��
		* һ��Topic���Է�Ϊ���Partition,ÿ��Partition��һ������Ķ���
		* Partition�е�ÿ����Ϣ���ᱻ����һ�������id(offset)
		* kafkaֻ��֤��һ��Partition�е�˳����Ϣ����Consumer,����֤һ��Topic������(���Partition��)��˳��
	
	# Offset
		* kafka�Ĵ洢�ļ����ǰ���offset.kafka������,��offset�����ֵĺô��Ƿ������
		* ��������λ��2049��λ��,ֻҪ�ҵ�2048.kafka���ļ�����
		* ��Ȼ the first offset����00000000000.kafka
	
	# ���ݹ��ڻ���
		* Kakfa����Ϣ���������֮�󲻻�ɾ��
		* ������ʱ�������ɾ��,Ĭ���Ǵ洢һ��

--------------------------
Producer���ؾ����HA����  |
--------------------------
	* producer�����û�ָ�����㷨,����Ϣ���͵�ָ����partition
	* ���ڶ��partiiton,ÿ��partition���Լ���replica,ÿ��replica�ֲ��ڲ�ͬ��Broker�ڵ���
	* ���partition��Ҫѡȡ��lead partition,lead partition�����д,����zookeeper����fail over(�����л�)
	* ͨ��zookeeper����broker��consumer�Ķ�̬�������뿪��

--------------------------
Consumer��pull����		  |
--------------------------
	* ����kafka broker��־û�����,brokerû��cahceѹ��,���,consumer�Ƚ��ʺϲ�ȡpull�ķ�ʽ��������
	* ��kafka���,�������Ѷ�
	* Consumer����������������������Ϣ��ȡ�ٶ�
	* consumer���������������ѡ������ģʽ,��������,�ظ�����,���ƶ�partition��λ��(offset)��ʼ���ѵ�

--------------------------
kafakϵͳ��չ��			  |
--------------------------
	* kafkaʹ��zookeeper��ʵ�ֶ�̬�ļ�Ⱥ��չ,����Ҫ���Ŀͻ���(producer��consumer)������
	* broker����zookeeperע�Ტ������ص�Ԫ����(topic,partition��Ϣ��)����
	* ���ͻ��˻���zookeeper��ע����ص�watcher,һ��zookeeper�����仯,�ͻ����ܼ�ʱ��֪��������Ӧ����
	* �����ͱ�֤����ӻ�ȥ��brokerʱ,��broker�������Զ�ʵ�ָ��ؾ���

--------------------------
partition �ķ���		  |
--------------------------
	* ������Broker(���蹲n��Broker)�ʹ������Partition����
	* ����i��Partition���䵽��(i % n)��Broker��,�������leader
	* ����i��Partition�ĵ�j��Replica���䵽��((i + j) % n)��Broker��


--------------------------
Kafka-Replica			  |
--------------------------
	# broker֮��replica����
		* replication�����ǻ���partition,������topic
		* kafka��ÿ��partition���ݸ��Ƶ����server��,�κ�һ��partition��һ��leader�Ͷ��follower(����û��)
		* ���ݵĸ�������ͨ��broker�����ļ����趨.
		* eader�������е�read-write����,follower��Ҫ��leader����ͬ��
		* Follower����һ��"consumer"������Ϣ�������ڱ�����־��
		* leader����������е�follower״̬,���follower"���"̫�����ʧЧ,leader���������replicasͬ���б���ɾ��

		* �����е�follower����һ����Ϣ����ɹ�,����Ϣ�ű���Ϊ��"committed",��ô��ʱconsumer����������,
		* ����ͬ������,��Ҫ��follower��leader֮�����������õ����绷��(ͬ��ˢ��)

		* ��ʹֻ��һ��replicasʵ�����,��Ȼ���Ա�֤��Ϣ���������ͺͽ���,ֻҪzookeeper��Ⱥ����.(��ͬ�������ֲ�ʽ�洢,����hbase��Ҫ"������"������)
		
	# �ж�һ��follower�������������2��
		* follower��Ҫ��zookeeper�������õ�����
		* �������ܹ���ʱ�ĸ���leader,�������̫��

		* ���ͬʱ��������2������,��ôleader����Ϊ��follower��"��Ծ��"
		* ���һ��followerʧЧ(serverʧЧ)�������̫��,
		* leader���������ͬ���б����Ƴ�[��ע:�����replicas���̫��,�����������leader��fetch����,ֱ���㹻"up-to-date",Ȼ���ٴμ��뵽ͬ���б���]
		* kafka�������replicas����,��Ϊ"ͬ���б�"��replicas��Ҫ�㹻��,�������ܱ�֤producer������Ϣʱ���ܵ�ACK���ӳٽ�С
	
	# ��leaderʧЧʱ,����followers��ѡȡ���µ�leader
		* ���ܴ�ʱfollower�����leader,�����Ҫѡ��һ��"up-to-date"��follower
		* kafka��leaderѡ�ٲ�û�в���"ͶƱ������"���㷨
		* ��Ϊ�����㷨����"�����ȶ���"/"ͶƱ����������"�������нϸߵ�Ҫ��,����kafka��Ⱥ�����,����Ҫ����N-1��replicasʧЧ
		* ����kafka����,ÿ��partition�����е�replicas��Ϣ��������zookeeper�л��,��ôѡ��leader����һ���ǳ��򵥵�����

		* ѡ��followerʱ��Ҫ���һ������,������leader server�����Ѿ����ص�partition leader�ĸ���
		* ���һ��server���й����partition leader,��ζ�Ŵ�server�������Ÿ����IOѹ��
		* ��ѡ����leader,��Ҫ���ǵ�"���ؾ���",partition leader���ٵ�broker������п��ܳ�Ϊ�µ�leader
		
		* ����������Ⱥ��,ֻҪ��һ��replicas���,��ô��partition�����Լ������ܶ�д����

--------------------------
MQ����Ϣģ��			  |
--------------------------
	# ��Ե�
		* һ����Ϣ,ֻ�ᱻ�����˸�Topic�������ߵ�����һ������������
		* ��Ե�
	
	# ��������
		* һ����Ϣ,ֻҪ�Ƕ����˸�Topic�������߶�������
		* �㲥

