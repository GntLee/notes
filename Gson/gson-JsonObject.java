------------------
JsonObject
------------------
	# JSON����
		final class JsonObject extends JsonElement
		
		* �ڲ�ʹ����һ�� LinkedTreeMap
			final LinkedTreeMap<String, JsonElement> members = new LinkedTreeMap<String, JsonElement>();
	
	# ���캯��
		
	
	# ��Ա����
		int size()
			* �ж��ǲ��ǿն���

		boolean has(String memberName)
			* �ж������Ƿ����

		JsonElement get(String memberName) 
			* ����json�е�ָ������
			* ���Բ�����, ����null