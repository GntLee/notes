------------------
JonsArray
------------------
	# JSON����
		final class JsonArray extends JsonElement implements Iterable<JsonElement> 
		
		* �ڲ�ά����һ�� ArrayList<JsonElement>() ���洢����

	# ���췽��
		JsonArray()
		JsonArray(int capacity)

	# ʵ������
		JsonElement get(int i)
			* ��ȡָ��λ�õ�Ԫ��
			* ���ܻ� IndexOutOfBoundsException
		
		
		Number getAsNumber()
		String getAsString()
		double getAsDouble()
		BigDecimal getAsBigDecimal()
		BigInteger getAsBigInteger()
		float getAsFloat()
		long getAsLong()
		int getAsInt()
		byte getAsByte()
		char getAsCharacter()
		short getAsShort()
		boolean getAsBoolean()
			* ���ת���������� JsonArray ��˵
			* ֻ���� size == 1 ������, ����г���ת��
				if (elements.size() == 1) {
					return elements.get(0).getAsXxxx();
				}
				throw new IllegalStateException();
		
