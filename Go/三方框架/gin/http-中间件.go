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

			// ��ȡ������
			var body, err = io.ReadAll(ctx.Request.Body)
			if err != nil {
				log.Printf("BODY��ȡ�쳣��%s\n", err.Error())
			}

			// ��������������
			ctx.Request.Body = io.NopCloser(bytes.NewReader(body))

			// ��¼��Ӧ��־
			var logWriter = &LogWriter {
				ResponseWriter: ctx.Writer,
				Buffer:    &bytes.Buffer{},
			}
			ctx.Writer = logWriter

			// ��ʼ�ͽ���ʱ��
			var startTime = time.Now().Unix()
			ctx.Next()
			var endTime = time.Now().Unix()

			var method = ctx.Request.Method					// ���󷽷�
			var fullPath = ctx.FullPath()					// ӳ��·��
			var requestURI = ctx.Request.URL.RequestURI()	// ������URL��������ѯ����
			var status = ctx.Writer.Status()				// ��Ӧ״̬��
			var responseBody = logWriter.Buffer.String()	// �ַ�����Ӧ�壬�����Ӧ�Ĳ����ַ���������Ҫ�������

			log.Printf("method=%s, path=%s, uri=%s, startTime=%d, endTime=%d, reqBody=%s, respBody=%s, status=%d\n",
				method, fullPath, requestURI, startTime, endTime, string(body), responseBody, status,
			)
		}
