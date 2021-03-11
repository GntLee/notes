-----------------------
gzip
-----------------------
	# ���Gzip�м��
		import (
			"github.com/gin-contrib/gzip"
			"github.com/gin-gonic/gin"
		)

		engine := gin.New()

		// ���gzip�м��
		engine.Use(gzip.Gzip(gzip.DefaultCompression))
	

--------------------------
var
--------------------------
	const (
		BestCompression    = gzip.BestCompression
		BestSpeed          = gzip.BestSpeed
		DefaultCompression = gzip.DefaultCompression
		NoCompression      = gzip.NoCompression
	)
	var (
		DefaultExcludedExtentions = NewExcludedExtensions([]string{
			".png", ".gif", ".jpeg", ".jpg",
		})
		DefaultOptions = &Options{
			ExcludedExtensions: DefaultExcludedExtentions,
		}
	)
--------------------------
type
--------------------------
	# type ExcludedExtensions map[string]bool
		
		* ָ��Ҫѹ������ĺ�׺

		func NewExcludedExtensions(extensions []string) ExcludedExtensions
		func (e ExcludedExtensions) Contains(target string) bool
	
	# type ExcludedPaths []string
		
		* ָ��Ҫѹ�������·��
		
		func NewExcludedPaths(paths []string) ExcludedPaths
		func (e ExcludedPaths) Contains(requestURI string) bool
	
	# type ExcludedPathesRegexs []*regexp.Regexp
		
		* ָ��Ҫѹ�������·������

		func NewExcludedPathesRegexs(regexs []string) ExcludedPathesRegexs 
		func (e ExcludedPathesRegexs) Contains(requestURI string) bool

	# type Option func(*Options)
		
		* ѹ���������
		
		func WithDecompressFn(decompressFn func(c *gin.Context)) Option
		func WithExcludedExtensions(args []string) Option
		func WithExcludedPaths(args []string) Option
		func WithExcludedPathsRegexs(args []string) Option
	
	# type Options struct {
			ExcludedExtensions   ExcludedExtensions
			ExcludedPaths        ExcludedPaths
			ExcludedPathesRegexs ExcludedPathesRegexs
			DecompressFn         func(c *gin.Context)
		}

		* ѹ��������

--------------------------
func
--------------------------
	func DefaultDecompressHandle(c *gin.Context)
	func Gzip(level int, options ...Option) gin.HandlerFunc

			
