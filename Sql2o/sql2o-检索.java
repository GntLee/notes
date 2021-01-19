-----------------------
select				   |
-----------------------
	# �����ļ���
		try(Connection connection = sql2o.open()){
			User user = connection.createQuery("SELECT * FROM `user`").executeAndFetchFirst(User.class);
		}

		* executeAndFetchFirst(Class<?> clazz) ,��װΪ���н����
		* ������ڶ�������,��ôֻȡ��һ��
	
	# �������ļ���
		try(Connection connection = sql2o.open()){
			List<User> users = connection.createQuery("SELECT * FROM `user` WHERE `create_date` >= :start")
				.addParameter("start",LocalDateTime.of(2019,4,12,7,21,53))
				.executeAndFetch(User.class);

			System.out.println(JSON.toJSONString(users));
		}

		* executeAndFetch(Class<?> clazz),��װΪ���ж��н����
		* ��SQL����ʹ�� ':name' ռλ
		* ͨ�� addParameter() api����������
	
	# �󶨶�������������
		try(Connection connection = sql2o.open()){

			User user = new User();
			user.setId(15);

			user = connection.createQuery("SELECT * FROM `user` WHERE `id` = :id")
					.addColumnMapping("create_date","createDate")
					.bind(user)
					.executeAndFetchFirst(User.class);

			System.out.println(user);
		}

		* ʹ�� bind(final Object pojo) �������ﵽһ������
		* :prop ���Ƕ��������
	
	# ͳ�Ƽ���
		try(Connection connection = sql2o.open()){
			List<Integer> count = connection.createQuery("SELECT COUNT(1) FROM `user`")
						.executeScalarList(Integer.class);
			System.out.println(JSON.toJSONString(count));
		}

		* ͨ�� executeScalarList(Class<?> clazz),����װͳ�ƵĽ������

		try(Connection connection = sql2o.open()){
			Long count = connection.createQuery("SELECT COUNT(1) FROM `user`")
						.executeScalar(Long.class);
			System.out.println(JSON.toJSONString(count));
		}

		* ͨ�� executeScalar(Class<?> clazz),����װͳ�Ƶĵ������
	
	# ʹ��Map��װ���ж��н����
		Table table = connection.createQuery("SELECT * FROM `user`")
					.executeAndFetchTable();

		// ��ȡ�������List,Listÿ��MapԪ�ض���һ�м�¼,ÿ��MapԪ�ص����ݶ��Ƕ��м�¼
		// Map��key��������,value�� sql ���͵����ݶ���
		List<Map<String,Object>> map = table.asList();

		// ÿһ��
		List<Row> rows = table.rows();

		// ÿһ��
		List<Column> columns = table.columns();

		// �����ı�����
		String name = table.getName();		

		* Row �����ʾһ�м�¼
			//����һ���м�¼,ת��ΪMap
			Map<String,Object> rowMap = row.asMap();

			//������������ȡ����ΪBigDecimal
			BigDecimal salary = row.getBigDecimal("salary");

			// �����±��ȡ����ΪInteger,��0��ʼ
			Integer id = row.getInteger(1);

			// ����������ȡ����Ϊָ�������ݶ�������
			// ��ʹ��ת����:Converter<T>
			String string = row.getObject("",String.class);

			// ��ȡ����ΪObject����
			Object result = row.getObject(1);

			* ���кܶ�: getXxxx(int index)/getXxxx(String name) �����ط���,����ת��Ϊ��ͬ����������
		
		* Column ��ʾһ�м�¼
			// �����ڵ�index,��0��ʼ
			Integer index = column.getIndex();
			// ����
			String name = column.getName();

			// ����������,JDBCType ö��ʵ����name()
			String type = column.getType();
	
	# ��������
		* �������ݼ�¼���ܴ�,һ���Ե�ȫ����ȡ���ڴ���ܻ���ɲ���Ҫ���鷳
		* ���Բ�ȡ���ֵ��������ķ���,�����εļ�������,�����εĴ���

		try(Connection connection = sql2o.open()){

			// һ������ദ���������
			final int MAXSIZE = 1;

			// ���������
			List<User> users = new ArrayList<>(MAXSIZE);

			ResultSetIterable<User> resultSetIterable = connection.createQuery("SELECT * FROM `user`")
					.executeAndFetchLazy(User.class);

			Iterator<User> iterator = resultSetIterable.iterator();
			
			while (iterator.hasNext()){

				User user = iterator.next();

				if(users.size() == MAXSIZE){
					//�Ѿ��������������,ִ��ҵ����
					users.stream().forEach(System.out::println);

					// ������Ϻ�,�������
					users.clear();
				}
				// ���Ԫ�ص�����
				users.add(user);
			}
		}

		* ʹ�� executeAndFetchLazy(Class<?> clazz) ��ȡ�����������
	
	# �������������ӳ��
		* ����������,���ڶ����в����ڵ�������,��ô���׳��쳣
			org.sql2o.Sql2oException: Could not map xxxx to any property.

		try(Connection connection = sql2o.open()){
			User user = connection.createQuery("SELECT * FROM `user`")
					.addColumnMapping("create_date","createDate")
					.executeAndFetchFirst(User.class);
				System.out.println(user);
		}
		
		* ʹ�� addColumnMapping(String columnName, String propertyName) ������������������������ӳ���ϵ
		* ��Ȼ,Ҳ�����Լ���SQL����ʹ��`AS` �����ķ�ʽ����������ӳ��
	
		* ����ͨ�� Sql2o ��������ȫ�ֵ�ӳ���ϵ,������ÿ�μ���������
			Map<String, String> colMaps = new HashMap<String,String>();
			colMaps.put("DUE_DATE", "dueDate");
			colMaps.put("DESC", "description");
			colMaps.put("E_MAIL", "email");
			colMaps.put("SHORT_DESC", "shortDescription");

			sql2o.setDefaultColumnMappings(colMaps);
	
