------------------------
json
------------------------
	# json��ķ�װ�����Ǿ�̬����

------------------------
��̬
------------------------
	public static final JsonCodec CODEC = load().codec()
		* ȫ��Ԥ����ı������

	public static io.vertx.core.spi.JsonFactory load()
		* ��spi����json�ı��������

	public static String encode(Object obj) throws EncodeException
	public static Buffer encodeToBuffer(Object obj) throws EncodeException
	public static String encodePrettily(Object obj) throws EncodeException

	public static <T> T decodeValue(String str, Class<T> clazz) throws DecodeException
	public static Object decodeValue(String str) throws DecodeException
	public static Object decodeValue(Buffer buf) throws DecodeException
	public static <T> T decodeValue(Buffer buf, Class<T> clazz) throws DecodeException
		* ����Json����Ϊ�Զ������
