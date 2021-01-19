------------------------
gin
------------------------

------------------------
var
------------------------
	const EnvGinMode = "GIN_MODE"
	const (
		DebugMode = "debug"			// debugģʽ
		ReleaseMode = "release"		// ��ʽ
		TestMode = "test"			// ����
	)

	var DefaultWriter io.Writer = os.Stdout
	var DefaultErrorWriter io.Writer = os.Stderr
		* Ĭ�ϵ���־���Ŀ�ĵ�


	const (
		MIMEJSON              = binding.MIMEJSON
		MIMEHTML              = binding.MIMEHTML
		MIMEXML               = binding.MIMEXML
		MIMEXML2              = binding.MIMEXML2
		MIMEPlain             = binding.MIMEPlain
		MIMEPOSTForm          = binding.MIMEPOSTForm
		MIMEMultipartPOSTForm = binding.MIMEMultipartPOSTForm
		MIMEYAML              = binding.MIMEYAML
		BodyBytesKey          = "_gin-gonic/gin/bodybyteskey"
	)

	var DebugPrintRouteFunc func(httpMethod, absolutePath, handlerName string, nuHandlers int)
		* Debug��־�������ʽ��Ĭ�Ϸ�����Ĭ��
			debugPrint("%-6s %-25s --> %s (%d handlers)\n", httpMethod, absolutePath, handlerName, nuHandlers)
	


------------------------
type
------------------------
	# type Accounts map[string]string
		* auth��֤���˻�����
	
	# type Context struct {
			Request *http.Request	
				* reuqest
			Writer  ResponseWriter
				* response
			Params Params
				* ���������ֵ��
			Keys map[string]interface{}
				* �������keys
			Errors errorMsgs	// type errorMsgs []*Error
				* �쳣��Ϣ		
			Accepted []string	
				* �ͻ���acceptͷ
		}
		
		* ������������

		func (c *Context) Abort()
		func (c *Context) AbortWithError(code int, err error) *Error
		func (c *Context) AbortWithStatus(code int)
		func (c *Context) AbortWithStatusJSON(code int, jsonObj interface{})
		func (c *Context) AsciiJSON(code int, obj interface{})
			* ��Ӧjson���ݣ���ѷ�ascii�ַ�ת��

		func (c *Context) Bind(obj interface{}) error
			* �����������obj�������ContentType��������


		func (c *Context) BindHeader(obj interface{}) error
		func (c *Context) BindJSON(obj interface{}) error
		func (c *Context) BindQuery(obj interface{}) error
		func (c *Context) BindUri(obj interface{}) error
		func (c *Context) BindWith(obj interface{}, b binding.Binding) error
		func (c *Context) BindXML(obj interface{}) error
		func (c *Context) BindYAML(obj interface{}) error
			* 

		func (c *Context) ClientIP() string
		func (c *Context) ContentType() string
		func (c *Context) Cookie(name string) (string, error)
		func (c *Context) Copy() *Context
			* ctx��Դ�����������������ͷŵ��������Ҫ��һЩ�첽�����д�������
			* ��ô���Ը���һ��context��ȥ

		func (c *Context) Data(code int, contentType string, data []byte)
		func (c *Context) DataFromReader(code int, contentLength int64, contentType string, reader io.Reader, ...)
			* ��ָ����Reader��ȡ������Ӧ���ͻ���
		
		func (c *Context) Deadline() (deadline time.Time, ok bool)
		func (c *Context) DefaultPostForm(key, defaultValue string) string
		func (c *Context) DefaultQuery(key, defaultValue string) string
			* ��ȡ��ѯ��������������ڣ�����Ĭ��ֵ
		
		func (c *Context) Done() <-chan struct{}
		func (c *Context) Err() error
		func (c *Context) Error(err error) *Error
		func (c *Context) File(filepath string)
		func (c *Context) FileAttachment(filepath, filename string)
		func (c *Context) FileFromFS(filepath string, fs http.FileSystem)
		func (c *Context) FormFile(name string) (*multipart.FileHeader, error)
			* ��ȡ�������ϴ��ļ�
		
		func (c *Context) FullPath() string
		func (c *Context) Get(key string) (value interface{}, exists bool)
		func (c *Context) GetBool(key string) (b bool)
		func (c *Context) GetDuration(key string) (d time.Duration)
		func (c *Context) GetFloat64(key string) (f64 float64)
		func (c *Context) GetHeader(key string) string
		func (c *Context) GetInt(key string) (i int)
		func (c *Context) GetInt64(key string) (i64 int64)
		func (c *Context) GetPostForm(key string) (string, bool)
		func (c *Context) GetPostFormArray(key string) ([]string, bool)
		func (c *Context) GetPostFormMap(key string) (map[string]string, bool)
		func (c *Context) GetQuery(key string) (string, bool)
		func (c *Context) GetQueryArray(key string) ([]string, bool)
		func (c *Context) GetQueryMap(key string) (map[string]string, bool)
			* ��ȡmap��ʽ��query����
				/post?ids[a]=1234&ids[b]=hello
				ids := c.QueryMap("ids") => {"a":"1234", "b": "hello"}


		func (c *Context) GetRawData() ([]byte, error)
		func (c *Context) GetString(key string) (s string)
		func (c *Context) GetStringMap(key string) (sm map[string]interface{})
		func (c *Context) GetStringMapString(key string) (sms map[string]string)
		func (c *Context) GetStringMapStringSlice(key string) (smss map[string][]string)
		func (c *Context) GetStringSlice(key string) (ss []string)
		func (c *Context) GetTime(key string) (t time.Time)
		func (c *Context) HTML(code int, name string, obj interface{})
			* ��Ⱦhtml��ָ��״̬�룬ģ�������ļ����·����������
				c.HTML(http.StatusOK, "index.tmpl", gin.H{
					"title": "Main website",
				})

		func (c *Context) Handler() HandlerFunc
			* ���ص�ǰHandler
		func (c *Context) HandlerName() string
			* ���ص�ǰHandler������
		func (c *Context) HandlerNames() []string
			* ����ִ�����ķ������ƣ��������������������fun���ֽ�β�� xxx.func1...xxx.funcn

		func (c *Context) Header(key, value string)
		func (c *Context) IndentedJSON(code int, obj interface{})
		func (c *Context) IsAborted() bool
		func (c *Context) IsWebsocket() bool
			* �жϵ�ǰ�����Ƿ���websocket
			* ��������ͨ���ж�Headerʵ�ֵ�
				Connection upgrade
				Upgrade websocket
			
		func (c *Context) JSON(code int, obj interface{})
		func (c *Context) JSONP(code int, obj interface{})
			* ��Ӧjson����jsonp���ݣ����html�ַ�����unicode����
			* �ص��������ƵĲ�����callback
				 /JSONP?callback=x  => x({\"foo\":\"bar\"})

		func (c *Context) MultipartForm() (*multipart.Form, error)
			* ����multipart.Form������

		func (c *Context) MustBindWith(obj interface{}, b binding.Binding) error
			* ʹ��ָ���İ󶨽ӿ�b�������ݰ󶨵�obj
			* �����Ƿ�ɹ�

		func (c *Context) MustGet(key string) interface{}
		func (c *Context) Negotiate(code int, config Negotiate)
		func (c *Context) NegotiateFormat(offered ...string) string
		func (c *Context) Next()
			* ����HandlerFunc������һ��������������ִ��

		func (c *Context) Param(key string) string
			* ��ȡ·�ɲ�����Ҳ����URI����
				/user/:name				=> c.Param("name")		
				/user/:name/*action		=> c.Param("action")

		func (c *Context) PostForm(key string) string
		func (c *Context) PostFormArray(key string) []string
		func (c *Context) PostFormMap(key string) map[string]string
			* ��������л�ȡmap��ʽ������
				names[first]=thinkerou&names[second]=tianou
				names := c.PostFormMap("names") => {"first": "thinkerou", "second": "tianou"}
		
		func (c *Context) ProtoBuf(code int, obj interface{})
		func (c *Context) PureJSON(code int, obj interface{})
			* ����json�����κα��룬����json 

		func (c *Context) Query(key string) string
		func (c *Context) QueryArray(key string) []string
		func (c *Context) QueryMap(key string) map[string]string
			* ��ȡ��ѯ����

		func (c *Context) Redirect(code int, location string)
			* �ض���ָ��״̬���·������������ԣ�Ҳ�����Ǿ���

		func (c *Context) Render(code int, r render.Render)
		func (c *Context) SSEvent(name string, message interface{})
		func (c *Context) SaveUploadedFile(file *multipart.FileHeader, dst string) error
			* ���ļ��洢��ָ����·��
		
		func (c *Context) SecureJSON(code int, obj interface{})
			* ��ֹ json �ٳ֡���������Ľṹ������ֵ����Ĭ��Ԥ�� "while(1)," ����Ӧ�塣

		func (c *Context) Set(key string, value interface{})
			* �������ݵ�context

		func (c *Context) SetAccepted(formats ...string)
		func (c *Context) SetCookie(name, value string, maxAge int, path, domain string, secure, httpOnly bool)
		func (c *Context) SetSameSite(samesite http.SameSite)
		func (c *Context) ShouldBind(obj interface{}) error
			* �Զ��󶨲�����obj��ע�⣬���ֻ�ܵ���һ�Σ�body�����û��

		func (c *Context) ShouldBindBodyWith(obj interface{}, bb binding.BindingBody) (err error)
			* ���԰�������󶨵�obj��bbָ��body�����ͣ�����err��ʾ�Ƿ�ɹ�
			* ���ţ��֮������һ��Body���Խ��ж�ΰ�
			* ���ڰ�֮ǰ�� body �洢���������У� �������������΢Ӱ�죬�������һ�ξ�����ɰ󶨵Ļ����ǾͲ�Ҫ�����������


		func (c *Context) ShouldBindHeader(obj interface{}) error
		func (c *Context) ShouldBindJSON(obj interface{}) error
		func (c *Context) ShouldBindQuery(obj interface{}) error
			* �����Ѱ󶨲�ѯ������obj
		
		func (c *Context) ShouldBindUri(obj interface{}) error
		func (c *Context) ShouldBindWith(obj interface{}, b binding.Binding) error
		func (c *Context) ShouldBindXML(obj interface{}) error
		func (c *Context) ShouldBindYAML(obj interface{}) error

		func (c *Context) Status(code int)
		func (c *Context) Stream(step func(w io.Writer) bool) bool
		func (c *Context) String(code int, format string, values ...interface{})
		func (c *Context) Value(key interface{}) interface{}
		func (c *Context) XML(code int, obj interface{})
		func (c *Context) YAML(code int, obj interface{})
	
	# type Engine struct {
			RouterGroup
				* ʵ���� RouterGroup
			RedirectTrailingSlash bool
			RedirectFixedPath bool
			HandleMethodNotAllowed bool
			ForwardedByClientIP    bool
			AppEngine bool
			UseRawPath bool
			UnescapePathValues bool
			MaxMultipartMemory int64
				* multipart�����ʱ��ռ�õ�����ڴ棬Ĭ���� 32 MiB
			
			RemoveExtraSlash bool
			HTMLRender render.HTMLRender
			FuncMap    template.FuncMap
		}
		func Default() *Engine
			* ʹ��Ĭ�ϵ�
		
		func New() *Engine
			* �����µ�����

		func (engine *Engine) Delims(left, right string) *Engine
			* ʹ���Զ����ģ������ָ���

		func (engine *Engine) HandleContext(c *Context)
			* �����ض���·��
				r.GET("/test", func(c *gin.Context) {
					c.Request.URL.Path = "/test2"		// �������õ�ǰ��URI
					r.HandleContext(c)					// ִ���ض���·��
				})

		func (engine *Engine) LoadHTMLFiles(files ...string)
			* ����ָ����ģ�����棬���غ������contextʹ��

		func (engine *Engine) LoadHTMLGlob(pattern string)
			* ����ȫ�ֵ�ģ���������ǰ׺
			* ָ��Ŀ¼�µ������ļ�
				router.LoadHTMLGlob("templates/*")
			* ָ��Ŀ¼�µ�����Ŀ¼�������ļ�
				router.LoadHTMLGlob("templates/**/*")

		func (engine *Engine) NoMethod(handlers ...HandlerFunc)
		func (engine *Engine) NoRoute(handlers ...HandlerFunc)
		func (engine *Engine) Routes() (routes RoutesInfo)
		func (engine *Engine) Run(addr ...string) (err error)
		func (engine *Engine) RunFd(fd int) (err error)
		func (engine *Engine) RunListener(listener net.Listener) (err error)
		func (engine *Engine) RunTLS(addr, certFile, keyFile string) (err error)
		func (engine *Engine) RunUnix(file string) (err error)
		func (engine *Engine) SecureJsonPrefix(prefix string) *Engine
		func (engine *Engine) ServeHTTP(w http.ResponseWriter, req *http.Request)
		func (engine *Engine) SetFuncMap(funcMap template.FuncMap)
			* �Զ���ģ�����淽��

		func (engine *Engine) SetHTMLTemplate(templ *template.Template)
			* ʹ���Լ������ģ�������

		func (engine *Engine) Use(middleware ...HandlerFunc) IRoutes
			* ���һ�����߶��
	
	# type errorMsgs []*Error
		
		* �쳣��Ϣ���ϣ��Ǹ�˽��type���ṩ�˹����ķ���
		
		func (a errorMsgs) ByType(typ ErrorType) errorMsgs
		func (a errorMsgs) Last() *Error 
		func (a errorMsgs) Errors() []string
		func (a errorMsgs) JSON() interface{} 
		func (a errorMsgs) MarshalJSON() ([]byte, error)
		func (a errorMsgs) String() string


	# type Error struct {
			Err  error
			Type ErrorType
			Meta interface{}
		}
		
		* �쳣��Ϣ

		func (msg Error) Error() string
		func (msg *Error) IsType(flags ErrorType) bool
		func (msg *Error) JSON() interface{}
		func (msg *Error) MarshalJSON() ([]byte, error)
		func (msg *Error) SetMeta(data interface{}) *Error
		func (msg *Error) SetType(flags ErrorType) *Error
	
	# type ErrorType uint64
		const (
			ErrorTypeBind ErrorType = 1 << 63			// Context.Bind()ʧ��
			ErrorTypeRender ErrorType = 1 << 62			// Context.Render() ʧ��
			ErrorTypePrivate ErrorType = 1 << 0			// ˽�д���?
			ErrorTypePublic ErrorType = 1 << 1			// ��������?
			ErrorTypeAny ErrorType = 1<<64 - 1			// ��ʾ�κ���������
			ErrorTypeNu = 2								// ��ʾ�κ���������
		)

		* �쳣������

	# type H map[string]interface{}
		func (h H) MarshalXML(e *xml.Encoder, start xml.StartElement) error

		* ��ݵ����ݶ�����map���ɣ�key���ַ�����value���������
	
	# type HandlerFunc func(*Context)

		* ִ����

		func BasicAuth(accounts Accounts) HandlerFunc
		func BasicAuthForRealm(accounts Accounts, realm string) HandlerFunc
		func Bind(val interface{}) HandlerFunc
		func ErrorLogger() HandlerFunc
		func ErrorLoggerT(typ ErrorType) HandlerFunc
		func Logger() HandlerFunc
		func LoggerWithConfig(conf LoggerConfig) HandlerFunc
		func LoggerWithFormatter(f LogFormatter) HandlerFunc
		func LoggerWithWriter(out io.Writer, notlogged ...string) HandlerFunc
		func Recovery() HandlerFunc
		func RecoveryWithWriter(out io.Writer) HandlerFunc
		func WrapF(f http.HandlerFunc) HandlerFunc
		func WrapH(h http.Handler) HandlerFunc
	
	# type HandlersChain []HandlerFunc

		* ִ����������
		func (c HandlersChain) Last() HandlerFunc
	
	# type IRouter interface {
			IRoutes
			Group(string, ...HandlerFunc) *RouterGroup
		}
	# type IRoutes interface {
			Use(...HandlerFunc) IRoutes
			Handle(string, string, ...HandlerFunc) IRoutes
			Any(string, ...HandlerFunc) IRoutes
			GET(string, ...HandlerFunc) IRoutes
			POST(string, ...HandlerFunc) IRoutes
			DELETE(string, ...HandlerFunc) IRoutes
			PATCH(string, ...HandlerFunc) IRoutes
			PUT(string, ...HandlerFunc) IRoutes
			OPTIONS(string, ...HandlerFunc) IRoutes
			HEAD(string, ...HandlerFunc) IRoutes
			StaticFile(string, string) IRoutes
			Static(string, string) IRoutes
			StaticFS(string, http.FileSystem) IRoutes
		}
		* ����·�ɣ���·������


	# type LogFormatter func(params LogFormatterParams) string
		* ��־��ʽ������

	# type LogFormatterParams struct {
			Request *http.Request
			TimeStamp time.Time
			StatusCode int
			Latency time.Duration
			ClientIP string
			Method string
			Path string
			ErrorMessage string
			BodySize int
			Keys map[string]interface{} // contains filtered or unexported fields
		}
		
		* ��־�ĸ�ʽ������

		func (p *LogFormatterParams) IsOutputColor() bool
		func (p *LogFormatterParams) MethodColor() string
		func (p *LogFormatterParams) ResetColor() string
		func (p *LogFormatterParams) StatusCodeColor() string

	# type LoggerConfig struct {
			Formatter LogFormatter
				* ��ʽ��������
			Output io.Writer
				* ���Ŀ�ĵ�
			SkipPaths []string
				* Ҫ������·��
		}

		* ��־��¼��

	# type Negotiate struct {
			Offered  []string
			HTMLName string
			HTMLData interface{}
			JSONData interface{}
			XMLData  interface{}
			YAMLData interface{}
			Data     interface{}
		}

	# type Param struct {
			Key   string
			Value string
		}
	
		type Params []Param
		func (ps Params) ByName(name string) (va string)
		func (ps Params) Get(name string) (string, bool)

	# type ResponseWriter interface {
			http.ResponseWriter
			http.Hijacker
			http.Flusher
			http.CloseNotifier
			Status() int
			Size() int
			WriteString(string) (int, error)
			Written() bool
			WriteHeaderNow()
			Pusher() http.Pusher
		}

		* ��Ӧ�ͻ���
	
	# type RouteInfo struct {
			Method      string
			Path        string
			Handler     string
			HandlerFunc HandlerFunc
		}
	
	# type RoutesInfo []RouteInfo
	
	# type RouterGroup struct {
			Handlers HandlersChain
				* handlerִ����
		}
		
		* ·����
		
		func (group *RouterGroup) BasePath() string
			* ��ȡ��ǰ·�����basepath

		func (group *RouterGroup) Group(relativePath string, handlers ...HandlerFunc) *RouterGroup
			* ���ݵ�ǰ·���飬��������һ��·����

		func (group *RouterGroup) HEAD(relativePath string, handlers ...HandlerFunc) IRoutes
		
		func (group *RouterGroup) Any(relativePath string, handlers ...HandlerFunc) IRoutes
		func (group *RouterGroup) Handle(httpMethod, relativePath string, handlers ...HandlerFunc) IRoutes
		func (group *RouterGroup) OPTIONS(relativePath string, handlers ...HandlerFunc) IRoutes
		func (group *RouterGroup) PATCH(relativePath string, handlers ...HandlerFunc) IRoutes
		func (group *RouterGroup) POST(relativePath string, handlers ...HandlerFunc) IRoutes
		func (group *RouterGroup) PUT(relativePath string, handlers ...HandlerFunc) IRoutes
		func (group *RouterGroup) DELETE(relativePath string, handlers ...HandlerFunc) IRoutes
		func (group *RouterGroup) GET(relativePath string, handlers ...HandlerFunc) IRoutes
			* ����http�����Ĵ���

		func (group *RouterGroup) Static(relativePath, root string) IRoutes
			* ָ��Ŀ¼��ӳ�侲̬�ļ�
				router.Static("/static", "/var/www")
				router.Static("/assets", "./assets")
			
		func (group *RouterGroup) StaticFS(relativePath string, fs http.FileSystem) IRoutes
			* ָ��fsӳ�侲̬�ļ�
				router.StaticFS("/static", gin.Dir("C:\\", true))
				router.StaticFS("/more_static", http.Dir("my_file_system"))

		func (group *RouterGroup) StaticFile(relativePath, filepath string) IRoutes
			* ָ����̬�ļ���ӳ��
				router.StaticFile("/favicon.ico", "./resources/favicon.ico")

		func (group *RouterGroup) Use(middleware ...HandlerFunc) IRoutes
			* ���ȫ�ֵ��м��
	
	
		

------------------------
func
------------------------
	func CreateTestContext(w http.ResponseWriter) (c *Context, r *Engine)
	func Dir(root string, listDirectory bool) http.FileSystem
		* ���� http.FileSystem��listDirectory ָ���Ƿ�Ҫ�г�Ŀ¼
	
	func DisableBindValidation()
	func DisableConsoleColor()
		* ���ÿ���̨��ɫ������־д���ļ�ʱ����Ҫ����̨��ɫ��

	func EnableJsonDecoderDisallowUnknownFields()
	func EnableJsonDecoderUseNumber()
	func ForceConsoleColor()
		* ǿ���ڿ���̨�������ɫ����־

	func IsDebugging() bool
		 * �Ƿ���DEBUGģʽ

	func Mode() string
	func SetMode(value string)
		* ��ȡ��������ģʽ