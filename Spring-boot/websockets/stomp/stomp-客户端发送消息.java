---------------------------------
�ͻ��˷�����Ϣ
---------------------------------
	# ʹ��SEND���COMMAND
		SEND
		destination:/topic/a
		content-type:text/plain

		hello world
		^@

		* destination ָ����ַ�������ַ����㣬զд����
		* ����
			��/topic��ͷ��Ϊ��������ģʽ, ��Ϣ�ᱻ����������client�յ�
			��/queue��ͷ��Ϊ����ƽ��ģʽ, ֻ�ᱻһ�����Ѷ�client�յ�
	
	# ��Ϣȷ��
		* Э��涨, ������SEND������Ϣ�м���receiptͷ. 
		* receiptͷ��ֵΨһȷ��һ��send.
		
		* server�յ���receiptͷ��SEND������Ϣ��, ��Ҫ�ظ�һ��RECEIPT������Ϣ,
		
		SEND
		destination:/queue/a
		receipt:message-12345

		hello queue a^@

		RECEIPT
		receipt-id:message-12345

		^@
	
