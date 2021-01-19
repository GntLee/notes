
--------------------------------
multipart
--------------------------------
--------------------------------
����
--------------------------------
	# ����������쳣
		var ErrMessageTooLarge = errors.New("multipart: message too large")


--------------------------------
type
--------------------------------
	# type FileHeader struct {
			Filename string
			Header   textproto.MIMEHeader
			Size     int64
		}
		func (fh *FileHeader) Open() (File, error)
			* ����File��������ȡ����
	
	# type Form struct {
			Value map[string][]string
			File  map[string][]*FileHeader
		}
		func (f *Form) RemoveAll() error
			* ɾ�����й�������ʱ�ļ�
	
	# type Part struct {
			Header textproto.MIMEHeader
		}
		func (p *Part) Close() error
		func (p *Part) FileName() string
		func (p *Part) FormName() string
		func (p *Part) Read(d []byte) (n int, err error)
	
	# type Reader struct {
		}
		func NewReader(r io.Reader, boundary string) *Reader
		func (r *Reader) NextPart() (*Part, error)
			* �����������β������ io.EOF �쳣

		func (r *Reader) NextRawPart() (*Part, error)
		func (r *Reader) ReadForm(maxMemory int64) (*Form, error)
	
	# type Writer struct {
		}
		func NewWriter(w io.Writer) *Writer
		func (w *Writer) Boundary() string

		func (w *Writer) Close() error
			* �����Ҫ����close������������ķֽ���

		func (w *Writer) CreateFormField(fieldname string) (io.Writer, error)
			* д����ͨ����
		func (w *Writer) CreateFormFile(fieldname, filename string) (io.Writer, error)
			* д���ļ�����
		func (w *Writer) CreatePart(header textproto.MIMEHeader) (io.Writer, error)
			* д���Զ���part�������ͨ��part�Զ���header
		func (w *Writer) WriteField(fieldname, value string) error
			* ���ٵ�д����ͨ����
				
		func (w *Writer) FormDataContentType() string
			* ����contentType 
				multipart/form-data; boundary=b647104eac41c51a2701657dee93231333b7f5ad44bf521e43880773a6ee
		func (w *Writer) SetBoundary(boundary string) error
			* ���÷ֽ磬�����Ҫ���ã�ֻ������д��֮ǰ�ͱ��뱻����
			* Ĭ�ϲ���Ҫ���ã���Ĭ�ϵ�
		
		
--------------------------------
����
--------------------------------

