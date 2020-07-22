-----------------------
index				   |
-----------------------

-----------------------
index	- ����		   |
-----------------------
	# ����
		PUT /<index> 
		{
			...config
		}
	
		{
			"acknowledged" : true,
			"shards_acknowledged" : true,
			"index" : "<index>",
			"settings": {	
				"number_of_shards": 5			// ���� primary shard������
				"number_of_replicas": 1			// ���� relicas shard������
			}
		}

-----------------------
index	- ɾ��		   |
-----------------------
	# ����
		DELETE /<index>?pretty
	
		{
		  "acknowledged" : true
		}



----------------------------
�鿴��Ⱥ��������Ϣ			|
----------------------------
	# ����
		GET /_cat/indices?v

	# ��Ӧ
		health status index                uuid                   pri rep docs.count docs.deleted store.size pri.store.size
		green  open   .kibana_task_manager cEN_d-7TTwiYBn7xE1LkpQ   1   0          2            0     12.7kb         12.7kb
		green  open   .kibana_1            RpX3IRZEQKag3H8xyWq4eQ   1   0          4            0     17.6kb         17.6kb
		yellow open   customer             R4mXIa-_QKiDngr7bKG-OA   1   1          0            0       230b           230b

		health
		status
		index
		uuid
		pri
		rep
		docs.count
		docs.deleted
		store.size
		pri.store.size