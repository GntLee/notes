---------------------------
MessageProducer
---------------------------
	# ��Ϣ�����ӿڣ�  interface MessageProducer<T> 


---------------------------
����
---------------------------
	MessageProducer<T> deliveryOptions(DeliveryOptions options);
		* ��������

	String address();
		* ���ص�ַ

	void write(T body, Handler<AsyncResult<Void>> handler);
	Future<Void> write(T body);
		* ������Ϣ���������ͽ��

	Future<Void> close();
	void close(Handler<AsyncResult<Void>> handler);
		* �رգ����ٷ���

---------------------------
��̬
---------------------------
	