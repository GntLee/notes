---------------------
POST JSON			 |
---------------------
	String post(String url, String json) throws IOException {

		MediaType JSON = MediaType.parse("application/json; charset=utf-8");

		OkHttpClient client = new OkHttpClient();

		// json������
		RequestBody body = RequestBody.create(JSON, json);

		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

---------------------
POST FORM			 |
---------------------
	OkHttpClient client = new OkHttpClient();

	// ��������
	RequestBody body = new FormBody.Builder()
			.add("name","KevinBlandy")          // ��ӱ���
			.addEncoded("charset","utf-8")      // ��ӱ����ı���
			.build();

	Request request = new Request.Builder().url("").post(body).build();

	Response response = client.newCall(request).execute();
	response.body().string();

---------------------
�����ļ�����		 |
---------------------
	OkHttpClient client = new OkHttpClient();

	// �ļ�����
	File file = new File("");
	RequestBody requestBody = new MultipartBody.Builder()
		.setType(MultipartBody.FORM)
		.addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
		.addFormDataPart("name", "KevinBlandy")
		.build();

	Request request = new Request.Builder().url("").post(requestBody).build();
	Response response = client.newCall(request).execute();
	response.body().string();


---------------------
���ӵ��ļ�����	 |
---------------------
	OkHttpClient client = new OkHttpClient();
	// �ļ�����
	File file = new File("");

	RequestBody requestBody = new MultipartBody.Builder()

		.setType(MultipartBody.FORM)        //�����ļ�����

		.addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/png"), file))  // �Ӵ��̼����ļ�����

		.addFormDataPart("file","head.jpg", RequestBody.create(MediaType.parse("image/png"), new byte[]{})) // ���ֽ������������

		.addFormDataPart("name", "KevinBlandy") // ��ͨ�ı���

		.addPart(MultipartBody.Part.create(  // �Զ�����Ϣͷ�ı���
				// �������Ϣͷ
				Headers.of(Collections.singletonMap("foo","bar")),
				// ��Ϣ��
				new FormBody.Builder()
						.addEncoded("name","KevinBlandy")
						.build()))

		.addPart(MultipartBody.Part.create( //�Զ�����Ϣͷ���ļ���
				// �������Ϣͷ
				Headers.of(Collections.singletonMap("foo","bar")),
				// ��Ϣ��
				RequestBody.create(MediaType.parse("image/png"), new byte[]{})
				))
		.build();

	Request request = new Request.Builder().url("").post(requestBody).build();

	Response response = client.newCall(request).execute();
	
	response.body().string();

