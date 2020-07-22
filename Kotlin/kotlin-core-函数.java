----------------------
����				  |
----------------------
	# �����ĺ�������
		fun max(a:Int, b:Int): Int{
			return if (a > b) a else b;
		}

		fund [������]([������]:[����]):[����ֵ����]{
			// ������
		}

	
	# ���ʽ������
		* �ڷ���ֵ���ͺ���� = ,����������
			fun min(a:Int, b:Int):Int = if (a > b) a else b;

		* ��������ʡ�Է���ֵ���ʽ
			fun min(a:Int, b:Int) = if(a > b) a else b;

		* ������Ƶ�������ֵ������,����,���ֱ��ʽ��������Ҫ��������ֵ����,�Լ� return ���
		* ���Ǳ��ʽ����,������ڷ���ֵ,����Ҫ��������ֵ����,�Լ� return ���
	
	
	# �����ĵ���,����ʹ����������
		fun foo(var1:String, var2:String, var3:String ){
			println("var1=$var1, var2=$var2, var3=$var3")
		}

		fun main(args:Array<String>) {
			foo(var3="1", var2="2", var1="3")	// var1=3, var2=2, var3=1
		}

		* ��Ϊ���õ�ʱ��ʹ����������,��ô���ݲ�����ʱ��,���Բ�����˳�򴫵�
		* һ������ʹ����������,����Ĳ�����ʹ����������
	
		* ���� java �ķ���,����ʹ����������(java8�Ժ�ų����˲������Ʊ�������,KotlinΪ�˼���jdk6)
	
	# ����������Ĭ��ֵ
		* �����ͺ���ʹ�� = ����Ĭ��ֵ
			fun foo(var1:String, var2:String = "default" ){
				println("var1=$var1, var2=$var2")
			}
		
		* ʹ�ó���ĵ����﷨ʱ, ���밴�պ��������ж���Ĳ���˳������������, ����ʡ�Ե�ֻ������ĩβ�Ĳ���
		* ���ʹ����������, ����ʡ���м��һЩ����, Ҳ��������Ҫ������˳��ֻ��������Ҫ�Ĳ��� 
		* ������Ĭ��ֵ�Ǳ����뵽�����õĺ�����,�����ǵ��õĵط� 

		* �� Java �е��� Kotlin ������ʱ��,������ʽ��ָ�����в���ֵ
		* �����Ҫ�� Java ��������Ƶ���ĵ���,����ϣ�����ܶ� Java �ĵ����߸����,������ @JvmOverloads ע����
		* ���ע��ָʾ���������� Java ���غ���, �����һ����ʼʡ��ÿ������
			fun foo(var1:String, var2:String,var3:String){
			}
			fun foo(var1:String, var2:String){
			}
			fun foo(var1:String){
			}
	

	# �ɱ����
		* �ڲ�����ʹ�ùؼ���: vararg
			fun foo(var1:String, vararg values: String, var2:String){
				for ((index, value) in values.withIndex()){
					/*
						value=0, value=2
						value=1, value=3
					*/
					println("value=$index, value=$value")
				}
			}

			fun main(args:Array<String>){
				foo("1",* args,var2 = "")
			}
			* vararg ���Է���������λ��
			* ������������һ�������Ļ�, ��ô������Ĳ���, �ڵ��õ�ʱ����Ҫ�ֶ���ͨ������������ָ��


		* �ɱ������Java����һ����ͬ, �ɱ������ʵ����һ����������
			* ����Java�Ŀɱ����, ����һ������Ļ�,�������������ǿɱ����
			* ����Kotlin�Ŀɱ����, ����һ������Ļ�, ��Ҫ�Լ�չ������, ��Ȼ�Ļ�, �����������ֻ����Ϊ�ɱ������һ��Ԫ��(ʹ�� *�ṹ���� - ���PY��TMһ��)
	
	# ��׺����
		* ���ú����ķ���
			1.to("one")		һ�����
			1 to "one"		��׺����
		
		* ��ν����׺���þ���: [����] [����] [����]
		* ����׺������, û�ж���ķָ���, ����������ֱ�ӷ���Ŀ��������ƺͲ���֮���

		* ������׺�������õĺ���, ����ʹ�ùؼ���: infix ����
		* ������׺����, ֻ����һ������
			class Foo(){
				infix fun foo(value:String):String{
					return value.plus(value)
				}
			}

			fun main(args:Array<String>){
				var foo = Foo();

				var result = foo.foo("Hello")
				println(result)

				result = foo foo "Hello";
				println(result)
			}
		
		* ����ʹ����չ����, ������һ�����ж��󶼿���ʹ�õ���׺���õķ���(��ʵϵͳ�Ѿ��������)
			infix fun Any.to(value:Any) = Pair(this,value)
			fun main(args:Array<String>){
				var pair = "name" to "KevinBlandy"
				println(pair)
			}
	
	# �ֲ�����
		* �����ڲ������ٴ�����һ����������,��js/pyһ��
			fun outer(){
				var number = 15
				fun inner (){
					println("number=$number")
				}
				inner()
			}
		*  �ڲ�����,���Է����ⲿ�����ľֲ�����


----------------------
��չ����			  |
----------------------
	# ���ǿ��Ը����е���,����µķ���
	
	# ���Ը� java.lang.String ��,���һ�� lastChar() ����,�����ַ��������һ���ַ�
		fun String.lastChar(): Char = this[this.length - 1]
		var lastChar = "KevinBlandy".lastChar()
		println(lastChar) // y
	
	# ���Ը� ArrayList ���һ�� foo ����
		fun <T : Any?> ArrayList<T>.foo() = println("�Լ���ӵķ���")
		arrayListOf(1).foo();
	
	# �﷨
		fun [Ŀ����].[��������]([��������]) = [������]

		fun [Ŀ����].[��������]([��������]): [����ֵ����] { 
			[������]
		}

		* ����չ��������,�������ɵ�ʹ�� this ,���߲�ʹ�� �����ʵ��ڲ�������/����
		* ����,��չ�������ܷ��ʵ� private / protected ����/����
	

	# ��չ����,��Ҫ����Ż���Ч(��ʵ��ν�ĵ���,����ִ�е����Ǿ���չ���������ô���)
		package io.kevinblandy.common
		fun String.lastChar(): Char = if (this.isEmpty()) ' ' else this[this.length - 1]

		import io.kevinblandy.common.lastChar as foo;
		var lastChar = "KevinBlandy".foo();
		println(lastChar)
	
	
	# ʵ����,��չ�����Ǿ�̬����
		* ��������ѵ�һ������,����Ϊ�˵�ǰ���õĶ���
		* ��չ�����������ɴ������,�����ж������������
	
	# �� Java �е�����չ����
		* ֪������չ�����ı���,����һ����̬����
		* ���������ʵ�ǳ��ļ�,����չ��������һ����̬����,Ȼ��ѵ��øú����Ķ���,���ݸ���չ�����ĵ�һ������

		@file:JvmName("CommonUtils")
		package io.kevinblandy.common
		fun String.foo(value:String) =  this + value;

		import io.kevinblandy.common.CommonUtils;
		public class Main {
			public static void main(String[] args) {
				String value = CommonUtils.foo("KevinBlandy","123456");
				System.out.println(value);		// KevinBlandy123456
			}
		}

	
	# ��չ������һ���ǳ���Ч���﷨��,����������ͨ������,��������չ�Ķ���
		import java.lang.StringBuilder
		fun Collection<String>.join(
			separator:String,
			prefix:String,
			suffix:String) : String {
			var stringBuilder = StringBuilder(prefix)
			for((index, value) in this.withIndex()){
				if (index > 0){
					stringBuilder.append(separator)

				}
				stringBuilder.append(value)
			}
			stringBuilder.append(suffix)
			return stringBuilder.toString()
		}

		fun main(){
			var result = arrayListOf("1","2","3","4","5").join(", ", "[", "]")
			println(result)
		}
	
	# ��չ���������Ա���д
		* ��չ������������д,��Ϊ��չ�����ᱻ�����һ����̬����,���øú����Ķ�����Ϊ��һ���β�
		* ��չ�������������һ����,������������֮���

			open class View {
				open fun click () = println("View Click")
			}
			open class Button : View (){
				override fun click() = println("Button click")
			}
			
			// �����඼��չ����
			fun View.showOff() = println("Im a View")
			fun Button.showOff() = println("Im a Button")

			fun main(){
				var view: View = Button()
				// ��ͨ����, ִ��ʱ����д
				view.click()				// Button click 

				// ��չ����, ִ��ʱû�б���д
				view.showOff()				// Im a View
			}
		
		* ���һ����ĳ�Ա��������չ��������ͬ��ǩ��(����һ��, �β�һ��),��ô��Ա����������ʹ��

----------------------
��չ����			  |
----------------------
	# ��չ�����б�Ҫ����չ����һ���˽�
	# ��չ����û���κ�״̬, ��Ϊû�ط��洢, ��Ϊ���ܸ����е�Java������Ӷ�����ֶ�
	# ��չ���Ա����Ͼ��������һ��: getter/setter 
	
	# �����﷨
		[var/val] [��].[��������] : [����ֵ] get() = [������]

	# �� String ���һ�� lastChar �� getter ����
		val String.lastChar get() = this[this.length - 1]
		var lastChar = "KevinBlandy".lastChar;
		println(lastChar) // y
	
	# �� StringBuilder ��� getter/setter ����(StringBuilder �����޸�)
		var StringBuilder.last : Char
			get() {
			   return this[this.length - 1]
			}
			set(value: Char) {
				this[this.length - 1] = value
			}
		val stringBuilder = StringBuilder("KevinBlandy")
		stringBuilder.last = 'I';
		println(stringBuilder.last)		// I
	
	# getter ��չ����ʹ�� val ����, ��Ϊֻ�ܶ�, setter ��չ���Ա����� var ����, ��Ϊ����д

	# ��Java�е�����չ����,��Ҫ��ʾ�ĵ���getter����(�������������շ�������get����)
		@file:JvmName("CommonUtils")
		package io.kevinblandy.common
		val String.last  get() = this[this.length - 1]

		import io.kevinblandy.common.CommonUtils;
		public class Main {
			public static void main(String[] args) {
				// getLast
				Character character = CommonUtils.getLast("KevinBlandy");
				System.out.println(character);
			}
		}

	
	# ���Ե���չ,�����ڷ����ڲ�ִ��
		val String.lastChar get() = this[this.length - 1]; // �����ⲿ��չ����, ����

		fun main(){
			// val String.lastChar get() = this[this.length - 1]; �����ڲ�������չ����, �����쳣
			println("123".lastChar)
		}





