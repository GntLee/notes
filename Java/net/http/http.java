------------------------
http
------------------------
	# java9提供的
	# 核心组件
		HttpClient 
		HttpRequest
		HttpResponse
		HttpHeaders
		WebSocket
		
		* 这些组件中的属性, 大都是'只读的', 只提供了 getter 方法
		* 初始化的时候需要通过 builder 来进行属性的 set

------------------------
http - GET
------------------------
	# GET
		

	# 下载
		

------------------------
http - POST
-----------------------
	// 创建 Http 客户端
	HttpClient httpClient = HttpClient.newHttpClient();
	
	// 创建请求和请求体
	HttpRequest request = HttpRequest
				.newBuilder(new URI("http://localhost/test"))
				.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.header("Accept", "application/json")   
				.POST(BodyPublishers.ofString("name=KevinBlandy"))
				.build();
	
	// 创建响应处理器
	BodyHandler<String> bodyHandler = BodyHandlers.ofString(StandardCharsets.UTF_8);
	
	// 执行请求，获取响应
	HttpResponse<String> responseBody = httpClient.send(request, bodyHandler);
	
	System.out.println(responseBody);

------------------------
http - 文件表单体
-----------------------
	难搞, 没得现成的api
