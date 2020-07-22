--------------------------
Springboot-ԭ��				|
--------------------------


--------------------------
Springboot-�Զ�����ԭ��		|
--------------------------
	# ����spring-boot�Զ����õ�Դ�붼��:spring-boot-autoconfigure-1.5.1.RELEASE.jar ��
	# ����뿴��spring-boot�Զ�Ϊ����������Щ����,������������ʱ����Ӳ���,Ҳ������ȫ�������ļ�����Ӳ���
		java -jar xxx.jar --debug
		debug=true
		* Positive matches: ����Ϊ���õ��Զ�����
		* Negative matches:	����Ϊδ���õ��Զ�����
		
	0,@SpringBootApplication ע��	EnableAutoConfigurationImportSelector
	1,@EnableAutoConfiguration ע�� �� @AutoConfigurationPackage
		* @AutoConfigurationPackage �Զ����ð�

	2,@EnableAutoConfiguration �е� @Import(EnableAutoConfigurationImportSelector.class) ע��
		* 2.0���Ѿ��޸�:AutoConfigurationImportSelector
	3,EnableAutoConfigurationImportSelector ��
		# ʹ�� SpringFactoriesLoader.loadFactoryNames(Class<?> factoryClass, ClassLoader classLoader);������ɨ����� META-INF/spring.factories ��jar��
			* public static List<String> loadFactoryNames(Class<?> factoryClass, ClassLoader classLoader)
			* spring-boot-autoconfigure-1.5.1.RELEASE.jar �о��� META-INF/spring.factories
		# META-INF/spring.factories ����������һЩ�Զ�������
			* ������N����������,�����Զ�������,�¼������ȵ�....

	
	4,����һ�� XxxxAutoConfiguration ��,һ�㶼��������ע��,�� org.springframework.boot.autoconfigure.condition ����
		@ConditionalOnBean
			* ��������ָ����bean��������
		@ConditionalOnClass
			* ������·������ָ�����������
		@ConditionalOnCloudPlatform
		@ConditionalOnExpression
			* ����SpEL���ʽ��Ϊ�ж�����
		@ConditionalOnJava
			* ����JVM�汾��Ϊ�ж�����
		@ConditionalOnJndi
			* ��JNDI���ڵ������²���ָ����λ��
		@ConditionalOnMissingBean
			* ����������û��ָ��Bean�������
		@ConditionalOnMissingClass
			* ����·����û��ָ������������
		@ConditionalOnNotWebApplication
			* ��ǰ��Ŀ����Web��Ŀ��������
		@ConditionalOnProperty
			* ָ���������Ƿ���ָ����ֵ
		@ConditionalOnResource
			* ��·���Ƿ���ָ����ֵ
		@ConditionalOnSingleCandidate
			* ��ָ���� Bean ��������ֻ��һ��,������Ȼ�ж��,����ָ����ѡ��Bean
		@ConditionalOnWebApplication
			* ��ǰ��Ŀ��WEB��Ŀ��������

		* ���ע�ⶼ������� @Conditional(spring) Ԫע��,ֻ��ʹ���˲�ͬ������
	
--------------------------
Springboot-�Զ���AutoConfiguration |
--------------------------
	1,׼������Bean,���Ҷ�ȡproperties�ļ�
		* һ�㶼����: xxxProperties ����
		* ����ǵ����������ģ��(maven),������Ҫ����spring boot�� autoconfigure ģ��:

			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-autoconfigure</artifactId>
				<version>XXXXX</version>
				<scope>compile</scope>
			</dependency>

			* ���ʵ��,ֱ�Ӽ̳�:spring-boot-starters
				<parent>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starters</artifactId>
					<version>2.0.2.RELEASE</version>
				</parent>
				
				<dependencies>
					<dependency>
						<groupId>org.springframework.boot</groupId>
						<artifactId>spring-boot-autoconfigure</artifactId>
						<scope>compile</scope>
					</dependency>
				</dependencies>

	2,���� XxxxAutoConfiguration ��,��ͨ�������һЩע��������Ƿ�Ҫע��һЩ���
		* ��ʶ������һ�������� @Configuration
		* �� ������ xxxProperties ���뵽IOC EnableConfigurationProperties(xxxProperties.class) ,�Ϳ�����ҳ���� @Autowired ע��ʹ��
		* ĳ��ע�� @Bean �ķ�����ȥʹ��ע������ж�(�������һ���˾���)
		* �ѷ��ص�Beanע�뵽IOC
			
		
	3,�� src/main/resource Ŀ¼�´����ļ��к��ļ�
		META-INF/spring.factories


		org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
		com...xxxx,\
		com...x...,\
		
		* ����Զ��ŷָ�
		
		org.springframework.context.ApplicationContextInitializer
			* ���� ApplicationContextInitializer ��ʵ��,�ڳ�ʼ����ɺ��ȡ��ApplicatioContext


		
		org.springframework.boot.SpringApplicationRunListener
			* ���� SpringApplicationRunListener ��ʵ��,�����¼�


--------------------------
Springboot-�Զ��������ʵ��|
--------------------------
	# һ�㶨�������Ƚ���Ҫ�����
		xxx-starter
		xxx-autoconfigure
	
	# starter ֻ��һ���յ�maven����,û���κεĴ���,����
		* �� starter����jar�г���maven��pom����,������һ���ļ�: META-INF/spring.providers
		* ���ļ�ֻ����������

		provides: xxx-autoconfigure, beetl-core
	
	# autoconfigure �Ǻ��ĵ��Զ�װ�乤��
		

--------------------------
Ԫ����					  |
--------------------------
	# SpringBoot��starter(jar)����Ԫ�����ļ�
		* Ԫ�����ļ��ṩ����֧�ֵ��������Ե���ϸ��Ϣ
		* ��Щ�ļ�ּ���� IDE ������Աʹ�� application.properties �� application.yml �ļ����û�һ���ṩ�����İ�����"�������"

		* ͨ�������˵,�����������Ϊ����idea���Զ�ȡ��,�Ӷ��� yml/properties �༭��ʱ��,���Ը�����ʾ
	
	# �ο��ĵ�
		https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html

	# Ԫ�����ļ���·��
		META-INF/spring-configuration-metadata.json

		* ����һ��json�ļ�
	
		
	# groups ����
	# properties ����
	# hints ����

