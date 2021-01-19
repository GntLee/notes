----------------------------
����spring - xml����		|
----------------------------
	<!-- ����Դ -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName">com.mysql.jdbc.Driver</property>
		<property name="url">jdbc:mysql://localhost:3306/testDB</property>
		<property name="username">user</property>
		<property name="password">pass</property>
	</bean>

	<!-- ���������ע������ -->
	<tx:annotation-driven transaction-manager="transactionManager"/>

	<!-- ע����������� -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>

	<!-- ע��Sql2o -->
	<bean id="sql2o" class="org.sql2o.Sql2o">
		<!-- ע�� dataSource -->
		<constructor-arg ref="dataSource"/>
	</bean>

----------------------------
����spring - ��������		|
----------------------------
	// ʵ�� TransactionManagementConfigurer �ӿ�
	@Configuration
	@EnableTransactionManagement
	public class DatabaseContext implements TransactionManagementConfigurer {

		// ע������Դ
	   @Bean
	   public DataSource dataSource() {
		  final BasicDataSource dataSource = new BasicDataSource();
		  dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		  dataSource.setUrl("jdbc:mysql://localhost:3306/testDB");
		  dataSource.setUsername("user");
		  dataSource.setPassword("pass");
	   }

		// ��д���󷽷�,ע�����������
	   @Bean
	   @Override
	   public PlatformTransactionManager annotationDrivenTransactionManager() {
		  return new DataSourceTransactionManager(this.dataSource());
	   }

		// ע��sql2o
	   @Bean
	   public Sql2o sql2o() {
		  return new Sql2o(this.dataSource());
	   }
	}

----------------------------
����spring - ע��ʹ��		|
----------------------------
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;

	@Repository
	public class UserDAO {

		@Autowired
		private Sql2o sql2o;

		public User getById(Integer userId){

			String sql = "SELECT * FROM `user` WHERE `id` = :id";

			Connection connection = sql2o.open();

			User user = connection.createQuery(sql)
					.addParameter("id",userId)
					.executeAndFetchFirst(User.class);

			return user;
		}
	}