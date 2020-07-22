-----------------
����			 | 
-----------------
	# �������ϵĺ���
		var list = listOf(1,2,3,4)								java.util.Arrays$ArrayList
			* Arrasys.asList()
			* ֻ��

		var list = arrayListOf(1,2,3,4);						ArrayList

		var set = setOf()
			* Collections.singleton()							java.util.Collections$SingletonSet
			* ֻ��

		var set = hashSetOf(1,2,3,4);							HashSet
	
		<A,B> mapOf(pairs:Pair<A,B>)							LinkedHashMap
			* ֻ��

		var map = hashMapOf(1 to "1", 2 to "2", 3 to "3")		HashMap
	
	# list/set/map ֧�ֵ�һЩ����(��չ����)
		last();
		min();
		max();
	
		withIndex(): Iterable<IndexedValue<T>>
			* list ���ݽṹ��һ��api
			* ����һ��������, ��������������±��ֵ
				public data class IndexedValue<out T>(public val index: Int, public val value: T)
		
		filter()
			* ����, ���� true �Ľ��ᱻ����
				arrayListOf(1,2,3,4,5).filter {it % 2 == 0} // [2, 4]
			* ���ִ�ж����� map, ��ô��������һ��: entry
				mapOf("1" to "2").filter { it -> it.key == it.value }
				mapOf("1" to "2").filter { (k,v) -> k == v}
		
		filterValues()
		filterKeys()
			* map��key �� value ������
		
		filterNotNull()
			* ���˵����п�nullԪ��
			* ���ص��б����� null Ԫ��
		
		mapValues()
		mapKeys()
			* map �ṹ�����Ѻ���
				mapOf("1" to "2").mapKeys { println("${it.key},${it.value}") }
				mapOf("1" to "2").mapValues { println("${it.key},${it.value}") }
		
		map()
			* һ�����Ѻ��� ,py/java����
				arrayListOf(1,2,3,4,5).map {it * 2} // [2, 4, 6, 8, 10]
			
		
		all()
		anly()
			* �жϺ���, ���� boolean
			* ������ж���������/�������κ�һ����������, ���� true
			

		find()
			* ����ƥ��ɹ��ĵ�һ��Ԫ��
				listOf(1,4,3,4).find {it % 2 ==0} // 4
		
		count()
			* ͳ�� ,��Ҳ֧�ֹ���, ���ط�������������
				listOf(1,2,3,4).count {it % 2 ==0} // 2
			
		groupBy()
			* �ۺ�,���صĽ����һ�� map<?,List<?>>, �� java ��streamһ��
			* �Ѵ�����һ�������ݷŵ�һ������,����Ľ����Ϊkey
				var result = listOf("a","bb","ccc","d","ee","fff").groupBy { it.length }
				println(result) // {1=[a, d], 2=[bb, ee], 3=[ccc, fff]}
		
		flatMap()
			* �ѽ���ϲ�Ϊһ����
			* �Ȱ�ÿ��Ԫ�����任, Ȼ���ٺϲ�Ϊһ����
				arrayOf(
				Book("Java���˼��", arrayListOf("Kevin","Litch")),
				Book("Python���˼��", arrayListOf("Ruby","xjp")),
				Book("Javascript���˼��", arrayListOf("Zy","Litch")),
				Book("C���˼��", arrayListOf("Kevin","Rocco")))

				.flatMap { it.authors }.forEach {println(it)} // �����е�������Ϣ, ����ϳ���һ����
		
		
		forEachIndex()
			* ���±�ı���, ������һ�� lambda, ����������: index, element
				intArray.forEachIndexed { index, element -> println("$index, $element") }
		

		* ��Щ����֮�������ʽ����, �� Java8�� Stream һ��
	
	# ��Ϊ��Ч�� asSequence 
		* һ��������Ĵ���
			arrayOf("1","2").map { it.length }.filter { it >= 1 }

			* ִ�е� map ��ʱ��, �ᴴ��һ������
			* ִ�е� filer ��ʱ��, �ᴴ��һ������

			* ��������, ÿһ��ִ�ж��ᴴ���µ�����,�����������, ��ô����Ӱ������
		
		* �����Ȱ���Ҫ������Ԫ�����л�, ʹ�÷���: asSequence() ,������ռ����
			arrayOf("1","2").asSequence().map { it.length }.filter { it >= 1 }.toList()

			* ���м䲻�ᴴ���κεļ���, ֻ��������ռ���ʱ��Żᴴ��
			* ����͸�Java��Streamһ��һ����, ������û��ִ���ռ�����, ��ô�м��������Ҳ����ִ��
			
		
		* asSequence ���� Java �� .stream()
		* ����һ�����������չ����, ���ֶ���Ҳ����Ϊ������ֵ
	
	# ���� sequence
		* ֮ǰ�� sequence ����ͨ�����ϵ� asSequence ����ȡ,
		* Ҳ�����Լ�ȥ����, ������py��������
			var sequence = generateSequence(0) {it + 1}
			var result = sequence.takeWhile { it <= 100 }.sum()
			println(result) // 5050
			
			* generateSequence() ����һ����ʼԪ��, �Լ���Ԫ�صĲ���lamdba
		


	
	# ������һЩ��غ���
		var pari = Pair(v1,v2)
			* ����һ�� pair ����,һ�����ڹ��� Map ��һ��ӳ��
			* ��������������(���Ƿ���)
				public val first: A
				public val second: B
			
			* �ö�����Ա��⹹��ֵ
				var (key, value) = Pair("name", "KevinBlandy")
				println("key=$key, value=$value")

	# Kotlin ��  Java �ļ��Ϲ�ϵ
		Iterable	MutableIterable	  : Iterable<T> 
		Collection	MutableCollection : Collection<E>, MutableIterable<E>
		List		MutableList		  : List<E>, MutableCollection<E> 
		Set			MutableSet		  : Set<E>, MutableCollection<E> 

		ArrayList
		HashSet
		
		* ʹ��  Mutable... ��ͷ�ı�ʾ�����޸����ݵĵĽӿ�
		* Java�� ArrayList,HashSet ���̳��� Kotlin�Ŀɱ�ӿ�
	
	# ���ϴ�������
		+----------------------------------------------------------------------------------------------
		|��������	���ɱ�			�ɱ�
		|List		listOf			mutableListOf(),arrayListOf()
		|Set		setOf			mutableSetOf(),hashSetOf(),linkedSetOf(),sortedSetOf()
		|Map		mapOf			mutableMapOf(),hashMapOf(),linkedMapOf(),sortedMapOf()
		+----------------------------------------------------------------------------------------------


---------------------
�ɱ伯�Ϻ�ֻ������	 | 
---------------------
	# ֻ�����Ͻӿ�
		kotlin.collections.Collection
	
	# �ɱ伯�Ͻӿ�(��������޸�)
		kotlin.collections.MutableCollection

		* ���̳��Խӿ�:kotlin.collections.Collection
		* ����� add / remove �ȷ���
	
	# �Ѽ���ת��Ϊ����
		toTypedArray(): Array<T>
	
	
	# �����������͵ļ���
		* Ϊ�˱�ʾ�����������͵�����, Kotlin �ṩ��N�����������
		* ÿ���������Ͷ���Ӧһ��
			IntArray
			BooleanArray
			...
		
		* ��Щ���ᱻ����Ϊ: int[], char[].... 
		
		* ֱ�Ӵ���, һ���ڲ���ָ������ĳ���
		* ��Ϊ�ǻ�����������, ������Ĭ��ֵ
			IntArray(size: Int)

		* ���캯�����������һ�� lambda ����, ���ڳ�ʼ�������Ա
		* ���ÿ���Ǳ�, ���ݸ��� lambda, ���Ұѷ���ֵ���������Ԫ��
			var arr = IntArray(10){i -> i + 1}
			println(arr.joinToString(",")) // 1,2,3,4,5,6,7,8,9,10
					
		
		* ʹ�ù�����������
			intArrayOf(1,2,3,4,6)
		
		
		* ��װ���͵����ݼ���, ����ͨ�� toXxxArray(), ת��Ϊ�����������͵ļ���
		    var array:ArrayList<Int> = arrayListOf(1,2,3)
			var intArray = array.toIntArray()
		
---------------------
����				 | 
---------------------
	# Kotlin�� Array ���� ����
		* ֧��ʹ���±�������ԪԪ��
			var list:List<String> = arrayListOf("1","3")
			var value = list[1]

			* Խ������쳣
		
	
	# Array �Ĺ��췽��, ���Խ���һ������Ĵ�С, �� lambda ���ʽ
		* �����ʹ�� lmabda ��ʼ���������, �������ǵ�ǰ�ĽǱ�
			var letters = Array(10) { i:Int -> (i + 1).toString()}
			println(letters.joinToString(",")) // 1,2,3,4,5,6,7,8,9,10
