---------------------
request
---------------------
	# type Request struct {
			Method string
			URL *url.URL
			Proto      string // "HTTP/1.0"
			ProtoMajor int    // 1
			ProtoMinor int    // 0
			Header Header
			Body io.ReadCloser
			GetBody func() (io.ReadCloser, error)
			ContentLength int64
			TransferEncoding []string
			Close bool
			Host string
			Form url.Values
				* ��������

			PostForm url.Values
				* form��
			MultipartForm *multipart.Form
				* �ಿ�����壬�����ȵ���ParseMultipartForm������ֶβŲ���Ϊnil

			Trailer Header
				* �ͻ�������ͷ
			RemoteAddr string
				* �ͻ��˵�ַ
			RequestURI string
				* URI��ַ
			TLS *tls.ConnectionState
			Cancel <-chan struct{}
			Response *Response
				* ��ȡResponse����
		}
		func NewRequest(method, url string, body io.Reader) (*Request, error)
		func NewRequestWithContext(ctx context.Context, method, url string, body io.Reader) (*Request, error)
		func ReadRequest(b *bufio.Reader) (*Request, error)

		func (r *Request) AddCookie(c *Cookie)
		func (r *Request) BasicAuth() (username, password string, ok bool)
		func (r *Request) Clone(ctx context.Context) *Request
		func (r *Request) Context() context.Context
		func (r *Request) Cookie(name string) (*Cookie, error)
			* ��ȡcookie����������ڣ������쳣 http.ErrNoCookie
		
		func (r *Request) Cookies() []*Cookie
		func (r *Request) FormFile(key string) (multipart.File, *multipart.FileHeader, error)
		func (r *Request) FormValue(key string) string
		func (r *Request) MultipartReader() (*multipart.Reader, error)
			* ��ȡmultipart/form-data �� multipart/mixed ������
			* ������ǣ��򷵻��쳣	http.ErrNotMultipart
				reader,_ := request.MultipartReader()
				for part, err := reader.NextPart(); err != io.EOF {
					headers := part.Header		// ����ͷ
					count, err := part.Read(make([]byte, 1024)) 	// ��ȡ����
					fileName := part.FileName()	// �ļ�����
					formName := part.FormName()	// ������
					err := part.Close()		// �ر���Դ
				}
		
		func (r *Request) ParseForm() error
			* ��������Ĳ�ѯ������POST���������Ҳ�������r.Form��r.PostForm��ֵ
			* ParseForm���ݵȵ�

		func (r *Request) ParseMultipartForm(maxMemory int64) error
			* �����multipar������Ҫ�ȵ������������ָ���ڴ�����洢�ռ䣬��������ռ�����ݻᱻIO����ʱ�ļ�
			* ParseMultipartForm���Զ�����ParseForm

		func (r *Request) PostFormValue(key string) string
		func (r *Request) ProtoAtLeast(major, minor int) bool
		func (r *Request) Referer() string
		func (r *Request) SetBasicAuth(username, password string)
		func (r *Request) UserAgent() string
		func (r *Request) WithContext(ctx context.Context) *Request
		func (r *Request) Write(w io.Writer) error
		func (r *Request) WriteProxy(w io.Writer) error

------------------------------------------
request ����multipart/formdata
------------------------------------------
import (
	"fmt"
	"io"
	"net/http"
	"os"
	"path/filepath"
)

func main(){
	serverMux := http.NewServeMux()
	serverMux.HandleFunc("/upload", func(writer http.ResponseWriter, request *http.Request) {
		// �Ƚ���
		request.ParseMultipartForm(1024)

		// �ಿ������
		form := request.MultipartForm

		if form == nil {
			return
		}

		defer request.MultipartForm.RemoveAll() // ɾ�����й�������ʱ�ļ�
		defer request.Body.Close()

		// ������ͨ���� map[string][]string
		values := form.Value
		for key, value := range values {
			fmt.Printf("name=%v value=%v\n", key, value)
		}

		// �ļ����� map[string][]*FileHeader
		file := form.File["file"][0]
		fileName := file.Filename
		size := file.Size
		fmt.Printf("�ļ�����:%s, �ļ���С:%d\n", fileName, size)

		// ��ȡ����ͷ��Ϣ map[string][]string
		header := file.Header
		for name, val := range header {
			fmt.Printf("header name=%s, value=%s\n", name, val)
		}

		// ���������ļ�
		localFile, err := os.OpenFile(filepath.Join("D:", "upload", fileName), os.O_CREATE | os.O_WRONLY | os.O_EXCL , os.ModePerm)
		if err != nil {
			fmt.Fprintf(os.Stderr, "�����ļ������쳣:%v\n", err)
			return
		}
		defer localFile.Close()

		// ��ȡ���ϴ����ļ���Ϣ multipart.File
		uploadFile, err := file.Open()
		if err != nil {
			fmt.Fprintf(os.Stderr, "��ȡ�������쳣:%v\n", err)
			return
		}

		// io������
		io.Copy(localFile, uploadFile)

		// ��Ӧ�ͻ���
		writer.WriteHeader(http.StatusOK)
		writer.Header().Set("Content-Type", "text/plan")
		writer.Write([]byte("success"))
	})
	server := http.Server{
		Addr: "0.0.0.0:1024",
		Handler: serverMux,
	}
	server.ListenAndServe()
}