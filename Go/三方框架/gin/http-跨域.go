------------------
cors
------------------
	# ��
		github.com/gin-contrib/cors
	
	# type
		type Config struct {
			AllowAllOrigins bool
			AllowOrigins []string
			AllowOriginFunc func(origin string) bool
			AllowMethods []string
			AllowHeaders []string
			AllowCredentials bool
			ExposeHeaders []string
			MaxAge time.Duration
			AllowWildcard bool
			AllowBrowserExtensions bool
			AllowWebSockets bool
			AllowFiles bool
		}
		
		func (c *Config) AddAllowMethods(methods ...string) 
		func (c *Config) AddAllowHeaders(headers ...string)
		func (c *Config) AddExposeHeaders(headers ...string)
		func (c *Config) Validate() error


	# func
		func DefaultConfig() Config 
			* ����Ĭ�ϵ�config

		func Default() gin.HandlerFunc 
			* ����Ĭ�ϵ�handler
		
		func New(config Config) gin.HandlerFunc 
			* �������÷���handler

	# Demo
		router.Use(cors.New(cors.Config{
			AllowMethods:     []string{"PUT", "PATCH", "POST", "OPTIONS"},
			AllowHeaders:     []string{"Origin"},
			ExposeHeaders:    []string{"Content-Length"},
			AllowCredentials: true,
			AllowOriginFunc: func(origin string) bool {
				return true
			},
			MaxAge: 12 * time.Hour,
		}))
	

------------------------
�����Լ��ĺ�ʹ
------------------------
import (
	"github.com/gin-gonic/gin"
	"net/http"
)

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
		return
	}
	context.Next()
}

