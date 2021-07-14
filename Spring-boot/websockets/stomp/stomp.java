------------------------
stomp
------------------------
	# ��ַ
		http://stomp.github.io/
		http://stomp.github.io/stomp-specification-1.2.html

		https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp

		https://blog.csdn.net/a617137379/article/details/78765025

	# STOMP Э��(Simple (or Streaming) Text Orientated Messaging Protocol)
		* stomp��websocket�Ĺ�ϵ������, Http��tcp�Ĺ�ϵ
		* ��������Ӧ��֮����������Ϣ������, ����Ҫȷ�����ӵ����˶�����ѭ��Щ����
	
		* stompЭ�鲢����Ϊwebsocket��Ƶ�, ����������Ϣ���е�һ��Э��, ��amqp, jmsƽ��.
		* ֻ�����������ļ���ǡ�ɿ������ڶ���websocket����Ϣ���ʽ.


	# Maven
		<!--websocket-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
        </dependency>
        <!--jackson,����SockJs��Spring WebSocket֮�����JSONͨѶ����Ҫ����jackson�����jar��-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
	
	# ����
		* client
			������client
			������client
		
		* server
			broker, ��Ϣ���еĹ�����
	
	# ��Ϣ���
		[command]
		header:value
		header:value

		Body^@
		
		* ^@ ����null��β.
		* 3���������: ����, Header, Body
		* body�����Ƕ����ƻ������ַ���
	

------------------------
stomp ����
------------------------
	CONNECT
		* ��������
			CONNECT
			accept-version:1.2
			host:stomp.github.org

			^@
		* ��Ӧ
			CONNECTED
			version:1.2

			^@



