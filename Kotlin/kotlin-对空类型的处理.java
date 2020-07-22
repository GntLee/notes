--------------------
null �Ĵ���			|
--------------------
	# �ҵ������� Kotlin ͨ��һЩ�ֶ�, �ѿ�ָ������, �����н׶�, �������˱���׶�

	# Kotlin ��, Ĭ�ϵ��������ݶ�����Ϊ null , ��ֵnull, ����δ��ֵ��������쳣
		var x:String = null; // �쳣
	
	# ��Ҫ�ֶ�������, �ñ�������Ϊ Null
		var x:String? = null; 

		* dei, ���������ͺ������һ���ʺ�, ��ʾ�ñ�������Ϊ null
	
	# ����������� null ֵ, ��ô������һЩ����
		* ����ִ�е��øñ����ķ���
			fun foo(value:String?){
				value.length		// ֱ�ӵ��÷���, �쳣
			}
		
		* ���ܸ�ֵ���ǿձ���
			var x:String? = null;
			var y: String = x // �쳣
		
			fun foo(value:String){}
			fun main() {
				var x:String? = ""
				foo(x)			// �쳣, ��Ϊ�������β��Ƿǿյ�
			}
		

		* ͻ����Щ������Ҫ�Լ�ͨ���ֶ���ȥ�ж� null 
		* һ�����й��ж�, �������ͻ��ס, ���ͻ���Ϊ�ǰ�ȫ��, �Ϳ���ִ��
			fun foo(value:String?){
				if (value != null){
					value.length
				}
			}

			var x:String? = null;
				if(x != null){
					var y: String = x 
				}
				
			fun foo(value:String){}
			fun main() {
				var x:String? = ""
				if (x != null) {
					foo(x)
				}
			}

	
	# ��ȫ���������: ?.
		* �����ʵ���Ǽ�����������, 1: �ж��Ƿ�ǿ�, 2: ִ�в���
			var value : String? = ""
			var result = value?.trim()
			println(result)
		
		* �����null,�Ļ���ô ?. �������ʽ�Ľ��Ϊ null
	
		* �������԰�ȫ�ĵ��÷���, ��֧�ְ�ȫ�ķ�������
			class User(val name:String?)
			fun main() {
				var user = User(null)
				var result = user.name?.trim()
				println(result)
			}

		* ��Ҫʱ,���������Ӷ����ȫ����
			class Account(val email:String?)
			class User(val account:Account?)
			fun main() {
				var user = User(null)
				// ��ʽ�İ�ȫ����
				var result = user.account?.email?.trim()
				println(result)
			}
	
	# Eivis�����: ?:
		* �ⶫ��, ������ж��Ƿ�Ϊnull, �����߱��ʽ�Ľ��Ϊ null, ��ô�͸�ֵΪ �ұߵı��ʽ���
			var value:String? = null
			var result = value ?: "�յ�"
			println(result) // �յ�
		
		* ���Ը���ȫ��������ʹ��
		* ��ȫ����������ڱ���Ϊnull��ʱ��, ��ȫ�ķ��� null, Eivis ��������԰� null ֵ����ΪĬ��ֵ
			fun length(value:String?):Int{
				return value?.length ?: 0
			}
		
		* Ҳ����������,������׳��쳣, ������js: if xx && throw ...
			fun length(value:String?):Int{
				return value?.length ?: throw Exception("����Ϊ��")
			}
		
	
	# ��ȫ��ת�������: as ?
		* as ��ʵ����ʹ���� cast ǿ��ת��, ���ת��ʧ��, ���׳��쳣
		* ����ʹ�ð�ȫ�� as? ת�������, �ڲ��ܽ���ת����ʱ��, ��ȫ�ķ��� null
			class Account
			class User

			fun main() {
				// ת��ʧ��, ����null
				var foo: Account? = User() as? Account
				println(foo)
			}

			* ��Ϊ���ܻ᷵��null, ���Խ��ձ���Ҳ���ǿ���Ϊ null ��
		
		* �������Eivis�������, �� equals ʱ��������
			class User (private val id:Int){
				override fun equals(other: Any?): Boolean {
					// ת��ʧ�ܷ���null, null����eivis�����, ֱ�ӷ��� false
					var otherUser = other as? User ?: return false
					return otherUser.id == this.id
				}
			}
	

	# �ǿն���: !!
		* ���þ����жϱ����Ƿ�Ϊnull, ����� null ���׳��쳣
		* ��������Ŀ����,���㶨����Ե�ʱ���׳�, ������ִ�е�ʱ���׳�, �����ҵ��쳣��
			fun length(value:String?):Int {
				// �ǿն���, ����� null ���״˴��׳��쳣, �����ǵ����������������ʱ���׳�
				var notNullString :String = value!!
				return notNullString.length
			}
	
	# let ����
		* ���Ƕ��Լ������ж�, ����� null, �򷵻�, ��ִ��
		* ����� null, ��ִ�� lambda ���ʽ�Ĵ���

			fun length(value:String):Int {
				return value.length
			}
			fun main() {
				var value : String? = null
				//?. ��ȫ�ķ��� value, 
				var result = value?.let { value -> length(value)}
			}
		
	
	# lateinit �ӳٳ�ʼ��
		* lateinit ���εı���, ��ʾ����Ϊnull, ��Ҫ�ӳٳ�ʼ��
		* ���û��ʼ������, ���쳣:UninitializedPropertyAccessException 
		* ����ȿ�ָ������
		* Ӧ�ÿ��Կ�������ʹ�� var,������ʹ�� val
			class User {
				public lateinit var name : String
			}
			fun main() {
				println(User().name) // UninitializedPropertyAccessException
			}
	

	# �ɿ����͵���չ����
		* ��������
			var value:String? = null
			// null �������: isNullOrEmpty ��Ȼû�쳣?
			var result = value.isNullOrEmpty()
			println(result) // true
		
		* ��ʵ���Ƕ�����չ���Ե�ʱ��, ������������õĶ������Ϊnull
		* ���Ƿ���������ͱ���Ҫ�����ж���, ��Ϊ��Ŀ���Ϊ��
			fun String?.foo() : String  {
				return (this ?: "null") + "foo"
			}

			fun main() {
				var value:String? = "131"
				var result = value.foo()
				println(result)
			}

		* �� java ���� this ��Զ����Ϊ��
		* �� Kotlin ����,��չ�����е� this ����Ϊnull

		* ��չ����������Ϊ��̬������, this ���ǵ�һ����������, ���Կ���Ϊnull
	
	# ���Ͳ����ǿɿյ�
		* ��������������� ? ��ʾ����Ϊ null, ���ֱ�Ӵ��� null, �ڱ����ʱ��ͻ���쳣
		* ���Ƿ��ͷ�������, ���Ͳ������� ?, ���õ�ʱ�򴫵� null, ����������쳣
			fun <T> foo( t : T){
				println(t)
			}
			fun bar (value:String){
				println(value)
			}
			fun main() {
				foo(null)
				bar(null) // �����쳣
			}
		
		* Ҫʹ���Ϳ����ڱ���ʱ�ھ��ж� null ֵ�Ļ�, ��Ҫ����ָ�����͵�����

	# �� Java �򽻵��еĿ�ֵ����
		* Kotlin ����ʶ��һЩ Java �����ע��: javax.annotation
		* �����Զ���ת��Ϊ Kotlin �� null �ı��
			@Nullable	+	 type  =  type?
				
			@NotNull	+	 type  = type
		

		* ��Kotlint�з��� Java �Ķ���Ҫע�� null �ж�
		
		* Kotlin �еĿɿ�����, ������ Java �Ļ����������ͱ�ʾ
		* ��ζ������ʹ���˻����������͵Ŀɿհ汾,	���ͻᱻ����Ϊ��Ӧ�İ�װ����
		
		* ��д Java ������ʱ��, �������¶��巽�������Ƿ��ʷǿ�
		* ��д Kotlin ������ʱ��, ��������ඨ���һ��, ���෽�����������, ������������


	
	
	# �������
		* ��ʾ���Ͽ��Դ洢��Ԫ��
			ArrayList<Int?>
		
		* ��ʾ���Ͽ���Ϊnull, ���һ����Դ洢 null Ԫ��
			ArrayList<Int?>?
		
