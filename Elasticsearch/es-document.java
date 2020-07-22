--------------------
document			|
--------------------


--------------------
document - ����		|
--------------------
	# �����Ĵ���
		PUT /<index>/_doc/<id>?pretty
		{...}

		{
		  "_index" : "customer",
		  "_type" : "_doc",
		  "_id" : "1",
		  "_version" : 4,
		  "result" : "updated",
		  "_shards" : {
			"total" : 2,
			"successful" : 1,
			"failed" : 0
		  },
		  "_seq_no" : 3,
		  "_primary_term" : 1
		}
		
		_shards.total
			* ִ�����������ķ�Ƭ����(����Ƭ�͸�����Ƭ)������

		_shards.successful
			* �����ɹ��ĸ�������

		_shards.failed
			* ����ʧ�ܵĸ�������

		

		* ���Index������, ���Զ��Ĵ���
		* PUT��ʽ, �����ֶ���ָ��id����

	# ʹ��POST����
		POST /<index>/_doc
		{...}

		* POST��ʽ, �����ָ��id�Ļ�, ϵͳ�Զ�Ϊdoc����һ��uuid(VMVNQGsBor31qRgUZwnr)

	# ǿ�ƴ���
		* ���ֻ���½��ĵ�,�����滻�ĵ�,��ô����Ҫǿ�ƴ���(���ַ�ʽ)

		PUT /<index>/_doc/<id>?op_type=create

		PUT /<index>/_doc/<id>/_create
			* ���ַ�ʽ�Ƚϳ���
	
		* �����id��document�Ѿ�����,��ô����PUT�ɹ�,���׳��쳣


--------------------
document - ����		|
--------------------
	# �����ĸ���id����
		GET /<index>/_doc/<id>

		{
		  "_index" : "customer",
		  "_type" : "_doc",
		  "_id" : "1",
		  "_version" : 4,
		  "_seq_no" : 3,
		  "_primary_term" : 1,
		  "found" : true,
		  "_source" : {
			...
		  }
		}
	
	# ����id�ж��Ƿ����, ʹ�� HEAD ����
		HEAD /<index>/_doc/<id>

		����:200 - OK
		����:404 - Not Found

	
------------------------------------
document ·��						|
------------------------------------
	# document ·�ɵ� shard
		* index ���ݻᱻ��Ƭ������shard���Ǹ�,����һ��documentֻ�����һ��shard
		* ����� document Ӧ���ڴ����ĸ�shard,��ʵ����·��

	# ·���㷨
		* shard = hash(routing) % number_of_primary_shards
			> routing Ĭ��Ϊ document�� id(�����ֶ�ָ��)
			> hash�㷨
			> number_of_primary_shards : primary shad������

		* �ֶ�ָ�� routing
			PUT /<index>/<id>?routing=15
			GET /<index>/<id>?routing=15

			> ͨ�������ֶ�ָ��routing value������,���Ա�֤,ĳһ��documentһ����·�ɵ�һ��shard��
			> ��ô�ں�������Ӧ�ü���ĸ��ؾ���,�Լ�����������ȡ���ܵ�ʱ��,�Ǻ��а�����

	# primary shard �������ɱ�
		* һ�������仯��,��ô��ȡģ�㷨ʱ,�ͻ�������,���ӵ������ݶ�ʧ
		* ���� replica shard����������ɾ

---------------------------
���Ƭ				   |
---------------------------
	# �ĵ�
		https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-create-index.html

	# ִ����ɾ�ĵ�ʱ��, ����ͨ����Ƭ�Ļ�Ծ������֤дһ����
	# ElasticSearch 5.0֮��ʹ�� wait_for_active_shards ������consistency ��ָ��дһ���Լ���
		POST /<index>/_doc/<id>?wait_for_active_shards=1

	# ��Чֵ��all���κ�������
		all
			* ���е�primary shard��replica shard���ǻ�Ծ��,�ſ���ִ��

		�κ�������
			* TODO

	# �����Ծ�ڵ���������ָ��������,���ܻᵼ���޷�ִ��д����
		* Ծ�ڵ���������ָ��������ʱ,Ĭ�� wait 1����(������Ծ��shard��������)
		* ����ͨ�� timeout ����������(��λĬ���Ǻ���,����ͨ�����s������Ϊ��)
			PUT /<index>/_doc/<id>?timeout=60s

---------------------------
����ˢ��				   |
---------------------------
	# д����ֻ��������Ƭִ��, ����Ƭд���, ��ͬ������������
		* ���ܵ��µ��������, ����Ƭ��δͬ����������Ƭ, ���Ѿ���Ӧ���ͻ��˳ɹ���Ϣ
		* �ͻ����ٴμ�����������Ƭ, ���ڻ�δͬ�����, ���Լ�����������
		
	# ����ͨ������������, �Ƿ�Ҫ�ȵ�����ͬ�����, �ŷ��ظ��ͻ��˳ɹ�

	# ͨ��query��������:refresh
		true (���ַ���Ҳ����)
			* ǿ��ִ��ˢ��
			* ���������ͻ���, �����ڿͻ��˵Ĳ�����Ӧ������ˢ����ص�����Ƭ�͸�����Ƭ

		wait_for
			* �ȴ�ˢ�µķ���
			* �������ͻ���, ����Ӧ֮ǰ, �ȴ�ˢ�����������ĸ���, �ⲻ��ǿ������ˢ��, ���ǵȴ�ˢ�·���
			* ES ��ÿ index.refresh_interval(Ĭ��ֵΪ1��)�Զ�ˢ���Ѿ����ĵ���Ƭ, ��������Ƕ�̬��
			* ����Refresh API�����κ�֧������API�Ͻ�refresh����ΪtrueҲ�ᵼ��ˢ��, �Ӷ������Ѿ����еĴ���refresh=wait_for��������Ӧ


		false (Ĭ��)
			* ���ȴ�ˢ��, ��������
	
		PUT  /<index>/_doc/<id>?refresh=false
	
	# refresh=wait_for Ҳ����ǿ��ˢ��
		* ����������� index.max_refresh_listener(Ĭ������Ϊ1000)
		* ����ȴ�ˢ�µ������, �Ǹ���Ƭ�ϳ���refresh=wait_for����, ��ô���������Ϊ�ͺ������Ѿ���refresh����Ϊtrueһ��
		* ����ǿ��ˢ��, �Ᵽ֤�˵�refresh=wait_for���󷵻�ʱ, ���ĸ��Ķ��������ǿɼ���, ͬʱ��ֹ�Ա�����������ʹ��δ������Դ

		* ���һ��������Ϊ�ľ��˼�������۶�ǿ��ˢ��, ��ô������Ӧ������"forced_refresh": true

--------------------
_all metadata		|
--------------------
	# �½�һ��doc, ES�ѻ�doc�������ֶ�ֵ, ����, ���һ�������ַ���
		* ���ַ�������doc�� _all field ��ֵ

	# �Ը��ַ�����������, ���������ʱ��, û��ָ��������field, ��ôĬ�Ͼ������� _all field
	

--------------------
���ݽṹ�ĸı�		|
--------------------
	# һ��json��doc, �ᷢ��һ�����ݽṹ�ĸı�
		* ������ʽ�洢, ���ᱻת��Ϊһ��

	# ����ĸı�
		{
			"name":"Java���ŵ�����",
			"author":{
				"name":"KevinBlandy",
				"age":23
			}
		}

		{
			"name":"Java���ŵ�����",
			"author.name":"KevinBlandy",
			"author.age":"KevinBlandy"
		}

	# ����ĸı�
		{
			"name":"KevinBlandy",
			"skill":[{
				"name":"Java",
				"proficiency": 90
			},{
				"name":"Python",
				"proficiency": 80
			}]
		}

		{
			"name":"KevinBlandy",
			"skill.name":["Java", "Python"],
			"skill.proficiency":[90, 80]
		}


--------------------------------------------
��field��������������ַ������������		|
--------------------------------------------
	# �� String field ��������, ���������׼ȷ, ��Ϊ�ִʺ����Ƕ������, �ٴν��������ʱ��, �������������Ҫ��
	# ͨ���Ľ��������, ��һ�� String field ������������, һ���ִ�, ��������, һ�����ִ�, ��������

		{
			"title":{
				"type":"string",
				"analyzer":"english",
				"fields": {
					"raw": {
						"type": "string",
						"index": "not_analyzed",
						"fielddata": true
					}
				}
			}
		}

		GET /_search
		{
			"query":{
				"match":{
					"title":"Hello"
				}
			},
			"sort": "title.raw"
		}
	
