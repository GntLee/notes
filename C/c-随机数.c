--------------------------
�����					  |
--------------------------
		# ��ȡ�����
		#include<stdio.h>
		#include<stdlib.h>
		#include<time.h>

		int main(void) {
			//��������,ʹ�õ�ǰʱ��,��֤ÿ�ε��û�ȡ�����������һ��
			srand((unsigned int)time(NULL));
			//���������
			for(int i = 0 ; i < 10 ; i++){
				int rand_number = rand();
				printf("��ȡ�����:%d\n",rand_number);
			}
			return EXIT_SUCCESS;
		}

