------------------
ģ������
------------------
	# ����·��
		func (engine *Engine) LoadHTMLFiles(files ...string)
			* ����ָ����ģ�����棬���غ������contextʹ��

		func (engine *Engine) LoadHTMLGlob(pattern string)
			* ����ȫ�ֵ�ģ���������ǰ׺
			* ָ��Ŀ¼�µ������ļ�
				router.LoadHTMLGlob("templates/*")
			* ָ��Ŀ¼�µ�����Ŀ¼�������ļ������У�
				router.LoadHTMLGlob("templates/**/*")
	
	# ��������ģ������ָ���
		func (engine *Engine) Delims(left, right string) *Engine
	
	# �Զ��庯��
		func (engine *Engine) SetFuncMap(funcMap template.FuncMap)
	
	# ʹ���Լ������ģ�������
		func (engine *Engine) SetHTMLTemplate(templ *template.Template)
	
	# ��Ӧģ������
		func (c *Context) HTML(code int, name string, obj interface{})

------------------
��ģ��������Ⱦ
------------------
	