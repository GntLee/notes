----------------------------
DeliveryOptions
----------------------------
	# ������Ϣ������ class DeliveryOptions 

----------------------------
����
----------------------------
	public DeliveryOptions()
	public DeliveryOptions(DeliveryOptions other)
	public DeliveryOptions(JsonObject json)


----------------------------
ʵ��
----------------------------
	public JsonObject toJson()

	public long getSendTimeout() 
	public DeliveryOptions setSendTimeout(long timeout)
		* ���ʹ���Ӧ����������Ϣʱ���������ó�ʱʱ��
		* ��������ʱ��֮��û���յ�Ӧ������ԡ�ʧ�ܵĽ����Ϊ��������Ӧ��������Ĭ�ϳ�ʱ�� 30 �롣

	public String getCodecName()
	public DeliveryOptions setCodecName(String codecName)
		* ����/��ȡ��������Ϣʵ�õ� Codec ����
		* ��������ã���ʹ��Ĭ�ϵ�

	public DeliveryOptions addHeader(String key, String value)
	public DeliveryOptions setHeaders(MultiMap headers)
		* �������Header

	public MultiMap getHeaders()
	private void checkHeaders()
	public boolean isLocalOnly()
	public DeliveryOptions setLocalOnly(boolean localOnly)
	public TracingPolicy getTracingPolicy()
	public DeliveryOptions setTracingPolicy(TracingPolicy tracingPolicy)


----------------------------
��̬
----------------------------
	public static final long DEFAULT_TIMEOUT = 30 * 1000;
	public static final boolean DEFAULT_LOCAL_ONLY = false;
	public static final TracingPolicy DEFAULT_TRACING_POLICY = TracingPolicy.PROPAGATE;