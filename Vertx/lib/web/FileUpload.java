----------------------
FileUpload
----------------------
	# �ϴ��ļ��Ľӿڣ� interface FileUpload

	String name();
	String uploadedFileName();
		* �����ϴ��ļ���ʱIO�Ĵ���·��

	String fileName();
	long size();
	String contentType();
	String contentTransferEncoding();
	String charSet();
	boolean cancel();
