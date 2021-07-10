--------------------
��·��
--------------------
	# ֧�ֶ�·�����ƣ��ڳ����쳣��ʱ��ִ�еĽ��������߼�
	# ��·���ƿ�����ǰ�᣺
		1, classpath��ѡ��·����ʵ��(ʵ���кܶ�)������: 
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
			</dependency>
		
		2, ���迪����·��
			feign.circuitbreaker.enabled=true

	# ͨ�� @FeignClient ע��� fallback ���ԣ�ָ�������߼�ʵ��
		* OrderClient
			import org.springframework.cloud.openfeign.FeignClient;
			import org.springframework.web.bind.annotation.RequestMapping;
			import org.springframework.web.bind.annotation.RequestMethod;

			@FeignClient(name = "order-service", fallback = OrderClientFallback.class) // fallback ָ��ʵ����
			public interface OrderClient {

				@RequestMapping(value = "/demo", method = RequestMethod.GET)
				String demo();
			}


		* OrderClientFallback
			import org.springframework.stereotype.Component;

			@Component		// ��Ҫ��ӵ�ioc
			public class OrderClientFallback implements OrderClient {

				// ��������
				@Override
				public String demo() {
					// Ҳ���Բ�ȡ�׳��쳣�Ĳ��ԣ��õ�ǰ������쳣�������ȥ����
					return "Զ�̵����쳣��";
				}
			}
	
		
		* �ͻ��������쳣��ʱ�򣬾ͻ᷵��: Զ�̵����쳣��
		
	
	# �����Ҫ�����쳣ԭ����ô����ʵ��: public interface FallbackFactory<T>  �ӿ�
		* FallbackFactory
			public interface FallbackFactory<T> {
				T create(Throwable cause);
				final class Default<T> implements FallbackFactory<T> {
					final Log logger;
					final T constant;
					public Default(T constant) {
						this(constant, LogFactory.getLog(Default.class));
					}
					Default(T constant, Log logger) {
						this.constant = checkNotNull(constant, "fallback");
						this.logger = checkNotNull(logger, "logger");
					}
					@Override
					public T create(Throwable cause) {
						if (logger.isTraceEnabled()) {
							logger.trace("fallback due to: " + cause.getMessage(), cause);
						}
						return constant;
					}
					@Override
					public String toString() {
						return constant.toString();
					}
				}
			}
		
		
		* OrderClient
			import org.springframework.cloud.openfeign.FeignClient;
			import org.springframework.web.bind.annotation.RequestMapping;
			import org.springframework.web.bind.annotation.RequestMethod;

			@FeignClient(name = "order-service", fallbackFactory = OrderClientFallback.class)		// ͨ�� fallbackFactory ָ��������
			public interface OrderClient {

				@RequestMapping(value = "/demo", method = RequestMethod.GET)
				String demo();
			}


		
		* OrderClientFallback
			import org.springframework.cloud.openfeign.FallbackFactory;
			import org.springframework.stereotype.Component;

			@Component  // ����IOC
			public class OrderClientFallback implements FallbackFactory<OrderClient> {

				// �����쳣ʱ���ã�����Feign�ӿڵĵ�ʵ�֣����Զ����ö�Ӧ��ʵ�ַ���

				@Override
				public OrderClient create(Throwable cause) { // cause �����׳����쳣
					return new OrderClient() {
						@Override
						public String demo() {
							return "�������쳣��" + cause.getMessage();
						}
					};
				}
			}
			
			�ͻ��˵��÷����쳣�� �������쳣��[503] during [GET] to [http://order-service/demo] [OrderClient#demo()]: [Load balancer does not contain an instance for the service order-service]
		