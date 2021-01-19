----------------------------
�ӿ�						|
----------------------------
	# ʹ�� interface ����һ���ӿ�
		interface Parent {
			fun foo()		// �����ĳ��󷽷�
			fun bar() = println("Ĭ�ϵ�ʵ��")
		}

		* ����ʹ��Ĭ�ϵ�ʵ�ַ���, �������� default �ؼ���, ֱ��д����������¶���
		
	# ���ʵ���˶��������ͬǩ��Ĭ�Ϸ����Ľӿ�, ��ô����Ҫ��ʵ������д�÷���(Java�����������զ�����???)
		interface Parent1 {
			fun bar() = println("Parent2")
		}
		interface Parent2 {
			fun bar() = println("Parent1")
		}
		class Sub : Parent1, Parent2 {
			override fun bar() {
				// ����Ҫ��д bar ����, ��Ϊ������������ӿ��ж���Ĭ�ϵ�ʵ��
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}
		};
		
		* ������������, ʹ�� super �ؼ��� + ������, �����ڷ��͵ĸ�ʽ, ȥָ����Ҫ�����ĸ������ӿڵķ���
			class Sub : Parent1, Parent2 {
				override fun bar() {
					return super<Parent1>.bar()
				}
			};
	
	# Kotlin �Ǽ���Java6��,��Java�Ľӿ�Ĭ�Ϸ�����Java8����
		* Kotlin��ѽӿڵ�Ĭ�Ϸ���,�����һ��������, Ȼ������Ĭ��ʵ�ֵķ�����, ����һ����̬����
		* Java ʵ��Kotlin �Ľӿ�, ����Ҫ��д���ķ���, ����Ĭ�Ϸ���
	
	# �ӿ�, ����������������, �������ʵ�ָ�����
		interface Super {
			var name : String // ��������
		}

		class Sub(override var name: String) : Super ;
		* ֱ���ڹ��캯������, ���� override �ؼ���

		class Sub1(var args: String) : Super{
			override var name : String
				get (){
					return args
				}
				set (value:String){
					args = value
				}
		}
		* �Լ�ʵ��getter
		* ����Ҫʵ�� setter, ��Ϊ�ӿ���ʹ�� var, ������ val ����

		class Sub2 : Super{
			override var name = foo();
			private fun foo () = ""
		}
		* �Լ���ʼ������

	
	# �ӿ������� getter �� setter ����
		* ���Ա�����̳�

		interface Super {
		var name : String
			get(){
				return ""
			}
			set(value:String) {
			}
		}
		
			

----------------------------
�ӿڵ�ʵ��					|
----------------------------
	# ʵ���﷨
		import java.io.Serializable
		interface Parent {
			fun foo()
		}
		class Sub : Parent, Serializable {
			override fun foo() {
				println("����ʵ��")
			}
		}
		fun main(args:Array<String>) {
			var obj :Parent = Sub()
			obj.foo()
		}
		
		* ʹ�� override �ؼ������η���,��ʾ��д, ��������ǿ����ӵ�
		* ʹ��ð�� : ������ implements �ؼ���, ����ͨ�����ŷָ�n���ӿ�, ��ʾ��ʵ��
		* ð�� : Ҳ���Դ��� extends �ؼ���, ����ֻ����һ��������/����

	

