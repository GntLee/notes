-------------------------
socket
-------------------------
	# Go���Ա�׼���socket�����Ĺ��̽����˳���ͷ�װ

	# �ͻ���
		* ��������ʹ��ʲôЭ�齨��ʲô��ʽ�����ӣ���ֻ��Ҫ����net.Dial()����

			func Dial(network, address string) (Conn, error)
			func DialTimeout(network, address string, timeout time.Duration) (Conn, error)
			func FileConn(f *os.File) (c Conn, err error)
			
			network
				* ����Э������
			address
				* Ŀ���ַ
			timeout
				* ��ʱʱ��
			f
				* �����ļ�Unixͨ��
			Conn
				* �ɹ�������
			error
				* �쳣��Ϣ
		
		* Conn �ӿ�
			type Conn interface {
				Read(b []byte) (n int, err error)
				Write(b []byte) (n int, err error)
				Close() error
				LocalAddr() Addr
				RemoteAddr() Addr
				SetDeadline(t time.Time) error
				SetReadDeadline(t time.Time) error
				SetWriteDeadline(t time.Time) error
			}

		
		* һЩ������Э��
			conn, err := net.Dial("tcp", "192.168.0.10:2100")	//tcp
			conn, err := net.Dial("udp", "192.168.0.12:975")	//udp
			conn, err := net.Dial("ip4:icmp", "www.baidu.com")	// ICMP���ӣ�ʹ��Э�����ƣ�
			conn, err := net.Dial("ip4:1", "10.0.0.3")			// ICMP���ӣ�ʹ��Э���ţ�
		
		* Э���ſ������߲鿴
			https://www.iana.org/assignments/address-family-numbers/address-family-numbers.xhtml
		
		* �߼���Э���װ����
			func DialTCP(net string, laddr, raddr *TCPAddr) (c *TCPConn, err error)
			func DialUDP(net string, laddr, raddr *UDPAddr) (c *UDPConn, err error)
			func DialIP(netProto string, laddr, raddr *IPAddr) (*IPConn, error)
			func DialUnix(net string, laddr, raddr *UnixAddr) (c *UnixConn, err error)
	
	# �����
		func Listen(network, address string) (Listener, error)
			* network��ָ��Ҫ��������������: tcp/udp....
			* string ָ����ַ�����ֻ�ж˿ڵĻ��� �������������
			
		* Listener �ӿ�
			type Listener interface {
				Accept() (Conn, error)
				Close() error
				Addr() Addr
			}
			func FileListener(f *os.File) (ln Listener, err error)
			func Listen(network, address string) (Listener, error)
		
-------------------------
ͨ�õ�һЩ����/�ӿ�
-------------------------
	# �����ַ
		type Addr interface {
			Network() string // name of the network (for example, "tcp", "udp")
			String() string  // string form of address (for example, "192.0.2.1:25", "[2001:db8::1]:80")
		}
		func InterfaceAddrs() ([]Addr, error)


	# IP
		type IP []byte

		func IPv4(a, b, c, d byte) IP
		func LookupIP(host string) ([]IP, error)
		func ParseIP(s string) IP

		func (ip IP) DefaultMask() IPMask
		func (ip IP) Equal(x IP) bool
		func (ip IP) IsGlobalUnicast() bool
		func (ip IP) IsInterfaceLocalMulticast() bool
		func (ip IP) IsLinkLocalMulticast() bool
		func (ip IP) IsLinkLocalUnicast() bool
		func (ip IP) IsLoopback() bool
		func (ip IP) IsMulticast() bool
		func (ip IP) IsUnspecified() bool
		func (ip IP) MarshalText() ([]byte, error)
		func (ip IP) Mask(mask IPMask) IP
		func (ip IP) String() string
		func (ip IP) To16() IP
		func (ip IP) To4() IP
		func (ip *IP) UnmarshalText(text []byte) error
	
