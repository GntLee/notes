---------------------
sql
---------------------
	# ��������
		_ "github.com/go-sql-driver/mysql"
	


	# ռλ��
		* ��ͬ���Ե�ռλ����ͬ
		MySQL                    
			WHERE col = ?		VALUES(?, ?, ?)   
		PostgreSQL       
			WHERE col = $1		VALUES($1, $2, $3)
		Oracle
			WHERE col = :col	VALUES(:val1, :val2, :val3)