---------------------------------------
ShutdownNotifier
---------------------------------------
	# public interface ShutdownNotifier

---------------------------------------
static
---------------------------------------


---------------------------------------
this
---------------------------------------


	public abstract boolean isOpen()
		* �ж������Ƿ��Ǵ�״̬
		* ������ʹ�����������ʵ���඼�Ǽ����ģ����ܺܵ�

	public abstract void removeShutdownListener(ShutdownListener listener)
	public abstract void addShutdownListener(ShutdownListener listener)
		* ���/ɾ���رռ���
		* ��� ���� �Ѿ��ǹر�״̬����ᱻ��������

	public abstract void notifyListeners()
		* ��������������

	public abstract ShutdownSignalException getCloseReason()
		* ��ȡ���ӹرյ�ԭ��