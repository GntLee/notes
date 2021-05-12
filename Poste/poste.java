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
@			TXT		"v=spf1 mx -all"

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
-p 465:465 \
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
			https://mail.springcloud.io/admin/ 
		
		* �ʼ������̨
			https://mail.springcloud.io/webmail
			
	
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

-----------------------------
Go����
-----------------------------
	import (
		"gopkg.in/gomail.v2"
	)


	func main() {
		// �����ʼ���Ϣ�����ñ���
		message := gomail.NewMessage(gomail.SetCharset("utf-8"))

		message.SetAddressHeader("From", "noreply@springcloud.io", "SpringBoot��������") // �������Լ�����������
		message.SetHeader("To", "747692844@qq.com")                                  // �ռ��ˣ������ж��
		message.SetHeader("Subject", "��ӭע��!")                                        // ����
		message.SetBody("text/html", "<h1>Hello World</h1>")                         // �ʼ�����

		// ��������������Ϣ��ָ�������˿ڣ��˻���������
		conn := gomail.NewDialer("smtp.springcloud.io", 465, "noreply@springcloud.io", "123456")

		// �������ӣ����ҷ����ʼ�
		if err := conn.DialAndSend(message); err != nil {
			panic(err)
		}
	}



-----------------------------
discourse����
-----------------------------
env
  DISCOURSE_SMTP_ADDRESS: smtp.springcloud.io
  DISCOURSE_SMTP_PORT: 587
  DISCOURSE_SMTP_USER_NAME: noreply@springcloud.io
  DISCOURSE_SMTP_PASSWORD: "123456"
  DISCOURSE_SMTP_ENABLE_START_TLS: true           # (optional, default true)
  DISCOURSE_SMTP_AUTHENTICATION: login
  DISCOURSE_SMTP_OPENSSL_VERIFY_MODE: none

run:
  - exec: rails r "SiteSetting.notification_email='noreply@springcloud.io'"
