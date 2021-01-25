--------------------
HashMap				|
--------------------
	# �洢�ṹ�ı仯
		* JDK 1.7����ǰʹ�� Hash �� + ����ʵ��
		* JDK 1.8���Ժ�ʹ�� Hash �� + �����ʵ��(�Ż��˲�ѯЧ��)
		* ��������һ����������,�������ת��Ϊ�����
			* �������ȴ��ڵ���8
			* table���鳤�ȴ��ڵ���64(able���������Ƚ�Сʱ, ��ֵ�Խڵ� hash ����ײ�ʿ��ܻ�Ƚϸ�, ���������������Ƚϳ�, ���ʱ��Ӧ����������, ��������������)
	
	# ������Ϊ�ı仯
		 * HashMap���ݽ׶�����ӳ��Ԫ��ʱ����Ҫ��1.7�汾��������ȥһ��������Ԫ�ص�hashֵ,
		 * ����ͨ��hash & oldCap��ֵ���ж�, ��Ϊ0������λ�ò���, ��Ϊ0��������=ԭ����+�����鳤��

		 * ��Ϊʹ�õ���2���ݵ���չ(ָ������Ϊԭ��2��), ����Ԫ�ص�λ��Ҫô����ԭλ��, Ҫô����ԭλ�����ƶ�2���ݵ�λ��
		 * ���,������HashMap��ʱ��,����Ҫ��JDK1.7��ʵ���������¼���hash
		 * ֻ��Ҫ����ԭ����hashֵ�������Ǹ�bit��1����0�ͺ���,��0�Ļ�����û��,��1�Ļ��������: ԭ���� + oldCap

		 * ��Ҳ�ǳ���Ϊ2���ݴη���һ���ô�


	# ����ڵ�ı仯
		* JDK1.7�õ���ͷ�巨, ��JDK1.8��֮��ʹ�õĶ���β�巨
		* ��ΪJDK1.7���õ��������е���������, ������ͷ�巨�����ܹ���߲����Ч��, ����Ҳ�����׳��������һ���������ѭ������
		* ����JDK1.8֮������Ϊ�����˺����ʹ��β�巨, �ܹ��������������������ѭ��������
	
	# Hash���ĳߴ�������ǳ�����Ҫ
		* һ����˵,Hash�����������������Ҫ����ʱ,������������û�г����趨�� thredhold,�������,��Ҫ����hash���ĳߴ�
		* ��������һ��,����hash��������ض���Ҫ������һ��,���rehash,����ɱ��൱�Ĵ�
	
	# �ײ������� + ��������(�����)
		Node<K,V>[] table;
		class Node {
			final int hash;
			final K key;
			V value;
			Node<K,V> next;
		}

	# ���ĵ�����
		static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
			* Ĭ�ϵĴ洢Ͱ��(��С)

		static final int MAXIMUM_CAPACITY = 1 << 30;
			* ���Ĵ洢Ͱ����

		static final float DEFAULT_LOAD_FACTOR = 0.75f;
			* Ĭ�ϵĸ�������, ����ʲôʱ������
				������С x �������� = �������ݵĴ�С

		static final int TREEIFY_THRESHOLD = 8;
			* �����ж��Ƿ���Ҫ������ת��Ϊ���������ֵ
			* ��������ĳ��ȴ����˸�ֵ,�����˸�ֵ�ͻ�ת��Ϊ�����

		static final int UNTREEIFY_THRESHOLD = 6;
			* �Ѻ����ת��Ϊ��������ֵ

		static final int MIN_TREEIFY_CAPACITY = 64;

		transient Node<K,V>[] table;
		transient Set<Map.Entry<K,V>> entrySet;
		int size;
			* �洢����������

		int modCount;
			* �޸ĵĴ���

		float loadFactor;
			* ��������
		
		int threshold;
			* ����Map��С����һ��ֵ

	
	# HashMap �ڲ���������ʹ��ʱ���׳�������
		* ����Դ��
			void transfer(Entry[] newTable, boolean rehash) {
				// ��table�ĳ���
				int newCapacity = newTable.length;
				// ������table
				for (Entry<K,V> e : table) {
					// ��������
					while(null != e) {
						// ��ǰ�ڵ����һ���ڵ�
						Entry<K,V> next = e.next;
						if (rehash) {
							// ���¼�����ڵ��hash
							e.hash = null == e.key ? 0 : hash(e.key);
						}
						// �����Ԫ�����ڵĽڵ�
						int i = indexFor(e.hash, newCapacity);
						// ����table�е�ͷ�ڵ�, ����Ϊ�Լ�����һ���ڵ�
						e.next = newTable[i];
						// ���Լ�����Ϊ��һ���ڵ�(ͷ�巨)
						newTable[i] = e;
						// ��ʼ������һ������
						e = next;
					}
				}
			}

			* ���߳�put������,get����������ѭ��
			* ��˵jdk8�޸����������, ����ʱʹ��β�巨, ������ԭ�������е�˳��
		
		* ���Ը�������
			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < 1000; i++) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						map.put(UUID.randomUUID().toString(), "");
					}
				}).start();
			}
			
			
			* HashMap ���ݵ�ʱ������ resize() ����
			* ����Ĳ�������������һ��Ͱ���γɻ�������
				* ����ȡһ�������ڵ� key ʱ,������� index �����ǻ����������±�ͻ������ѭ��

		
		* ���߳�put��nullԪ�غ�,get�����õ�nullֵ(����Ԫ�ض�ʧ,�������jdk8û���޸�)
		
	
	# ����ֻ����2�Ĵη�
		* �������������, �ᱻ�޸�Ϊ���ڵ������ֵ��2����
		    static final int tableSizeFor(int cap) {
				int n = cap - 1;
				n |= n >>> 1;
				n |= n >>> 2;
				n |= n >>> 4;
				n |= n >>> 8;
				n |= n >>> 16;
				return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
			}
			
			* ���㷨�����λ��1�����λȫ��Ϊ1, ������ý��n+1, ���õ���2���������ݵ�ֵ��
			* ��cap- 1�ٸ�ֵ��n��Ŀ�������ҵ���Ŀ��ֵ���ڻ����ԭֵ

			* ��������� 1000 ʮ������ֵΪ8, �����������1��ֱ�Ӳ���, ���õ��� 10000, ��16 ��Ȼ���ǽ��
			* ��1������� Ϊ111, �ٽ��в������õ�ԭ������ֵ 1000, ��8, ͨ��һϵ��λ���������Ч��
			
		
			* �ô�1
			* �����鳤��Ϊ2���ݴη�ʱ, ����ʹ��λ����������Ԫ���������е��±�
			* ֻ�е����鳤��Ϊ2���ݴη�ʱ, hash &(length-1) �ŵȼ��� hash%length, ʹ��λ����������Ч��
			
			* �ô�2
			* ����hashֵ������ԣ�����hash��ͻ
			* ��� length Ϊ 2 ���ݴη�, �� length-1 ת��Ϊ�����Ʊض��� 11111��������ʽ
			* �����Ļ�����ʹ����λ�ö��ܺ�Ԫ��hashֵ��������, �������� length ����2�Ĵ���
			* ����lengthΪ15, ��length-1Ϊ14, ��Ӧ�Ķ�����Ϊ1110, �ں�hash ��������ʱ, ���һλ��Զ��Ϊ0, �˷ѿռ�


		* ����Ԫ��hash�����ڵ��±�: i = (n - 1) & hash
			 if ((p = tab[i = (n - 1) & hash]) == null)
				 tab[i] = newNode(hash, key, value, null);
			
			hash	�������hashֵ
			tab		����
			i		��ʱ����, �洢��������±�
			n		���鳤��

	
	# Hash�ļ���
		* Դ��
			static final int hash(Object key) {
				int h;
				return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
			}