---------------------------
embed
---------------------------
	# �﷨
		//go:embed [pattern]
	
	# ֧�ֵ�����	
		[]byte		��ʾ���ݴ洢Ϊ�����Ƹ�ʽ�����ֻʹ��[]byte��string��Ҫ��import (_ "embed")����ʽ����embed��׼��
		string		��ʾ���ݱ������utf8������ַ�������˲�Ҫ�������ʽǶ��������ļ�����ͼƬ������embed�Ĺ���ͬ[]byte
		embed.FS	��ʾ�洢����ļ���Ŀ¼�Ľṹ��[]byte��stringֻ�ܴ洢�����ļ�
			//go:embed texts
			var dir embed.FS

			// ����ûʲô����
			//go:embed texts/*
			var files embed.FS
				
		
		* ���ֻʹ��[]byte��string��Ҫ��import _ "embed" ����ʽ����embed��׼��
		* ����Ƕ�����ݵı���һ��Ҫ��ʹ��var����
		* FS֧��һ����Ƕ������Դ�����Զ������ظ���
			//go:embed hello.txt
			//go:embed hello2.txt
			var f embed.FS

			//go:embed hello.txt hello2.txt
			var f embed.FS
		


	# pattern����
		* Ĭ�ϵĸ�Ŀ¼��module��Ŀ¼��ʼ��·����ͷ�����Դ�/
		* ����windows��������ϵͳ·���ָһ��ʹ��/

		* ���ƥ�䵽����Ŀ¼����ôĿ¼�µ������ļ����ᱻǶ�룬������а�������Ŀ¼�������Ŀ¼���еݹ�Ƕ�롣(����ͨ��ͨ�����Ƕ��/�ų�ָ�����ļ�)

		* golang�ڶ�Ŀ¼���еݹ�Ƕ���ʱ��(ֻд�ļ�������)������������»��ߣ�_���͵㣨.����ͷ���ļ���Ŀ¼��
		* ע��: * �����еݹ��ԣ���Ƕ��.��_��ͷ���ļ����ļ���
		* ��Щ�ļ����ڲ����ļ�ϵͳ��Ϊ�����ļ�
			//go:embed images
			var images embed.FS // ������.b.jpg��_photo_metadataĿ¼

			//go:embed images/*
			var images embed.FS // ע�⣡���� �������.b.jpg��_photo_metadataĿ¼

			//go:embed images/.b.jog
			var bJPG []byte // ��ȷ�����ļ���Ҳ���ᱻ���ԡ���
		
		* ��֧�־���·������֧��·���а���.��..,�����Ƕ��goԴ�ļ����ڵ�·����ʹ��*:
			//go:embed *
			var f embed.FS

		* pattern �� path.Matc h��֧�ֵ�·��ͨ���
		* �����linuxϵͳ�ϣ�������man 7 glob�鿴����ϸ�Ľ̳�
		
			?				��������һ���ַ�����������������ţ�
			*				����0����������ַ���ɵ��ַ�������������������ţ�
			[...]��[!...]	��������һ��ƥ�䷽�������ַ����ַ���!��ʾ���ⲻƥ�䷽�������ַ����ַ�
			[a-z]��[0-9]	����ƥ��a-z����һ���ַ����ַ�����0-9�е�����һ������
			**				����ϵͳ֧�֣�*���ܿ�Ŀ¼ƥ�䣬**���ԣ�����Ŀǰ��golang�к�*��ͬ���
		
		* ���Ŀ¼/�ļ��пո������ʹ������
			//go:embed "he llo.txt" `hello-2.txt`
			var f embed.FS
		
		* ͨ�����Ĭ��Ŀ¼��Դ�ļ����ڵ�Ŀ¼��ͬһĿ¼����������ֻ��ƥ��ͬĿ¼�µ��ļ���Ŀ¼������ƥ�䵽��Ŀ¼
			.
			������ code
			��   ������ main.go
			������ go.mod
			������ imgs
			��   ������ jpg
			��   ��   ������ a.jpg
			
			* �����main.go�ɼ�����Դֻ��codeĿ¼������Ŀ¼����ļ�����imgs��texts����ļ����޷�ƥ�䵽��
	
	# Demo
		// ӳ��Ŀ¼
		//go:embed static
		var fileSystem embed.FS

		// ӳ���ļ�����Ϊ�ַ���
		//go:embed static/demo.txt
		var txtFile string

		// ӳ���ļ�����Ϊ�ֽ�����
		//go:embed static/demo.txt
		var binFile []byte

		func main(){
			// entry, err := fileSystem.ReadDir(".") // entry ֻ��һ��Ԫ�أ����� /static Ŀ¼
			// ��ȡ�ļ����µ������ļ�
			entry, err := fileSystem.ReadDir("static")
			if err != nil {
				fmt.Println(err.Error())
			}
			for _, e := range entry {
				fmt.Println(e.Name())
				// demo.txt
				// index.html
			}

			fmt.Println(txtFile)
			// 123
			fmt.Println(string(binFile))
			// 123
		}

---------------------------
��̬��Դӳ��
---------------------------
	//go:embed static
	var fsys embed.FS
	http.Handle("/", http.FileServer(http.FS(fsys)))


	http.Handle("/static/", http.StripPrefix("/static/", http.FileServer(http.FS(fsys))))

---------------------------
ģ������ӳ��
---------------------------
	# �ṹ
		|-main.exe
		|-resources
			|-templates
				|-default.html
				|-index
					|-index.html
		
	# ʵ��
		import (
			"embed"
			"fmt"
			"github.com/gin-gonic/gin"
			"html/template"
			"io"
			"io/fs"
			"log"
			"net/http"
			"os"
			"path/filepath"
			"strings"
		)

		// ӳ����ԴĿ¼Ϊһ���ļ�ϵͳ
		//go:embed resources
		var resource embed.FS

		func main(){
			// ���ļ�ϵͳ�� ��resources/templates"�� ����HTMLģ������
			templates, err := loadTemplates(resource, "resources/templates")
			if err != nil {
				log.Fatalf(err.Error())
			}
			for _, v := range templates.Templates() {
				fmt.Printf("����ģ�����棺%s\n", v.Name())
			}
			router := gin.New()
			router.SetHTMLTemplate(templates)
			router.GET("/", func(context *gin.Context) {
				context.HTML(http.StatusOK, "default.html", nil)
			})
			router.GET("/index", func(context *gin.Context) {
				context.HTML(http.StatusOK, "index/index.html", nil)
			})
			
			server := &http.Server {
				Addr:    ":1024",
				Handler: router,
			}
			if err := server.ListenAndServe(); err != nil {
				log.Fatalf(err.Error())
			}
		}

		func loadTemplates(fileSystem fs.FS, root string) (*template.Template, error) {

			templates := template.New("templates")

			// ��������ӷ�����ģ�����������ʹ�õ��˷����������ڻ��쳣
			templates.Funcs(map[string] interface{} {})

			// ����ģ������Ŀ¼���������ļ�������ģ�壬��Ŀ¼�����·����Ϊģ������
			err := fs.WalkDir(fileSystem, root, func(path string, d fs.DirEntry, err error) error {
				if !d.IsDir() {
					absPath, err := filepath.Rel(root, path)
					if err != nil {
						return err
					}
					// ��ȡÿһ���ļ����ڴ棬ʹ�����·����Ϊ��ģ�����ơ�
					err = func () error {
						file, err := fileSystem.Open(path)
						if err != nil {
							return err
						}
						defer file.Close()
						context, err := io.ReadAll(file)
						if err != nil {
							return err
						}
						// ���ļ��ָ�����ͳһ�滻Ϊ ��/��
						templates.New(strings.ReplaceAll(absPath, string(os.PathSeparator), "/")).Parse(string(context))
						return nil
					}()
					return err
				}
				return nil
			})
			if err != nil {
				return nil, err
			}
			return templates, nil
		}
	

	# ��Gin��ʹ��
		//go:embed resources
		var Resources embed.FS

		func LoadTemplates(fileSystem fs.FS, root string) (*template.Template, error) {

			templates := template.New("templates")

			// ��������ӷ�����ģ�����������ʹ�õ��˷����������ڻ��쳣
			templates.Funcs(map[string] interface{} {})

			// ����ģ������Ŀ¼���������ļ�������ģ�壬��Ŀ¼�����·����Ϊģ������
			err := fs.WalkDir(fileSystem, root, func(path string, d fs.DirEntry, err error) error {
				if !d.IsDir() {
					absPath, err := filepath.Rel(root, path)
					if err != nil {
						return err
					}
					// ��ȡÿһ���ļ����ڴ棬ʹ�����·����Ϊ��ģ�����ơ�
					err = func () error {
						file, err := fileSystem.Open(path)
						if err != nil {
							return err
						}
						defer file.Close()
						context, err := io.ReadAll(file)
						if err != nil {
							return err
						}
						// ���ļ��ָ�����ͳһ�滻Ϊ ��/��
						templates.New(strings.ReplaceAll(absPath, string(os.PathSeparator), "/")).Parse(string(context))
						return nil
					}()
					return err
				}
				return nil
			})
			if err != nil {
				return nil, err
			}
			return templates, nil
		}

		// ����ģ������
		templates, err := LoadTemplates(Resources, "resources/templates")
		if err != nil {
			log.Fatalf("����ģ�������쳣��%s\n", err.Error())
		}
		router.SetHTMLTemplate(templates)

		// ��̬��Դ·��
		staticFs, err := fs.Sub(Resources, "resources/static")
		if err != nil {
			log.Fatalf("��̬��Դ·����ȡ�쳣��%s\n", err.Error())
		}
		router.StaticFS("/static", http.FS(staticFs))

		* Ŀ¼�ṹ
			static      ��̬��Դ�ļ���ͨ�� /static ������
			templates   ģ������Ŀ¼
			main.go