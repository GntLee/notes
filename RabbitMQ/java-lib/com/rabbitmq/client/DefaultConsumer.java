---------------------------------------
DefaultConsumer
---------------------------------------
	# public class DefaultConsumer implements Consumer
		* ϵͳ�Դ���Consumerʵ����

---------------------------------------
static
---------------------------------------


---------------------------------------
this
---------------------------------------
	public DefaultConsumer(Channel channel)


	public Channel getChannel()
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig)
	public String getConsumerTag()
		* �����߱�ǩ��ͬһ��Channel�еĲ�ͬ�������߲�һ��
	
	public void handleConsumeOk(String consumerTag)
	public void handleCancelOk(String consumerTag)
	public void handleCancel(String consumerTag)
	public void handleRecoverOk(String consumerTag)

	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
		* ��������
