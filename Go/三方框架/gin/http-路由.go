----------------------
req·������
----------------------
	# ·����ؽӿ�
		* ִ��������ִ������
			type HandlerFunc func(*Context)
			type HandlersChain []HandlerFunc
		
		* IRouter����������·�ɵĴ������ӿڣ���������·��������·������
			type IRouter interface {
				IRoutes
				Group(string, ...HandlerFunc) *RouterGroup
			}

		* IRoutes���������е�·�ɴ���ӿڡ�
			type IRoutes interface {
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
		
		* RouterGroup���ڲ���������·�ɵģ�һ��RouterGroup��һ��ǰ׺��һ������������飨�м������
			type RouterGroup struct {
				Handlers HandlersChain
					* handlerִ����
			}

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
				
			func (group *RouterGroup) StaticFS(relativePath string, fs http.FileSystem) IRoutes
				* ָ��fsӳ�侲̬�ļ�
					engine.Group("/").StaticFS("/static", gin.Dir("C:\\", true))

			func (group *RouterGroup) StaticFile(relativePath, filepath string) IRoutes
				* ָ����̬�ļ�

			func (group *RouterGroup) Use(middleware ...HandlerFunc) IRoutes
		
		* ·����Ϣ
			type RouteInfo struct {
					Method      string
					Path        string
					Handler     string
					HandlerFunc HandlerFunc
				}
			type RoutesInfo []RouteInfo
			
					
	# Ⱥ��·��
		engine := gin.Default()
		user := engine.Group("/user")
		user.GET("/login", func(context *gin.Context) {
			context.Writer.Write([]byte(context.Request.URL.String()))
		})
		user.GET("/submit", func(context *gin.Context) {
			context.Writer.Write([]byte(context.Request.URL.String()))
		})
		user.GET("/read", func(context *gin.Context) {
			context.Writer.Write([]byte(context.Request.URL.String()))
		})

