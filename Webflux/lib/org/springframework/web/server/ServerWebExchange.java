-----------------------------
ServerWebExchange
-----------------------------
	# ��װ��req/resp�Ľӿ�
		interface ServerWebExchange 

-----------------------------
this
-----------------------------
	ServerHttpRequest getRequest();
	ServerHttpResponse getResponse();
	Map<String, Object> getAttributes();
	default <T> T getAttribute(String name)
	default <T> T getRequiredAttribute(String name)
	default <T> T getAttributeOrDefault(String name, T defaultValue)
	Mono<WebSession> getSession();
	<T extends Principal> Mono<T> getPrincipal()

	Mono<MultiValueMap<String, String>> getFormData();
		* ��ȡ������

	Mono<MultiValueMap<String, Part>> getMultipartData();
		* ��ȡMultipart����

	LocaleContext getLocaleContext();
	ApplicationContext getApplicationContext();
	boolean isNotModified();
	boolean checkNotModified(Instant lastModified);
	boolean checkNotModified(String etag);
	boolean checkNotModified(@Nullable String etag, Instant lastModified);
	String transformUrl(String url);
	void addUrlTransformer(Function<String, String> transformer);
	String getLogPrefix();
	default Builder mutate()

------------------------
static
------------------------
	String LOG_ID_ATTRIBUTE = ServerWebExchange.class.getName() + ".LOG_ID";