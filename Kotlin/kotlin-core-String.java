-------------------------
String					 |
-------------------------
	# �ַ���ģ��
		* ������ ES6 ��ģ��
			var name = "KevinBlandy";
			var age = 25;
			println("$name's age is $age")
		
		* ʹ�� $ ���ʱ���,�������������,�޷�����,���׳��쳣
		* �����Ҫ��� $ ����, ��ô��Ҫʹ��ת���ַ�: "\$"
	
		* �ַ�����ģ��,����ʹ�ü򵥵ı��ʽ: ${���ʽ}
			var age = 25;
			println("${if (age > 25) "������" else "������"}");

			* �ڱ��ʽ��, ��������Ƕ��˫���ŵ��ַ������ʽ
		
		* ֧�ֶ�������Ե���,��Ҫʹ�� {} ����
			var user = User("KevinBlandy", 25);
			println("${user.name},${user.age}");
	

		* �����Ĵ���,��ʵ�Ǵ�����һ�� StringBuilder ����
	
	# �������ַ���
		* ������ js ����� `` �ַ���, python����� ''' ''''
		* ������ַ�����Ҫת��,������б��,�����԰����κ��ַ�, ��������
			val name : String = """
				Hello
					World
					"
			"""
			println(name)

		* �ر��ʺ�����������ʽ
		* ������������һЩasciiͼ
		* ����ʹ���ַ�����ģ��, ��Ϊ��֧��ת���ַ�,������Ҫ���: $ ���ŵĻ�,��Ҫʹ��: ${'$'}
			val name : String = """�Ҿ���Ϊ�����:${'$'}"""

-------------------------
String	- ʵ������		 |
-------------------------
	trimMargin(marginPrefix: String = "|"): String
		# ɾ���ַ���ǰ��Ŀո�,�Լ�ָ����ǰ׺
		# ����
			marginPrefix
				* ��Ҫ�Ϳո�һ��ɾ����ǰ׺,Ĭ��Ϊ "|" (ΪɶĬ��ֵ�����?)
	

	

	substringBefore(delimiter: String, missingDelimiterValue: String = this): String
	substringBefore(delimiter: Char, missingDelimiterValue: String = this): String
	substringBeforeLast(delimiter: String, missingDelimiterValue: String = this): String
	substringBeforeLast(delimiter: Char, missingDelimiterValue: String = this): String
		# ��ȡָ���ַ�/�ַ���֮ǰ���ַ���, ���ص��ַ���������ָ�����ַ�/�ַ���
			* substringBefore ��ȡ����һ�γ��ֵ�λ��
			* substringBeforeLast ��ȡ�����һ�γ��ֵ�λ��
		
		# ����
			missingDelimiterValue
				* ���ָ�����ַ���, �ַ�������,���ص�����
				* Ĭ��ֵΪ this , Ҳ��˵,���ָ�����ַ�, �ַ���������,Ĭ�Ϸ���ԭʼ�ַ���

	substringAfter(delimiter: String, missingDelimiterValue: String = this): String
	substringAfter(delimiter: Char, missingDelimiterValue: String = this): String
	substringAfterLast(delimiter: String, missingDelimiterValue: String = this): String
	substringAfterLast(delimiter: Char, missingDelimiterValue: String = this): String
		# ͬ��, ��ȡ����,ָ���ַ���֮����ַ���


	split(vararg delimiters: Char, ignoreCase: Boolean = false, limit: Int = 0): List<String>
	split(delimiter: String, ignoreCase: Boolean, limit: Int): List<String>
	split(regex: Regex, limit: Int = 0): List<String>
		# �����ַ���, ���������и��ַ���
		# ����: delimiters 
			* �������ö���ָ��ַ�

			val name : String = "1_2-3_4.5"
			val result = name.split("_", "-", ".")
			println(result) // [1, 2, 3, 4, 5]


	toRegex(): Rege
	toRegex(option: RegexOption): Regex
		# ת����ǰ�ַ���Ϊ Regex ����
		# ���� RegexOption(ö��)
			* ��ѡ��һЩ��������

			IGNORE_CASE
			MULTILINE
			LITERAL
			UNIX_LINES
			COMMENTS
			DOT_MATCHES_ALL
			CANON_EQ
	


			