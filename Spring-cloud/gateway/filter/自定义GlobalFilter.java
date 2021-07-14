

-------------------
�Զ���GlobalFilter
-------------------
	# GlobalFilter �ӿ�
		* org.springframework.cloud.gateway.filter.GlobalFilter
			public interface GlobalFilter {
				Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain);
			}    
	
		* ֻ��Ҫʵ������������ӵ�IOC����
	
	

-------------------
ǰ��Filter
-------------------
@Bean
public GlobalFilter customGlobalFilter() {
    return (exchange, chain) -> exchange.getPrincipal()
        .map(Principal::getName)
        .defaultIfEmpty("Default User")
        .map(userName -> {
          //adds header to proxied request
          exchange.getRequest().mutate().header("CUSTOM-REQUEST-HEADER", userName).build();
          return exchange;
        })
        .flatMap(chain::filter);
}

-------------------
����Filter
-------------------
@Bean
public GlobalFilter customGlobalPostFilter() {
    return (exchange, chain) -> chain.filter(exchange)
        .then(Mono.just(exchange))
        .map(serverWebExchange -> {
          //adds header to response
          serverWebExchange.getResponse().getHeaders().set("CUSTOM-RESPONSE-HEADER",
              HttpStatus.OK.equals(serverWebExchange.getResponse().getStatusCode()) ? "It worked": "It did not work");
          return serverWebExchange;
        })
        .then();
}