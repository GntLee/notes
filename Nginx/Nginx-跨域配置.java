-----------------------------
Nginx cors��������
-----------------------------

server {
    listen 80;
    server_name localhost 127.0.0.1;
	location / {
		# �����������ġ���
		add_header 'Access-Control-Allow-Origin' $http_origin;
		# ����ͻ����ύCookie
		add_header 'Access-Control-Allow-Credentials' 'true';
		# ����ͻ��˵����󷽷�
		add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS, DELETE, PUT';
		# ����ͻ����ύ�ĵ�����ͷ
		add_header 'Access-Control-Allow-Headers' 'Origin, x-requested-with, Content-Type, Accept, Authorization';
		# ����ͻ��˷��ʵ���Ӧͷ
		add_header 'Access-Control-Expose-Headers' 'Cache-Control, Content-Language, Content-Type, Expires, Last-Modified, Pragma';
		# ����Ԥ������
		if ($request_method = 'OPTIONS') {
			# Ԥ�����󻺴�ʱ��
			add_header 'Access-Control-Max-Age' 1728000;
			add_header 'Content-Type' 'text/plain; charset=utf-8';
			add_header 'Content-Length' 0;
			return 204;
		}
		
		# SpringBoot Ӧ�÷���·��
		proxy_pass http://127.0.0.1:8080;
		
		proxy_set_header Host $host;
		proxy_set_header X-Real-IP $remote_addr;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		proxy_set_header X-Forwarded-Proto $scheme;
		
		proxy_connect_timeout 600;
		proxy_read_timeout 600;
	}
}
