----------------------------
PlatformTransactionManager	|
----------------------------
	# ���������
	# һЩʵ����
		DataSourceTransactionManager(Jdbc/MyBatis/JdbcTmplate)
		HibernateTransactionManager(hibernate)
		JtaTransactionManager(jta)
			|-WebLogicJtaTransactionManager
			|-WebSphereUowTransactionManager

	TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException;
		* �÷���������������Ϣ�����񻷾�����һ���Ѿ����ڵ�����,���ߴ���һ������

	void commit(TransactionStatus status) throws TransactionException;
		* ��������״̬�ύ����
		* ������񱻱��ΪrollbackOnly,��÷���ִ�лع�����

	void rollback(TransactionStatus status) throws TransactionException;
		* �ع�����
		* ��commit�����׳��쳣ʱ,�÷������ᱻ��ʽ����
	
----------------------------
TransactionStatus			|
----------------------------
	# �̳��Խӿ�:SavepointManager
	# �����������״̬

	boolean isNewTransaction();
		* �жϵ�ǰ�����Ƿ���һ���µ�����,�������false,���ʾ��ǰ������һ���Ѿ����ڵ�����,���ߵ�ǰ����δ���������񻷾���

	boolean hasSavepoint();
	void setRollbackOnly();
	boolean isRollbackOnly();
		* ���ύ,����ִ�лع�
		* ����ǰ������ΪrollbackOnly,�Ӷ�֪ͨ���������ֻ�ܻع�������

	void flush();
	boolean isCompleted();
		* �жϵ�ǰ�����Ƿ��Ѿ��������Ѿ��ύ��ع���

	Object createSavepoint() throws TransactionException;
		* �����ָ���
	
	void rollbackToSavepoint(Object savepoint) throws TransactionException;
		* �ع����ָ���

	void releaseSavepoint(Object savepoint) throws TransactionException;
		* ����ָ���

----------------------------
TransactionDefinition		|
----------------------------
	# ����Ķ���(���뼶��,����,��ʱ,ֻ��)

	int PROPAGATION_REQUIRED = 0;
	int PROPAGATION_SUPPORTS = 1;
	int PROPAGATION_MANDATORY = 2;
	int PROPAGATION_REQUIRES_NEW = 3;
	int PROPAGATION_NOT_SUPPORTED = 4;
	int PROPAGATION_NEVER = 5;
	int PROPAGATION_NESTED = 6;
		* ���񴫲�����

	int ISOLATION_DEFAULT = -1;
		* ʹ�����ݿ�Ĭ�ϵĸ��뼶��
	int ISOLATION_READ_UNCOMMITTED = Connection.TRANSACTION_READ_UNCOMMITTED;
	int ISOLATION_READ_COMMITTED = Connection.TRANSACTION_READ_COMMITTED;
	int ISOLATION_REPEATABLE_READ = Connection.TRANSACTION_REPEATABLE_READ;
	int ISOLATION_SERIALIZABLE = Connection.TRANSACTION_SERIALIZABLE;
		* ����ĸ��뼶��

	int TIMEOUT_DEFAULT = -1;
		* Ĭ�ϵĳ�ʱ

	int getPropagationBehavior();
	int getIsolationLevel();
		* ��ȡ���뼶������񴫲�����

	int getTimeout();
		* ��ʱʱ��

	boolean isReadOnly();
		* �Ƿ�ֻ��

	String getName();

----------------------------------
TransactionSynchronizationManager |
----------------------------------
	# �����ͬ��������(abstract)
		* �����Ǻ��߳̽�����ص�
		* ����ͬ��������ʹ��ThreadLocalΪÿ���̰߳�һ�����ݿ�����

		* ������һ���߳���,��һ��Service�ķ�����ִ��ʱ,������������Ҫһ������
		* spring���ڿ�������ʱ����������ͬ��������Ϊ���̰߳�һ�����ݿ�����
		* ֮���ڵ�ǰ�̵߳����������,ֻҪ��Ҫ�õ����ݿ�����,���Ǵ�ThreadLocal��ȡ���֮ǰ���󶨵�����
		* ��Ҳ��Ϊʲô��JdbcTemplate���ֵ�����Bean�ܹ����������ڶ��̻߳�����
		* ��ΪJdbcTemplate��ִ��sqlʱҲ�Ǵ�����ͬ���������������ݿ����ӵ�
	
	# ˽�еľ�̬
		ThreadLocal<Map<Object, Object>> resources = new NamedThreadLocal<>("Transactional resources");
			* ��ǰ�̶߳�Ӧ��connection��session�����͵���Դ

		ThreadLocal<Set<TransactionSynchronization>> synchronizations = new NamedThreadLocal<>("Transaction synchronizations");
			* ��ŵ�ǰ�̵߳�����ִ�й����еĻص�����

		ThreadLocal<String> currentTransactionName = new NamedThreadLocal<>("Current transaction name");
			* ��ǰ�̵߳���������

		ThreadLocal<Boolean> currentTransactionReadOnly = new NamedThreadLocal<>("Current transaction read-only status");
			* ��ǰ�̵߳������Ƿ�Ϊֻ��

		ThreadLocal<Integer> currentTransactionIsolationLevel = new NamedThreadLocal<>("Current transaction isolation level");
			* ��ǰ�̵߳�������뼶��

		ThreadLocal<Boolean> actualTransactionActive = new NamedThreadLocal<>("Actual transaction active");
			* ��ǰ�̵߳������Ƿ񱻼���

