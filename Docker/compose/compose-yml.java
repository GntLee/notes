
# �����ļ��ο�
	https://github.com/docker/docker.github.io/blob/master/compose/compose-file/index.md


# ����

version: "[�汾��]"
# ��ʾ���������ļ��İ汾��
services:
  [Ӧ������]:
    image:[image]
	container_name: [��������]
    build: [ָ��DockerfileĿ¼,���¹���һ������]
	ports:
	  - "[�������˿�]:[�����˿�]"
    volumes:
	  - [������Ŀ¼]:[����Ŀ¼]
    networks:
	  - [����]
    environment:
	    # ��������
		NAME: root
		PASS: ROOT
   depends_on:
      # ��Ӧ��,������Ӧ��
      - nginx
	  - mysql
    working_dir: /opt/app
    # ����Ŀ¼
	deploy:
	# ����ѡ��
	  replicas: [��Ⱥ����]
	  update_config:
	    parallelism: 2
		delay: 10s
		restart_policy:
		# ��������
		  condition: on-failure
		  # ��������,ö��ֵ