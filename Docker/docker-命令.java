----------------------------
docker ����					|
----------------------------
	docker help
		* �鿴���е��������

	docker info
		* �鿴docker�Ļ���������Ϣ
	
----------------------------
����						|
----------------------------
	# ������Բ�����
		*  ʹ�� $(cmd)
			docker rmi $(docker images -q)
		
		*  ``Ҳ����
			docker rm `docker ps -aq`













docker build -t friendlyname .# ʹ�ô�Ŀ¼�� Dockerfile ��������
docker run -p 4000:80 friendlyname  # ���ж˿� 4000 �� 90 �ġ��Ѻ����ơ�ӳ��
docker run -d -p 4000:80 friendlyname         # ������ͬ�����ڷ���ģʽ��
docker ps                                 # �鿴�����������е��������б�
docker stop <hash>                     # ƽ�ȵ�ָֹͣ��������
docker ps -a           # �鿴�����������б���������δ���е�����
docker kill <hash>                   # ǿ�ƹر�ָ��������
docker rm <hash>              # �Ӵ˻�����ɾ��ָ��������
docker rm $(docker ps -a -q)           # �Ӵ˻�����ɾ����������
docker images -a                               # ��ʾ�˻����ϵ����о���
docker rmi <imagename>            # �Ӵ˻�����ɾ��ָ���ľ���
docker rmi $(docker images -q)             # �Ӵ˻�����ɾ�����о���
docker login             # ʹ������ Docker ƾ֤��¼�� CLI �Ự
docker tag <image> username/repository:tag  # ��� <image> ���ϴ��������
docker push username/repository:tag            # ���ѱ�ǵľ����ϴ��������
docker run username/repository:tag                   # ���о�����еľ���


docker stack ls              # �г��� Docker �����������������е�Ӧ��
docker stack deploy -c <composefile> <appname>  # ����ָ���� Compose �ļ�
docker stack services <appname>       # �г���Ӧ�ù����ķ���
docker stack ps <appname>   # �г���Ӧ�ù������������е�����
docker stack rm <appname>                             # ���Ӧ��

docker-machine create --driver virtualbox myvm1 # ���� VM��Mac��Win7��Linux��
docker-machine create -d hyperv --hyperv-virtual-switch "myswitch" myvm1 # Win10
docker-machine env myvm1                # �鿴�йؽڵ�Ļ�����Ϣ
docker-machine ssh myvm1 "docker node ls"         # �г� swarm �еĽڵ�
docker-machine ssh myvm1 "docker node inspect <node ID>"        # ���ڵ�
docker-machine ssh myvm1 "docker swarm join-token -q worker"   # �鿴��������
docker-machine ssh myvm1   # ���� VM �� SSH �Ự�����롰exit���Խ����Ự
docker-machine ssh myvm2 "docker swarm leave"  # ʹ�����ڵ��˳� swarm
docker-machine ssh myvm1 "docker swarm leave -f" # ʹ���ڵ��˳�����ֹ swarm
docker-machine start myvm1            # ������ǰδ���е� VM
docker-machine stop $(docker-machine ls -q)               # ֹͣ�����������е� VM
docker-machine rm $(docker-machine ls -q) # ɾ������ VM ������̾���
docker-machine scp docker-compose.yml myvm1:~     # ���ļ����Ƶ��ڵ����Ŀ¼
docker-machine ssh myvm1 "docker stack deploy -c <file> <app>"   # ����Ӧ��
