---------------------------------------
BasicProperties
---------------------------------------
	# public static class BasicProperties extends com.rabbitmq.client.impl.AMQBasicProperties
	
	# ��Ϣ�����ԣ�����ͨ��Builderģʽ����

---------------------------------------
static
---------------------------------------


---------------------------------------
this
---------------------------------------
	public BasicProperties()
	public BasicProperties(DataInputStream in)
	public BasicProperties(
            String contentType,
				* ��Ϣbody������
            String contentEncoding,
            Map<String,Object> headers,
				* ������Ϣheader
            Integer deliveryMode,
				* ��ϢͶ��ģʽ
            Integer priority,
				* ��Ϣ���ȼ�
            String correlationId,
            String replyTo,
            String expiration,	
				* ����ʱ�䣬��λ�Ǻ���
            String messageId,
            Date timestamp,
            String type,
            String userId,
            String appId,
            String clusterId)


	public boolean equals(Object arg0)
	public int hashCode()
	public Integer getPriority()
	public String getType()
	public String getClassName()
	public String getContentType()
	public String getContentEncoding()
	public String getExpiration()
	public Map getHeaders()
	public Date getTimestamp()
	public void appendPropertyDebugStringTo(StringBuilder arg0)
	public void writePropertiesTo(ContentHeaderPropertyWriter arg0)
	public Builder builder()
	public int getClassId()
	public String getCorrelationId()
	public String getReplyTo()
	public Integer getDeliveryMode()
	public String getClusterId()
	public String getMessageId()
	public String getAppId()
	public String getUserId()
