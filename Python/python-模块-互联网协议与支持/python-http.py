------------------------
http					|
------------------------
	* ��ģ���ṩ�˹���httpͨ�ŵĵ�һЩ����

	* http״̬�����: http.HTTPStatus
		http.HTTPStatus.OK
		http.HTTPStatus.CREATED
	

	

------------------------
http.server				|
------------------------
	* ��
		HTTPServer(socketserver.TCPServer)
		BaseHTTPRequestHandler(socketserver.StreamRequestHandler)
		SimpleHTTPRequestHandler(BaseHTTPRequestHandler)
		CGIHTTPRequestHandler(SimpleHTTPRequestHandler)

	
	* ����
		nobody_uid()
		executable(path)
		test(HandlerClass=BaseHTTPRequestHandler,ServerClass=HTTPServer, protocol="HTTP/1.0", port=8000, bind="")


