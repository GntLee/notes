----------------------
httpsץ��
----------------------
	# ��ѡѡ��
		Tools -> Options -> Https -> Decrypt Https Traffic
			
		
		Ignore Server Certificate error (Unsafe)
		Check For Certificate Revocation
		Protocols
		Skip Decryption For The Follow Hosts

		
	

	

----------------------
httpsץ��ʧ�ܴ���
----------------------

	# Certificate Error
		* Creation of the root certificate was not successful.

		1. ����https֤��
			Actions ->	Reset All Certificates
		
		2. ����֤��
			Actions ->	Trust Root Certificates
		
		3. �鿴֤���Ƿ�����OK
			Actions ->	Open Windows Certificate Manager

			* ���� -> ����֤�� -> ����: fiddler
			* ��ѯfiddler�ĸ�֤���Ƿ����ɳɹ�
	

----------------------
���win7��֤�����������
----------------------
	# ����fiddler�İ�װĿ¼, Ȼ��ִ����������
		makecert.exe -r -ss my -n "CN=DO_NOT_TRUST_FiddlerRoot, O=DO_NOT_TRUST, OU=Created by http://www.fiddler2.com" -sky signature -eku 1.3.6.1.5.5.7.3.1 -h 1 -cy authority -a sha1 -m 120 -b 01/01/2020

 

		-m
			* ָ����Ч��(��λ����)
		-b
			* ֤����Чʱ��

	* ִ����֮�� ��ȥ��������һ�� �Ϳ�����ȷ��װ֤����

	# ���� FiddlerCertMaker & ִ��
		http://www.enhanceie.com/dl/FiddlerCertMaker.exe


----------------------
firefox httpsץ��
----------------------
	# �ȸ��IE, ���Ƕ�ȡϵͳ��֤��, �Ƚϼ�
	# Firefox �ǹ����Լ���֤��

	# ѡ�� -> �߼� -> ���� -> ϵͳ����/�ֶ�ָ������

	# ���� Fiddler���ɵ�֤��
		