-----------------------------
�����ķ�ʽ					 |
----------------------------
	# JPA���ӿڶ���ļ�������
	# ���������������
	# JPQL����
	# ����SQL����
	# Specification ��̬��ѯ

-----------------------------
JPA���ӿڶ���ļ�������		 |
----------------------------

    List<UserEntity> findAllById(Iterable<Integer> integers);
    Optional<UserEntity> findById(Integer integer);
	UserEntity getOne(Integer integer);
	
	Optional<UserEntity> findOne(Specification<UserEntity> spec);
    <S extends UserEntity> Optional<S> findOne(Example<S> example);

	List<UserEntity> findAll();
    List<UserEntity> findAll(Sort sort);
    Page<UserEntity> findAll(Pageable pageable);

	<S extends UserEntity> List<S> findAll(Example<S> example);
    <S extends UserEntity> List<S> findAll(Example<S> example, Sort sort);
    <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable);

    List<UserEntity> findAll(Specification<UserEntity> spec);
    Page<UserEntity> findAll(Specification<UserEntity> spec, Pageable pageable);
    List<UserEntity> findAll(Specification<UserEntity> spec, Sort sort);
	
	long count();
	<S extends UserEntity> long count(Example<S> example);
    long count(Specification<UserEntity> spec); 

	<S extends UserEntity> boolean exists(Example<S> example);
	boolean existsById(Integer integer);
    
	
	* �޷Ǿ���, ��ҳ, Specification��̬����, Example����, Sort ������ϲ�ѯ
	* findOne ����������, getOne ���ӳټ���



----------------
���������Ŀ�
----------------
	1. ���е�ע��, Ҫô��������������, Ҫô�������� getter ������, ��Ҫ����
	2. ���еĹ��������Ե���, ˫�����, JSON�����л���ʱ��������ѭ������, ��Ҫ�Լ��ֶ�����(���json�����ֶ�)
	3. �����й���������, ��һ���ǲ��ô������������, @MappedBy Ҫע��
	4. ����ɾ���Ƚ�Σ��, ���鿼�����, ������ȫ����
	5. ��ͬ�Ĺ�����ϵ������, @JoinClumn ����� namereferencedColumnName �������˼�ǲ�һ����, ������Ū��, ���Ը��ݴ�ӡ������SQL������
	6. ��������Щ������ϵ��ʱ������ֱ���ڱ�����, ���������, Ȼ��ͨ���������ǽ��ܵĿ�������ֱ������, �������Լ����Լ����Ե�ʱ��
	

