----------------------------
var
----------------------------

----------------------------
type
----------------------------
	# type CustomTypeFunc func(field reflect.Value) interface{}
		* �Զ����У�鷽��

	# type FieldError interface {
			Tag() string
				* ����ע��
			ActualTag() string
				* ��֤ʧ�ܵ�ע��
			Namespace() string
			StructNamespace() string
			Field() string
			StructField() string
				* �ֶ��ڽṹ���е�����

			Value() interface{}
				* �ֶ�ֵ
			
			Param() string
			Kind() reflect.Kind
			Type() reflect.Type
				* �����ֶε�Type

			Translate(ut ut.Translator) string
			Error() string
		}
		
		* У��ʧ�ܵ��쳣�����

	# type FieldLevel interface {
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
		
		* У���е��ֶ���Ϣ

	# type FilterFunc func(ns []byte) bool

	# type Func func(fl FieldLevel) bool
	# type FuncCtx func(ctx context.Context, fl FieldLevel) bool
		* �Զ���Tag��֤����֤����

	# type InvalidValidationError struct {
			Type reflect.Type
		}
		func (e *InvalidValidationError) Error() string
	# type RegisterTranslationsFunc func(ut ut.Translator) error

	# type StructLevel interface {
			Validator() *Validate
			Top() reflect.Value
			Parent() reflect.Value
			Current() reflect.Value
			ExtractType(field reflect.Value) (value reflect.Value, kind reflect.Kind, nullable bool)

			ReportError(field interface{}, fieldName, structFieldName string, tag, param string)
				* �����쳣?
			
			ReportValidationErrors(relativeNamespace, relativeActualNamespace string, errs ValidationErrors)
		}
		
		* У���еĽṹ����Ϣ
			
	# type StructLevelFunc func(sl StructLevel)
	# type StructLevelFuncCtx func(ctx context.Context, sl StructLevel)
		* �ṹ��У�鷽��

	# type TagNameFunc func(field reflect.StructField) string
	# type TranslationFunc func(ut ut.Translator, fe FieldError) string
	# type Validate struct {
		}
		func New() *Validate
		func (v *Validate) RegisterAlias(alias, tags string)
		func (v *Validate) RegisterCustomTypeFunc(fn CustomTypeFunc, types ...interface{})
			* ע���Զ����У�麯����ͨ��typesָ������

		func (v *Validate) RegisterStructValidation(fn StructLevelFunc, types ...interface{})
		func (v *Validate) RegisterStructValidationCtx(fn StructLevelFuncCtx, types ...interface{})
			* ע���Զ���У��ṹ��ĺ���
			* fnָ��������typesָ���ṹ�壨����һ��ʵ����

		func (v *Validate) RegisterTagNameFunc(fn TagNameFunc)
			* ���ڽṹ���е��ֶΣ�У��ʧ�ܵ�ʱ�򣬻����쳣��Ϣ������ֶ�����
			* ����ͨ��������������޸��ֶ�����
			* ���磺ʹ��Ϊ�ṹ��JSON��ʾ��ָ�������ƣ���������ͨ��Go�ֶ�����
				validate.RegisterTagNameFunc(func(fld reflect.StructField) string {
					name := strings.SplitN(fld.Tag.Get("json"), ",", 2)[0]
					if name == "-" {
						return ""
					}
					return name
				})

		func (v *Validate) RegisterTranslation(tag string, trans ut.Translator, registerFn RegisterTranslationsFunc, ...) (err error)

		func (v *Validate) RegisterValidation(tag string, fn Func, callValidationEvenIfNull ...bool) error
		func (v *Validate) RegisterValidationCtx(tag string, fn FuncCtx, callValidationEvenIfNull ...bool) error
			* ע���Զ���У��tag
			* tagָ��������fnָ��У�鷽����callValidationEvenIfNull �Ƿ���nullֵ��ʱ��Ҳ������֤

		func (v *Validate) SetTagName(name string)

		func (v *Validate) Struct(s interface{}) error
		func (v *Validate) StructCtx(ctx context.Context, s interface{}) (err error)
			* У��ṹ��

		func (v *Validate) StructExcept(s interface{}, fields ...string) error
		func (v *Validate) StructExceptCtx(ctx context.Context, s interface{}, fields ...string) (err error)
		func (v *Validate) StructFiltered(s interface{}, fn FilterFunc) error
		func (v *Validate) StructFilteredCtx(ctx context.Context, s interface{}, fn FilterFunc) (err error)
		func (v *Validate) StructPartial(s interface{}, fields ...string) error
		func (v *Validate) StructPartialCtx(ctx context.Context, s interface{}, fields ...string) (err error)

		func (v *Validate) Var(field interface{}, tag string) error
		func (v *Validate) VarCtx(ctx context.Context, field interface{}, tag string) (err error)
			* У��ָ���ı������Ƿ����tag

		func (v *Validate) VarWithValue(field interface{}, other interface{}, tag string) error
		func (v *Validate) VarWithValueCtx(ctx context.Context, field interface{}, other interface{}, tag string) (err error)
			* У��2�������Ĺ�ϵ���Ƿ����tag 
				s1 := "abcd"
				s2 := "abcd"
				validate.VarWithValue(s1, s2, "eqcsfield") // returns true
			

	# type ValidationErrors []FieldError
		
		* У��ʧ�ܺ���쳣�����
		* һ����֤ʧ�ܺ󣬶���ת��Ϊ����쳣
		
		func (ve ValidationErrors) Error() string
		func (ve ValidationErrors) Translate(ut ut.Translator) ValidationErrorsTranslations

	# type ValidationErrorsTranslations map[string]string
		
----------------------------
func
----------------------------