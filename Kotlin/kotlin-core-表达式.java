----------------------------
���ʽ						|
----------------------------
	# Kotlin�Ĵ�������ʽ�����з���ֵ��
		* һ���֧���������һ�����ʽ���,�ᱻ��������ֵ

	# if
		* if ���з���ֵ�ı��ʽ
		* ���ֻ��һ�д���,����ʡ�Դ�����
	
	# while �� do while ���Ǹ�Java�ʹ��������һ��

	# as
		* ���ڰ�һ��������ת��Ϊ��һ����������(cast ǿ��ת��)
		* �������ת��,���׳��쳣
			interface Parent
			class Sub:Parent

			var x = Sub();
			var y = x as Parent;
	
	# == �� ===
		* Kotlin ��� == ����Ϊ equals ����
		*  ==== ���ǱȽϵ��ڴ��ַ
	



----------------------------
�������					|
----------------------------
	# ������ python �� for i in range()....
	# ������������Ͼ����ڵ���:kotlin.ranges.IntRange
		var rannge = 1..10;
		for (i in rannge){
			println(i)
		}
		
	# ����ı��ʽ,��ͷ����β

	# �������ĵ���(�ݼ��ĵ���)
		for (i in 100 downTo -5 step 1){
			println(i)
		}
		
		* �� 100 ��ʼ, �� -5 ����
		* �����Ĳ���Ϊ 5

	# ʹ�� until ���Բ�������β
		for (i in 0 until 10){
			println(i)
		}

		* �������� 0 - 9
		* �൱��
			for (i in 0..(10 - 1)){
				println(i)
			}

	# ���� Map �ĵ���,����ʹ����������,ͬʱ���� key �� value
		var map = HashMap<String,String>();
		map["key1"] = "value1"
		map["key2"] = "value2"
		map["key3"] = "value3"

		for((key,value) in map){
			println("key=$key, value=$value")
		}
	
	# ����ʹ�����������������±�ļ���
		var arr = arrayOf("1","2","3","4","5");
		for ((index,value)  in arr.withIndex()){
			println("index=$index, value=$value")
		}
		
		* �ؼ�������,�����ϵ������Ǽ��ϵ� withIndex() ���صĴ��±�ĵ�����: kotlin.collections.IndexingIterable

----------------------------
is ���ʽ					|
----------------------------
	# ʹ�� is �ж�ָ���ı���,�Ƿ���ĳ����������
		var x = 15;
		var isInt = x is Int;
		println(isInt);
	
	# is �����Զ���ת����������
		* �� Java�� instanceof ����,�����������Զ���ǿ��ת����������

		class Sub1(): Parent
		class Sub2(): Parent

		fun foo(value: Parent){
			if (value is Sub1){
				println(value)		// �Զ���castΪSub1
			}else if(value is Sub2){
				println(value)		// �Զ���castΪSub2
			}
		}

----------------------------
in ���ʽ					|
----------------------------
	# ʹ�� in ���� !in ���ж�ֵ�Ƿ������ĳ������/����

		fun isLetter(char:Char) = char in 'a'..'z' || char in 'A'..'Z'

		var exists = "Java" in setOf<String>("Java","Groovy","Kotlin","Scala")
	
----------------------
�⹹��ֵ,��[]չ��	 |
----------------------
	# ��js/py���
	# [] չ��, ʹ�� * �����
		fun foo(var1:String, vararg values: String, var2:String){
			for ((index, value) in values.withIndex()){
				println("value=$index, value=$value")
			}
		}

		fun main(args:Array<String>){
			foo("1",* args,var2 = "")
		}

	# �⹹��ֵ
		var (key, value) = Pair("name", "KevinBlandy")
		println("key=$key, value=$value")
		

----------------------------
when ���ʽ					|
----------------------------
	# ����˵�� case ����������
	# ���ʽ����ƥ��ı���,�������κζ���
	# �ñ��ʽ���Է���һ�����
		fun bar (number: Int):String {
			return when (number){
				1 -> {
					"����";
				}
				2 -> {
					"ż��"
				}
				else -> {
					"ɶҲ����";
				}
			}
		}

		* ��������ʡ��
	
	# ֧���ں�����ʹ�ñ��ʽ
		fun getLang(lang :Lang) = when (lang){
			Lang.JAVA -> "java"
			Lang.C -> "c"
			Lang.JAVASCRIPT -> "javascript"
			Lang.PYTHON -> "python"
		}
	
	# ֧����ͬ����Ķ��ƥ��ֵ
		fun foo(number:Int) = when(number){
			1,3,5,7,9 -> "����"
			2,4,6,8,10 -> "ż��"
			else -> "ɶҲ����"
		}
	
	# �� when �ı�����ö��ʱ,����Ҫָ�� else 

	# ֧�ֳ��� == ƥ��ģʽ���������ƥ��ģʽ
		* Ҫ��ƥ���������������,���Բ�����ʽ�ļ���
		* ���ҽ���� boolean ����

		fun foo(number:Int) = when(number){
			in 0..9 -> "��0-9��Χ��"
			is Int -> "��Int"
			else -> "����0-9��Χ��"
		}

	# Ҳ��������ȡ�� if-else if��
		* ������ṩ����,���еķ�֧�������Ǽ򵥵Ĳ������ʽ
		* ��һ����֧������Ϊ��ʱ��ִ�и÷�֧�Ĵ����

		var x = 5;
		when {
			x > 5 -> println("����5");

			x < 5 -> println("С��5");
			
			else -> println("����5");
		}

	
	# when �е�����ʹ�� ==(equals) �����ж�
		fun foo(v1:Int, v2:Int) :String {
			return when(setOf(v1,v2)) {
				setOf(1, 2) -> "1��2"
				setOf(3, 4) -> "3��4"
				else -> throw Exception("ɶҲ����")
			}
		}

		fun main(args:Array<String>){
			println(foo(2,1))           // 1��2
			var v1 = setOf(1, 2);
			var v2 = setOf(1, 2);
			println(v1 === v2)          // false
		}

----------------------------
λ������					|
----------------------------
	# û��Java�ṩ����Щ����
	shl		����				<<
	shr		����				>>

	ushr	�޷�������			>>>
	and		��					&
	xor		���				|
	inv		ȡ��				^

