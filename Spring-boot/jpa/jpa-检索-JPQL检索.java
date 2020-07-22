----------------
JPQL����
----------------
	# �����İ�
		* ͨ��λ�ð�	WHERE id = ?1
		* ͨ�����ư�	WHERE id = :id
	
	# JPQL���޸�Ҫ�������½��з�������쳣: InvalidDataAccessApiUsageException
		 Executing an update/delete query; nested exception is javax.persistence.TransactionRequiredException: Executing an update/delete query

	# ���󷽷�����ֵ֧��ʹ�� Optional ��װ

	
	
-------------------
JPQL��̬����
-------------------
	@DynamicUpdate
		* ��ʶ��ʵ������
		* �Ƚϸ���Ҫʹ�õ�ʵ�����е��ֶ�ֵ������ݿ��в�ѯ�������ֶ�ֵ, �ж����Ƿ����޸�
		* ͨ��������, �����ݿ��еļ�¼�����, ����������ֵ���жԱ�, �����������޸��ֶ�

	@DynamicInsert
		* ��ʶ��ʵ������
		* ��ִ�в����ʱ��, �������ݶ�ʵ��ķ�null�ֶ�����SQL���
	


----------------
JPQL�����﷨
----------------
	# �﷨
		* �������Ƕ��� @Entity, ���Ǳ�, �������Ƕ�������, Ҳ�����ֶ�
		* Ĭ��Entity���ƾ��������ƴ�д, Ҳ����ͨ���޸� @Entity ���Ե�ע�����޸�

		* ��֧��ʹ�� `` ����, ��֧����ĩβ��ӷֺ�: ;
		* ֧��ʹ�� AS �ؼ��������

	
	# LIKE
		FROM User u WHERE u.name LIKE %:name%
	
	# IN
		FROM User u WHERE u.id in :ids
			* ids��һ������
	
	# COUNT
		SELECT COUNT(1) FROM User AS u WHERE u.name LIKE %:#{#user.name}%
	
	# ORDER BY
		FROM User u WHERE u.id = :id ORDER BY id DESC
	
	# INSERT INTO 
		//TODO
	
	# ʹ�� new �ؼ���������ָ�����͵ķ��ض���
		SELECT new io.springboot.jpa.domain.model.UserModel(u.name) FROM User AS u WHERE u.id = :id
		
			* ͨ�����췽��, ����������
			* ��������Ǳ�EntityManager�����, ����ֻд������, ������Ҫ������������·��
			* ͨ�����ַ�ʽ��������: ����ֻ���������ֶ�
		
	# ʹ��Map��Ϊ�������

		SELECT new map(u.name AS name, u.id AS id) FROM User AS u WHERE id = :id
			* ��Ҫ�Լ�������, ʹ��AS�����, ��Ϊkey������
			* ���ж��з��صĽ��������: List<Map<String, Object>> (�κ����������)
			* ���ж��з��صĽ��������: Map<String, Object>

			* ��������ñ���, ���е��е������, key = null, ���е������, key = ���(��0��ʼ)
				{
					"0":"Kevin",
					"1":1
				}
	
	# �������������ֶ�
		SELECT u.name, u.id FROM User AS u
			* ���� List<Object[]>
			* ������ new ���󷽷���ʹ��Map��Ϊ�����Ҳ�������
	
	
	# �������������ֶ�
		SELECT u.name FROM User AS u WHERE u.id = :id

			* ����ֵ����, �͸����ֶε����Ͷ���
	
	# ��������/����ֶε��ܽ�
		* �����ֶε�����¼ʱ, ������������ö�Ӧ�ֶε����ͻ��� Object��
		* �����ӵ�������¼ʱ, ������������� List<Object> ���� List<�ֶζ�Ӧ����>
		* ����ֶ�ʱ, �����Ƕ�����¼���ǵ�����¼, �������Ͷ�Ӧ���� List<Object[]>
	

	# ���峣������
		SELECT new UserModel('unknow' AS name, u.id) FROM User AS u
			
			* ͨ�� Ĭ��ֵ AS ���� ����

----------------
JPQL��������
----------------
	LENGTH()
		* ���㳤��: @Query("SELECT LENGTH(u.name) AS length FROM User AS u WHERE id = :id")

----------------
JPQL��
----------------
	# ��ִ�з������ע��: @Lock

	# �������ĳ�ʱʱ��
		@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
	
	# ��̬����
		//TODO

----------------
JPQL����CAS����
----------------
	//TODO
	
----------------
SPEL
----------------
	# ֧��ʹ��spel���ʽ���ж������Եĵ���
		@Query(value = "FROM user u WHERE u.id = :#{#user.id}")
		Page<UserEntity> testSelect (@Param("user")UserEntity userEntity, Pageable pageable);
	
	# ֧��ʹ��SPEL���ʽ��ȡĳЩ����ı���
		#{#entityName}
			
			* ��ȡ��entityʵ�������, Ĭ����ʵ�����Ƶ�Сд
			* Ҳ������ @Entity(name = "user")��, ͨ��name��������

			@Entity
			class User

			@Query("select u from #{#entityName} u where u.lastname = ?1")
			List<User> findByLastname(String lastname);

				

----------------
JPQL����ע��
----------------
	Query
		String value() default "";
			* ִ�����, ������CREUD, �����Ǳ���SQL, JPQL

		String countQuery() default "";
			* ��ѯ�����������
			* �������һ�������� Pageable , ����ֵ��: Page<T>
				@Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1",
					countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
					nativeQuery = true)
				Page<User> findByLastname(String lastname, Pageable pageable);

		String countProjection() default "";
			* �����ĸ��ֶμ���count

		boolean nativeQuery() default false;
			* �Ƿ��Ǳ���SQL��ѯ
			* ���ز�ѯ, ��֧��ʹ�ö�̬������: Sort api
			* ���ز�ѯ, ��ֱ��֧�� Pageable �Զ������ܼ�¼��, ��Ҫ�Լ�ͨ�� countQuery, ��������ͳ�Ʋ�ѯ

		String name() default "";
			* ָ����ǰquery������, ����Ψһ
			* Ĭ��: ${domainClass}.${queryMethodName}

		String countName() default "";
			* ָ��һ�� countQuery ������, ����Ψһ
			* Ĭ��: ${domainClass}.${queryMethodName}.count

	
	@Param
		* ͨ�����������İ� , ���Ժ��Բ�����λ��
			@Query(value = "select * from #{#entityName} where id > :id", nativeQuery = true)
			Page<UserEntity> testSelect (@Param("id")Integer id, Pageable pageable);

		* �����JDK8�б����˲�������, ����ʡ�Ը�ע��
	
	
	@Modifying
		boolean flushAutomatically() default false;
		boolean clearAutomatically() default false;
			* ���������һ������, ���Ҹ�����=true, ���²���, �ͻ�����ˢ��һ������
			* �������Ϊ false, ���ܻᵼ��һ������, ��ͬһ���ӿ���, ����һ������, �ٴβ�ѯ, ���ܶ�ȡ�����ǻ�����ûˢ�µĶ���

		* �� @Query ���ʹ��
		* ��ʶ��ĳ�� repository �ķ�����,��ʾ��ǰ�� @Query ��һ��UPDATE ���
		* �÷������ص� int ֵ��ǩ�ܵ�Ӱ�������

	@NamedQuery
		String name();
		String query();
		LockModeType lockMode() default NONE;
		QueryHint[] hints() default {};

		* ��ʶ�� Entity ��
		* name	Sring���͵�����,����ָ��������,���� "User.findByName"
		* query	String���������,����HQL,���� "FROM User WHERE name = :name"
		* �ڸ� Entity �Ľӿ��ж���� findByName ����,����ͨ�� query ���Ե�HQL�����м�����

	@QueryHints
		QueryHint[] value() default {};
		boolean forCounting() default true;
		
		* QueryHint ��������
			String name(); 
			String value();

		* �ܶ����ݿⶼ֧�� query hints �﷨(�𽥱���̭)
		* �������õ�ѡ���: QueryHints ���ж���
	
	@Lock
		LockModeType value();

		* ����ģʽ
		* LockModeType ö��
			READ,
			WRITE,
			OPTIMISTIC,
			OPTIMISTIC_FORCE_INCREMENT,
			PESSIMISTIC_READ,					Lock In Share Mode
			PESSIMISTIC_WRITE,					FOR UPDATE
			PESSIMISTIC_FORCE_INCREMENT,
			NONE

	@Procedure
		String value() default "";
		String procedureName() default "";
			* ָ�����ݿ�洢���̵�����
		String name() default "";
			* ָ�� @NamedStoredProcedureQuery �е� String name(); �Ĵ洢��������
		String outputParameterName() default "";
			* �������������


		* ���ô洢����, procedureName �� name ����ֻ����һ��

----------------
����ֹ�
----------------
	# dtype�ֶ�
		* ��Ϊ�̳й�ϵ���³��ֵ��ֶ�
		* ��Ϊ�ж���̳���entity, �������ڶ����������� @Entity ע��
	
	
