------------------------
zuul					|
------------------------
	# �������,ʵ�ֽӿ�: com.netflix.zuul.ZuulFilter
		@Bean		//ע��ioc����Ч
		public class DemoFilter extends ZuulFilter {
			@Override
			public boolean shouldFilter();
				* �жϵ�ǰ�������Ƿ�ִ��,������� true,��ִ��:run() ����

			@Override
			public Object run();
				* ִ��У��ķ���
				* Ŀǰ��ܲ���ȥ������ֵ(�����˷���ֵ)

			@Override
			public String filterType();
				* ���������������,�������˹�������������ĸ�����������ִ��,ö���ַ���
					pre(FilterConstants.PRE_TYPE)
						* ������·��֮ǰִ��
					error(FilterConstants.ERROR_TYPE)
						* �������쳣ʱ����
					post(FilterConstants.POST_TYPE)
						* �����(route��error֮��)����
					route(FilterConstants.ROUTE_TYPE)
						* ������·��ʱ����
					

			@Override
			public int filterOrder();
				* �����ڶ����������ʱ��,��ֵ�����˹�������ִ��˳��
				* ��ֵԽС,���ȼ�Խ��
		}
	
	# У��СDemo
		@Override
		public Object run() {
			RequestContext requestContext = RequestContext.getCurrentContext();
			HttpServletRequest httpServletRequest = requestContext.getRequest();
			if(httpServletRequest.getHeader("auth") == null) {
				//������·��
				requestContext.setSendZuulResponse(false);
				//������Ӧ״̬��Ϊ401
				requestContext.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			}
			return null;
		}


------------------------
ͨ�����������ù�����	|
------------------------
	zuul.<SimpleClasName>.<filterType>.disable=true

	* zuul.AuthFilter.pre.disable=true
	
		

------------------------
��̬Filter				|
------------------------
	# ��������������Ķ�̬����,��Ҫ������̬��jvm����:Groovy

------------------------
FilterProcessor			|
------------------------
	FilterProcessor getInstance()
	void setProcessor(FilterProcessor processor)



------------------------
RequestContext			|
------------------------
	# ����������
	# RequestContext
		HttpServletResponse getResponse();
        StringBuilder getFilterExecutionSummary();
        getOriginContentLength();
        getOriginResponseHeaders();
        getZuulResponseHeaders();
        getZuulEngineRan();
        getResponseGZipped();
        getResponseDataStream();
        getRequestQueryParams();
        getResponseStatusCode();
        getRouteHost();
        getResponseBody();

