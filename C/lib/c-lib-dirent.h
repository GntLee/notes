------------------------
dirent					|
------------------------
	# ����Ŀ¼�Ŀ�

	DIR * opendir (const char *__dirname)
		* ��һ��Ŀ¼,����һ��Ŀ¼ָ�� typedef struct __dirstream_t DIR;
		* ���Ŀ¼������,���� NULL
	
	dirent*  readdir (DIR *__dir)
		* ��ȡĿ¼��Ϣ,������һ��Ŀ¼ָ��
		* ���� struct dirent
			struct dirent
			{
			  long            d_ino;				/* Always zero. */
			  unsigned short  d_reclen;				/* Always sizeof struct dirent. */
			  unsigned short  d_namlen;				/* �ļ�/�ļ������Ƶ��ַ����� */
			  unsigned        d_type;				/* File attributes */
			  char            d_name[FILENAME_MAX]; /* �ļ�/�ļ������� */
			};

		* ��Ӧ��ѭ������
		* ��Ϊÿ�ε���,�����𽥱�����Ŀ¼ָ���µ������ļ�/Ŀ¼��Ϣ,���������ĩβ,���� NULL
	
		* ����ĳ��Ŀ¼�µ������ļ�
			DIR *dir = opendir("E:\\letsencrypt");
			struct dirent *dirent = NULL;
			while ((dirent = readdir(dir)) != NULL) {
				printf("%s\n", dirent->d_name);
			}
	

