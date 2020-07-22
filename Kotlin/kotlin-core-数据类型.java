----------------------
����				  |
----------------------
	# �����������ؼ���:var
		var name = "KevinBlandy";

		* �Զ����Ƶ�����������,����Ҫ�ֶ�������������
		* �����޸ı�����ֵ,���ǲ����޸ı�������������,����������¸�һ����ͬ�������͵�ֵ,���쳣
			
	
	# �ֶ���������������
		var name: String = "KevinBlandy";
		var age: Int = 25;
	
		* �������ֻ������,��û�г�ʼ��,��ô����Ҫ��������
			var name:String ;
			name = "KevinBlandy" ;
		
		* ��������Ҫ��ʼ�������ʹ��,��Ȼ�����쳣
	
	# �����������ؼ���: val
		* �� java �� final һ��,��ʼ����Ͳ����޸�,����������,�ٳ�ʼ��

			val name:String;
			name = "KevinBlandy";
		
		* �ñ���������ֵ�����޸�,�������õĶ���,�ǿ��������޸���������ֵ��
	

----------------------
������������		  |
----------------------
	# ��Java��һ��, Kotlin �����ֻ������ͺͰ�װ����

	# ��������
		Byte,Short,Int,Long

	# ����������
		Float,Double

	# �ַ�����
		Char

	# ��������
		Boolean
	

	# ÿһ�ֻ������������Ͷ�������һϵ�е�װ����
		toChar()
		toInt()
		toLong()
		..

		* ���Խ��в�ͬ�������͵�ת��

----------------------
Any					  |
----------------------
	# Any ��ʵ���� java.lang.Object 
		* ����KotlinһЩ����ĸ�����
		* Kotlin��Any��ʱ��, ������ᱻ����Ϊ Object
	
	# ��Javaһ��, �ѻ����������͸�ֵ����, ���Զ���װ��
		var n:Any = 2
	
	# Any Ĭ���Ƿǿ�ֵ, ���ܸ�ֵΪ null, ����ʹ�� Any? ��ʾ���� null ֵ

	# Any ���ܵ��� Object �� wait() / notify() �ȷ���
		* ���ǿ����ֶ��İ�ֵת��Ϊ Object �����
	

----------------------
Unit				  |
----------------------
	# ������� Java �е� void, ��ʾ����Ҫ����ֵ
		fun foo():Unit {}
		fun foo() {}

		* Unit ���͵ķ���ֵ, ����ʡ��, ��������治��Ҫ return ���
	
	# Java �� Void �Ƚ�
		* Java �ķ���, ������һ�����ͷ��ط���
		* ���าд��ʱ��, ʹ�� Void, Ҳ����Ҫ�ֶ��� return һ������
			interface Foo <T> {
				T process();
			}
			class FooImpl implements Foo<Void> {
				@Override
				public Void process() {
					return null;  // ����Ҫʹ�� return �ؼ���
				}
			}

		* ����Kotlin ����Ҫ
			interface Foo <T>{
				 fun process(): T
			}
			class FoolImp : Foo<Unit> { // ����ָ��Ϊ Unit
				override fun process() {
					// ����Ҫ return ���
				}
			}
	
----------------------
Noting				  |
----------------------
	# �÷���ֵ���ͱ�ʾ, ���������Զ���᷵��
		* ���ڻ��׳��쳣�ķ���
			fun foo(): Nothing{
				throw Exception("")
			}
		* ������ѭ���ķ���
			fun bar (): Nothing {
				while (true){}
			}
	
	# ��û�κ�ֵ, ֻ�ܵ��������ķ���ֵ��, ���ߵ������Ͳ�������


