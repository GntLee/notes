----------------------
NetworkOptions
----------------------
	# �������Ļ������������ࣺ abstract class NetworkOptions
		* Ӧ���ǻ���ͨ��Э���ж��������

----------------------
����
----------------------
	public NetworkOptions() 
	public NetworkOptions(NetworkOptions other)
	public NetworkOptions(JsonObject json) 


----------------------
ʵ��
----------------------
	public JsonObject toJson()
	public int getSendBufferSize()
	public NetworkOptions setSendBufferSize(int sendBufferSize)
	public int getReceiveBufferSize()
	public NetworkOptions setReceiveBufferSize(int receiveBufferSize)
	public boolean isReuseAddress()
	public NetworkOptions setReuseAddress(boolean reuseAddress)
	public int getTrafficClass()
	public NetworkOptions setTrafficClass(int trafficClass)
	public boolean getLogActivity()
	public NetworkOptions setLogActivity(boolean logActivity)
	public boolean isReusePort(
	public NetworkOptions setReusePort(boolean reusePort)


----------------------
��̬
----------------------
	public static final int DEFAULT_SEND_BUFFER_SIZE = -1;
	public static final int DEFAULT_RECEIVE_BUFFER_SIZE = -1;
	public static final int DEFAULT_TRAFFIC_CLASS = -1;
	public static final boolean DEFAULT_REUSE_ADDRESS = true;
	public static final boolean DEFAULT_REUSE_PORT = false;
	public static final boolean DEFAULT_LOG_ENABLED = false;
