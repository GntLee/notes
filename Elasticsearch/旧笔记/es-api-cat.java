
------------------------
cat	- api				|
------------------------
	# ϵͳ�ṩ��һ�׿���cat - api
	# ��ʾ�������ͷ����Ϣ
		* ��api��ַ������Ӳ���: ?v

/_cat/health
	# ���ٵĲ鿴���񽡿�״��
		epoch      |timestamp|cluster		|status |node.total|node.data|shards|pri|relo|init|unassign|pending_tasks|max_task_wait_time|active_shards_percent
		1529503744 |22:09:04 |elasticsearch |green  | 1        |1        |1     |1  |0   |0   |0       | 0           | -                |100.0%

		ʱ��� ʱ�� ��Ⱥ����

/_cat/indices
	# ���ٵĲ鿴������Ϣ
		health status index   uuid                   pri rep docs.count docs.deleted store.size pri.store.size
		green  open   .kibana TiiXPteWSB6eLJ0GwTbMsQ   1   0          1            0        4kb            4kb