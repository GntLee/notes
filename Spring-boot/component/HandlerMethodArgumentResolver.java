------------------------------
HandlerMethodArgumentResolver |	
------------------------------
	# ʵ�ָýӿڿ����Զ����������
		public interface HandlerMethodArgumentResolver {
			boolean supportsParameter(MethodParameter parameter);

			@Nullable
			Object resolveArgument(MethodParameter parameter, Message<?> message) throws Exception;
		}
	
	# ��mvc��������, ����
	@Configuration
	public class WebMvcConfiguration implements WebMvcConfigurer {
		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
			// ����Լ���ʵ����
		}
	}
	
	