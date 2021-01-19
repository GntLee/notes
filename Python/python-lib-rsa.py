-------------------------
rsaģ��					 |
-------------------------
	# ������Կ��
		(publicKey, privateKey) rsa.newkeys(length)
			* length ��ʾ����
		
	# ��key���л�Ϊ֤���ʽ���ı�
		publicKey.save_pkcs1().decode()
		privateKey.save_pkcs1().decode()

		* ��ʵ�������л�Ϊ�ַ���
			-----BEGIN RSA PUBLIC KEY-----
			MIIBCgKCAQEA3TQgbv7yA2tmp6Cxp2VO9dz8ByfYSZWVhtpc1kKYwTljjaZv2U4e
			...
			-----END RSA PUBLIC KEY-----

			-----BEGIN RSA PRIVATE KEY-----
			MIIEqgIBAAKCAQEA3TQgbv7yA2tmp6Cxp2VO9dz8ByfYSZWVhtpc1kKYwTljjaZv
			2U4eKCc4k33z6euoWwEyn5eYYfSC+9I7AJLwJXq7ABy+o/fBVXZR/WsepuX506Ew
			...
			-----END RSA PRIVATE KEY-----
	
	# ������Կ
		* �����ı���ʽ�Ĺ�Կ����Կ(��ʽ����)
		
		rsa.PublicKey.load_pkcs1(publicKeyStr)
		rsa.PrivateKey.load_pkcs1(privateKeyStr)
	
	# ˽Կǩ��,��Կ��ǩ
		signature = rsa.sign(content, privateKey, 'SHA-1')	# ʹ��˽Կ�����ǰ��,�㷨ʹ�� SHA-1
		rsa.verify(content, signature, publicKey)				# ʹ�ù�Կ��ǩ��������֤
	
	# ��Կ����,˽Կ����
		import rsa
		publicKey, privateKey = rsa.newkeys(512)                        # ��ʼ����Կ��
		result = rsa.encrypt("��ð�".encode('UTF_8'), publicKey)        # ��Կ����,���������
		content = rsa.decrypt(result, privateKey);                      # ˽Կ�������Ľ��ܳ�ԭʼ����
		print(content.decode('UTF_8'))

	# ˽Կ���ܹ�Կ����
		* ��Ȼrsa�㷨������֧�ֶԳƵĹ�Կ����˽Կ����/˽Կ���ܹ�Կ����,���󲿷�ƽ̨��rsa api����Ƴ�ֻ�ṩpublic key encrypt/ private key decrypt�Ľӿ�
		* ��������˽Կ���ܻ����˽Կй¶�ķ���,һ��˽Կ���ܹ���ֻ����ǩ��sign
		* ��Ϊsign�Ĺ����Ǽ���֮ǰ����Ϣ����hash,Ȼ��der,Ȼ�����,��֤�Ĺ����������,�ԱȽ��ܺ�der����֮���hash���Ա�,��˲���й¶private key

