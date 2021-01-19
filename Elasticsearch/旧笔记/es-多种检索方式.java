------------------------
���ּ�����ʽ			|
------------------------
	# һ����˵��6����
		1,query string search
		2,query DSL
		3,query filter
		4,full-text search
		5,phrase search
		6,highlight search

------------------------
������Ӧ				|
------------------------
	# ��Ӧ��
		{
		  "took": 2,				
		  "timed_out": false,	
		  "_shards": {			
			"total": 5,
			"successful": 5,
			"skipped": 0,
			"failed": 0
		  },
		  "hits": {		
			"total": 1,	
			"max_score": null,
			"hits": [
			  {
				"_index": "user",
				"_type": "coder",
				"_id": "3",
				"_score": null,
				"_source": {
				  "id": 3,
				  "name": "Rocco",
				  "age": 21,
				  "gender": "girl",
				  "hobby": [
					"basketball",
					"Sing",
					"Write the code"
				  ],
				  "skill": {
					"java": {
					  "level": "9"
					},
					"python": {
					  "level": "9"
					},
					"javascript": {
					  "level": "6"
					}
				  }
				},
				"sort": [
				  21
				]
			  }
			]
		  }
		}
		
		took
			* �ķѺ���
		timed_out
			* �Ƿ�ʱ
		_shards
			* ���ݲ��Ϊ��5����Ƭ,�������������е�primary shard(����ĳ��replica shar)
		hits
		hits.total
			* �ܼ�¼����
		hits.max_score
			* document����һ��search����ضȵ�ƥ��ϵ��,Խ���,��Խƥ��,����ҲԽ��
		hits.hits
			* document����ϸ����

------------------------
query string search		|
------------------------
	GET /{index}/{type}/_search?q=[k]:[v]&sort=[k]:[desc/asc]
		* ����k=v�����м�¼,���Ұ���k����desc/asc����

		* /user/coder/_search?q=gender:boy&sort=age:asc
		- ����������gender=boy���û�,���Ұ���age��������
	
	* ��򵥵ļ���,���е�����,��������ͨ��uri��������
	* һ�������������ٵ�ʹ��

	
------------------------
query DSL				|
------------------------
	# DSL:Domain Specied Language(�ض�����������)
	
	# ����gender=boy����������,���Ұ���age��������,id��������
			{
				"query":{
					"match":{
						"gender":"boy"
					}
				},
				"sort":[{"age":"asc"},{"id":"desc"}]
			}
		
	# ��ҳ��ѯ
		{
			"query":{
				"match_all":{}
			},
			"from":1,		//�ӵڼ������ݿ�ʼ����
			"size":2		//�����ڼ���
		}
	
	# ��������ָ�����ֶ�����
		{
			"query":{
				"match_all":{}
			},
			"_source":["name","id","skill.java"]	//��������ָ�����ֶ�
		}
		* ��������Documnet�����name,id����.�Լ�skill����(����)�����java����
		


	# ������Ķ������

		{
			"query":{
				"match_all":{
					
				},
				"match":{
					"key":"value"
				}
			},
			"sort":[
				{"key1":"desc"},
				{"key2":"asc"}
			],
			"from":1,
			"size":2,
			"_source":[""]
		}
		
		query.match_all
			* ��������,��ֵ��һ���ն���{}

		query.match
			* ��������������
			* ��ֵ��һ������,ͨ��kv���������
		from
			* �ӵڼ������ݿ�ʼ����,limit�ĵ�һ������
		size
			* ÿҳ��ʾ�ļ�¼��
		_source
			* ��ֵ��һ������,ָ��Ҫ�������ֶ�,�����Ǽ�������
			* ͨ���ַ���ִ������,֧��.���Ե���
			* "_source":["name","age","skill.java"]		//��������name,age��skill���������java����

------------------------
query filter			|
------------------------
	# �����ݽ��й���,�ǻ���DSL���еĹ���
	
	# ���������Ա�Ϊ:girl,���� gt/lt/le/ge/ne 23�ļ�¼
		{
			"query":{
				"bool":{
					"must":{
						"match":{
							"gender":"girl"
						}
					},
					"filter":{
						"range":{
							"age":{"gt/lt/le/ge/ne":"23"}
						}
					}
				}
			}
		}

------------------------
full-text search		|
------------------------
	# ȫ�ļ���
		{
			"query":{
				"match":{
					"key":"value1 value2"
				}
			}
		}
		
		* ���� key��������� value1,value2�ؼ��ֵļ�¼
		* ����,������

------------------------
full-phrase search		|
------------------------
	# ��������
		{
			"query":{
				"match_phrase":{
					"key":"value"
				}
			}
		}

		* ��ȫ�ļ����෴,Ҫ��key��value������ȫƥ��,�Żᱻ��������
		* ����ȫƥ��,������
	

------------------------
full-highlight search	|
------------------------
	# ����
	{
		"query":{
			"match":{
				"key":"value"
			}
		},
		"highlight":{
			"fields":{
				"key":{}
			}
		}
	}

--------------------
�ۺ�				|
--------------------
	{
		"aggs":{
			"group_by_tags":{
				"terms":{
					"field":"tags"
				}
			}
		}
	}

	* ����tags�ֶν��оۺϼ���,����ÿ��tag�µ���Ʒ����

	{
		"aggs":{
			"group_by_tags":{
				"terms":{
					"field":"tags",
					"order":{
						"avg_price":"desc"
					}
				},
				"aggs":{
					"avg_price":{
						"field":"price"
					}
				}
			}
		}
	}

	* ����tags�ֶμ������,Ȼ������ÿ���ƽ��ֵ
	* ���� avg_price ����������

	