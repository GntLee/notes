------------------
У���������
------------------
	# ��֤�����Ƿ����tag
		v := validator.New()
		email := "88855.com"
		if err := v.Var(email, "email"); err != nil {
			for _, err := range err.(validator.ValidationErrors) {
				log.Println(err)
			}
		}
	
	# ��֤2��������ϵ�Ƿ����tag
		// ʹ�� VarWithValue
	


		