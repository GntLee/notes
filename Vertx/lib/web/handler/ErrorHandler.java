--------------------------
ErrorHandler
--------------------------
	# �쳣�������ӿڣ� interface ErrorHandler extends Handler<RoutingContext> 
		* ���Զ���Ⱦ�쳣ҳ��

	
	String DEFAULT_ERROR_HANDLER_TEMPLATE = "META-INF/vertx/web/vertx-web-error.html";
		* Ĭ�ϵ��쳣ҳ�棬Ĭ���� vertx-web ģ����

	static ErrorHandler create(Vertx vertx) 
	static ErrorHandler create(Vertx vertx, String errorTemplateName, boolean displayExceptionDetails)
	static ErrorHandler create(Vertx vertx, boolean displayExceptionDetails)
	static ErrorHandler create(Vertx vertx, String errorTemplateName)
		* �����쳣������
			errorTemplateName			��classpath��ģ������
			displayExceptionDetails		�������Ϊtrue�������ʾϸ��
		
			