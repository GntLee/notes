-----------------------
��֤
-----------------------
	# ������֤����
		* ʹ��ע���ڶ���������Ͻ��б�ʶ

	# ��֤����
		1. ʹ�� Validation ��ȡ�� ValidatorFactory
		2. ͨ�� ValidatorFactory ��ȡ�� Validator
		3. ʹ�� Validator �Զ���, ���Խ���У��, ����У��Ľ��
	
	
	# ֧����֤�ĵط�
		* ����, ����, ���캯��, ����, ����ֵ, ����

		* ע��ע�⣬����ǶԷ�����������֤����ô����������Ҫ����JavaBean�Ĺ淶:������ getter/is ��ͷ
			@AssertTrue(message = "�ֻ������������д����һ��")
			public boolean isEmailAndPhoneValid () {
				if (this.email == null && this.phone == null) {
					return false;
				}
				return true;
			}
	
	# ��֤
		// SPI����������
		GenericBootstrap genericBootstrap = Validation.byDefaultProvider();
		
		// ��ȡ����
		Configuration<?> configuration = genericBootstrap.configure();
		
		// �����ô���������
		ValidatorFactory  validatorFactory = configuration.buildValidatorFactory();
		
		Validator validation = validatorFactory.getValidator();
		
		Set<ConstraintViolation<Object>> constraintViolations = validation.validate(user);
		
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			// �쳣��Ϣ
			String message = constraintViolation.getMessage();
			System.out.println(message);
		}

-----------------------
Object Graph��֤
-----------------------
	# Object Graph��ָ��������˽ṹ�������������ù�ϵ
		* Bean Validation֧��Object Graph��֤
	
	# Ĭ�����A��������B�����ǲ����B�������У��ġ���Ҫ��B������ֶλ���getter��ʶ @Valid ע�����
		* ���Ա�ʶ�ڼ����ϣ���Լ����õ�Ԫ�ذ���ִ��У��
	

