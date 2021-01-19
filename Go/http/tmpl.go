------------------------
ģ������
------------------------
	# ʹ�� "html/template"
	# �ο�
		https://www.cnblogs.com/f-ck-need-u/p/10035768.html


------------------------
����
------------------------
	# ���غ���
		func New(name string) *Template
			* ���ڴ����½�һ��ģ�壬ָ������

		func ParseFiles(filenames ...string) (*Template, error)
			* ����һ��ģ�壬ʹ���ļ�����Ϊģ�������
		
		func ParseGlob(pattern string) (*Template, error)
			* ����pattern��������ƥ���ģ�岢����
		
	# ģ���������Ĭ��ʹ���ļ�����(base name)
		* ���ģ�嶨���� {{ define "templateName" }} �Ļ�����ʹ�� templateName
	

------------------------
������Ⱦ
------------------------
	# ��ǰ�����ڵĶ������Ϊ: .
		
		Hello {{ .Name }}

		* ����ֶ��Ƕ��󣬿�����ʽ���� {{ .Struct.StructTwo.Field }}
		* ������ݵĲ��������ǵ������ݣ������Ǹ������ݣ���ô����ֱ��ʹ��:.
			tpl.ExecuteTemplate(writer, "index.html", "KevinBlandy")
			Hello {{ . }}
		
		* �������� String() ������
	
	# with������������"."��ֵ
		* ��pipeline��Ϊ0ֵ��ʱ�򣬵�"."����Ϊpipeline�����ֵ��ִ��T1�����
			{{ with pipeline }} 
				T1 
			{{ end }}
		
		* ��pipelineΪ0ֵʱ��ִ��else���飬����"."����Ϊpipeline�����ֵ����ִ��T1��
			{{ with pipeline }} 
				T1 
			{{ else }}
				T0 
			{{ end }}
	
	# ģ�弶��������Ķ�������� $
		* ��ĳЩ�ֲ������򣬶��������. �����ʱ������ⲿ��ȫ�ֱ������ͱ������� $
			{{range .slice}}
				{{$.ArticleContent}}		// �����ⲿ��ȫ�ֱ���
			{{end}}
	

	# ģ�������������ģ�����Զ��������ʹ�� $ ���������
		{{$root := .}}
        Hello {{ $root.Name }}

		// δ������ı���
		$var := pipeline

		// �Ѷ�����ı���
		$var = pipeline
	
	# ��ȫ��HTML
		* ��ͨ��HTML�ᱻת��
			tpl.ExecuteTemplate(writer, "index.html", "<h1>Vin</h1>")
			Hello &lt;h1&gt;Vin&lt;/h1&gt;
		
		* ʹ�� template.HTML �������HTML
			tpl.ExecuteTemplate(writer, "index.html", template.HTML("<h1>Vin</h1>"))
			Hello <h1>Vin</h1>

	# ��ֵ����
		* ��Ⱦ�����ڵı������᷵�ؿո��ַ���
		* �Բ����ڵı������м��㣬���쳣
		
	# ����
		* ��ȡ��������ֵʹ�����ú��� index
			 ǲ{{ index .Users 0 }}��ս
			 Users[0]
	
		* ��ά����
			{{ index .Users 0 1 2 }}
			Users[0][1][2]
	
	# Map
		* ʹ�� . ��������
			err := tpl.ExecuteTemplate(writer, "index.html", map[string] interface{} {
				"Foo": map[string] interface{} {
					"Bar": "Bar",
				},
			})

			{{ .Foo.Bar }}
	
	# �Ƴ�����Ŀո񣬿����� {{}} ��� -
		* �����ӱ�ʾ�Ƴ���ߵĿո��ұ���ӱ�ʾ�Ƴ��ұߵ�
			{{- .Foo.Bar -}}
	
	
	# ע�ͣ�ʹ�� /**/
		{{/* a comment */}}
				

------------------------
����
------------------------
	# ʹ��Range����
		err := tpl.ExecuteTemplate(writer, "index.html", map[string] interface{} {
			"Users": []string {"����", "�ŷ�", "����", "��", "����"},
		})

		<ul>
			{{ range .Users }}
				<li>{{ . }}</li>
			{{ end }}
		</ul>

		* ��range�У� . ��ʾ��ǰ������Ԫ��
	

	# Rang��ֵ����
		{{ range $key, $val :=. }}
			{{$key}} = {{$val}}
		{{ end }}

		* ֻ��һ�����������Ǳ����ĵ�ǰֵ
		* 2����������һ��Ϊkey/index���ڶ���Ϊ��ǰֵ
	
	# else���жϣ����û�пɱ������ݣ��ͻ�ִ��
		{{ range . }}
			<li>{{ . }}</li>
		{{ else }}
			<li> û������ </li>
		{{ end}}
	

	

	 