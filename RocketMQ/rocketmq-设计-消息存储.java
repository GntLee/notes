-------------------------
��Ϣ�洢�ܹ�			 |
-------------------------
	# �洢Ŀ¼
		$HOME/store
			|-abort
			|-checkpoint
			|-commitlog
				|-00000000000000000000
			|-config
			|-consumequeue
				|-RMQ_SYS_TRANS_HALF_TOPIC
				|-RMQ_SYS_TRANS_OP_HALF_TOPIC
				|-SCHEDULE_TOPIC_XXXX
			|-index
			|-lock
	
	# commitlog
		* ��Ŀ¼�´洢�ľ���Producerд�����Ϣ�����Լ�Ԫ����(���Ǵ洢��������)����־�ļ�
		* �����ļ���СĬ��Ϊ 1GB, �ļ����� 20 ���ַ�,��ʾ��ʼ���ֽ�ƫ����, �����0 
			00000000000000000000	 // ��ʾ��һ���ļ�����ʼΪ 0 
			00000000001073741824	 // ��ʾ�ڶ����ļ�����ʼΪ 1073741824 (1073741824 byte = 1GB)
		
		* ��Ϣ˳���д�뵽��־�ļ���д���˺�д����һ���ļ�

		* Broker����ʵ�������е�Topic����һ����־�����ļ�����ΪCommitLog�����洢
	
	# consumequeue
		* ��Ϣ���Ѷ��У������Ŀ����Ҫ�������Ϣ���ѵ�����
		* RocketMQ�ǻ�������topic�Ķ���ģʽ����Ϣ���������������еģ����Ҫ����commitlog�ļ��и���topic������Ϣ�Ƿǳ���Ч��

		* Consumer�ɸ���ConsumeQueue�����Ҵ����ѵ���Ϣ
		* ConsumeQueue���߼����Ѷ��У���Ϊ������Ϣ��������������ָ��Topic�µĶ�����Ϣ��CommitLog�е���ʼ����ƫ����offset����Ϣ��Сsize����ϢTag��HashCodeֵ
		* onsumequeue�ļ����Կ����ǻ���topic��commitlog�����ļ�����֯��ʽ����
			$HOME/store/consumequeue/{topic}/{queueId}/{fileName}

		* consumequeue�ļ���ȡ������ƣ�����洢һ��������Ŀ
		* ÿһ����Ŀ��20���ֽڣ��ֱ�Ϊ8�ֽڵ�commitlog����ƫ������4�ֽڵ���Ϣ���ȡ�8�ֽ�tag hashcode
		* �����ļ���30W����Ŀ��ɣ�����������һ���������ÿһ����Ŀ��ÿ��ConsumeQueue�ļ���СԼ5.72M


	# index
		* IndexFile�������ļ����ṩ��һ�ֿ���ͨ��key��ʱ����������ѯ��Ϣ�ķ���
		* ��������
			$HOME/store/index/${indexFile}

		* �ļ��� ${indexFile} ���Դ���ʱ��ʱ��������ġ�����:20190718193445235
		* �̶��ĵ���IndexFile�ļ���СԼΪ400M��һ��IndexFile���Ա��� 2000W������

		* IndexFile�ĵײ�洢���Ϊ���ļ�ϵͳ��ʵ��HashMap�ṹ����rocketmq�������ļ���ײ�ʵ��Ϊhash����
	







