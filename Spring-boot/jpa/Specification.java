---------------------
Specification
---------------------
	# ��̬������ѯ
	# Specification
		static <T> Specification<T> not(@Nullable Specification<T> spec) 
		static <T> Specification<T> where(@Nullable Specification<T> spec)


		default Specification<T> and(@Nullable Specification<T> other)
		default Specification<T> or(@Nullable Specification<T> other)

		Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
			* Ψһ�ĳ��󷽷�
			
			root
				* ʵ�����, ����ͨ������ȡ������ֵ
			
			query
				* �������ɼ������

			criteriaBuilder
				* ����ƴ������
