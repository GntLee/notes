---------------------------
http
---------------------------
	# ����Ĵ�������
		* �������ͷ��Ϣ����ȫ��ȡʱ�����Handler
			HttpServer server = vertx.createHttpServer();
			server.requestHandler(request -> {
			  // Handle the request in here
			});
		
		* ��һ����body���Ա���ȡ��ʱ�򣬵��ã� HttpServerRequest handler(Handler<Buffer> handler);
		* ����������ܻᱻִ�ж�Σ������Լ�������е�����
			httpServer.requestHandler(req -> {
				Buffer body = Buffer.buffer();		// �洢����Body
				req.handler(chunk -> {				// ��ÿ�ζ�ȡ�������ݣ�����ӵ�body
					body.appendBuffer(chunk);
				});
				req.endHandler(vod -> {
					System.out.println(body.toString());	// ��������ȫ����ȡ��Ϻ󣬻�ȡbody�е�����
				});
				req.response().putHeader("content-type", "text/plain; charset=utf-8").end("success");
			})

		* �ۺ������壬����: 
			default HttpServerRequest bodyHandler(@Nullable Handler<Buffer> bodyHandler)	// ����ص���ֻ����body() -> success�ĲŻ����
			default HttpServerRequest body(Handler<AsyncResult<Buffer>> handler)			// ����ص�
			Future<Buffer> body();															// ��ʽ����
		* ����Զ��ۺ����������壬���Լ�������¼���BodyΪnull����Ϊ��
			
	
		* ���������󣨰������������壩�Ѿ�����ȫ��ȡʱ�����ã� HttpServerRequest endHandler(Handler<Void> endHandler);
	
	# ������
		* ������body��ȡ��Ϻ���ܻ�ȡ
		* ��ʹ�� application/x-www-form-urlencoded �� multipart/form-data ������ content-type ���ύ HTML ����
		
			request.setExpectMultipart(true);  // ��Ҫ�������
			request.endHandler(v -> {			// ��body������ȡ��Ϻ󣬵��� formAttributes
				MultiMap formAttributes = request.formAttributes();
			});
		
		* �ͻ��˱�����ȷ������ ContentType���Լ��ǺϷ�����Я��body�����󷽷�
	
	# �ಿ����������
		* ����multipart ������ʽ�ϴ��ĵ��ļ�
		
		httpServer.requestHandler(req -> {
			req.setExpectMultipart(true);
			req.exceptionHandler(err -> {
				req.response().putHeader("content-type", "text/plain; charset=utf-8").end("error:" + err.getMessage());
			});
			
			
			// ����ÿһ���ļ�
			req.uploadHandler(upload -> {
				String formName = upload.name();
				String fileName = upload.filename();
				String contentType = upload.contentType();
				long size = upload.size();
				
				System.out.println(String.format("formName=%s, fileName=%s, contentType=%s, size=%sd", formName, fileName, contentType, size));
				
				// ��ȡ���ڴ棬����IO������
				Buffer body = Buffer.buffer();	
				upload.handler(chunk -> {
					body.appendBuffer(chunk);
				});
				
				upload.endHandler(vod -> {
					System.out.println("��ȡ���");
					System.out.println("body��" + body.toString("utf-8"));
				});
			});
			
			// ͳһ�����
			req.endHandler(vod -> {
				MultiMap form = req.formAttributes();
				System.out.println("form=" + form.toString());
			});
			
			req.response().putHeader("content-type", "text/plain; charset=utf-8").end("success");
		})
	
	# server push
		
		