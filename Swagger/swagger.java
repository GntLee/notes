------------------------
swagger
------------------------
	# 官网
		https://swagger.io/
	
	
-----------------------------
swagger - springboot 整合
-----------------------------
	# springfox
		http://springfox.github.io/springfox/
		http://springfox.github.io/springfox/docs/current/

	
	# Maven
		<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>
	
	# springfox 3 已经更新，文档可能有变化
		* Spring Boot 支持 springfox-boot-starter 依赖性（零配置，自动配置支持）
	
	# 启用 swagger
		@EnableSwagger2
	
	# 配置 Docket
	
	
	# 标识注解
		* controoller
			@Api(tags = {"用户管理"})

		* 请求方法
			@ApiOperation("添加用户")
		
		* 请求参数
			@ApiParam("用户的昵称")
		
		* 响应对象
			@ApiModel("用户信息")
		
		* 响应对象的属性
			@ApiModelProperty("用户id")

	# 访问
		http://localhost/swagger-ui.html
		http://localhost/v2/api-docs
	


-----------------------------
swagger - springboot 整合
-----------------------------
	# controller 方法, 返回值如果是对象, 就会被 swagger 扫描到, 并且识别为 model

