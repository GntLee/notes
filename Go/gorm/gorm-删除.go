-------------------
ɾ��
-------------------
	# ɾ��ʱ��Ҫָ�����������ֶ�ͨ��Where�����������Ȼ��ȫ��ɾ��
		* Delete��model��ֻ�����IDɾ��
			db.Delete(&model.User{Id: 58, Version: 22})	// Version�׸�
			// DELETE FROM `user` WHERE `user`.`id` = 58  

		* �Լ��������
			db.Where("id = ?", 1).Where("version = ?", 5555).Delete(&model.User{})
			// DELETE FROM `user` WHERE id = 1 AND version = 5555

			db.Where(&model.User{Id: 1555, Version: 55}).Delete(&model.User{})
			// DELETE FROM `user` WHERE `user`.`id` = 1555 AND `user`.`version` = 55
	
	# ָ������ɾ����ֻ֧��������ֵ����Ϊ string ���ܵ��� SQL ע��
		db.Delete(&User{}, 10)
		// DELETE FROM users WHERE id = 10;

		db.Delete(&User{}, "10")
		// DELETE FROM users WHERE id = 10;

		db.Delete(&users, []int{1,2,3})
		// DELETE FROM users WHERE id IN (1,2,3);

	
	# Delete Ҳ���Կ������ɾ��
		db.Delete(&model.User{}, "id = ? AND version > ?", 55, 66)
		// DELETE FROM `user` WHERE id = 55 AND version > 66
	

-------------------
�߼�ɾ��
-------------------
	# gorm�ṩ��һ���ֶ�
		type DeletedAt sql.NullTime
	
	# ��Ϊ�ֶ����������Լ��Ķ�����
		* ��ִ��ɾ��������ʱ�򣬻�� DeletedAt ��Ϊ��ǰʱ�䣬 ���Ҳ�����ͨ�������Ĳ�ѯ�����ҵ��ü�¼
		* �ڼ�����ʱ�򣬶������������ AND deleted_at IS NULL
	
	# �����Ҫǿ������ɾ��������ʹ��Unscoped 
		db.Unscoped().Delete(&order)
	
