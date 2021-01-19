--------------------------------------------------
������QueryDsl�Ķ�̬���򹤾���
--------------------------------------------------
	private void setSort(String[] sorts, String[] orders, BiConsumer<String, Order> biConsumer) {
		if (sorts == null || sorts.length == 0) {
			return ;
		}
		for (int i = 0; i < sorts.length; i ++) {
			String sort = sorts[i];
			Order order = null;
			if (orders != null && i < orders.length) {
				order = Order.valueOf(orders[i].toUpperCase());
			} else {
				order = Order.ASC; // ʹ��Ĭ�ϵ�����
			}
			biConsumer.accept(sort, order);
		}
	}
	
	@GetMapping("/list")
	public Object list(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			
			@RequestParam(value = "sort", defaultValue = "createdDate") String[] sorts,
			@RequestParam(value = "order", defaultValue = "desc") String[] orders) {
		
		QueryResults<Merchant> results = merchantService.executeReadOnly(query -> {
			QMerchant qMerchant = QMerchant.merchant;
			JPAQuery<Merchant> jpaQuery = query.select(Projections.bean(Merchant.class,qMerchant.id, qMerchant.account, qMerchant.lastModifiedDate ,qMerchant.createdDate,qMerchant.enabled))
						.from(qMerchant)
						.offset((page - 1) * limit).limit(limit);
			
			setSort(sorts, orders, (key, order) -> {
				if (key.equals(qMerchant.account.getMetadata().getName())) {
					jpaQuery.orderBy(new OrderSpecifier<>(order, qMerchant.account));
				} else if (key.equals(qMerchant.createdDate.getMetadata().getName())) {
					jpaQuery.orderBy(new OrderSpecifier<>(order, qMerchant.createdDate));
				} else if (key.equals(qMerchant.lastModifiedDate.getMetadata().getName())) {
					jpaQuery.orderBy(new OrderSpecifier<>(order, qMerchant.lastModifiedDate));
				} else {
					// TODO �����ڵ������ֶ�
				}
			});
			
			return jpaQuery.fetchResults();
						
		});
		return Message.success(new PageResult(results));
	}

	// http://localhost:8080/merchant/list?sort=account,createdDate,lastModifiedDate&order=desc


--------------------------------------------------
Ҳ���Լ�д��һ�������࣬�������̫�࣬Ӱ������
--------------------------------------------------
	/**
	 * ����QueryDsl������
	 * @param sorts
	 * @param orders
	 * @param biConsumer
	 */
	public static  void setSort(String[] sorts, String[] orders, BiConsumer<String, com.querydsl.core.types.Order> biConsumer) {
		if (sorts == null || sorts.length == 0) {
			return ;
		}
		for (int i = 0; i < sorts.length; i ++) {
			String sort = sorts[i];
			com.querydsl.core.types.Order order = null;
			if (orders != null && i < orders.length) {
				order = com.querydsl.core.types.Order.valueOf(orders[i].toUpperCase());
			} else {
				order = com.querydsl.core.types.Order.ASC; // ʹ��Ĭ�ϵ�����
			}
			biConsumer.accept(sort, order);
		}
	}
	
	/**
	 * queryDsl ���������װ
	 * @param entityPathBase			�����ֶεĶ���
	 * @param properties				�������������
	 * @param orders					�������
	 * @return
	 */
	@Deprecated
	public static OrderSpecifier<?>[] queryDslSort(EntityPathBase<?> entityPathBase,String[] properties, String[] orders) {
		List<Expression<?>> expressions = Stream.of(properties).map(i -> {
			Field field;
			try {
				field = entityPathBase.getClass().getField(i);
				if (Modifier.isFinal(field.getModifiers()) && Modifier.isPublic(field.getModifiers())) {
					return (Expression<?>) field.get(entityPathBase);
				}
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}).filter(i -> i != null).collect(Collectors.toList());
		
		return queryDslSort(expressions.toArray(new Expression<?>[expressions.size()]), orders);
	}
	
	/**
	 * queryDsl ���������װ 
	 * @param expressions
	 * @param order
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static OrderSpecifier<?>[] queryDslSort(Expression<?>[] expressions, String[] orders) {
		if (expressions == null || expressions.length == 0) {
			return new OrderSpecifier[0];
		}
		OrderSpecifier<?>[] orderSpecifier = new OrderSpecifier[expressions.length];
		
		for (int x = 0; x < expressions.length; x++) {

			// �������
			com.querydsl.core.types.Order order = null;
			
			if (orders != null && orders.length > x) {
				try {
					order = com.querydsl.core.types.Order.valueOf(orders[x].toUpperCase().trim());	
				} catch (IllegalArgumentException e) {
					// throw new ServiceException(Message.fail(Message.Code.BAD_REQUEST, "�Ƿ���������ԣ�" + orders[x]));
					order = com.querydsl.core.types.Order.ASC;
				}
			}else {
				order = com.querydsl.core.types.Order.ASC;
			}
			
			orderSpecifier[x] = new OrderSpecifier(order, expressions[x], OrderSpecifier.NullHandling.NullsLast);
		}
		
		return orderSpecifier;
	}