-------------------------
Ŀ¼����ṹ			 |
-------------------------
	# ��������,��javaһ��,����˵

	# �������
		* Kotlin �Ա�����ɶ�Ĺ���λ�� package,Ҳ����˵ kt �ļ���������ɶ,�Եò���ô��Ҫ
		* Kotlinһ�� kt �ļ�������ܶ�����N�����,����,��Ҫʹ��ʲô,�͵���ʲô

			package io.kevinblandy.funcs
			fun max(a:Int, b:Int) = if (a > b) a else b;

			import io.kevinblandy.funcs.max
			fun main(args:Array<String>){
				println(max(1,2));
			}

		*  ��ͬ package �µ����� kt �ļ���,�����ظ�������ͬ���Ƶı���,�����쳣

		* ����ʹ��: * ����������

		* ������ı����뵱ǰ�������ı������Ƴ�ͻ��ʱ��

			* �����ʹ�� * ����ı�����ͻ,��ǰ���ı������ȼ���,��֮,�� import �ı������ȼ���

				import io.kevinblandy.funcs.x;
				import io.kevinblandy.funcs.*;
				var x = "inner";
				var y = "inner";
				fun main(args:Array<String>){
					print("x=$x, y=$y")		// x=outer, y=inner
				}
		
		* ����ʹ�� as ���������/���Ե� ����,pythonһ������
			
		
	# ���㺯��������
		* Kotiin �������ɵ��������, ��Ӧ�ڰ����������ļ�������, ����ļ��е����ж��㺯������Ϊ�����ľ�̬����

		* Ҫ�ı���� Kotlin ���㺯�������ɵ��������, ��ҪΪ����ļ���� @file:JvmName ��ע��, ����ŵ�����ļ��Ŀ�ͷ, λ�ڰ�����ǰ��
		
		* ��������Ҳ��һ��,Ҳ����Ϊ���Ա����
			var ����������,������ getter/setter ����: getXxx/setXxx
			val ����������,ֻ������ getter ���� ����: getXxx
			const val ����������,���� public static final .... ����,����ֱ�ӷ���,����Ҫ����

			@file:JvmName("CommonUtils")
			package io.kevinblandy.funcs

			var var1 = "var1";
			val var2 = "var2";
			const val VAR3 = "var3";

			fun foo(){
				println("Hello")
			}

			import io.kevinblandy.funcs.CommonUtils;
			public class Demo {

				public static void main(String[] args) {

					CommonUtils.foo();

					// getter/setter
					String var1 = CommonUtils.getVar1();
					CommonUtils.setVar1("new value");
					
					// getter
					String var2 = CommonUtils.getVar2();
					
					// �൱�� public static final String VAR3 = "var3"
					String var3  = CommonUtils.VAR3;
				}
			}

	
