-----------------------
����ѡ���ֶ�
-----------------------
	# ͨ���ṹ�嶨��ͶӰ��ѯ
		type SimpleUser struct {
			NickName string
			Email string
		}
		var result = &SimpleUser{}
		db.Model(&model.User{}).Find(result, "id = ?", 1) //  SELECT `user`.`nick_name`,`user`.`email` FROM `user` WHERE id = 1
	
-----------------------
��
-----------------------
	# ������
		db.Model(&model.User{}).Clauses(clause.Locking{
			Strength: "UPDATE",
		}).Find(result)		// FOR UPDATE
	
	# ����
		db.Model(&model.User{}).Clauses(clause.Locking{
			Strength: "SHARE",
			Table: clause.Table{Name: clause.CurrentTable},
		}).Find(result, "id = ?", 1)		// LOCK IN SHARE MODE

-----------------------
�Ӳ�ѯ
-----------------------
	# �����е��Ӳ�ѯ
		var result []*model.User
		subQuery := db.Model(&model.User{}).Select("AVG(id)")
		db.Model(&model.User{}).Where("id >= (?)", subQuery).Find(&result)
		//  SELECT * FROM `user` WHERE id >= (SELECT AVG(id) FROM `user`)

		* where������䣬�жϵ��������󣬿�������һ���Ӳ�ѯ
	
	# ��ʱ���Ӳ�ѯ
		var result []*model.User
		db.Table("(?) AS `u`", db.Model(&model.User{}))
				.Select("`u`.`id`", "`u`.`nick_name`")
				.Where("`u`.`id` >= ?", 1).Find(&result)
		// SELECT `u`.`id`,`u.`nick_name` FROM (SELECT * FROM `user`) AS `u` WHERE `u`.`id` >= 1
		
	
		* Table�Ĳ�����䣬��������һ���Ӳ�ѯ

	# ���Ӳ�ѯ
		var result []*model.User
		db.Table("`user` AS `u`")
			.Select("`u`.`id`", "`u`.`nick_name`", "(SELECT `allow_follow` FROM `user_config` WHERE `user_id` = `u`.`id`) AS `allow_follow`")
			.Find(&result)
			// SELECT `u`.`id`,`u`.`nick_name`,(SELECT `allow_follow` FROM `user_config` WHERE `user_id` = `u`.`id`) AS `allow_follow` FROM `user` AS `u`

		* ����ֱ����Select�����ַ���д��һ������е�SQL�������

-----------------------
��������
-----------------------
	# Where �������԰�����һ��Where
		var result []*model.User
		db.Model(&model.User{}).
			Where(
				db.Where("id > ?", 1).Or("nick_name = ?", "Vin"),
			).
			Where(
				db.Where("version <> ?", "5"),
			).
			Or(
				db.Where("create_at != NOW()").Or("update_at > NOW()"),
			).
		Find(&result)
		// SELECT * FROM `user` WHERE (id > 1 OR nick_name = 'Vin') AND version <> '5' OR (create_at != NOW() OR update_at > NOW())

		* �����ϣ�Ƕ��һ��Where�����൱�������һ������ () ����
	

-----------------------
��������
-----------------------
	# ֧�� sql.NamedArg �� map[string]interface{}{} ��ʽ����������
		db.Model(&model.User{}).Find(&result, "id > @id", sql.NamedArg{Name: "id", Value: 0})
		// SELECT * FROM `user` WHERE id > 0

		db.Model(&model.User{}).Find(&result, "id > @id OR nick_name = @name", map[string]interface{} {
			"id": 1,
			"name": "Vin",
		})
		// SELECT * FROM `user` WHERE id > 1 OR nick_name = 'Vin'
	
	# ֧��ʹ�ýṹ����ֶ�������Ϊ��������
		var user = &model.User{}
		db.Model(&model.User{}).Where("id = @Id AND version = @V", &struct{
			Id int		// �ֶ�����Id
			V int		// �ֶ�����V
		}{1, 2}).First(user)

		// SELECT * FROM `user` WHERE id = 1 AND version = 2 ORDER BY `user`.`id` LIMIT 1
	

-----------------------
FirstOrInit
-----------------------
	# �����������û��ѯ�����ͷ����Զ���Ľ��
		param := &model.User{NickName: "Foo"}
		db.Model(&model.User{}).FirstOrInit(param, &model.User{
			NickName: "Foo������",
		})

		// SELECT * FROM `user` WHERE `user`.`nick_name` = 'Foo������' ORDER BY `user`.`id` LIMIT 1

		// param��ֵ�Ѿ�������Զ���Ľ��ֵ
		log.Println(param) // &{0 Foo������  <nil> <nil> 0 }

		* ��֧�� sturct �� map ����

-----------------------
FirstOrCreate
-----------------------


-----------------------
�Ż�����������ʾ
-----------------------

-----------------------
����
-----------------------
	# ͨ�� Rows ��ȡ�� sql/Rows ���е�����ͨ�� ScanRows ���з�װ
		rows, err := db.Model(&model.User{}).Rows()
		if err != nil {
			log.Fatal(err)
		}
		for rows.Next() {
			// var user = &model.User{}
			var result = map[string] interface{}{}
			db.ScanRows(rows, &result)
			log.Println(result)
		}
		
		* �����ýṹ������� map[string] interface{}{} ����װ����
	
-----------------------
FindInBatches
-----------------------
	# ����������ѯ�������¼
		result := db.Model(&model.User{}).FindInBatches(&results, 2, 	// ָ���������������һ������������
					func(tx *gorm.DB, batch int) error {

			// ������������
			for _, result := range results {
				log.Println(result)
			}

			// ��һ�������ж���
			log.Println(tx.RowsAffected)

			// ���Ǵ���ĵڼ���������
			log.Println(batch)

			// ������ش������ֹ������������
			return nil
		})
		log.Println(result.Error) 		// �����������ص��쳣
		log.Println(result.RowsAffected) // ������������Ӱ��ļ�¼��


		* ���������������ͨ��������ID��������ɵ�: `user`.`id` > 1 ORDER BY `user`.`id` LIMIT 2 
	

-----------------------
Pluck
-----------------------
	# ���н������װ
		var ages []int64
		db.Model(&users).Pluck("age", &ages)

		var names []string
		db.Model(&User{}).Pluck("name", &names)

		db.Table("deleted_users").Pluck("name", &names)

		// Distinct Pluck
		db.Model(&User{}).Distinct().Pluck("Name", &names)
		// SELECT DISTINCT `name` FROM `users`

-----------------------
Scopes
-----------------------
	# ָ�����õĲ�ѯ�������ڵ��÷���ʱ������Щ��ѯ
		func AmountGreaterThan1000(db *gorm.DB) *gorm.DB {
		  return db.Where("amount > ?", 1000)
		}

		func PaidWithCreditCard(db *gorm.DB) *gorm.DB {
		  return db.Where("pay_mode_sign = ?", "C")
		}

		func PaidWithCod(db *gorm.DB) *gorm.DB {
		  return db.Where("pay_mode_sign = ?", "C")
		}

		func OrderStatus(status []string) func (db *gorm.DB) *gorm.DB {
		  return func (db *gorm.DB) *gorm.DB {
			return db.Where("status IN (?)", status)
		  }
		}

		db.Scopes(AmountGreaterThan1000, PaidWithCreditCard).Find(&orders)
		// �������н����� 1000 �����ÿ�����

		db.Scopes(AmountGreaterThan1000, PaidWithCod).Find(&orders)
		// �������н����� 1000 �� COD ����

		db.Scopes(AmountGreaterThan1000, OrderStatus([]string{"paid", "shipped"})).Find(&orders)
		// �������н�����1000 ���Ѹ�����ѷ�������
		
		* Scopes ����һ������������ֵҪô�� *gorm.DB��Ҫô�� func (db *gorm.DB) *gorm.DB
			func (db *gorm.DB) *gorm.DB
			func (any) func (db *gorm.DB) *gorm.DB

----------------
Count
----------------
	# ��ȡ�ܼ�¼����
		var count int64
		db.Model(&model.User{}).Where("id > ?", 0).Count(&count)
		log.Println(count)
		// SELECT count(1) FROM `user` WHERE id > 0

		* Count �βΣ����յ��� int64 ��ָ��
	
