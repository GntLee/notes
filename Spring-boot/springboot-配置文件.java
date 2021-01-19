-------------------
�����ļ�
-------------------
	# �ĵ�
		https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config-files
	
	# ���������ļ���صĲ���
		spring.config.activate.on-cloud-platform
		spring.config.activate.on-profile
		spring.config.additional-location
		spring.config.import
		spring.config.location
		spring.config.name=application
		
		* spring.config.name �� spring.config.location ���������ȷ�����������Щ�ļ������뽫���Ƕ���Ϊ�������ԣ�ͨ����OS����������ϵͳ���Ի������в�����
			System.setProperty("spring.config.additional-location", "optional:file:D:\\config/");

	
	# active & include ָ����׺���ļ�
		spring.profiles.active 
			* ����ָ����׺�������ļ�
				spring.profiles.active=pro // �����ļ� ${spring.config.name}-${spring.profiles.active}.properties/yaml
			
		spring.profiles.include
			* ����һ�����߶����׺�������ļ�
				spring.profiles.include=mysql,redis	 // �����ļ� ${spring.config.name}-${spring.profiles.include}.properties/yaml
					
	
	# Profile Groups��һ���Լ���N��ָ����׺�������ļ�
		spring.profiles.group.prod=proddb,prodmq,prodmetrics


	# �������ݾɰ�(2.3)�����ü��ط�ʽ
		spring.config.use-legacy-processing=true
	
	# springboot�������������ļ�·��
		1. classpath:/
		2. classpath:/config/
		3. file:./
		4. file:./config/*/																		*/
		5. file:./config/

		* �����Ŀ¼�������£����µ����ø��������

		* Ĭ������Ϊ: application.properties/application.yaml
		* Ĭ�����ƿ���ͨ��: spring.config.name �����޸�


	# �����ⲿ�������ļ�
		spring.config.location
			* ����ָ����Ĭ�������ļ�

		spring.config.import
			* �µ��������ļ�������������ָ�����������Ϊ��������������λ�õ���������������
			* ��֧��Kubernetes�е�����������Ҫʹ��configtree:ǰ׺
				spring.config.import=optional:configtree:/etc/config/
			
			* ���Ҫ��ͬһ���ļ��е������������������ʹ��ͨ�����ݷ�ʽ
				spring.config.import=optional:configtree:/etc/config/*/																									<--*/
		
		* ����ж��������ʹ�ö��ŷָ�
			spring.config.location=optional:classpath:/default.properties,optional:classpath:/override.properties

		* �ļ�������� optional: ǰ׺����ʶ���ǿ�ѡ�ģ��������ļ������ڣ�ϵͳ�ͻ�����
			spring.config.location=optional:/etc/config/application.properties
		
		* ����뽫����ָ���������ļ���Ĭ��Ϊ��ѡ�ģ�����ͨ��������
			SpringApplication.setDefaultProperties("spring.config.on-location-not-found", "ignore"); // Ҳ���԰����ֵд�뵽��������
		
		* �ļ�����Ҫ����չ�� properties/yaml�����û�еĻ�����Ҫ������ָ��
			spring.config.import=file:/etc/config/myconfig[.yaml]
	
	# �����ļ��ļ���Ŀ¼

		* �����趨�����ļ�����Ŀ¼
			spring.config.location 
				spring.config.location=optional:classpath:/custom-config/,optional:file:./custom-config/
			
			* ������ã�Ҳ����ָ��һ��Ŀ¼�������滻Ĭ�ϵ������ļ�����·�����������Ӧ����Ŀ¼: / ��β
			* ���ָ���˶��λ�ã�������λ�ÿ��Ը���ǰ���λ�ã�����Ĭ�ϵ������ļ�·��������Ч��

	
		* ����µ������ļ�Ŀ¼
			spring.config.additional-location
				spring.config.additional-location=optional:classpath:/custom-config/,optional:file:./custom-config/

			* ����µ������ļ�����·����Ĭ�ϵ�·�����棬�������滻
		
		* fileĿ¼�����ö�֧��ͨ���(classpath��֧��)����ʾ������������Ŀ¼�������ļ�
			spring.config.location=optional:file:/config/*/																		*/
				/config/redis/application.properties
				/config/mysql/application.properties
	

	# ͬһ�������ļ������Բ��Ϊ�����ͬ���߼������ļ�
		* Yml�Ĳ�֣�ʹ�� --- 
			spring.application.name: MyApp
			---
			spring.config.activate.on-cloud-platform: kubernetes
			spring.application.name: MyCloudApp
	
		* properties�Ĳ�֣�ʹ�� #---
			spring.application.name=MyApp
			#---
			spring.config.activate.on-cloud-platform=kubernetes
			spring.application.name=MyCloudApp
	
		
		* ����߼����ã�����ͬ���Եģ�����ĻḲ��ǰ���




		* �߼��ļ������Եļ���
			spring.config.activate.on-profile
				* ��ǰ�߼����ã���spring.profiles.activeָ����ģʽ�²���Ч
				* ֧�ֹ�ϵ����
					spring.config.activate.on-profile=prod | staging

			spring.config.activate.on-cloud-platform
				* ����ƽ̨��⣬��ָ��CloudPlatform�²���Ч
	
		
	# Origin Chains
		* Origin �ӿڸ��£�ʹ����ȫ�µ� getParent() �����������Ϳ����ṩ�����Ĳ�����Դ������׼ȷ��ʾĳһ���������Դ��
		* ������ application.properties �����ļ���ʹ�� spring.config.import ������ڶ��������ļ��Ĳ������ӵڶ��������ļ����صĲ����� Origin ������һ��ָ��ԭʼ���������ĸ�����
	
		* ͨ��˵�����ǿ��Կ������������ﵼ�����ģ�����ͨ�� actuator/env ���� actuator/configprops �˵����鿴��֮��ص������Ϣ
	

	
	# �ܽ�
		spring.config.name							ָ�������ļ�������
		spring.config.location						���µ���һ�������ļ�����������ָ�������ļ�������·��
		spring.config.import						�����ⲿ�������ļ������߼���Kubernetes�е�������
		spring.config.additional-location			��������ļ�������·��
		spring.profiles.active						��������ָ�������������ļ�
		spring.profiles.include						һ���԰���������������ļ�
		spring.config.activate.on-profile			��ǰ�߼����ã�ֻ����ָ��������Ч��ʱ�򣬲Ż���Ч
		spring.config.activate.on-cloud-platform	��ǰ�߼����ã�ֻ����ָ����ƽ̨�²���Ч