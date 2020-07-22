----------------------------
Druid						|
----------------------------
	1,�����ļ�
		# ָ������Դ����
		spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
		spring.datasource.driver-class-name=com.mysql.jdbc.Driver
		spring.datasource.url=jdbc:mysql://127.0.0.1:3306/springboot?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&serverTimezone=GMT%2b8
		spring.datasource.username=root
		spring.datasource.password=KevinBlandy_mysql
		
		# ���ӳ�����,������dataSource���Զ���Ч
		spring.datasource.initialSize=10
		spring.datasource.minIdle=5
		spring.datasource.maxActive=20
		spring.datasource.maxWait=60000
		spring.datasource.timeBetweenEvictionRunsMillis=60000
		spring.datasource.minEvictableIdleTimeMillis=300000
		spring.datasource.validationQuery=SELECT 1 FROM DUAL
		spring.datasource.testWhileIdle=true
		spring.datasource.testOnBorrow=false
		spring.datasource.testOnReturn=false
		spring.datasource.poolPreparedStatements=true
		spring.datasource.maxPoolPreparedStatementPerConnectionSize=20
		spring.datasource.filters=stat,wall,log4j
		spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000

	3,DataSourceConfiguration
		@Configuration
		public class DataSourceConfiguration {

			@Bean(initMethod = "init")
			@ConfigurationProperties(prefix = "spring.datasource")
			public DataSource druidDataSource() {
				DruidDataSource druidDataSource = new DruidDataSource();
				return druidDataSource;
			}
		}
	
	4,��������
		
		* �ǵ������߳����,@ServletComponentScan(basePackages = {"com.xx"}) ��ɨ�� web���
		* Ҳ����ʹ�� springboot��web���ע��beanȥע��
			ServletRegistrationBean
			FilterRegistrationBean
			ServletListenerRegistrationBean

		@WebServlet(urlPatterns = "/druid/*",initParams={@WebInitParam(name="loginUsername",value="KevinBlandy"), @WebInitParam(name="loginPassword",value="F8575532")})
		public class DruidStatViewServlet extends StatViewServlet {
			private static final long serialVersionUID = -1625661402500921518L;
		}
		
		@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")})
		public class DruidStatFilter extends WebStatFilter {
		}

----------------------------
starter						|
----------------------------
	# �ĵ�
		https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter

	# ����
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid-spring-boot-starter</artifactId>
			<version>1.1.10</version>
		</dependency>

	# ����:������:DruidStatProperties,DataSourceProperties
spring:
  datasource:
    #��spring�ṩ��ͨ��(����)����
    driver-class-name:
    url:
    username:
    password:
    
    # druid����
    druid:
      # ���ӳ�����(DruidDataSourceʵ������)
      initial-size:
      max-active:
      min-idle:
      max-wait:
      pool-prepared-statements:
      max-pool-prepared-statement-per-connection-size:
      max-open-prepared-statements:
      validation-query:
      validation-query-timeout:
      test-on-borrow:
      test-on-return:
      test-while-idle:
      time-between-eviction-runs-millis:
      min-evictable-idle-time-millis:
      max-evictable-idle-time-millis:
      filters:
      
      # Spring���AOP�����(x.y.z.service.*)��һ������ String[]
      aop-patterns:
      
      # ���Servlet
      stat-view-servlet:
        enabled:
        url-pattern:
        allow:
        deny:
        login-username:
        login-password:
        reset-enable:
      
      # ���Filter
      web-stat-filter:
        enabled:
        url-pattern:
        exclusions:
        session-stat-max-count:
        session-stat-enable:
        principal-session-name:
        principal-cookie-name:
        profile-enable:
        
    
    
