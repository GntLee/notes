# �޸Ľڵ����ݴ�С������
	* �������Ϳͻ��˶������Ϊ�ϸ��鲢����ÿ��Znode�����ݴ�Լ��1MB(Ӧ�þ���С���ֵ)
	* ����ͨ���޸� jute.maxbuffer �������޸�
	
	* jute.maxbuffer Ĭ��ֵ1048575,��λ�ֽ�,�������õ������ݽڵ�(ZNode)�Ͽ��Դ洢��������ݴ�С
	* ��Ҫע�����,���޸ĸò�����ʱ��,��Ҫ��zookeeper��Ⱥ�����з�����Լ��ͻ��������ò�����Ч


# ����˵��޸�
	* zkServer.sh ���� -Djute.maxbuffer ����


# �ͻ��˵��޸�
	* �����ֱ��ʹ��zookeeper�Դ���zkCli.sh��ȡ����,��ôҪ��zkCli.sh������ -Djute.maxbuffer ����

	* ���ʹ�ÿͻ���(����),������ System.properties ������(�ڳ�ʼ��zookeeper�ͻ���֮ǰ)
		System.setProperty("jute.maxbuffer",String.valueOf(1024 * 10000L)); //10MB
		
