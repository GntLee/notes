
yum update -y

cat /etc/redhat-release
	* release��ֵ����7.3����

wget --no-check-certificate https://github.com/teddysun/across/raw/master/bbr.sh && chmod +x bbr.sh && ./bbr.sh
	* ��װ��ɺ�, ����y, ������������

egrep ^menuentry /etc/grub2.cfg | cut -f 2 -d \'
grub2-set-default 0
reboot
	* ��������, �ű��Ѿ�ִ����, û��Ҫ�ٴ�ִ��

uname -r
	* �鿴�ں��Ƿ��Ѹ���Ϊ4.9 +

echo "net.core.default_qdisc = fq" >> /etc/sysctl.conf
echo "net.ipv4.tcp_congestion_control = bbr" >> /etc/sysctl.conf
sysctl -p 
	* ��������, �ű��Ѿ�ִ����, û��Ҫ�ٴ�ִ��

sysctl net.ipv4.tcp_available_congestion_control
	* ��֤��ǰTCP�����㷨
	* ����ֵһ����(���߹�ϵ)
		net.ipv4.tcp_available_congestion_control = bbr cubic reno 
		net.ipv4.tcp_available_congestion_control = reno cubic bbr
	
sysctl net.ipv4.tcp_congestion_control
	* ��֤bbr�Ƿ�����
	* ����ֵһ��Ϊ
		net.ipv4.tcp_congestion_control = bbr
	
lsmod | grep bbr
	* ����ֵ�� tcp_bbr ģ�鼴˵�� bbr ������
		tcp_bbr                20480  9 
	




