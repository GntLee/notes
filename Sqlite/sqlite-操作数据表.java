------------------------
������					|
------------------------
	# ����
		CREATE TABLE user(
		   id				INT		PRIMARY	KEY     NOT NULL,
		   name				TEXT    NOT NULL,
		   age				INT     NOT NULL,
		   address			CHAR(50),
		   salary			REAL,
		   birthday			INTEGER,
		   create_date		TEXT
		);

		* �����¼:insert into `user`(`id`,`name`,`age`,`address`,`salary`,`birthday`,`create_date`) values(1,'KevinBlandy',24,'����',5115415.5,1529570646967,'2018-06-21 16:44:19');

	# �鿴���Ҫ
		.schema [tables]
	
	
	# �鿴���б�
		.tables
	
	# ɾ����
		drop table [tablename]
