------------------------------
Response
------------------------------
	# interface FullHttpResponse extends HttpResponse, FullHttpMessage
	
	# Ĭ�ϵ�ʵ��
		DefaulFullHttpResponse
	
		DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status)
		DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, boolean validateHeaders)
		DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, boolean validateHeaders, boolean singleFieldHeaders)
		DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf content, boolean validateHeaders)
		DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf content, boolean validateHeaders, boolean singleFieldHeaders)
		DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf content, HttpHeaders headers, HttpHeaders trailingHeaders)
	
		
