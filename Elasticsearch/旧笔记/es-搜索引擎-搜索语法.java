------------------------------------
_all metadata��ԭ�������			|
------------------------------------
	GET /_search?q=elaticsearch
		* ���е�index,���е�type,���е�document,���е�field������elaticsearch�ͷ�������
		* ��������������õ����
		* Ҳ����ͨ��urlȥ����index,type
	
	# ԭ��
		* �ڽ���������ʱ��,����һ��document,�������˶��field,��ʱes��Ѷ��field��ֵ���ַ�������,���һ���ܳ����ַ���
			{
				"name":"KevinBlandy",
				"age":23,
				"gender":"��"
			}
			=============== Ԫ����
			"KevinBlandy 23 ��"

		* ����ַ������� _all Ԫ����


------------------------------------
Query String �����﷨				|
------------------------------------
	GET /index/type/_search?q=content:elaticsearch
		* content�������elaticsearch�ؼ���	
		
	GET /index/type/_search?q=+content:elaticsearch
		*  ͬ��
		
	GET /index/type/_search?q=-content:elaticsearch
		* content����û����elaticsearch�ؼ���
	
	GET /_search
	GET /_index1,index2/type1,type2/_search
		* ����ڶ��index,type�ļ���
	
	
	* HTTPЭ��涨GETû��������,һ��Ҳ������GET�������body,��GET�����ʺ��ڼ�������
	* ���������֧�ֵĳ���,Ҳ����ʹ��:POST /_search


------------------------------------
Query String - ��ҳ	& ����			|
------------------------------------
	* ��ҳ
		GET /_search?from=0&size=10

		* from ��ʾ�ӵڼ������ݿ�ʼ
		* size ��ʾȡ��������
	
	* ����
		GET /_search?sort=age:asc

		* ����age��������


------------------------------------
DSL	- query							|
------------------------------------

	# ��������
		{
			"query":{
				"match_all": {}    
			}
		}
	
	# �����ֶ����ݼ���
		{
			"query":{
				"match":{
					"name":"KevinBlandy"
				}
			}
		}
		* ���� name ������������� KevinBlandy �ļ�¼
		* һ��matchֻ����һ������,��֧�ֶ������
	
	# ����ֶε���ֵͬ�ļ���
		{
		 "query": {
		   "multi_match":{
			 "query":"1",
			 "fields": ["id","name"]
		   }
		 }
		}
		* ���� id ������ 1 ���� name������ 1 ������
	
	# boolean ����
		* boolean ������һ�����߶������
		* ��������ö�ٹ̶�,����ֵ����Ϊ������߶���
			* must		һ�����߶������,����ȫ������
			* should	һ�����߶������,����һ������
			* must_not	һ�����߶������,����ȫ��������
			* filter	����(���ڣ����ڣ�С��)
		
		* must	����
		* should �κ�һ���������㼴��
		* minimm_should_match ����Ҫƥ�䵽һ��
			
	# boolean - filter
		{
		  "query": {
			"bool": {
			  "filter": {
				"range": {
				  "id": {			//�ֶ�
					"gte": 10,		//������ֵ
					"lte": 20
				  }
				}
			  }
			}
		  }
		}
		* range Ҳ����ֱ�ӷ���query����,�����Ļ�,�������ضȵļ���

	#  query �� filter
		* filter�������������������˳���Ҫ������
		* query,��ȥ����ÿ��document�����������������ض�,���Ұ�����ضȽ�������
		
		* filter����Ҫ������ضȷ���,����Ҫ������ضȷ�����������,ͬʱ�������õ�,�Զ�cache�ʹ��filter�Ĺ���
		* query�෴,��Ҫ������ضȷ���,���շ�����������,�����޷�cache���
	


------------------------------------
DSL	- �����������					|
------------------------------------
	"query":{
		"_source":[],
	}

	* ��ֵ��һ������,ָ��Ҫ�������ֶ�,�����Ǽ�������
	* ͨ���ַ���ִ������,֧��.���Ե���

------------------------------------
DSL	- ����							|
------------------------------------
	"query":{
		"sort":[
			{"field1":"desc"},
			{"field2":"asc"}
		],
	}

	* desc,����,asc ����

------------------------------------
DSL	- ���ƽ������					|
------------------------------------
	"query":{
		"from":1,
		"size":2,
	}

	* �ӵ�һ����¼��ʼ,������2�����

------------------------------------
�������							|
------------------------------------
	"query":{
		"highlight":{
			"fields":{
				"field":{}
			}
		}
	}

------------------------------------
�ж���ļ����Ƿ����				|
------------------------------------
	GET /index/type/_validate/query?explain

	{
	  "_shards": {
		"total": 1,
		"successful": 1,
		"failed": 0
	  },
	  "valid": true,
	  "explanations": [
		{
		  "index": "user",
		  "valid": true,
		  "explanation": "+ConstantScore(id:[2 TO 9223372036854775807]) #*:*"
		}
	  ]
	}

	* ���ر���,�Ӵ�ļ�����,������ʹ�ø�api����һ�¼�������Ƿ�������



------------------------------------
����ַ������������				|
------------------------------------
	# ��һ��field��������������ַ�������������
		PUT /user
		{
			"mappings":{
				"coder":{
					"properties":{
						"title":{
							"type":"text",
							"analyzer":"english",
							"field":{						//��һ�ν�������
								"raw":{
									"type":"string",		//����
									"index":"false",		//������
									"fielddata":true
								}
							}
						}
					}
				}
			}
		}
		
		* û������ɶɧ����
