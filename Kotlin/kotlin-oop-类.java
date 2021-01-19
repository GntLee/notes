------------------------
��						|
------------------------
	# �򵥵���Ķ���
		class User(
			val name:String,
			val age:Int
		)

		* �﷨: class [������] (val [������]:[��������]);

		* ���Եķ���Ȩ��Ĭ��Ϊ: public
	
	
	# Kotlin �� ������ static (��̬)��Ա

	# Kotlin����/����Ĭ�϶��� final,Ҳ����˵�������̳�/��д��(��Java��һ��),
		* �����������̳�, ����Ҫʹ�� open ����
		* ����������าд, ������Ҫʹ�� open ����

		// ����̳� Parent��
		open class Parent {
			// �������าд bar ����
			open fun  bar() = println("Im bar In Parent")
			// ���������าд foo ����
			fun foo() = println("Im Foo In Param")
		}
		
		* ����� open �������и�д��(��� override �ؼ���), �ø�д����Ĭ�Ͼ��� open ��,������ԶԸ÷������и�д
		* �����Ҫ��ֹ���าд, ����������� final ���η�
			open class Sub : Parent() {
				final override fun bar() = println("Im bar In Sub")
			}
		* ͨ��������: û�б�ע final �� override ����, ���� open ��,���Ա���д

------------------------
getter/setter			|
------------------------
	# ���� getter/setter
		* �� js һ������,getter ��ֵ�Ǽ��������
			class User {
				var name : String
					get() {
						return "����Ƕ�ȡ��name����"
					}
					set(value: String ){
						println("��������õ�name����:$name")
					}
			}
			println(user.name);
		
		* �﷨: 
			val [��������] : [��������] get(){ 
				[�������]
				return [�����ķ���ֵ] 
			}
		
		* ����������� getter, ��ô���Ե�����������: val
		* �����Ҫ���� setter, ��ô���Ե�����������: var 
		* Ҳ����ʡ�� {} ,ʹ�ü򵥵ı��ʽ
			val old : Boolean get() = this.age > 25;

			val old get() = this.age > 25;

			* ͬ��,��������ʡ����������,�������ݱ��ʽ�ķ���ֵ�Զ��Ƶ�����������
		
	#  getter/setter Ҳ����ʹ�� private ��Ȩ�����η������Ʒ���
		class Foo {
			var name : String = "Name Value"
				private set

			fun changeName(){
				this.name = "new Name"
			}
		}
		
		* name ���Ե�setter, ������Ϊ�� private, �ⲿ����ִ�� obj.name = ""  ���޸�����
		* ���Ե����ù���������������ݵ��޸�
	
	# �� setter ������,����ʹ�� field �ؼ��������ʳ�ʼ����ֵ
		class Foo {
			var name : String = "747692844"
				set (value : String) {
					// ����Ϊ��ʼ����ֵΪ��ֵ, ����ִ���޸�
					field = value + "Hello"
				}
		}


------------------------
�๹�췽��				|
------------------------
	# Kotlin �����������췽��
		class User(val nme:String)

		* ������ž��������췽��, �����˹��캯���Ĳ���, �Լ�ʹ����Щ������ʼ��ʵ��������
		* ����ʹ�� constructor �ؼ���, ����������һ�������췽��

		* ʹ�� init �ؼ���, ������һ����ʼ������, ���󴴽���ʱ��, ����� init ����Ĵ���
		* init �������Զ�����
			class User1 constructor(name:String){ 
				val name:String
				init {
					this.name = name
				}
			}
			* �ֶ����� constructor ����, �����Ա����
			* ����ʹ�� init ��ʼ�����Ա����

			class User2 (name: String){
				val name:String = name
			}
			* ʡ�� constructor�ؼ��ֺ�init�����, ֱ�Ӷ����ʼ����Ա����,Ȼ���ʼ��

			class User3(val name:String)
			* ֱ������ʼ���Ĵ���, ��ʡ��
			* ע��Ҫʹ�ùؼ���: var / val
	
	# Kotlin ����ӹ��췽��
		* ʹ�� constructor �ؼ��ֶ���

		open class Super(val name: String)
		class Foo : Super {
			// �ڴӹ��췽��, ���ø���Ĺ��췽��
			constructor(name: String) : super(name){
			}
			// ���췽������
			constructor() : this("Unknown")
		}
	
	# ���캯��, Ҳ����ʹ��Ĭ��ֵ
		class User3(val name:String ,val gender:String = "��")

		* ������еĹ��캯����������Ĭ��ֵ�Ļ�, ������������һ�����������Ĺ��췽��, ��ʹ�����е�Ĭ��ֵ
		* ����ƻ���Kotlinʹ�ÿ��ʱ��ü�, ��Ϊ����ͨ���޲ι�������ʵ��������
	
	# ����, ��Ҫ�ֶ��ĵ��ø��๹�캯����ɳ�ʼ��
		* �̳�����﷨, ����һ������(�ӿڲ���Ҫ����, ����Ϊ�ӿڲ����ڹ��췽��)
			open class Super
			class Sub: Super() // ���๹�캯��, û�в���, ��������Ҳ�����κβ���
		
		* ���ô��в����ĸ��๹����
			open class Super(var name:String)
			class Sub (name:String): Super(name)
		
	# ˽�л����캯��, ������ֹ���洴����ʵ��
		class Foo private constructor() {}

	# ����ϲ����д��, ���ֱ��Ҫ����㻹��
		open class Super {
			private val name:String
			constructor(name: String){
				this.name = name
			}
		}
		class User : Super{
			private val name:String
			private constructor(name:String): super(name) {
				this.name = name
			}
			constructor(): this("Unknown")
		}
	
------------------------
������					|
------------------------	
	# ������Ķ���, ��Ҫ������������η� abstract , �಻�� ֱ��ʵ����
	# ���󷽷�, Ҳ��Ҫ��� abstract �ؼ���
		abstract class Foo {
			abstract fun bar()
		}
	
	# ������ͳ��󷽷�, ������ final ����, ���������̳�, ����д , Ĭ�Ͼ��� open ����
	# ���������ͨ����, Ĭ���� final , ���ܱ���д, ��Ҫ�������� open, ���ܱ���д

------------------------
�ڲ���					|
------------------------	
	# Kotlint���ⲿ��, ���ܷ��ʵ��ڲ���� private ����(��Java��һ��)
		class Outer {

			class Inner (private val name:String){}

			fun foo (){
				var inner = Inner("KevinBlandy")
				// Error:(6, 26) Kotlin: Cannot access 'name': it is private in 'Inner'
				var name = inner.name;
			}
		}
	
	# Ƕ����, �ڲ�����Java��Kotlin�е�����
		+-------------------------------+---------------------+---------------------------------------------+
		|�� A ���� B ������				|	Java			  |	Kotlin										|
		+-------------------------------+---------------------+---------------------------------------------+
		|Ƕ����(����Ҫ�洢�ⲿ�������)	|	static class A	  |	class A										|
		+-------------------------------+---------------------+---------------------------------------------+
		|�ڲ���(��Ҫ�洢�ⲿ�������)	|	class A			  |	inner class A								|
		+-------------------------------+---------------------+---------------------------------------------+

		* ͨ�������ǲ��� inner ���ڲ���, ���������,����ͨ���������ʵ�(ǰ�������㹻�ķ���Ȩ��)
		*  ����inner ���ڲ���, �����ڶ����, ��Ҫͨ����ʵ�����ʵ�
			class Outer {
				class Inner1         // ������
				inner class Inner2  // ���ڶ���
			}
			fun main(args:Array<String>) {
				var obj1 = Outer.Inner1()       //ͨ�������
				var obj2 = Outer().Inner2()     //ͨ���������

			}
		
		* �������ڲ����л�ȡ���ⲿ��Ķ�������, ʹ��: this@[�ⲿ��]
			class Outer {
				inner class Inner {
					// ��ȡ��������
					fun getOuter() = this@Outer
				}
			}
			fun main(args:Array<String>) {
				var outer = Outer()
				var inner = outer.Inner()
				println(inner.getOuter() === outer) // true
			}
		
------------------------
�ܷ���					|
------------------------
	# ʹ�� sealed ���ε���, ����������, ���붼Ƕ���ڸ�����

		sealed class Parent() {
			class Sub1(): Parent()
			class Sub2(): Parent()
		}
		
		* Kotlin 1.1 ��ǰ, ���඼����Ƕ�׶����ڸ�����
		* Kotlin 1.1 �Ժ��޸�������, �����ڵ�ǰ�ļ�������λ�ö��� sealed ������, �����ǽ��������� sealed ����
	
	# when ���ʽ, �ڶ�һ�� sealed ��ʵ������ is ƥ���ʱ��,����Ҫ else ��֧
		sealed class Parent() {
			class Sub1(): Parent()
			class Sub2(): Parent()
		}
		fun main(args:Array<String>) {
			var obj:Parent = Parent.Sub1()
			var result = when (obj){
				is Parent.Sub1 -> "sb1"
				is Parent.Sub2 -> "sb2" // ʹ��is��ʱ��, ���붨�� sealed �� �е�����ʵ��ƥ����
			}
		}
		
		* ���� sealed ��ʵ������ is ƥ��, when ����ķ�֧, ���붨�������е�ʵ��
		* ���û�ж��嵽�κ�һ��, ����׳������쳣

------------------------
������					|
------------------------
	# �� class �������: data �ؼ���, ��ʾ��������һ��������

		data class Foo (var name: String,val email: String)

		* ������, ����Ҫ������(û����,������������), ��Ȼ�����쳣
	
	# ��ν��������, ���ǰ����Զ���д�� :equals,hashCode,toString ��һ����
		
		hashCode
			* ��������е�����,����һ��hashֵ
			* û����ӵ������캯��������, �������hash����

		equals
			* ��������������ֵ�Ƿ����
			* û����ӵ������캯��������, �������Ƚ�
		
		toString
			* ������������˳��, �����е��������л�Ϊ�ַ���
				Foo(name=KevinBlandy, email=747692844@qq.com)
	
------------------------
��ί��					|
------------------------
	# ��ί��, ���Ǹ��ݽӿ�, ��ָ��һ��ʵ��, �������Զ�������ʵ�ֵ��÷���
	# װ�������ģʽ, ����Ҫ�ѷ�����������, ί�и�һ��ʵ����, ʹ��ί�л���, ���Ժܷ����ʵ��
	# Kotlin �����Լ����, ʹ�� by �ؼ���
		class MyCollection<String> (
			private val innerList : Collection<String> = ArrayList())
				:Collection<String> by innerList {
		}
		
		* ����ѡ���Ե���д��Щ��Ҫʵ�����⹦�ܵķ���

	# �﷨
		class [��] ([ʵ�����ӿ�ʵ�ֶ���]) : [�̳нӿ�]  by [�ӿ�ʵ�ֶ���]

	
	# ʹ��ί��, ʵ��һ����add��������Set
		class CountSet<String> (
				private val innerSet : MutableCollection<String> = HashSet<String>())
				:MutableCollection<String> by innerSet {

			var count : Int = 0
				private set

			override fun add(element: String): Boolean {
				this.count ++
				return this.innerSet.add(element)
			}

			override fun addAll(elements: Collection<String>): Boolean {
				this.count += elements.size
				return this.innerSet.addAll(elements)
			}
		}
		fun main(args:Array<String>) {
			var set = CountSet<String> ()
			set.add("1")
			set.add("1")
			set.add("1")
			println(set.count)
		}

------------------------
Ȩ�����η�				|
------------------------	
	# ��ķ������η�
		+--------+-------------------------------+------------------------------------------------------------------+
		|���η�  |								 |																	|
		+--------+-------------------------------+------------------------------------------------------------------+
		|final   |���ܱ���д					 |���г�ԱĬ��ʹ��													|
		+--------+-------------------------------+------------------------------------------------------------------+
		|oepn    |���Ա���д					 |��Ҫ��ȷ������													|
		+--------+-------------------------------+------------------------------------------------------------------+
		|abstract|���뱻��д					 |ֻ���ڳ�������ʹ��, �����Ա������ʵ��							|
		+--------+-------------------------------+------------------------------------------------------------------+
		|override|��д����/�ӿ��еķ���			 |���û���� final ����, ��д�ĳ�ԱĬ���� open ��					|
		+--------+-------------------------------+------------------------------------------------------------------+
	
	# �ɼ������η�
		* ��Java����, ����ʹ��: public, protected, private ���η�

		* Kotlin Ĭ�ϵķ������η���: public, ���ﶼ����
		* JavaĬ�ϵķ������η���: protected, ͬһ�����µ�����Է���, ������Է���

		* ����Kotlinֻ�Ѱ���Ϊ�����ռ�����֯�����һ�ַ�ʽ, ��û����Ȩ�޿���
		* Kotlin ʹ��һ���µĹؼ���: internal ,������ֻ���ڵ�ǰ'ģ��' ��ʹ��
		
		* һ��ģ��, ����һ��һ������Kotlin�ļ�
		* ͨ��������, ����һ�����Ĵ���, ���ܷ��� internal ���εı���
	
		+------------+----------------------------------+-------------------------------------------------------------------+
		|���η�		 |���Ա							|��������															|
		+------------+----------------------------------+-------------------------------------------------------------------+
		|public(Ĭ��)|���еط����ɼ�					|���еط����ɼ�														|
		+------------+----------------------------------+-------------------------------------------------------------------+
		|internal	 |ģ���пɼ�						|ģ���пɼ�															|
		+------------+----------------------------------+-------------------------------------------------------------------+
		|protected	 |�����пɼ�(��������, ͬ��������)  |����ʹ�� protected �ؼ���											|
		+------------+----------------------------------+-------------------------------------------------------------------+
		|private	 |���пɼ�							|��ǰ�ļ��пɼ�														|
		+------------+----------------------------------+-------------------------------------------------------------------+
	
		* �߷���Ȩ��Ԫ��, ���ܱ�¶��Ȩ��Ԫ��
			internal class Foo()
			// �����쳣, ��Ϊ public �ķ���, ��¶�� internal ����
			fun function ():Foo {
				return Foo()
			}
	
		