------------------------------
network						  |
------------------------------
	# �ο��ĵ�
		https://docs.docker.com/config/containers/container-networking/
	
	# ͬһ�������е�����,��ά����һ�� dns�б�(/etc/hosts)

		ip [container-name]
		ip [container-name].[net-name]

------------------------------
network						  |
------------------------------
	# ����һ������
		docker network create [name]
			name
				* ��������
			-d 
				* ָ�����������,ö��ֵ
					bridge
					overlay(Swarm mode)
	
	# �鿴���д���������
		docker network ls
	
	# �鿴���������
		docker network inspect [name]
			name
				* �������ƻ���id
	
	# ����������ָ��������
		docker network connect [net] [container]
			container
				* ָ����container����
			net
				* ָ������
					
	# ��ָ���������жϿ���������
		docker network connect [net] [container] 
			container
				* ָ����container����
			net
				* ָ������
	
	# ɾ��
		docker network rm [name]
			name
				* �������ƻ���id