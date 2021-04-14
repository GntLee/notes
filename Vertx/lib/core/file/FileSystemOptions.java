----------------------------------
FileSystemOptions
----------------------------------
	# ���ļ�ϵͳ�������ࣺ class FileSystemOptions 



----------------------------------
����
----------------------------------
	public FileSystemOptions()
	public FileSystemOptions(FileSystemOptions other) 
	public FileSystemOptions(JsonObject json)

----------------------------------
ʵ��
----------------------------------
	public JsonObject toJson() 

	public boolean isClassPathResolvingEnabled()
	public FileSystemOptions setClassPathResolvingEnabled(boolean classPathResolvingEnabled)
		* �Ƿ���classpath�ļ�����

	public boolean isFileCachingEnabled()
	public FileSystemOptions setFileCachingEnabled(boolean fileCachingEnabled)
		* �Ƿ���classpath�ļ�����

	public String getFileCacheDir()
	public FileSystemOptions setFileCacheDir(String fileCacheDir)
		* ����classpath�ļ�����Ŀ¼

	public String toString()

----------------------------------
��̬
----------------------------------
	public static final boolean DEFAULT_FILE_CACHING_ENABLED = !Boolean.getBoolean(DISABLE_FILE_CACHING_PROP_NAME);
	public static final boolean DEFAULT_CLASS_PATH_RESOLVING_ENABLED = !Boolean.getBoolean(DISABLE_CP_RESOLVING_PROP_NAME);
	public static final String DEFAULT_FILE_CACHING_DIR = System.getProperty(CACHE_DIR_BASE_PROP_NAME, TMPDIR + File.separator + DEFAULT_CACHE_DIR_BASE);
