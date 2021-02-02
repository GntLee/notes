---------------------------
http
---------------------------
	# Webѧϰ
		https://gowebexamples.com/

---------------------------
http ģ���һЩ����
---------------------------
	func CanonicalHeaderKey(s string) string
	func DetectContentType(data []byte) string
	func Error(w ResponseWriter, error string, code int)

	func Handle(pattern string, handler Handler)
	func HandleFunc(pattern string, handler func(ResponseWriter, *Request))
		* ����·��������HTTP��������ע�ᵽĬ�ϵ�ServeMux (DefaultServeMux)

	func ListenAndServe(addr string, handler Handler) error
	func ListenAndServeTLS(addr, certFile, keyFile string, handler Handler) error
		* ����һ��HTTP����
		* ���handlerΪnil������������£�ʹ��DefaultServeMux

	func MaxBytesReader(w ResponseWriter, r io.ReadCloser, n int64) io.ReadCloser
	func NotFound(w ResponseWriter, r *Request)
	func ParseHTTPVersion(vers string) (major, minor int, ok bool)
	func ParseTime(text string) (t time.Time, err error)
	func ProxyFromEnvironment(req *Request) (*url.URL, error)
	func ProxyURL(fixedURL *url.URL) func(*Request) (*url.URL, error)
	func Redirect(w ResponseWriter, r *Request, url string, code int)
	func Serve(l net.Listener, handler Handler) error
	func ServeContent(w ResponseWriter, req *Request, name string, modtime time.Time, ...)
	func ServeFile(w ResponseWriter, r *Request, name string)
	func ServeTLS(l net.Listener, handler Handler, certFile, keyFile string) error
	func SetCookie(w ResponseWriter, cookie *Cookie)
	func StatusText(code int) string

---------------------------
http GET ����
---------------------------
import (
	"fmt"
	"io/ioutil"
	"net/http"
	"net/url"
	"os"
)

func main(){
	// url
	target, err := url.Parse("http://localhost")
	if err != nil {
		fmt.Fprintf(os.Stderr, "URL�쳣��%s\n", err.Error())
		os.Exit(1)
	}

	// ��ȡ�����޸�/���/ɾ���������
	query := target.Query()
	query.Add("name", "test")
	target.RawQuery = query.Encode()

	// ����http�ͻ���
	client := http.Client{}

	// ����http����
	req, err := http.NewRequest(http.MethodGet, target.String(), nil)

	// ͨ���ͻ��ˣ�ִ��http����
	resp, err := client.Do(req)
	if err != nil {
		fmt.Fprintf(os.Stderr, "�����쳣��%s\n", err.Error())
		os.Exit(1)
	}
	defer resp.Body.Close()

	// ��ȡ��Ӧ��
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		fmt.Fprintf(os.Stderr, "��Ӧ��ȡ��%s\n", err.Error())
		os.Exit(1)
	}
	fmt.Println(string(body))
}


---------------------------
http POST ����
---------------------------


---------------------------
http Multipart ����
---------------------------
	// �ܵ���
	r, w := io.Pipe()
	defer r.Close()

	// ���� multipart��ָ��writer
	formWriter := multipart.NewWriter(w)

	go func() {

		defer w.Close()

		var writer io.Writer

		// ���ٹ�����ͨ���key/value�����ַ���
		formWriter.WriteField("lang", "PHP��������õ�����")

		// ������ͨ�ı��ͨ��Writerд������
		writer, _ = formWriter.CreateFormField("lang")
		writer.Write([]byte("Java����������õ�����"))

		// �����ļ����ָ�������ƣ��Լ��ļ����ƣ�ͨ��Writerд�����ݣ�Ĭ�ϵ�ContentType �� application/octet-stream
		writer, _ = formWriter.CreateFormFile("file", "app.json")
		jsonVal, _ := json.Marshal(map[string] string {"name": "KevinBlandy"})
		writer.Write(jsonVal)

		// �Զ���part�����������Զ����header
		header := textproto.MIMEHeader{}
		header.Set("Content-Disposition", `form-data; name="file"; filename="app1.json"`)		// �Զ����ֶ����ƣ��ļ����ƣ����Ǳ����
		header.Set("Content-Type", `application/octet-stream`)									// ָ��ContentType�����Ǳ����
		writer, _ = formWriter.CreatePart(header)
		writer.Write([]byte("foo"))

		// ���д�룬��Ҫ����close����
		formWriter.Close()
	}()

	// ����http�ͻ���
	client := http.Client{}
	// ����request����ָ��body reader
	req, _ := http.NewRequest(http.MethodPost, "http://127.0.0.1/upload", r)
	req.Header.Set("Content-Type", formWriter.FormDataContentType()) // ��Ҫ��ȷ������ContentType

	// ִ�������ȡ��Ӧ
	resp, _ := client.Do(req)
	defer resp.Body.Close()

	// ��ȡ��Ӧ
	data, _ := ioutil.ReadAll(resp.Body)
	fmt.Println(string(data))

	* ��������岻�Ǻܴ�Ļ������Կ���ʹ�� bytes.Buffer ��Ϊ�ײ��reader/writer


---------------------------
http �Լ����� Multipart 
---------------------------
	reader := multipart.NewReader(in, "bu")	// in �����������壬bu ���Ƿָ�����
	for part, err := reader.NextPart(); err != io.EOF {
		headers := part.Header		// ����ͷ
		count, err := part.Read(make([]byte, 1024)) 	// ��ȡ����
		fileName := part.FileName()	// �ļ�����
		formName := part.FormName()	// ������
		err := part.Close()		// �ر���Դ
	}