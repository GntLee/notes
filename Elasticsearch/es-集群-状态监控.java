----------------------------
��Ⱥ״̬					|
----------------------------
	# ����
		GET /_cat/health?
	
		epoch      timestamp cluster      status node.total node.data shards pri relo init unassign pending_tasks max_task_wait_time active_shards_percent
		1560149716 06:55:16  elaticsearch green           1         1      2   2    0    0        0             0                  -                100.0%

		epoch
		timestamp
		cluster
			* ��Ⱥ����

		status
			* ��ʾ��Ⱥ��״̬, ʹ��Ӣ�ĵ���ɫ�ʶ���ʾ
				Green	:һ�ж��ܺ�
				Yellow	:�������ݶ�����,����δ����һЩ����
				Red		:ĳЩ��������ĳ��ԭ�򲻿���
		
		node.total
			* ��Ⱥ�еĽڵ�����
		
		node.data
		shards 
		pri
		relo
		init
		unassign
		pending_tasks
		max_task_wait_time
		active_shards_percent

----------------------------
�鿴��Ⱥ�еĽڵ���Ϣ		|
----------------------------
	# ����
		GET /_cat/nodes?v

		ip        heap.percent ram.percent cpu load_1m load_5m load_15m node.role master name
		127.0.0.1           12          51   8                          mdi       *      KEVINBLANDY

		ip
		heap.percent
		ram.percent
		cpu
		load_1m
		load_5m
		load_15m
		node.role
		master
		name