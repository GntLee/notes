-------------------------
image					 |
-------------------------
	# ��ѯ����
		docker search [name]
			name
				* ��ѯָ���ľ���
			
			--filter=stars=10000
				* ���ڹ���, �����鿴�ղش���10000�ľ���
			
			 --filter=is-automated=true
				* ������ʾautomated build�ľ���
			

		����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
		NAME                                   DESCRIPTION                                     STARS               OFFICIAL            AUTOMATED
		mysql                                  MySQL is a widely used, open-source relation��   7649                [OK]                [ok]
		����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
		NAME ����
		DESCRIPTION ����
		STARS ��������(�ܵ���ӭ�ĳ̶�)
		OFFICIAL �Ƿ��ǹٷ�������
		AUTOMATED �Ƿ�DockerHub�Զ�������


	# �鿴���صľ���(�г�����)
		docker images [name]
			name
				* �����г����صľ���
			-a
				* ��ʾ���еľ���
				* �м��ľ���Ҳ����ʾ����

			--digest
				* ��ʾժҪ��Ϣ
			
			--no-trunc
				* ��ʾ������Ϣ
			
		--------------------------------------------------------------------------------------
		REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
		hello-world           latest              fce289e99eb9        6 days ago          1.84kB
		nginx                 latest              7042885a156a        9 days ago          109MB
		--------------------------------------------------------------------------------------
	
	# ������ȡ
		docker pull [name]:[tag]
			name
				* ��������
			tag
				* ��ǩ(�汾��)
				* ���ʡ��, Ĭ����ȡ latest �汾
			-a
				* ����ָ�����������tag(�汾)������
	
	# ɾ������
		docker rmi [name]:[tag]
			* �����ָ��tag, Ĭ��ɾ�� last �汾
			* Ҳ���԰�name����ָ����image id
			* ɾ�����еľ���
				docker rmi `docker images -q`
		
			-f
				* ǿ��ɾ��, �����ǻ����������е�����
	
	# ��������
		docker save [name]:[tag] > /[path].image
		
	# ���뾵��
		docker load < /[path].image
	
	

-------------------
��������			|
-------------------
	# ����
		�������ԭ�����о������ͱ�ǩ��,ԭ��Ϊ mongo:3.2,���Źٷ�����ά��,�������°汾��,���� docker pull mongo:3.2 ʱ,mongo:3.2 �����������ת�Ƶ��������صľ�������
		���ɵľ����ϵ����������ȡ��,�Ӷ���Ϊ�� <none>
		���� docker pull ���ܵ����������,docker build Ҳͬ�����Ե�����������,�����¾ɾ���ͬ��,�ɾ������Ʊ�ȡ��,�Ӷ����ֲֿ���,��ǩ��Ϊ <none> �ľ���
		�����ޱ�ǩ����Ҳ����Ϊ ��������(dangling image) ,���������������ר����ʾ���ྵ��

			docker image ls -f dangling=true

	# ɾ����������
		docker image prune