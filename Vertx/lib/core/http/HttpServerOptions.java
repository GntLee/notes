-----------------------------
HttpServerOptions
-----------------------------
	# Http�����������ࣺ class HttpServerOptions extends NetServerOptions


-----------------------------
���캯��
-----------------------------
	public HttpServerOptions()
	public HttpServerOptions(HttpServerOptions other)
	public HttpServerOptions(JsonObject json)

-----------------------------
this
-----------------------------
	public JsonObject toJson()
	public HttpServerOptions setSendBufferSize(int sendBufferSize) 
	public HttpServerOptions setReceiveBufferSize(int receiveBufferSize)
	public HttpServerOptions setReuseAddress(boolean reuseAddress)
	public HttpServerOptions setReusePort(boolean reusePort)
	public HttpServerOptions setTrafficClass(int trafficClass) 
	public HttpServerOptions setTcpNoDelay(boolean tcpNoDelay)
	public HttpServerOptions setTcpKeepAlive(boolean tcpKeepAlive)
	public HttpServerOptions setSoLinger(int soLinger)
		* TCP��ص�����

	public HttpServerOptions setIdleTimeout(int idleTimeout)
	public HttpServerOptions setIdleTimeoutUnit(TimeUnit idleTimeoutUnit)
		* vert.x �����Զ��ر� keep-alive ������
		* ������Ҫ��һ�ο���ʱ��֮���� Vert.x �Զ��ر� keep-alive �����ӣ���ʹ����������������á�

	public HttpServerOptions setSsl(boolean ssl)
		* ����SSL
	public HttpServerOptions setUseAlpn(boolean useAlpn)
		* ����HTT2
		* ʹ��TLSӦ�ò�Э��Э�� (ALPN)Э����Э�̵� HTTP/2 Э��

	public HttpServerOptions setKeyCertOptions(KeyCertOptions options) 
	public HttpServerOptions setKeyStoreOptions(JksOptions options)
	public HttpServerOptions setPfxKeyCertOptions(PfxOptions options)
	public HttpServerOptions setPemKeyCertOptions(PemKeyCertOptions options) 
		* ���÷�������֤����Ϣ

	public HttpServerOptions setTrustOptions(TrustOptions options)
	public HttpServerOptions setTrustStoreOptions(JksOptions options)
	public HttpServerOptions setPemTrustOptions(PemTrustOptions options)
	public HttpServerOptions setPfxTrustOptions(PfxOptions options) 
		* �ͻ�������֤���������Ϣ

	public HttpServerOptions addEnabledCipherSuite(String suite)
	public HttpServerOptions addEnabledSecureTransportProtocol(final String protocol)
	public HttpServerOptions removeEnabledSecureTransportProtocol(String protocol) 
	public HttpServerOptions setTcpFastOpen(boolean tcpFastOpen)
	public HttpServerOptions setTcpCork(boolean tcpCork)
	public HttpServerOptions setTcpQuickAck(boolean tcpQuickAck)

	public HttpServerOptions addCrlPath(String crlPath) throws NullPointerException
	public HttpServerOptions addCrlValue(Buffer crlValue) throws NullPointerException 
		* ���õ���֤���·��

	public HttpServerOptions setAcceptBacklog(int acceptBacklog)

	public HttpServerOptions setPort(int port)
	public HttpServerOptions setHost(String host)
		* �����Ķ˿ں�IP

	public HttpServerOptions setClientAuth(ClientAuth clientAuth)
		* ���ÿͻ��ĵ�У��ģʽ

	public HttpServerOptions setSslEngineOptions(SSLEngineOptions sslEngineOptions)
	public HttpServerOptions setJdkSslEngineOptions(JdkSSLEngineOptions sslEngineOptions)
	public HttpServerOptions setOpenSslEngineOptions(OpenSSLEngineOptions sslEngineOptions)
		* ����SSL����

	public HttpServerOptions setEnabledSecureTransportProtocols(Set<String> enabledSecureTransportProtocols)
	public HttpServerOptions setSslHandshakeTimeout(long sslHandshakeTimeout)
	public HttpServerOptions setSslHandshakeTimeoutUnit(TimeUnit sslHandshakeTimeoutUnit)
		* ����ssl���ֳ�ʱʱ��

	public boolean isCompressionSupported()
	public HttpServerOptions setCompressionSupported(boolean compressionSupported)
		* �Ƿ���ѹ��

	public int getCompressionLevel()
	public HttpServerOptions setCompressionLevel(int compressionLevel)
		* ѹ������

	public boolean isAcceptUnmaskedFrames()
	public HttpServerOptions setAcceptUnmaskedFrames(boolean acceptUnmaskedFrames)

	public int getMaxWebSocketFrameSize()
	public HttpServerOptions setMaxWebSocketFrameSize(int maxWebSocketFrameSize)
		* ws�����Ϣ֡���

	public int getMaxWebSocketMessageSize()
	public HttpServerOptions setMaxWebSocketMessageSize(int maxWebSocketMessageSize)
		* ws�����Ϣ���

	public HttpServerOptions addWebSocketSubProtocol(String subProtocol)
	public HttpServerOptions setWebSocketSubProtocols(List<String> subProtocols)
	public List<String> getWebSocketSubProtocols()
		* ws��Э��

	public boolean isHandle100ContinueAutomatically()
	public HttpServerOptions setHandle100ContinueAutomatically(boolean handle100ContinueAutomatically)
		* 101Э���Զ�����

	public HttpServerOptions setMaxChunkSize(int maxChunkSize)
	public int getMaxChunkSize()
		

	public int getMaxInitialLineLength()
	public HttpServerOptions setMaxInitialLineLength(int maxInitialLineLength)

	public int getMaxHeaderSize()
	public HttpServerOptions setMaxHeaderSize(int maxHeaderSize)
		* ���Header���

	public int getMaxFormAttributeSize()
	public HttpServerOptions setMaxFormAttributeSize(int maxSize)

	public Http2Settings getInitialSettings()
	public HttpServerOptions setInitialSettings(Http2Settings settings)
		* ������������ HTTP/2 ����ʱ��������ͻ��˷��������ʼ����

	public List<HttpVersion> getAlpnVersions()
	public HttpServerOptions setAlpnVersions(List<HttpVersion> alpnVersions)
	public int getHttp2ConnectionWindowSize()
	public HttpServerOptions setHttp2ConnectionWindowSize(int http2ConnectionWindowSize)

	public HttpServerOptions setLogActivity(boolean logEnabled)
		* �Ƿ��¼���־

	public HttpServerOptions setSni(boolean sni)
	public HttpServerOptions setUseProxyProtocol(boolean useProxyProtocol)
	public HttpServerOptions setProxyProtocolTimeout(long proxyProtocolTimeout)
	public HttpServerOptions setProxyProtocolTimeoutUnit(TimeUnit proxyProtocolTimeoutUnit)

	public boolean isDecompressionSupported()
	public HttpServerOptions setDecompressionSupported(boolean decompressionSupported)
		* �Ƿ��Զ���ѹ���ͻ��˵�ѹ�������壬Ĭ���� false

	public int getDecoderInitialBufferSize()
	public HttpServerOptions setDecoderInitialBufferSize(int decoderInitialBufferSize)

	public HttpServerOptions setPerFrameWebSocketCompressionSupported(boolean supported)
	public boolean getPerFrameWebSocketCompressionSupported()
		* �Ƿ��Զ�ѹ��ws��Ϣ

	public HttpServerOptions setPerMessageWebSocketCompressionSupported(boolean supported)
	public boolean getPerMessageWebSocketCompressionSupported()
		* ��֧�ַ�Ԥѹ��ws��Ϣ

	public HttpServerOptions setWebSocketCompressionLevel(int compressionLevel)
	public int getWebSocketCompressionLevel()
		* ws��Ϣѹ������

	public HttpServerOptions setWebSocketAllowServerNoContext(boolean accept)
	public boolean getWebSocketAllowServerNoContext()
	public HttpServerOptions setWebSocketPreferredClientNoContext(boolean accept)
	public boolean getWebSocketPreferredClientNoContext()
	public int getWebSocketClosingTimeout()
	public HttpServerOptions setWebSocketClosingTimeout(int webSocketClosingTimeout)
	public TracingPolicy getTracingPolicy()
	public HttpServerOptions setTracingPolicy(TracingPolicy tracingPolicy)



-----------------------------
static
-----------------------------
	public static final int DEFAULT_PORT = 80;  // Default port is 80 for HTTP not 0 from HttpServerOptions
	public static final boolean DEFAULT_COMPRESSION_SUPPORTED = false;
	public static final int DEFAULT_COMPRESSION_LEVEL = 6;
	public static final int DEFAULT_MAX_WEBSOCKET_FRAME_SIZE = 65536;
	public static final int DEFAULT_MAX_WEBSOCKET_MESSAGE_SIZE = 65536 * 4;
	public static final int DEFAULT_MAX_CHUNK_SIZE = 8192;
	public static final int DEFAULT_MAX_INITIAL_LINE_LENGTH = 4096;
	public static final int DEFAULT_MAX_HEADER_SIZE = 8192;
	public static final int DEFAULT_MAX_FORM_ATTRIBUTE_SIZE = 2048;
	public static final boolean DEFAULT_HANDLE_100_CONTINE_AUTOMATICALLY = false;
	public static final List<HttpVersion> DEFAULT_ALPN_VERSIONS = Collections.unmodifiableList(Arrays.asList(HttpVersion.HTTP_2, HttpVersion.HTTP_1_1));
	public static final long DEFAULT_INITIAL_SETTINGS_MAX_CONCURRENT_STREAMS = 100;
	public static final int DEFAULT_HTTP2_CONNECTION_WINDOW_SIZE = -1;
	public static final boolean DEFAULT_DECOMPRESSION_SUPPORTED = false;
	public static final boolean DEFAULT_ACCEPT_UNMASKED_FRAMES = false;
	public static final int DEFAULT_DECODER_INITIAL_BUFFER_SIZE = 128;
	public static final boolean DEFAULT_PER_FRAME_WEBSOCKET_COMPRESSION_SUPPORTED = true;
	public static final boolean DEFAULT_PER_MESSAGE_WEBSOCKET_COMPRESSION_SUPPORTED = true;
	public static final int DEFAULT_WEBSOCKET_COMPRESSION_LEVEL = 6;
	public static final boolean DEFAULT_WEBSOCKET_ALLOW_SERVER_NO_CONTEXT = false;
	public static final boolean DEFAULT_WEBSOCKET_PREFERRED_CLIENT_NO_CONTEXT = false;
	public static final int DEFAULT_WEBSOCKET_CLOSING_TIMEOUT = 10;
	public static final TracingPolicy DEFAULT_TRACING_POLICY = TracingPolicy.ALWAYS;