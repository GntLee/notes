----------------------------
object						|
----------------------------
	# object �� Kotlin ������һ�� �ؼ���, ��������������������ʽ�Ķ���
		* ������, Javascript ����һ�� json ����һ��
		* �������ɵĶ�������, ����, ������Ҫ������
	
		object User {
			var name = "KevinBlandy"
			fun say(){
				println(this.name)
			}
		}
		
		User.say()
	
		
		* �����������԰��� init �����, ���ǲ��ܰ������캯��(����Ҫ����)
		* init �����ֻ��ִ��һ��, ����������������������ʱ��
	

	# ��������, ���ö�˵


	# ֧��ʵ�ֽӿ�, �̳���
		abstract class Super {
			abstract fun foo()
		}

		object User : Comparator<String>, Super() {
			override fun foo() {
				println("impl...")
			}

			override fun compare(o1: String, o2: String): Int {
				return o1.compareTo(o2)
			}
		}
	

	# ���Լ�װ��̬��Ա(��������)
		* Kotlin���಻���ھ�̬��Ա���˵��, ���ǿ��������ڲ�������������������
		* ������ڲ�,���ֶ�����Է��ʵ����еĳ�Ա
		* �ö���û����������д, ���������,û�����ʵ�����İ�������
		* ֱ��ͨ�������Ϳ��Ե���, �ﵽ��̬��Ա��Ч��
			class Foo {
				object Object {
					fun func(){
						println("Hello")
					}
				}
			}

			Foo.Object.func()
		
		* ����������ʵ�ֹ�������
			
			data class Foo private constructor(var name:String){
				object InnerObject {
					fun getInstance (name:String):Foo {
						return Foo(name)
					}
				}
			}
			var single = Foo.InnerObject.getInstance("KevinBlandy")
		
		* ʹ��: companion ֱ�﷽��,�����
		* ��class��, ʹ�� companion �ؼ������� object, ���Ժ���object������,ֱ�ӵ��õ�����
			class Foo {
				companion object InnerObject {
					fun foo() = println("Hello")
				}
			}
			Foo.foo() // ����·��, ֱ�Ӻ��Ե� InnerObject
		
		
		* ��Java�з�����İ�������
		* ��������,Ҳ�ᱻ����Ϊ��ͨ�Ķ���,��Ϊ���һ����̬�ֶδ���, ���ƴ�������:INSTANCE
			@file:JvmName("CommonUtils")
			package io.kevinblandy.funcs

			class Foo {
				object Ruby {
					var name = "KevinBlandy"
				}
			}
			
			import io.kevinblandy.funcs.Foo;
			public class Demo {
				public static void main(String[] args) {
					String name = Foo.Ruby.INSTANCE.getName();
					System.out.println(name);
				}
			}

			
	# ��������ʵ��
		interface Function<T> {
			fun accept(value:T )
		}

		fun foo(function: Function<String>) {
			function.accept("Hello")
		}

		fun main(args:Array<String>) {
			var function = object : Function<String> { //����һ������������, ʵ��function�ӿ�
				override fun accept(value: String) {	// ʵ�ֽӿڳ��󷽷�
					println(value)
				}
			}

			foo(function)
		}

		* û��, �������������ʹ��һ����������, �ǳ���js(���)


	
	# ��Java��ʹ�����ֶ���
		* ���ֶ���, ���������ͨ����̬�ֶ�����һ���еĶ���
		* �ֶε����ƴ�������: INSTANCE

		@file:JvmName("CommonUtils")
		package io.kevinblandy.funcs
		object Foo {
			var name = "KevinBlandy"
		}

		import io.kevinblandy.funcs.Foo;
		public class Demo {

			public static void main(String[] args) {
				String name = Foo.INSTANCE.getName();
			}
		}
	
