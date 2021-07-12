--------------------------
Filter
--------------------------
	# �ӿڶ���: GatewayFilter

		public interface GatewayFilter extends ShortcutConfigurable {
			String NAME_KEY = "name";
			String VALUE_KEY = "value";
			Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain);
		}


---------------------
GlobalFilter
---------------------
	# �ӿڶ���: GlobalFilter
		public interface GlobalFilter {
			Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain);
		}
		
	# GlobalFilter �Ĺ�����ʵ��GatewayFilter����ͬ�ģ�ֻ��GlobalFilter�������������е�·�����ã������ǰ���ָ����·�������ϡ�
	# ���GlobalFilter����ͨ�� @Orde r���� getOrder() ����ָ��ÿ�� GlobalFilter ��ִ��˳��
		* pre���͹��������orderֵԽС����ô����Ӧ����pre���������Ķ��㣬������֮ǰ����ִ��
		* post���͹��������orderֵԽС����ô����Ӧ����pre���������ĵײ㣬������֮������ִ��
	

	# �ڽ���GlobalFilter
		ForwardRoutingFilter		�ض���
		LoadBalancerClientFilter	���ؾ���
		NettyRoutingFilter			Netty��HTTP�ͻ��˵�·��
		NettyWriteResponseFilter	Netty��Ӧ����д����
		RouteToRequestUrlFilter		����·�����ø���URL
		WebsocketRoutingFilter		Websocket����ת��������
	
	# ͨ��Ϊ����·�ɶ���ӹ�����
		spring:
		  cloud:
			gateway:
			  default-filters:
			    - AddResponseHeader=X-Response-Default-Red, Default-Blue
			    - PrefixPath=/httpbin
		
