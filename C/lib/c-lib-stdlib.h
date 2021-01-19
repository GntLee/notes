--------------------------------
stdlib							|
--------------------------------

FILE

NULL
	* null 

EXIT_SUCCESS = 0
	* �ɹ�

EXIT_FAILURE = 1
	* ʧ��
	
exit()
	* �����˳�,����Ϊ int ֵ,��ʾ������˳�״̬

system()
	* ִ��ϵͳ����
	* ����ֵ int,��ֵ���ǳ���ķ���ֵ,�ڲ�ͬ��ƽ̨��һ��

char *getenv (const char *);
	* ��ȡϵͳ��������,��������ڷ���NULL
		char *p = getenv("JAVA_HOME");
		printf("%s",p);	//C:\Program Files\Java\jdk1.8.0_171

srand(unsigned int)
	* ���������������

rand()
	* �ú�������һ������� int
	* �������һ��,��ô��ε��õĽ��Ҳһ��

atoi(const char *nptr);
	* ���ַ���ת��Ϊ����,Ȼ�󷵻�
	* ������ǰ��Ŀո�,ֱ���������ֻ��������Ųſ�ʼת��,�����������ַ�������\0�ͽ���ת��,���ҷ���ת�����

stof(const char *nptr);
	* ͬ��
	* ���ַ���ת��Ϊfloat
	
atol(const char *nptr);
	* ͬ��
	* ���ַ���ת��Ϊlong

void *malloc (size_t)
	* ������ʾ����Ŀռ��Ƕ���
	* �������ɹ�,���ص����ݾ�������Ķѿռ����Ԫ�ص�ַ(ָ��),����ʧ��,���� NULL
	* ����Ķѿռ�,�������û�н���,��ô�����ͷ�,��Ҫ�����ֶ����ͷ�
	* demo
		int *p = (int *) malloc(sizeof(int));
		*p = 15;
		printf("%d",p[0]);		//15

void free (void *);
	* �ͷŶѿռ���ڴ�,����������ϵͳ
	* ͬһ����Ķ��ڴ�,ֻ��ִ��һ���ͷŲ���
	* �ͷŵ��ڴ��,ִ�и��ڴ��ָ�����Ұָ����

fclose()
fopen()
freopen()
fflush()
atexit (void (*)(void));
	* �ú���������һ������ָ��
	* �÷������Զ�ε���,���ٿ��Է�32���ص�����
	* �ڳ����˳���ʱ��,����ø÷���(�����ӵ���ִ��)
	
	void f1(){
		printf("��һ��ִ��\n");
	}
	void f2(){
		printf("�ڶ���ִ��\n");
	}

	int main(int argc, char **argv) {
		atexit(&f1);
		atexit(&f2);

		/*
			�ڶ���ִ��
			��һ��ִ��
		*/
		return EXIT_SUCCESS;
	}

void qsort(void *arr, size_t length, size_t size, int (*)(const void *, const void *));
	* ���������ʵ��
	* arr �������������Ԫ��
	* lenngth ����ĳ���
	* size ����Ԫ�صĴ�С
	*  int (*)(const void *v1, const void *v2) ���򷽷���ָ��
		- ���v1 > v2 ��������
		- ���v1 < v2 ���ظ���
		- ���v1 = v2 ����0
	* ����
		int compareTo(const void *v1, const void *v2) {
			//�Ӵ�С����
			return *((int *) v1) < *((int *) v2);
		}

		int main(int argc, char **argv) {

			int arr[] = { 1, 5, 4, 7, 8, 2, 3, 9, 6 };

			size_t arrLength = sizeof(arr) / sizeof(arr[0]);
			size_t itemSize = sizeof(arr[0]);

			qsort(arr, arrLength, itemSize, &compareTo);

			for (int x = 0; x < arrLength; x++) {
				printf("%d\n", arr[x]);
			}

			return EXIT_SUCCESS;
		}

void *bsearch(const void *key, const void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *))
	* ���ֲ��ҵ�ʵ��,����ҵ�Ԫ��,�򷵻ظ�Ԫ�ص�ָ��,���򷵻ؿ�ָ��
	* key ����Ԫ�ص�ָ��
	* base ����
	* nitems ����Ԫ�ظ���
	* size ����Ԫ�ص��ֽڴ�С
	* compar ָ�뺯��,���ڶԱ�Ԫ��
		int comparable(const void *arg1, const void *arg2) {
			int v1 = *(int *) arg1;
			int v2 = *(int *) arg2;
			if(v1 > v2){
				return 1;
			}else if(v1 < v2){
				return -1;
			}else{
				return 0;
			}
		}
	* demo
		int comparable(const void *arg1, const void *arg2) {
			int v1 = *(int *) arg1;
			int v2 = *(int *) arg2;
			if(v1 > v2){
				return 1;
			}else if(v1 < v2){
				return -1;
			}else{
				return 0;
			}
		}

		int main(int argc, char **argv) {
			int arr[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			size_t itemSize = sizeof(arr[0]);
			size_t arrLeng = sizeof(arr) / itemSize;
			int number = 6;
			int * index = (int *) bsearch(&number, arr, arrLeng, itemSize, &comparable);
			printf("index = %p\n", index);		//index = 0028FF24
			return EXIT_SUCCESS;
		}

	* �ṹ���ָ������
		int compareTo(const void *v1, const void *v2) {
			//��ָ��ʵ������һ������ָ��,��Ϊָ�����������Ԫ��ÿ������ָ��
			struct User **user1 = (struct User **) v1;
			struct User **user2 = (struct User **) v2;
			int height1 = (*user1)->height;
			int height2 = (*user2)->height;
			return height1 > height2;
		}