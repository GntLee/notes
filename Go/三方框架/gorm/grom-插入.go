--------------------
����
--------------------
	# ͨ�� Create �洢��¼��������ָ��
		role := model.Role{
			Name: "��������Ա",
		}
		result := gormDB.Create(&role)
		log.Printf("Error=%v\n", result.Error)		// Error=<nil>
		log.Printf("RowsAffected=%d\n", result.RowsAffected)	// RowsAffected=1
		log.Println(role.Id)	// 1

		* �ӷ��ص�result�л�ȡ�쳣/��Ӱ��������Ϣ
		* �Ӳ��������л�ȡ������ID
	
	# ָ���ֶ�
		* ��������ָ�����ֶΣ����������ֶ�
			result := gormDB.Select("Email", "Version").Create(user)
		
		* ����û��ָ�����ֶΣ�����Omitָ�����ֶΣ�
			result := gormDB.Omit("nick_name", "introduction").Create(user)
	
	# ��������
		* Create�βΣ�ʹ����Ƭ
			var roles = []*model.Role {{
					Name: "role1",
				}, {
					Name: "role2",
				}, {
					Name: "role3",
				}, {
					Name: "role4",
				},
			}
			result := gormDB.Create(roles)
			log.Println(result)
			for _, r := range roles {
				log.Println(r.Id)
			}
	
		* ʹ�� CreateInBatches ����ʱ��������ָ��������С
			result := gormDB.CreateInBatches(roles, 10)
	

	# ʹ��Map��Ϊ���ݣ����в��룬�����ǵ���map[string] interface{}Ҳ����������[]map[string] interface{}
		* ͨ��Model���а����ݱ��ִ�У�ע��Map�е�Key�Ƕ�����ֶ�����
			result := gormDB.Model(&model.UserConfig{}).Create([]map[string] interface{} {
				map[string] interface{} {
					"UserId": 7,
					"AllowFollow": false,
				},
				map[string] interface{} {
					"UserId": 6,
					"AllowFollow": true,
				},
			})
			log.Println(result) // nil, 0
		
		* ͨ��Table�����ݱ��ִ�У�ע��Map�е�Key�Ǳ��ֶ�����
			result := gormDB.Table("user_config").Create([]map[string] interface{} {
				map[string] interface{} {
					"user_id": 99,
					"allow_follow": true,
				},
				map[string] interface{} {
					"user_id": 88,
					"allow_follow": true,
				},
			})

		* ���� map ������¼ʱ��association ���ᱻ���ã�������Ҳ�����Զ����
	
	# Ĭ��ֵ������
		* ��ֵ��Ҳ��ֵ���ᱻ�������ݴ���
				role := &model.Role{
					// Name: ""
				}
				result := gormDB.Create(role)	// ����ɹ������ݿ��name�ֶ��ǿ��ַ���: ""
				log.Println(result)
				log.Println(role.Id) 
		
		* ��Ҫ����null��������ָ������������ṹ����ֶ�
			type Role struct {
				Id		uint	`gorm:"type:int(11) unsigned not null auto_increment comment 'ID';"`
				Name	*string	`gorm:"type:varchar(20) not null comment '��ɫ����'"`
			}

			//�ٴ�ִ�пն������ &model.Role{}���ͻ��׳��쳣�� Column 'name' cannot be null
		
		* ��Ҫ����null������ʹ��sql���µ� NullXXX ����
			type Role struct {
				Id		uint	`gorm:"type:int(11) unsigned not null auto_increment comment 'ID';"`
				Name	sql.NullString	`gorm:"type:varchar(20) not null comment '��ɫ����'"`
			}

			role := &model.Role{
				Name: sql.NullString{
					String: "",
					Valid:  false,		// ֵ��true����ʾ��null��ֵ��false����ʾnull
				},
			}
			result := gormDB.Create(role)	// ִ���쳣��Column 'name' cannot be null
		
		* ʹ��default��ǩָ����ֵ��Ĭ��ֵ
			type Role struct {
				Id		uint	`gorm:"type:int(11) unsigned not null auto_increment comment 'ID';"`
				Name	string	`gorm:"default:Ĭ�Ͻ�ɫ; type:varchar(20) not null comment '��ɫ����'"`
			}
			// ִ�пն������ &model.Role{}��Ĭ�ϵ�Name ����"Ĭ�Ͻ�ɫ"

	

	# Upsert ����ͻ
		* �ڳ�ͻʱ��ʲô������
			user := &model.User{
				Email:   "1000@qq.com",	// email ��ΨһԼ���������Ѿ�����������¼��
				Version: 0,
			}
			result := gormDB.Clauses(clause.OnConflict{DoNothing: true}).Create(user)
			log.Println(result)	// nil, ��Ӱ��������0

