------------------------
CorsHandler
------------------------
	# ����handler�� interface CorsHandler extends Handler<RoutingContext>
	
	static CorsHandler create()
	static CorsHandler create(String allowedOriginPattern) 

	CorsHandler addOrigin(String origin);
	CorsHandler addOrigins(List<String> origins);
	CorsHandler allowedMethod(HttpMethod method);
	CorsHandler allowedMethods(Set<HttpMethod> methods);
	CorsHandler allowedHeader(String headerName);
	CorsHandler allowedHeaders(Set<String> headerNames);
	CorsHandler exposedHeader(String headerName);
	CorsHandler exposedHeaders(Set<String> headerNames);
	CorsHandler allowCredentials(boolean allow);
	CorsHandler maxAgeSeconds(int maxAgeSeconds);


	# �Զ���ıȽϺ�ʹ
		.handler(ctx -> {
			String origin = ctx.request().getHeader(HttpHeaders.ORIGIN);
			if (!StringUtil.isNullOrEmpty(origin)) {
				// �����Դվ
				ctx.response().headers().set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
				// ����Я��������ͷ
				String accessControlRequestHeaders = ctx.request().getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
				if (!StringUtil.isNullOrEmpty(accessControlRequestHeaders)) {
					ctx.response().headers().set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, accessControlRequestHeaders);
				}
				// ������ʵ�����ͷ
				ctx.response().headers().set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
				// ����Я��ƾ֤
				ctx.response().headers().set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
				// �������󷽷�
				ctx.response().headers().set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, OPTIONS, DELETE, PATCH");
				if (ctx.request().method() == HttpMethod.OPTIONS) {
					ctx.response().setStatusCode(204).end();
					return;
				}
			}
			ctx.next();
		})