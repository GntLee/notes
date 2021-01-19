-------------------
WHERE �����Ӳ�ѯ
-------------------
	# WHERE�����е��е��м���
		queryFactory.selectFrom(QUser.user)
				.where(QUser.user.id.eq(
					JPAExpressions.select(QUser.user.id).from(QUser.user).where(QUser.user.name.eq("KevinBlandy")))
				)
				.fetch();
	
	# WHERE�����ж��е���
		where(QUser.user.id.in(
				JPAExpressions.select(QUser.user.id).from(QUser.user).where(...))
			)
		
		* ������in������н��
		
	# WHERE������ exists ����
		QUser qUser = QUser.user;
		queryFactory.select(qUser)
				.from(qUser)
				.where(JPAExpressions.selectOne().from(QUserRole.userRole)
					.where(QUserRole.userRole.userId.eq(qUser.id).and(QUserRole.userRole.roleId.eq(1)))
					.exists()
				)
				.fetch();
		

-------------------
������Ӳ�ѯ
-------------------
	# count����
		QCategory qCategory = QCategory.category;
		QVideoCategory qVideoCategory = QVideoCategory.videoCategory;

		NumberExpression<Long> numberExpression = qVideoCategory.categoryId.count();
		
		QBean<CategoryDTO> categoryQBean = Projections.bean(CategoryDTO.class, 
				qCategory.id, qCategory.title, qCategory.poster, qCategory.describe, qCategory.sorted, qCategory.createdDate);
		
		
		JPAQuery<Tuple> jpaQuery = query.select(categoryQBean, JPAExpressions.select(numberExpression)
					.from(qVideoCategory)
					.where(qVideoCategory.categoryId.eq(qCategory.id)))
				.from(qCategory);
	
	# exists ����
		QVideo qVideo = QVideo.video;
		QVideoTag qVideoTag = QVideoTag.videoTag;

		// �Ӳ�ѯ
		BooleanExpression exists = JPAExpressions.selectOne().from(qVideoTag).where(qVideoTag.videoId.eq(qVideo.id)).fetchAll().exists();

		List<Tuple> tuples = this.jpaQueryFactory.select(qVideo, exists).from(qVideo).fetch();
		for (Tuple tuple : tuples) {
			// ��ȡ�Ӳ�ѯ���
			boolean result = tuple.get(exists);
		}

		* ���� exists �Ӳ�ѯ������Ƕ���� Projections.bean() �У����������� select ������
			this.jpaQueryFactory.select(Projections.bean(VideoDTO.class, qVideo.id, qVideo.title, exists)).from(qVideo).fetch(); // �쳣

	
	# ������Ƿ�ΪNull����(�㲻���Ӳ�ѯ)
		QPayMode qPayMode = QPayMode.payMode;
		QPayChannel qPayChannel = QPayChannel.payChannel;
	
		JPAQuery<PayModeDTO> jpaQuery = this.jpaQueryFactory.select(Projections.bean(PayModeDTO.class, qPayMode.id,
				qPayMode.name, qPayMode.enabled, qPayChannel.payModeId.isNotNull().as("selected")))
			.from(qPayMode)
			
			.leftJoin(qPayChannel).on(qPayMode.id.eq(qPayChannel.payModeId).and(qPayChannel.goodsId.eq(goodsId)))
			
			.where(qPayMode.deletedDate.eq(SystemProperties.NOT_DELETED).and(qPayMode.payTypeId.eq(1)))
			.orderBy(new OrderSpecifier<>(Order.DESC, qPayMode.sorted), new OrderSpecifier<>(Order.DESC, qPayMode.createdDate));
		
		List<PayModeDTO> payModes = jpaQuery.fetch();