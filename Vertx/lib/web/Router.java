----------------------------
Router
----------------------------
	# ·�ɽӿڣ� interface Router extends Handler<HttpServerRequest>


	static Router router(Vertx vertx)
		* ����Routerʵ��

	Route route();
		* ����һ��·�ɣ�Ĭ�����ᴦ����������

	Route route(HttpMethod method, String path);
	Route route(String path);
	Route routeWithRegex(HttpMethod method, String regex);
	Route routeWithRegex(String regex);

	Route get();
	Route get(String path);
	Route getWithRegex(String regex);

	Route head();
	Route head(String path);
	Route headWithRegex(String regex);

	Route options();
	Route options(String path);
	Route optionsWithRegex(String regex);

	Route put();
	Route put(String path);
	Route putWithRegex(String regex);

	Route post();
	Route post(String path);
	Route postWithRegex(String regex);

	Route delete();
	Route delete(String path);
	Route deleteWithRegex(String regex);

	Route trace();
	Route trace(String path);
	Route traceWithRegex(String regex);

	Route connect();
	Route connect(String path);
	Route connectWithRegex(String regex);

	Route patch();
	Route patch(String path);
	Route patchWithRegex(String regex);

	List<Route> getRoutes();
	Router clear();
	Route mountSubRouter(String mountPoint, Router subRouter);
		* ����·����

	Router errorHandler(int statusCode, Handler<RoutingContext> errorHandler);
		* ����ָ��״̬����쳣������
			Router maiRouter = Router.router(vertx)
				.errorHandler(404, ctx -> {
					ctx.response().setStatusCode(404);
					ctx.json(new JsonObject().put("message", "ɶҲû"));
				});
		

	void handleContext(RoutingContext context);
	void handleFailure(RoutingContext context);
	Router modifiedHandler(Handler<Router> handler);

	Router allowForward(AllowForwardHeaders allowForwardHeaders);
		* ���ý�������ͷ(X-Forward)�Ĺ���
			NONE			// Ĭ��
			FORWARD			// ֻ���� Forward 
			X_FORWARD		// ֻ���� X-Forward
			ALL				// ������������ Forward ���ȼ��Ƚϸ�
			