
	# �ο�
		https://blog.csdn.net/xu_0705/article/details/34435445

	# ���������ļ�
		openssl.cnf

	
	# ����RSA˽Կ
		openssl genrsa -out ca_root.key 2048
			* -out �ö�����ļ�,��ָ���Ļ�,�������Ļ
			*  2048 ��ʾRSA��Կ����,Ĭ�� 2048
	
	# ����֤������
		openssl req -new -key ca_root.key -out ca_root.csr -md5
			* -key ָ��RSA˽Կ�ļ�
			* -out  ���ɵ�֤���ļ�
			* -config ָ�������ļ�
			* -md5 ʹ�õ�hash�㷨,Ĭ��ʹ�õ���sha1
		
		* ��Ҫ��������
			Contry Name:cn
				* ����,��Сд��ĸ
			State Of Province Name:����
				* ʡ��
			Locality Name:����
				* ����
			Organization Name:Javaweb
				* ��ҵ����
			Organizational Unit Name:Javaweb
				* ��λ����
			Common Name:javaweb.io
				* ����
			Email Address:747692844@qq.com
				* �ʼ���ַ
			
			* ����ѡ����Բ��ÿ���,����.�س���������

			A challenge password:
				* ����
			An Optional Company name:
				* ��ѡ�Ĺ�˾����

		* ֱ�ӻس���ѡ��ʹ��Ĭ��ֵ,�����"."����ʾ����Ϣ������
	

	# ǩ��֤��
		openssl req -x509 -key ca_root.key -in ca_root.csr -out ca1.crt -days 365
			* -x509 ��ʾ��Ҫ��ǩ��֤��
			* -key ָ��RSA˽Կ
			* -in ֤�������ļ�
			* -out ǩ����֤���ļ�
			* -days ����ʱ��
		
		* "-x509" ������� "-new"��"-newkey"ʹ��,�����Ļ�,����Ҫָ������֤���ļ�(-in ѡ��)
		* "-x509"ѡ���,"-new"��"-newkey"����ʾ����һ��֤���ļ�,������һ��֤�������ļ�
		* ������ǩ��������Զ���������֤���ļ�,������Ҫ����ʽ������������Ϣ
			openssl req -new -x509 -key ca_root.key -out ca1.crt -days 365

------------------------
ǩ���¼�֤��			|
------------------------
	# �����Լ���˽Կ
		openssl genrsa -aes128 -out myprivate.key 2048
	
	# ����֤�������ļ�
		openssl  req -new -key  myprivate.key  -out MyCaReq.csr -config  D:\OpensslInstall\openssl.cnf
	
	# ��֤��ǩ���¼�֤��
		openssl x509 -req -in MyCaReq.csr  -out MyCa.crt   -signkey myprivate.key  -CA CARoot.crt -CAkey rootca.key -CAcreateserial  -days 990

------------------------
��������				|
------------------------
	# ��֤����ȡ����Կ
		openssl req -in ca_root.csr -pubkey -noout
			* -in ָ��֤���ļ�
			* -pubkey ָ����ȡ��Կ
			* -noout �������Ļ
			* -out ָ����Կ������ļ�
	
	# ��֤�����ļ�������ǩ��,����������֤��֤�������ļ��Ƿ񱻴۸Ĺ�
		openssl req -verify -in ca_root.csr
			 -in ָ��֤���ļ�
	
