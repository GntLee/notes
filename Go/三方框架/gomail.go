-------------
gomail
-------------
	# ��
		gopkg.in/gomail.v2

-------------
var
-------------

-------------
type
-------------
	# type Dialer struct {
			Host string
			Port int
			Username string
			Password string
			Auth smtp.Auth
			SSL bool
			TLSConfig *tls.Config
			LocalName string
		}
		func NewDialer(host string, port int, username, password string) *Dialer
		func NewPlainDialer(host string, port int, username, password string) *Dialer

		func (d *Dialer) Dial() (SendCloser, error)
		func (d *Dialer) DialAndSend(m ...*Message) error
	
	# type Encoding string
		const (
			QuotedPrintable Encoding = "quoted-printable"
			Base64 Encoding = "base64"
			Unencoded Encoding = "8bit"
		)
	
	# type FileSetting func(*file)
		func Rename(name string) FileSetting
		func SetCopyFunc(f func(io.Writer) error) FileSetting
		func SetHeader(h map[string][]string) FileSetting
	
	# type Message struct {
		}
		func NewMessage(settings ...MessageSetting) *Message
		func (m *Message) AddAlternative(contentType, body string, settings ...PartSetting)
		func (m *Message) AddAlternativeWriter(contentType string, f func(io.Writer) error, settings ...PartSetting)
		func (m *Message) Attach(filename string, settings ...FileSetting)
		func (m *Message) Embed(filename string, settings ...FileSetting)
		func (m *Message) FormatAddress(address, name string) string
		func (m *Message) FormatDate(date time.Time) string
		func (m *Message) GetHeader(field string) []string
		func (m *Message) Reset()
		func (m *Message) SetAddressHeader(field, address, name string)
		func (m *Message) SetBody(contentType, body string, settings ...PartSetting)
		func (m *Message) SetDateHeader(field string, date time.Time)
		func (m *Message) SetHeader(field string, value ...string)
		func (m *Message) SetHeaders(h map[string][]string)
		func (m *Message) WriteTo(w io.Writer) (int64, error)
	
	# type MessageSetting func(m *Message)
		func SetCharset(charset string) MessageSetting
		func SetEncoding(enc Encoding) MessageSetting
	
	# type PartSetting func(*part)
		func SetPartEncoding(e Encoding) PartSetting
	
	# type SendCloser interface {
			Sender
			Close() error
		}
	
	# type SendFunc func(from string, to []string, msg io.WriterTo) error
		func (f SendFunc) Send(from string, to []string, msg io.WriterTo) error
	
	# type Sender interface {
			Send(from string, to []string, msg io.WriterTo) error
		}
	

-------------
func
-------------
	func Send(s Sender, msg ...*Message) error



-------------
Demo
-------------
	# �����ʼ�
		// �����ʼ�����
		m := gomail.NewMessage()

		m.SetHeader("From", "alex@example.com", "alex<alex@example.com>")						// ������

		m.SetHeader("To", "bob@example.com", "cora@example.com")	// �ռ��ˣ������ж��
		m.SetAddressHeader("Cc", "dan@example.com", "Dan")			// �ռ���
		m.SetHeader("Subject", "Hello!")							// ����
		m.SetBody("text/html", "Hello <b>Bob</b> and <i>Cora</i>!")	// �ʼ�����
		m.Attach("/home/Alex/lolcat.jpg")							// ������Ϣ

		// ������������
		d := gomail.NewDialer("smtp.example.com", 587, "user", "123456")

		// �����ʼ�
		if err := d.DialAndSend(m); err != nil {
			panic(err)
		}

		* �����˿�����ӱ���
			m.SetHeader("From", "alex<alex@example.com>")	
	
	# ����֤��У��
		d := gomail.NewDialer("smtp.example.com", 587, "user", "123456")
		d.TLSConfig = &tls.Config{InsecureSkipVerify: true}