----------------------
Request
----------------------
	# interface FullHttpRequest extends HttpRequest, FullHttpMessage
		* �ýӿڷ�װ��������������Ϣ
		* Ĭ�ϵ�ʵ��: DefaultFullHttpRequest
	
	# ���߷���
		HttpMethod method();
		HttpRequest setMethod(HttpMethod method);
			* ���󷽷�

		String uri();
		HttpRequest setUri(String uri);
			* �����URI

		HttpRequest setProtocolVersion(HttpVersion version);
		HttpVersion protocolVersion();
			* Э��汾

		HttpHeaders headers();
			* ����ͷ
		
		HttpHeaders trailingHeaders();

		DecoderResult decoderResult();
		void setDecoderResult(DecoderResult result);
			* ������

		ByteBuf content()
			* ��ȡ��������

----------------------
Request - �������
----------------------

----------------------
Request - �ļ��ϴ�
----------------------