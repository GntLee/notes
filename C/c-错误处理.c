------------------------
������				|
------------------------
	# C ���Բ��ṩ�Դ������ֱ��֧��,������Ϊһ��ϵͳ�������,���Է���ֵ����ʽ���������ʵײ�����
	# �ڷ�������ʱ,������� C �� UNIX �������÷��� 1 �� NULL,ͬʱ'������һ��������� errno,�ô��������ȫ�ֱ���,��ʾ�ں��������ڼ䷢���˴���'
	# ������ errno.h ͷ�ļ����ҵ����ָ����Ĵ������
	# ����,C ����Ա����ͨ����鷵��ֵ,Ȼ����ݷ���ֵ������ȡ�����ʵ��Ķ���
	# ������ԱӦ���ڳ����ʼ��ʱ,�� errno ����Ϊ 0,����һ�����õı��ϰ��,0 ֵ��ʾ������û�д���

		#include <stdio.h>
		#include <errno.h>
		#include <string.h>

		//����ȫ�ֱ���
		extern int errno;

		int main() {
			FILE * file;
			int errnum;
			file = fopen("unexist.txt", "rb");
			if (file == NULL) {

				errnum = errno;
				fprintf(stderr, "�����: %d\n", errno);
				perror("ͨ�� perror �������");
				fprintf(stderr, "���ļ�����: %s\n", strerror(errnum));
			} else {
				fclose(file);
			}
			return 0;
		}


	
	# �����쳣�����ȡ����������
		char *strerror (int)
		* <string.h>
		* ����int(�쳣��)��������������
	
	
	
	# ��ӡ������Ϣ
		void perror (const char *);
			* ��ע�Ĵ����ӡ��,���ڲ����ַ����������쳣/��������ʾ
				perror("hi");	//hi: No error

			* �����Դ�ӡ'�⺯��'����ʧ�ܵ�ԭ��
				fclose(stdout);
				printf("Hello");
				perror("hi");	//hi: Bad file descriptor(�����ļ�������,��Ϊ�Ѿ��ر���)
	
