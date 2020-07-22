------------------------
http
------------------------
	# java9�ṩ��
	# �������
		HttpClient 
		HttpRequest
		HttpResponse
		HttpHeaders
		WebSocket
		
		* ��Щ����е�����, ����'ֻ����', ֻ�ṩ�� getter ����
		* ��ʼ����ʱ����Ҫͨ�� builder ���������Ե� set

------------------------
http - GET
------------------------
	# GET
		

	# ����
		

------------------------
http - POST
-----------------------
	// ���� Http �ͻ���
	HttpClient httpClient = HttpClient.newHttpClient();
	
	// ���������������
	HttpRequest request = HttpRequest
				.newBuilder(new URI("http://localhost/test"))
				.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.header("Accept", "application/json")   
				.POST(BodyPublishers.ofString("name=KevinBlandy"))
				.build();
	
	// ������Ӧ������
	BodyHandler<String> bodyHandler = BodyHandlers.ofString(StandardCharsets.UTF_8);
	
	// ִ�����󣬻�ȡ��Ӧ
	HttpResponse<String> responseBody = httpClient.send(request, bodyHandler);
	
	System.out.println(responseBody);

------------------------
http - �ļ�����
-----------------------
	�Ѹ�, û���ֳɵ�api
