--------------------
JPA��ע��			|
--------------------
	@EnableJpaRepositories
		String[] value() default {};
		String[] basePackages() default {};
			* ���� Repository ɨ��İ�

		Class<?>[] basePackageClasses() default {};
			

		Filter[] includeFilters() default {};
		Filter[] excludeFilters() default {};
		String repositoryImplementationPostfix() default "Impl";
		String namedQueriesLocation() default "";
			* namedQuery��ŵ�λ��, Ĭ��: META-INF/jpa-named-queries.properties
				
		Key queryLookupStrategy() default Key.CREATE_IF_NOT_FOUND;
			* �����Ĳ�ѯ����, ö��
				CREATE
					* ���ݷ������ƴ�����ѯ, ����������Ʋ����Ϲ���, ����ʱ����׳��쳣
				USE_DECLARED_QUERY,
					* ����ʽ����, ʹ�� @Query ע������JPQL����SQL
					* ���������ʱ��, ����û�������Ϸ��� @Query , ����׳��쳣
				CREATE_IF_NOT_FOUND
					* �ۺϷ�ʽ, @Query ����, û��query, �͸��ݷ������ƴ�������


		Class<?> repositoryFactoryBeanClass() default JpaRepositoryFactoryBean.class;
			* ���� Repository ������

		Class<?> repositoryBaseClass() default DefaultRepositoryBaseClass.class;

		String entityManagerFactoryRef() default "entityManagerFactory";
			* �����������IOC����

		boolean considerNestedRepositories() default false;
		boolean enableDefaultTransactions() default true;
		BootstrapMode bootstrapMode() default BootstrapMode.DEFAULT;
		char escapeCharacter() default '\\';

	@EntityScan	
		* ����ʵ����ɨ��İ�

	@NoRepositoryBean
		* ��ʶ�� Repository ��,��ʾ�ýӿڲ���һ��Repository
		* ����Ҫ���ɶ�̬�������

	@Modifying
		* ��ʶ�ڷ�����, ��ʾ�÷�����һ������/ɾ������

		boolean flushAutomatically() default false;
		boolean clearAutomatically() default false;
	
