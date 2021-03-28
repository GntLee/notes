--------------------
Future
--------------------
	# Future ��Ҫ���ڳ����첽���

	# Future/�ص� 2�ַ�ʽ��ȡ�첽���
		FileSystem fileSystem = Vertx.vertx().fileSystem();

		// ͨ���Է���  Future �ļ�������ȡ�����
		fileSystem.props("D:\\avatar.png").onComplete(result -> {
			if (result.succeeded()) {
				FileProps fileProps = result.result();
				System.out.println(fileProps.size());
			} else {
				Throwable throwable = result.cause();
				System.err.println(throwable.getMessage());
			}
		});
		
		// ����һ��Handler��ͨ���ص���ȡ�����
		fileSystem.props("D:\\avatar.png", result -> {
			if (result.succeeded()) {
				FileProps fileProps = result.result();
				System.out.println(fileProps.size());
			} else {
				Throwable throwable = result.cause();
				System.err.println(throwable.getMessage());
			}
		});
	
	# Future ���
		* ��һ���첽���������������ȫ�����ɹ��������յĽ�����ǳɹ�������κ�һ��ʧ�ܣ����յĽ������ʧ��
			FileSystem fileSystem = Vertx.vertx().fileSystem();
			String file = "D:\\demo.txt";

			// �첽�����ļ�������Future
			fileSystem.createFile(file)
				.compose(result -> { // result == Void
					// �첽���ļ�д�����ݣ����� Future
					return fileSystem.writeFile(file, Buffer.buffer("Hello Vertx", StandardCharsets.UTF_8.displayName()));
				})
				.compose(result -> {	 // result == Void
					// �첽�ƶ��ļ�������Future
					return fileSystem.move(file, "D:\\demo_new.txt");
				})
				.onComplete(resullt -> {
					// ���ս��
					if (resullt.succeeded()) {
						System.out.println("����ˣ�������д�룬�ƶ�");
					} else {
						System.err.println("�������쳣��" + resullt.cause().getMessage());
					}
				})
				;
	
