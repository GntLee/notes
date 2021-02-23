-------------------------
grom�Ĵ���
-------------------------
	# ͨ�� gorm.Open ����
		func Open(dialector Dialector, config *Config) (db *DB, err error)
		dialector
			* ָ��SQL����ʵ��
		config
			* ������Ϣ
		
	# ʹ���Ѿ����ڵ�����Դ����
		import (
		  "database/sql"
		  "gorm.io/gorm"
		)
		// �Լ�����DB
		sqlDB, err := sql.Open("mysql", "mydb_dsn")
		// ����GROM
		gormDB, err := gorm.Open(mysql.New(mysql.Config{
		  Conn: sqlDB,	// ͨ���������Connָ������Դ
		}), &gorm.Config{})
	
	# ���Ե�ʹ�ã�MYSQLΪ����
		* �������Ӵ�������
			import "gorm.io/driver/mysql"
			const (
				host string = "localhost"		// ����
				port int = 3306					// �˿�
				db string = "demo"				// ���ݿ�
				username string = "root"		// �û���
				password string = "root"		// ����
			)

			mysql.Open(fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8mb4&parseTime=True&loc=Local", username, password, host, port, db))
		
		* �������ô�������
			mysql.New(mysql.Config{
			  DSN: fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8mb4&parseTime=True&loc=Local", username, password, host, port, db), // DSN data source name
			  DefaultStringSize: 256, // string �����ֶε�Ĭ�ϳ���
			  DisableDatetimePrecision: true, // ���� datetime ���ȣ�MySQL 5.6 ֮ǰ�����ݿⲻ֧��
			  DontSupportRenameIndex: true, // ����������ʱ����ɾ�����½��ķ�ʽ��MySQL 5.7 ֮ǰ�����ݿ�� MariaDB ��֧������������
			  DontSupportRenameColumn: true, // �� `change` �������У�MySQL 8 ֮ǰ�����ݿ�� MariaDB ��֧����������
			  SkipInitializeWithVersion: false, // ���ݵ�ǰ MySQL �汾�Զ�����
			}
	
	# config
		type Config struct {
			SkipDefaultTransaction bool
				* ����Ĭ������Ĭ�������GORM ����������ִ��д����������������¡�ɾ����

			NamingStrategy schema.Namer
				* ��������Լ��
				* Namer �ӿ�
					type Namer interface {
						TableName(table string) string
						ColumnName(table, column string) string
						JoinTableName(table string) string
						RelationshipFKName(Relationship) string
						CheckerName(table, column string) string
						IndexName(table, column string) string
					}
				* Ĭ�ϵ�һ��ʵ������ schema.NamingStrategy
					type NamingStrategy struct {
						TablePrefix   string			// ������ǰ׺
						SingularTable bool				// ʹ�õ�������
						NameReplacer  *strings.Replacer	// ��תΪ���ݿ�����֮ǰ��ʹ��NameReplacer���Ľṹ/�ֶ����ơ�
					}
				
			FullSaveAssociations bool
			Logger logger.Interface
				* ��־��¼��

			NowFunc func() time.Time
				* ���Ĵ���ʱ��ʹ�õĺ���
			
			DryRun bool
				* ���� SQL ����ִ�У���������׼����������ɵ� SQL
			
			PrepareStmt bool
				*  ��ִ���κ� SQL ʱ���ᴴ��һ�� prepared statement �����仺�棬����ߺ�����Ч��
			
			DisableAutomaticPing bool
				* ����ɳ�ʼ����GORM ���Զ� ping ���ݿ��Լ�����ݿ�Ŀ�����

			DisableForeignKeyConstraintWhenMigrating bool
				* �� AutoMigrate �� CreateTable ʱ��GORM ���Զ��������Լ������Ҫ���ø����ԣ��ɽ�������Ϊ true

			DisableNestedTransaction bool
				* ��ֹǶ������

			AllowGlobalUpdate bool
				* ����ȫ�� update/delete
			
			QueryFields bool
			CreateBatchSize int
			ClauseBuilders map[string]clause.ClauseBuilder
			ConnPool ConnPool
			Dialector
			Plugins map[string]Plugin
		}
	
	# ���ӳص�����
		* ����ͨ�� DB() ������ȡ��ԭʼ�����ӳض��󣬽��гز���������
			func (db *DB) DB() (*sql.DB, error)

			// SetMaxIdleConns �����������ӳ��п������ӵ����������
			sqlDB.SetMaxIdleConns(10)

			// SetMaxOpenConns ���ô����ݿ����ӵ����������
			sqlDB.SetMaxOpenConns(100)

			// SetConnMaxLifetime ���������ӿɸ��õ����ʱ�䡣
			sqlDB.SetConnMaxLifetime(time.Hour)
	

	# Demo
		import (
			"fmt"
			"gorm.io/driver/mysql"
			"gorm.io/gorm"
			"gorm.io/gorm/schema"
			"log"
		)

		func main(){
			const (
				host string = "localhost"		// ����
				port int = 3306					// �˿�
				db string = "demo"				// ���ݿ�
				username string = "root"		// �û���
				password string = "root"		// ����
			)
			gormDB, err := gorm.Open(mysql.Open(fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8mb4&parseTime=True&loc=Local", username, password, host, port, db)),
				&gorm.Config{
					NamingStrategy: schema.NamingStrategy{
						SingularTable: true,		// �����Ƶ���
					},
				})
			if err != nil {
				log.Fatal(err)
			}

			log.Println(gormDB.Name())
		}