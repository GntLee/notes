--------------------
��Ӧѹ������		|
--------------------
	 # ������
		org.springframework.boot.context.embedded.Compression
	
	# ����������
server:
  compression:

    # ����ѹ��
    enabled: true

    # ����֧��ѹ������Ӧ��������,Ĭ��:text/html", "text/xml", "text/plain","text/css", "text/javascript", "application/javascript
    mime-types: 
      - application/json
      - application/xml
      - application/javascript
      - text/html
      - text/xml
      - text/plain
      - text/css
      - text/javascript
	 
    # Ĭ�������,����ѹ��2048�ֽ����ϵ�����
    min-response-size: 2048

    # ָ����ѹ����user-agent ,Ĭ��Ϊ null
    excluded-user-agents:
      - application/json


	# �����ÿ�����̬��Դ���ᱻѹ��	

