-----------------------
TopicConfig			   |
-----------------------
	# ���������,��broker�˶���Ĭ�ϵ�,�������Ĳ���û������,��ô�ͻ�ʹ��brokerĬ�ϵ�

	# ���������
		segment.bytes
		segment.ms
		segment.jitter.ms
		segment.index.bytes
		flush.messages
			* �ռ����˶�����Ϣ,��ǿ��ˢ�����
			* Ĭ��ֵ:Long.MAX_VALUE,Ҳ�����ò���ϵͳ����

		flush.ms
			* ��Ҫ�ȴ���òŻὫ��Ϣǿ��ˢ�µ�����
			* Ĭ��ֵΪ Long.MAX_VALUE ,���ò���ϵͳ������

		retention.bytes
		retention.ms
		max.message.bytes
		index.interval.bytes
		file.delete.delay.ms
			* �����ļ�֮ǰ���Եȴ�����ʱ��,Ĭ��Ϊ:60000ms Ҳ���� 1����

		delete.retention.ms
			* ��ʶ��ɾ�������ݱ�����ú�����ɾ��
			* Ĭ��86400000ms Ҳ����1 ��

		min.compaction.lag.ms
		min.cleanable.dirty.ratio
		cleanup.policy
			* ��־�������
				delete(Ĭ��)
				compcat

		unclean.leader.election.enable
		min.insync.replicas
		compression.type
			* ������� cleanup.policy=compcat
			* ��ô����ͨ���ò���������,��Ϣ��ѹ���㷨
				uncompressed
				snappy
				lz4
				gzip

		preallocate
		message.format.version
		message.timestamp.type
		message.timestamp.difference.max.ms
		message.downconversion.enable

