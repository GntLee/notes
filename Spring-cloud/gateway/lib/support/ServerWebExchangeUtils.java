-----------------------------
ServerWebExchangeUtils
-----------------------------
	# ServerWebExchange ������
		final class ServerWebExchangeUtils

		* ��װ�˴������������ƣ��Լ���ݲ�����ʽ

-----------------------------
static
-----------------------------
	public static final String PRESERVE_HOST_HEADER_ATTRIBUTE = qualify("preserveHostHeader");
		* �Ƿ񱣴�Host���ԣ�ֵ�ǲ���ֵ���ͣ�д��λ���� PreserveHostHeaderGatewayFilterFactory ʹ�õ�λ���� NettyRoutingFilter
		* �������������Ϊtrue��HTTP����ͷ�е�Host���Ի�д���ײ�Reactor-Netty������Header�����С�

	public static final String URI_TEMPLATE_VARIABLES_ATTRIBUTE = qualify("uriTemplateVariables");	
		* PathRoutePredicateFactory����·���������֮�󣬰ѽ�����ɺ��ռλ��KEY-·��Pathӳ������ServerWebExchange��������
		* KEY���� URI_TEMPLATE_VARIABLES_ATTRIBUTE

	public static final String CLIENT_RESPONSE_ATTR = qualify("gatewayClientResponse");
		* ����ײ�Reactor-Netty����Ӧ���������� reactor.netty.http.client.HttpClientResponse��

	public static final String CLIENT_RESPONSE_CONN_ATTR = qualify("gatewayClientResponseConnection");
		* ����ײ�Reactor-Netty�����Ӷ��������� reactor.netty.Connection��

	public static final String CLIENT_RESPONSE_HEADER_NAMES = qualify("gatewayClientResponseHeaderNames");
		* ����ײ�Reactor-Netty����ӦHeader�����Ƽ��ϡ�

	public static final String GATEWAY_ROUTE_ATTR = qualify("gatewayRoute");
		* ���ڴ��RoutePredicateHandlerMapping��ƥ������ľ����·��(org.springframework.cloud.gateway.route.Route)ʵ��
		* ͨ�����·��ʵ�����Ե�֪��ǰ�����·�ɵ������ĸ�����

	public static final String GATEWAY_REQUEST_URL_ATTR = qualify("gatewayRequestUrl");
		* java.net.URI ���͵�ʵ�������ʵ������ֱ��������߸��ؾ��⴦��֮����Ҫ�������η������ʵURI

	public static final String GATEWAY_ORIGINAL_REQUEST_URL_ATTR = qualify("gatewayOriginalRequestUrl");
		* java.net.URI���͵�ʵ������Ҫ��д����URI��ʱ�򣬱���ԭʼ������URI

	public static final String GATEWAY_HANDLER_MAPPER_ATTR = qualify("gatewayHandlerMapper");
		* ���浱ǰʹ�õ�HandlerMapping����ʵ�������ͼ��(һ�����ַ���"RoutePredicateHandlerMapping")

	public static final String GATEWAY_SCHEME_PREFIX_ATTR = qualify("gatewaySchemePrefix");
		* ȷ��Ŀ��·��URI���������schemeSpecificPart���ԣ��򱣴��URI��scheme�ڴ������У�·��URI�ᱻ���¹��죬�� RouteToRequestUrlFilter 

	public static final String GATEWAY_PREDICATE_ROUTE_ATTR = qualify("gatewayPredicateRouteAttr");
		* ���ڴ��RoutePredicateHandlerMapping��ƥ������ľ����·��(org.springframework.cloud.gateway.route.Route)ʵ����ID

	public static final String WEIGHT_ATTR = qualify("routeWeight");
		* ʵ���Թ���(�˰汾������������ʽ�汾ʹ��)��ŷ���Ȩ��������ԣ���WeightCalculatorWebFilter

	public static final String ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR = "original_response_content_type";
		* �����ӦHeader�е�ContentType��ֵ

	public static final String CIRCUITBREAKER_EXECUTION_EXCEPTION_ATTR = qualify("circuitBreakerExecutionException");
		* Throwable��ʵ������ŵ��Ƕ�·��Hystrixִ���쳣ʱ����쳣ʵ������ HystrixGatewayFilterFactory

	public static final String GATEWAY_ALREADY_ROUTED_ATTR = qualify("gatewayAlreadyRouted");
		* ����ֵ�������ж��Ƿ��Ѿ�������·�ɣ��� NettyRoutingFilter

	public static final String GATEWAY_ALREADY_PREFIXED_ATTR = qualify("gatewayAlreadyPrefixed");
		* ����ֵ�������ж�����·���Ƿ������ǰ�ò��֣��� PrefixPathGatewayFilterFactory

	public static final String CACHED_SERVER_HTTP_REQUEST_DECORATOR_ATTR = "cachedServerHttpRequestDecorator";
	public static final String CACHED_REQUEST_BODY_ATTR = "cachedRequestBody";
	public static final String GATEWAY_LOADBALANCER_RESPONSE_ATTR = qualify("gatewayLoadBalancerResponse");

	public static void setAlreadyRouted(ServerWebExchange exchange)
	public static void removeAlreadyRouted(ServerWebExchange exchange)
	public static boolean isAlreadyRouted(ServerWebExchange exchange)
	public static boolean setResponseStatus(ServerWebExchange exchange, HttpStatus httpStatus) 
	public static void reset(ServerWebExchange exchange) 
	public static boolean setResponseStatus(ServerWebExchange exchange, HttpStatusHolder statusHolder)
	public static boolean containsEncodedParts(URI uri) 
	public static HttpStatus parse(String statusString) 
	public static void addOriginalRequestUrl(ServerWebExchange exchange, URI url)
	public static AsyncPredicate<ServerWebExchange> toAsyncPredicate(Predicate<? super ServerWebExchange> predicate)
	public static String expand(ServerWebExchange exchange, String template) 
	public static void putUriTemplateVariables(ServerWebExchange exchange, Map<String, String> uriVariables)
	public static Map<String, String> getUriTemplateVariables(ServerWebExchange exchange)
	public static <T> Mono<T> cacheRequestBodyAndRequest(ServerWebExchange exchange, Function<ServerHttpRequest, Mono<T>> function) 
	public static <T> Mono<T> cacheRequestBody(ServerWebExchange exchange, Function<ServerHttpRequest, Mono<T>> function)
