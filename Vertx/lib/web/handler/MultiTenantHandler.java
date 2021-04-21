-----------------------
MultiTenantHandler
-----------------------
	# ���⻧handler�� interface MultiTenantHandler extends Handler<RoutingContext> 


	String TENANT = "tenant";
		* ����Ĭ�ϴ�����RoutingContext�е�key

	
	static MultiTenantHandler create(String header)
	static MultiTenantHandler create(Function<RoutingContext, String> tenantExtractor)
	static MultiTenantHandler create(Function<RoutingContext, String> tenantExtractor, String contextKey)
		* �����Զ���header, key, ִ����(ͨ������ֵ��Ϊvalue)������handler

	MultiTenantHandler addTenantHandler(String tenant, Handler<RoutingContext> handler)
	MultiTenantHandler removeTenant(String tenant)
		* ����value������/ɾ����Ӧ�Ĵ�����

	MultiTenantHandler addDefaultHandler(Handler<RoutingContext> handler)
		* ���Ĭ�ϵĴ�����
	


	# Demo
		MultiTenantHandler.create("X-Tenant")			// ����header
		  .addTenantHandler("tenant-A", ctx -> {		// ���� X-Tenant: tenant-A ������
			// do something for tenant A...
		  })
		  .addTenantHandler("tenant-B", ctx -> {		// ���� X-Tenant: tenant-B ������
			// do something for tenant B...
		  })
		  // optionally
		  .addDefaultHandler(ctx -> {					// ����Ĭ������
			// do something when no tenant matches...
		  });

