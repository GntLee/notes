-----------------------
ö��
-----------------------
	# Ĭ�����л�/�����л�ֵʹ�� name() ����
	
	# ����ͨ�� @SerializedName ��ʶ��ö��ʵ����, �ı�ö�ٵ�ֵΪ�Զ����ֵ
		enum Gender {
			@SerializedName("Ů��")
			GIRL, 

			@SerializedName("�к�")
			BOY, 

			@SerializedName("δ֪")
			UNKNOWN
		}

		private Gnder gender = GIRL; // {"gender": "Ů��"}

	# Ҳ����ͨ��ע�� TypeAdapter ���Զ���ö�ٵ����л�����
		registerTypeHierarchyAdapter(Enum.class, new JsonSerializer<Enum<?>> () {
			@Override
			public JsonElement serialize(Enum<?> src, Type typeOfSrc, JsonSerializationContext context) {
				// ʹ�� ordinal ���л��ɻ�����������
				return new JsonPrimitive(src.ordinal());
			}
			
		})
		registerTypeHierarchyAdapter(Enum.class, new JsonDeserializer<Enum<?>> () {

			@Override
			public Enum<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				try {
					// �����ǻ�����������
					if (json.isJsonPrimitive()) {
						
						JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();
						
						// �����ȡ���е�ö��ʵ��
						Enum<?>[] enumConstants  = (Enum<?>[]) Class.forName(typeOfT.getTypeName()).getEnumConstants();
						
						if (jsonPrimitive.isNumber()) { // ����
							return enumConstants[jsonPrimitive.getAsInt()];
						} else if (jsonPrimitive.isString()) { // �ַ���
							String val = jsonPrimitive.getAsString();
							for (Enum<?> constant : enumConstants) {
								if (constant.name().equalsIgnoreCase(val)) {
									return constant;
								}
							}
						} 
					} 
				} catch (ClassNotFoundException | ArrayIndexOutOfBoundsException e) {
					
				}
				throw new IllegalArgumentException("bad param:" + json.getAsString());
			}
		})