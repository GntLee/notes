----------------------------
�쳣����					|
----------------------------
	# �׳��쳣,��Javaһ��
		throw Exception("exception....")
	
	# ��֧�ṹ����һ��
		try {

		}catch (e : Exception){

		}finally {
			
		}
	
	# Kotlin ���������ܼ��쳣��δ�ܼ��쳣
		* ����ָ�������׳����쳣 , ���ҿ��Դ���Ҳ���Բ������쳣
	
	# try catch Ҳ�ǿ����з���ֵ��
		* ���������һ�����ʽ��Ϊ����ֵ
		* ��ɶ���˼�밡?

		var result = try {
			5
		}catch (e: Exception){
			4
		}

		fun mustFour(value:Int) = try {
				if (value == 4){
					4
				}else{
					throw Exception()
				}
			}catch (e:Exception){ // �쳣��,�򷵻� 1
				1
			}finally {
				-1				// ���catch����黹���쳣,�򷵻� -1
		}

	
