-----------------------------
Poste						 |
-----------------------------
	# ����
		https://poste.io
	
	# �ο��ĵ�
		https://www.wumvp.com/2019/03/24/poste.html
	
	# ��������(springcloud.io)

mail		A		[�ʼ�������ip]

smtp		CNAME	mail.springcloud.io	 
pop			CNAME	mail.springcloud.io	 
imap		CNAME	mail.springcloud.io

@			MX		mail.springcloud.io
@			TXT		"v=spf1 mx ~all"

s20191223631._domainkey TXT "DKIM key
	* �ü�¼��Ҫ��д��ͨ������̨���ɵ�key
	* ���ڿ���̨���ɺ�����д

_dmarc.springcloud.io	TXT "v=DMARC1; p=none; rua=mailto:dmarc-reports@springcloud.io"
	* �ü�¼������Ҫ���




	# Docker ��װ
docker run \
-p 25:25 \
-p 80:80 \
-p 110:110 \
-p 143:143 \
-p 443:443 \
-p 587:587 \
-p 993:993 \
-p 995:995 \
-v /etc/localtime:/etc/localtime:ro \
-v /srv/poste/data:/data \
--name "PosteServer" \
-h "mail.springcloud.io" \
-t analogic/poste.io
	
	# ����admin���� & ����
		admin@springcloud.io
	
	# ������Կ
		Virtual domains -> springcloud.io -> create new key
		
		* �� DKIM key ����������

	# SSL����
		System settings -> TLS Certificate -> Let's Encrypt certificate

		Enable: ��
		Common name: mail.springcloud.io
		Alternativenames: 
			smtp.springcloud.io
			pop.springcloud.io
			imap.springcloud.io


	# ҳ��
		* �ܹ����̨
			https://mail.undefined.design/admin/ 
		
		* �ʼ������̨
			https://mail.undefined.design/webmail
			
	
	# �����ʼ�����
		���� -> ѡ�� -> ���� -> ����[Ĭ�ϸ����ҵĹ��� PGP ��Կ]ѡ������, ������
	
	# ���÷��������
		���� -> ��������� -> ��ʾ����			=> �ʼ��б���, ��ʾ������
		���� -> ��������� -> ��֯				=>


	# ��־
		* �鿴��־
			docker logs -f PosteServer
		
		* ��־Ŀ¼
			data/logs
