--------------------
document�ĸ���		|
--------------------
	# ǿ�Ƹ���
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

		* ��������Ҫ�ύ�����ֶ�,�����ڵ��ֶλᱻɾ��
		* ���ܱ����ύ,�Ƿ��гɹ��޸��ֶ�,resultֵ��ԶΪ:'updated'
		* ���������޸�,_version�ֶαػ��1
		* �������Ϊǿ�Ƹ���
	
	# ��ǿ�Ƹ���
		POST /{index_name}/{type_name}/{id}/_update
		* ���ַ�ʽ,�ύ��JSON�������仯
			{
				"doc":{
					//��Ҫ�޸ĵ��ֶ�
				}
			}
		* ���Խ����ύ������Ҫ���µ��ֶ�
		* ��������ύδ�޸����ݵĻ�,��ôresult�ֶ�ֵΪ:'noop',����û��:'_seq_no'��'_primary_term'�ֶ�,
		* �����ύ���޸����ݵ���,��ǿ�Ƹ��µķ��ؽ����һ����
		* ֻ�����������޸ĵ�ʱ��,version +1
		* �������Ϊ��ǿ�Ƹ���
		* partial update(���ָ���)

	# ȫ���滻
		PUT  /{index_name}/{type_name}/{id}	

		* ���id�Ѿ�����,��ôԭ����document���ᱻ����ɾ��,���ǻᱻ���Ϊ: delete
		* ��es������Խ��Խ���ʱ��,es���ں�̨�Լ����İѱ��Ϊ:delete ��document����ɾ����
		* _version ʼ�ջ� +1
	
		
--------------------
document��ǿ�ƴ���	|
--------------------
	# �����ĵ���ȫ���滻���﷨��һ����
	# ���ֻ���½��ĵ�,�����滻�ĵ�,��ô����Ҫǿ�ƴ���(���ַ�ʽ)
		PUT /index/type/id?op_type=create
		PUT /index/type/id/_create
			* ���ַ�ʽ�Ƚϳ���
	
		* �����id��document�Ѿ�����,��ô����PUT�ɹ�,���׳��쳣

--------------------
document��ɾ��		|
--------------------
	# document���ᱻ����������ɾ��,ֻ�ᱻ���Ϊdelete,������Խ��Խ�������,�ں�̨�Զ���ɾ��

		DELETE /index/type/id

----------------------------
partial update ���			|
----------------------------
	# partial update
	
		POST /index/type/id/_update
		{
			"doc":{
				"������Ҫ�޸ĵ�����,����Ҫȫ��������"
			}
		}
		* �������ȽϷ���,������Ҫ�����޸��޸ĵĲ�������
		* '����Ҫ�ȶ�ȡ����,���޸�,ֱ���ύ��Ҫ�޸ĵ��ֶμ���'
		
	# �ڲ�ԭ��
		* ��ʵes�� partial update ��ִ��,��ʵ��ȫ���滻������һ����
		* ��ִ�� partial update ��ʱ��,�ڲ����ǻ�͵͵���Ȳ�ѯ������document������,Ȼ��'������Ҫ���µ��ֶ�'
		* ������ɺ�,�Ѿɵ�document���Ϊdelete,��д���µ�doucment
	
	# �ŵ�
		1,���в�ѯ,�޸�,��д����,��������һ��shard�ڲ�,�����������������ݴ���Ŀ���
			* ��ȡ����,��д����
		2,�������޸ĺͲ�ѯ�е�������,������Ч���ٳ�ͻ�����
			* ��ǰ�û����޸Ľ���,ռ��ʱ�����,��ʵ��document�Ѿ����������û��������޸�,��ǰ�û�ִ�и��»ᷢ����ͻ
	

----------------------------
partial update �ֹ�����������|
----------------------------
	# Ĭ�ϻ�ʹ���ֹ����Ĳ������Ʋ���
		* partial update �ύ��shard��,����ȥ���ݶ�ȡ��document������field,�Լ�version
		* �޸�partial update�ύ�Ĳ���field,Ȼ���д,�ڻ�д��ʱ��,ʹ��version������������

	# retry����
		* ��ִ���޸�ʱ,����version����
		* ��һ�ζ�ȡdocumnet�����°汾��
		* �������µİ汾��ȥ����document
		* ���ʧ��,���ظ�����������,�ظ��Ĵ�������ͨ�� retry_on_conflict ֵ������

		POST /user/coder/1/_update?retry_on_conflict=5
	
	# Ҳ�����ֶ�ͨ��������� _version �����Ʋ���,��version��һ��ʱ,������쳣


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
			> number_of_primary_shards ��primary shad������

		* �ֶ�ָ�� routing
			PUT /index/type/id?routing=15
			GET  /index/type/id?routing=15

			> ͨ�������ֶ�ָ��routing value������,���Ա�֤,ĳһ��documentһ����·�ɵ�һ��shard��
			> ��ô�ں�������Ӧ�ü���ĸ��ؾ���,�Լ�����������ȡ���ܵ�ʱ��,�Ǻ��а�����

	# primary shard �������ɱ�
		* һ�������仯��,��ô��ȡģ�㷨ʱ,�ͻ�������,���ӵ������ݶ�ʧ
		* ���� replica shard����������ɾ

----------------------------
document ��ɾ���ڲ�ʵ��ԭ��	|
----------------------------
	1,�ͻ���ѡ��һ��node��������,��node���� coordinating node(Э���ڵ�)
		* ÿ��node,��֪��document���ĸ�node��

	2,coordinating node,��document����·��,������ת������Ӧ��node(primary shard)
		* ��ɾ��,ֻ����primary shard����

	3,ʵ��node�ϵ�primary shard��������,Ȼ�������ͬ����replica node
		

	4,coordinating node,�������primary node������replicat node�����֮��,��Ӧ�ͻ���
	


-------------------------------
documentдһ����	consistency	|
--------------------------------
	# ��ִ����ɾ�ĵ�ʱ��,�����ύһ�� consistency ����,��ָ��дһ���Եļ���
		PUT /index/type/id?consistency=one
	
	# ��ֵ��һ��ö��ֵ
		one
			* ֻҪ��һ��primary shard��active��Ծ����,�Ϳ���ִ��

		all
			* ���е�primary shard��replica shard���ǻ�Ծ��,�ſ���ִ��

		quorum(Ĭ��)
			* �� number_of_replicas > 1ʱ�û��ƲŻ���Ч
			* shard�л�Ծ��shard��������/���� quorum, �ſ���ִ��д����
			* ���㹫ʽ: quorum = int((primary + number_of_replicas) / 2) + 1
				primary				-> primary shard������
				number_of_replicas	-> ÿ��primary shard�м���replica shard

			* �����Ծ�ڵ��������� quorum ����,���ܻᵼ��quorum����ȫ,���������޷�ִ��д����
			* quorum����ȫʱ,wait Ĭ��1����(������Ծ��shard��������),����ͨ�� timeout ����������(��λĬ���Ǻ���,����ͨ�����s������Ϊ��)
				PUT /index/type/id?timeout=60s


-------------------------------
document ��ѯԭ��				|
--------------------------------
	1,�ͻ��˷�����������node,��node��Ϊ:coordinate node

	2,coordinate node �� document ����·��,��������ת������Ӧ��node
		* ��ʹ�� round-robin �����ѯ�㷨,��primary shard �� replica shard �����ѡ��һ��
		* �ö�,���ؾ���

	3,���������node����document�� coordinate node

	4,coordinate node ����document ���ͻ���

	5,�������
		* document���ڽ�������������,����ֻ��primary shard��,�κ�һ�� replica shard��û��
		* ������ص��� relicat shard,��ô���ܻ��ȡ����document


	