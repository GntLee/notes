--------------------
CopyOptions
--------------------
	# �����ļ����ã� class CopyOptions 


--------------------
����
--------------------
	public CopyOptions()
	public CopyOptions(CopyOptions other)
	public CopyOptions(JsonObject json)

	public boolean isReplaceExisting()
	public CopyOptions setReplaceExisting(boolean replaceExisting)
		* ���Ŀ���ļ��Ѿ����ڣ��Ƿ��滻

	public boolean isCopyAttributes()
	public CopyOptions setCopyAttributes(boolean copyAttributes) 
		* �Ƿ����ļ�����

	public boolean isAtomicMove()
	public CopyOptions setAtomicMove(boolean atomicMove)
		* �Ƿ�Ժ���ƶ�

	public boolean isNofollowLinks()
	public CopyOptions setNofollowLinks(boolean nofollowLinks)
		* �Ƿ��������

--------------------
ʵ��
--------------------
	

--------------------
��̬
--------------------
	public static final boolean DEFAULT_REPLACE_EXISTING = false;
	public static final boolean DEFAULT_COPY_ATTRIBUTES = false;
	public static final boolean DEFAULT_ATOMIC_MOVE = false;
	public static final boolean DEFAULT_NOFOLLOW_LINKS = false;
