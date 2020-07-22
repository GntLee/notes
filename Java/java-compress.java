-----------------------------
ѹ���㷨					 |
-----------------------------
	ZipOutputStream
	ZipInputStream

	GZIPOutputStream
	GZIPInputStream


-----------------------------
�Ѷ���ļ�ѹ��Ϊzip			 |
-----------------------------
	public static void packet(Path[] files, Path zipFile) throws IOException {
		OutputStream outputStream = Files.newOutputStream(zipFile, StandardOpenOption.CREATE_NEW);
    	ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        for (Path file : files) {
        	InputStream inputStream = Files.newInputStream(file);
        	ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
            zipOutputStream.putNextEntry(zipEntry);
            int len;
            byte[] buffer = new byte[1024 * 10];
            while ((len = inputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
        }
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        outputStream.close();
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
