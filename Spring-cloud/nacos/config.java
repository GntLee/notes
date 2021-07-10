---------------------
config
---------------------
	# ��Ϊ��������ʹ��

---------------------
ʹ�ò���
---------------------
	# �������
		<!-- https://mvnrepository.com/artifact/com.alibaba.cloud/spring-cloud-starter-alibaba-nacos-config -->
		<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
			<version>${spring-cloud-starter-alibaba-nacos.version}</version>
		</dependency>

	# �������
		# ��boostrap.yaml ָ��nacos��ַ
		spring.cloud.nacos.config.server-addr=127.0.0.1:8848
		# Ӧ�������Ǳ����
		spring.application.name=example

	# �ڴ�����ʹ������
		@RestController
		@RequestMapping("/config")
		@RefreshScope					// ʹ�� @RefreshScope ���������õĶ�̬ˢ��
		public class ConfigController {

			@Value("${useLocalCache:false}")		// ���ص�������
			private boolean useLocalCache;

			@RequestMapping("/get")
			public boolean get() {
				return useLocalCache;
			}
		}

---------------------
���ĵ�һЩ����
---------------------
	Data ID
		* ��ɹ�ʽ: ${prefix}-${spring.profiles.active}.${file-extension}

		* ֮������Ҫ���� spring.application.name, ����Ϊ���ǹ��� Nacos ���ù��� dataId�ֶε�һ����

		* prefix Ĭ��Ϊ spring.application.name ��ֵ, Ҳ����ͨ�������� spring.cloud.nacos.config.prefix ������
		* spring.profiles.active ��Ϊ��ǰ������Ӧ�� profile, ������Բο� Spring Boot�ĵ��� 
			* ע������ǿ�ѡ��: �� spring.profiles.active Ϊ��ʱ����Ӧ�����ӷ� - Ҳ�������ڣ�dataId ��ƴ�Ӹ�ʽ��� ${prefix}.${file-extension}
		* file-exetension Ϊ�������ݵ����ݸ�ʽ, ����ͨ�������� spring.cloud.nacos.config.file-extension ������, Ŀǰֻ֧�� properties �� yaml ����

			 
	Group
	