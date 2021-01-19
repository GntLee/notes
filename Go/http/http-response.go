------------
response
------------
	# ResponseWriter �ӿ�
		type ResponseWriter interface {
			Header() Header
				* header
			Write([]byte) (int, error)
				* ��Ӧbody
			WriteHeader(statusCode int)
				* ��Ӧhttp״̬��
		}

	# Response ��
		type Response struct {
			Status     string // e.g. "200 OK"
			StatusCode int    // e.g. 200
			Proto      string // e.g. "HTTP/1.0"
			ProtoMajor int    // e.g. 1
			ProtoMinor int    // e.g. 0
			Header Header
			Body io.ReadCloser
				* ��Ӧ������

			ContentLength int64
				* body����
			
			TransferEncoding []string
			Close bool
				* ��Ӧ�Ƿ��Ѿ��ر�
			Uncompressed bool
			Trailer Header
			Request *Request
			TLS *tls.ConnectionState
		}

		func Get(url string) (resp *Response, err error)
		func Head(url string) (resp *Response, err error)
		func Post(url, contentType string, body io.Reader) (resp *Response, err error)
		func PostForm(url string, data url.Values) (resp *Response, err error)
			* ����Http���󣬻�ȡ��Ӧ
			* Ĭ��ʹ�õ���Ĭ�ϵĿͻ���
				var DefaultClient = &Client{}

		func ReadResponse(r *bufio.Reader, req *Request) (*Response, error)

		func (r *Response) Cookies() []*Cookie
			* ����Cookie��Ϣ

		func (r *Response) Location() (*url.URL, error)
			* ����URI��Ϣ
		func (r *Response) ProtoAtLeast(major, minor int) bool
		func (r *Response) Write(w io.Writer) error
			* �����ָ����Writer