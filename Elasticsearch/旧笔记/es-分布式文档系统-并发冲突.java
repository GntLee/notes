--------------------------------
������ͻ-������					|
--------------------------------
	# �����ڹ�ϵ�����ݿ���,�������
		 SELECT .. FROM ... WHERE ... FOR UPDATE;
	
	# ����������,���Ƿ���
		


--------------------------------
������ͻ-�ֹ���					|
--------------------------------
	# CAS �㷨
	# ����ͨ��version�ֶ�������
	# ����
		1,
		  * �߳�a��ȡ��Ʒ,ʣ��100,version=1
		  * �߳�b��ȡ��Ʒ,ʣ��100,version=1
		 
		2,
		  * �߳�a�µ�,�ѿ�� -1
		  * ��д��ʱ���鵱ǰ���ݵ�version���Ƿ��es�е�versionһ��
		  * һ�����д,��һ��,�����¶�ȡ�������ݺ� -1,�ٻ�д
	
	# ����������,�����鷳
	# ����_version
		* ÿ�δ����µ�document��ʱ��,�����ڲ� _version �ڲ��汾�ž���1
		* �Ժ�ÿ�ζԸ�doucment�����޸�/ɾ����ʱ��,��_version�����Զ�+1(������ɾ��,Ҳ��+1)
		* ɾ��document��ʱ��,�����������Ľ�������ɾ��,�����������ǵ�Ԫ��Ϣ(_version��...)
		
		* ��ɾ��һ��document,������put(����)��ʱ��,_version ����delete�ļ�¼��+1(��ѧ)
	
	# es�ڲ����߳��첽����ִ���޸�ʱ,�ǻ���_version�汾�Ž����ֹ������Ƶ�
		* ��ͬ���޸ĵ�ʱ��,��Ƚ�һ�µ�ǰes��_version�Ƿ��뵱ǰ��_versionһ��
		* ��һ���Ͷ���,һ�������
	
	# ���� _version �������ֹ������в�����ͻ�Ŀ���
		POST /{index_name}/{type_name}/{id}?version=1
		POST /{index_name}/{type_name}/{id}/_update?version=1
		PUT  /{index_name}/{type_name}/{id}?version=1

		* ˵����,�����ڽ����޸ĵ�ʱ����Ҫ���ϵ�ǰ�汾��
		* ��uri��Ӳ�ѯ���� version,��ֵΪ��ǰdocument��version�ֶ�
		* �ڸ��µ�ʱ��ϵͳ��Խ����ֹ�������,���versionһ�����޸�,���򷵻ش�����Ϣ,���޸�
	
	# �����ֹ����ĵ�demo
		1,��������
			PUT /user/1
			{
				"name":"KevinBlandy"
			}
		
		2,�߳�1,����
			PUT /user/1?version=1
			{
				"name":"KevinBlandy1"
			}
			* ���³ɹ�,_version = 2

		3,�߳�2,����
			PUT /user/1?version=1
			{
				"name":"KevinBlandy1"
			}
			* �쳣,��Ϊ�߳�1�Ѿ��޸ĳɹ�,version�Ѿ���2��,
				{
				  "error": {
					"root_cause": [
					  {
						"type": "version_conflict_engine_exception",
						"reason": "[coder][666]: version conflict, current version [2] is different than the one provided [1]",
						"index_uuid": "hmtk8vwASdCPpQENyyLwqw",
						"shard": "0",
						"index": "user"
					  }
					],
					"type": "version_conflict_engine_exception",
					"reason": "[coder][666]: version conflict, current version [2] is different than the one provided [1]",
					"index_uuid": "hmtk8vwASdCPpQENyyLwqw",
					"shard": "0",
					"index": "user"
				  },
				  "status": 409
				}
						
	# external version
		* es�ṩ��һ��feature,���Բ�ʹ�����ݲ���_version�汾�������в�������
		* ���Ի����Լ�ά����'version�汾��'�����в�������
		* ʹ�ó���
			��mysql��Ҳ����һ������,Ӧ��ϵͳ�����ά����һ���汾��,��ʱʹ���ֹ������Ƶ�ʱ��,����ʹ��es��version,������ʹ��Ӧ��ϵͳ�е�version
		
		* version�����﷨
			?version=1&version_type=external

		* �� version_type=external ��ʱ��,version��������Ҫ���ڵ�ǰ��_version���ܸ��³ɹ�
		* ���޸ĳɹ���,���һ��document��_version�޸�Ϊversion������ֵ
		

			