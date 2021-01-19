----------------------------
ö��						|
----------------------------
	# ö�ٵ�����
		enum class Lang {
			JAVA, C, JAVASCRIPT, PYTHON
		}

		* ����kotlin������,��������,Ӧ��ֻ������һ�� enum �ͺ���, Ϊɶ��Ҫ����һ�� class ?
		* Ϊ�˲�ռ�� enum �ؼ���,ֻ�� enum ������� class �ؼ��ֵ�ʱ��,enum ���ǹؼ���,������ʱ��,���Ե�����������ʹ��(Ŷ)
	
	# ����������/������ö��
		enum class Lang (var desc :String ,var order :Int ){
			// ����ʵ����ʱ���ʼ��
			JAVA("��������õ�����",1),
			C("��������ţ�Ƶ�����",2),
			JAVASCRIPT("ȫջ����",3),
			PYTHON("�����е�����",4);

			fun say(){
				println("name=${this},desc=${this.desc},order=${this.order}");
			}
		}

		* ������һ��ö�ٺ�������(����),��ô���һ��ö�ٺ���һ��Ҫʹ�÷ֺ�: ; (����Kotlin��Ψһһ��ǿ��Ҫ��ʹ�÷ֺŽ����ĵط�)
	
	# ����ʹ�� when ������ö��
		fun getLang(lang :Lang) = when (lang){
			Lang.JAVA -> "java"
			Lang.C -> "c"
			Lang.JAVASCRIPT -> "javascript"
			Lang.PYTHON -> "python"
		}

		* �����Ҫƥ�������,��ʹ�ö��ŷָ����ֵ
			fun getLang(lang :Lang) = when (lang){
				Lang.JAVA, Lang.PYTHON, Lang.JAVASCRIPT -> "��ϲ����"
				Lang.C -> "��ó���"
			}
	
	# ����ʹ�� * һ���Ե������е�ö��ʵ��,�Ӷ�����ֱ�ӷ���ʵ��,������Ҫͨ��ö���ർ��
		import io.kevinblandy.funcs.Lang.*
		fun main(args:Array<String>){
			println(PYTHON)
		}
	
	
	# ö�ٿ��Լ̳�/ʵ��,���Ҹ�д����
		interface Foo {
			fun foo()
		}

		enum class Lang : Foo{
			JAVA {
				override fun foo() {
					TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
				}
			}
		}