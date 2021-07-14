------------------------
WebFluxConfigurer
------------------------
	# WebFlux�����ýӿ�: WebFluxConfigurer 

		default void configureContentTypeResolver(RequestedContentTypeResolverBuilder builder) {}

		default void addCorsMappings(CorsRegistry registry) {}

		default void configurePathMatching(PathMatchConfigurer configurer) {}

		default void addResourceHandlers(ResourceHandlerRegistry registry) {}

		default void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {}

		default void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {}
			* http��Ϣ�������������
			
		default void addFormatters(FormatterRegistry registry) {}

		@Nullable
		default Validator getValidator() {
			return null;
		}

		@Nullable
		default MessageCodesResolver getMessageCodesResolver() {
			return null;
		}

		@Nullable
		default WebSocketService getWebSocketService() {
			return null;
		}
		default void configureViewResolvers(ViewResolverRegistry registry) {}

	# �߼�����ʵ��: DelegatingWebFluxConfiguration 
		|-WebFluxConfigurationSupport
			|-DelegatingWebFluxConfiguration
		
		* �����Զ���̳��࣬���� WebFluxConfigurer ��ʵ��
			@Configuration
			public class WebfluxConfiguration extends DelegatingWebFluxConfiguration {

			}


		

	