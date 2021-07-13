---------------------
ObjectProvider
---------------------
	# Spring4.3�ṩ�Ķ�����ObjectProvider �ӿ� ��ObjectFactory�ӿڵ���չ��ר��Ϊע�����Ƶģ�������ע���ø��ӿ��ɺ͸����п�ѡ��
	# �����ע�������BeanΪ�ջ��ж��ʱ������ObjectProvider�������õ�ʱ����
		1. ���ע��ʵ��Ϊ��ʱ��ʹ�� ObjectProvider �������ǿ�������µ��������󲻴����쳣��
		2. ����ж��ʵ����ObjectProvider �ķ��������Beanʵ�ֵ�Ordered�ӿڻ� @Order ע��ָ�����Ⱥ�˳���ȡһ��Bean���Ӷ����ṩ��һ�����ӿ��ɵ�����ע�뷽ʽ��

	
	# interface ObjectProvider<T> extends ObjectFactory<T>, Iterable<T>

		T getObject(Object... args) throws BeansException;
			* ����ָ�����͵�bean, ��������в�����, �׳�NoSuchBeanDefinitionException�쳣
			* ����������ж�������͵�bean, �׳�NoUniqueBeanDefinitionException�쳣

		@Nullable
		T getIfAvailable() throws BeansException;
			*  ���ָ�����͵�beanע�ᵽ������, ���� bean ʵ��, ���򷵻� null
		
		T getIfAvailable(Supplier<T> defaultSupplier) throws BeansException 
			* ������ض��󲻴��ڣ�����лص����ص������� Supplier ����

		void ifAvailable(Consumer<T> dependencyConsumer) throws BeansException
			* ���Ѷ����һ��ʵ���������ǹ���Ļ�����ģ����������ͨ��Consumer�ص�����Ŀ�����

		@Nullable
		T getIfUnique()
			*  ��������û�Ψһ��û��ָ��primary���򷵻�null�����򣬷��ض���
		
		T getIfUnique(Supplier<T> defaultSupplier) throws BeansException 
			* �������Ψһ���������Supplier�Ļص�����
		
		void ifUnique(Consumer<T> dependencyConsumer) throws BeansException
			* �������Ψһ���������ĵ��ö���
		
		Iterator<T> iterator()
			* ���ط��������Ķ����Iterator��û������˳��֤��һ��Ϊע��˳��
		
		Stream<T> stream()
			* ���ط������������������Stream��û������˳��֤��һ��Ϊע��˳��

		Stream<T> orderedStream()
			* ���ط������������������Stream���ڱ�עSpringӦ���������в���@Orderע���ʵ��Order�ӿڵ�˳��
