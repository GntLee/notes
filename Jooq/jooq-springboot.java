------------------------------
springboot
------------------------------
	# starter
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jooq</artifactId>
		</dependency>
	
	# ������(JooqProperties)
		spring.jooq.sql-dialect=MySQLDialect
			* ���÷��ԣ�Ĭ�ϣ�Auto-detected by default.
		
	
	# ����Ҫ�ĵط�ע��
		@Autowired
		private DSLContext dslContext;
	
	