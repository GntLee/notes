---------------------
ServerHttpResponse
---------------------
	# HTTP��Ӧ�ӿ�
		public interface ServerHttpResponse extends ReactiveHttpOutputMessage 

---------------------
this
---------------------

	HttpHeaders getHeaders();
		* ��ȡ��Ӧ��Header�����Header����ֱ���޸�
	
	DataBufferFactory bufferFactory();
		* ��ȡDataBufferFactoryʵ�������ڰ�װ�����������ݻ�����DataBufferʵ��(������Ӧ��)

	void beforeCommit(Supplier<? extends Mono<Void>> action);
		* ע��һ����������HttpOutputMessage�ύ(��Ӧ)֮ǰ�˶�������лص�

	boolean isCommitted();
		* �ж�HttpOutputMessage�Ƿ��Ѿ��ύ(��Ӧ)

	Mono<Void> writeWith(Publisher<? extends DataBuffer> body);
		* д����Ϣ�嵽HTTPЭ���

	Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body);
		* д����Ϣ�嵽HTTPЭ��㲢��ˢ�»�����

	Mono<Void> setComplete();
		* ָ����Ϣ�����Ѿ�������һ������Ϣ��������Զ����ô˷�������ε��ò������������

	boolean setStatusCode(@Nullable HttpStatus status);
		* ������Ӧ״̬��

	HttpStatus getStatusCode();
		*  ��ȡ��Ӧ״̬��

	default boolean setRawStatusCode(@Nullable Integer value)
	default Integer getRawStatusCode()
		* ��ȡԭʼ��HTTP״̬�룬int����

	MultiValueMap<String, ResponseCookie> getCookies()
		* ��ȡ��ӦCookie����װΪMultiValueMapʵ���������޸�

	void addCookie(ResponseCookie cookie);
		* �����ӦCookie
