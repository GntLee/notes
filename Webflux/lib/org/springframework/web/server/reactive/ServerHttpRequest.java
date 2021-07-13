--------------------
ServerHttpRequest
--------------------
	# HttpRequest�ӿ�
		public interface ServerHttpRequest extends HttpRequest, ReactiveHttpInputMessage


--------------------
this
--------------------
	
	HttpHeaders getHeaders();
		* ��ȡheader��ע�⣬���header��ֻ���ģ������޸�
		* �����Ҫ�޸ģ���ѡͨ�� mutate ���ص�Builder���޸ĺ���дBuildһ���µ�Request

	default HttpMethod getMethod()
	String getMethodValue();
		* ���󷽷�

	URI getURI();
		* ��ȡ�����URL

	Flux<DataBuffer> getBody();
		* ��ȡ������
	
	String getId();

	RequestPath getPath();
		* ��ȡ����path

	MultiValueMap<String, String> getQueryParams();
		* ��ѯ����

	MultiValueMap<String, HttpCookie> getCookies();
		* ����cookie

	default InetSocketAddress getLocalAddress()
	default InetSocketAddress getRemoteAddress()
		* ����/Զ�̵�ַ

	default SslInfo getSslInfo()

	default ServerHttpRequest.Builder mutate()
		* �����족��copy��ǰ���ݣ�����һ��Builder������ͨ��Builder�޸�һЩ���ݺ���Buildeһ���µ�Request
		* Ĭ��ʵ��
			return new DefaultServerHttpRequestBuilder(this);

