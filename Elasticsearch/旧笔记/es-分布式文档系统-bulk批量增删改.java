----------------------------
bulk������ɾ��				|
----------------------------
	# _bulk �����json���﷨,Ҫ���൱�ϸ�
		* ÿ��json����,���ܻ���,ֻ�ܷ���ͬһ��
		* ����json����֮��Ҫ��һ��

	# create,update����,��Ҫ��json��
		* ��һ��jsonָ������,�Լ�Ԫ����
		* �ڶ���jsonָ���ύ��ҵ������

	# json��ʽ
		{"create":{...Ԫ����}}
		{...ҵ������}

		{"index":{...Ԫ����}}
		{...ҵ������}

		{"delet":{...Ԫ����}}

		{"update":{...Ԫ����}}
		{...ҵ������}
		

		* create ����ǿ�ƴ���		PUT /index/type/id/_create
		* index ������ͨ��put����,�������滻�ĵ�,���ߴ����ĵ�	
		* update ���� partial update ����,����ʹ�� retry_on_conflict �������ֹ��������Դ���
		*  ���������������Ԫ�����ж���id��ô���Զ�����id
		* һ��������������ͬʱ�ύN��� create,delete,update ����,ֻ��Ҫ����json��ʽ����
		* ���������е�����һ������ʧ��,����Ӱ�쵽��������������,���ǻ��ڷ��ؽ������ʾʧ�ܵ���־

	# ��ͬindex����������
		POST /_bulk
		{"create":{"_index":"user","_type":"coder",	"_id":3}}
		{"id":3,"name":"Batch Name","age":24}
		{"delete":{"_index":"user","_type":"coder","_id":2}}
		{"update":{"_index":"user","_type":"coder","_id":1,"retry_on_conflict":3}}
		{"doc":{"name":"Batch Uapdate Name"}}
	
	# ��ͬindex,��ͬtype����������
		POST /user/_bulk
		{"create":{"_type":"coder","_id":3}}
		{"id":3,"name":"Batch Name","age":24}
		{"delete":{"_type":"coder","_id":2}}
		{"update":{"_type":"coder","_id":1,"retry_on_conflict":3}}
		{"doc":{"name":"Batch Uapdate Name"}}
		
		* Ԫ���ݽ�����Ҫ���� _type �� id ����,��Ϊindex�Ѿ���url�ж���
	
	# ��ͬindex,��ͬtype����������
		POST /user/coder/_bulk
		{"create":{"_id":3}}
		{"id":3,"name":"Batch Name","age":24}
		{"delete":{"_id":2}}
		{"update":{"_id":1,"retry_on_conflict":3}}
		{"doc":{"name":"Batch Uapdate Name"}}

		* Ԫ���ݽ�����Ҫ���� id ����,��Ϊ _type �� _index �Ѿ���url�ж���
		
	
	# bulk �����Ż�
		* bulk request ����ص�����,���̫��Ļ�,���ܷ������½�,�����Ҫ��������һ����ѵ�bulk size
		* һ��� 1000 - 5000����ʼ,����������
		* ����Ӵ�С�Ͽ��Ļ�,�������5 - 15MB֮��
	
	# bulk���ص�JSON��ʽ��ײ������Ż���ϵ
		1,bulk�е�ÿ������,������Ҫת������ͬ��node��shardȥִ��
		2,������ñȽ����õ�json�����ʽ
			(1),��json�������Ϊ JSONArray ����
			(2),��ÿ�������е�document����·��
			(3),Ϊ·�ɵ�ͬһ��shard�ϵĶ�����󴴽�һ����������
			(4),����������������л�
			(5),�����л������ݷ��͵���Ӧ�Ľڵ�

			* �ķѸ�����ڴ�,�����jvm gc����
	
		3,�����json�����ʽ
			(1),���ջ��з��и�json
			(2),��ÿ����һ���json,��ȡmeta,����document·��
			(3),ֱ�ӰѶ�Ӧ��json���͵�node��ȥ

			* ������������,����Ҫ��json�������Ϊһ��JSONArray����,�γ�һ�ݴ����ݵĿ���,�˷��ڴ�ռ�