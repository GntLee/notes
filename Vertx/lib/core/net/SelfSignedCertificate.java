------------------------------------
SelfSignedCertificate
------------------------------------
	# ��ǩ��֤��Ľӿڣ������ã�  interface SelfSignedCertificate
		
		PemKeyCertOptions keyCertOptions();
		PemTrustOptions trustOptions();
		String privateKeyPath();
		String certificatePath();
		void delete();

		static SelfSignedCertificate create()
		static SelfSignedCertificate create(String fqdn)