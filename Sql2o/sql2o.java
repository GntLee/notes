-------------------------
SQL2O					 |
-------------------------
	# Github
		https://github.com/aaberg/sql2o
	
		
	# Maven
		<dependency>
			<groupId>org.sql2o</groupId>
			<artifactId>sql2o</artifactId>
			<version>1.6.0</version>
		</dependency>


	# ���캯��
		Sql2o(String jndiLookup)
		Sql2o(String url, String user, String pass)
		Sql2o(String url, String user, String pass, Quirks quirks)
		Sql2o(DataSource dataSource)
		Sql2o(DataSource dataSource, Quirks quirks)
		
		jndiLookup
			* ��jndi��������Դ
		
		quirks
			* �����һЩ����