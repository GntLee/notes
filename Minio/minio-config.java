---------------------
����
---------------------
	# Ĭ�ϵ�����Ŀ¼��:${HOME}/.minio
		* ����������, ����ʹ�� --config-dir ������ѡ����д
		* docker ͨ��: -v /mnt/config:/root/.minio ָ����������Ŀ¼ӳ��
	


---------------------
����
---------------------

---------------------
֤�������
---------------------
	# ֤�������Ŀ¼
		${HOME}/.minio
		������ certs
		��   ������ CAs
		��   ������ private.key
		��   ������ public.crt
		������ config.json
	
		
		* ������Ŀ¼����һ�� certs Ŀ¼, ���ڴ��֤��
		* private.key �洢˽Կ, public.crt �洢��Կ, ���Ʋ����޸�
			[root@localhost]# cp ./fullchain.pem /mnt/config/certs/public.crt
			[root@localhost]# cp ./privkey.pem /mnt/config/certs/private.key