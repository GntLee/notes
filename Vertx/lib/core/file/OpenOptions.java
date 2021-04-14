-----------------------
OpenOptions
-----------------------
	# �ļ��Ĵ����ã� class OpenOptions 


-----------------------
����
-----------------------	
	public OpenOptions()
	public OpenOptions(OpenOptions other)
	public OpenOptions(JsonObject json) 

	public String getPerms()
	public OpenOptions setPerms(String perms)
		* ����Ȩ�ޣ����½���ʱ����õ�

	public boolean isRead()
	public OpenOptions setRead(boolean read)
	public boolean isWrite()
	public OpenOptions setWrite(boolean write)
		* �򿪶�д��ֵ

	public boolean isCreate()
	public OpenOptions setCreate(boolean create)
		* ����ļ��������򴴽�

	public boolean isCreateNew()
	public OpenOptions setCreateNew(boolean createNew)
		* �ļ������ǲ����ڵģ������쳣

	public boolean isDeleteOnClose()
	public OpenOptions setDeleteOnClose(boolean deleteOnClose)
		* ���ļ��رյ�ʱ��ɾ���ļ�

	public boolean isTruncateExisting()
	public OpenOptions setTruncateExisting(boolean truncateExisting)
		* ����ļ��Ѿ����ڣ���ض�Ϊ0

	public boolean isSparse()
	public OpenOptions setSparse(boolean sparse)

	public boolean isSync()
	public OpenOptions setSync(boolean sync)

	public boolean isDsync()
	public OpenOptions setDsync(boolean dsync)

	public boolean isAppend()
	public OpenOptions setAppend(boolean append)
		* ��ӵ�ĩβ

-----------------------
ʵ��
-----------------------

-----------------------
��̬
-----------------------
  public static final String DEFAULT_PERMS = null;
  public static final boolean DEFAULT_READ = true;
  public static final boolean DEFAULT_WRITE = true;
  public static final boolean DEFAULT_CREATE = true;
  public static final boolean DEFAULT_CREATENEW = false;
  public static final boolean DEFAULT_DSYNC = false;
  public static final boolean DEFAULT_SYNC = false;
  public static final boolean DEFAULT_DELETEONCLOSE = false;
  public static final boolean DEFAULT_TRUNCATEEXISTING = false;
  public static final boolean DEFAULT_SPARSE = false;

  public static final boolean DEFAULT_APPEND = false;