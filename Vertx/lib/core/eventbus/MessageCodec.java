---------------------
MessageCodec
---------------------
	# ��Ϣ��������ӿڣ� interface MessageCodec<S, R>

	void encodeToWire(Buffer buffer, S s);
		* ��s���뵽buffer�У�����

	R decodeFromWire(int pos, Buffer buffer);
		* ��buffer�ж�ȡ���ݣ�λ���ǣ�post������Ϊ����

	R transform(S s);
		* ��s����ΪR

	String name();
		* �Զ��������

	byte systemCodecID();