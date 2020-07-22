--------------------------------
SpringBoot
--------------------------------
	# ����
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
	
	# �� Controller �ķ����ϣ����βζ������ @Validated ע��
		
		@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
		@Retention(RetentionPolicy.RUNTIME)
		@Documented
		public @interface Validated {
			Class<?>[] value() default {};  // ͨ��valuָ��Ҫִ��У���Group
		}

		* �βζ���һ��Ҫ���� getter/setter ����
	

	# ͨ������ BindException ����У����쳣��Ϣ
		* �� @Validated �������棬����һ����BindingResult bindingResult ����
		* ͨ������������ж��Ƿ����쳣���Լ���ȡ���쳣����Ϣ

		@GetMapping
		public Object test ( @Validated  User user, BindingResult bindingResult) {
			if (bindingResult.hasErrors()) {
				// ��ȡ���쳣����
				List<ObjectError> objectErrors = bindingResult.getAllErrors();
				// ��ȡ��Ĭ�ϵ���ʾ��Ϣ
				List<String> errorMessages = objectErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
				return errorMessages;
			}
			return Message.success(user);
		}
	
	# ͨ��ȫ���쳣������������У����쳣��Ϣ
		* ���û�ж��� BindingResult ����ô��У��ʧ�ܵ�ʱ����׳��쳣
		* �����쳣: BindException/MethodArgumentNotValidException 
			@ExceptionHandler(value = {
				BindException.class
			})
			public Object validateFail(HttpServletRequest request, HttpServletResponse response, BindException e)
					throws IOException {
				String errorMessage = e.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("|"));
				return this.errorHandler(request, response, Message.fail(Message.Code.BAD_REQUEST, errorMessage), e);
			}
	
	# ����ʽ����У��
		@GetMapping("/get")
		public RspDTO getUser(@RequestParam("userId") @NotNull(message = "�û�id����Ϊ��") Long userId) {
			User user = userService.selectById(userId);
			if (user == null) {
				return new RspDTO<User>().nonAbsent("�û�������");
			}
			return new RspDTO<User>().success(user);
		}
		
		* ����ʽ�Ӳ���У�飬��Ҫ��Controller������:@Validated
		* ����βεĲ�����֤ʧ�ܣ��׳��쳣: ConstraintViolationException
			@ExceptionHandler(value = { ConstraintViolationException.class })
			public Object validateFail(HttpServletRequest request, HttpServletResponse response, ConstraintViolationException e)
					throws IOException {
				//	e.getMessage();   ��ʾ��Ϣ�����ǰ׺��[������].[������]: 
				String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).limit(1).collect(Collectors.toList()).get(0);
				return this.errorHandler(request, response, Message.fail(Message.Code.BAD_REQUEST, errorMessage), e);
			}