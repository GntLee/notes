---------------------------------
Gson
---------------------------------
	# Maven
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-json</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
		</dependency>
	
	# ע������, ������Ĭ�ϵ�Jackson
		@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })

		* �Զ�װ����: GsonAutoConfiguration
	
	# ����(������: GsonProperties)
		spring.mvc.converters.preferred-json-mapper=gson
		spring.gson.date-format=
		spring.gson.disable-html-escaping=
		spring.gson.disable-inner-class-serialization=
		spring.gson.enable-complex-map-key-serialization=
		spring.gson.exclude-fields-without-expose-annotation=
		spring.gson.field-naming-policy=
		spring.gson.generate-non-executable-json=
		spring.gson.lenient=
		spring.gson.long-serialization-policy=
		spring.gson.pretty-printing=
		spring.gson.serialize-nulls=
		

		* �ο��ĵ�
			https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#json-properties
	
	# �Զ�������, ֻ��Ҫ�Լ����һ�� GsonBuilder ʵ����ioc����·
		@Configuration
		public class GsonConfiguration {
			
			private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			@Bean
			public GsonBuilder gsonBuilder(List<GsonBuilderCustomizer> customizers) { // customizers �Ƕ�ȡ���������ļ��й���gson������

				GsonBuilder builder = new GsonBuilder();
				// ����ͨ�������ļ����������
				customizers.forEach((c) -> c.customize(builder));
				
				/**
				 *�Զ�������
				 */
				
				// �������͵ĸ�ʽ��
				builder.registerTypeHierarchyAdapter(TemporalAccessor.class, new JsonSerializer<TemporalAccessor>() {
					@Override
					public JsonElement serialize(TemporalAccessor src, Type typeOfSrc, JsonSerializationContext context) {
						return new JsonPrimitive(DATE_TIME_FORMATTER.format(src));
					}
				});
			}
		}

	# SpringBoot ͨ�� GsonAutoConfiguration �����Զ���װ�� Gson
		* �����������ע��
			@Autowired
			Gson gson;
