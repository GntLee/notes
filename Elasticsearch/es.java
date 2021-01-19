-----------------------------
Elasticsearch			 	 |
-----------------------------
	# ����
		https://www.elastic.co
		https://www.elastic.co/cn/
		https://www.elastic.co/guide/en/elasticsearch/reference/index.html

	# Github
		https://github.com/elastic/elasticsearch
	
	# �ο�
		https://blog.csdn.net/laoyang360/article/details/79293493
		https://github.com/laoyang360/deep_elasticsearch
		http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html
		https://elasticsearch.apachecn.org/#/

		https://elastic.blog.csdn.net/
		https://elasticsearch.cn/

	

-----------------------------
Elasticsearch-Ŀ¼�ṹ		 |
-----------------------------
	bin
		|-elasticsearch
		|-elasticsearch-plugin
	config
		|-elasticsearch.yml
		|-jvm.options
		|-log4j2.properties
		|-role_mapping.yml
		|-roles.yml
		|-users
		|-users_roles
	jdk
	lib
	logs
	modules
	plugins

------------------------------
Linux��װ����					|
------------------------------
	# ����ָ���İ汾
		https://www.elastic.co/cn/downloads/elasticsearch
	
	# ��ѹ��Ŀ¼

	# ���������û�, ִ��Ŀ¼��Ȩ
		* ES������ֱ��ʹ��root�˻���������, ������쳣:can not run elasticsearch as root

		useradd -r elasticsearch

		chown elasticsearch:elasticsearch [path] -R

		* ���ָ����������־����Ŀ¼, Ҳ��Ҫ������Ȩ

	
	# �����ű�
		/bin/elasticsearch
			-d
				* �ں�ִ̨��
			-E
				* �������ò���, ���� elasticsearch.yml
					-E path.data=node1_data
				* ���Գ��ֶ��
		
	
	# ����:http://127.0.0.1:9200/
		{
			"name": "KEVINBLANDY",
			"cluster_name": "elaticsearch",
			"cluster_uuid": "wCaZ0Z6rSmmFpFjQFSWjDw",
			"version": {
			"number": "7.1.1",
			"build_flavor": "default",
			"build_type": "zip",
			"build_hash": "7a013de",
			"build_date": "2019-05-23T14:04:00.380842Z",
			"build_snapshot": false,
			"lucene_version": "8.0.0",
			"minimum_wire_compatibility_version": "6.8.0",
			"minimum_index_compatibility_version": "6.0.0-beta1"
			},
			"tagline": "You Know, for Search"
		}

-----------------------------
Elasticsearch-���ĸ���		 |
-----------------------------
	Near Realtime(NRT)
		# ��ʵʱ,������˼,��д�����ݵ�����������Լ����1s���ӳ�,����es���������ͷ������Դﵽ�뼶
	
	Cluster
		# �ڵ�,��Ⱥ�е�һ���ڵ�,�ڵ����һ������,Ĭ������������
		# �ڵ����ƺ���Ҫ,�ڵ�Ĭ�ϻ�ȥ����һ����Ϊ:elaticsearch �ļ�Ⱥ
		# �������һ�ѽڵ�,��ô���ǻ��Զ����һ��es��Ⱥ,��Ȼ,һ���ڵ�Ҳ�������һ��es��Ⱥ
	
	Index
		# ����,����һ�������ƽṹ���ĵ�����,���������һ���ͻ�����,��Ʒ��������,��������,��������һ�����Ƶ�
	
	Type
		# ����,ÿ�������ﶼ������һ�����߶��type,type��index�е���һ���߼�����
		# һ��type�µ�document������ͬ��field,���粩��ϵͳ,��һ������,���Զ����û�����type,��������type,��������type
		# ��7�����Ѿ������׵�ɾ����
	
	Documen
		# �ĵ�,es�ֵ���С���ݵ�Ԫ,һ��document������һ���ͻ�����,һ����Ʒ��������
		# ͨ��ʹ��JSON���ݽṹ��ʾ,ÿ��index�µ�type��,�����Դ洢���document
	
	Shard
		# ��̨�����޷��洢��������,es���԰�һ���������ݷ�Ϊ���shard,�ֲ�ʽ�ڶ�̨�������ϴ洢
		# ����Shard�Ϳ��Ժ�����չ,�洢��������,�������ͷ����Ȳ����ֲ�����̨��������ȥִ��,����������������
		# ÿ��shard����һ��lucene index
	
	Replica
		# �κ�һ�����������п��ܻ�崻�,��ʱshard�ͻᶪʧ,��˿���Ϊÿ��shard����n��replica����
		# replia������shard����ʱ,�ṩ����,��֤shard�Ĳ���ʧ,���replica��������������������������������
		# Shard   -> primary shard(��������ʱһ������,�����޸�,Ĭ��5��)
		# Replica -> replica shard(��ʱ�޸�����,Ĭ��1��)
		# Ĭ��ÿ��������10��shard,5��primary shard,5��replica shard
		# ��С�ĸ߿���peizhi,����̨������
	

	# ��ͳ��ϵ�����ݿ�ĶԱ�
		Relational DB ->	Databases	-> Tables	-> Rows			-> Columns
		Elasticsearch ->	Indices		-> Types	-> Documents	-> Fields



-----------------------------
Elasticsearch-��;			 |
-----------------------------
��������
��ֻ���
ȫ�ļ���ԭ��
�ִ�ԭ��