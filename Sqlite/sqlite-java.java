------------------------
java					|		
------------------------
	# jdbc����
		<!-- https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc -->
		<dependency>
			<groupId>org.xerial</groupId>
			<artifactId>sqlite-jdbc</artifactId>
			<version>3.23.1</version>
		</dependency>


	# ���Ӵ���
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
		
		* ���test.bd������,������Ŀ�ĸ�Ŀ¼������һ�� test.db �ļ�
		* ����ʹ��classpath:test.db,�����ļ�Ŀ¼��ָ��db �ļ�

	
