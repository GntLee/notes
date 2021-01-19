---------------------------
health					   |
---------------------------
	# app������״̬�ڵ�
	
	# ��ص���
		HealthAggregator
			|-OrderedHealthAggregator
		HealthContributorRegistry
				|-DefaultHealthContributorRegistry
		HealthContributor
		Status
	
	
	# Ԥ����� HealthIndicator 
		CassandraHealthIndicator
		CouchbaseHealthIndicator
		DiskSpaceHealthIndicator
		ElasticSearchRestHealthContributorAutoConfiguration
		HazelcastHealthIndicator
		InfluxDbHealthIndicator
		InfluxDbHealthIndicator
		JmsHealthIndicator
		MailHealthIndicator
		MongoHealthIndicator
		PingHealthIndicator
		RabbitHealthIndicator
		RedisHealthIndicator
		SolrHealthIndicator
		...
	
---------------------------
�Զ���HealthIndicator	   |
---------------------------
	# ʵ�ֽӿ�: HealthIndicator 
		* ʵ����������� HealthIndicator ��β, ����: FooHealthIndicator 
		* ��ͷ���ǽ����������:Foo

		import org.springframework.boot.actuate.health.Health;
		import org.springframework.boot.actuate.health.HealthIndicator;
		import org.springframework.stereotype.Component;

		import java.util.Random;


		@Component
		public class FooHealthIndicator implements HealthIndicator {
			
			@Override
			public Health health() {
				int status = code();
				if ((status % 2)  == 0){
					return Health.down().withDetail("error", status).build();
				}
				return Health.up().build();
			}

			// ���ģ��״̬��
			protected int code (){
				return new Random().nextInt();
			}
		}
	
	# Ҳ���Լ̳�:AbstractHealthIndicator
		* ���췽��
			AbstractHealthIndicator() 
			AbstractHealthIndicator(String healthCheckFailedMessage) 
			AbstractHealthIndicator(Function<Exception, String> healthCheckFailedMessage)

		* ��дһ�����󷽷�
			void doHealthCheck(Health.Builder builder)

		* �ڳ��󷽷�����ɽ�����Ŀ�ļ��
		* ͨ���׳��쳣�ķ�ʽ, ����Ӧ�쳣״̬
			return builder.down(ex);



	# Health
		Health.up().build();

		Health.down().withDetail("error", status).build();

		Health.outOfService().build();

		Health.status(Status.DOWN).build();
			* ���� Status ���󴴽�����״̬

		Health.status("DOWN").build();
			* �Զ������Ƶ�״̬

		Health.unknown().build();

	
	# Status
		* ά����һ�� String code ��һ�� String description
		* ��̬����
			Status UNKNOWN = new Status("UNKNOWN");
			Status UP = new Status("UP");
			Status DOWN = new Status("DOWN");
			Status OUT_OF_SERVICE = new Status("OUT_OF_SERVICE");��
		
		* ���캯��
			Status(String code){this(code, "");}
			Status(String code, String description)
		

---------------------------
������					   |
---------------------------
# һ������
management.health.defaults.enabled=false
	* �Ƿ�����Ĭ�ϵĽ���������

management.endpoint.health.show-details=always
	* �Ƿ���ʾapp״̬������

management.endpoint.health.show-components=
	* ö��ֵ: 
		never			������ʾ
		when-authorized	��ʾ����Ȩ�û�
		always			��ʾ�������û�

management.endpoint.health.roles=
management.endpoint.health.<name>.order=fatal,down,out-of-service,unknown,up
	* ����ָ����Ŀ״̬��������˳��

management.endpoint.health.<name>.http-mapping.<status>=503
	* ����ָ����Ŀ, ��ָ��״̬�µ�http״̬��

# ��Ⱥ����
management.endpoint.health.group.custom.include=db
management.endpoint.health.group.custom.show-details=when-authorized
management.endpoint.health.group.custom.roles=admin
management.endpoint.health.group.custom.status.order=fatal,up
management.endpoint.health.group.custom.status.http-mapping.fatal=500