-----------------------
BodyHandler
-----------------------
	# �����崦�����ӿڣ� interface BodyHandler extends Handler<RoutingContext> 

-----------------------
this
-----------------------
	BodyHandler setHandleFileUploads(boolean handleFileUploads)
	BodyHandler setBodyLimit(long bodyLimit)
		* ������Ϣ������
		* ������Է��ʹ�������С������Request Entity Too Large����ᷢ��HTTP״̬��413
	
	BodyHandler setUploadsDirectory(String uploadsDirectory)
		* �����ϴ��ļ�����ʱIOĿ¼

	BodyHandler setMergeFormAttributes(boolean mergeFormAttributes)
		* Ĭ������£����������body�����ϲ����������

	BodyHandler setDeleteUploadedFilesOnEnd(boolean deleteUploadedFilesOnEnd)
		* �����Ƿ��ڽ���ʱ�Զ�ɾ���ϴ�����ʱ�ļ�

	BodyHandler setPreallocateBodyBuffer(boolean isPreallocateBodyBuffer)
		

-----------------------
static
-----------------------
	long DEFAULT_BODY_LIMIT = -1;
		* ���body���������������

	String DEFAULT_UPLOADS_DIRECTORY = "file-uploads";
		* Ĭ���ļ��ϴ�����ʱ�ļ�������

	boolean DEFAULT_MERGE_FORM_ATTRIBUTES = true;
		* �ϲ�������

	boolean DEFAULT_DELETE_UPLOADED_FILES_ON_END = false;
		* �ڽ�����ʱ��ɾ��IO�����̵���ʱ�ļ�

	boolean DEFAULT_PREALLOCATE_BODY_BUFFER = false;
		* �Ƿ����HTTP����ͷ�����ݳ���Ԥ�������建������С��Ĭ��ֵ��
	
	static BodyHandler create()
	static BodyHandler create(boolean handleFileUploads)
	static BodyHandler create(String uploadDirectory)

