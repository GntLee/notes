------------------------
io.h					|
------------------------
	# ϵͳio���
	# ��Linux������,������λ����:/usr/include/sys/io.h

	int mkdir (const char *);
		* ����һ���ļ���
		* �����ɹ�����0,���򷵻�-1(Ŀ¼�Ѿ�����)

	char *getcwd (char *buf, int size);
		* ��ȡ��ǰִ�г������ڵ�Ŀ¼
		* �ѵ�ǰ·��д��buf,��󳤶�Ϊsize
			char buf[1024] = { 0 };
			getcwd(buf, 1024);
			printf("%s", buf);	//D:\workspace\clang-practice
