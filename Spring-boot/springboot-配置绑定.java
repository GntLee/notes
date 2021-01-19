----------------------------
Spring-boot ���ð����	|
----------------------------

----------------------------
Spring-boot �򵥵İ�		|
----------------------------
	@Value(value="name")
	private String name;

	* ����˵,�ܼ�. @Value ֻ�ܰ���Щ������:application.properties �ļ��е�����


----------------------------
Spring-boot �󶨵�POJO		|
----------------------------
	# ��Ķ���
		@ConfigurationProperties(prefix="user")
		public class User{
			private String userName;
			private String age;
			private List<String> hobe = new ArrayList<String>();
			//ʡ��get/set
		}
	
	# �����ļ��Ķ���
		user.username=kevinblandy
		user.age=23
		user.hobe[0]=java
		user.hobe[1]=Linux
	
	# �Ѹ�����ӵ�IOC����
		* ������������� @Component  ע��

		* Ҳ��������������,ͨ��:@EnableConfigurationProperties({User.class}); ע������ӵ�IOC
		* @EnableConfigurationProperties ���Ҫ������ @Configuration ����
	
		* Spring Bootʹ�ÿ��ɵĹ������ڰ����Ե� @ConfigurationProperties 
		* �����õ���������bean����������Ҫ��ȷƥ�䡣
		* ����,context-path�󶨵�contextPath,PORT��port��
	
	# 2.2.1 �汾��, ��Ҫͨ�� @ConfigurationPropertiesScan ����, ��������Щ���µ� @EnableConfigurationProperties ��Ч
		@AliasFor("basePackages")
		String[] value() default {};

		@AliasFor("value")
		String[] basePackages() default {};

		Class<?>[] basePackageClasses() default {};
	
	# ֧�ִӹ��캯�����а󶨣��ر�����Щ�����˾Ͳ����޸ĵ����ԣ��������final��ֱ�ӱ�¶��ȥ
		* ������
			minio:
			  endpoint: https://192.168.21.22:9000
			  access-key: root
			  secret-key: root
			  bucket: bucket
			  gateway: ${minio.endpoint}/${minio.bucket}
		
		* ������
			package io.springboot.minio;
			import org.springframework.boot.context.properties.ConfigurationProperties;
			import org.springframework.boot.context.properties.ConstructorBinding;
		
			@ConfigurationProperties(prefix = "minio")	// ǰ׺
			@ConstructorBinding				// ���캯����
			public class MinioConfig {

				public final String endpoint;
				public final String accessKey;
				public final String secretKey;
				public final String bucket;
				public final String gateway;

				public MinioConfig(String endpoint, String accessKey, String secretKey, String bucket, String gateway) {
					super();
					this.endpoint = endpoint;
					this.accessKey = accessKey;
					this.secretKey = secretKey;
					this.bucket = bucket;
					this.gateway = gateway;
				}

				@Override
				public String toString() {
					return "MinioConfig [endpoint=" + endpoint + ", accessKey=" + accessKey + ", secretKey=" + secretKey
							+ ", bucket=" + bucket + ", gateway=" + gateway + "]";
				}
			}
		
		* ����ɨ�����Ҫ������Щ���µ�����
			@ConfigurationPropertiesScan(basePackages = { "io.springboot.minio" })