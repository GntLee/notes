---------------------------------
JpaSpecificationExecutor
---------------------------------
	# һ��֧�ָ��Ӳ�ѯ�Ľӿ�
	# �ӿڷ���
		Optional<T> findOne(@Nullable Specification<T> spec);Optional<T> findOne(@Nullable Specification<T> spec);
		List<T> findAll(@Nullable Specification<T> spec);
		Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
		List<T> findAll(@Nullable Specification<T> spec, Sort sort);
		long count(@Nullable Specification<T> spec);
	
	
		