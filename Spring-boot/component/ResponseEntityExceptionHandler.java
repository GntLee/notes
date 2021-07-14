-----------------------------------
ResponseEntityExceptionHandler 
-----------------------------------
	# ϵͳԤ�����Exception������������ֵ���;��� ResponseEntity
	# ���Ժܶ��쳣��������Ԥ����
		@ExceptionHandler(value={
				NoSuchRequestHandlingMethodException.class,
				HttpRequestMethodNotSupportedException.class,
				HttpMediaTypeNotSupportedException.class,
				HttpMediaTypeNotAcceptableException.class,
				MissingServletRequestParameterException.class,
				ServletRequestBindingException.class,
				ConversionNotSupportedException.class,
				TypeMismatchException.class,
				HttpMessageNotReadableException.class,
				HttpMessageNotWritableException.class,
				MethodArgumentNotValidException.class,
				MissingServletRequestPartException.class,
				BindException.class,
				NoHandlerFoundException.class
			})
		public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {...}
		
		
		* ����ͨ�����쳣���͵��жϣ����ò�ͬ���쳣������
		* Ĭ�ϣ����ǵ��� handleExceptionInternal �������
				protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
						HttpHeaders headers, HttpStatus status, WebRequest request) {

					if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
						request.setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
					}

					return new ResponseEntity<Object>(body, headers, status);
				}
		
		* һ��ʹ�ã����Լ̳�������� @ControllerAdvice ע�⣬Ȼ��д��Ҫ������쳣��������