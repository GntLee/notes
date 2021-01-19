----------------------------
Spring boot-�쳣����1		|
----------------------------
	# ʹ�� Spring Boot �ṩ�Ĵ�����
	# ���Դ��� 404,500���쳣
	1,�Զ��쳣���� Controller ,ʵ�ֽӿ�: org.springframework.boot.web.servlet.error.ErrorController
	2,��д����
		
		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;

		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
		import org.springframework.stereotype.Controller;
		import org.springframework.web.bind.annotation.RequestMapping;
		import org.springframework.web.util.WebUtils;

		/**
		 * 
		 * ����̬��Դ 404 ֮����쳣
		 * @author Administrator
		 *
		 */
		@Controller
		@RequestMapping("/error")
		public class ErrorHandler implements org.springframework.boot.web.servlet.error.ErrorController {

			static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);
			
			@Override
			public String getErrorPath() {
				return "/error";
			}
			
			@RequestMapping
			public Object onError (HttpServletRequest request, HttpServletResponse response) {
				
				Integer statusCode = null;
				
				String forwardServletPath = (String) request.getAttribute(WebUtils.FORWARD_SERVLET_PATH_ATTRIBUTE);	
				
				if (forwardServletPath != null) {
					/**
					 * ��ֱ�ӷ��ʣ������� Servlet forward����
					 */
					Class<?> exceptionType = (Class<?>) request.getAttribute(WebUtils.ERROR_EXCEPTION_TYPE_ATTRIBUTE);   //�쳣����
					Throwable exception = (Throwable) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);    		//�쳣��,ֻ���� 500 �쳣�������,��ֵ��Ϊ��
					statusCode = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);     				//HTTP�쳣״̬��
					String erroPath = (String) request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE);         		//�������쳣�������ַ(�����ǵ�ǰ��ַ-request.getRequestURI())
					
					LOGGER.error("exceptionType={}, exception={}, statusCode={}, erroPath={}", exceptionType, exception, statusCode, erroPath);
				}
				
				return ResponseEntity.status(statusCode == null ? HttpStatus.NOT_FOUND.value() : statusCode).build();
			}
		}

	
	* @ControllerAdvice ���ܴ�����̬��Դ·���� 404 �쳣
	* ����ʹ�����ַ�ʽ����
	* Ҫע�������� ���� /error ·��

----------------------------
Spring boot-�쳣����2		|
----------------------------
	# ʹ�� Spring �ṩ�Ĵ�����
	# ����ֻ�ܴ���� 500 �������쳣,404 ֮��Ĳ��ᴦ��
	# ʵ�� HandlerExceptionResolver ע�ᵽIOC��ʵ��ȫ�ֵ��쳣����

		@Component
		public class ExceptionController implements HandlerExceptionResolver {

			private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
			
			/*
			 * ���Ը������������(Ajax)��ȷ���Ƿ���mv����ֱ����Ӧajax����
			 * ���ַ�ʽ,ֻ�ܴ�������������쳣,�޷����� 404 ֮����쳣
			 */
			@Override
			@ExceptionHandler
			public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
				HandlerMethod handlerMethod = (HandlerMethod) o;		//��handler����ǿת
				Method method = handlerMethod.getMethod();				//��ȡ�������쳣�ķ�������
				return new ModelAndView("error/error");
			}
		}
	

	# ����
	# ����ֻ�ܴ���� 500 �������쳣,404 ֮��Ĳ��ᴦ��
	# ��ʶ @ControllerAdvice ����Controllerע��,ע�ᵽIOC,��ʵ��ȫ���쳣�Ĵ���
		@ControllerAdvice
		public class ExceptionController {

			private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
			
			/*
			 * ���Ը������������(Ajax)��ȷ���Ƿ���mv����ֱ����Ӧajax����
			 * ���ַ�ʽ,ֻ�ܴ�������������쳣,�޷����� 404 ֮����쳣
			 */
			@ExceptionHandler
			public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
				HandlerMethod handlerMethod = (HandlerMethod) o;		//��handler����ǿת
				Method method = handlerMethod.getMethod();				//��ȡ�������쳣�ķ�������
				return new ModelAndView("error/error");
			}
		}


----------------------------
Spring boot-����ϲ����ʵ��  |
----------------------------
	# ��ͼ�����쳣,��Ӧ�쳣����ͼ��Ϣ
	# api�ӿ��쳣,��Ӧ�쳣��JSON/״̬����Ϣ
	
	# ����������쳣������
		import java.io.IOException;

		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;

		import org.springframework.http.HttpStatus;
		import org.springframework.http.converter.HttpMessageNotReadableException;
		import org.springframework.validation.BindException;
		import org.springframework.web.HttpMediaTypeNotSupportedException;
		import org.springframework.web.HttpRequestMethodNotSupportedException;
		import org.springframework.web.bind.MissingServletRequestParameterException;
		import org.springframework.web.bind.ServletRequestBindingException;
		import org.springframework.web.bind.annotation.ExceptionHandler;
		import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
		import org.springframework.web.multipart.MaxUploadSizeExceededException;
		import org.springframework.web.servlet.NoHandlerFoundException;

		import io.javaweb.paste.common.Message;
		import io.javaweb.paste.exception.ServiceException;


		public abstract class BaseExceptionAdvice {
			
			//����·��δ�ҵ�
			@ExceptionHandler(NoHandlerFoundException.class)  
			public Object noHandlerFoundException(HttpServletRequest request,HttpServletResponse response,NoHandlerFoundException e) throws IOException {
				return this.errorHandler(request,response,Message.fail(HttpStatus.NOT_FOUND, "����·��������"),e);
			}
			
			//�ϴ��ļ�����
			@ExceptionHandler(value = {MaxUploadSizeExceededException.class})
			public Object maxUploadSizeExceededException(HttpServletRequest request,HttpServletResponse response,MaxUploadSizeExceededException e) throws IOException {
				return this.errorHandler(request,response,Message.fail(HttpStatus.BAD_REQUEST, "�ļ���С���ܳ���:" + e.getMaxUploadSize()),e);
			}
			
			//����ʽ��֧��
			@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
			public Object httpRequestMethodNotSupportedException(HttpServletRequest request,
											HttpServletResponse response,
											HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
				return this.errorHandler(request, response, Message.fail(HttpStatus.METHOD_NOT_ALLOWED, "����ʽ��֧��"),httpRequestMethodNotSupportedException);
			}
			
			//ȱ�ٱ������
			@ExceptionHandler(MissingServletRequestParameterException.class)
			public Object missingServletRequestParameterException(HttpServletRequest request,
																		HttpServletResponse response,
																		MissingServletRequestParameterException exception) {
				return this.errorHandler(request,response,Message.fail(HttpStatus.BAD_REQUEST, "ȱ�ٱ������:" + exception.getParameterName()),exception);
			}
			
			//ҵ���쳣
			@ExceptionHandler(ServiceException.class)
			public Object businessException(HttpServletRequest request,
													HttpServletResponse response,
													ServiceException exception) {
				return this.errorHandler(request, response, exception.message(),exception);
			}
			
			//ϵͳ�쳣
			@ExceptionHandler(Exception.class)
			public Object exception(HttpServletRequest httpServletRequest,
											HttpServletResponse httpServletResponse,
											Exception exception) {
				return this.errorHandler(httpServletRequest, httpServletResponse, Message.fail(HttpStatus.INTERNAL_SERVER_ERROR, "ϵͳ�쳣"),exception);
			}
			
			
			//�Ƿ�����
			@ExceptionHandler(value = {
				HttpMessageNotReadableException.class,
				IllegalArgumentException.class,
				MethodArgumentTypeMismatchException.class,
				BindException.class,
				HttpMediaTypeNotSupportedException.class,
				ServletRequestBindingException.class
			})
			public Object  badRequestException(HttpServletRequest request,HttpServletResponse response,Exception e) throws IOException {
				e.printStackTrace();
				return this.errorHandler(request,response,Message.fail(HttpStatus.BAD_REQUEST, "�Ƿ�����"),e);
			} 
			
			
			abstract public Object errorHandler(HttpServletRequest request,HttpServletResponse response,Message<Void> message,Throwable throwable); 
		}
	
	# api�쳣
		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;

		import org.springframework.http.ResponseEntity;
		import org.springframework.web.bind.annotation.ControllerAdvice;
		import org.springframework.web.bind.annotation.RestController;

		import io.javaweb.paste.common.Message;

		@ControllerAdvice(annotations = {RestController.class})		// ֻ����restController
		public class RestExceptionAdvice extends BaseExceptionAdvice {

			@Override
			public ResponseEntity<String> errorHandler(HttpServletRequest request, HttpServletResponse response, Message<Void> message,Throwable throwable) {
				throwable.printStackTrace();
				return ResponseEntity.status(message.getStatus()).build();
			}

		}

	# ��ͼ�쳣
		
		import java.io.PrintWriter;

		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;

		import org.springframework.stereotype.Controller;
		import org.springframework.web.bind.annotation.ControllerAdvice;
		import org.springframework.web.servlet.ModelAndView;

		import io.javaweb.paste.common.Message;
		import io.javaweb.paste.common.StringBuilderWriter;

		@ControllerAdvice(annotations = {Controller.class})		// ֻ����Controller
		public class ExceptionAdvice extends BaseExceptionAdvice {

			public static final String ERROR_PATH = "error/error";
			
			@Override
			public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Message<Void> message,Throwable throwable) {
				
				ModelAndView modelAndView = new ModelAndView(ERROR_PATH);
				
				modelAndView.addObject("message", message);
				
				StringBuilderWriter stringBuilderWriter = new StringBuilderWriter();
				
				PrintWriter printWrite = new PrintWriter(stringBuilderWriter);
				
				throwable.printStackTrace(printWrite);
				
				modelAndView.addObject("throwable", stringBuilderWriter.toString());
				
				return modelAndView;
			}
		}

		

		# Ҳ���Զ�����ע��
			@View		��ʶ����ͼController��
			@Api		��ʶ��api�ӿ�Controller��


			// ������ͼ�쳣
			@ControllerAdvice(annotations = {View.class})
			public class VieExceptionAdvice extends BaseExceptionAdvice
			

			// ����ӿ��쳣
			@ControllerAdvice(annotations = {Api.class})
			public class ApiExceptionAdvice extends BaseExceptionAdvice 
