-----------------------------
validate
-----------------------------
	# Gookit��֯��Դ��У����
		* Github
			https://github.com/gookit/validate
		
		* �ĵ�
			https://github.com/gookit/validate/blob/master/README.zh-CN.md
			https://pkg.go.dev/github.com/gookit/validate
	
	# �Ƿ���������
		* Go���ֶα������Ĭ��ֵ������"��ϱ�ǩ"����������Ϊ��
			Id int 				`validate:"uint" message:"ID����С��0"`
			Account string		`validate:"minLen:0|maxLen:20" message:"�˻��ַ�������6-20λ"`

			{
				"Id": null,			// OK
				"Account": null		// OK
			}

		* ���Ҫ��ͻ��˴������ݣ��ͱ���ʹ��������У���ǩ
			

	
	# �Զ���У�鷽��
		* �ڽṹ���ֶ�������һ���������ƣ�Сд��ͷ
		* �ṹ��ʵ�������������д��ͷ�����������ֶ�ֵ������bool��true��ʾOK��false��ʾ�쳣

		type UserRequest struct {
			Desc string			`validate:"descValidate" message:"DescУ��ʧ�ܣ�������S��ͷ"`
		}
		func (f UserRequest) DescValidate(val string) bool {
			return val[0] != 'S'
		}
	

	
	# �ṹ��Ļ�����֤
		v := validate.Struct(u)
		if !v.Validate() {
			// Map<String, Map<String, String>> -> {�ֶ�����: {��֤��ǩ: ������Ϣ}}
			for field, v := range v.Errors {
				for tag, message := range v {
					fmt.Printf("Field=%s, tag=%s, err=%s\n", field, tag, message)
				}
			}
		}
	
		