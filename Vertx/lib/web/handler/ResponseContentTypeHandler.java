-------------------------------
ResponseContentTypeHandler 
-------------------------------
	# �Զ����Response��Content-Typeͷ�� interface ResponseContentTypeHandler extends Handler<RoutingContext> 
		* �����Ͼ��ǣ����ݿͻ��˵�accepetͷ������contentTypeͷ
		* ������Ҫ�Լ���ҵ��handler���ж�acceptͷ�����в�ͬ��������Ӧ


	String DEFAULT_DISABLE_FLAG = "__vertx.autoContenType.disable";
		* Ĭ�Ͻ��õ�flag
		* ���RoutingContext���������flag��ֵ��null����ô�ͻ�������handler
	
	static ResponseContentTypeHandler create()
	static ResponseContentTypeHandler create(String disableFlag)

