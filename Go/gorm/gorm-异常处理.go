-------------------------
�쳣����
-------------------------
	# GORM �ṩ������ʽ API����������κδ���GORM ������ *gorm.DB �� Error �ֶ�
	
	# ��Ҫ�Լ��������
		if err := db.Where("name = ?", "jinzhu").First(&user).Error; err != nil {
			// �������...
		}

		if result := db.Where("name = ?", "jinzhu").First(&user); result.Error != nil {
		  // �������...
		}

	
	# ���õĴ����б�
		// ErrRecordNotFound record not found error
		ErrRecordNotFound = errors.New("record not found")
		// ErrInvalidTransaction invalid transaction when you are trying to `Commit` or `Rollback`
		ErrInvalidTransaction = errors.New("no valid transaction")
		// ErrNotImplemented not implemented
		ErrNotImplemented = errors.New("not implemented")
		// ErrMissingWhereClause missing where clause
		ErrMissingWhereClause = errors.New("WHERE conditions required")
		// ErrUnsupportedRelation unsupported relations
		ErrUnsupportedRelation = errors.New("unsupported relations")
		// ErrPrimaryKeyRequired primary keys required
		ErrPrimaryKeyRequired = errors.New("primary key required")
		// ErrModelValueRequired model value required
		ErrModelValueRequired = errors.New("model value required")
		// ErrInvalidData unsupported data
		ErrInvalidData = errors.New("unsupported data")
		// ErrUnsupportedDriver unsupported driver
		ErrUnsupportedDriver = errors.New("unsupported driver")
		// ErrRegistered registered
		ErrRegistered = errors.New("registered")
		// ErrInvalidField invalid field
		ErrInvalidField = errors.New("invalid field")
		// ErrEmptySlice empty slice found
		ErrEmptySlice = errors.New("empty slice found")
		// ErrDryRunModeUnsupported dry run mode unsupported
		ErrDryRunModeUnsupported = errors.New("dry run mode unsupported")
	

	# ͨ�� erros.Is ���ж��쳣����
		// �������Ƿ�Ϊ RecordNotFound
		err := db.First(&user, 100).Error
		errors.Is(err, gorm.ErrRecordNotFound)
