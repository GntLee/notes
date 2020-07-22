----------------------------
Spring-boot 配置绑定详解	|
----------------------------

----------------------------
Spring-boot 简单的绑定		|
----------------------------
	@Value(value="name")
	private String name;

	* 不多说,很简单. @Value 只能绑定那些配置在:application.properties 文件中的属性


----------------------------
Spring-boot 绑定到POJO		|
----------------------------
	# 类的定义
		@ConfigurationProperties(prefix="user")
		public class User{
			private String userName;
			private String age;
			private List<String> hobe = new ArrayList<String>();
			//省略get/set
		}
	
	# 配置文件的定义
		user.username=kevinblandy
		user.age=23
		user.hobe[0]=java
		user.hobe[1]=Linux
	
	# 把该类添加到IOC容器
		* 可以在类上添加 @Component  注解

		* 也可以在其他类上,通过:@EnableConfigurationProperties({User.class}); 注解来添加到IOC
		* @EnableConfigurationProperties 如果要配置在 @Configuration 类上
	
		* Spring Boot使用宽松的规则用于绑定属性到 @ConfigurationProperties 
		* 以配置的属性名和bean属性名不需要精确匹配。
		* 比如,context-path绑定到contextPath,PORT绑定port。
	
	# 2.2.1 版本后, 需要通过 @ConfigurationPropertiesScan 配置, 来定义哪些包下的 @EnableConfigurationProperties 生效
		@AliasFor("basePackages")
		String[] value() default {};

		@AliasFor("value")
		String[] basePackages() default {};

		Class<?>[] basePackageClasses() default {};