----------------------------
��׼������					|
----------------------------
	buildString()
		* ���ٵĽ���, StringBuilder, ������һ�� lambda ���ʽ
		* ��ʹ�� apply, �Ѿ�����һ��������: StringBuilder
			var result = buildString {
				for (i in 0..9){
					append(i) // �ڱ��ʽ�п��Ե��� StringBuilder �����з���
				}
			}

			println(result)
		
		* ��󷵻� StringBuilder.toString()
	

					