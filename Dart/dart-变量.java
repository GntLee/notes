----------------------
��������
----------------------
	# �����Զ��ƶ�, ʹ�� var �ؼ���
		var number = 15;
		var name = "KevinBlandy";
	
	# �ֶ�������������
		int number = 15;
		String name = "KevinBlandy";

	# �����ٴθ�ֵ�������͵����ݻ��쳣
		String name = "KevinBlandy";
		name = 27; //  Error: A value of type 'int' can't be assigned to a variable of type 'String'.

		var number = 15;
		number = "Hello"; // Error: A value of type 'String' can't be assigned to a variable of type 'int'.
	
	# ��̬���������͵ı���, ʹ�� dynamic  �ؼ���
		dynamic obj = null;
		obj = "123456";
		obj = 15;
		print(obj);

		* dynamic �е��������java�е� Object, ���԰��������ݶ���ֵ����(Dart��, ����Զ���)
	

----------------------
����
----------------------
	# const ����
		* const �Ǳ���ʱ�ĳ���, �ڱ����ʱ�����Ҫȷ��ֵ
		* const ���������������Ҫ��ʼ��ֵ
			const NAME = "name";
		
		* const �ĳ�ʼ��ֵ, ������һ�� '�������ʽ'
			const now = new DateTime.now(); // New expression is not a constant expression.


	# final ����
		* ����ʱ����, �����Գ�ʼ��(������), �ڵ�һ��ʹ�õ�ʱ��ų�ʼ��ֵ
	
		
	
	# ʵ������������ final, �������� const


			
		
	
		