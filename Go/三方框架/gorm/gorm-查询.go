-----------------
�����Ĳ�ѯ
-----------------
	# First/Take/Last���������ѯ
		*  �����ݿ��м����������󣬻������ LIMIT 1 ��������û���ҵ���¼ʱ�����᷵�� ErrRecordNotFound ����
			var user = &model.User{}
			result := gormDB.First(user)
			if errors.Is(result.Error, gorm.ErrRecordNotFound) {
				log.Println("û���ҵ�")
			} else {
				log.Println(user)
			}

		* First ����ID����ȡ��һ��	ORDER BY ID LIMIT 1
		* Last ����ID����ȡ���һ��	LIMIT 1
		* Take û������ȡ��һ��		ORDER BY ID DESC LIMIT 1

		* First, Last������������������ҵ�һ/���һ����¼��ֻ������struct��ѯ���ṩmodelʱ����Ч����ΪҪͨ�������ȡ����Ϣ��
			db.First(&user)
			db.Model(&User{}).First(&result)

		* �����ǰmodelû�ж���������������һ���ֶ�����

		* ʹ��Model�󣬷�װ�Ľ����Ҳ�����ã�map[string]interface{}{}
			rvar user = map[string]interface{}{}
			result := gormDB.Model(&model.User{}).First(&user) // key�Ǳ�������

		* Take������ʹ��table�ķ�ʽ������ΪTake����Ҫ�κ�����Ϣ��
			result := map[string]interface{}{}
			db.Table("users").Take(&result)
			
	
	# ��������ID����
		* First����ָ�������У�Ĭ�ϸ���ID���������ID����ֵ����ô�����������ַ���/��ֵ
			db.First(&user, 10)
			db.First(&user, "10")
		
		* Find����ָ�������У����ݶ��ID������Ҳ����IN
			var users = make([]*model.User, 3, 3)	// �����Ƭ�����������Զ�����
			result := gormDB.Find(&users, []interface{} {1, 2, "4"}) 
			log.Println(result)
			for _, user := range users {
				log.Println(user)
			}
					
		* ���ID���ַ����Ļ�����ô��Ҫע��SQLע������⣬����ͨ��ָ��ID�У�Ԥ����������
			db.First(&user, "id = ?", "1b74413f-f3b8-409f-ac47-e8c062e3472a")
			// SELECT * FROM users WHERE id = "1b74413f-f3b8-409f-ac47-e8c062e3472a";

		
	# ������������
		* Find ��д����
			result := db.Find(&users)
			// SELECT * FROM users;
		
-----------------
������ѯ
-----------------
	# �ַ���������ʹ���ַ������죬�����е��У��Ǳ�������
		* = 
			var users = make([]*model.User, 1, 1)
			result := gormDB.Where("nick_name = ? AND email = ?", "vin", "10000@qq.com").Find(&users)
		
		* !=
			Where("name <> ?", "jinzhu")

		* IN 
			Where("name IN ?", []string{"jinzhu", "jinzhu 2"})
		
		* LIKE
			Where("name LIKE ?", "%jin%")
		
		* BETWEEN
			Where("created_at BETWEEN ? AND ?", lastWeek, today)


		* ʹ�� Find ����������¼����װΪ��Ƭ
		* ʹ�� First �����ڵ�����¼����װΪ��������

		* ��Ҫ��װΪ []map[string] interface{}����ô����ʹ�� Table �󶨱�
			var users = make([]map[string] interface{}, 1, 1)
			result := gormDB.Table("user").Where("nick_name = ? AND email = ?", "vin", "10000@qq.com").Find(&users)
			log.Println(result)

	# ʹ�ö���/Map��Ϊ����
		* ʹ��struct��Ϊ����
			param := &model.User{
				NickName: "vin",
				Email: "10000@qq.com",
			}
			result := gormDB.Where(&param).First(param)

			* struct��Ϊ������ֻ���÷�0ֵ��Ϊ������Ĭ�ϵ�0, '', false ���������������
				db.Where(&User{Name: "jinzhu", Age: 0}).Find(&users) // Age �ǰ�д
		
		* ����ʹ�ýṹ���е�ĳЩ�ֶ���Ϊ����
			param := &model.User{
				NickName: "name",
				Email: "abs@qq.com",
			}
			result := gormDB.Where(param, "NickName").First(param) // ����ʹ��param�е�"NickName"��Ϊ��������
			
		
		* ʹ��Map��Ϊ����
			param := &model.User{}
 			result := gormDB.Where(map[string] interface{} {"nick_name": "vin"}).First(param)
	
	# ��������
		* ʹ���ַ�������
			var users []*model.User
			result := gormDB.Find(&users, "id > ?", 0)	// WHERE `id` > 0
		
		* ʹ�ýṹ��
			var users []*model.User
			result := gormDB.Find(&users, &model.User{NickName: "vin", Email: "100100@qq.com"}) // WHERE `nick_name` ='vin' AND `email` = '100100@qq.com'
		
		* ʹ��Map
			var users []*model.User
			result := gormDB.Find(&users, map[string]interface{} {"nick_name": "vin"})	// WHERE `nick_name` ='vin' 
	
	# Not ����
		result := gormDB.Where("nick_name = ?", "vin").Not("id = ?", 2).Find(&users) // WHERE nick_name = 'vin' AND NOT id = 2
	
	# Or ����
		Where("nick_name = ?", "vin").Or("id = ?", 2).Find(&users) // Where("nick_name = ?", "vin").Or("id = ?", 2).Find(&users)

	# ���
		var users []*model.User
		result := gormDB.Where("nick_name = ?", "vin").
				Or(map[string]interface{}{"id": 1, "email": "10000@qq.com"}).
				Not(&model.User{Version: 1, Introduction: "55"}).
				Find(&users)
				//  WHERE nick_name = 'vin' OR (`email` = '10000@qq.com' AND `id` = 1) AND (`user`.`version` <> 1 AND `user`.`introduction` <> '55')
		
	
-----------------
ͶӰ��ѯ
-----------------
	# ָ����
		db.Select("UPPER(`nick_name`)", "email").Find(&users) //  SELECT UPPER(`nick_name`),`email` FROM `user`
	
	# ͨ����Ƭָ����
		db.Select([]string{"`nick_name`", "age"}).Find(&users)	//  SELECT `nick_name`,age FROM `user`
	
	# ��Table��Select
		var users []*model.User
		db.Table("user").Select([]string{"UPPER(`nick_name`)", "email"}).Find(&users) //  SELECT UPPER(`nick_name`),`email` FROM `user`

-----------------
����
-----------------
	# Order��������
		db.Order("`nick_name DESC`, `email` ASC").Order("`id`").Find(&users)  // ORDER BY `nick_name DESC`, `email` ASC,`id`
	
	# Clauses��������
		// TODO

-----------------
��ҳ
-----------------
	# ʹ�� Offset/Limit ���з�ҳ
		var users []*model.User
		db.Offset(1).Limit(2).Find(&users) //  LIMIT 2 OFFSET 1
	
		* Offset/Limit�Ĳ���ֵ�����-1���������������
	

-----------------
�ۺ�
-----------------
	# Group & Having ���оۺϼ���

	# ���ؽ��Ϊ (sql.Rows, err) / (sql.Row)
		var users []*model.User
		db.Table("`user`").Group("version").Having("`version` < ?", 100).Select("SUM(`id`)", "COUNT(`id`)").Rows()
		// SELECT SUM(`id`),COUNT(`id`) FROM `user` GROUP BY `version` HAVING `version` < 100
	
	# ��װ���Ϊ�ṹ��
		type Result struct {
			Sum uint
			Count uint
		}
		var result = &Result{}
		db.Model(&model.User{}).Select("SUM(`id`) AS `sum`", "COUNT(`id`) AS `count`", `version`).Group("version").Having("`version` < ?", 100).Find(&result)
	
-----------------
ȥ��
-----------------
	# Distinct
		db.Distinct("name", "age").Order("name, age desc").Find(&results)

	
-----------------
����
-----------------
	# Joins
		var r = map[string]interface{}{}
		result := db.Model(&model.User{}).Select("`user`.`nick_name`", "`user_config`.`allow_follow`").
				Joins("INNER JOIN `user_config` ON `user_config`.`user_id` = `user`.`id`").
				Where("`user`.`id` = ?", 1).
				Scan(&r)
		log.Println(result)
	

	
-----------------
Scan
-----------------
	# ������Find�����ڷ�װ����
		type Result struct {
		  Name string
		  Age  int
		}

		var result Result
		db.Table("users").Select("name", "age").Where("name = ?", "Antonio").Scan(&result)

		// Raw SQL
		db.Raw("SELECT name, age FROM users WHERE name = ?", "Antonio").Scan(&result)

