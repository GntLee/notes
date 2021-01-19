
----------------------------
FastThreadLocal<V>			|
----------------------------
	# ���췽��
		FastThreadLocal()
	
	
	# ��̬����
		void destroy()
		void removeAll()
		int size()


	# ʵ������
		V get()
		V get(InternalThreadLocalMap threadLocalMap)

		boolean isSet()
		boolean isSet(InternalThreadLocalMap threadLocalMap)

		void remove()
		void remove(InternalThreadLocalMap threadLocalMap)

		void set(V value)
		void set(InternalThreadLocalMap threadLocalMap, V value)


		
		protected V initialValue()
		protected void onRemoval(@SuppressWarnings("UnusedParameters") V value)