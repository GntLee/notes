# ����Ŀ¼
	cd /etc/pki/tls/certs

# ����key
	 make server.key
	
	* ��Ҫ��������

# ����RSA key
	openssl rsa -in server.key -out server.key

	 * ��Ҫ���� server.key ���õ�����

# ����ssr
	 make server.csr

	 Country Name (2 letter code) [XX]:
		* ���ù���

	State or Province Name (full name) []:
		* �޻�ʡ������
	
	Locality Name (eg, city) [Default City]:
		* ����
	
	Organization Name (eg, company) [Default Company Ltd]:
		* ��֯����
	
	Organizational Unit Name (eg, section) []:
		* ��֯��λ
	
	Common Name (eg, your name or your server's hostname) []:
		* ������ַ
	
	Email Address []:
		* �����ʼ�
	
	* ��������ȫ���հ�, һ�ٻس������¶���
	

# ����֤��
	openssl x509 -in server.csr -out server.crt -req -signkey server.key -days 3650
	
	Signature ok
	subject=/C=cn/ST=cq/L=cq/O=undefined/OU=undefined/CN=undefined.design
	Getting Private key
	Enter pass phrase for server.key:
		* �������������, ������Ҫ����

# �޸�֤���Ȩ��
	chmod 400 server.* 


# vim /etc/postfix/main.cf
	* ������õ����

smtpd_use_tls = yes
smtpd_tls_cert_file = /etc/pki/tls/certs/server.crt
smtpd_tls_key_file = /etc/pki/tls/certs/server.key
smtp_tls_session_cache_database = btree:${data_directory}/smtp_scache
smtpd_tls_session_cache_database = btree:${data_directory}/smtpd_scache
smtpd_tls_loglevel = 0
smtpd_tls_auth_only = yes

# vim /etc/postfix/master.cf
	* ȡ�������õ�ע��(28��)
	* ǧ��ע��, -o ǰ�������ո�

	  -o smtpd_tls_wrappermode=yes

# vim /etc/dovecot/conf.d/10-ssl.conf 
	* 8��, ����Ϊyes
		ssl = yes

	* 14,16��: ָ��֤��
		ssl_cert = </etc/pki/tls/certs/server.crt
		ssl_key = </etc/pki/tls/certs/server.key
	
	* �������������,����Ҫ��21����������
		ssl_key_password = 123456

# ����SSL�˿�
	firewall-cmd --add-port={465/tcp,995/tcp,993/tcp} --permanent
	firewall-cmd --reload 

# ����
	systemctl restart postfix
	systemctl restart dovecot