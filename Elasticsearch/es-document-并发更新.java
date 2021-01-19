----------------------------
 �ֹ�����������				|
----------------------------
	# �ĵ�
		https://www.elastic.co/guide/en/elasticsearch/reference/current/optimistic-concurrency-control.html


	# �����Ľ���л��������Ԫ����
		{
		  ...
		  "_seq_no" : 28,
		  "_primary_term" : 1
		}
	
		
	# CAS����
		PUT /<index>/_doc/<id>?if_seq_no=<_seq_no>&if_primary_term=<_primary_term>
		
		POST /<index>/_doc/<id>?if_seq_no=<_seq_no>&if_primary_term=<_primary_term>

		POST /<index>/_update/<id>?if_seq_no=<_seq_no>&if_primary_term=<_primary_term>


		* ͨ����ѯ����: if_seq_no �� if_primary_term �����ư汾��,�������ʧ��, �׳��쳣
			{
			  "error": {
				"root_cause": [
				  {
					"type": "version_conflict_engine_exception",
					"reason": "[1]: version conflict, required seqNo [8], primary term [1]. current document has seqNo [9] and primary term [1]",
					"index_uuid": "NhO0l2JpRW-MwwnQpjexcA",
					"shard": "0",
					"index": "goods"
				  }
				],
				"type": "version_conflict_engine_exception",
				"reason": "[1]: version conflict, required seqNo [8], primary term [1]. current document has seqNo [9] and primary term [1]",
				"index_uuid": "NhO0l2JpRW-MwwnQpjexcA",
				"shard": "0",
				"index": "goods"
			  },
			  "status": 409
			}
		
		* ����ǲ��ָ���, ��ôֻ���ڸ��³ɹ�(���µ��������޸�)��ʱ��Ż�ȥ�жϰ汾��

----------------------------
�汾����					|
----------------------------
	# ÿ��doc����һ�� version �ֶ�
		{
		  "_version" : 20,
		}

		 * Ĭ�ϴ�1��ʼ, ����ÿ�θ���ʱ����, ����ɾ��
		 
	# ִ���޸�ʱ, ʹ�ð汾��
		PUT /<index>/_doc/<id>?version=<_version>&version_type=<version_type>
		{
			"message" : "elasticsearch now has versioning support, double cool!"
		}

		* CAS����,���ʹ��doc�ڲ��İ汾����, ʹ�� if_seq_no �� if_primary_term 
		* CAS����,���ʹ���ⲿ�İ汾��, ��ʹ�� version
	
	# version_type ,ö��ֵ
		internal
			* ���ڸ����汾��洢�ĵ��İ汾��ͬʱ�Ŷ��ĵ���������
			* �Ѿ���֧����: internal versioning can not be used for optimistic concurrency control. Please use `if_seq_no` and `if_primary_term` instead

		external / external_gt
		external_gte

	# ���԰Ѱ汾�Ž����ⲿ�������, external version
		* es�ṩ��һ��feature,���Բ�ʹ�����ݲ���_version�汾�������в�������
		* ���Ի����Լ�ά����'version�汾��'�����в�������
		* ʹ�ó���
			��mysql��Ҳ����һ������,Ӧ��ϵͳ�����ά����һ���汾��,��ʱʹ���ֹ������Ƶ�ʱ��,����ʹ��es��version,������ʹ��Ӧ��ϵͳ�е�version
		
		* version�����﷨
			?version=<_version>&version_type=external

		* �� version_type=external ��ʱ��,version��������Ҫ���ڵ�ǰ��_version���ܸ��³ɹ�
		* ���޸ĳɹ���,���һ��document��_version�޸�Ϊversion������ֵ