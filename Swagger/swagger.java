------------------------
swagger
------------------------
	# ����
		https://swagger.io/
	
	
-----------------------------
swagger - springboot ����
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
	
	# springfox 3 �Ѿ����£��ĵ������б仯
		* Spring Boot ֧�� springfox-boot-starter �����ԣ������ã��Զ�����֧�֣�
	
	# ���� swagger
		@EnableSwagger2
	
	# ���� Docket
	
	
	# ��ʶע��
		* controoller
			@Api(tags = {"�û�����"})

		* ���󷽷�
			@ApiOperation("����û�")
		
		* �������
			@ApiParam("�û����ǳ�")
		
		* ��Ӧ����
			@ApiModel("�û���Ϣ")
		
		* ��Ӧ���������
			@ApiModelProperty("�û�id")

	# ����
		http://localhost/swagger-ui.html
		http://localhost/v2/api-docs
	


-----------------------------
swagger - springboot ����
-----------------------------
	# controller ����, ����ֵ����Ƕ���, �ͻᱻ swagger ɨ�赽, ����ʶ��Ϊ model

