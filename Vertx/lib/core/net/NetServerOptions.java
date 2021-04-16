------------------------
NetServerOptions
------------------------
	# NetServer ����������ࣺ class NetServerOptions extends TCPSSLOptions

------------------------
����
------------------------
	public NetServerOptions()
	public NetServerOptions(NetServerOptions other)
	public NetServerOptions(JsonObject json)

------------------------
ʵ��
------------------------
	public JsonObject toJson()
	public NetServerOptions setSendBufferSize(int sendBufferSize)
	public NetServerOptions setReceiveBufferSize(int receiveBufferSize)
	public NetServerOptions setReuseAddress(boolean reuseAddress)
	public NetServerOptions setReusePort(boolean reusePort)
	public NetServerOptions setTrafficClass(int trafficClass)
	public NetServerOptions setTcpNoDelay(boolean tcpNoDelay)
	public NetServerOptions setTcpKeepAlive(boolean tcpKeepAlive)
	public NetServerOptions setSoLinger(int soLinger)
	public NetServerOptions setIdleTimeout(int idleTimeout)
	public NetServerOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit)
	public NetServerOptions setSsl(boolean ssl)
		* ����SSL�����ú���Ҫ����֤����Ϣ

	public NetServerOptions setUseAlpn(boolean useAlpn)

	public NetServerOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions)
	public NetServerOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions)
	public NetServerOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions)
		* ����ssl����

	public NetServerOptions setKeyCertOptions(KeyCertOptions options)
		* �������������͵�֤����Ϣ

	public NetServerOptions setKeyStoreOptions(JksOptions options)
		* ����JKS֤����Ϣ��Javaר����ʽ

	public NetServerOptions setPfxKeyCertOptions(PfxOptions options)
		* ����P12֤����Ϣ��ͨ�ø�ʽ

	public NetServerOptions setPemKeyCertOptions(PemKeyCertOptions options)
		* ����pemKey֤����Ϣ��Nginx�õ�֤��

	public NetServerOptions setTrustOptions(TrustOptions options)

	public NetServerOptions setTrustStoreOptions(JksOptions options)
	public NetServerOptions setPfxTrustOptions(PfxOptions options)
	public NetServerOptions setPemTrustOptions(PemTrustOptions options)
		* ������֤�ͻ��˵�֤����Ϣ��SSL˫����֤
		
	public NetServerOptions addEnabledCipherSuite(String suite)
		* ���������׼�

	public NetServerOptions addEnabledSecureTransportProtocol(final String protocol)
	public NetServerOptions removeEnabledSecureTransportProtocol(String protocol)
		* ���/ɾ��TLSЭ��汾

	public NetServerOptions setTcpFastOpen(boolean tcpFastOpen)
	public NetServerOptions setTcpCork(boolean tcpCork)
	public NetServerOptions setTcpQuickAck(boolean tcpQuickAck)

	public NetServerOptions addCrlPath(String crlPath) throws NullPointerException
	public NetServerOptions addCrlValue(Buffer crlValue) throws NullPointerException
		* ����֤������б�CRL�����������ٱ����ε�֤�����

	public NetServerOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols)
	public NetServerOptions setSslHandshakeTimeout(long sslHandshakeTimeout)
	public NetServerOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit)
	public int getAcceptBacklog()
	public NetServerOptions setAcceptBacklog(int acceptBacklog)
	public int getPort()
	public NetServerOptions setPort(int port)
	public String getHost()
	public NetServerOptions setHost(String host)
	public ClientAuth getClientAuth()
	public NetServerOptions setClientAuth(ClientAuth clientAuth)
		* ���ÿͻ�����֤ģʽ��clientAuth �Ǹ�ö��
			NONE		����֤
			REQUEST		��Ҫ֤�飬����֤
			REQUIRED	������֤

	public NetServerOptions setLogActivity(boolean logEnabled)
	public boolean isSni()
	public NetServerOptions setSni(boolean sni)
	public boolean isUseProxyProtocol()
	public NetServerOptions setUseProxyProtocol(boolean useProxyProtocol)
	public long getProxyProtocolTimeout()
	public NetServerOptions setProxyProtocolTimeout(long proxyProtocolTimeout)
	public NetServerOptions setProxyProtocolTimeoutUnit(TimeUnit proxyProtocolTimeoutUnit)
	public TimeUnit getProxyProtocolTimeoutUnit()



------------------------
��̬
------------------------
  public static final int DEFAULT_PORT = 0;
  public static final String DEFAULT_HOST = "0.0.0.0";
  public static final int DEFAULT_ACCEPT_BACKLOG = -1;
  public static final ClientAuth DEFAULT_CLIENT_AUTH = ClientAuth.NONE;
  public static final boolean DEFAULT_SNI = false;
  public static final boolean DEFAULT_USE_PROXY_PROTOCOL = false;
  public static final long DEFAULT_PROXY_PROTOCOL_TIMEOUT = 10L;
  public static final TimeUnit DEFAULT_PROXY_PROTOCOL_TIMEOUT_TIME_UNIT = TimeUnit.SECONDS;