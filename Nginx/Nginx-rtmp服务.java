-----------------------
���밲װrtmpģ��		|
-----------------------
	# ����stmpģ��
		https://github.com/arut/nginx-rtmp-module

	# ����NginxԴ��
		..��

	# ����
./configure --prefix=/usr/local/nginx \
--add-module=/opt/nginx-rtmp-module \
--with-http_stub_status_module \
--with-http_ssl_module \
--with-file-aio \
--with-http_realip_module \
--with-http_v2_module \
--with-stream \
--with-stream_ssl_module \
--with-http_slice_module \
--with-mail \
--with-mail_ssl_module

	make && make install

	* add-module ָ����Ǵ�git clone������Ŀ¼

	# �鿴Nginx���밲װ��ģ��
		nginx -V

-----------------------
�������				|
----------------------
	