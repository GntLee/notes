------------------------
����
------------------------
	# ģ��������õķ�����ֻ�ܷ���һ�������������2������ô�ڶ��������� error
		

	# �ṹ�巽�����ã�ʹ��:. 
		type User struct {
			Name string
		}
		func (u *User) Say(val string) string {
			return fmt.Sprintf("%s, Im %s\n", val, u.Name)
		}
		
		
		err := tpl.ExecuteTemplate(writer, "index.html", map[string] interface{} {
			"user": &User{Name: "KevinBlandy"},
		})

		{{ .user.Say "Hello" }}


		* ���û�в�����ʡ�Լ���
	
	# �����������ã�ʹ�� call ָ��
		err := tpl.ExecuteTemplate(writer, "index.html", map[string] interface{} {
			"say": func(val string) string {
				return fmt.Sprintf("Hello %s\n", val)
			},
		})

		{{ call .say "½����" }}
	

	# ע��ȫ�ֺ���������ط������Ե��ã����������ã�����д�ں��棬����Ļ�ʹ�ö��ŷָ�
		testTemplate, err = template.New("hello.gohtml").Funcs(template.FuncMap{
		"hasPermission": func(user User, feature string) bool {
		  if user.ID == 1 && feature == "feature-a" {
			return true
		  }
		  return false
		},
	  }).ParseFiles("hello.gohtml")
	 
	
	# �ܵ�����
		* ʹ�� | �ѱ�����Ϊ���������ݸ�����
		* �����ķ���ֵ������Ϊ��һ�������Ĳ���
			<a href="/search?q={{. | urlescaper | attrescaper}}">{{. | htmlescaper}}</a>
		
		* ��������ж����������ô�ܵ�����ѱ������ݸ����һ������
			{{ . | printf "%s\n" "abcd" }}  // {.}}�Ľ�������ݸ�printf���Ҵ��ݵĲ���λ����"abcd"֮��
				
	# ֱ�����ԭʼ����
		{{`"output"`}}


------------------------
���ú���
------------------------
	js
		* ��������������ı���ʾ��ʽ��Ч��ת��JavaScript
	
	len
		* ���س���
	
	index
		* ������������ֵ
	
	call
		* ִ�н���ǵ��õ�һ�������ķ���ֵ���ò��������Ǻ������ͣ����������Ϊ���øú����Ĳ���
	
	html
		* �Բ�������HTML����

	urlquery
		* �Լ����������б���
			{{ urlquery "name=����&age=65" }}
			name%3D%E5%88%98%E5%A4%87%26age%3D65
	

------------------------
�������ĺ�����
------------------------
	# https://github.com/Masterminds/sprig