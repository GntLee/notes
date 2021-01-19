----------------------------
��ͬServlet������������		|
----------------------------
	# WebServerFactory ��һ����ǽӿ�,ʵ��������
		TomcatServletWebServerFactory
		JettyServletWebServerFactory
		UndertowServletWebServerFactory
	
	# �Զ�����̳���:WebServerFactoryCustomizer<T extends WebServerFactory>
		@FunctionalInterface
		public interface WebServerFactoryCustomizer<T extends WebServerFactory> {
			void customize(T factory);
		}
		
		* ͨ����ͬ�ķ��������ò�ͬ��Servlet����
			@Configuration
			public class ServletContainerConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

				@Override
				public void customize(TomcatServletWebServerFactory factory) {
					factory.setServerHeader("springboot.io");
					factory.addAdditionalTomcatConnectors(connector());
					factory.addContextCustomizers(new TomcatContextCustomizer() {
						@Override
						public void customize(Context context) {
							context.setCookieProcessor(new LegacyCookieProcessor());
						}
					});
				}

				private Connector connector()  {
					Connector connector = new Connector("org.apache.coyote.http11.Http11Nio2Protocol");
					return connector;
				}
			}
