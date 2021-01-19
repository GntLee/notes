-----------------------------
ѹ���㷨					 |
-----------------------------
	ZipOutputStream
	ZipInputStream

	GZIPOutputStream
	GZIPInputStream


-----------------------------
zipѹ�����ѹ��
-----------------------------
	/**
	 * ��ѹ�ļ� 
	 * @param file		ѹ���ļ�
	 * @param targetDir	��ѹ�ļ������Ŀ¼
	 * @throws IOException 
	 */
	public static void unPacket(Path file, Path targetDir) throws IOException {
		if (!Files.exists(targetDir)) {
			Files.createDirectories(targetDir);
		}
		// ����zip����
		ZipFile zipFile = new ZipFile(file.toFile());
		try {
			// ��ȡzip��
			try(ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(file))){
				ZipEntry zipEntry = null;
				// ����ÿһ��zip��
				while ((zipEntry = zipInputStream.getNextEntry()) != null) {
					// ��ȡzip��Ŀ����
					String entryName = zipEntry.getName();
					// ��������·��
					Path entryFile = targetDir.resolve(entryName);
					if(zipEntry.isDirectory()) {	// �ļ���
						if (!Files.isDirectory(entryFile)) {
							Files.createDirectories(entryFile);
						}
					} else {							// �ļ�
						// ��ȡzip��������
						try(InputStream zipEntryInputStream = zipFile.getInputStream(zipEntry)){
							try(OutputStream fileOutputStream = Files.newOutputStream(entryFile, StandardOpenOption.CREATE_NEW)){
								byte[] buffer = new byte[4096];
								int length = 0;
								while ((length = zipEntryInputStream.read(buffer)) != -1) {
									fileOutputStream.write(buffer, 0, length);
								}
								fileOutputStream.flush();
							}
						}
					}
				}
			}
		} finally {
			zipFile.close();
		}
	}

	/**
	 * ѹ��ָ�����ļ�
	 * @param files				Ŀ���ļ�
	 * @param zipFile			���ɵ�ѹ���ļ�
	 * @throws IOException
	 */
	public static void packet(Path[] files, Path zipFile) throws IOException {
		OutputStream outputStream = Files.newOutputStream(zipFile, StandardOpenOption.CREATE_NEW);
		ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
		try {
			for (Path file : files) {
				if (Files.isDirectory(file)) {
					continue;
				}
				try (InputStream inputStream = Files.newInputStream(file)) {
					// ����һ��ѹ���ָ������
					ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
					// ��ӵ�ѹ����
					zipOutputStream.putNextEntry(zipEntry);
					// ����ע��
					zipOutputStream.setComment("����ע��");
					// д������
					int len = 0;
					byte[] buffer = new byte[1024 * 10];
					while ((len = inputStream.read(buffer)) > 0) {
						zipOutputStream.write(buffer, 0, len);
					}
					zipOutputStream.flush();
				}
			}
			// �������ѹ��������
			zipOutputStream.closeEntry();
		} finally {
			zipOutputStream.close();
			outputStream.close();
		}
	}
	
	/**
	 * ѹ��ָ����Ŀ¼ 
	 * @param folder
	 * @param zipFile
	 * @throws IOException
	 */
	public static void packet(Path folder, Path zipFile) throws IOException {
		if (!Files.isDirectory(folder)) {
			throw new IllegalArgumentException(folder.toString() + " ���ǺϷ����ļ���");
		}
		OutputStream outputStream = Files.newOutputStream(zipFile, StandardOpenOption.CREATE_NEW);
		ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
		
		LinkedList<String> path = new LinkedList<>();
		
		try {
			Files.walkFileTree(folder, new FileVisitor<Path>() {
	
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					
					if (!dir.equals(folder)) {
						// ��ʼ����Ŀ¼
						String folder = dir.getFileName().toString();
						path.addLast(folder);
						// д��Ŀ¼ 
						ZipEntry zipEntry = new ZipEntry(path.stream().collect(Collectors.joining("/", "", "/")));
						try {
							zipOutputStream.putNextEntry(zipEntry);
							zipOutputStream.flush();
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}
					return FileVisitResult.CONTINUE;
				}
	
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					// ��ʼ�����ļ�
					try (InputStream inputStream = Files.newInputStream(file)) {
						
						// ����һ��ѹ���ָ������
						String fileName = path.size() > 0 
								? path.stream().collect(Collectors.joining("/", "", "")) + "/" + file.getFileName().toString()
								: file.getFileName().toString();
						
						ZipEntry zipEntry = new ZipEntry(fileName);
						
						// ��ӵ�ѹ����
						zipOutputStream.putNextEntry(zipEntry);
						// д������
						int len = 0;
						byte[] buffer = new byte[1024 * 10];
						while ((len = inputStream.read(buffer)) > 0) {
							zipOutputStream.write(buffer, 0, len);
						}
						
						zipOutputStream.flush();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					return FileVisitResult.CONTINUE;
				}
				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}
				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					// ��������Ŀ¼
					if (!path.isEmpty()) {
						path.removeLast();
					}
					return FileVisitResult.CONTINUE;
				}
			});
			zipOutputStream.closeEntry();
		} finally {
			zipOutputStream.close();
			outputStream.close();
		}
	}

------------------------------------------
 GIZѹ��/��ѹ��							  |
------------------------------------------
    /**
     * ѹ��
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] gZip(byte[] data) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        GZIPOutputStream gzipOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(data);
            gzipOutputStream.finish();
            bytes = byteArrayOutputStream.toByteArray();
        } finally {

        }
        return bytes;
    }

	/**
     * ��ѹ��
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] unGZip(byte[] data) throws IOException {
        byte[] bytes = null;
        ByteArrayInputStream byteArrayInputStream = null;
        GZIPInputStream gzipInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(data);
            gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] buf = new byte[1024];
            int num = -1;
            byteArrayOutputStream = new ByteArrayOutputStream();
            while ((num = gzipInputStream.read(buf, 0, buf.length)) != -1)
            {
                byteArrayOutputStream.write(buf, 0, num);
            }
            bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
        } finally {
            byteArrayInputStream.close();
            gzipInputStream.close();
            byteArrayOutputStream.close();
        }
        return bytes;
    }
