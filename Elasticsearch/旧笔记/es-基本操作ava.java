------------------------
��������				|
------------------------

------------------------
�����Ĳ���				|
------------------------
	# �½�
		PUT		/{index_name}	
		{
			"acknowledged": true,
			"shards_acknowledged": true,
			"index": "test_index1"
		}
	
	# ɾ��
		DELETE	/{index_name}
		{
			"acknowledged": true
		}

------------------------
Document-����			|
------------------------
	PUT /{index_name}/{type_name}/{id}
	{
	  "_index": "[index_name]",			//index����
	  "_type": "[type_name]",			//type����
	  "_id": "[id]",					//document��id
	  "_version": 1,					//�汾��
	  "result": "created",				//ִ�н��
	  "_shards": {						
		"total": 2,	
		"successful": 1,
		"failed": 0
	  },
	  "_seq_no": 0,
	  "_primary_term": 1
	}

------------------------
Document-����			|
------------------------
	# ����-1
		POST /{index_name}/{type_name}/{id}	
		{
		  "_index": "test_index",
		  "_type": "product",
		  "_id": "1",
		  "_version": 2,			
		  "result": "updated",
		  "_shards": {
			"total": 2,
			"successful": 1,
			"failed": 0
		  },
		  "_seq_no": 1,
		  "_primary_term": 1
		}

		* ������ֱ���ύ��Ҫ�޸ĵ��ֶμ���
		* ���ܱ����ύ,�Ƿ��гɹ��޸��ֶ�,resultֵ��ԶΪ:'updated'
		* _version�ֶαػ��1
		* �������Ϊǿ�Ƹ���
	
	# ����-2
		POST /{index_name}/{type_name}/{id}/_update
		* ���ַ�ʽ,��۵�JSON�������仯
			{
				"doc":{
					//��Ҫ�޸ĵ��ֶ�
				}
			}
		* ��������ύδ�޸����ݵĻ�,��ôresult�ֶ�ֵΪ:'noop',����û��:'_seq_no'��'_primary_term'�ֶ�,versionҲ����+1
		* �������Ϊ��ǿ�Ƹ���,����������Ҫ���µ��ֶ�


	# �滻
		PUT  /{index_name}/{type_name}/{id}	
	
	# �Ա�
		* ֱ���滻�Ļ�,version�ᱻ����Ϊ1,���µĻ�,version���1
		* ���������,result�ֶ�ֵΪ"updated"
		* ֱ���滻����,�滻�������,����������е�����
		* ������,������Ҫ�ṩ���µ��ֶμ���

------------------------
Document-ɾ��			|
------------------------
	DELETE /{index_name}/{type_name}/{id}
	{
	  "_index": "test_index",
	  "_type": "product",
	  "_id": "1",
	  "_version": 24,
	  "result": "deleted",
	  "_shards": {
		"total": 2,
		"successful": 1,
		"failed": 0
	  },
	  "_seq_no": 23,
	  "_primary_term": 1
	}

	* ���δɾ���ɹ�(Document������),��ôresultֵΪ: not_found

------------------------
Document-����			|
------------------------
	GET /{index_name}/{type_name}/{id}
	{
	  "_index": "[index_name]",
	  "_type": "[type_name]",
	  "_id": "1",
	  "_version": 1,
	  "found": true,		//�Ƿ�ɹ�������
	  "_source": {			//����
		"id": 1,
		"name": "java",
		"price": 30,
		"producer": "this is producer",
		"tags": [
		  "t1",
		  "t2"
		]
	  }
	}
	