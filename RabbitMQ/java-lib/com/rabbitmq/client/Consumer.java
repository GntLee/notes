---------------------------------------
Consumer
---------------------------------------
	# public interface Consumer 
		* push����ģʽʵ�ֽӿ�
	
	# ϵͳ�Դ���ʵ����
		DefaultConsumer

---------------------------------------
static
---------------------------------------


---------------------------------------
this
---------------------------------------


	public abstract void handleShutdownSignal(String consumerTag, ShutdownSignalException sig)
		* Channel ���� Connection �رյ�ʱ������

	public abstract void handleCancel(String consumerTag)
	public abstract void handleCancelOk(String consumerTag)
		* ����������ʽ������ʾȡ������ʱ����

	public abstract void handleRecoverOk(String consumerTag)
	public abstract void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
	public abstract void handleConsumeOk(String consumerTag)
		* ������������֮ǰ����
