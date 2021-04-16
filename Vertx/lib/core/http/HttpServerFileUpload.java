-----------------------
HttpServerFileUpload
-----------------------
	# multipart���������ӿڣ�  interface HttpServerFileUpload extends ReadStream<Buffer> 

	HttpServerFileUpload exceptionHandler(Handler<Throwable> handler);
	HttpServerFileUpload handler(Handler<Buffer> handler);
		* ÿ�ν�����body���ݶ�������������
		* �����Ա����ö��
		
	HttpServerFileUpload endHandler(Handler<Void> endHandler);
		* ��ǰbody������Ϻ�ᱻ����

	HttpServerFileUpload pause();
	HttpServerFileUpload resume();
	HttpServerFileUpload fetch(long amount);

	void streamToFileSystem(String filename, Handler<AsyncResult<Void>> handler);
	Future<Void> streamToFileSystem(String filename);
		* IO������Ŀ¼
		
	boolean cancelStreamToFileSystem() throws IllegalStateException;

	String filename();
		* �ļ�����
	String name();
		* ������
	String contentType();
	String contentTransferEncoding();
	String charset();
	long size();
	boolean isSizeAvailable();
	AsyncFile file();