-------------------
gin
-------------------
	# ����gin
		// ʵ��gin��У���� binding.StructValidator
		type customValidator struct {}
		func (v *customValidator) ValidateStruct(ptr interface{}) error {
			var result = validate.Struct(ptr)
			if result.Validate() {
				return nil
			}
			return result.Errors
		}
		func (v *customValidator) Engine() interface{} {
			return nil
		}

		// ����gin��Ĭ��У����
		binding.Validator = &customValidator{}

	
	# ʹ��
		var userRequest = &UserRequest{}
		if err := ctx.ShouldBindJSON(userRequest); err != nil {
			// ת��ΪУ���쳣
			ctx.JSON(http.StatusBadRequest, gin.H{
				"success": false,
				"message": err.(validate.Errors).One(),  // ת��Ϊ validate.Errors�� �����Ӧһ���쳣��Ϣ
			})
			return
		}
		ctx.JSON(http.StatusOK, gin.H{"success": true, "data": userRequest})
