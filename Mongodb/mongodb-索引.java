---------------------
����
---------------------
	# Mongoʹ�õ�����
		B��
		��ַλ�ÿռ�����
		�ı�����
		��ϣ����
		
	
	# ���ǲ�ѯ
		* �����ϸ�mysql�ġ��ر���һ���ģ�����������ѯ���ݡ��պ���ֻ��Ҫ����������ݡ���ôЧ�ʾͻ����
			db.user.find({name: "1"}, {_id:0, name: 1}); // ��������name��ѯ������ֻ����name�ֶ�

---------------------
�����Ĳ���
---------------------
	# �����Ĵ���
		db.[collection].createIndex([keys], [options])
			
		
		* ����������
			db.user.createIndex({'name': 1})  // �����ֶ�����
			db.user.createIndex({'name': 1, 'phone': 1}) // �������� 

			{
				"createdCollectionAutomatically" : false,
				"numIndexesBefore" : 1,
				"numIndexesAfter" : 2,
				"ok" : 1
			}

		* ����ֵ��1:��������-1:������������ν

	# options
		{
			background: <boolean>
				* ���������Ĺ��̣����ں�̨��ǰִ̨�л��������̣���Ĭ�� false

			unique: <boolean>
				* �Ƿ����ΨһԼ����Ĭ�� false

			name: <string>
				* �������ƣ�Ĭ��ͨ�������������ֶ���������˳������һ���������ơ�

			sparse: <boolean>
				* ���ĵ��в����ڵ��ֶ����ݲ���������
				* ���������Ҫ�ر�ע�⣬�������Ϊtrue�Ļ����������ֶ��в����ѯ����������Ӧ�ֶε��ĵ�.��Ĭ��ֵΪ false.

			expireAfterSeconds: <boolean>
				* ָ��һ������Ϊ��λ����ֵ����� TTL�趨���趨���ϵ�����ʱ�䡣

			v: 
				* ��������汾�š�Ĭ�ϵ������汾ȡ����mongod��������ʱ���еİ汾��

			weights: <int>
				* ����Ȩ��ֵ����ֵ�� 1 �� 99,999 ֮�䣬��ʾ��������������������ֶεĵ÷�Ȩ�ء�
			
			default_language: <string>
				* �����ı��������ò���������ͣ�ôʼ��ʸɺʹ����Ĺ�����б� Ĭ��ΪӢ��
			
			language_override: <string>
				* �����ı��������ò���ָ���˰������ĵ��е��ֶ��������Ը���Ĭ�ϵ�language��Ĭ��ֵΪ language.
		}


	# �鿴��������
		* ������Ϣ
			db.[collection].getIndexes()
			[
				{
						"v" : 2,			// ��������汾��
						"key" : {			// ������
								"_id" : 1
						},	
						"name" : "_id_",	// ��������
						"ns" : "test.user" // name space
				}
			]
		
		* �鿴��������ռ�õĴ�С
			db.[collection].totalIndexSize()
	
	# ɾ������
		* ɾ��ָ������
			db.[collection].dropIndex([indexName])

			db.user.dropIndex({'name': 1}); // ���������У�ɾ������
			db.user.dropIndex('name_1');	// �����������ƣ�ɾ������


		* ɾ����������
			db.[collection].dropIndexes()
		

---------------------
���� - �鿴ִ�мƻ�
---------------------
	# ͨ�� explain �������鿴ִ�мƻ�
		db.[collectin].find(...).explain([config]);;

		{
			"queryPlanner" : {
				"plannerVersion" : 1,
				"namespace" : "test.user",
				"indexFilterSet" : false,
				"parsedQuery" : {

				},
				"queryHash" : "8B3D4AB8",
				"planCacheKey" : "8B3D4AB8",
				"winningPlan" : {
					"stage" : "COLLSCAN",
					"direction" : "forward"
				},
				"rejectedPlans" : [ ]
			},
			"serverInfo" : {
				"host" : "SKY-20190107XTL",
				"port" : 27017,
				"version" : "4.2.6",
				"gitVersion" : "20364840b8f1af16917e4c23c1b5f5efd8b352f8"
			},
			"ok" : 1
		}
		
		queryPlanner.winningPlan.stage
			"COLLSCAN"	
				* ����ɨ��

			"IDHACK"
				* ����

---------------------
ȫ������
---------------------
	