--------------------------
GatewayFilter
--------------------------
	# ��Ҫʵ�ֽӿ�: org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory
		
		* ����ӿڳ��󷽷�ʵ����̫���ˣ�����̳г�����
			org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory

		* ���Ͳ�����һ��������
	
	# ���еĳ��󷽷�
		String NAME_KEY = "name";
		String VALUE_KEY = "value";

		GatewayFilter apply(String routeId, Consumer<C> consumer)
		GatewayFilter apply(Consumer<C> consumer)
			* ִ��Filter����

		Class<C> getConfigClass()
		C newConfig()
			* ��ȡ��������Ϣ���Լ�����������ʵ��

		String name()
			* ���ع���������

		ShortcutType shortcutType()
			* ö��
				DEFAULT
				GATHER_LIST
				GATHER_LIST_TAIL_FLAG
			
		List<String> shortcutFieldOrder()
		String shortcutFieldPrefix()
			* ������

		
	# ����
		1, ʵ��GatewayFilterFactory�ӿڻ��߼̳�AbstractGatewayFilterFactory��
		2, ��Ӧ������ע�ᵽSpring��������
		3, ·�������е� filters ������Ӷ�Ӧ GatewayFilter ���ã�ע��һ�£������������� GatewayFilterFactory#name() ������
	
	# ������
		* Ԥ�����Filter�����඼���ڲ�����ڵ�
	
	# ������
		* ���÷���
			ShortcutType shortcutType()
				* ö��
					DEFAULT
					GATHER_LIST
					GATHER_LIST_TAIL_FLAG
				
			List<String> shortcutFieldOrder()
			String shortcutFieldPrefix()
				* ������
	
	# ��������
		* ������ʹ�� GatewayFilterFactory ��β������: SomethingGatewayFilterFactory
		* ������Ϲ淶��������ô����Ҫ��д name() ��������ù��������ƾ���ǰ׺: Something

		* ������������淶����ͨ����д name() �������������������
		

--------------------------
Demo
--------------------------
	# DemoFilter
		import org.springframework.cloud.gateway.filter.GatewayFilter;
		import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
		import org.springframework.http.server.reactive.ServerHttpRequest;
		import org.springframework.stereotype.Component;

		@Component
		public class DemoFilter extends AbstractGatewayFilterFactory<DemoFilter.Config> {

			public DemoFilter(){
				// ͨ�����캯������������
				super(Config.class);
			}

			@Override
			public GatewayFilter apply(DemoFilter.Config config) {
				return (exchange, chain) -> {

					// ǰ��ִ��
					ServerHttpRequest request = exchange.getRequest().mutate().headers(httpHeaders -> {
						httpHeaders.set(config.getHeaderName(), config.getHeaderValue());
					}).build();

					return chain.filter(exchange.mutate().request(request).build());
					//  return chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable(() -> {
					   // ����ִ��
					// }));
				};
			}

			// Filter����
			@Override
			public String name() {
				return "Demo";
			}

			/**
			 * �����࣬���Կ���ͨ������argsע��
			 */
			public static class Config {
				private String headerName;
				private String headerValue;
				public String getHeaderName() {
					return headerName;
				}

				public void setHeaderName(String headerName) {
					this.headerName = headerName;
				}

				public String getHeaderValue() {
					return headerValue;
				}

				public void setHeaderValue(String headerValue) {
					this.headerValue = headerValue;
				}
			}
		}
	
	# ����
	      filters:
            - name: RequestSize
              args:
                maxSize: 10KB
            - name: Demo
              args:
                headerName: "foo-name"
                headerValue: "foo-value"