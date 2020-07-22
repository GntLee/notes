-----------------------------------------
docker-registry							 |
-----------------------------------------
	# ��װ
		docker run -d -p 5000:5000 --restart=always --name registry -v /usr/local/registry:/var/lib/registry registry
	
	# Ϊ���ؾ�����ӱ�ǩ
		 docker tag [image] 127.0.0.1:5000/[image]:[tag]
		
	# ���͵����زֿ�
		docker push 127.0.0.1:5000/[image]:[tag]
	
	# �鿴�ֿ��еľ���
		curl 127.0.0.1:5000/v2/_catalog
		{"repositories":["ubuntu"]}
	
	# ɾ������
		docker image rm 127.0.0.1:5000/[image]:[tag]
	
	# �ñ����ε���������Ҳ�ܰѾ������͵�˽�вֿ�
		* ֱ�����ͻ�ʧ��,��ΪDocker Ĭ�ϲ������ HTTPS ��ʽ���;���
		* ����ͨ�� Docker ������ѡ����ȡ���������
		* �޸�������Ӿ�������ip��ַ:/etc/docker/daemon.json
			{
			  "insecure-registries": ["192.168.199.100:5000" ]
			}

-----------------------------------------
������									 |
-----------------------------------------
	1. ��¼������Docker Registry
		$ sudo docker login --username=747692844@qq.com registry.cn-hangzhou.aliyuncs.com
		���ڵ�¼���û���Ϊ�������˺�ȫ��������Ϊ��ͨ����ʱ���õ����롣

		�������ڲ�Ʒ����̨��ҳ�޸ĵ�¼���롣

	2. ��Registry����ȡ����
		$ sudo docker pull registry.cn-hangzhou.aliyuncs.com/kevinblandy/test:[����汾��]

	3. ���������͵�Registry
		$ sudo docker login --username=747692844@qq.com registry.cn-hangzhou.aliyuncs.com
		$ sudo docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/kevinblandy/test:[����汾��]
		$ sudo docker push registry.cn-hangzhou.aliyuncs.com/kevinblandy/test:[����汾��]
		�����ʵ�ʾ�����Ϣ�滻ʾ���е�[ImageId]��[����汾��]������

	4. ѡ����ʵľ���ֿ��ַ
		��ECS���;���ʱ������ѡ��ʹ�þ���ֿ�������ַ�������ٶȽ��õ��������ҽ�����������Ĺ���������

		�����ʹ�õĻ���λ�ھ������磬��ʹ�� registry-internal.cn-hangzhou.aliyuncs.com ��ΪRegistry��������¼������Ϊ���������ռ�ǰ׺��
		�����ʹ�õĻ���λ��VPC���磬��ʹ�� registry-vpc.cn-hangzhou.aliyuncs.com ��ΪRegistry��������¼������Ϊ���������ռ�ǰ׺��

	5. ʾ��
		ʹ��"docker tag"�������������񣬲�����ͨ��ר�������ַ������Registry��

		$ sudo docker images
		REPOSITORY                                                         TAG                 IMAGE ID            CREATED             VIRTUAL SIZE
		registry.aliyuncs.com/acs/agent                                    0.7-dfb6816         37bb9c63c8b2        7 days ago          37.89 MB
		$ sudo docker tag 37bb9c63c8b2 registry-vpc.cn-hangzhou.aliyuncs.com/acs/agent:0.7-dfb6816
		ʹ��"docker images"�����ҵ����񣬽��þ��������е��������ֱ��ΪRegistryר�������ַ��

		$ sudo docker push registry-vpc.cn-hangzhou.aliyuncs.com/acs/agent:0.7-dfb6816