-----------------------------------
���ĺ�ȡ������
-----------------------------------
	# ������Ϣ��SUBSCRIBE����
		SUBSCRIBE
		id:0
		destination:/topic/foo
		ack:client

		^@
	
		* id, һ��client����һ��server���Զ��Ķ��, ��������ͬһ��Ŀ�ĵ�ַ�����Զ��Ķ��.
		* Ϊ��Ψһȷ��һ�ζ���, Э��涨�������id header, ��idҪ����ͬһ������Ψһ.

		* ack header, ����server, server���ȷ��client�Ѿ��յ���Ϣ.
		* ������ֵ: auto, client, client-individual
			auto
				* ��ʾ��server������Ϣ�������ȷ��client�յ�����Ϣ. Ҳ����˵��client�յ���Ϣ�󲻻��server����ȷ��
			client
				* ��ʾֻ�е�server�յ�client��ack���ȷ��client�յ�����Ϣ, Ҳ����˵client��Ҫ��server��ack����ȷ��.
				* ���ȷ�����ۻ���, ��˼��˵�յ�ĳ����Ϣ��ack, ��ô������Ϣ֮ǰ�����е���Ϣ, server����Ϊclient���յ�.
			client-individual
				* ��client����. ֻ���������ۻ���. ÿ�յ�һ����Ϣ����Ҫ��server�ظ�ack��ȷ��.
	
	# ȡ��������UNSUBSCRIBE�������
		UNSUBSCRIBE
		id:0

		^@
				
		* �����������˵�Ƚϼ�ֻ��Ҫ��һ��id header.
		* ���id header��ֵ���Զ���ʱid headerֵ. ����server����Ψһȷ������Ҫȡ���ĸ�����.
