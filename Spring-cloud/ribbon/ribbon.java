----------------------------
ribbon						|
----------------------------
	# Spring Cloud Ribbon �ǻ���Netflix Ribbonʵ�ֵ�һ��'�ͻ���,���ؾ���'����
	# Ribbon��netflix�����Ŀ�Դ��Ŀ,��Ҫ�ṩ�ͻ��˵�������ؾ����㷨
	# LB(Load Balance)��΢������߷ֲ�ʽ��Ⱥ�о����õ�һ��Ӧ��
	# �����ĸ��ؾ���Nginx,LVS,Ӳ��F5
	
	# ���ؾ��������
		* Ӳ��LB
			* F5֮���,����
		* ������LB
			* ��LB�߼����ɵ����ѷ�,���ѷ���ע�����Ļ�ȡ�����ַ,�ٴӵ�ַ��ѡ��һ�����е���
	
	# ribbon�ڹ���ʱ��Ϊ����
		1,��ѡ��EurekaServer,��ѡ��ͬһ�����ڸ��ؽ��ٵ�EurekaServer
		2,�����û�ָ���Ĳ���,��Serverȡ�������ṩ��(���)�ĵ�ַ��Ϣ�л�ȡһ������Զ�̵���
	
	# ribbon�ṩ�˶��ֲ���:��ѯ,���,������Ӧʱ���Ȩ

	# Ĭ���㷨��-��ѯ


----------------------------
����						|
----------------------------
	# ����
		 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
	
	# �������ؾ���
		@Configuration
		public class RestTemplateConfig{

			@Bean
			@LoadBalanced
			public RestTemplate restTemplate(){
				return new RestTemplate();
			}
		}

		* �� RestTemplate ע��Iocʱ,��� @LoadBalanced ע��
		* @LoadBalanced ��Springcloud����Ľӿ�ע��
		
----------------------------
LoadBalancerClient			|
----------------------------
	# ���ؾ�����
	# @LoadBalanced ������ RestTemplate�����,��ʹ�� LoadBalancerClient ��������

		public interface LoadBalancerClient extends ServiceInstanceChooser {
			
			/*
				������ServiceInstanceChooser�ӿ�
				�Ӹ��ؾ���������һ��ָ�����Ƶķ���ʵ��
			*/
			ServiceInstance choose(String serviceId);
			
			/*
				ʹ�ôӸ��ؾ���������������ʵ��ִ������
			*/
			<T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException;

			<T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException;

			
			/*
				Ϊϵͳ����һ�����ʵ�URI:	host:port
				���ǰѷ������Ʒ���Ϊ��:		host:port
			*/
			URI reconstructURI(ServiceInstance instance, URI original);
		}

----------------------------
���Ի���					|
----------------------------
	//TODO
