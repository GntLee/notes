------------------------
postfix					|
------------------------
	# ��Ҫ���ŵĶ˿�
		25		SMTP, �����ʼ�
		110		pop3, �����ʼ�
		143		imap, �����ʼ�

		465		SMTP, �����ʼ�(ssk)
		995		pop3, �����ʼ�(ssl)
		993		imap, �����ʼ�(ssl)
	
		* ֻ��pop3����, ��ôֻ��Ҫ��110��25�˿ڼ���


			firewall-cmd --add-port=25/tcp --permanent
			firewall-cmd �C-add-port=110/tcp --permanent
			firewall-cmd --add-port=143/tcp --permanent
			firewall-cmd �C-add-port=465/tcp --permanent
			firewall-cmd --add-port=995/tcp --permanent
			firewall-cmd �C-add-port=993/tcp --permanent
			firewall-cmd --reload
	
	# �Ƴ� sendmail
		* ����
			rpm -e sendmail 
		
		* �Ƴ�
			yum remove sendmail
	
	# �Ƴ��ɰ� postfix
		yum remove postfix
	
	# ��װ postfix
		yum install postfix -y
		yum install crontabs -y
		yum install cyrus-sasl* -y
			* �ṩstmp�����˻����������
		
	# �鿴mta(Ĭ���ʼ��������)
		alternatives --display mta
			mta - status is auto.
			 link currently points to /usr/sbin/sendmail.postfix
			/usr/sbin/sendmail.postfix - priority 30
			 slave mta-mailq: /usr/bin/mailq.postfix
			 slave mta-newaliases: /usr/bin/newaliases.postfix
			 slave mta-pam: /etc/pam.d/smtp.postfix
			 slave mta-rmail: /usr/bin/rmail.postfix
			 slave mta-sendmail: /usr/lib/sendmail.postfix
			 slave mta-mailqman: /usr/share/man/man1/mailq.postfix.1.gz
			 slave mta-newaliasesman: /usr/share/man/man1/newaliases.postfix.1.gz
			 slave mta-sendmailman: /usr/share/man/man1/sendmail.postfix.1.gz
			 slave mta-aliasesman: /usr/share/man/man5/aliases.postfix.5.gz
			Current `best' version is /usr/sbin/sendmail.postfix.

			* ���û����仰: Current `best' version is /usr/sbin/sendmail.postfix. ' ��Ҫ����ִ��
				/usr/sbin/alternatives --set mta /usr/sbin/sendmail.postfix
			
			* �ٴβ鿴mta
				alternatives --display mta
	
		
	# ���ÿ�������
		systemctl enable postfix.service
	
	
	# ������������(���A��¼��mx��¼), ��������:undefined.design
		A��¼
			��¼  :mail				(mail.undefined.design)
			��¼ֵ:������Ip

		MX��¼
			��¼  :@				(@.undefined.design)
			��¼ֵ:mail.undefined.design
	
	# �޸ı�����¼
		hostnamectl  set-hostname   mail.undefined.design
	
	# �޸�postfix�����ļ��޸�(/etc/postfix/main.cf)
075��:myhostname = mail.undefined.design 
083��:mydomain = undefined.design 
099��:myorigin = $mydomain 
116��:inet_interfaces = all 
119��:inet_protocols = ipv4 
164��:mydestination = $myhostname, localhost.$mydomain, localhost, $mydomain 
264��:mynetworks = 127.0.0.0/8
419��:home_mailbox = Maildir/ 
571��:smtpd_banner = $myhostname ESMTP 

# ��ӵ���� 
message_size_limit = 10485760 
mailbox_size_limit = 1073741824 
smtpd_sasl_type = dovecot 
smtpd_sasl_path = private/auth 
smtpd_sasl_auth_enable = yes
smtpd_sasl_security_options = noanonymous 
smtpd_sasl_local_domain = $myhostname 
smtpd_recipient_restrictions = permit_mynetworks,permit_auth_destination,permit_sasl_authenticated,reject 
	
	# ����
		systemctl restart postfix
	

	# ��װdovecot
		yum install dovecot -y
	
	# �༭dovecot�����ļ�(/etc/dovecot/dovecot.conf)
30��:listen = *
	* ����ipv4
48��:login_trusted_networks = 192.168.10.0/24
	* ���ƿ��Ե�¼��ip, �����������ط���¼, �����޸�
	
	# �༭�ļ�10-auth.conf (/etc/dovecot/conf.d/10-auth.conf)
 10��:disable_plaintext_auth = no 
100��:auth_mechanisms = plain login 
	
	# �༭�ļ�10-mail.conf(/etc/dovecot/conf.d/10-mail.conf)	
30��:mail_location = maildir:~/Maildir

	# �༭�ļ�10-master.conf(/etc/dovecot/conf.d/10-master.conf)
		* ���Postfix smtp��֤ (88-90��)
unix_listener /var/spool/postfix/private/auth { 
	mode = 0666 
	user = postfix
	group = postfix
}
	
	# �༭�ļ�10-ssl.conf(/etc/dovecot/conf.d/10-ssl.conf)
		* ���ʹ����SSL, �����޸�
8��:ssl = no
	
	# ������dovecot����ӵ���������
		systemctl restart dovecot
		systemctl enable dovecot
	
	# �����û�
		* �ʼ����û��Ǻ�ϵͳ�û�һ�µ�, Ҳ����˵ϵͳ�û����Ե����ʼ��û�
		useradd admin
		passwd admin
	
	
	# Foxmail ��¼
		���շ���������: POP3
		�ʼ��˻�:
		����:
		POP������: mail.undefined.design
		SMTP������: mail.undefined.design
		[] ���������֧��, ��ʹ��start tls ����
			* ���������SSL, �͹���

	
	# ��־
		/var/log/maillog
		
		* ���ͳɹ�����־
			postfix/smtp[17082]: C6CD339B70: to=<747692844@qq.com>, relay=mx3.qq.com[203.205.219.57]:25, delay=2.4, delays=0.28/0.01/0.06/2, dsn=2.0.0, status=sent (250 Ok: queued as )
			* ��� status=bounced ,��ô����ζ��Ŀ���ʼ����������������������IP��Ϊ����IP, ���������IP���͵��ʼ�
			* Ҳ����ζ���������Ҫ���ʼ���Ŀ������Ļ�, ����Ҫ����������ip

------------------------
postfix	- ά��			|
------------------------
	du -sh /var/spool/postfix/*							*/
		* �鿴Ŀ¼�µ�defer��deferred��Ŀ¼��С
		* ͨ�� postqueue �Cp ���鿴���е��ʼ�, ͨ�� postsuper -d ALL ɾ�����еĶ����ʼ�
	

------------------------
����ˢpostfix�� IP�ű�	|
------------------------
#!/bin/bash

LOGFILE="/var/log/maillog"

#ͳ��maillog��authentication failure��IP������IP
grep "authentication failure" $LOGFILE|awk '{print $7}'|grep -E -o "[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+"|sort|uniq -c > af_iplist.txt

#ȡ��AF���ִ���300��ʱ��IP
awk '$1>300 {print $2}' af_iplist.txt > block_ip_list.txt

#����300��AF��IP��ӵ�iptables��
cat block_ip_list.txt|while read line
do
/sbin/iptables -nL | grep $line
if [ $? != 0 ]
then
    iptables -I INPUT -s $line -j DROP
fi
done