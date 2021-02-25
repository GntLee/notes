--------------------------
ԭ��SQL
--------------------------
	# ԭ��API
		* ����
			func (db *DB) Raw(sql string, values ...interface{}) (tx *DB)
		
		* �޸�
			func (db *DB) Exec(sql string, values ...interface{}) (tx *DB)
		
	
	# ԭ��API֧�ִ󲿷�orm�Ĺ���
		* ��������
		* map��Ϊ����������
		* �ṹ����Ϊ����������
	
	
	# DryRun ģʽ
		* ��ȡ��orm����ִ�е�SQL�����ǲ�����ִ�У����ڵ���
			stmt := db.Session(&Session{DryRun: true}).First(&user, 1).Statement
			stmt.SQL.String() //=> SELECT * FROM `users` WHERE `id` = $1 ORDER BY `id`	// ����ִ�е�SQL
			stmt.Vars         //=> []interface{}{1}										// ���Ĳ���
		
	
	# ��ȡ��Row/Rows�������Լ�����������
		func (db *DB) Rows() (*sql.Rows, error)
		func (db *DB) Row() *sql.Row
	
		
		* gorm�ṩ�������rows�Ŀ�ݷ�װ����
			func (db *DB) ScanRows(rows *sql.Rows, dest interface{}) error 
		
		* ����Row�ķ�װ������ʹ�� Scan
			db.Raw("...", "...").Scan()
		
