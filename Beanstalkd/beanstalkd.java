---------------------
beanstalkd
---------------------
	# C�����ĸ�������Ϣ���п�ܣ���������ʵ����ʱ����

	# ��ַ
		* Github
			https://github.com/beanstalkd/beanstalkd
		
		* �����ĵ�
			https://github.com/beanstalkd/beanstalkd/blob/master/doc/protocol.zh-CN.md

		* Go�ͻ���
			https://github.com/beanstalkd/go-beanstalk
	


	# ���ĸ���
		* Job
			�������Ϊ��Ϣ

		* tube 
			����Topic��һ�����⣬���ڴ��N��Job
	
	# Job��������
		put			��һ��������ý� tube ��
		deayed		������������ٵȴ��У���Ҫ���������׼����ϡ��ӳٶ��С�
		ready		��������Ѿ�׼�����ˣ����������ˡ����е����Ѷ���Ҫ��ȡ ready ״̬�� job
		reserved	��������Ѿ�������������
		release		��� job ִ��ʧ���ˣ������Ž� ready ״̬�����С�����������ִ��
		bury		��� job ִ��ʧ���ˣ�����ϣ����������ִ�У��Ȱ���������
	

---------------------
beanstalkd ��װ
---------------------
	# Centos��װ
		yum -y install beanstalkd --enablerepo=epel
	
	# ά��
		* ����
			beanstalkd -l 0.0.0.0 -p 11300 -b /home/software/binstalkd/binlogs
				-l ָ��IP
				-p ָ���˿�
				-b ָ�����л��ļ��洢·��
		

		