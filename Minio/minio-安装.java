--------------------
Mino - docker��װ
--------------------
docker run -p 9000:9000 --name minio1 \
-e "MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE" \
-e "MINIO_SECRET_KEY=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY" \
-v /mnt/data:/data \
-v /mnt/config:/root/.minio \
minio/minio server /data


--------------------
Mino - ������
--------------------
	# ����
		https://dl.min.io/server/minio/release/linux-amd64/minio
	
	# ִ��
		chmod +x minio
		./minio server /data
		
		* ����ʾ����, ��Ҫ�ֶ�ȥ�����ļ��޸��û���������
			Detected default credentials 'minioadmin:minioadmin', please change the credentials immediately using 'MINIO_ACCESS_KEY' and 'MINIO_SECRET_KEY'
		
		* Ĭ�ϵ�ƾ֤��Ϣ
			minioadmin:minioadmin
	


	
