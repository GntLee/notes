------------------------
�쳣����				|
------------------------
	# ʹ�� SendErrorFilter ������
		* ���������쳣,�������Ƿ�ִ�е�������,�������д����쳣,������δת����errorPath
		* errorPath : @Value("${error.path:/error}")

			@Override
			public boolean shouldFilter() {
				RequestContext ctx = RequestContext.getCurrentContext();
				return ctx.getThrowable() != null && !ctx.getBoolean("sendErrorFilter.ran", false);
			}

		* ������ִ�й����������쳣,������SendErrorFilter�����쳣����
			RequestContext requestContext = RequestContext.getCurrentContext();
			requestContext.setThrowable(new Exception());

		* Ҳ����������µĲ���
			// �������
			requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			// �쳣����
			requestContext.set("error.exception",new Exception());
			// ������Ϣ
			requestContext.set("error.message","������Ϣ");
		
		* �����Լ�ʵ���쳣��ת����http�ӿ�,�����Լ�����Ĵ�����Ϣ
	

	# ��Ϊ���������������:pre,post,route �����׶���,ֻҪ���쳣�׳�������뵽erro�׶εĴ���,���Կ����Զ���ErrorFilter
		
		