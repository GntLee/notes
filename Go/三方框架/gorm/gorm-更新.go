---------------------
����
---------------------
	# Save �ᱣ�����е��ֶΣ���ʹ�ֶ�����ֵ
		var user = &model.User{}
		db.First(&user)
		user.NickName = "Foo"
		db.Save(user)

		// SELECT * FROM `user` ORDER BY `user`.`id` LIMIT 1
		// UPDATE `user` SET `nick_name`='Foo',`email`='10000@qq.com',`create_at`='2021-02-23 17:12:52',`update_at`='2021-02-23 17:12:52',`version`=0,`introduction`='1���û�' WHERE `id` = 1
	
	# Update ���µ�����
		db.Model(&model.User{}).Where("version > 0").Update("version", 0)
		// UPDATE `user` SET `version`=0 WHERE version > 0
	
	# Updates ���¶���
		var user = &model.User{
			NickName: "New Name",
			Version: 15,
		}
		db.Model(&model.User{}).Where("id = ?", user.Id).Updates(user)
		// UPDATE `user` SET `nick_name`='New Name',`version`=15 WHERE id = 0

		var user = &model.User{
			Id: 1,
			NickName: "New Name",
			Version: 15,
		}
		db.Model(&user).Updates(&user)
		// UPDATE `user` SET `nick_name`='New Name',`version`=15 WHERE `id` = 1

		* �÷���֧�� struct �� map[string]interface{} ������
		* ��ʹ�� struct ����ʱ��Ĭ������£�GORM ֻ����·���ֵ���ֶ�

	# ����ѡ���ֶ�
		db.Model(&model.User{}).Select("nick_name", "version").Where("id = ?", 1).Updates(map[string] interface{}{
			"NickName": "Bar",
			"Version": 155,
		})
		UPDATE `user` SET `nick_name`='New Name',`version`=15 WHERE `id` = 1

		* ��select��ָ��Ҫ���µ��ֶΣ�orm�ͻ��map/struct��ȡ����Щ�ֶ�ֵ�����и���
	
	# ȫ�ָ���
		* ���µ�ʱ�����û�и�structָ��IDֵ������û���κ������жϣ���ô�ᵼ��ȫ�����
		* Ĭ�ϲ�����ȫ�ָ��£����׳��쳣��ErrMissingWhereClause 

		* �����Ҫȫ�ָ�����ô���Ա��룺��һЩ����������ʹ��ԭ�� SQL���������� AllowGlobalUpdate ģʽ
	
	# �����б���ʹ��SQL���ʽ��func Expr(expr string, args ...interface{}) clause.Expr 
		db.Model(&model.User{}).
			Where("id = ?", 1).
			Where("version = ?", 1).
			Updates(map[string]interface{}{
				"Version": gorm.Expr("Version + ?", 1),
		})
		// UPDATE `user` SET `version`=version + 1 WHERE id = 1 AND version = 1
	
	# �����Ӳ�ѯ���и���
		subQuery := db.Model(&model.Role{}).Select("name").Where("id = ?", 1)
		db.Model(&model.User{}).Where("id = ?", 1).UpdateColumn("name", subQuery)
		// UPDATE `user` SET `name`=(SELECT `name` FROM `role` WHERE id = 1) WHERE id = 1


		* Update ��ֵ����������һ���Ӳ�ѯ
	
	# ����Hook
		* ������������UpdateColumn��UpdateColumns �� Update/Updates һ��
		* Ψһ��ͬ������������������ Hook
	

	# ����ֶ��Ƿ��б��

	# �ڸ���ʱ�޸�ֵ
