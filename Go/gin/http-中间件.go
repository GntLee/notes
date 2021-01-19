----------------------
req �м��
----------------------
	# ��������������������Ҳ��һ��������
		type HandlerFunc func(*Context)
	
	# Ԥ������м��
		func BasicAuth(accounts Accounts) HandlerFunc
		func BasicAuthForRealm(accounts Accounts, realm string) HandlerFunc
			* auth��֤�������֤�ɹ�����û���Ϣ����ctx�У���� realm Ϊ""����Ĭ��ʹ�� "Authorization Required"��
			* key�� const AuthUserKey = "user"
			
		func Bind(val interface{}) HandlerFunc
			* �����ݰ󶨵�val������󶨳ɹ����ͻ��val���õ�context��
			* Ĭ�ϵ�Key��	const BindKey = "_gin-gonic/gin/bindkey"

		func ErrorLogger() HandlerFunc
			* �����쳣������������쳣����ΪJSON��Ӧ���ͻ���
				return ErrorLoggerT(ErrorTypeAny)
		func ErrorLoggerT(typ ErrorType) HandlerFunc
			* ��������ָ���쳣

		func Logger() HandlerFunc
		func LoggerWithConfig(conf LoggerConfig) HandlerFunc
		func LoggerWithFormatter(f LogFormatter) HandlerFunc
		func LoggerWithWriter(out io.Writer, notlogged ...string) HandlerFunc
			* ��ȡ��־��Ϣ

		func Recovery() HandlerFunc
		func RecoveryWithWriter(out io.Writer) HandlerFunc
			* ����panic�쳣
			* ����� broken connection������ԣ���֮����Ӧ�ͻ���500

		func WrapF(f http.HandlerFunc) HandlerFunc
			* ��http.HandlerFunc ��װ�� HandlerFunc
				return func(c *Context) {
					f(c.Writer, c.Request)
				}

		func WrapH(h http.Handler) HandlerFunc
			* ��װ�� HandlerFunc
				return func(c *Context) {
					h.ServeHTTP(c.Writer, c.Request)
				}
	
	# ����м���Լ�ִ������
		engine := gin.New()
		
		// ���ȫ�ֵ�
		engine.Use(func(context *gin.Context) {
			fmt.Println("��һ����ʼ....")
			// ִ����һ��
			context.Next()
			fmt.Println("��һ������....")
		})
		engine.Use(func(context *gin.Context) {
			fmt.Println("�ڶ�����ʼ....")
			// ִ����һ��
			context.Next()
			fmt.Println("�ڶ�������....")
		})
		engine.GET("/", func(context *gin.Context) {
			context.Writer.Write([]byte("Hello"))
		})

		
		// Ϊָ����URI���
		engine.GET("/", func(context *gin.Context) {
			// ��һ���м��
			context.Next()
		}, func(context *gin.Context) {
			// �ڶ����м��
			context.Next()
		}, func(context *gin.Context) {
			// �������м�� TODO
		})

		// Ϊָ����Group���
		g := engine.Group("/user", func(context *gin.Context) {
			fmt.Println("��һ��")
			context.Next()
		}, func(context *gin.Context) {
			fmt.Println("�ڶ���")
			context.Next()
		})

		server := http.Server{Addr: ":8080", Handler: engine}
		server.ListenAndServe()




		* �����־
			��һ����ʼ....
			�ڶ�����ʼ....
			�ڶ�������....
			��һ������....