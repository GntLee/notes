--------------------------------
����				|
--------------------------------
	# �����ĵ�
		http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance



--------------------------------
����							|
--------------------------------

tickTime=2000
	* zookeeper������֮�������ͻ��˱���������ʱ����

initLimit=10
	* zookeeper���ܵĿͻ�������

syncLimit=5
	* Leader��Follower֮�䷢����Ϣ,����,Ӧ��ʱ��ĳ���
	* ����ܳ������ٸ�'tickTime'

dataDir=/tmp/zookeeper
	* ����Ŀ¼

dataLogDir
	* ��־Ŀ¼

clientPort=2181
	* �ͻ��˶˿�

server.[id]=[host]:[heart-port]:[election-port]
	* ��Ⱥ����
	* [id] �ڵ��id
	* [host] �ڵ��host
	* [heart-port] �ڵ�����/���ݽ����˿�
	* [election-port] �ڵ��ѡ�ٶ˿�
