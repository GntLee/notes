--------------------------
server
--------------------------
	# HTTP������
		type Server struct {
			Addr string
			Handler Handler
				* ָ�������������Ϊ�գ���ʹ�� http.DefaultServeMux

			TLSConfig *tls.Config
			ReadTimeout time.Duration
			ReadHeaderTimeout time.Duration
			WriteTimeout time.Duration
			IdleTimeout time.Duration
			MaxHeaderBytes int
			TLSNextProto map[string]func(*Server, *tls.Conn, Handler)
			ConnState func(net.Conn, ConnState)
			ErrorLog *log.Logger
			BaseContext func(net.Listener) context.Context
			ConnContext func(ctx context.Context, c net.Conn) context.Context
		}
		func (srv *Server) Close() error
		func (srv *Server) ListenAndServe() error
		func (srv *Server) ListenAndServeTLS(certFile, keyFile string) error
		func (srv *Server) RegisterOnShutdown(f func())
		func (srv *Server) Serve(l net.Listener) error
		func (srv *Server) ServeTLS(l net.Listener, certFile, keyFile string) error
			* ��ʼ����
			* ����������� Shutdown() ֹͣ�����򷵻� ErrServerClosed �쳣��Ϣ

		func (srv *Server) SetKeepAlivesEnabled(v bool)
		func (srv *Server) Shutdown(ctx context.Context) error
			* �رշ�������Servexx �ȷ����᷵�� ErrServerClosed �쳣��Ϣ

	# һЩ���������������ķ���
		func ListenAndServe(addr string, handler Handler) error
		func ListenAndServeTLS(addr, certFile, keyFile string, handler Handler) error
			* ָ��handler�ӿڣ�����һ��http/https����
			* ���handlerΪnil������������£�ʹ�� DefaultServeMux
				server := &Server{Addr: addr, Handler: handler}
				return server.ListenAndServe()
		
		func Serve(l net.Listener, handler Handler) error
		func ServeContent(w ResponseWriter, req *Request, name string, modtime time.Time, ...)
		func ServeFile(w ResponseWriter, r *Request, name string)
		func ServeTLS(l net.Listener, handler Handler, certFile, keyFile string) error

	
	# HTTP��������
		type ServeMux struct {
		}
		
		* һ��HTTP����������
		* ����ÿ�����������URL��ע��ģʽ�б�����ƥ�䣬��������URL��ƥ���ģʽ�Ĵ�������

		func NewServeMux() *ServeMux
		func (mux *ServeMux) Handle(pattern string, handler Handler)
		func (mux *ServeMux) HandleFunc(pattern string, handler func(ResponseWriter, *Request))
			* ����pattern����http������

		func (mux *ServeMux) Handler(r *Request) (h Handler, pattern string)
			* ����ָ�����󣬷������ڴ����������handler�Լ�pattern
			* �����������·���㷨��ʵ��

		func (mux *ServeMux) ServeHTTP(w ResponseWriter, r *Request)
			* ����Handller���صĴ�������ִ�д���

	
	# Http�������ӿ�
		type Handler interface {
			ServeHTTP(ResponseWriter, *Request)
		}

		func FileServer(root FileSystem) Handler
			* ����һ���ļ�HTTP����

		func NotFoundHandler() Handler
			* ����һ��404����

		func RedirectHandler(url string, code int) Handler
			* ����һ���ض������

		func StripPrefix(prefix string, h Handler) Handler
		func TimeoutHandler(h Handler, dt time.Duration, msg string) Handler
	
	# Http��������, ʵ���� �������ӿ�
		type HandlerFunc func(ResponseWriter, *Request)

		func (f HandlerFunc) ServeHTTP(w ResponseWriter, r *Request)
	
	# Header
		type Header map[string][]string

		func (h Header) Add(key, value string)
		func (h Header) Clone() Header
		func (h Header) Del(key string)
		func (h Header) Get(key string) string
		func (h Header) Set(key, value string)
		func (h Header) Values(key string) []string
		func (h Header) Write(w io.Writer) error
		func (h Header) WriteSubset(w io.Writer, exclude map[string]bool) error
	

	# Cookie
		type Cookie struct {
			Name  string
			Value string
			Path       string    // optional
			Domain     string    // optional
			Expires    time.Time // optional
			RawExpires string    // for reading cookies only
			MaxAge   int
			Secure   bool
			HttpOnly bool
			SameSite SameSite
			Raw      string
			Unparsed []string // Raw text of unparsed attribute-value pairs
		}
		func (c *Cookie) String() string
	
		* Cookie����
			type CookieJar interface {
				SetCookies(u *url.URL, cookies []*Cookie)
				Cookies(u *url.URL) []*Cookie
			}

			* ʵ�ֱ����ǰ�ȫ�ģ����Ա����goroutineͬʱʹ��
			* ϵͳ�ṩ��ʵ��
				cookiejar.Jar

		
		* Cookie��SameSite����
			type SameSite int
			const (
				SameSiteDefaultMode SameSite = iota + 1
				SameSiteLaxMode
				SameSiteStrictMode
				SameSiteNoneMode
			)
	
	# Demo
		package main
			import (
				"fmt"
				"net/http"
			)
			func main() {
				// ����·��
				serverMux := http.NewServeMux()
				serverMux.HandleFunc("/", func(writer http.ResponseWriter, request *http.Request) {
					defer request.Body.Close()
					writer.WriteHeader(http.StatusOK)
					writer.Write([]byte("Hello World"))
					writer.Header().Set("Content-Type", "text/plan")
				})

				// ����������
				server := http.Server{
					Addr: ":8080",
					Handler: serverMux,
				}

				// ��������
				err := server.ListenAndServe()
				if err != nil {
					fmt.Println(err)
				}
			}