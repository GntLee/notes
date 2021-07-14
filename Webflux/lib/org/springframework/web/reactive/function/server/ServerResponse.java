------------------------
ServerResponse
------------------------
	# Response�ӿ�
		interface ServerResponse 

------------------------
static
------------------------
	static BodyBuilder from(ServerResponse other)
	static BodyBuilder status(HttpStatus status)
	static BodyBuilder status(int status)
	static BodyBuilder ok()
	static BodyBuilder created(URI location)
	static BodyBuilder accepted()
	static HeadersBuilder<?> noContent()
	static BodyBuilder seeOther(URI location)
	static BodyBuilder temporaryRedirect(URI location)
	static BodyBuilder permanentRedirect(URI location) 
	static BodyBuilder badRequest()
	static HeadersBuilder<?> notFound()
	static BodyBuilder unprocessableEntity()


------------------------
this
------------------------
	HttpStatus statusCode();
	int rawStatusCode();
	HttpHeaders headers();
	MultiValueMap<String, ResponseCookie> cookies();
	Mono<Void> writeTo(ServerWebExchange exchange, Context context);


------------------------
ServerResponse$HeadersBuilder
------------------------
	# �ڲ��ӿ�
		interface HeadersBuilder<B extends HeadersBuilder<B>> 
	
	# ����
		B header(String headerName, String... headerValues);
		B headers(Consumer<HttpHeaders> headersConsumer);
		B cookie(ResponseCookie cookie);
		B cookies(Consumer<MultiValueMap<String, ResponseCookie>> cookiesConsumer);
		B allow(HttpMethod... allowedMethods);
		B allow(Set<HttpMethod> allowedMethods);
		B eTag(String eTag);
		B lastModified(ZonedDateTime lastModified);
		B lastModified(Instant lastModified);
		B location(URI location);
		B cacheControl(CacheControl cacheControl);
		B varyBy(String... requestHeaders);
		Mono<ServerResponse> build();
		Mono<ServerResponse> build(Publisher<Void> voidPublisher);
		Mono<ServerResponse> build(BiFunction<ServerWebExchange, Context, Mono<Void>> writeFunction);
	


------------------------
ServerResponse$BodyBuilder
------------------------
	# �ڲ��ӿ�
		interface BodyBuilder extends HeadersBuilder<BodyBuilder>
	
	# ����
		BodyBuilder contentLength(long contentLength);
		BodyBuilder contentType(MediaType contentType);
		BodyBuilder hint(String key, Object value);
		BodyBuilder hints(Consumer<Map<String, Object>> hintsConsumer);
		Mono<ServerResponse> bodyValue(Object body);
		<T, P extends Publisher<T>> Mono<ServerResponse> body(P publisher, Class<T> elementClass);
		<T, P extends Publisher<T>> Mono<ServerResponse> body(P publisher, arameterizedTypeReference<T> elementTypeRef);
		Mono<ServerResponse> body(Object producer, Class<?> elementClass);
		Mono<ServerResponse> body(Object producer, ParameterizedTypeReference<?> elementTypeRef)
		Mono<ServerResponse> body(BodyInserter<?, ? super ServerHttpResponse> inserter);
		@Deprecated
		Mono<ServerResponse> syncBody(Object body);
		Mono<ServerResponse> render(String name, Object... modelAttributes);
		Mono<ServerResponse> render(String name, Map<String, ?> model);
	
------------------------
ServerResponse$Context
------------------------
	# �ڲ��ӿ�
		interface Context
	
	# ����
		List<HttpMessageWriter<?>> messageWriters();
		List<ViewResolver> viewResolvers();

