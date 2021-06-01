-----------------------------
redigo
-----------------------------
	# Doc
		https://pkg.go.dev/github.com/gomodule/redigo/redis
		https://github.com/gomodule/redigo

	# package
		 github.com/gomodule/redigo/redis

-----------------------------
var
-----------------------------
	var ErrNil = errors.New("redigo: nil returned")
		* ��redis��������ȡ���������ǿ�
		* ���ԶԷ��صĿ����ݣ����з�װ���᷵������쳣

	var ErrPoolExhausted = errors.New("redigo: connection pool exhausted")
		* ���ӳغľ��������ӳ����޷���ȡ���ӵ�ʱ��ط��ظ��쳣

-----------------------------
type
-----------------------------
	# type Args []interface{}
		func (args Args) Add(value ...interface{}) Args
			* ֱ�ӽ�ֵ׷�ӵ�args����Ľ�����أ������µģ������޸�args
			* Դ��
				return append(args, value...)

		func (args Args) AddFlat(v interface{}) Args
			* ��ֵ�����ʹ洢����Ϊ�������������Struct Slice Map Ptr ��������
			* ������ӳ��
				Struct
					* ���ݷ����ȡ���е����ֶΣ��Ȱ��ֶ��������ַ�����ӵ��������ٰ�����ֶε�ֵ��ӵ�����
				Slice
					* ��ÿ��Ԫ�ض���ӵ�����
				Slice
					* ����Mapÿһ��Ȱ�KEY��ӵ��������ٰ�VALU��ӵ�����
				Ptr
					* ָ�룬����ָ�룬����ȥ��ȡָ���ֵ
				��������
					* ֱ����ӵ���������
			* ���������struct��֧�ֵ�ע�⣬��Ҫ��ӳ����Redis��Hash��
				var p1, p2 struct {
					Title  string `redis:"title"`
					Author string `redis:"author"`
					Body   string `redis:"body"`
				}
			
			
	
	# type Argument interface {
			RedisArg() interface{}
		}
	
	# type Conn interface {
			Close() error
				* �ر�����
			
			Err() error
				* ��ȡ�쳣��Ϣ

			Do(commandName string, args ...interface{}) (reply interface{}, err error)
				* ִ��������ؽ��

			Send(commandName string, args ...interface{}) error
				* ����һ�����߶������
			
			Flush() error
				* ��Send���͵�������͵�������ִ��

			Receive() (reply interface{}, err error)
				* ��ȡFlush�󣬷��صĽ��
		}
		func Dial(network, address string, options ...DialOption) (Conn, error)
		func DialContext(ctx context.Context, network, address string, options ...DialOption) (Conn, error)
			* �������ӣ�ͨ��options����N������
				c, err := redis.Dial("tcp", ":6379")

		func DialTimeout(network, address string, ...) (Conn, error)
		func DialURL(rawurl string, options ...DialOption) (Conn, error)
			* ����Redis��URL��������
		func NewConn(netConn net.Conn, readTimeout, writeTimeout time.Duration) Conn
		func NewLoggingConn(conn Conn, logger *log.Logger, prefix string) Conn
		func NewLoggingConnFilter(conn Conn, logger *log.Logger, prefix string, skip func(cmdName string) bool) Conn
	
	# type ConnWithTimeout interface {
			Conn
			DoWithTimeout(timeout time.Duration, commandName string, args ...interface{}) (reply interface{}, err error)
			ReceiveWithTimeout(timeout time.Duration) (reply interface{}, err error)
		}
	
	# type DialOption struct {
		}

		* Redis���������Ӳ���

		func DialClientName(name string) DialOption
		func DialConnectTimeout(d time.Duration) DialOption
			* �������ӳ�ʱʱ��
		func DialContextFunc(f func(ctx context.Context, network, addr string) (net.Conn, error)) DialOption
		func DialDatabase(db int) DialOption
			* �����������ݿ�
		func DialKeepAlive(d time.Duration) DialOption
		func DialNetDial(dial func(network, addr string) (net.Conn, error)) DialOption
		func DialPassword(password string) DialOption
			* ��������redis����
		func DialReadTimeout(d time.Duration) DialOption	
			* ���ö���ʱʱ��
		func DialTLSConfig(c *tls.Config) DialOption
			* ����TLS������Ϣ
		func DialTLSHandshakeTimeout(d time.Duration) DialOption
		func DialTLSSkipVerify(skip bool) DialOption
			* �Ƿ�����TLS��֤
		func DialUseTLS(useTLS bool) DialOption
			* �Ƿ�ʹ��TLS
		func DialUsername(username string) DialOption
			* �˻�����
		func DialWriteTimeout(d time.Duration) DialOption
			* ���ö���ʱʱ��
	
	# type Error string
		func (err Error) Error() string
	
	# type Message struct {
			Channel string	 // Ƶ������
			Pattern string	//  Ƶ��ģʽ����ʽ��
			Data []byte		//  ����
		}
	# type Pong struct {
			Data string
		}
	
	# type Pool struct {
			Dial func() (Conn, error)
				* �������ӵķ���
			
			DialContext func(ctx context.Context) (Conn, error)
				* �������ӵķ�������������context

			TestOnBorrow func(c Conn, t time.Time) error
				* ������ʹ��֮ǰ�����״̬��t �����ӷ��ص�ʱ��
				* ����������ش��������ӽ���ɾ��

			MaxIdle int
			MaxActive int
			IdleTimeout time.Duration
			Wait bool
				* �Ƿ�����Get������ֱ������ȡ�����µ�����

			MaxConnLifetime time.Duration
		}

		* ���ӳ�

		func NewPool(newFn func() (Conn, error), maxIdle int) *Pool
			* �����������Լ����� Pool ʵ��
		func (p *Pool) ActiveCount() int
			* ��ȡ�������
		func (p *Pool) Close() error
			* �ر�
		func (p *Pool) Get() Conn
		func (p *Pool) GetContext(ctx context.Context) (Conn, error)
			* ��ȡһ������
		func (p *Pool) IdleCount() int
			* ��ȡ��������
		func (p *Pool) Stats() PoolStats
			*  ��ȡ�ص�״̬


	# type PoolStats struct {
			ActiveCount int		// ���������
			IdleCount int		// ������������
			WaitCount int64		// �ȴ�����
			WaitDuration time.Duration // �ȴ���ʱʱ��
		}

		* ���ӳ�״̬
	
	# type PubSubConn struct {
			Conn Conn
		}
	
		* ����ͨ��

		func (c PubSubConn) Close() error
			* �رն���
		func (c PubSubConn) PSubscribe(channel ...interface{}) error
			* ����һ���������ϸ���ģʽ��Ƶ��
		func (c PubSubConn) PUnsubscribe(channel ...interface{}) error
			* �˶����и���ģʽ��Ƶ��
		func (c PubSubConn) Ping(data string) error
			* ���Կͻ����Ƿ��ܹ�������ͨ
		func (c PubSubConn) Receive() interface{}
			* ��ȡ��Ϣ�����ܻ᷵���쳣��Ϣ���������Ϣ�����ǣ�Message
		func (c PubSubConn) ReceiveWithTimeout(timeout time.Duration) interface{}
			* ��ȡ��Ϣ��ָ����ʱʱ��
		func (c PubSubConn) Subscribe(channel ...interface{}) error
			* ���ĸ�����һ������Ƶ������Ϣ
		func (c PubSubConn) Unsubscribe(channel ...interface{}) error
			* ָ�˶�������Ƶ��

	# type Scanner interface {
			RedisScan(src interface{}) error
		}
	
	# type Script struct {
		}
		
		* �ű�

		func NewScript(keyCount int, src string) *Script
		func (s *Script) Do(c Conn, keysAndArgs ...interface{}) (interface{}, error)
		func (s *Script) Hash() string
		func (s *Script) Load(c Conn) error
		func (s *Script) Send(c Conn, keysAndArgs ...interface{}) error
		func (s *Script) SendHash(c Conn, keysAndArgs ...interface{}) error
	
	# type SlowLog struct {
			ID int64
			Time time.Time
			ExecutionTime time.Duration
			Args []string
			ClientAddr string
			ClientName string
		}
		func SlowLogs(result interface{}, err error) ([]SlowLog, error)
	
	# type Subscription struct {
			Kind string
			Channel string
			Count int
		}

		* ��������

		

-----------------------------
func
-----------------------------
	func Bool(reply interface{}, err error) (bool, error)
		* �ѽ��ת��Ϊboolֵ
		* ���err������nil������false����֮������������
			integer         value != 0, nil
			bulk string     strconv.ParseBool(reply)
			nil             false, ErrNil
			other           false, error

	func ByteSlices(reply interface{}, err error) ([][]byte, error)
	func Bytes(reply interface{}, err error) ([]byte, error)
	func DoWithTimeout(c Conn, timeout time.Duration, cmd string, args ...interface{}) (interface{}, error)
		* ִ��һ��Redis���������ó�ʱʱ��

	func Float64(reply interface{}, err error) (float64, error)
	func Float64s(reply interface{}, err error) ([]float64, error)
	func Int(reply interface{}, err error) (int, error)
	func Int64(reply interface{}, err error) (int64, error)
		* ���԰ѽ��ת��Ϊ��ֵ����

	func Int64Map(result interface{}, err error) (map[string]int64, error)
	func Int64s(reply interface{}, err error) ([]int64, error)
	func IntMap(result interface{}, err error) (map[string]int, error)
	func Ints(reply interface{}, err error) ([]int, error)
	func MultiBulk(reply interface{}, err error) ([]interface{}, error)
	func Positions(result interface{}, err error) ([]*[2]float64, error)
	func ReceiveWithTimeout(c Conn, timeout time.Duration) (interface{}, error)

	func Scan(src []interface{}, dest ...interface{}) ([]interface{}, error)
		* �ѷ������ݣ���װ��dest

	func ScanSlice(src []interface{}, dest interface{}, fieldNames ...string) error
		* �ѷ������ݷ�װ����Ƭ
		* ����ͨ�� fieldNames ָ��Ҫ��װ���ֶΣ���Ƭ�����ǽṹ�壩
	
	func ScanStruct(src []interface{}, dest interface{}) error
		* �ѷ������ݷ�װ��Map

	func String(reply interface{}, err error) (string, error)
		* �ѽ��ת��Ϊ�ַ���

	func StringMap(result interface{}, err error) (map[string]string, error)
		* �ѽ��ת��ΪMap
	
	func Strings(reply interface{}, err error) ([]string, error)
		* �ѽ��ת��Ϊ�ַ�����Ƭ

	func Uint64(reply interface{}, err error) (uint64, error)
	func Uint64Map(result interface{}, err error) (map[string]uint64, error)
	func Uint64s(reply interface{}, err error) ([]uint64, error)

	func Values(reply interface{}, err error) ([]interface{}, error)
		* �ѷ���ֵ��value����װΪ��Ƭ
		* �ü�����װ�������βζ���Ҫ����Ƭ��ʽ�ķ���ֵ