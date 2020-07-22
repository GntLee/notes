----------------------------
ConcurrentHashMap			|
----------------------------
	# JDK1.8��ǰʹ�� Segment �ֶ���
		* Segment �̳��� ReentrantLock,������ HashTable ���������� put ���� get ��������Ҫ��ͬ������
		* ������ ConcurrentHashMap ֧�� CurrencyLevel (Segment ��������)���̲߳���,ÿ��һ���߳�ռ��������һ�� Segment ʱ,����Ӱ�쵽������ Segment
		* Ĭ���� 16 ����(Segment)
		* ÿ�������� 16 ��Ԫ��
		* ������Ȼ����,��ѯ��������Ч��̫�͵�����
	

	# JDK1.8��,����������ԭ�е� Segment �ֶ���,�������� CAS(�ֹ���) + synchronized ����֤������ȫ��
		* Hash��ͻ������������һ���������ת��Ϊ�����
		* ȡ���� ReentrantLock ��Ϊ�� synchronized(���Կ������°�� JDK �ж� synchronized �Ż��Ǻܵ�λ��)

	
	# PUT ����
		final V putVal(K key, V value, boolean onlyIfAbsent) {
			if (key == null || value == null) throw new NullPointerException();
			int hash = spread(key.hashCode());
			int binCount = 0;
			for (Node<K,V>[] tab = table;;) { // 1
				Node<K,V> f; int n, i, fh;
				if (tab == null || (n = tab.length) == 0)// 2
					tab = initTable();
				else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {// 3
					if (casTabAt(tab, i, null,
								 new Node<K,V>(hash, key, value, null)))
						break;                   // no lock when adding to empty bin
				}
				else if ((fh = f.hash) == MOVED)// 4
					tab = helpTransfer(tab, f);
				else {
					V oldVal = null;
					synchronized (f) {// 5
						if (tabAt(tab, i) == f) {
							if (fh >= 0) {
								binCount = 1;
								for (Node<K,V> e = f;; ++binCount) {
									K ek;
									if (e.hash == hash &&
										((ek = e.key) == key ||
										 (ek != null && key.equals(ek)))) {
										oldVal = e.val;
										if (!onlyIfAbsent)
											e.val = value;
										break;
									}
									Node<K,V> pred = e;
									if ((e = e.next) == null) {
										pred.next = new Node<K,V>(hash, key,
																  value, null);
										break;
									}
								}
							}
							else if (f instanceof TreeBin) {
								Node<K,V> p;
								binCount = 2;
								if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
															   value)) != null) {
									oldVal = p.val;
									if (!onlyIfAbsent)
										p.val = value;
								}
							}
						}
					}
					if (binCount != 0) {
						if (binCount >= TREEIFY_THRESHOLD) // 6
							treeifyBin(tab, i);
						if (oldVal != null)
							return oldVal;
						break;
					}
				}
			}
			addCount(1L, binCount);
			return null;
		}
			
		1 ���� key ����� hashcode 
		2 �ж��Ƿ���Ҫ���г�ʼ��
		3 f ��Ϊ��ǰ key ��λ���� Node,���Ϊ�ձ�ʾ��ǰλ�ÿ���д������,���� CAS ����д��,ʧ����������֤�ɹ�
		4 �����ǰλ�õ� hashcode == MOVED == -1,����Ҫ��������
		5 �����������,������ synchronized ��д������
		6 ����������� TREEIFY_THRESHOLD ��Ҫת��Ϊ�����
	
	# GET����
		public V get(Object key) {
			Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;
			int h = spread(key.hashCode()); // 1
			if ((tab = table) != null && (n = tab.length) > 0 &&
				(e = tabAt(tab, (n - 1) & h)) != null) {
				if ((eh = e.hash) == h) {
					if ((ek = e.key) == key || (ek != null && key.equals(ek)))
						return e.val;  // 2
				}
				else if (eh < 0)
					return (p = e.find(h, key)) != null ? p.val : null;  
				while ((e = e.next) != null) {
					if (e.hash == h &&
						((ek = e.key) == key || (ek != null && key.equals(ek))))
						return e.val; //3 
				}
			}
			return null;
		}

		1 ���ݼ�������� hashcode Ѱַ,�������Ͱ����ôֱ�ӷ���ֵ
		2 ����Ǻ�����ǾͰ������ķ�ʽ��ȡֵ
		3 �Ͳ������ǾͰ�������ķ�ʽ������ȡֵ
			
	# ��̬����
		<K> KeySetView<K,Boolean> newKeySet()
		<K> KeySetView<K,Boolean> newKeySet(int initialCapacity)
	
		