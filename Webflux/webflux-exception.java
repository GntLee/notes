-------------------------
�쳣����
-------------------------
	# �쳣����ӿ�: WebExceptionHandler
		public interface WebExceptionHandler {
			Mono<Void> handle(ServerWebExchange exchange, Throwable ex);
		}

		* �����Դ���Filter/Handler���쳣
		* �Զ���ʵ�֣�ͨ��ע����ӵ�IOC
		* �����ж����ͨ�� @Order ����ִ��˳��
	
	# �쳣����ʵ����ϵ
		interface WebExceptionHandler
			|-interface ErrorWebExceptionHandler
				|-abstract AbstractErrorWebExceptionHandler abstract
					|-DefaultErrorWebExceptionHandler

		
	# DefaultErrorWebExceptionHandler ��Ĭ��ע����쳣������

--------------------------------------------------
ResponseStatusExceptionHandler
--------------------------------------------------
	# ϵͳԤ������쳣ʵ�֣�ͨ�����ö��쳣�� HTTP ״̬�������Ӧ���ṩ�������쳣�Ĵ��� 
		public class ResponseStatusExceptionHandler implements WebExceptionHandler
	
	# ����
		public void setWarnLogCategory(String loggerName)
		@Nullable
		@Deprecated
		protected HttpStatus determineStatus(Throwable ex)
		protected int determineRawStatusCode(Throwable ex) 

--------------------------------------------------
WebFluxResponseStatusExceptionHandler
--------------------------------------------------
	# ��չ ResponseStatusExceptionHandler ������ȷ�� @ResponseStatus �κ��쳣ע�͵� HTTP ״̬���롣
		public class WebFluxResponseStatusExceptionHandler extends ResponseStatusExceptionHandler 
	
	# ����
		protected int determineRawStatusCode(Throwable ex)