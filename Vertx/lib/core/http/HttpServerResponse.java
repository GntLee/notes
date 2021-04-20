-----------------------------
HttpServerResponse
-----------------------------
	# http��Ӧ�ӿڣ�  interface HttpServerResponse extends WriteStream<Buffer> 

	HttpServerResponse exceptionHandler(Handler<Throwable> handler);
	HttpServerResponse setWriteQueueMaxSize(int maxSize);
	HttpServerResponse drainHandler(Handler<Void> handler);

	int getStatusCode();
	HttpServerResponse setStatusCode(int statusCode);
		* ״̬��

	String getStatusMessage();
	HttpServerResponse setStatusMessage(String statusMessage);
		* ״̬��Ϣ��HTTP2Э�鲻Ӧ��д���
	
	HttpServerResponse setChunked(boolean chunked);
	boolean isChunked();
		* �����ֿ���Ӧ��ÿһ��write�������µ�http��д��
		* �ֿ���Ӧ�� HTTP/2 ������Ч��
	
	MultiMap headers();
	HttpServerResponse putHeader(String name, String value);
	HttpServerResponse putHeader(CharSequence name, CharSequence value);
	HttpServerResponse putHeader(String name, Iterable<String> values);
	HttpServerResponse putHeader(CharSequence name, Iterable<CharSequence> values);
		* ����header
	
	MultiMap trailers();
	HttpServerResponse putTrailer(String name, String value);
	HttpServerResponse putTrailer(CharSequence name, CharSequence value);
	HttpServerResponse putTrailer(String name, Iterable<String> values);
	HttpServerResponse putTrailer(CharSequence name, Iterable<CharSequence> value);
		* �ڷֿ�ģʽ�£����Խ���Ӧ�� HTTP ��Ӧ����β��(trailers)д����Ӧ
		* ���ַ�ʽʵ��������д����Ӧ�����һ�顣



	HttpServerResponse closeHandler(@Nullable Handler<Void> handler);
	HttpServerResponse endHandler(@Nullable Handler<Void> handler);

	Future<Void> write(String chunk, String enc);
	void write(String chunk, String enc, Handler<AsyncResult<Void>> handler);
	Future<Void> write(String chunk);
	void write(String chunk, Handler<AsyncResult<Void>> handler);
		* ���ͻ���д�����ݣ�
		* ��һ��д������ᴥ����Ӧͷ��д�룬��ˣ������ʹ�� HTTP �ֿ飬��ô������д����Ӧ֮ǰ���� Content-Length ͷ�� ���򲻻���Ч��
		* ���ʹ�� HTTP �ֿ�����Ҫ������㡣


	HttpServerResponse writeContinue();

	Future<Void> end(String chunk);
	void end(String chunk, Handler<AsyncResult<Void>> handler);
	Future<Void> end(String chunk, String enc);
	void end(String chunk, String enc, Handler<AsyncResult<Void>> handler);
	Future<Void> end(Buffer chunk);
	void end(Buffer chunk, Handler<AsyncResult<Void>> handler);
	Future<Void> end();
		* �����Ӧ��������ͻ���������1������

	default void send(Handler<AsyncResult<Void>> handler)
	default Future<Void> send()
	default void send(String body, Handler<AsyncResult<Void>> handler)
	default Future<Void> send(String body)
	default void send(Buffer body, Handler<AsyncResult<Void>> handler)
	default Future<Void> send(Buffer body)
	default void send(ReadStream<Buffer> body, Handler<AsyncResult<Void>> handler)
	default Future<Void> send(ReadStream<Buffer> body)

	default Future<Void> sendFile(String filename)
	default Future<Void> sendFile(String filename, long offset)
	Future<Void> sendFile(String filename, long offset, long length)
	default HttpServerResponse sendFile(String filename, Handler<AsyncResult<Void>> resultHandler)
	default HttpServerResponse sendFile(String filename, long offset, Handler<AsyncResult<Void>> resultHandler)
	HttpServerResponse sendFile(String filename, long offset, long length, Handler<AsyncResult<Void>> resultHandler)
		* ֱ�Ӱѱ����ļ���Ӧ���ͻ���
		* �������ö�ȡ����body��ƫ��λ��

	void close();
	boolean ended();
	boolean closed();
	boolean headWritten();
		* �Ƿ��Ѿ���Ӧ�ͻ���header

	HttpServerResponse headersEndHandler(@Nullable Handler<Void> handler);
	HttpServerResponse bodyEndHandler(@Nullable Handler<Void> handler);
	long bytesWritten();
	int streamId();

	default HttpServerResponse push(HttpMethod method, String host, String path, Handler<AsyncResult<HttpServerResponse>> handler)
	default Future<HttpServerResponse> push(HttpMethod method, String host, String path)
	default HttpServerResponse push(HttpMethod method, String path, MultiMap headers, Handler<AsyncResult<HttpServerResponse>> handler)
	default Future<HttpServerResponse> push(HttpMethod method, String path, MultiMap headers)
	default HttpServerResponse push(HttpMethod method, String path, Handler<AsyncResult<HttpServerResponse>> handler)
	default Future<HttpServerResponse> push(HttpMethod method, String path)
	default HttpServerResponse push(HttpMethod method, String host, String path, MultiMap headers, Handler<AsyncResult<HttpServerResponse>> handler)
	Future<HttpServerResponse> push(HttpMethod method, String host, String path, MultiMap headers)
		* http2�ķ�����push
		* ��������Ӧ����֮ǰ���� push������������������Ӧ������Ȼ����д��Ӧ��

	default boolean reset()
	boolean reset(long code)
		* ��ֵ��
		* Ĭ�ϻᷢ�� NO_ERROR (0)������룬Ҳ���Է�������һ���������
		* ���õĴ������
			http://httpwg.org/specs/rfc7540.html#ErrorCodes
		

	HttpServerResponse writeCustomFrame(int type, int flags, Buffer payload)
	default HttpServerResponse writeCustomFrame(HttpFrame frame)
		* д��http2��֡

	default HttpServerResponse setStreamPriority(StreamPriority streamPriority)
	HttpServerResponse addCookie(Cookie cookie)
	default @Nullable Cookie removeCookie(String name)
	@Nullable Cookie removeCookie(String name, boolean invalidate)


	
