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
			* ����panic�쳣�� out ��ָ���쳣��Ϣ������أ����������dump����Ϣ��Ĭ���� stdout
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


----------------------
���õ�һЩ�м��
----------------------
	# �����Ϣ������
		func MaxBody (size int64) func(ctx *gin.Context){
			return func(ctx *gin.Context) {
				ctx.Request.Body = http.MaxBytesReader(ctx.Writer, ctx.Request.Body , size)
				ctx.Next()
			}
		}
		
		* ����������ж�ȡ��ʱ�򣬴�С����������ƣ��ͻ᷵���쳣��
			errors.New("http: request body too large")
		* ���ctx.Writerʵ���� requestTooLarge() �����Ļ�������ִ�� requestTooLarge() ����


	# ��������ʱ����
		func TimeOut (timeout time.Duration) func(*gin.Context) {
			return func(c *gin.Context) {
				ctx, cancel := context.WithTimeout(c.Request.Context(), timeout)
				defer cancel()

				c.Request = c.Request.WithContext(ctx)

				c.Next()
			}
		}

		* �����������Ƶ�ǰ�����ʱ�䣬�����Ǵ�����һ����ʱctx�󶨵�Request
		* ҵ��controller�У����Ի�ȡ�����ctxȥִ�������ķ�����ã�������ã�A => B => C��
			_, err := ctxhttp.Get(c.Request.Context(), http.DefaultClient, "https://www.google.com/")
			if err != nil {
				log.Fatalf("ctxhttp.Get err: %v", err)
			}

	
	# ��ȡ��Ӧ/����BODY�м��
		type LogWriter struct {
			gin.ResponseWriter
			Buffer *bytes.Buffer
		}
		func (l LogWriter) Write(data []byte) (int, error){
			if count, err := l.Buffer.Write(data); err != nil {
				return count, err
			}
			return l.ResponseWriter.Write(data)
		}

		// Log ��¼������Ӧ��־�ȵ���Ϣ
		func Log (ctx *gin.Context ){

			// ��¼��Ӧ��־
			var logWriter = &LogWriter {
				ResponseWriter: ctx.Writer,
				Buffer:    &bytes.Buffer{},
			}
			ctx.Writer = logWriter

			// ���ڵ�ǰ��������ΨһID
			var requestedId = uuid.New().String()
			ctx.Writer.Header().Set(constant.HttpHeaders.RequestId, requestedId)

			// ��ȡ������
			var body, err = io.ReadAll(ctx.Request.Body)
			if err != nil {
				response.ErrorResponse(ctx, err)  // ��ȡBOdy�쳣��ֱ�Ӹ��ͻ�����Ӧ�쳣��Ϣ
				ctx.Abort()  					  // ���ִ����
			} else {
				// ��������������
				ctx.Request.Body = io.NopCloser(bytes.NewReader(body))
			}

			// ����ʱ�䣬Ĭ��Ϊ-1����ʾû�н��뵽ҵ��ִ�в���
			var executionTime int64 = -1
			if !ctx.IsAborted() {
				var startTime = time.Now().Unix()
				ctx.Next()
				var endTime = time.Now().Unix()
				executionTime = endTime - startTime
			}

			var method = ctx.Request.Method					// ���󷽷�
			var fullPath = ctx.FullPath()					// ӳ��·��
			var requestURI = ctx.Request.URL.RequestURI()	// ������URL��������ѯ����
			// TODO ����Header
			// TODO ��ӦHeader
			var status = ctx.Writer.Status()				// ��Ӧ״̬��
			var requestBody = string(body)					// ������
			var responseBody = logWriter.Buffer.String()	// �ַ�����Ӧ�壬�����Ӧ�Ĳ����ַ���������Ҫ�������

			// �����־
			log.Printf("id=%s, method=%s, path=%s, uri=%s, time=%d, reqBody=%s, respBody=%s, status=%d\n",
				requestedId, method, fullPath, requestURI, executionTime, requestBody, responseBody, status,
			)
		}
	
	# ���������м��
		func Cors (context *gin.Context ){
			origin := context.GetHeader("Origin")
			if origin != "" {
				context.Header("Access-Control-Allow-Origin", origin)
				requestHeader := context.GetHeader("Access-Control-Request-Headers")
				if requestHeader != "" {
					context.Header("Access-Control-Allow-Headers", requestHeader)
				}
				context.Header("Access-Control-Allow-Credentials", "true")
				context.Header("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS, DELETE, PATCH")
				context.Header("Access-Control-Expose-Headers", "*")
				context.Header("Access-Control-Max-Age", "3000")

				if context.Request.Method == http.MethodOptions {
					context.AbortWithStatus(http.StatusNoContent)
				}
			}
			context.Next()
		}