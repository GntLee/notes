-------------------------
lambda					 |
-------------------------
	# �������﷨
		* ʹ�ô����Ű����������ʽ: {[�β�]:[����] -> ������ }

			var func = {x:Int -> x * x}
			println(func(5))

		* lambda���ʽ���Ա���ֵ��һ������, ���յ���һ��������ִ��(�������һ������)
	
		* lambda���ʽ,֧��ֱ�ӵ���,�е�����js���﷨: (function(){})()
			{ println("KevinBlandy") }()

			{ value:String -> println(value) }("KevinBlandy")
		
		* lambda ���������ڵ��д���, �����ж��д����
		* ���һ�д���Ľ��, ����lambda���ʽ�Ľ��
			 var func = {x:String -> {
				var x = 15
				println("Hello World")
				x
			}}
		

	# ��� lambda ���ʽ�Ǻ��������һ��ʵ��, ��ô����д����������
		rrayOf("").maxBy() { i:String -> i.length }

		* ���﷨�ֵֹ�


	# ��� lambda ���ʽ�Ǻ�����Ψһʵ��, ��ô�����Ŷ����Բ�Ҫ��
		arrayOf("").maxBy { i:String -> i.length }

	
	# ������������ǿ����Ƶ���, ��ô���������Ͷ��ǿ��Ժ��Ե�
		arrayOf("").maxBy { i:String -> i.length }
		arrayOf("").maxBy { i  -> i.length }

		* arraOf(""), ���ص���һ�� Array<String>, �����Ƶ�����, ���е��������Ͷ����ַ���, ������� lambda ����ʡ�Բ�������������
		* Ҳ�п����Ƶ���������������, ����һ������: �ȱ��Ƶ�, զ��զд, ���뱨����, �ٸ�(���Ͻ̵�)
	
	# ʹ��Ĭ�ϵĲ�������
		* ����Ǽ򻯵�����߼��ĵز���
		* ���lambdaֻ��һ������, ���ҿ����Ƶ�����������, ��ôʡ��lambda����������, ʹ��Ĭ�ϵı������ƴ���
		* ʹ��Ĭ�ϵĲ�������: it ����ʾ����������, it ����������ƴ�������

			arrayOf("").maxBy { it.length }
		
	
	# ���������з��ʱ���
		* ��Java��ͬ ,Kotlin�� lambda ���ʽ�������Է����ⲿ�� final ����, �����������޸�����

			fun foo(list:Collection<String>,prefix:String):Int{
				var count = 0
				list.forEach {
					println("$prefix$it")
					count ++  // ��lambda�޸�����ľֲ�����
				}
				return count
			}
			fun main(){
				var result = foo(arrayListOf("1","2","3"), "-")
				println(result)
			}

		* ��������һ���ⲿ�� final ����ʱ, ����ֵ�ᱻ��������, �� lambda ���ʽ�洢��һ��(JavaҲ�����ԭ��)

		* ��������һ���ⲿ�ķ� final ������ʱ��, ����ֵ�ᱻ��Ϊ Raf ʵ�������һ�����Ա�������, Raf �� final ���ε�, ���Ա����׵Ĳ�׽
		* Ȼ�� Raf �洢��ֵ, �Ϳ����� lambda ִ��ʱ�����޸�
	
	# Kotlin ��lambda ���ʽ���� һ���ǲ�֧�� this ��
		

-------------------------
��Ա����				 |
-------------------------
	# �� Javaһ��, ���԰�ĳЩ�Ѿ�����õķ���, ��Ϊһ�� lambda

	# ��Ա���õĴ���: [��]::[��Ա]
		var length = String::length
		arrayOf("").maxBy(length)

		arrayOf("").maxBy(String::length)
	
	# ���������õ����㺯��: ::[����]
		fun foo(){
			println("Hello")
		}
		fun main(){
			var f = ::foo // ��ȡ�����㺯��������
			run(f)
		}

		* ��Ϊû����, ���Բ���Ҫǰ���������
	

	# ���췽��������: ::[��]
		data class User(val name:String)

		// ��ȡ��ָ����Ĺ�������
		var userCreate = ::User
		fun main(){

			// ִ��
			var result = userCreate("12345")
			println(result) // User(name=12345)
		}

	
	# Kotlin 1.1 ��ǰ, ��Ҫ�ֶ���ָ�����к������õ�ʵ������
		* �е�������ڷ������˼, �һ�ȡ��ִ�к���, ���Ǹ����ĸ�����������ִ���������, ��Ҫ�ֶ���ָ��
			data class User(val name:String) {
				fun foo(value:String) {
					println("$name,$value")
				}
			}
			fun main(){
				// ��ȡָ����� foo, 
				var foo = User::foo
				// ִ�и����õ�ʱ��, ��һ������Ϊ�����õ������Ķ���(this)
				foo(User("KevinBlandy"),"Hello")
			}
		
		* ע������, �����������, ��ͨ��������ȡ����
	
	# Ҳ�������õ���չ����
		fun String.foo() = println("Hello")
		fun main() {
			// ���ݶ���, ��ȡ����չ����
			var foo = ""::foo
			foo()
		}
	
	# Kotlin 1.1 �Ժ�, ֧�ִ�ʵ�������ȡ����������
		* ��ָ����ʵ����ȡ���ķ�������, ������������,���Կ���ֱ������, �������ֶ���ȥָ������ʱ�Ķ���

			data class User(val name:String) {
				fun foo(value:String) {
					println("$name,$value")
				}
			}
			fun main(){
				var user = User("KevinBlandy")
				// ��ȡָ������ķ�������
				var foo = user::foo
				foo("Hello")
			}

		* ע������, �����������, ��ͨ����������ȡ����




		

	# Kotlin �� lambda �����޷�ĺ� Java api �Խ�
		* ��һ�� Lmabda ���� Java ����

			public class Main {
				public static void foo(Runnable runnable){
					new Thread(runnable).start();
				}
			}

			fun main() {
				Main.foo {println("Hello")} // ���������Զ��İ����lambdaת��Ϊ Runnable ��ʵ��
			}
		
			
			* Ҳ����ʹ�� object ��������������
			* ����ÿ�ε���, ���ᴴ��һ���µ�����������
				Main.foo(object : Runnable {
					override fun run() {
						println("Hello World")
					}
				})
			
	
	# SAM���췽��,�� lambda ת��Ϊ �ӿ�
		* ɶ���������
			fun callDone() : Runnable {
				return Runnable { println("done") }
			}
			fun main() {
				var runnable = callDone()
				runnable.run()
			}
			
			* �ҵ������ǰ� lambda ������ʽ, ��Ϊһ��ʵ�ִ���,���Ը�ֵ������
		
		* �﷨: [����] [lambda]
			var runnable = Runnable { println("Hello World") }
		
	
-------------------------
with / apply			 |
-------------------------
	# �������ߵ�lambda
		* �ǳ������, ��TM������ Javascript ���溯���� call/apply ô?
		* ����ָ�� lambda ����ʱ�� this ָ��(������)

			fun alphabet () : String {
				val stingBuilder = StringBuilder()
				for(letter in 'A'..'Z'){
					stingBuilder.append(letter)
				}
				return stingBuilder.toString()
			}
			* ��Ҫ���ȥʹ�� stingBuilder ��������, ȥִ���������ķ���

			fun alphabetWith () : String {
				val stingBuilder = StringBuilder()
				with(stingBuilder, {        
					for(letter in 'A'..'Z'){
						this.append(letter)
					}
					return toString() // ʡ�� this, ִ�з��ʵ��ﵽ����������ķ���
				})
			}
			* ʹ�� with ���� this ָ��Ķ���, ��ô�� lambda �������ʹ���ʹ�� this
			* Ҳ����ʡ�� this �ؼ���, ��Ϊ�������Ѿ�ȷ����, ����ֱ�ӷ��ʷ���
		
		* �﷨: with ([this����] , [�����( lambda ���ʽ )])
	
	
	# ��ʵ��չ����,����һ�ִ��˽�����(ִ��������)��lambda
		* �����, ��һ����̬����, ��һ����������ִ�еĶ���, ����������
	

	# With ����,Ҳ���н����
		* Ҳ����˵,������ʽ���Է���һ��ֵ
		* ���������һ�н��, ��Ϊ����ֵ
		
		var letters = with(StringBuilder(), {
			for(letter in 'A'..'Z'){
				this.append(letter)
			}
			toString()
		})
	
	
	# ��������ͻ������
		class Bar {
			fun foo (){
				println("Im Foo In Bar")
			}
		}

		class Foo {
			fun foo (){
				println("Im Foo In Foo")
			}
			fun bar (){
				with (Bar()){
					foo()            // Im Foo In Bar
					this.foo()      // Im Foo In Bar
					this@Foo.foo() // Im Foo In Foo
				}
			}
		}

		fun main() {
			var foo = Foo()
			foo.bar()
		}
		
		* ����һ�� with ��lambda ���ʽ,��һ���෽����, ʹ�� this ���߲�ʹ��, ���õķ�����, �ڵ�ǰ����Ҳ����
		* �����Ҫ�ڵ�ǰ��with�е������е�ͬ������,��Ҫʹ��: this@[��].[����]
	

	# apply
		* apply �� with �����ö���һ����, ����Ϊ���޸�����ʱ����
		* �������Ϊһ����չ���� , Ҳ����˵�ܶ���󶼿���ִ���������
			val stringBuilder = StringBuilder().apply {
				for(letter in 'A'..'Z'){
					this.append(letter)
				}
			}

			fun main() {
				println(stringBuilder.toString())
			}
		
		* apply,��Ҫ����������ȥִ��
		* apply, ʼ�ջ᷵�ؽ����߶���(������)

		* ���������ַ�ʽ, �ڴ�����϶��������һЩ��ʼ������

	

