---------------------------------
String
---------------------------------
	# n�ֶ��巽ʽ
		var str = 'Hello';
		String str  = "Hello";
		String str = """Hello""";
		String str = '''Hello''';
	
	# �ַ���ʹ�� + ƴ��
		String str = "Hello" + "World";
	
	# ʹ��ռλ��: ${expression}
		String name = "KevinBlandy";
		print("Hello ${name}"); //Hello KevinBlandy

		* ��jsһ��, ֧�ַ����ķ��ʺͼ򵥵ļ���
		* Ĭ�Ϸ��ʶ����: toString() ����
	
	# ����ԭʼ�ַ���
		String reg = r'^\d{1,15}$';
		String reg = '^\\d{1,15}\$'; // ���ͬ��

		* �� r ��ʶ���ַ���, ���������������Ų���Ҫת��

	
	# �ж��ַ����Ƿ����, ʹ�� == 
		print("Hello" == "Hello"); // true
	



---------------------------------
String - ����
---------------------------------
	