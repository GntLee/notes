----------------------------
mget ������ѯ				|
----------------------------
	# �����Ҫ����100����,ʹ�õ�������,��ô�ͻ����100�ε���������,��������,���ʹ��������ѯ,��ô����Ҫһ�����翪������
	# mget �൱��Ҫ
		* ��һ����Ҫ�����������ݵ�ʱ��,һ��Ҫʹ��mget,�������翪�����Դ������������

	# mget �����﷨
		GET /_mget
		{
		  "docs":[{
			  "_index":"user",			//ͨ�� _index ָ��index
			  "_type":"coder",			//ͨ�� _type ָ�� type
			  "_id":1					//ͨ�� _id ָ�� id
			},{
			  "_index":"user",
			  "_type":"coder",
			  "_id":2
			}]
		}
		
		* doc �Ǹ�����,���Զ���������
		* ��Ӧ�����е�docsҲ��һ������,���ص��Ƿ�������������
			{
			  "docs": [
				{
				  "_index": "user",
				  "_type": "coder",
				  "_id": "1",
				  "_version": 3,
				  "found": true,
				  "_source": {
					  ...
				  }
				},
				{
				  "_index": "user",
				  "_type": "coder",
				  "_id": "2",
				  "_version": 1,
				  "found": true,
				  "_source": {
					  ...
				  }
				}
			  ]
			}
	
	# ���������ѯ��document,��ͬһ��index��,���Ƿ�ͬһ��type�е�ʱ�����ʹ����һ���﷨
		GET /user/_mget
		{
			"docs":[{
				"_type":"coder",
				"_id":1
			}]
		}
		
		* ������Ҫ��docs��ָ��_type��_id����,��Ϊ��url���Ѿ�ָ���� index
	
	# ���������ѯ��document,����ͬһ��index,ʹһ��type�еĻ�,����ʹ�����������﷨
		GET /user/coder/_mget
		{
			"docs":[{
				"_id":1
			}]
		}
		* ������Ҫ��docs��ָ��_id,��Ϊurl�Ѿ�ָ����index��type

		GET /user/coder/_mget
		{
			"ids":[1,2]
		}
		* json��ʽ����,ֻҪ����ids�ֶμ���
