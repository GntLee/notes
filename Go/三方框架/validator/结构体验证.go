------------------
�ṹ����֤
------------------
	# ���������map/��Ƭ����Ҫ����У����������ݣ���ôʹ�� dive ��ǩ
		type User struct {
			Id int `json:"id"`
			Hobby [] *struct {
				Name string `json:"name" validate:"required,min=1,max=50"`	// �ַ�����1-50���ַ�
				Proficiency int `json:"proficiency" validate:"required,min=0,max=100"` // �����ȣ�0 - 100
			} `json:"hobby" validate:"required,max=5,dive"` // ���10��ѡ�����ʹ�� dive ������У�����Hobby���������
		}
	
	# ��������ǽṹ�壬��ô���Զ�����У��

	


