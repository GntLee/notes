-------------------------
����					 |
-------------------------
	# �������
		Connection connection = sql2o.beginTransaction();
		try{
			// TODO ʹ�ø�Connection��ɶ���������

		}catch (Exception e) {
			if(connection != null) {
				connection.rollback();
			}
		}finally {
			if(connection != null){
				connection.close();
			}
		}

		* beginTransaction() ��ȡһ����������
		* �����ڻ�ȡ�������ӵ�ʱ��,��������ĸ��뼶��
			beginTransaction(int isolationLevel)
			java.sql.Connection
				int TRANSACTION_NONE             = 0;
				int TRANSACTION_READ_UNCOMMITTED = 1;
				int TRANSACTION_READ_COMMITTED   = 2; (Sql2oĬ��)
				int TRANSACTION_REPEATABLE_READ  = 4;
				int TRANSACTION_SERIALIZABLE     = 8;
		
		* Ҳ���Դ�ָ��������Դ��ȡ����������
			 beginTransaction(ConnectionSource connectionSource)
			 beginTransaction(ConnectionSource connectionSource, int isolationLevel)

			 public interface ConnectionSource {
				java.sql.Connection getConnection() throws SQLException;
			 }

			 * һ������,�������Ŀ�ܹ�������
			
		
		* ʹ�� rollback(); �ع�����,Ҳ����ʹ��: rollback(boolean closeConnection) ������,�Ƿ��ڻع�ʱ�ر�����
	
