-----------------
sql
-----------------
	# SQL��������Ҫ���룬��Javaһ�������ص��ڴ����
		import (
			_ "github.com/go-sql-driver/mysql"
		)
	


-----------------
var
-----------------
	# �쳣��Ϣ
		var ErrConnDone = errors.New("sql: connection is already closed")
			* ����һ���ر�
		
		var ErrNoRows = errors.New("sql: no rows in result set")
			* �ս���쳣
				var name string
				err = db.QueryRow("select name from users where id = ?", 1).Scan(&name)
				if err != nil {
					if err == sql.ErrNoRows {
						// û�������κ�����
					} else {
						log.Fatal(err)
					}
				}
				fmt.Println(name)
		
		var ErrTxDone = errors.New("sql: transaction has already been committed or rolled back")
			* �쳣�Ѿ��ع������ύ


-----------------
type
-----------------
	# type ColumnType struct {
		}

		* ������
		func (ci *ColumnType) DatabaseTypeName() string
			* �������ݿ������������
		
		func (ci *ColumnType) DecimalSize() (precision, scale int64, ok bool)
			* ����С�����͵ı����;��ȡ���������û�֧��ok�򷵻�false��

		func (ci *ColumnType) Length() (length int64, ok bool)
			* Length ���ؿɱ䳤�������ͣ����ı��Ͷ������ֶ����ͣ��������ͳ��ȡ�
			* ������ͳ����������Ƶģ���ô��ֵ����math.MaxInt64���κ����ݿ�������Ȼ���ã���
			* ��������Ͳ��ǿɱ䳤�ȵģ�����int�����������������֧�֣�okΪfalse��

		func (ci *ColumnType) Name() string
			* ������

		func (ci *ColumnType) Nullable() (nullable, ok bool)
			* Nullable�������Ƿ�Ϊ�ա�
			* ���һ����������֧��������ԣ���ôok��Ϊfalse��

		func (ci *ColumnType) ScanType() reflect.Type
			* �����ͣ���Ӧ��Go����
			* ���һ����������֧��������ԣ�ScanType������һ���սӿڵ����͡�
	
	# type Conn struct {
		}
	
		* ���ݿ�����

		func (c *Conn) BeginTx(ctx context.Context, opts *TxOptions) (*Tx, error)
		func (c *Conn) Close() error
		func (c *Conn) ExecContext(ctx context.Context, query string, args ...interface{}) (Result, error)
		func (c *Conn) PingContext(ctx context.Context) error
		func (c *Conn) PrepareContext(ctx context.Context, query string) (*Stmt, error)
		func (c *Conn) QueryContext(ctx context.Context, query string, args ...interface{}) (*Rows, error)
		func (c *Conn) QueryRowContext(ctx context.Context, query string, args ...interface{}) *Row
		func (c *Conn) Raw(f func(driverConn interface{}) error) (err error)
	
	# type DB struct {
		}
		
		* ���ݿ����ӳ�

		func Open(driverName, dataSourceName string) (*DB, error)
			* ����DB���Լ�����������������Ҫ�ȱ����ؽ��ڴ�
				db, err := sql.Open("mysql", "user:password@/dbname")

		func OpenDB(c driver.Connector) *DB
			* ������������DB

		func (db *DB) Begin() (*Tx, error)
		func (db *DB) BeginTx(ctx context.Context, opts *TxOptions) (*Tx, error)
			* ��ʼ����
			
		func (db *DB) Close() error
			* �ر�����Դ

		func (db *DB) Conn(ctx context.Context) (*Conn, error)
		func (db *DB) Driver() driver.Driver

		func (db *DB) Exec(query string, args ...interface{}) (Result, error)
		func (db *DB) ExecContext(ctx context.Context, query string, args ...interface{}) (Result, error)
			* ִ�в�����һ�����޸�ɾ�����������SQL��ȡ��ִ�н��
			* ���ִ���˼��������������쳣��result��2��ֵ����0

		func (db *DB) Ping() error
			* ����ping����������Ƿ�OK
			* Open()�����������ᴴ�����ӣ��������ӳټ��صģ�����ͨ������������ж������Ƿ�����

		func (db *DB) PingContext(ctx context.Context) error
			* ��ȡ���ݿ�����

		func (db *DB) Prepare(query string) (*Stmt, error)
		func (db *DB) PrepareContext(ctx context.Context, query string) (*Stmt, error)
			* ����SQLΪԤ�������

		func (db *DB) Query(query string, args ...interface{}) (*Rows, error)
		func (db *DB) QueryContext(ctx context.Context, query string, args ...interface{}) (*Rows, error)����
			* ִ�в�ѯ����ȡ���ж��н����

		func (db *DB) QueryRow(query string, args ...interface{}) *Row
		func (db *DB) QueryRowContext(ctx context.Context, query string, args ...interface{}) *Row
			* ִ�в�ѯ����ȡ���ж��н����
			* ���������ж��У����ֻȡ��һ�У������쳣

		func (db *DB) SetConnMaxIdleTime(d time.Duration)
			* ����������ʱ��
		func (db *DB) SetConnMaxLifetime(d time.Duration)
			* ���������ʱ��
		func (db *DB) SetMaxIdleConns(n int)
			* ��������������
		func (db *DB) SetMaxOpenConns(n int)
			* �����������
		func (db *DB) Stats() DBStats
	
	# type DBStats struct {
			MaxOpenConnections int // Maximum number of open connections to the database.

			// Pool Status
			OpenConnections int // The number of established connections both in use and idle.
			InUse           int // The number of connections currently in use.
			Idle            int // The number of idle connections.

			// Counters
			WaitCount         int64         // The total number of connections waited for.
			WaitDuration      time.Duration // The total time blocked waiting for a new connection.
			MaxIdleClosed     int64         // The total number of connections closed due to SetMaxIdleConns.
			MaxIdleTimeClosed int64         // The total number of connections closed due to SetConnMaxIdleTime.
			MaxLifetimeClosed int64         // The total number of connections closed due to SetConnMaxLifetime.
		}

		* ���ݿ�״̬
	
	# type IsolationLevel int
		* ������뼶��

		const (
			LevelDefault IsolationLevel = iota
			LevelReadUncommitted
			LevelReadCommitted
			LevelWriteCommitted
			LevelRepeatableRead
			LevelSnapshot
			LevelSerializable
			LevelLinearizable
		)
		func (i IsolationLevel) String() string

	
	# type NamedArg struct {
			Name string
			Value interface{} // contains filtered or unexported fields
		}
		* SQL�еľ�������
		func Named(name string, value interface{}) NamedArg
	
	# type NullBool struct {
			Bool  bool
			Valid bool // Valid is true if Bool is not NULL
		}
		func (n *NullBool) Scan(value interface{}) error
		func (n NullBool) Value() (driver.Value, error)
	
	# type NullFloat64 struct {
			Float64 float64
			Valid   bool // Valid is true if Float64 is not NULL
		}
		func (n *NullFloat64) Scan(value interface{}) error
		func (n NullFloat64) Value() (driver.Value, error)
	
	# type NullInt32 struct {
			Int32 int32
			Valid bool // Valid is true if Int32 is not NULL
		}
		func (n *NullInt32) Scan(value interface{}) error
		func (n NullInt32) Value() (driver.Value, error)
	
	# type NullInt64 struct {
			Int64 int64
			Valid bool // Valid is true if Int64 is not NULL
		}
		func (n *NullInt64) Scan(value interface{}) error
		func (n NullInt64) Value() (driver.Value, error)
	
	# type NullString struct {
			String string
			Valid  bool // Valid is true if String is not NULL
		}
		func (ns *NullString) Scan(value interface{}) error
		func (ns NullString) Value() (driver.Value, error)
	
	# type NullTime struct {
			Valid bool // Valid is true if Time is not NULL
		}
		func (n *NullTime) Scan(value interface{}) error
		func (n NullTime) Value() (driver.Value, error)
	
	# type Out struct {
			Dest interface{}
			In bool // contains filtered or unexported fields
		}
	
	# type RawBytes []byte
		* ��ԭʼ���ֽ�����

	# type Result interface {
			LastInsertId() (int64, error)
				* ����ID�����쳣��Ϣ

			RowsAffected() (int64, error)
				* ��Ӱ������������쳣��Ϣ
		}
		* SQL���޸�ִ�н��
	
	# type Row struct {
		}

		* ���ж�������

		func (r *Row) Err() error
			* �쳣��Ϣ

		func (r *Row) Scan(dest ...interface{}) error
	
	# type Rows struct {
		}

		* ���ж�������

		func (rs *Rows) Close() error
			* �ر�Rows���������Ҫ�ǵ�ִ��
			* Rows�ᱣ�����ݿ����ӣ�ֱ��sql.Rows�ر�
				_, err := db.Query("DELETE FROM users") // ���ֲ������ᵼ�����Ӳ��ᱻ�ͷ�

		func (rs *Rows) ColumnTypes() ([]*ColumnType, error)
		func (rs *Rows) Columns() ([]string, error)
		func (rs *Rows) Err() error
		func (rs *Rows) Next() bool
		func (rs *Rows) NextResultSet() bool
		func (rs *Rows) Scan(dest ...interface{}) error

		* Rows�ᱣ�����ݿ����ӣ�ֱ��sql.Rows�ر�
	
	# type Scanner interface {
			Scan(src interface{}) error
		}
	# type Stmt struct {
		}
	
		* Ԥ����SSQL

		func (s *Stmt) Close() error
		func (s *Stmt) Exec(args ...interface{}) (Result, error)
		func (s *Stmt) ExecContext(ctx context.Context, args ...interface{}) (Result, error)
			* ִ���޸�SQL������������ȡ�����

		func (s *Stmt) Query(args ...interface{}) (*Rows, error)
		func (s *Stmt) QueryContext(ctx context.Context, args ...interface{}) (*Rows, error)
			* ��������ִ�м�������ȡ���ж��н��

		func (s *Stmt) QueryRow(args ...interface{}) *Row
		func (s *Stmt) QueryRowContext(ctx context.Context, args ...interface{}) *Row
			* ��������ִ�м�������ȡ���ж��н��
	
	# type Tx struct {
		}
		* ����
		func (tx *Tx) Commit() error
			* �ύ����

		func (tx *Tx) Exec(query string, args ...interface{}) (Result, error)
		func (tx *Tx) ExecContext(ctx context.Context, query string, args ...interface{}) (Result, error)
			* ִ��SQL����ȡ���

		func (tx *Tx) Prepare(query string) (*Stmt, error)
		func (tx *Tx) PrepareContext(ctx context.Context, query string) (*Stmt, error)
			* ִ��Ԥ���룬����*Stmt

		func (tx *Tx) Query(query string, args ...interface{}) (*Rows, error)
		func (tx *Tx) QueryContext(ctx context.Context, query string, args ...interface{}) (*Rows, error)
			* ִ�м������ض��ж��н����

		func (tx *Tx) QueryRow(query string, args ...interface{}) *Row
		func (tx *Tx) QueryRowContext(ctx context.Context, query string, args ...interface{}) *Row
			* ִ�м������ص��ж��н����

		func (tx *Tx) Rollback() error
			* �ع�����
		
		func (tx *Tx) Stmt(stmt *Stmt) *Stmt
		func (tx *Tx) StmtContext(ctx context.Context, stmt *Stmt) *Stmt


-----------------
func
-----------------
	func Drivers() []string
	func Register(name string, driver driver.Driver)


-----------------
demo
-----------------
	