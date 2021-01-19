-----------------------------
List
-----------------------------
	# ���ϵĳ�ʼ��
		var arr = [];				
		List arr = [1, 2, 3, '4'];	
		List arr = new List(2);  
	
		* �ڲ�ʹ�÷��͵������, һ��list�п��Դ洢��ͬ���͵�Ԫ��
	
	# List ��Ĺ��췽��, ���Գ�ʼ��һ�¹̶���С�ļ���
		List(int length)

		* ͨ������ָ��List�ĳ���, Ĭ��ʹ�� null ���

	# ����ĳ���
		* ͨ�� length ���Ի�ȡ��
		* ʹ������������ʽ��ʼ��, ���鳤�Ȼ�����Ԫ�ص�����������
			var arr = [];
			arr.add(1);
			arr.add(2);
			arr.add(3);
			print(arr);		// [1, 2, 3]
		
		* �̶��������length (ͨ�����췽��), ������ʹ�� add ����, ֻ��ͨ���±�ȥ����Ԫ��
			var arr = new List(1);
			arr.add(1);// Unsupported operation: Cannot add to a fixed-length list
			print(arr);
		
		* ������������±������Ԫ��, �ᷢ��Խ���쳣
			var arr = [];
			arr[0] = 1;  // RangeError (index): Invalid value: Valid value range is empty: 0
			print(arr);
			
	
	# ���͵� List
		* ���� list �Ķ������Ҫͨ������ List �������������, ����ʹ�� var ���� dynamic
			List<String> arr = ['123', '123']; // ok

			List<String> arr = new List(2);
			arr[0] = 1; // Error: A value of type 'int' can't be assigned to a variable of type 'String'.

		* �洢�������������, ���׳��쳣
		



-----------------------------
List - ��̬����
-----------------------------
	List.filled(int length, E fill, {bool growable = false})
		* ����һ�� List, �̶�����Ϊ length, ʹ�� fillԪ�����
		* growable ������ʾ�����Ƿ��������, Ĭ��Ϊ false, Ҳ���ǹ̶�����
	
	List.from(Iterable elements, {bool growable = true});
		* ���ݵ���Ԫ��, ���� List, Ĭ�Ϲ̶�����
	
	List.generate(int length, E generator(int index), {bool growable = true})
	List.of(Iterable<E> elements, {bool growable = true})
	List.unmodifiable(Iterable elements)
	

	static List<T> castFrom<S, T>(List<S> source) => CastList<S, T>(source);
	static void copyRange<T>(List<T> target, int at, List<T> source, [int start, int end]);
	static void writeIterable<T>(List<T> target, int at, Iterable<T> source)

-----------------------------
List - ����
-----------------------------
	first
	last
	length
	reversed
	hashCode
	isEmpty
	isNotEmpty
	iterator
	runtimeType
	single

-----------------------------
List - ʵ������
-----------------------------
	void add(E e);
	void addAll(Iterable<E> iterable);
	Map<int, E> asMap();
	List<R> cast<R>();
	void clear();
	void fillRange(int start, [ int end, [ E fillValue ]);
	Iterable<E> getRange(int start int end)
	int indexOf(E element, [ int start = 0 ]);
	int indexWhere(bool test(E element), [ int start = 0 ]);
	void insert(int index, E element);
	void insertAll(int index, Iterable<E> iterable);
	int lastIndexOf(E element, [ int start ]);
	int lastIndexWhere(bool test(E element), [ int start ]);
	bool remove(Object value);
	E removeAt(int index);
	E removeLast();
	void removeRange(int start int end);
	void removeWhere(bool test(E element));
	void replaceRange(int start, int end, Iterable<E> replacement);
	void retainWhere(bool test(E element));
	void setAll(int index, Iterable<E> iterable);
	void setRange(int start, int end, Iterable<E> iterable, [ int skipCount = 0 ]);
	void shuffle([Random random ]);
	void sort([int compare(E a E b) ]);
	List<E> sublist(int start, [ int end ]);
	bool any(bool test(E element));
	bool contains(Object element);
	E elementAt(int index);
	bool every(bool test(E element));
	Iterable<T> expand<T>(Iterable<T> f(E element));
	E firstWhere(bool test(E element), { E orElse() });
	T fold<T>(T initialValue, T combine(T previousValue, E element));
	Iterable<E> followedBy(Iterable<E> other);
	void forEach(void f(E element));
	String join([String separator = "" ]);
	E lastWhere(bool test(E element), { E orElse() }) ;
	Iterable<T> map<T>(T f(E e))
	dynamic noSuchMethod(Invocation invocation)
	E reduce(E combine(E value E element))
	E singleWhere(bool test(E element), { E orElse() })
	Iterable<E> skip(int count)
	Iterable<E> skipWhile(bool test(E value))
	Iterable<E> take(int count)
	Iterable<E> takeWhile(bool test(E value))
	List<E> toList({bool growable: true })
	Set<E> toSet()
	String toString() 
	Iterable<E> where(bool test(E element))
	Iterable<T> whereType<T>() 


-----------------------------
List - ����
-----------------------------
	# ���, ����µ�List
		  var arr1 = [1,2,3];
		  var arr2 = [4,5,6];
		  var arr3 = arr1 + arr2;
		  arr3.add(7);
		  print(arr3); // [1, 2, 3, 4, 5, 6, 7]
		 
	
	# �Ƚ�
		var arr1 = [1,2,3];
		var arr2 = [1,2,3];
		print(arr1 == arr2);

		* == �Ƚϵ�����List�����Ƿ���ͬһ������
		* ���ܱȽ�List�е�Ԫ���Ƿ����
	
	# ��ȡ�͸�ֵ
		var arr = [1,2,3];
		arr[1];  // 2
		arr[0] = 4; // [4,2,3]
		print(arr);


		