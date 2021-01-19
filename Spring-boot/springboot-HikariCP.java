------------------------
HikariCP				|
------------------------
	# һ����Druid��������ӳ�
	# Github
		https://github.com/brettwooldridge/HikariCP
	# SpringBoot2Ĭ��ʹ��(����Ҫ�����starter)
	# Maven����
		<dependency>
			<groupId>com.zaxxer</groupId>
			<artifactId>HikariCP</artifactId>
		</dependency>
	
	# ������

spring:
  # ��������
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8&allowMultiQueries=true
    username: root
    password: root
  
    # ���ӳص���������
    hikari:
	  catalog: 
	  connection-timeout: 
	  validation-timeout: 
	  idle-timeout: 
	  leak-detection-threshold: 
	  max-lifetime: 
	  max-pool-size: 
	  min-idle: 
	  username: 
	  password: 
	  initialization-fail-timeout: 
	  connection-init-sql: 
	  connection-test-query: SELECT 1
	  data-source-class-name: 
	  data-source-jndi-name: 
	  driver-class-name: 
	  jdbc-url: 
	  pool-name: 
	  schema: 
	  transaction-isolation-name: 
	  is-auto-commit: 
	  is-read-only: 
	  is-isolate-internal-queries: 
	  is-register-mbeans: 
	  is-allow-pool-suspension: 
	  data-source: 
	  data-source-properties: 
	  thread-factory: 
  	  scheduled-executor: 
	  metrics-tracker-factory: 
  	  metric-registry: 
	  health-check-registry: 
      health-check-properties: 
	  sealed: 

	# �ٷ�֧�ֵ�������
		spring.datasource.hikari.allow-pool-suspension=
		spring.datasource.hikari.auto-commit=
		spring.datasource.hikari.catalog=
		spring.datasource.hikari.connection-init-sql=
		spring.datasource.hikari.connection-test-query=
		spring.datasource.hikari.connection-timeout=
		spring.datasource.hikari.data-source-class-name=
		spring.datasource.hikari.data-source-j-n-d-i=
		spring.datasource.hikari.data-source-properties=
		spring.datasource.hikari.driver-class-name=
		spring.datasource.hikari.exception-override-class-name=
		spring.datasource.hikari.health-check-properties=
		spring.datasource.hikari.health-check-registry=
		spring.datasource.hikari.idle-timeout=
		spring.datasource.hikari.initialization-fail-timeout=
		spring.datasource.hikari.isolate-internal-queries=
		spring.datasource.hikari.jdbc-url=
		spring.datasource.hikari.leak-detection-threshold=
		spring.datasource.hikari.login-timeout=
		spring.datasource.hikari.max-lifetime=
		spring.datasource.hikari.maximum-pool-size=
		spring.datasource.hikari.metric-registry=
		spring.datasource.hikari.metrics-tracker-factory=
		spring.datasource.hikari.minimum-idle=
		spring.datasource.hikari.password=
		spring.datasource.hikari.pool-name=
		spring.datasource.hikari.read-only=
		spring.datasource.hikari.register-mbeans=
		spring.datasource.hikari.scheduled-executor=
		spring.datasource.hikari.schema=
		spring.datasource.hikari.transaction-isolation=
		spring.datasource.hikari.username=
		spring.datasource.hikari.validation-timeout=

------------------------
��������				|
------------------------
@Bean
public DataSource dataSource() {
	HikariConfig config = new HikariConfig();
	config.setJdbcUrl(dataSourceUrl); //����Դ
	config.setUsername(user); //�û���
	config.setPassword(password); //����
	config.addDataSourceProperty("cachePrepStmts", "true"); //�Ƿ��Զ������ã�Ϊtrueʱ����������������Ч
	config.addDataSourceProperty("prepStmtCacheSize", "250"); //���ӳش�СĬ��25���ٷ��Ƽ�250-500
	config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //���������󳤶�Ĭ��256���ٷ��Ƽ�2048
	config.addDataSourceProperty("useServerPrepStmts", "true"); //�°汾MySQL֧�ַ�������׼���������ܹ��õ�������������
	config.addDataSourceProperty("useLocalSessionState", "true");
	config.addDataSourceProperty("useLocalTransactionState", "true");
	config.addDataSourceProperty("rewriteBatchedStatements", "true");
	config.addDataSourceProperty("cacheResultSetMetadata", "true");
	config.addDataSourceProperty("cacheServerConfiguration", "true");
	config.addDataSourceProperty("elideSetAutoCommits", "true");
	config.addDataSourceProperty("maintainTimeStats", "false");

	HikariDataSource ds = new HikariDataSource(config);
	return ds;
}