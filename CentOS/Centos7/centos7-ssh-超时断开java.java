
-------------------------------------
centos7��ʱ�䲻�����������ӳ�ʱ�ж�  |
-------------------------------------

1.�༭sshd�����ļ�

    # vi /etc/ssh/sshd_config

    �ҵ�

    #ClientAliveInterval 0
    #ClientAliveCountMax 3

    �޸�Ϊ

    ClientAliveInterval 60
    ClientAliveCountMax 3

2.����sshd����

    # systemctl restart sshd
