--------------------------
TemplateHandler 
--------------------------
	# ģ�����洦������ interface TemplateHandler extends Handler<RoutingContext> 

	String DEFAULT_TEMPLATE_DIRECTORY = "templates";
		* Ĭ�ϵ�ģ������Ŀ¼

	String DEFAULT_CONTENT_TYPE = "text/html";
		* Ĭ�ϵ�ģ������COntentType

	String DEFAULT_INDEX_TEMPLATE = "index";
		* Ĭ�ϵ�ģ����������
	
	
	static TemplateHandler create(TemplateEngine engine) 
	static TemplateHandler create(TemplateEngine engine, String templateDirectory, String contentType)


	TemplateHandler setIndexTemplate(String indexTemplate);


	# Demo
		TemplateHandler handler = TemplateHandler.create(engine);

		router.get("/dynamic").handler(ctx -> {

		  ctx.put("request_path", ctx.request().path());
		  ctx.put("session_data", ctx.session().data());

		  ctx.next();
		});

		router.get("/dynamic/").handler(handler);