---------------------
��Ƭ
---------------------
	# ������Ƭ����֤tag�е���ֵ��������֤����(len)
		type User struct {
			Hobby []string `validate:"required,len=3,dive,min=3,max=5"`
			// required��len��ָ��Hobby�Ǳ���ģ����ҳ���ֻ����3
			// dive ��ʾҪ����У��Ԫ��
			// min=3 ��ʾ�ַ������ȣ����Ϊ3�����Ϊ5
		}

		func main() {
			v := validator.New()
			var user = &User{Hobby: []string {"Jav", "hon", "Rb" }}
			if err := v.Struct(user); err != nil {
				log.Println(err)
			}
		}
	

	# ��ά��Ƭ�������ö�� dive У��
		var slicethree [][]string
		validate.Var(slicethree, "min=2,dive,len=2,dive,required")
			* min=2����֤��һ�� [] �����ŵ�ֵ���� ;
			* len=2����֤�ڶ��� []string ����;
			* required����֤slice���ֵ

		validate.Var(slicethree, "min=2,dive,dive,required")
			* min=2����֤��һ�� [] �����ŵ�ֵ���� ;
			* dive�� ��û������tagֵ������֤�ڶ��� []string ;
			* required�� ��֤slice���ֵ
	
---------------------
��Ƭ
---------------------
	# ʹ��dive,keys,endkeys tag���У��
		* keys �� endkeys ��tag����֤��2��tag֮��map�� key����ʾһ������

		mapone = map[string]string{"one": "jimmmy", "two": "tom", "three": ""}

		err := validate.Var(mapone, "gte=3,dive,keys,eq=1|eq=2,endkeys,required")

		* gte=3����֤map�Լ��ĳ��ȣ�
		* dive��� keys,eq=1|eq=2,endkeys����֤map��keys������Ҳ������֤��ֵ��
		* required����֤ map��ֵvalue
	
	# Ƕ��map���ǾͶ�� dive
		var maptwo map[[3]string]string{}
		validate.Var(maptwo, "gte=3,dive,keys,dive,eq=1|eq=3,endkeys,required")

		* gte=3�� ��֤map�ĳ���
		* keys,dive,eq=1|eq=3,endkeys��keys��endkeys����һ��dive(����һ��)����֤map��key������ÿһ��ֵ
		* required�� ��֤map��ֵ

