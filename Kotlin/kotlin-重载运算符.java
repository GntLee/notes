--------------------
Kotlin ���������	| 
--------------------
	# ��py��ʵһ��
		* ʵ����ĳЩ�̶����Ƶķ�����, �Ϳ���ʹ���ض����������ֱ������
		* ��Щ���͵ķ�����Ҫ��ӹؼ���:operator
		* ����ʹ�øùؼ��ֺ�, ����������һ��Ҫ���Ϲ淶
		* �÷���������ֵ�������ռ���Ľ��
	
	# Demo
		data class Foo (val num:Int){
			operator fun plus(other:Foo) : Foo{
				return Foo(this.num + other.num)
			}
			operator fun plus(other:Int) : Int{
				return this.num + other
			}
		}

		fun main() {
			var result = Foo(1) + Foo(2)
			println(result)         // Foo(num=3)
			println(Foo(1) + 5) //6
		}
	
	# ������ʹ����չ����������
		operator fun Foo.plus(other:Foo) : Foo {
			return Foo(this.num + other.num)
		}
		
	# ��֧���������ߵĽ���, ��Ҫ���¶��庯��
		* Ϊ Foo ���巽��
			operator fun plus(other:Int) => Foo(5) + 5
		
		* ΪInt���巽��
			operator fun plus(other:Foo) => 5 + Foo(5)

	
	
	# �����صĺ���
		a * b		times
		a / b		div
		a % b		mod
		a + b		plus
		a - b		minus