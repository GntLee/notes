-------------------
˵��
-------------------
	# ������Spring�е�����ʽ���ﴦ��
	# ���������������У����񷽷�ֱ�ӵ��໥���ã��ᱣ֤���з�����ʹ�õ�ͬһ������
	# ���񷽷�֧�ֵݹ����
	# ���񷽷��Ĺ̶�����ģ��
		1. ��ȡ����
		2. ��defer�лع�����
		3. ִ��ҵ�񷽷�/����������ҵ�񷽷�
		4. �ύ����


-------------------
ʵ��
-------------------
	# Dao�����
		package dao
		import (
			"context"
			"database/sql"
			"fmt"
			"gorm.io/driver/mysql"
			"gorm.io/gorm"
			"gorm.io/gorm/schema"
			"log"
		)

		var (
			ctxKeySession = "_db_session"	// ��ctx�д洢���ݿ�����Session
			ctxKeyDepth = "_db_depth"		// ��ctx�д洢�������
		)


		// ȫ�����ݿ�
		var DB *gorm.DB

		// ��ʼ�����ݿ�
		func init (){
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
					QueryFields: false,				// ����ʱ�г�������
					PrepareStmt: true,				// ����Ԥ����SQL
				})
			if err != nil {
				log.Fatal(err)
			}

			DB = gormDB.Debug()
		}

		// ��ctx��ȡ���ݿ����ӣ���������ڣ��򴴽��µ�
		func GetSession(ctx *context.Context, txOptions *sql.TxOptions) *gorm.DB {
			var session, ok = (*ctx).Value(ctxKeySession).(*gorm.DB)
			if ok {
				*ctx = context.WithValue(*ctx, ctxKeyDepth, (*ctx).Value(ctxKeyDepth).(int) + 1)
				log.Printf("��ȡ�Ѿ����ڵ�Session: %d\n", (*ctx).Value(ctxKeyDepth).(int))
			} else {
				log.Println("�������µ�Session")
				session = DB.Begin(txOptions)
				*ctx = context.WithValue(*ctx, ctxKeySession, session)
				*ctx = context.WithValue(*ctx, ctxKeyDepth, 0)
			}
			return session
		}

		// ���Իع���������Ƕ�����ã������
		func TryRollback(ctx context.Context)  {
			if depth, ok := ctx.Value(ctxKeyDepth).(int); ok {
				if depth == 0 {
					if session, ok := ctx.Value(ctxKeySession).(*gorm.DB); ok {
						log.Printf("����ع���%d\n", depth)
						session.Rollback()
					} else {
						//TODO û�ж�ȡ��Session
					}
				}
			} else {
				// TODO û��ȡ��Depth
			}
		}

		// �����ύ��������Ƕ�����������
		func TryCommit(ctx context.Context){
			if depth, ok := ctx.Value(ctxKeyDepth).(int); ok {
				if depth == 0 {
					if session, ok := ctx.Value(ctxKeySession).(*gorm.DB); ok {
						log.Printf("�����ύ��%d\n", depth)
						session.Commit()
					} else {
						//TODO û�ж�ȡ��Session
					}
				}
			} else {
				// TODO û��ȡ��Depth
			}
		}


	# ҵ���ĵ���
		func main() {
			// ��ڷ�������ʹ�ã�context.Background()
			if err := CreateUser(context.Background(), 3); err != nil {
				log.Printf("ִ���쳣��:%s\n", err.Error())
			}
		}

		func Server(ctx context.Context) (interface{}, error){
			// 1, ��ȡ����
			db := dao.GetSession(&ctx, nil)
			// 2, ʼ�ջع��������Ѿ��ύ��������ٴ�ִ�лع������ݲ�����Ӱ��
			defer func() {
				dao.TryRollback(ctx)
			}()

			// ִ��ҵ���߼������ߵ���������ҵ�񷽷�
			var now time.Time
			if err := db.Raw("SELECT NOW()").Scan(&now).Error; err != nil {
				return nil, err
			}

			// 3, û���쳣���ύ����
			dao.TryCommit(ctx)

			// ����ҵ����
			return "result", nil
		}