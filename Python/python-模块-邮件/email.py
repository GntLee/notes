-------------------------
email					 |
-------------------------
	# �ο�
		https://www.liaoxuefeng.com/wiki/0014316089557264a6b348958f449949df42a6d3a2e542c000/001432005226355aadb8d4b2f3f42f6b1d6f2c5bd8d5263000#0
		https://www.jianshu.com/p/abb2d6e91c1f
	# ���ĵ�ģ��
		email
		smtplib
		nntplib
		Message
		|- MIMEBase
		   |- MIMEMultipart
		   |- MIMENonMultipart
			  |- MIMEMessage
			  |- MIMEText
			  |- MIMEImage
	# ���²���

-------------------------
������ͨ�ʼ�			 |
-------------------------
from email.header import Header
from email.mime.text import MIMEText
from email.utils import parseaddr, formataddr

import smtplib

# �����ַ��Ϣ�������ģ���Ҫͨ��Header������б��롣
def format_address(address):
    name, addr = parseaddr(address)
    return formataddr((Header(name, 'utf-8').encode(), addr))


# ������Ϣ(��Ϣ����,����,����)
# ����HTML��Ϣ MIMEText('<h1>Hello</h1>', 'html', 'utf-8')
message = MIMEText('Hello', 'plain', 'utf-8')

# ������
message['From'] = format_address('Javaweb���������� <no-reply@javaweb.io>')

# �ռ���,����ж����ַ,ʹ�ö��ŷָ��ַ���
message['To'] = format_address('KevinBlandy <747692844@qq.com>')

# ����
message['Subject'] = Header('��ӭ�㰡', 'utf-8').encode()

# �½�STMPʵ��(��ַ,�˿�)
server = smtplib.SMTP_SSL('smtp.exmail.qq.com', 465)
# �������SSL������:smtplib.SMTP(host,port)

# ����DEBUG����
server.set_debuglevel(1)

# ʹ���˻��������¼
server.login('no-reply@javaweb.io', '123456789')

# �����ʼ�,ע�ⷢ�����˻�����͵�¼���˻���һ��
server.sendmail('no-reply@javaweb.io', '747692844@qq.com', message.as_string())

# �˳��ͻ���
server.quit()

-------------------------
��ͨ����ת��ΪSSL		 |
-------------------------
server = smtplib.SMTP(host,port)
server.starttls() # ���̵���starttls()����,�ʹ����˰�ȫ����

-------------------------
���ʹ��������ʼ�		 |
-------------------------
from email import encoders
from email.header import Header
from email.mime.text import MIMEText
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart
from email.utils import parseaddr, formataddr
import smtplib


def format_address(address):
    name, addr = parseaddr(address)
    return formataddr((Header(name, 'utf-8').encode(), addr))


# �����ʼ�����:
msg = MIMEMultipart()

msg['From'] = format_address('Javaweb���������� <no-reply@javaweb.io>')
msg['To'] = format_address('KevinBlandy <747692844@qq.com>')
msg['Subject'] = Header('����һ�����������ʼ�', 'utf-8').encode()

# �ʼ����һ��text/plain��Ϣ
msg.attach(MIMEText('send with file...', 'plain', 'utf-8'))

# ��Ӹ������Ǽ���һ�� MIMEBase���ӱ��ض�ȡһ��ͼƬ:
with open('D:\\20181009153347.jpg', 'rb') as file:
    filename = file.name

    # ���ø�����MIME���ļ�����������png����:
    mime = MIMEBase('image', 'png', filename=filename)

    # ���ϱ�Ҫ��ͷ��Ϣ:
    mime.add_header('Content-Disposition', 'attachment', filename=filename)
    mime.add_header('Content-ID', '<0>')
    mime.add_header('X-Attachment-Id', '0')

    # �Ѹ��������ݶ�����:
    mime.set_payload(file.read())
    # ��Base64����:
    encoders.encode_base64(mime)

    # ��ӵ�MIMEMultipart:
    msg.attach(mime)


# �½�STMPʵ��(��ַ,�˿�)
server = smtplib.SMTP_SSL('smtp.exmail.qq.com', 465)

# ����DEBUG����
server.set_debuglevel(1)

# ʹ���˻��������¼
server.login('no-reply@javaweb.io', '123456')

# �����ʼ�,ע�ⷢ�����˻�����͵�¼���˻���һ��
server.sendmail('no-reply@javaweb.io', '747692844@qq.com', msg.as_string())

# �˳��ͻ���
server.quit()
