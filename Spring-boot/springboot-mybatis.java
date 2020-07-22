----------------------------
Spring boot MyBatis			|
----------------------------


----------------------------
Spring boot ��һ�ַ�ʽ		|
----------------------------
	# ʹ�ùٷ��ṩ��jar,��������
	1,��������
		
		<!-- spring boot mybatis -->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.2.0</version>
		</dependency>
		
		<!-- mybatis��ҳ��� -->
		<dependency>
			<groupId>com.github.miemiedev</groupId>
			<artifactId>mybatis-paginator</artifactId>
			<version>1.2.17</version>
		</dependency>
	
	2,�� @SpringBootApplication �������ע����ɨ�� mapper �ӿ�
		@MapperScan("com.xx");
			* ɨ��ָ�����µ� mapper �ӿ�
			* value				������һ������,����ɨ����
			* annotationClass	�������������ָ��ע�����
			* sqlSessionFactoryRef 
				* ������Щmapper��������ʱ��
				* ʹ��ָ�����Ƶ�IOC��SqlSessionFactoryʵ��
				* �������ڶ�����Դʵ�ֵ�ʱ��

			sqlSessionTemplateRef
				* ������Щmapper��������ʱ��
				* ʹ��ָ�����Ƶ�IOC��sqlSessionTemplateʵ��
				* �������ڶ�����Դʵ�ֵ�ʱ��

		@MapperScans
			* ��һ�����ע��,valueֵ�Ƕ�� @MapperScan
		

	3,��properties�����ò���
		mybatis.mapper-locations[0]=classpath*:com/tedi/**/*mapper.xml																
			* mapper�ļ���ַ,�����ж��,֧��ʹ��ͨ���
		mybatis.config-location=classpath:mybatis/mybatis-config.xml
			* mybatis�����ļ���ַ
		-----------------------------------------------------------
		mybatis.mapper-locations[0]=classpath:mapper/**/*-mapper.xml
		mybatis.mapper-locations[1]=classpath:mapper/**/*-mapper-ext.xml
		mybatis.config-location=classpath:mybatis/mybatis-config.xml

	
	

----------------------------
Spring boot �ڶ��ַ�ʽ		|
----------------------------
	# ʹ��ԭʼ����spring�ķ�ʽ��������
	# spring ����ô���ϵ�,����ô����


----------------------------
mybatis ������Դ			|
----------------------------
	# ��һ������Դ��mybatis����
		@Configuration
		// ָ�����µ�mapperʵ��,ʹ��test1SqlSessionTemplate(bean name)
		@MapperScan(basePackages = "com.neo.mapper.test1", sqlSessionTemplateRef  = "test1SqlSessionTemplate")
		public class DataSource1Config {
			
			// ʵ������һ������Դ
			@Bean(name = "test1DataSource")
			@ConfigurationProperties(prefix = "spring.datasource.test1")
			@Primary // ʹ���Զ�ע���ʱ��,���ڶ����ͬ����ʵ��,��δָ������,������ע��
			public DataSource testDataSource() {
				return DataSourceBuilder.create().build();
			}

			// ��������Դʵ������һ�� SqlSessionFactory
			@Bean(name = "test1SqlSessionFactory")
			@Primary // ʹ���Զ�ע���ʱ��,���ڶ����ͬ����ʵ��,��δָ������,������ע��
			public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {
				SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
				bean.setDataSource(dataSource);
				return bean.getObject();
			}
			
			// ��������Դʵ������һ�� DataSourceTransactionManager
			@Bean(name = "test1TransactionManager")
			@Primary // ʹ���Զ�ע���ʱ��,���ڶ����ͬ����ʵ��,��δָ������,������ע��
			public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
				return new DataSourceTransactionManager(dataSource);
			}

			// ����SqlSessionFactoryʵ������һ�� SqlSessionTemplate
			@Bean(name = "test1SqlSessionTemplate")
			@Primary
			public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
				return new SqlSessionTemplate(sqlSessionFactory);
			}

		}
	
	# �ڶ�������Դ��mybatis����
		@Configuration
		@MapperScan(basePackages = "com.neo.mapper.test2", sqlSessionTemplateRef  = "test2SqlSessionTemplate")
		public class DataSource2Config {

			@Bean(name = "test2DataSource")
			@ConfigurationProperties(prefix = "spring.datasource.test2")
			public DataSource testDataSource() {
				return DataSourceBuilder.create().build();
			}

			@Bean(name = "test2SqlSessionFactory")
			public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource) throws Exception {
				SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
				bean.setDataSource(dataSource);
				return bean.getObject();
			}

			@Bean(name = "test2TransactionManager")
			public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
				return new DataSourceTransactionManager(dataSource);
			}

			@Bean(name = "test2SqlSessionTemplate")
			public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
				return new SqlSessionTemplate(sqlSessionFactory);
			}

		}

	
	# ע��ʹ��
		@Autowired Mapper1
			
		@Autowired Mapper2
			
