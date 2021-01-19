-----------------
���������
-----------------
	# ����������@Query����, ����Ҫ�Լ����巵�صĽ������
		* ��ͬ�ķ���ֵ����, ���ܻ�Ӱ�쵽������ʽ
	
	
	# ��ҳ�Ľ����
		Page<User> findByName(String name, Pageable pageable);
			* Page ��Ϊ����ֵ, ��Ĭ��ִ��һ�� COUNT ����
				* Ҳ����ͨ����̬����, ����һ���������ܼ�¼���ķ�ҳ����: Pageable.unpaged()
			* һ���Լ��������еĽ��, ���Ұ����˷�ҳ��Ϣ

		Slice<User> findByName(String name, Pageable pageable);
			* ��������ܼ�¼����, û�з�ҳ��Ϣ
			* ��Slice��˵, ֻ֪���Ƿ�����һ��Slice����, ����֪�����ж�����

		List<User> findByName(String name, Pageable pageable);
			*  ����ִ��ͳ�Ʋ�ѯ, ����������ҳָ��������
	

	# ��ʽ�����, ʹ��Java8�� Stream ��Ϊ�����
		
		Stream<User> all();

		* ������ֻ��������ִ��: @Transactional(readOnly = true), �����������̱���Ҫ����jdbc���ӵ���Ч��
			@Transactional(readOnly = true)
			public List<User> all() {
				Stream<User> stream = this.userRepository.all();
				return stream.collect(Collectors.toList());
			} 
		
		* ������׳��쳣: InvalidDataAccessApiUsageException
	

		
	# �첽�����, ʹ�� Future/CompletableFuture/ListenableFuture ��Ϊ�����
		@Query("FROM User")
		Future<List<User>> all();

		@Query("FROM User")
		CompletableFuture<List<User>> all();
		
		@Query("FROM User")
		ListenableFuture<List<User>> all();


		* �첽����, �ڵ��õ�ʱ��ͻ���������
		* ��ѯ������ύ�� Spring TaskExecutor

	# ͨ�õĽ����
		List<Map<String, Object>>
		Map<String, Object> 

		List<Object[]>
		Object[]
		
		Object

		* ֧������ͨ�õĽ����
	
	# Projections ��չ
		* ��������ʵ��Ĳ�������, �����Զ��岿�����Եķ�װ�ӿ�
			class User{
				id,
				phone,
				version,
				... // ����N������
			}

			public interface UserNameAndVersion { // �ӿڽ���������Ҫ������ getter
				String getName();
				Integer getVersion();
			}
			
			public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor <User> {
				@Query("SELECT u.name, u.version FROM User AS u WHERE id = :id")
				UserNameAndVersion test(@Param("id")Integer id);
			}
			

		* ͨ����̬�������ɵ���: org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap
		* ���������װ��һ��map
		* ���л�ΪJSON
			{
				"target":{null:"Kevin", null:1},
				"targetClass":"org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap"
			}
		
		* �����Զ�������Ķ���, �Լ�֧��ʹ��Spel���ʽ
	
	# ���������
		* ֧�ֶԽ����ʹ�÷���, ����Ҫ�ڲ�����ָ��ʵ������
		* ����ֻ��ʹ��: List,Page,Slice

			public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor <User> {
				@Query("FROM User WHERE id = :id")
				<T> List<T> all(@Param("id")Integer id, Class<T> target, Pageable page);
			}

			List<UserNameAndVersion> users = this.userRepository.all(1, UserNameAndVersion.class, PageRequest.of(0, 10)); // ����ʱָ����������
			List<User> users = this.userRepository.all(1, User.class, PageRequest.of(0, 10));  // ����ʱָ����������

