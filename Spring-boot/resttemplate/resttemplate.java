-----------------------
RestTemplate		   |
-----------------------
	# http�ͻ���
	# ������: InterceptingHttpAccessor ʵ��: RestOperations
	# ���ĵ����
		private final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
			* ��Ϣת����
			
		private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();
			* �쳣������

		private final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
			* ����������

		private ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			* http���󹤳�

	# ���캯��
		public RestTemplate(ClientHttpRequestFactory requestFactory)
		public RestTemplate()
		public RestTemplate(List<HttpMessageConverter<?>> messageConverters)

	
	# ������Ϣת����
		* �������л�/�����л�����Ϊhttp��Ϣ
		* ��Ϣת��������:HttpMessageConverter<T> �ӿڵ�ʵ����
		
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setCharset(StandardCharsets.UTF_8);
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);

		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		
		restTemplate.setMessageConverters(Arrays.asList(fastJsonHttpMessageConverter));
	
	# ����������
		* �ܼ�,����һ��������ģʽ

		restTemplate.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor(){
			/**
			 * @param request		�������
			 * @param body			������
			 * @param execution		ִ����
			 * @return
			 * @throws IOException
			 */
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
				// ִ����һ��������,���һ�ȡ����Ӧ
				ClientHttpResponse clientHttpResponse = execution.execute(request,body);
				// ������Ӧ����һ��������
				return clientHttpResponse;
			}
		}));
	
	# �����쳣������
		* ResponseErrorHandler ʵ����

		restTemplate.setErrorHandler(new ResponseErrorHandler(){
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return false;
			}

			@Override
			public void handleError(ClientHttpResponse response) throws IOException {

			}

			@Override
			public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {

			}
		});

-------------------------
ClientHttpRequestFactory |
-------------------------
	# ���ӹ�����������һ���ӿ�
		ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException;

	# �Ѿ��ṩ��ʵ��
		SimpleClientHttpRequestFactory
		OkHttp3ClientHttpRequestFactory


	# RestTemplate ���� ClientHttpRequestFactory ��api
		ClientHttpRequestFactory getRequestFactory()
		void setRequestFactory(ClientHttpRequestFactory requestFactory)
	
	
	# �����Զ����Factory
		OkHttpClient okHttpClient = new OkHttpClient();
		OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory = new OkHttp3ClientHttpRequestFactory(okHttpClient);

		restTemplate.setRequestFactory(okHttp3ClientHttpRequestFactory);
