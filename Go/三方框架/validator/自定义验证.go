--------------------
�Զ���tag��֤
--------------------
	# ��֤������ͨ������boolֵ����ʾ�Ƿ���֤�ɹ�
		type Func func(fl FieldLevel) bool
		type FuncCtx func(ctx context.Context, fl FieldLevel) bool
	
	# ��֤����
		type FieldLevel interface {
			Top() reflect.Value
			Parent() reflect.Value
			Field() reflect.Value
				* ��ȡ��ǰ�ֶ���Ϣ

			FieldName() string
				* ��ȡ�ֶ�����

			StructFieldName() string
			Param() string
				* ��ȡtag��Ӧ�Ĳ���

			GetTag() string
			ExtractType(field reflect.Value) (value reflect.Value, kind reflect.Kind, nullable bool)
			GetStructFieldOK() (reflect.Value, reflect.Kind, bool)
			GetStructFieldOKAdvanced(val reflect.Value, namespace string) (reflect.Value, reflect.Kind, bool)
			GetStructFieldOK2() (reflect.Value, reflect.Kind, bool, bool)
			GetStructFieldOKAdvanced2(val reflect.Value, namespace string) (reflect.Value, reflect.Kind, bool, bool)
		}

	# ע�᷽��
		func (v *Validate) RegisterValidation(tag string, fn Func, callValidationEvenIfNull ...bool) error
		func (v *Validate) RegisterValidationCtx(tag string, fn FuncCtx, callValidationEvenIfNull ...bool) error
			* ע���Զ���У��tag
			* tagָ��������fnָ��У�鷽����callValidationEvenIfNull �Ƿ���nullֵ��ʱ��Ҳ������֤
	
	# Demo
		type User struct {
			Id int `json:"id"`
			Name string `json:"name" validate:"omitempty,nick_name"`  // ���Name����ֵ����У�飬�����ֵ��ô������� nick_name ��У��
			Hobby *Hobby `validate:required"`
		}

		v := validator.New()

		var user = &User{...} // ���Գ�ʼ������

		// ���tag
		v.RegisterValidation("nick_name", func(fl validator.FieldLevel) bool {
			// ��ȡ���ֶε�ֵ
			val := fl.Field().Interface()
			// ת��Ϊstring�������ж��Ƿ�����ַ��� "nick_name"
			if v, ok := val.(string); ok {
				if v == "nick_name" {
					return true
				}
			}
			return false
		})

		if err := v.Struct(user); err != nil {
			if err, ok := err.(validator.ValidationErrors); ok {
				log.Println(err)
			}
		}
		
--------------------
�Զ���struct��֤
--------------------
	# ��֤����
		type StructLevelFunc func(sl StructLevel)
	
	# ��֤����
		type StructLevel interface {
			Validator() *Validate
			Top() reflect.Value
			Parent() reflect.Value
			Current() reflect.Value
				* ��ȡ��ǰ��У�����

			ExtractType(field reflect.Value) (value reflect.Value, kind reflect.Kind, nullable bool)

			ReportError(field interface{}, fieldName, structFieldName string, tag, param string)
				* �����쳣��ָ���ֶΣ��ֶ����ƣ��ṹ���е��ֶ�������ǩ������ֵ
			
			ReportValidationErrors(relativeNamespace, relativeActualNamespace string, errs ValidationErrors)
		}
	
	# ע�᷽��
		func (v *Validate) RegisterStructValidation(fn StructLevelFunc, types ...interface{})
		func (v *Validate) RegisterStructValidationCtx(fn StructLevelFuncCtx, types ...interface{}) 
		
		* ��������������̰߳�ȫ�ģ���Ҫע��
	
	# Demo
		v.RegisterStructValidation(func(sl validator.StructLevel) {
			// ��ȡ��У�����
			if user, ok := sl.Current().Interface().(User); ok {
				if user.Id < 0 {
					// ���У��ʧ�ܣ��򱨸��쳣
					sl.ReportError(user.Id, "Id", "id", "id", "id")
				}
			}
		}, &User{})
	
--------------------
�Զ���type��֤
--------------------
	# ��֤����
		type CustomTypeFunc func(field reflect.Value) interface{}
	

	# ע�᷽��
		func (v *Validate) RegisterCustomTypeFunc(fn CustomTypeFunc, types ...interface{})
		
		* ������������̰߳�ȫ��
	
	# Demo
		v.RegisterCustomTypeFunc(func(field reflect.Value) interface{} {
			// ���ֶ�ֵ��ת��Ϊָ��������
			if valuer, ok := field.Interface().(driver.Valuer); ok {
				val, err := valuer.Value()
				log.Println(val)
				if err != nil {
					// TODO value err
				}
				// ת���󣬷���
				return val
			}
			return nil
		}, sql.NullString{})