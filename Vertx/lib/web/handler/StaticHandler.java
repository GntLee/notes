--------------------------
StaticHandler
--------------------------
	# ��̬��Դ������handler�ӿڣ� interface StaticHandler extends Handler<RoutingContext> 
	# ���
		router.route("/static/*").handler(StaticHandler.create());
	
		* ��Vert.x�״�����·�����ҵ���Դʱ�����Ḵ���������ش�����
		* ���Ҫ��ֹ������ƣ�����ͨ�� FileSystemOptions ����


--------------------------
static
--------------------------
	String DEFAULT_WEB_ROOT = "webroot";
	boolean DEFAULT_FILES_READ_ONLY = true;
	long DEFAULT_MAX_AGE_SECONDS = 86400; // One day
	boolean DEFAULT_CACHING_ENABLED = !WebEnvironment.development();
	boolean DEFAULT_DIRECTORY_LISTING = false;
	String DEFAULT_DIRECTORY_TEMPLATE = "META-INF/vertx/web/vertx-web-directory.html";
	boolean DEFAULT_INCLUDE_HIDDEN = true;
	long DEFAULT_CACHE_ENTRY_TIMEOUT = 30000; // 30 seconds
	String DEFAULT_INDEX_PAGE = "/index.html";
	int DEFAULT_MAX_CACHE_SIZE = 10000;
	boolean DEFAULT_ALWAYS_ASYNC_FS = false;
	boolean DEFAULT_ENABLE_FS_TUNING = true;
	long DEFAULT_MAX_AVG_SERVE_TIME_NS = 1000000; // 1ms
	boolean DEFAULT_RANGE_SUPPORT = true;
	boolean DEFAULT_ROOT_FILESYSTEM_ACCESS = false;
	boolean DEFAULT_SEND_VARY_HEADER = true;

	static StaticHandler create() 
	static StaticHandler create(String root)
	StaticHandler setAllowRootFileSystemAccess(boolean allowRootFileSystemAccess)
	StaticHandler setWebRoot(String webRoot)
		* ������ԴĿ¼

	StaticHandler setFilesReadOnly(boolean readOnly)
		* �������Ϊtrue���򻺴����ڴ��е��ļ�����޸�ʱ�䣬��Զ������£���Զ����ȥ���̼��
		* Ĭ��Ϊtrue
	
	StaticHandler setMaxAgeSeconds(long maxAgeSeconds)
		* ��������˿ͻ��˻��棬�������������������ļ���ʱ�䣬��λ����
		* Ĭ���ǣ�DEFAULT_MAX_AGE_SECONDS�� 1��

	StaticHandler setCachingEnabled(boolean enabled)
		* ����/���ÿͻ��˻���
		* ���û��洦���Vert.x-Web�����ڴ��л�����Դ���ϴ��޸����ڣ��������Ա���ÿ�η��ʴ���ʱ��Ҫ���ʵ�ʵ��ϴ��޸����ڡ�
		* �����е���Ŀ��һ������ʱ�䣬�ڴ�ʱ��֮�󣬽��ٴμ������ϵ��ļ��������»����е���Ŀ��


	StaticHandler setDirectoryListing(boolean directoryListing)
		* �Ƿ��ṩĿ¼�б�

	StaticHandler setIncludeHidden(boolean includeHidden)
		* �Ƿ�¶�����ļ�

	StaticHandler setCacheEntryTimeout(long timeout)
		* ���ã��ڴ��л�����ļ�����޸�ʱ�䣬ʧЧʱ�䡣�������ʱ��󣬾ͻ�ȥIO�������¶�ȡ��

	StaticHandler setIndexPage(String indexPage)
		* ����Ĭ��ҳ����ļ����ƣ�Ĭ�ϣ� index.html

	StaticHandler setMaxCacheSize(int maxCacheSize)
		* �������ڴ��е��ļ�����޸�ʱ�䣬��໺����ٸ���

	StaticHandler setHttp2PushMapping(List<Http2PushMapping> http2PushMappings)
	StaticHandler skipCompressionForMediaTypes(Set<String> mediaTypes)
	StaticHandler skipCompressionForSuffixes(Set<String> fileSuffixes)
	StaticHandler setAlwaysAsyncFS(boolean alwaysAsyncFS)
	StaticHandler setEnableFSTuning(boolean enableFSTuning)
	StaticHandler setMaxAvgServeTimeNs(long maxAvgServeTimeNanoSeconds)
	StaticHandler setDirectoryTemplate(String directoryTemplate)
		* ������ʾĿ¼�б��ģ��

	StaticHandler setEnableRangeSupport(boolean enableRangeSupport)
	StaticHandler setSendVaryHeader(boolean varyHeader)
	StaticHandler setDefaultContentEncoding(String contentEncoding)
