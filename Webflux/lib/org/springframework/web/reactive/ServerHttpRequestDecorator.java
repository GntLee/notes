------------------------------------
ServerHttpRequestDecorator
------------------------------------
	# Request�İ�װ��
		public class ServerHttpRequestDecorator implements ServerHttpRequest
	
------------------------------------
static
------------------------------------

------------------------------------
this
------------------------------------
	public ServerHttpRequestDecorator(ServerHttpRequest delegate)
		* ��װRequest

	public ServerHttpRequest getDelegate()
		* ��ȡ��װ��Request
	
	public static <T> T getNativeRequest(ServerHttpRequest request)
		* ���صײ������API�ı�������������ܵĻ���ҲҪ���װ
		* ����ֻ�������£�������������������쳣
			ServerHttpRequestDecorator
			AbstractServerHttpRequest