----------------------
����
----------------------
	# ����
		

----------------------
����
----------------------
	# ����
		JPAUpdateClause jpaUpdateClause = queryFactory
			.update(QUser.user)
			.set(QUser.user.name, "new Value")
			.where(QUser.user.id.eq(15));
		long result = jpaUpdateClause.execute();
		
----------------------
ɾ��
----------------------
	# ɾ��
		JPADeleteClause jpaDeleteClause = queryFactory
					.delete(QUser.user)
					.where(QUser.user.id.in(5, 6, 9, 8, 10));  // ����ɾ��
		long result = jpaDeleteClause.execute();
		

	

