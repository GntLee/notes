-----------------------------
ServerWebExchange
-----------------------------
	# ��װ��req/resp�Ľӿ�
		interface ServerWebExchange 
	
	# ������Context��ɫ
		

-----------------------------
this
-----------------------------
	ServerHttpRequest getRequest();
	ServerHttpResponse getResponse();
		* ��ȡreq/resp����

	Map<String, Object> getAttributes();
		* ���ص�ǰexchange���������ԣ����ؽ����һ���ɱ��Map

	default <T> T getAttribute(String name)
		* ����key��ȡ���ԣ�����ֵ������null
	
	default <T> T getRequiredAttribute(String name)
		* ����KEY��ȡ���ԣ����ֵ�����ڣ��׳��쳣: IllegalArgumentException

	default <T> T getAttributeOrDefault(String name, T defaultValue)
		* ����KEY��ȡ���ԣ����ֵ�����ڣ�����Ĭ��ֵ

	Mono<WebSession> getSession();
		* ���ص�ǰ��Session

	<T extends Principal> Mono<T> getPrincipal()
		* ���ص�ǰ�������֤�û���������ڵĻ�

	Mono<MultiValueMap<String, String>> getFormData();
		* ��ȡ������
		* ֻ��Content-TypeΪapplication/x-www-form-urlencoded��ʱ����������Ż᷵��һ���ǿյ�Map

	Mono<MultiValueMap<String, Part>> getMultipartData();
		* ��ȡMultipart����
		* ֻ��Content-TypeΪmultipart/form-data��ʱ����������Ż᷵��һ���ǿյ�Map
		* ����ʹ�� @RequestBody Flux<Part> ����װ
			

	LocaleContext getLocaleContext();

	ApplicationContext getApplicationContext();
		* ����Spring��������
	
	boolean isNotModified();
	boolean checkNotModified(Instant lastModified);
	boolean checkNotModified(String etag);
	boolean checkNotModified(@Nullable String etag, Instant lastModified);
		* lastModified�������

	String transformUrl(String url);
		*  URLת��
	
	void addUrlTransformer(Function<String, String> transformer);
		* URLת��ӳ��

	String getLogPrefix();
		* ��ȡ��־ǰ׺

	default Builder mutate()
		* �����족�����ʾ��ǰѵ�ǰ��WebExchange����copy������װ��Builder���棬����ͨ��Builder�޸�һЩ���Ժ���дbuildһ��WebExchange
		* Ĭ��ʵ��
			new DefaultServerWebExchangeBuilder(this);

------------------------
static
------------------------
	String LOG_ID_ATTRIBUTE = ServerWebExchange.class.getName() + ".LOG_ID";
		* ��־ǰ׺���Ե�KEY��ֵ: Ϊorg.springframework.web.server.ServerWebExchange.LOG_ID
		* �������Ϊ: attributes.set("org.springframework.web.server.ServerWebExchange.LOG_ID", "��־ǰ׺�ľ���ֵ");
		* �����Ǵ�ӡ��־��ʱ���ƴ�����KEY��Ӧ��ǰ׺ֵ��Ĭ��ֵΪ: ""
	
