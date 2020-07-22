-----------------------
Validation
-----------------------
	# ��̬����
		static ValidatorFactory buildDefaultValidatorFactory()
		static GenericBootstrap byDefaultProvider()
		static <T extends Configuration<T>, U extends ValidationProvider<T>> ProviderSpecificBootstrap<T> byProvider(Class<U> providerType)

-----------------------
ValidatorFactory
-----------------------
	# �ӿڷ���
		Validator getValidator();
		ValidatorContext usingContext();
		MessageInterpolator getMessageInterpolator();
		TraversableResolver getTraversableResolver();
		ConstraintValidatorFactory getConstraintValidatorFactory();
		ParameterNameProvider getParameterNameProvider();
		ClockProvider getClockProvider();
		public <T> T unwrap(Class<T> type);
		public void close();

-----------------------
Validator
-----------------------
	# �ӿڷ���
		<T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups);
			* ��֤���󣬿���ָ��group

		<T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups);
			* ��֤�����ָ�����ԣ�����ָ��group

		<T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups);
			* ͨ��value��֤�����ָ�������Ƿ�Ϸ�������ָ��group
			
		BeanDescriptor getConstraintsForClass(Class<?> clazz);
		<T> T unwrap(Class<T> type);
		ExecutableValidator forExecutables();

-----------------------
ConstraintViolation
-----------------------
	# �ӿڷ���
		String getMessage();
		String getMessageTemplate();
		T getRootBean();
		Class<T> getRootBeanClass();
		Object getLeafBean();
		Object[] getExecutableParameters();
		Object getExecutableReturnValue();
		Path getPropertyPath();
		Object getInvalidValue();
		ConstraintDescriptor<?> getConstraintDescriptor();
		<U> U unwrap(Class<U> type);


---------------------------------------------------
ConstraintValidator<A extends Annotation, T>
---------------------------------------------------
	# �����У���߼��Ľӿ�
	# �ӿڷ���
		void initialize(A constraintAnnotation)
		boolean isValid(T value, ConstraintValidatorContext context);

