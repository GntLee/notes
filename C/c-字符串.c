------------------------
�ַ�������				|
------------------------
	# C�Z����û���ַ���,ʹ�õ����ַ�����
	# �ַ������ڴ���һ��������char�ռ�,��'\0'(����0)��β
	# �ַ���һ�����ַ�����,�ַ����鲻һ�����ַ���
		//�ַ�����
		char name[5] = {'K','e','v','i','n'};
		//�ַ���
		char name[10] = {'K','e','v','i','n','\0'};
		//�ַ���
		char name[10] = {'K','e','v','i','n',0};
		//�ַ���
		char a1[10] = {'a','b','c'};(��Ϊ����ĽǱ�Ĭ��ֵΪ0,0���ǽ�����)

		* �ַ�������'\0'��β,��ô����ַ���������ַ���
	
	# '%s'���������������
		    char a1[] = {'a','b','c','\0','d','e','f'};
			printf("%s",a1);        //abc
	
	# ��õĳ�ʼ��
		char string[] = "��ð�";
		
		* �ַ����Ľ�β,���������Զ���ӽ�����

			char string[] = "���";
			printf("%s size=%d",string,sizeof(string));
			//��� size=7  
			//UTF8����һ������ռ3���ֽ�,�����һ�����ص�0,һ���ֽڱ�ʾ������
		
		* �������Լ�������ӽ�����,���������ǻ�������һ��
			const char names[] = "Hello\0";
			printf("string = %s,size = %d",names,sizeof(names));
			//string = Hello,size = 7
		
		* ���� \0 �����
			char string[] = "Hello\0c";
			printf("%s size=%d",string,sizeof(string));
			//Hello size=8
			//����\0����,����ֻ��ӡ��Hello,����Զ������\0������,����Ҳ��ռһ���ֽ�
		
		* \0��������,���ܸպ����һ��ת���ַ�
			char string[] = "\0123Hello";
			printf("%s size=%d",string,sizeof(string));
			//
			//3Hello size=8
			// '\012' ��ת�����һ�����з�(012��һ���˽���,��ascii�б�ʾ����)
		
		* ����޶��ַ�����,һ��Ҫ����������һ��λ��
			char string[6] = "Hello";
			printf("%s size=%d",string,sizeof(string));
			//Hello size=5
			//���[]С��6,��������,��Ϊû������������λ��
	
	# ��ȡ�ַ����ĳ���(�ֽڴ�С)
		* ʹ�� strlen(const char *s) ����
		* ���㲢�����ַ���s�ĳ���,������������ '\0',size_t ����
		* ���������һ���ļ��ָ�����ǰ���ַ�������
	
	# �ַ�����copy
		* ʹ�� strcpy(char *dst,char *src) ����
		* ���src�����ݳ��ȴ���dst��,��ô������������
		* ֻ�´����һ��'\0'ǰ������
			char name[11] = "KevinBlandy";
			char cp_name[11];

			strcpy(cp_name,name);
			printf("%s\n",cp_name);		//KevinBlandy
		
		* strncpy(char *dst,char *src,int size) 
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
	
	# �ַ����ıȽ�
		* �ַ����ıȽ�,strcmp(s1,s2)
		* ��� s1 �� s2 ����ͬ��,�򷵻� 0
		* ��� s1<s2 �򷵻�С�� 0,��� s1 > s2 �򷵻ش��� 0
			char s1[11] = "Hello";
			char s2[11] = "Hi";
			char s3[11] = "Hello";
			printf("%d %d %d\n",strcmp(s1,s3),strcmp(s1,s2),strcmp(s2,s1));	//0 -1 1
		* ��ʵ�ǱȽ��ַ���ascii��,˭�����˭��
		
		* ����ָ���Ƚϵ��ַ�������ʹ�� strncmp(s1,s2,length);
		* �����Ƚ� s1,s2��ǰlength���ַ�
			char src[] = "HelloC";
			char dst[] = "HelloJava";
			int result = strncmp(src,dst,5);
			printf("result = %d\n",result);	//result = 0
			
	
	# �ַ�����׷��
		* strcat(s1, s2)�����ַ��� s2 ���ַ��� s1 ��ĩβ
			char s1[11] = "Hello";
			char s2[11] = " C";
			strcat(s1,s2);
			printf("%s\n",s1);	//HelloC
		
		* ����׷��ָ���ĳ��ȿ���ʹ�� strncat(s1,s2,length);
		* ��s2��ǰlength���ַ�׷�ӵ�s1
			char src[] = "Hello";
			char dst[] = "clang";
			strncat(src,dst,1);
			printf("result = %s\n",src);		//result = Helloc

	
	# ��ȡ��ʽ������ַ���(���л�)
		* sprintf(char dst*, const char *, ...)
		*  �� �ַ�����ʽ����,д�뵽dst��
			int x = 10;
			char y = 'H';
			char str[] = "Java";
			char dst[100];
			sprintf(dst,"x = %d,y = %c,buf = %s",x,y,str);
			printf("dst = %s\n",dst);	//dst = x = 10,y = H,buf = Java
	
	# Ԥ����Ӽ��̵�����ĸ�ʽ���ַ���(�����л�)
		* sscanf (const char *, const char *, ...)
		* �Ѵ���Ļ��ȡ�����ַ�����ʽ����,д�뵽dst��
			//����һ��"������ַ���"
			char dst[] = "1 2 3";
			//�������
			int a,b,c;
			//ʹ�� cccanf �� ������ַ�,��ֵ������
			sscanf(dst,"%d %d %d",&a,&b,&c);
			printf("a=%d,b=%d,c=%d\n",a,b,c);	//a=1,b=2,c=3

		* ���ַ�������ȡ���α���������
			char inputs[] = "a=10,b=20";
			int a , b;
			sscanf(inputs,"a=%d,b=%d",&a,&b);
			printf("a=%d,b=%d\n",a,b);  //a=10,b=20

		* ��ȡ�ַ���,Ĭ���Կո�ָ�
			char temp[] = "abc def 123";
			char str1[4],str2[4],str3[4];
			sscanf(temp,"%s %s %s",str1,str2,str3);
			printf("str1=%s,str2=%s,str3=%s",str1,str2,str3);//str1=abc,str2=def,str3=123
	
	# �ַ����Ĳ�ѯ
		* �����ַ���һ�γ��ֵ�λ��(�ڴ��ַ) strchr(temp,'x');
		* �����ѯʧ��,���� NULL

		* �����ַ�����һ�γ��ֵ�λ��(�ڴ��ַ) strstr(temp,"def");
		* �����ѯʧ��,���� NULL
		
		    char temp[] = "abcdefg";

			char *result = strchr(temp,'a');
			printf("result = %p,char = %c \n",result,*result);  //result = 0061FF24,char = a

			result = strstr(temp,"def");
			printf("result = %p,char = %c \n",result,*result);  //result = 0061FF27,char = d
		
	
	# �ַ����и�
		* strtok(char *str,const char *delmi);
		* �� delmi ���и��ַ���str
		* ����ֵΪ�и����ַ���
		* ƥ���и��ַ����ĵط�,���ɽ�����
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
		
		# ���ַ���ת��Ϊ��������
			* ת��Ϊ����: atoi(const char *nptr);
			* ������ǰ��Ŀո�,ֱ���������ֻ��������Ųſ�ʼת��,�����������ַ�������\0�ͽ���ת��,���ҷ���ת�����
			
			* ת��Ϊ������: stof();
			* ת��Ϊlong: atol();


		# �ַ�������
			* ÿ���ַ�������һ���׵�ַ�������ַ��ָ�ַ�����Ԫ�ص�ַ
				printf("%s\n","Hello" + 1);		//ello		+1���׵�ַ���ƫ��1
				printf("%c\n",*("Hello"));		//H			ȡ�����׵�ַ���ַ�

				char *p = "123456789";				//�ַ�������ֱ�Ӹ�ֵ��ָ�룬��Ϊ�ַ���������Ǹ�ָ��

			* �ַ������������ڴ��data�������ֳ�����
				void func(char *p){
					printf("%p",p);
				}
				int main() {
					printf("%p\n","Hello");
					func("Hello");
					/*
						00405067
						00405067
					*/
					return EXIT_SUCCESS;
				}
			
			* �ַ������������ֳ��������ַ�����ֻ���������޸�
				char *p = "123456";
				*p = 'z';
				printf("%s\n",p);	//error

				char *p = "123456";
				strcpy(p, "abc");	//error
			
			* ���ֳ��������������ڸ��������������һ��������������ͷ��ڴ�
		
		# �ַ���������ʼ��������
			//p ָ�룬�����˳��� "KevinBlandy" �ĵ�ַ
			//���������޸�
			char *p = "KevinBlandy";
			*p = "123";		//error

			//��"KevinBlandy"������buf�����У��ڴ�λ�÷ǳ����أ��������ǿ����޸ĵ�
			char buf[] = "KevinBlandy";
			buf[0] = 'z';	//ok

------------------------
�ַ�������/���			|
------------------------
	# scanf �� printf
		char ch;
		scanf("%c",&ch);
		printf("%c",ch);

		char name[100];
		char buf[100] = {0};
		scanf("%s",buf);
		printf("�����������:%s\n",buf);

		* Ĭ���Կո�Ϊ�ָ�,������ȡ�ո��ǰ�������,������ַ��ᱻ��ӵ�������
		* 'scanf() ���ַ�ʽ���벻����Խ����,����ȫ'
		* scanf ���Զ���������ַ���� '\0'
	
	# getchar() / putchar()
		* ר�����ڴ������ַ��ĺ���
		* һ���ԵĶ�ȡ/��������ַ�,Ч�ʱȽϸ�,��Ϊר�Ŵ���ľ����ַ�
			char ch;
			scanf("%c",&ch)

			char ch = '1';
			printf("%d",ch)

	
	# ʹ�� gets / fgets ��ȡ�ַ���
		gets(char *s)
			* �Ѿ�������,�ӱ�׼�����ȡ�ַ�,�����浽sָ�����ڴ�ռ�ֱ�����ֻ��з������ļ���βΪֹ
			* ���ܻᷢ������Խ������,��Ϊ��֪���û���������ݴ�С,������ʹ��
		
		fgets(char *s,int size,FILE stream)
			* ����
				s: �ַ���
				size:ָ����ȡ����ַ����ĳ���(Ĭ�� -1,������)
				stram:�ļ�ָ��,'�����ȡ����������ַ���,�̶�Ϊstdin'
			* ��streamָ�����ļ��ڶ����ַ�,���浽sָ�����ڴ�ռ�,ֱ�����ֻ����ַ�,��ȡ���ļ���β,�����Ѿ���ȡ��size - 1 ���ַ�Ϊֹ
			* ���Զ��������� '\0' ��ʶ,��ѻ��з�Ҳһ���ȡ��ȥ
			* s���ֻ��װ length - 1���ַ�,��Ϊ����Ҫ��һ�����ַ��������� '\0',����������ݴ����� size ���� sizeof(s) ��ô�������ֻᱻ�ض�
			* ��ȡ�ɹ����ض�ȡ�����ַ���,��ȡ���ļ�ĩβ���߳���,���� NULL
			* ��ȡ��������demo
				char buf[100];
				fgets(buf,sizeof(buf),stdin);
				printf("���������:%s\n",buf);
		
		fgetc( FILE * fp );
			* fgetc() ������ fp ��ָ��������ļ��ж�ȡһ���ַ�,����ֵ�Ƕ�ȡ���ַ�
			* ������������򷵻� EOF,(-1)

	# ʹ�� puts / fputs ������ַ���
		puts(const char *s)
			* ��׼�豸���s�ַ���,���Զ���ӻ��з� \n
			* �ɹ����طǸ���,ʧ�ܷ��� -1

		fputs(const char *str,FILE *stream)
			* �� str �ַ�д�뵽streamָ�����ļ���,�ַ��������� '\0' ��д���ļ�
			* �ɹ����� 0,ʧ�ܷ��� -1
			* ���԰�stream�滻Ϊ stdout,ʹstr���������Ļ
		
		fputc( int c, FILE *stream );
			* ���ڰѵ����ַ�д��streamָ�����ļ���
			* ��д��ɹ�,���᷵��д����ַ�,�����������,��᷵�� EOF
	
	# ��ӡ��
		fprintf(FILE *fp,const char *format, ...)
			* ���԰Ѹ�ʽ��������,�����ָ������
			* printf("Hello %s","Java") == fprintf(stdout,"Hello %s","Java")
	
	
	# ���ļ���ȡһ���ַ�
		int fscanf(FILE *fp, const char *format, ...) 
		* ���������ļ��ж�ȡ�ַ���,������������һ���ո�/�����ַ�ʱ,����ֹͣ��ȡ
		* ���Դ�ָ��������ȡ����,���ģ��,���л���ָ���Ļ�����
			FILE *file = fopen("E:\\c-lang.txt","r");
			char buf[1024];
			fscanf(file,"%s",buf);	//��ȡ����һ��,����buf
		
	

----------------------------
���õ��ַ�������			|
----------------------------
	# ȥ���ַ������ߵĿո�
		void trim(char *p,char *dst){
			char *start = p;
			char *end = p + (strlen(p) - 1);
			while((*start == ' ' || *start == '	' || *start == '\n') && start != '\0'){
				start++;
			}
			while((*end == ' ' || *end == '	' || *end == '\n') && start != end){
				end --;
			}
			*(end + 1) = '\0';
			strcpy(dst, start);
			//strcpy(p,start); ������ԭ�ַ������޸�,����ԭ�ַ����������ַ�������
		}

		void trim(char *str){
			char *start = str;
			char *end = str + (strlen(str) - 1);
			while((*start == ' ' || *start == '\n' || *start == '	') && start < end){
				start ++;
			}
			while((*end == ' ' || *end == '\n' || *end == '	') && end > start){
				end --;
			}
			*(end + 1) = '\0';
			strcpy(str, start);
		}
	
	# ��ת�ַ���
		void reversal(char *p) {
			int start = 0;
			int end = strlen(p) - 1;
			while (start < end) {
				p[start] = p[start] ^ p[end];
				p[end] = p[start] ^ p[end];
				p[start] = p[start] ^ p[end];

				start++;
				end--;
			}
		}
	# �����ַ����У��Ӵ����ֵĴ���
		int count(const char *p,const char *s){
			char *temp = strstr(p,s);
			int count = 0;
			while(temp != NULL){
				p = temp + strlen(s);
				temp = strstr(p,s);
				count ++;
			}
			return count;
		}

		int count(char *p, char *sub) {
			int count = 0;
			char *index = strstr(p, sub);
			int position = strlen(sub);
			while (index != NULL) {
				index = strstr(index + position, sub);
				count++;
			}
			return count;
		}