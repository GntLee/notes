
# �ڿͻ���������Կ��
	* ʹ��ssh��������
		ssh-keygen -t rsa
	
	* ʹ��Git�ͻ�������
		ssh-keygen -t rsa -C "747692844@qq.com"

# �޸ķ��������ļ�
	vim /etc/ssh/sshd_config

	PermitRootLogin no
		* �Ƿ����ROOT��¼(�Ǳ����)

	PubkeyAuthentication yes
		* �Ƿ�����֤���¼

	PasswordAuthentication yes
		* �Ƿ�����ʹ��������е�¼

	StrictModes no	
		* �Ƿ��� sshd ȥ����û���Ŀ¼����ص�����Ȩ�����ݣ�
		* ����Ϊ�˵���ʹ���߽�ĳЩ��Ҫ������Ȩ��������ܻᵼ��һЩ�������¡�
		* ����ʹ���ߵ� ~.ssh/ Ȩ�����ʱ��ĳЩ��������»᲻���û�����

	AuthorizedKeysFile .ssh/authorized_keys		
		* ָ����Կ�ļ�
		* ����ָ��Ϊ�û���homeĿ¼
			%h/.ssh/authorized_keys

# ��homeĿ¼����ļ�
	.ssh/authorized_keys

	* ���ļ�, �����ж����Կ, һ������һ���ͻ��˹�Կ


# ���� ssh ����
	systemctl restart sshd.service

	* ǧ��ǵ�Ҫ�Ȱѹ�Կ��ӵ��˷�����, ������

# ����Linux�ͻ���
	* ʹ��Linux�ϵ�ssh�ͻ��ˣ���¼Զ�̷�����

	vim /etc/ssh/ssh_config

	IdentityFile ~/.ssh/id_rsa
		* ָ��������˽Կ
		* �����ж��IdentityFile
