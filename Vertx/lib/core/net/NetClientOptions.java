-----------------------
NetClientOptions
-----------------------
	# NetClient �����ࣺ class NetClientOptions extends ClientOptionsBase


-----------------------
����
-----------------------
	public NetClientOptions()
	public NetClientOptions(NetClientOptions other)
	public NetClientOptions(JsonObject json)

-----------------------
ʵ��
-----------------------
	public NetClientOptions setSendBufferSize(int sendBufferSize)
	public NetClientOptions setReceiveBufferSize(int receiveBufferSize)
	public NetClientOptions setReuseAddress(boolean reuseAddress)
	public NetClientOptions setReusePort(boolean reusePort)
	public NetClientOptions setTrafficClass(int trafficClass)
	public NetClientOptions setTcpNoDelay(boolean tcpNoDelay)
	public NetClientOptions setTcpKeepAlive(boolean tcpKeepAlive)
	public NetClientOptions setSoLinger(int soLinger)
	public NetClientOptions setIdleTimeout(int idleTimeout)
	public NetClientOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit)
	public NetClientOptions setSsl(boolean ssl)
		* ����SSL

	public NetClientOptions setKeyCertOptions(KeyCertOptions options)
	public NetClientOptions setKeyStoreOptions(JksOptions options)
	public NetClientOptions setPfxKeyCertOptions(PfxOptions options)
	public NetClientOptions setPemKeyCertOptions(PemKeyCertOptions options)
		* ���ÿͻ��˵�����֤��

	public NetClientOptions setTrustOptions(TrustOptions options)

	public NetClientOptions setTrustStoreOptions(JksOptions options)
	public NetClientOptions setPemTrustOptions(PemTrustOptions options)
	public NetClientOptions setPfxTrustOptions(PfxOptions options)
		* ���ÿͻ��˵�����֤��

	public NetClientOptions addEnabledCipherSuite(String suite)
		* ���������׼�
		* Ĭ������£�TLS���ý�ʹ������Vert.x��JVM �����׼����������׼����� ����һ�����õ�����

	public NetClientOptions addEnabledSecureTransportProtocol(final String protocol)
	public NetClientOptions removeEnabledSecureTransportProtocol(String protocol)
	public NetClientOptions setUseAlpn(boolean useAlpn)
	public NetClientOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions)
	public NetClientOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions)
	public NetClientOptions setTcpFastOpen(boolean tcpFastOpen)
	public NetClientOptions setTcpCork(boolean tcpCork)
	public NetClientOptions setTcpQuickAck(boolean tcpQuickAck)
	public ClientOptionsBase setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions)

	public NetClientOptions addCrlPath(String crlPath) throws NullPointerException
	public NetClientOptions addCrlValue(Buffer crlValue) throws NullPointerException	
		* ����֤������б�CRL�����������ٱ����ε�֤�����

	public NetClientOptions setTrustAll(boolean trustAll)
		* �ͻ��˽��������з����֤��

	public NetClientOptions setConnectTimeout(int connectTimeout)
	public NetClientOptions setMetricsName(String metricsName)
	public NetClientOptions setReconnectAttempts(int attempts)
	public int getReconnectAttempts()
		* �����������Դ���

	public NetClientOptions setReconnectInterval(long interval)
		* �����������Լ��

	public String getHostnameVerificationAlgorithm()
	public NetClientOptions setHostnameVerificationAlgorithm(String hostnameVerificationAlgorithm)
		* Ĭ������£��ͻ��˽���������֤�� 
		* Ҫ����������֤����������ʹ�õ��㷨��Ŀǰ��֧��HTTPS��LDAPS��

	public List<String> getApplicationLayerProtocols()
	public NetClientOptions setApplicationLayerProtocols(List<String> protocols)
	public long getReconnectInterval()
	public NetClientOptions setLogActivity(boolean logEnabled)
		* ��¼������־

	public NetClientOptions setProxyOptions(ProxyOptions proxyOptions)
	public NetClientOptions setLocalAddress(String localAddress)
	public NetClientOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols)
	public NetClientOptions setSslHandshakeTimeout(long sslHandshakeTimeout)
	public NetClientOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit)


-----------------------
��̬
-----------------------
  public static final int DEFAULT_RECONNECT_ATTEMPTS = 0;
  public static final long DEFAULT_RECONNECT_INTERVAL = 1000;
  public static final String DEFAULT_HOSTNAME_VERIFICATION_ALGORITHM = "";