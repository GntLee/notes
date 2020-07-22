----------------------------
ö�ٴ���					|
----------------------------
	# Ĭ�ϵ�ö�ٴ�����ֱ�Ӱ�ö�ٵ��������л�Ϊ�ַ���

		
	

--------------------------------
��ö�ٶ���ת��Ϊ�Զ������������|
--------------------------------
	# ����Serialize��Deserialize
		import java.io.IOException;
		import java.lang.reflect.Type;
		import com.alibaba.fastjson.serializer.JSONSerializer;
		import io.javaweb.common.BaseEnum;
		public class EnumsSerialize implements com.alibaba.fastjson.serializer.ObjectSerializer {
			
			public EnumsSerialize() {
			}
			
			@Override
			public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)throws IOException {
				if(!(object instanceof BaseEnum)) {
					//�������һ�����,����Ҫ�� getValue() ����,���е�ö�ٶ�Ӧ��ʵ���������
					throw new RuntimeException(object.getClass().getName() + " δʵ�� io.javaweb.common.BaseEnum �ӿ�");
				}
				serializer.out.writeInt(((BaseEnum)object).getValue());
			}
		}

		import java.lang.reflect.Type;
		import com.alibaba.fastjson.parser.DefaultJSONParser;
		import com.alibaba.fastjson.parser.JSONToken;
		import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

		import io.javaweb.common.BaseEnum;


		public class EnumDeserialize implements ObjectDeserializer {

			@SuppressWarnings("unchecked")
			@Override
			public <T> T deserialze(DefaultJSONParser parser, Type type, java.lang.Object fieldName) {
				
				Integer intValue = parser.parseObject(int.class);

				String typeName = type.getTypeName();
				
				try {
					
					Class<?> clazz = Class.forName(typeName);
					
					if(!clazz.isEnum()) {
						throw new IllegalArgumentException(clazz.getName() + " ��ö��");
					}
					
					if(!BaseEnum.class.isAssignableFrom(clazz)) {
						throw new IllegalArgumentException(clazz.getName() + " δʵ�� io.javaweb.common.BaseEnum �ӿ�");
					}
					
					BaseEnum[] constants = (BaseEnum[])clazz.getEnumConstants();
					
					for(BaseEnum constant : constants) {
						if(constant.getValue().equals(intValue)) {
							return (T) constant;
						}
					}
					
					throw new IllegalArgumentException("value " + intValue + ",�� " + clazz.getName() + " ��δ����");
					
				} catch (ClassNotFoundException | IllegalArgumentException  | SecurityException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				
			}

			@Override
			public int getFastMatchToken() {
				return JSONToken.LITERAL_INT;
			}
		}

	



	# http ��Ϣת����������
		@Bean
		public HttpMessageConverters fastJsonHttpMessageConverter() {
			
			FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
			
			//ʵ������������
			SerializeConfig serializeConfig = new SerializeConfig();
			//ָ��ö��,�Լ�����Serialize����
			serializeConfig.put(BaseEntity.Status.class, new EnumsSerialize());
			
			FastJsonConfig fastJsonConfig = new FastJsonConfig();
			fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
			fastJsonConfig.setCharset(StandardCharsets.UTF_8);
			fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);

			//���õ�fastJsonConfig
			fastJsonConfig.setSerializeConfig(serializeConfig);

			fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
			fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
			
			return new HttpMessageConverters(fastJsonHttpMessageConverter);
		} 
	
	# ע��ʽ(���)
		* ��ö���ֶ�������ע��
			@JSONField(serializeUsing = EnumsSerialize.class,deserializeUsing = EnumDeserialize.class)


--------------------------------
���л�ö��Ϊordinal				|
--------------------------------
	# ȫ������
		JSON.DEFAULT_GENERATE_FEATURE &= ~SerializerFeature.WriteEnumUsingName.mask;
	
	# ��������
		// ���л�ö��Ϊ ordinal
		int serializerFeatures = JSON.DEFAULT_GENERATE_FEATURE & ~SerializerFeature.WriteEnumUsingName.mask;
		String text = JSON.toJSONString(object, serializerFeatures);

		// ���л�ö��Ϊ name
		int serializerFeatures = JSON.DEFAULT_GENERATE_FEATURE | SerializerFeature.WriteEnumUsingName.mask;
		String text = JSON.toJSONString(object, serializerFeatures);

--------------------------------
Enum����JavaBean���л�			|
--------------------------------
	# ��ö�����ʶע��
		@JSONType(serializeEnumAsJavaBean = true)
	
	# ��������
		//�������л�����
		SerializeConfig serializeConfig = new SerializeConfig();
		//ʹ�ø� api ��ָ��ö����.class,�ɱ��������������һ�ε����л���Ч
		serializeConfig.configEnumAsJavaBean(PreservationModel.Category.class);

		// Ҳ����ȫ�����ã�����Ĭ�ϵ����л�����Ч
		// SerializeConfig.globalInstance.configEnumAsJavaBean(OrderType.class);
		

		//����fastjson����
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializeConfig(serializeConfig);
		
		PreservationModel preservationModel = new PreservationModel();
		preservationModel.setCategory(PreservationModel.Category.BASIS);
		preservationModel.setId(1);
		
		//�����л�����ʱ��,����fastjsonConfig����serializeConfig
		System.out.println(JSON.toJSONString(preservationModel, serializeConfig));