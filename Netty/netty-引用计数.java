-------------------------
���ü���				 |
-------------------------
	# ���ü���
		* �����ü��������Ķ���,�ܹ���ʾ�ı���������,����ʼ����ʱ��,������������ٵ�0�����ᱻ��ʾ����
		* �ٴη��ʱ����յ���Щ���󽫻��׳��쳣
		* ���һ������ʵ����ReferenceCounted,���Ұ�������������Ҳʵ����ReferenceCounted,������������Ϊ0�����յ�ʱ��,�������Ķ���ͬ����ͨ��release()�ͷŵ�
		* һ�����ö���մ���ʱ,��ʼ��refCntֵ����1

	# �ӿ� ReferenceCounted
	# �ӿڷ���
		int refCnt();
			* ����������

		ReferenceCounted retain();
			* ���һ������

		ReferenceCounted retain(int increment);
			* ���N������

		ReferenceCounted touch();
		ReferenceCounted touch(Object hint);

		boolean release();
			* �ͷ�һ������,������õ���0,��ᱻ����

		boolean release(int decrement);
			* �ͷ�n������,������õ���0,��ᱻ����


-------------------------
ReferenceCountUtil		 |
-------------------------
	# ���� ReferenceCounted �Ĳ���������
	# �ṩ��N��ľ�̬����(������ReferenceCounted�ӿ�һ��)
		* �������� Object,�����ṩ��ȫ���ͷŷ���

	int refCnt(Object msg)

	boolean release(Object msg)
	boolean release(Object msg, int decrement)

	<T> T retain(T msg)
	<T> T retain(T msg, int increment)

	void safeRelease(Object msg)
	void safeRelease(Object msg, int decrement)

	<T> T touch(T msg)
	<T> T touch(T msg, Object hint)



	
