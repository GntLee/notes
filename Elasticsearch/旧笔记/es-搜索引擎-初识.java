------------------------
search ���				|
------------------------
	{
	  "took": 19,
		  * �����������󻨷��˶��ٺ���
	  "timed_out": false,
			* �Ƿ�ʱ
	  "_shards": {
			* ����shard��һЩ��Ϣ
		"total": 6,
				* Ĭ����˵˵,һ����������,��򵽸�index���е�primary shard��ȥ
		"successful": 6,
		"skipped": 0,
		"failed": 0
	  },
	  "hits": {
			* �������
		"total": 6,
				* ���������ķ��صĽ��
		"max_score": 1,
				* �����������н����,�����ضȷ����Ƕ���
				* ÿһ��document����search����ض�,Խ���,_score����Խ��,��λԽ��ǰ
		"hits": [
				* ����
		  {
			"_index": ".kibana",
			"_type": "doc",
			"_id": "config:6.3.0",
			"_score": 1,
			"_source": {
			  "type": "config",
			  "updated_at": "2018-06-20T13:41:22.461Z",
			  "config": {
				"buildNum": 17230,
				"telemetry:optIn": true
			  }
			}
		  }
		]
	  }
	}

------------------------
timeout����				|
------------------------

	# timeout����
		* Ĭ�������,û��timeout
		* �û���,ָ����ÿ��shard��ֻ����timeoutʱ�䷶Χ��,����������������Ӧ���ͻ���
		* ��timeoutʱ����,�����Ѿ�����������,Ҳ����û����ȫ,���������,�������Ѿ���������������Ӧ
	
	# �﷨
		GET /_search?timeout=10ms
		GET /_search?timeout=1m

		* ʹ��timeout������ָ����,����...

------------------------
multi-index��multi-type	|
------------------------
	GET /_search
		* ��������index,����index������type������
	
	GET /index/type/_search
		* ����index������type������
	
	GET /index/type1,type2/_search
		* һ��index�¶��type
		* ����index��type1,type2�µ���������
	
	GET /index1,index2/_search
		* ���index
		* ����index1,index2����������type������
	
	GET /index1,index2/type1,type2/_search
		* ���index,���type
		* ����index1�µ�type1,index2�µ�type,����������
	
	GET /index_*/_search
		* ֧��ͨ���
		* �������������� 'index_' ��ͷ��index,����������type������
	
	GET /_all/type1,type2/_search
		* ��������index��,ָ��type������

	
------------------------
����ԭ��				|
------------------------
	# clinet��һ������,��ֲ���index�����е�primary shard��,��Ϊ���������κ�һ��primary shard��
		* ���primary shard����replica shard,��ô��������replica shard��
	

------------------------
��ҳ����				|
------------------------
	# ��ҳ����
		GET /_search?size=10
		GET /_search?size=10&from=0
		GET /_search?size=10&from=20

		* size,ÿҳ��ʾ�ļ�¼����
		* from,�ӵڼ������ݿ�ʼ����,0��ʾ��һ��
	
	# deep paging����
		* deep paging,����˵,�����������ر���,����1000000������,ÿҳ��ʾ10��,��ʱ��Ҫ�������һҳ������
		* �������������,���ܴ����ڶ��primary shard,replica shard,���Ǿ�Ҫ���������ݻ��ܵ� coordinating node(Э���ڵ�)
		* ��Э���ڵ��������,ȡ�����������������,���շ�ҳ����
		* ������̺ķѴ���,�ڴ�,�������,�������deep paging����,���ǵĿ�������Ҫ�����������



