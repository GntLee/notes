----------------------------
�Զ�������ת����			|
----------------------------
	# ʵ��ת�����ӿ� Converter<T>
		T convert(Object val) throws ConverterException;
			* �� sql ����ת��Ϊ ʵ������

		Object toDatabaseParam(T val);
			* ��ʵ������ת��Ϊ sql ����
	
	# ʵ�� ConvertersProvider �ӿ� 
		void fill(Map<Class<?>,Converter<?>> mapToFill);
			* ��д�÷���,ͨ������,��Ӷ��: Converter ��ʵ��
	
	# �� src/main/resources Ŀ¼�´����ļ�
		META-INF/services/org.sql2o.converters.ConvertersProvider
	
	# �ڸ��ļ���д�� ConvertersProvider �ӿڵ�ʵ��ȫ·��,ʹ�ûس���β
	
		

----------------------------
demo						|
----------------------------
	# ʵ�� Converter
		import org.sql2o.converters.Converter;

		import java.sql.Timestamp;
		import java.time.Instant;
		import java.time.LocalDateTime;
		import java.time.ZoneOffset;

		public class LocalDateTimeConverter implements Converter<LocalDateTime> {
			@Override
			public LocalDateTime convert(final Object val)  {
				if(val != null ){
					if(val instanceof Timestamp){
						return LocalDateTime.ofInstant(Instant.ofEpochMilli(((Timestamp)val).getTime()), ZoneOffset.UTC);
					}
				}
				return null;
			}

			@Override
			public Object toDatabaseParam(final LocalDateTime val) {
				if(val != null){
					return new Timestamp(val.toInstant(ZoneOffset.UTC).toEpochMilli());
				}
				return null;
			}
		}

	# ʵ�� ConvertersProvider
		import org.sql2o.converters.Converter;
		import org.sql2o.converters.ConvertersProvider;

		import java.time.LocalDateTime;
		import java.util.Map;

		public class Sql2oConvertersProvider implements ConvertersProvider {
			@Override
			public void fill(Map<Class<?>, Converter<?>> mapToFill) {
				mapToFill.put(LocalDateTime.class, new LocalDateTimeConverter());
			}
		}
	
	# �� META-INF/services/org.sql2o.converters.ConvertersProvider �ļ������ʵ����ȫ·��(ע������пո�)
		io.javaweb.example.sql2o.converter.Sql2oConvertersProvider


----------------------------
�ڳ�������������ת����		|
----------------------------

	// ��������ӳ��Map
	Map<Class, Converter> converters = new HashMap<>();

	// ������ͺ�ת������ӳ��
	converters.put(LocalDateTime.class,new LocalDateTimeConverter());

	// ʵ���� NoQuirks
	Quirks quirks = new NoQuirks(converters);

	// ʵ����sql2o
	Sql2o sql2o = new Sql2o(dataSource,quirks);