-------------------------------
�쳣����
-------------------------------
	# �쳣����
		
		* �쳣����ӿ�
			public interface ErrorWebExceptionHandler extends WebExceptionHandler 
				Mono<Void> handle(ServerWebExchange exchange, Throwable ex);

		* Ĭ�ϵ��쳣������
			DefaultErrorWebExceptionHandler

		* ������
			ErrorWebFluxAutoConfiguration
			
			* ErrorWebExceptionHandler �� DefaultErrorAttributes��ʹ���� @ConditionalOnMissingBean ע�⣬Ҳ���ǿ���ͨ���Զ���ʵ��ȥ��������
	



			
	
