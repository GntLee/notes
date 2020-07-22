-------------------------
POST					 |
-------------------------
	URI postForLocation(String url, @Nullable Object request, Object... uriVariables) throws RestClientException;
	URI postForLocation(String url, @Nullable Object request, Map<String, ?> uriVariables)throws RestClientException;
	URI postForLocation(URI url, @Nullable Object request) throws RestClientException;

	<T> T postForObject(String url, @Nullable Object request, Class<T> responseType,Object... uriVariables) throws RestClientException;
	<T> T postForObject(String url, @Nullable Object request, Class<T> responseType,Map<String, ?> uriVariables) throws RestClientException;
	<T> T postForObject(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException;

	<T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType,Object... uriVariables) throws RestClientException;
	<T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType,Map<String, ?> uriVariables) throws RestClientException;
	<T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType)throws RestClientException;



	# http����
		RestTemplate restTemplate = new RestTemplate();

		// ������Ϣͷ
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		// ������Ϣ��
		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("name", "KevinBlandy"); // ������Ϣ
		requestBody.add("age", "23");
		requestBody.addAll("skills", Arrays.asList("java","python","javascript","c")); // �����Ϣ
		
		// ������http��Ϣ��
		HttpEntity<MultiValueMap> httpEntity = new HttpEntity<>(requestBody,httpHeaders);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost/user.do", httpEntity, String.class);
	
	# json����

		RestTemplate restTemplate = new RestTemplate();

		// ������Ϣͷ
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		// ����JSON������
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name","KevinBlandy");
		jsonObject.put("age",23);

		// ����������http����
		HttpEntity<JSONObject> httpEntity = new HttpEntity<>(jsonObject,httpHeaders);
		// ����ֱ��ʹ��json�ַ��� : HttpEntity<String> httpEntity = new HttpEntity<>(jsonObject.toJSONString(),httpHeaders);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8081/user.do", httpEntity, String.class);

	
	# �ಿ������
		RestTemplate restTemplate = new RestTemplate();
		
		// ��Ϣͷ
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA); // �ಿ������

		MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
		// ������ͨ����
		multipartBodyBuilder.part("name", "KevinBlandy");
		multipartBodyBuilder.part("skill", Arrays.asList("Java","Python","Javascript","C"));

		// �����ļ�����(����,�ļ�����,ContentType)
		Resource file1 = new FileSystemResource("D:\\20181009153347.jpg");
		multipartBodyBuilder.part("file", file1,MediaType.IMAGE_JPEG).header("Bar", "Foo");

		Resource file2 = new ClassPathResource("log-1.log"); // Resource �����кܶ���ʵ��,���ڴӲ�ͬ��Դ��������
		multipartBodyBuilder.part("file", file2,MediaType.TEXT_PLAIN).header("Bar", "Foo"); // �������ö��ͬ�����Ƶ��ļ�����
		
		// ��������Ϣ��
		MultiValueMap<String, HttpEntity<?>> multipartBody = multipartBodyBuilder.build();

		ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity("http://localhost:8081/user.do", multipartBody, JSONObject.class);

		System.out.println(responseEntity);