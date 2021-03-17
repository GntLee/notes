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
	
		* �ʼ�������������

		func NewDialer(host string, port int, username, password string) *Dialer
		func NewPlainDialer(host string, port int, username, password string) *Dialer

		func (d *Dialer) Dial() (SendCloser, error)
			* �������ӣ�����Sender

		func (d *Dialer) DialAndSend(m ...*Message) error
			* ���ӣ����ҷ���n���ʼ�
	
	# type Encoding string

		* ������

		const (
			QuotedPrintable Encoding = "quoted-printable"
			Base64 Encoding = "base64"
			Unencoded Encoding = "8bit"
		)

	
	# type FileSetting func(*file)
		
		* �ļ�����
		
		func Rename(name string) FileSetting
		func SetCopyFunc(f func(io.Writer) error) FileSetting
		func SetHeader(h map[string][]string) FileSetting
	
	# type Message struct {
		}
		func NewMessage(settings ...MessageSetting) *Message
			* �����µ���Ϣ��ͨ���������������

		func (m *Message) AddAlternative(contentType, body string, settings ...PartSetting)
		func (m *Message) AddAlternativeWriter(contentType string, f func(io.Writer) error, settings ...PartSetting)
		func (m *Message) Attach(filename string, settings ...FileSetting)
			* ��Ӹ�����ָ���ļ����ƣ��Լ��ļ���ͨ��FileSetting��ȡ��

		func (m *Message) Embed(filename string, settings ...FileSetting)
		func (m *Message) FormatAddress(address, name string) string
			* ����address�����������ط����˵�ַ��Ϣ
				 FormatAddress("no-reply@springboot.io", "SpringBoot������")	 
				 // =?UTF-8?q?SpringBoot=E4=B8=AD=E7=A4=BE=E5=8C=BA?= <no-reply@springboot.io>

		func (m *Message) FormatDate(date time.Time) string
		func (m *Message) GetHeader(field string) []string
		func (m *Message) Reset()
		func (m *Message) SetAddressHeader(field, address, name string)
			* ���õ�ַHeader���������ñ���
			* ���������ǵ��� FormatAddress ���б���
				m.header[field] = []string{m.FormatAddress(address, name)}
				
		func (m *Message) SetBody(contentType, body string, settings ...PartSetting)
			* �����ʼ����ݣ�ָ��ContentType

		func (m *Message) SetDateHeader(field string, date time.Time)
		func (m *Message) SetHeader(field string, value ...string)
			* ����Header
				From ������
				To �ռ���
				Subject ����

		func (m *Message) SetHeaders(h map[string][]string)
		func (m *Message) WriteTo(w io.Writer) (int64, error)
	
	# type MessageSetting func(m *Message)
		
		* ��Ϣ������
		
		func SetCharset(charset string) MessageSetting
			* ���ñ���
		func SetEncoding(enc Encoding) MessageSetting
			* ���ñ�����
	
	# type PartSetting func(*part)
			
		* Part������

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
		import (
			"gopkg.in/gomail.v2"
		)

		var authCode = "123456"

		func main(){

			// �����ʼ���Ϣ�����ñ���
			message := gomail.NewMessage(gomail.SetCharset("utf-8"))

			message.SetAddressHeader("From", "10086@qq.com", "�ɴ���")	// �������Լ�����������
			message.SetHeader("To", "10010@qq.com")					// �ռ��ˣ������ж��
			message.SetHeader("Subject", "Hello!")							// ����
			message.SetBody("text/html", "Hello World!")					// �ʼ�����
			message.Attach("D:\\ruby.db")										// ����

			// ��������������Ϣ��ָ�������˿ڣ��˻���������
			conn := gomail.NewDialer("smtp.163.com", 465, "10086@qq.com", authCode)

			// �������ӣ����ҷ����ʼ�
			if err := conn.DialAndSend(message); err != nil {
				panic(err)
			}
		}
	
	# ����֤��У��
		d := gomail.NewDialer("smtp.example.com", 587, "user", "123456")
		d.TLSConfig = &tls.Config{InsecureSkipVerify: true}