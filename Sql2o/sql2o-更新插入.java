-----------------------
insert/update		   |
-----------------------
	# �����¼
		try(Connection connection = sql2o.open()){
			Connection executeUpdate = connection.createQuery("INSERT INTO `user`(`id`,`name`,`create_date`) VALUES(NULL, :name, :createDate);")
					.addParameter("name", "KevinBlandy")
					.addParameter("createDate", LocalDateTime.now())
					.executeUpdate();

			// �����յ���Ӱ������
			int result = executeUpdate.getResult();
			// ������������key
			Object key = executeUpdate.getKey();
			Integer integerKey = executeUpdate.getKey(Integer.class);

			Object[] keys = executeUpdate.getKeys();
			List<Long> longKeys = executeUpdate.getKeys(Long.class);

			System.out.println(result);
			System.out.println(key);
		}

		* ͨ�� addParameter(String name, final String value) ���ռλ������
		* addParameter() �� N �����ط���,��Ӧ��ͬ����������

		* ʹ�� executeUpdate() ִ�в���/�޸�
		
		* ͨ�� getResult(); ��ȡ����Ӱ��ĺ���
		* ͨ�� getKey(); ��ȡ���������ֶε�ֵ,Ҳ����ʹ�� getKey(Class<?> clzz); ��ת��Ϊ��Ҫ����������

		* ������ڶ���������ֶ�,��ô����ʹ��:Object[] getKeys() / List<Long> getKeys(Long.class); ����ȡ

	
	# �޸ļ�¼
		try(Connection connection = sql2o.open()){
			Connection executeUpdate = connection.createQuery("UPDATE `user` SET `name` = :name WHERE `id` = :id")
					.addParameter("id",13)
					.addParameter("name", "KevinBlandy1")
					.executeUpdate();

			// �����յ���Ӱ������
			int result = executeUpdate.getResult();
			System.out.println(result);
		}

		* ʹ�� executeUpdate() ִ�в���/�޸�
		* ͨ�� getResult(); ��ȡ����Ӱ��ĺ���
	
	# �󶨶������ռλ����ӳ���ϵ
		try(Connection connection = sql2o.open()){
			
			// ��������
			User user = new User();
			user.setId(null);
			user.setName("SpringBoot��������");
			user.setCreateDate(LocalDateTime.now());

			Connection executeUpdate = connection.createQuery("INSERT INTO `user`(`id`, `name`, `create_date`) VALUES(:id, :name, :createDate)")
					// ӳ���������Ե�SQL�е�ռλ��
					.bind(user)
					.executeUpdate();

			// �����յ���Ӱ������
			int result = executeUpdate.getResult();

			// ��������id
			Integer id = executeUpdate.getKey(Integer.class);

			System.out.println("result = " + result + ", id = " + id);
		}

		* :prop �Ƕ��������
	

-----------------------
��������			   |
-----------------------
	# ��������
		try(Connection connection = sql2o.open()){

			Query query = connection.createQuery("INSERT  INTO `user`(`id`, `name`, `create_Date`) VALUES(:id, :name, :createDate);");

			for(int x = 0 ;x < 100 ; x++){

				// ִ�����ݰ�
				query.addParameter("id",x);
				query.addParameter("name","name_" + x);
				query.addParameter("createDate",LocalDateTime.now());

				// ��ӵ�������
				query.addToBatch();
			}

			// ִ����������
			Connection executeBatch = query.executeBatch();

			// ��ȡ����������Ľ��
			int[] result = executeBatch.getBatchResult();

			System.out.println(Arrays.toString(result));
		}

		* addToBatch() ��ӵ�������
		* executeBatch() ִ��������
		* getBatchResult() ��ȡ����������
	
		
		* �ŵ㣺
			SQL���Ԥ������
			����ͬһ�����͵�SQL���,���ñ�д�ܶ���

		* ȱ�㣺
			���ܷ��Ͳ�ͬ���͵�SQL���