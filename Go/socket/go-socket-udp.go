---------------------
udp
---------------------
	# �ο�ѧϰ
		https://colobu.com/2016/10/19/Go-UDP-Programming/
		https://colobu.com/2014/12/02/go-socket-programming-UDP/
	

	# UDP��ַ����
		type UDPAddr struct {
			IP   IP
			Port int
			Zone string // IPv6 scoped addressing zone
		}
		func ResolveUDPAddr(network, address string) (*UDPAddr, error)
			* ����UDP��ַ
				net.ResolveUDPAddr("udp4", "127.0.0.1:1024")
			
		func (a *UDPAddr) Network() string
		func (a *UDPAddr) String() string
	
	# net.UDPConn���ӷ���
		type *UDPConn struct {
		}
		func (c *UDPConn) Close() error
		func (c *UDPConn) File() (f *os.File, err error)
		func (c *UDPConn) LocalAddr() Addr
		func (c *UDPConn) Read(b []byte) (int, error)
		func (c *UDPConn) ReadFrom(b []byte) (int, Addr, error)
		func (c *UDPConn) ReadFromUDP(b []byte) (int, *UDPAddr, error)
			* �ӿͻ��˶�ȡһ��UDP��Ϣ

		func (c *UDPConn) ReadMsgUDP(b, oob []byte) (n, oobn, flags int, addr *UDPAddr, err error)
		func (c *UDPConn) RemoteAddr() Addr
		func (c *UDPConn) SetDeadline(t time.Time) error
		func (c *UDPConn) SetReadBuffer(bytes int) error
		func (c *UDPConn) SetReadDeadline(t time.Time) error
		func (c *UDPConn) SetWriteBuffer(bytes int) error
		func (c *UDPConn) SetWriteDeadline(t time.Time) error
		func (c *UDPConn) SyscallConn() (syscall.RawConn, error)
		func (c *UDPConn) Write(b []byte) (int, error)
			* ������������һ��UDP��Ϣ

		func (c *UDPConn) WriteMsgUDP(b, oob []byte, addr *UDPAddr) (n, oobn int, err error)
		func (c *UDPConn) WriteTo(b []byte, addr Addr) (int, error)
		func (c *UDPConn) WriteToUDP(b []byte, addr *UDPAddr) (int, error)
			* ���ͻ�����ӦUDP��Ϣ
	

---------------------
udp - �ͻ���
---------------------
	# ����
		func DialUDP(net string, laddr, raddr *UDPAddr) (c *UDPConn, err error)
			* net ָ������: udp4
	
	# Demo
		// ��ַ
		addr, _ := net.ResolveUDPAddr("udp", "127.0.0.1:1024")
		// �����ͻ���
		conn, _ := net.DialUDP("udp", nil, addr)
		// ������Ϣ
		size, _ := conn.Write([]byte("Hello Udp"))
		fmt.Printf("������������%d�ֽ���Ϣ ", size)

		// ��ȡ��Ӧ
		buffer := make([]byte, 65507, 65507)
		size, _ = conn.Read(buffer)
		fmt.Printf("�յ��������Ļظ�:%s\n", string(buffer[:size]))
---------------------
udp - �����
---------------------
	# ����
		func ListenUDP(network string, laddr *UDPAddr) (*UDPConn, error)
	


	# Demo
		// ������������ַ
		addr, _ := net.ResolveUDPAddr("udp", "127.0.0.1:1024")
		// �����
		server, _ := net.ListenUDP("udp", addr)

		buffer := make([]byte, 65507, 65507) // udp��Ϣ����ֽ���
		for {
			size, addr, _  := server.ReadFromUDP(buffer)
			
			// ��ȡ����
			data := buffer[:size]
			fmt.Printf("�յ��ͻ���[%s]��Ϣ:%s", addr.String(), string(data))

			// ���ͻ��˻�д����
			size, _ = server.WriteToUDP([]byte("���յ���"), addr)
			fmt.Printf("��д��%d�ֽ�����\n", size)
		}
	
	# ��ָ���˿ڷ���UDP���ݰ�
		TODO

---------------------
udp - udp�ಥ/�鲥
---------------------
	TODO

---------------------
udp - udp�β�
---------------------
	TODO

---------------------
udp - udp�㲥
---------------------
	TODO