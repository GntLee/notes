-----------------------------
openssl						 |
-----------------------------
	# ����
		https://www.openssl.org



	# ��pem֤��ת��Ϊ p12֤��
		openssl pkcs12 -export -in [name.cer] -inkey [name.key] -out [name.p12]
			-in
				* ֤��

			-inkey
				* ˽Կ
			
			-out
				* ���ɵ�p12֤���ļ�

	# �� pkcs12 ֤��ת��Ϊ pem֤��
		* ��ȡ֤���ļ�
			openssl pkcs12 -in [name.p12] -out [name.pem] -nokeys -clcerts

				-in
					* p12��Կ���ļ�
				-out
					* �����֤���ļ�
				-nokeys
					* �������Կ
				-clcerts

		* ��ȡ˽Կ�ļ�
			openssl pkcs12 -in [name.p12] -out [name.key] -nocerts -nodes

				-in
					* p12��Կ���ļ�
				-out
					* �������Կ�ļ�
				-nocerts
					* �����֤��
				-nodes
					* ����˽Կ�ļ����м���

