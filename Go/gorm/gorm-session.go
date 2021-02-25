--------------------
session
--------------------
	# �Ự�ĸ���
		* Session/WithContext/Debug ���������½�һ���Ự
		* db������ʽ���ã����½�һ���Ự


	# ͨ�� Session �´���һ���Ự
		func (db *DB) Session(config *Session) *DB

	
	# type Session struct {
			DryRun                   bool		
			PrepareStmt              bool
			NewDB                    bool
				* ����֮ǰ������

			SkipHooks                bool
				* �������ӳ�

			SkipDefaultTransaction   bool
				* �Ƿ�ر�Ĭ������
				* Ĭ�� GORM ����������ִ��д����������������¡�ɾ����

			DisableNestedTransaction bool
				* �Ƿ����Ƕ������

			AllowGlobalUpdate        bool
				* �Ƿ�����ȫ�ָ���/ɾ��

			FullSaveAssociations     bool
				* ���������¼�¼ʱ���Ƿ�����������Ķ���

			QueryFields              bool
				* ������ʱ���Ƿ�����������������

			Context                  context.Context
				* ָ��Context

			Logger                   logger.Interface
				* ָ����־

			NowFunc                  func() time.Time
				*  GORM ��ȡ��ǰʱ���ʵ��

			CreateBatchSize          int
				* ���������С
		}


	# DryRun
		* �������ģʽ������ִ��SQL
			stmt := db.Session(&Session{DryRun: true}).First(&user, 1).Statement
			stmt.SQL.String() //=> SELECT * FROM `users` WHERE `id` = $1 ORDER BY `id`	// �������ɵ����
			stmt.Vars         //=> []interface{}{1}										// ���ı���
		

		* ִ���������ɵ�SQL
			// ע�⣺SQL ���������ܰ�ȫ��ִ�У�GORM ������������־�������ܵ��»� SQL ע��
			db.Dialector.Explain(stmt.SQL.String(), stmt.Vars...)
			// SELECT * FROM `users` WHERE `id` = 1

	# PreparedStmt 
		* �Ƿ�Ҫ����SQLԤ���룬�������Ч��

