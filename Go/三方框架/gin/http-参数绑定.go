-----------------------
��
-----------------------
	# GinĿǰ֧��JSON��XML��YAML�ͱ�׼��ֵ�İ�

	# �󶨽ӿڵĶ���
		type Binding interface {
			Name() string
				* ����
			Bind(*http.Request, interface{}) error
				* ��request
		}
	
		type BindingBody interface {
			Binding
			BindBody([]byte, interface{}) error
				* ��body
		}
		type BindingUri interface {
			Name() string
			BindUri(map[string][]string, interface{}) error
				* ��uri
		}
	
	# Ԥ�����ContentType
		const (
			MIMEJSON              = "application/json"
			MIMEHTML              = "text/html"
			MIMEXML               = "application/xml"
			MIMEXML2              = "text/xml"
			MIMEPlain             = "text/plain"
			MIMEPOSTForm          = "application/x-www-form-urlencoded"
			MIMEMultipartPOSTForm = "multipart/form-data"
			MIMEPROTOBUF          = "application/x-protobuf"
			MIMEMSGPACK           = "application/x-msgpack"
			MIMEMSGPACK2          = "application/msgpack"
			MIMEYAML              = "application/x-yaml"
		)

	# Ԥ����İ󶨽ӿ�ʵ��
		var (
			JSON          = jsonBinding{}
			XML           = xmlBinding{}
			Form          = formBinding{}
			Query         = queryBinding{}
			FormPost      = formPostBinding{}
			FormMultipart = formMultipartBinding{}
			ProtoBuf      = protobufBinding{}
			MsgPack       = msgpackBinding{}
			YAML          = yamlBinding{}
			Uri           = uriBinding{}
			Header        = headerBinding{}
		)

	# ��صķ���
		func Default(method, contentType string) Binding
			* �������󷽷���contentType��ȡBindingʵ��
			* �����GET����δƥ�䵽ContentType�򷵻� Form


	# MustBind
		func (c *Context) Bind(obj interface{}) error 
			* ����contentType��ѡ�����
		
		func (c *Context) BindHeader(obj interface{}) error
		func (c *Context) BindJSON(obj interface{}) error
		func (c *Context) BindQuery(obj interface{}) error
		func (c *Context) BindUri(obj interface{}) error
		func (c *Context) BindXML(obj interface{}) error
		func (c *Context) BindYAML(obj interface{}) error

		func (c *Context) BindWith(obj interface{}, b binding.Binding) error
			* ʹ��ָ���İ���

		* ��Щ�������� MustBindWith �ľ������
			func (c *Context) MustBindWith(obj interface{}, b binding.Binding) error
		
		* ��������󶨴�����������ֹ�������� c.AbortWithError(400, err).SetType(ErrorTypeBind)��
		* ��Ӧ״̬�뱻����Ϊ 400 ���� Content-Type ������Ϊ text/plain; charset=utf-8��
		* ����ڴ�֮����������Ӧ״̬�룬Gin�������־ 
			[GIN-debug] [WARNING] Headers were already written. Wanted to override status code 400 with 422�� 
		* �����ϣ�����õؿ��ư󶨣�����ʹ�� ShouldBind ��Ч����
	
	# ShouldBindWith
		func (c *Context) ShouldBind(obj interface{}) error
			* ����contentType��ѡ�����

		func (c *Context) ShouldBindHeader(obj interface{}) error
		func (c *Context) ShouldBindJSON(obj interface{}) error
		func (c *Context) ShouldBindQuery(obj interface{}) error
		func (c *Context) ShouldBindUri(obj interface{}) error
		func (c *Context) ShouldBindXML(obj interface{}) error
		func (c *Context) ShouldBindYAML(obj interface{}) error

		* ��Щ�������� ShouldBindWith �ľ�����á� 
			func (c *Context) ShouldBindWith(obj interface{}, b binding.Binding) error
		
		* ��������󶨴���Gin �᷵�ش����ɿ����ߴ�����������


		
		func (c *Context) ShouldBindBodyWith(obj interface{}, bb binding.BindingBody) (err error)
			* ���԰�������󶨵�obj��bbָ��body�����ͣ�����err��ʾ�Ƿ�ɹ�
			* ���ţ��֮������һ��Body���Խ��ж�ΰ�
			* ���ڰ�֮ǰ�� body �洢���������У� �������������΢Ӱ�죬�������һ�ξ�����ɰ󶨵Ļ����ǾͲ�Ҫ�����������

-----------------------
��֤
-----------------------
	# Ginʹ�� go-playground/validator.v8 ������֤
		https://github.com/go-playground/validator
		https://pkg.go.dev/gopkg.in/go-playground/validator.v8



-----------------------
һЩdemo
-----------------------
	import (
		"github.com/gin-gonic/gin"
		"log"
		"net/http"
		"streamer/validate"
	)

	type LoginRequest struct {
		Account string `json:"account" form:"account" binding:"required,gte=6,lte=50"`
		Password string `json:"password" form:"password" binding:"required,gte=6,lte=50"`
	}

	func Login (c *gin.Context) {
		var body = new(LoginRequest)
		if err := c.ShouldBind(body); err != nil {
			c.AbortWithStatusJSON(http.StatusBadRequest, gin.H{"msg": "bad body"})
			return
		}
		log.Println(validate.Validator.Struct(body))

		c.JSON(http.StatusOK, gin.H{"msg": "ok"})
	}
