-------------------------
Hibernate �ṩ��ע��
-------------------------
	@CodePointLength
		* ��֤�ַ����еĴ���㳤���Ƿ���ڰ�������Сֵ�����ֵ֮��

		int min() default 0;
		int max() default Integer.MAX_VALUE;
		NormalizationStrategy normalizationStrategy() default NormalizationStrategy.NONE;
			* ö��
				NONE
				NFD
				NFC
				NFKD
				NFKC

	@Range
		* ֵ������ָ����Χ��

	@URL
		* ��֤�ַ�����һ��URL

		String protocol() default "";
			* �����Э��
		String host() default "";
			* �����HOST
		int port() default -1;
			* ����Ķ˿�
		String regexp() default ".*";
			* ��֤������
		Pattern.Flag[] flags() default { };
			* �����Flag

	@Length
		* ��֤�ַ������ȱ�����ָ����Χ��

		int min() default 0;
		int max() default Integer.MAX_VALUE;