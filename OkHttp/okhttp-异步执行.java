----------------------------
�첽ִ��					|
----------------------------
	OkHttpClient okHttpClient = new OkHttpClient();

	Request request = new Request.Builder()
			.url("http://www.qq.com")
			.build();

	Call call = okHttpClient.newCall(request);

	// �첽ִ�в�������,���ûص�
	call.enqueue(new Callback() {
		// �����쳣
		@Override
		public void onFailure(Call call, IOException e) {
			System.out.println(e.getMessage());
		}
		// ������Ӧ
		@Override
		public void onResponse(Call call, Response response) throws IOException {
			if (response.isSuccessful()) {
				System.out.println(response.body().string());
			}
		}
	});
