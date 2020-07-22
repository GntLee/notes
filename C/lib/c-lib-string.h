--------------------------------
string							|
--------------------------------

strcpy(char *dst,char *src);
	* �� src �����ݿ����� dst
	* ���src�����ݳ��ȴ���dst��,��ô������������
	* ֻ�´����һ��'\0'ǰ������,���һ�� '\0' ������Ŀ������
		char name[11] = "KevinBlandy";
		char cp_name[11];

		strcpy(cp_name,name);
		printf("%s\n",cp_name);		//KevinBlandy

 strncpy(char *dst,char *src,int size) 
	* �� src �����ݿ����� dst,size������������copy�ĳ���
	* ����ֻ���� sizeof(dst) - 1 ��Ԫ�ص�Ŀ��,��ΪҪ��һ��λ�ø�'\0'
	* ��� size ������ sizeof(dst) - 1,��ô�ᷢ�����
		char str[] = "123456";
		char dst[5];
		strncpy(dst,str,sizeof(dst) - 1);
		printf("%s",dst);       //123

	* ��� '\0' �ڿ����ķ�Χ֮��,��ô'\0'�Ժ������ȫ���ᱻ����
		char str[] = "Hello\0Java";
		char dst[100];

		strncpy(dst,str,sizeof(str));

		printf("dst = %s\n",dst);                       //Hello
		printf("dst = %s\n",dst + strlen("Hello") + 1); //

strcat(s1, s2)
	* �����ַ��� s2 ���ַ��� s1 ��ĩβ
		char s1[11] = "Hello";
		char s2[11] = " C";
		strcat(s1,s2);
		printf("%s\n",s1);	//HelloC

strncat(s1,s2,length)
	* ͬ��,��������׷���ַ����ĳ���
	* ��s2��ǰlength���ַ�׷�ӵ�s1
		char src[] = "Hello";
		char dst[] = "clang";
		strncat(src,dst,1);
		printf("result = %s\n",src);		//result = Helloc

strlen(s1)
	* �����ַ����ĳ���(�ֽڴ�С),������������,�������㵽��һ���ָ��
	* ���� size_t ����
		char s1[11] = "Hello";
		char s2[11] = "He\0llo";
		printf("%d %d\n",strlen(s1),strlen(s2));		//5 2

strcmp(s1,s2)
	* �ַ����ıȽ�
	* ��� s1 �� s2 ����ͬ��,�򷵻� 0
	* ��� s1<s2 �򷵻�С�� 0,��� s1 > s2 �򷵻ش��� 0
		char s1[11] = "Hello";
		char s2[11] = "Hi";
		char s3[11] = "Hello";
		printf("%d %d %d\n",strcmp(s1,s3),strcmp(s1,s2),strcmp(s2,s1));	//0 -1 1
	
strncmp(s1,s2,length)
	* ͬ��,Ҳ���ַ����ıȽ�,�÷�������ָ���Ƚϵ��ַ�������
		char src[] = "HelloC";
		char dst[] = "HelloJava";
		int result = strncmp(src,dst,5);
		printf("result = %d\n",result);	//result = 0

strchr(s1, ch);
	* ����һ��ָ��,ָ���ַ��� s1 ���ַ� ch �ĵ�һ�γ��ֵ�λ��
	* ���û�ҵ�����0
		char s1[11] = "Hello";
		printf("%p\n",strchr(s1,'H'));		//28FF35

strstr(s1, s2);
	* ����һ��ָ��,ָ���ַ��� s1 ���ַ��� s2 �ĵ�һ�γ��ֵ�λ��
	* ���û�ҵ�����0
		char s1[11] = "Hello";
		char s2[11] = "ll";
		printf("%p",strstr(s1,s2));	//0028FF37


strtok(char *str,const char *delmi);
	* �� delmi ���и��ַ���str
	* ����ֵΪ�и����ַ���
	* ƥ���и��ַ����ĵط�,���ɽ�����
	* ���и���ַ�������Ϊ�и���������޸�(������\0)
	* ����ͨ����ε��ø÷�������ȡ���ַ��������б��и���ֶ�
	* ���˵�һ�ε���,���µ�N�ε��õ�һ��������������NULL,�����һ��ƥ�䲻��������� NULL
		char temp[] = "ab-cd-ef";
		//��һ��ƥ��
		char *p = strtok(temp,"-");
		printf("p = %s\n",p);           //ab

		//�ڶ���ƥ��
		p = strtok(NULL,"-");
		printf("p = %s\n",p);           //cd

		//������ƥ��
		p = strtok(NULL,"-");
		printf("p = %s\n",p);           //ef

		//���Ĵ�ƥ��
		p = strtok(NULL,"-");
		printf("p = %s\n",p);           //NULL ���Ĵ�ƥ��,û���˷���NULL

		printf("ԭ�ַ���:%s\n",temp);   //���и���ַ�������Ϊ�и���������޸�(������\0)
	* ��ȡ�����еķָ��
		char temp[] = "ab-cd-ef-gh-ij";
		char delmi[] = "-";
		char *p = strtok(temp,delmi);

		while(p != NULL){
			printf("find:%s \n",p);
			p = strtok(NULL,delmi);
		}
		/*
			find:ab
			find:cd
			find:ef
			find:gh
			find:ij
		*/

void *memset (void *p, int v, size_t s)
	* ��p��ָ���ĳһ���ڴ��е�'ÿ���ֽ�'������ȫ������Ϊvָ����ASCIIֵ,��Ĵ�С�ɵ���������sָ��
	* ����
		- p �������ڴ��׵�ַ
		- c �������ݿ�����������,��ʵ�ǵ��� ascii ��ֵ,unsigned int,������ 0 - 255
			* ��ʵ����ֵֻ����0 ��������,����˵p��һ������
		- n �������ݴ�С(��p��ʼ�����ٸ��ֽ�)
	* ���� p ���׵�ַ��ַ
	* demo
		int a;
		memset(&a,0,sizeof(a));		//��ʵ���ĸ��ֽ�ÿ���ֽڶ�д���� 97
		printf("%d\n",a);	//0

		memset(&a,97,sizeof(a));	//��ʵ���ĸ��ֽ�ÿ���ֽڶ�д���� 97
		printf("%c\n",a);			//a(%c������ȡһ���ֽ�)

		int arr[10];
		memset(arr,97,sizeof(arr));	//40���ֽ�,ÿ���ֽڶ�д����0
		printf("%c\n",arr[0]);	//a
	
	* ��������������������
		int arr[] = {1,2,3,4,5};
		memset(arr,0,sizeof(arr) * 5);
	

void *memcpy (void *dst, const void *src, size_t size);
	* ��src�е�size���ֽ�����copy��dst��
	* ʹ�øú���,��ò�Ҫ�����ڴ��ص�(����Դ��Ŀ�Ķ���һ��)
	* demo
		int src[] = {1,2,3};
		int dst[3];
		//��src�����ݿ�����dst�У�����dst��С���ֽ�
		memcpy(dst,src,sizeof(dst));
		for(int x = 0 ;x < 3 ;x++){
			printf("%d\n",dst[x]);	//1 2 3
		}

void *memmove (void *dst, const void *src, size_t szie);
	* ͬ��,��src����szie�ֽڵ�dest,����ʹ�ó�����'�ڴ��ص�cpy'��ʱ��
	* ���ܹ���֤'src�ڱ�����֮ǰ���ص�������ֽڿ�����Ŀ��������',��������ɺ�src���ݻᱻ����
	* ��Ŀ��������Դ����û���ص����memcpy����������ͬ

int memcmp (const void src*, const void dst*, size_t size)
	* �����Ƚ����ڴ����Ƿ����
	* �Ƚ�src��dst�ڴ�鿪ʼ��size���ֽ������Ƿ���ͬ
	* �����ͬ���� 0,��� dst > src ���� 1,��� dst С�� src ���� -1


char *strerror (int)
	* ����int(�쳣��)��������������