-------------------------------
ClassPathBeanDefinitionScanner |
-------------------------------
	# SpringBoot��Ŀ�л��� Spring��Ŀ������<context:component-scan base-package="com.example.demo" />
	
	# ��ô��IOC ������ʼ���׶�(����beanFactoryPostProcessor�׶�)�ͻ���� ClassPathBeanDefinitionScanner ����ɨ����� ������

	# �������Ϲ�����������ע�ᵽIOC ������
		* Mybatis ��Mapperע����(ClassPathMapperScanner) ��ͬ���̳� ClassPathBeanDefinitionScanner, �����Զ����˹�����������ʵ�ֵ�
	
	# ClassPathBeanDefinitionScanner ���þ��ǽ�ָ�����µ���ͨ��һ��������˺� ��Class ��Ϣ��װ�� BeanDefinition ����ʽע�ᵽIOC������

	# һ�������Զ���ɨ����
		* �ѱ�ʶ���Զ���ע�����,��ӵ�IOC��

	# �๹�췽��
		ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry);
		ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters);
		ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters,Environment environment);
		ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters,Environment environment, @Nullable ResourceLoader resourceLoader);


		* ����̳е�ʱ��,����ָ��Ҫ�ֶ�����Ҫ���õĸ��๹�췽��: super
	
	
-------------------------------
�Զ���ɨ����				    |
-------------------------------

		