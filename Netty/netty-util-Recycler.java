-------------------------
Recycler<T>				 |
-------------------------
	# ������
	# ���췽��
		Recycler()
		Recycler(int maxCapacityPerThread)
		Recycler(int maxCapacityPerThread, int maxSharedCapacityFactor)
		Recycler(int maxCapacityPerThread, int maxSharedCapacityFactor,int ratio, int maxDelayedQueuesPerThread)
	

	# ʵ������
		T get()
		

	# Ψһ���󷽷�
		abstract T newObject(Handle<T> handle);
	
