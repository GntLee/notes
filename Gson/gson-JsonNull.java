---------------
JsonNull
---------------
	# ��������json��null����
		* null ����
			JsonObject jsonElement = JsonParser.parseString("{\"val\": null}").getAsJsonObject();
			System.out.println(jsonElement.get("val").isJsonNull()); // true

		* �� null �ַ���Ҳ��
			JsonElement jsonElement = JsonParser.parseString("null");
			System.out.println(jsonElement.getAsJsonNull()); // true
	
	# Null ����
		final class JsonNull extends JsonElement

	# �ṩΨһ�ľ�̬ʵ������
		public static final JsonNull INSTANCE = new JsonNull();
	