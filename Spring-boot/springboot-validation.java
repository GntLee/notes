--------------------
validation			|
--------------------
	# Maven
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
	
--------------------
Լ��ע��			|
--------------------
	@AssertFalse
		* ����Ϊnull,�����Ϊnull�Ļ�����Ϊfalse

	@AssertTrue
		* ����Ϊnull,�����Ϊnull�Ļ�����Ϊtrue

	@DecimalMax
		* ���ò��ܳ������ֵ

	@DecimalMin
		* ���ò��ܳ�����Сֵ

	@Digits
		* ���ñ���������������������λ����С����λ��������ָ����Χ��

	@Future
		* ���ڱ����ڵ�ǰ���ڵ�δ��

	@Past
		* ���ڱ����ڵ�ǰ���ڵĹ�ȥ

	@Max
		* ��󲻵ó��������ֵ

	@Min
		* ��󲻵�С�ڴ���Сֵ

	@NotNull
		* ����Ϊnull�������ǿ�

	@Null
		* ����Ϊnull

	@Pattern
		* ��������ָ����������ʽ

	@Size
		* ���ϡ����顢map�ȵ�size()ֵ������ָ����Χ��

	@Email
		* ������email��ʽ

	@Length
		* ���ȱ�����ָ����Χ��

	@NotBlank
		* �ַ�������Ϊnull,�ַ���trin()��Ҳ���ܵ��ڡ���

	@NotEmpty
		* ����Ϊnull�����ϡ����顢map��size()����Ϊ0���ַ���trin()����Ե��� ""

	@Range
		* ֵ������ָ����Χ��

	@URL
		* ������һ��URL
