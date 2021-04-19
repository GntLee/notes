------------------------
web
------------------------
	# �����������ã�dev
		* ����������VERTXWEB_ENVIRONMENT
		* ϵͳ���ԣ�vertxweb.environment

		* devģʽ��
			- ģ�����滺�汻����
			- ErrorHandler ����ʾ�쳣��ϸ��Ϣ
			- StaticHandler ��������ͷ
			- GraphQL�������߱�����

------------------------
·��
------------------------
	# ·�ɹ���
		* ����Խ�β�� / ����·�� /some/path ���� /some/path// ������Ҳ��ƥ���
		* ����ʹ��������ʽ��ʵ�֣����򵥵ķ�ʽ�������� Route ��·��ʱʹ��һ�� * ��Ϊ��β��
			Route route = router.route().path("/some/path/*"); // /some/path/foo.html �� /some/path/otherdir/blah.css ������ƥ��
		
		* ·��������/user/:id
			 ctx.pathParam("id");
			
			* ����������ĸ�����ֺ��»��߹���
			* ������չ������ַ���������-, $
				-Dio.vertx.web.route.param.extended-pattern=true
		
		* ����·����ֻҪ��·��ƥ�䣬�ͻ�ִ��handler��ִ��˳��ȡ����order


	# ������ģ��
		* ����·���ϵĶ��ִ����
			Router router = Router.router(vertx);
			router.get("/").handler(ctx -> {
				LOGGER.info("��һ��");
				ctx.next();
			}).handler(ctx -> {
				LOGGER.info("��һ��");
			}).handler(ctx -> {
				ctx.response().setStatusCode(200).end("success");
			});
		
		* ���·���ϵ�ִ����
			Router router = Router.router(vertx);

			Route api1 = router.route("/api");
			Route api2 = router.route("/api");
			Route api3 = router.route("/api");


			api1.method(HttpMethod.GET).handler(ctx -> {
			LOGGER.debug("����api1");
			ctx.next();
			});
			api2.method(HttpMethod.GET).handler(ctx -> {
			LOGGER.debug("����api2 - 1");
			ctx.next();
			}).handler(ctx -> {
			LOGGER.debug("����api2 - 2");
			ctx.next();
			});
			api3.method(HttpMethod.GET).respond(ctx -> {
			return Future.succeededFuture(new JsonObject().put("success", true));
			});
					
			
	# ·�ɷ���

		// ��·��
		Router maiRouter = Router.router(vertx);
		
		// ��·��
		Router userRouter = Router.router(vertx);
		userRouter.get("/:id").respond(ctx -> Future.succeededFuture(new JsonObject().put("id", ctx.pathParam("id"))));
		userRouter.get("/:id/foo").respond(ctx -> Future.succeededFuture(new JsonObject().put("id", ctx.pathParam("id"))));
		userRouter.get("/:id/bar").respond(ctx -> Future.succeededFuture(new JsonObject().put("id", ctx.pathParam("id"))));
		
		// ����
		maiRouter.mountSubRouter("/api/user", userRouter);


		/api/user/2/bar
		/api/user/2/foo
		/api/user/2
		