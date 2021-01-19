----------------------------
�ṹ��						|
----------------------------
	# ����������ɴ洢��ͬ����������ı���,�ṹ�� C �������һ���û��Զ���Ŀ��õ���������,������洢��ͬ���͵�������
	# �ṹ��ı���,�����ڳ�ʼ����ʱ��ֵ
		* �ṹ��ֻ��һ������,û�ж������ǰû�з���ռ�,û�ռ�Ͳ��ܸ�ֵ
	# �ṹ��������붨���ֵ�ĳ�ʼ��
		struct Book {
			int id;
			char name[50];
			char *author;
			double price;
		};

		struct Book java,python;

		java.id = 1;	
		strcpy(java.name,"Java���ŵ���ͨ");		//�ַ����������ʹ��copy
		java.author = "KevinBlandy";			//�ַ�ָ��,����ֱ�Ӹ�ֵ
		java.price = 15.6;

		printf("id=%d,name=%s,author=%s,price=%f",java.id,java.name,java.author,java.price);
		//id=1,name=Java���ŵ���ͨ,author=KevinBlandy,price=15.600000
	
	# ʹ�ô����Ž��г�ʼ��Ҳ�ǿ��Ե�
		struct User {
			int id;
			char *name;
			char hobby[20];
		};
		struct User user = {1,"KevinBlandy"};
		printf("id=%d,name=%s,hobby=%s",user.id,user.name,"Java & Python",user.hobby);	//id=1,name=KevinBlandy,hobby=Java & Python

		* ʹ�ô����Ž��г�ʼ����ʱ��,����ֱ�Ӷ��������͸�ֵ,�ײ�ͨ��copy���
	
	# c99��c11�µĳ�ʼ����ʽ
		struct Lang {
			int id;
			char name[10];
		};
		struct Lang java = {		//ͨ�� .������ = ֵ ����ʼ��
			.id = 1,
			.name = "Java"
		};
		printf("id=%d,name=%s", java.id, java.name);	//id=1,name=Java

	# �ṹ��Ϊ��������
		* ֵ����,���ַ�ʽ����ͨ�ı���ֵ������һ����
			struct Book {
				int id;
				char name[50];
				char *author;
				double price;
			};
			void func(struct Book book){
				book.id = 15;
				printf("id=%d,name=%s,author=%s,price=%f\n",book.id,book.name,book.author,book.price);
			}
			int main(){
				struct Book java;
				java.id = 1;
				strcpy(java.name,"Java���ŵ���ͨ");
				java.author = "KevinBlandy";
				java.price = 15.6;

				//�ѽṹ���ֵ���Ƹ��������β�ʹ�õ�,�������ַ�ʽ�ں������ڲ�û���޸Ľṹ�������
				func(java);	//id=1,name=Java���ŵ���ͨ,author=KevinBlandy,price=15.600000
				printf("%d",java.id);	//1

				//���ô���,���ַ�ʽ,�ں����ڲ������޸Ľṹ�������
				func(&java);
				return EXIT_SUCCESS;
			}

		* ָ�봫�ݵĺ���Ҳ������������
			void func(struct Book *p){
				*p->id = 15;
			}
		
		* ָ�봫�����ַ�ʽҲ������ const ָ��Ĺ���
		* const ����ָ���������,�ñ���ֻ��
		* const ����ָ��,��ָ��ָ����ڴ����ݲ����޸�
	
	# �ṹ���ǳ����
		* ����ṹ����ָ��,��������ַ�ʽ�ͻᷢ��ǳ����
			*to = *from;		
		
		* �ṹ�����ֱ��ͨ��������ֵ,���ǲ�Ҫʹ�����ַ�ʽ
		* Ҫ���ṹ���еĳ�Աһ��һ������
	
	# ָ��ṹ��ָ��
		struct Book {
			int id;
		};
		int main(){
			//��ʼ���ṹ��
			struct Book java;
			//��ʼ������ֵ
			java.id = 15;

			//����һ��ָ��ṹ���ָ��
			struct Book *p;
			//��ȡ�ṹ��ĵ�ַ
			p = &java;
			
			//ͨ�� -> ��������ͨ���ṹ��ָ����ʽṹ������
			printf("id=%d\n",p -> id);
	
			//Ҳ����ͨ�� -> ����������ֵ
			p -> id = 255;

			//Ҳ����ͨ��ȡ��ַ�������ṹ��ı���
			(*p).id = 15;
			printf("id=%d\n",(*p).id);		//15

			return EXIT_SUCCESS;
		}

		* �ṹ���ָ������Ԫ�ص�λ����ͬ
			struct User {
				int id;
				char *name;
				char hobby[20];
			};
			struct User user = {1,"KevinBlandy"};
			printf("%p %p",&user,&user.id);	//0061FF14 0061FF14
	
	# �ṹ���ڴ����ԭ��
		* ԭ��һ:�ṹ����Ԫ�ذ��ն���˳���ŵ��ڴ���,����'���ǽ�������'
		* �ӽṹ��洢���׵�ַ��ʼ ,ÿһ��Ԫ�ش����ڴ���ʱ,��������Ϊ�ڴ������Լ��Ŀ�������ֿռ��
		* ���'Ԫ�ش�ŵ�λ��һ�������Լ���С���������Ͽ�ʼ'
			

		* ԭ���:��ԭ��һ�Ļ�����,���������'�洢��Ԫ�Ƿ�Ϊ����Ԫ��������Ԫ�س��ȵ�������'
		* ����,�����:����,����'����'Ϊ����������

		* ����demo
			struct Data{
				char a;			//1 -> 1���ֽ�
				int b;			//8 -> ��Ϊ����Ԫ�ض���int,ǰ���ĸ��ֽ��Ѿ���������,���Դ�4�ֽں󿪱��ڴ�
				char c;			//9 -> 1���ֽ�
				double d;		//24 -> ��Ϊ����Ԫ�ض���double,ǰ��9���ֽڶ���������,���Դ�18�ֽں󿪱��ڴ�
				short f;		//26 -> 2�ֽ�
			} data;

			/*
				26 % 8 != 0
				(26 + 6) % 8 == 0;
				26 + 6 = 32
			*/
			printf("d1 = %d\n",sizeof(data));		//32
		
	
	# �ṹ������
		struct User {
			int id;
			char *name;
		};

		struct User users[5] = {
				{1,"KevinBlandy"},
				{2,"Litch"},
				{3,"Rooco"},
		};

		int size = sizeof(users) / sizeof(users[0]);

		printf("size=%d\n",size);		//5

		//ͨ��.����
		users[0].id = 1;
		users[0].name = "KevinBlandy";
		printf("id=%d,name=%s\n",users[0].id,users[0].name);	//id=1,name=KevinBlandy

		//ͨ��->����
		(users+1)->id = 2;
		(users+1)->name = "Litch";
		printf("id=%d,name=%s\n",(users+1)->id,(users+1)->name);	//id=2,name=Litch

		//Ҳ������������
		(*(users + 2)).id = 3;
		(*(users + 2)).name = "Rocco";
		printf("id=%d,name=%s\n",(*(users + 2)).id,(*(users + 2)).name);	//id=3,name=Rocco
	
	# �ṹ���Ƕ��
		* �ṹ������ĳ�Ա����һ���ṹ��
			struct Role {
				int id;
				char *name;
			};
			struct User {
				int id;
				char *name;
				struct Role role;
			};
			
			//ֱ�ӳ�ʼ���ṹ���Ա
			struct User user = {1,"Kevin",{1,"admin"}};
			printf("id=%d,role=%s",user.id,user.role.name);	//id=1,role=admin
			
			//�ȶ����ڳ�ʼ���ṹ���Ա
			struct User user = {1,"Kevin"};
			struct Role role = {1,"ADMIN"};
			user.role = role;
			printf("%s �Ľ�ɫ�� %s",user.name,user.role.name);//Kevin �Ľ�ɫ�� ADMIN

	
	# ָ��ָ��ѿռ�
		struct User {
			int id;
			char *name;
		};
		struct User *p = (struct User *)malloc(sizeof(struct User));
		p -> id = 1;
		p -> name = "KevinBlandy";
		printf("id=%d,name=%s",p[0].id,(*p).name);	//id=1,name=KevinBlandy
		free(p);		//ʹ�����֮��,�ǵ��ͷ�
	
	# ��Աָ��ָ������ռ�
		struct User {
			int id;
			char *name;
		};
		struct User user;
		user.name = (char *)malloc(strlen("KevinBlandy") + 1);
		strcpy(user.name, "KevinBlandy");
		printf("%s",user.name);
		free(user.name);

	# ����ṹ����ṹ���Ա��ָ���˶��ڴ�,��ô�ͷŵ�ʱ��Ҫ��С���,���ﵽ��
		struct User {
			char *name;
		};
		//������ڴ�,��Žṹ��
		struct User *user = (struct User *)malloc(sizeof(struct User));
		//������ڴ�,��Žṹ���е�name����ֵ
		user -> name = malloc(sizeof("KevinBlandy") + 1);
		strcpy(user -> name,"KevinBlandy");
		printf("name=%s",user[0].name);		//name=KevinBlandy
		//���ͷ����ԵĶ��ڴ�
		free(user -> name);
		//����ͷŽṹ��Ķ��ڴ�
		free(user);
		
		* ������ͷŵ��˽ṹ��,��ô user->name,���Ҳ�����ַ��,û�����ͷ�����ֵռ�õĶ��ڴ�,�����ڴ�й©
	
	# �ṹ��������
		struct Lang {
			int id;
			char name[10];
		};
		struct Lang lang;
		lang = (struct Lang ) { 1, "Java" };				//���������൱��һ�������Ľṹ��
		printf("id=%d,name=%s", lang.id, lang.name);		//id=1,name=Java
		return EXIT_SUCCESS;
	
	# �����������Ա
		* c99����,�����������,�ṹ������һ�������Ա�߱�һЩ����
		* ������Ե���ͼ������������һ���ṹ�����,��������һ��ָ��,ͨ�� malloc()�� �����㹻�Ŀռ�
		* �����������Ա�Ĺ���
			- �����������Ա�����ǽṹ������һ����Ա
			- �ṹ����������һ�������ĳ�Ա
			- ����������������ͨ����,ֻ�ǲ���������
		* �Ϸ�������
			struct Lang {
				int id;
				char name[];		//�����������Ա
			};

			struct Lang * lang;
			lang = (struct Lang *) calloc(1, sizeof(struct Lang) + 5 * sizeof(char));//Ϊ�������鿪��5�ֽ��ڴ�

			//�����ĸ��ַ�����������
			strncpy(lang->name, "Java", 4);

			printf("id=%d,name=%s", lang->id, lang->name);		//id=0,name=Java

----------------------------
�Ϸ��Ľṹ�嶨��������		|
----------------------------
	# ͬʱ����ṹ���Լ�����
		struct Book {
			int id;
			char name[50];
			char author[50];
			double price;
		} java,python;
		
	
	# �����ṹ��
		struct {
			int id;
			char name[50];
			char author[50];
			double price;
		} java,python = {1,"KevinBlandy","Litch"};
	
	# ��������ṹ��
		struct Book {
			int id;
			char name[50];
			char author[50];
			double price;
		};

		* �����ýṹ��ı���
			struct Book t1, t2[20],*t3;
 
	# ��typedef����������
		typedef struct {
			int id;
			char name[50];
			char author[50];
			double price;
		} Book;

		* ���ڿ�����Book��Ϊ���������µĽṹ�����
			Book t1, t2[20],*t3;
	


----------------------------
λ��						|
----------------------------
	# ��Щ��Ϣ�ڴ洢ʱ,������Ҫռ��һ���������ֽ�,��ֻ��ռ������һ��������λ
	# �����ڴ��һ��������ʱ,ֻ�� 0 �� 1 ����״̬,�� 1 λ����λ����
	# Ϊ�˽�ʡ�洢�ռ�,��ʹ������,C �������ṩ��һ�����ݽṹ,��Ϊ"λ��"��"λ��"
	# �����븳ֵ
		struct Bits {
			int a :8;				//a����ռ��8bit
			unsigned int b :2;		//b����ռ��2bit
			int c :6;				//c����ռ��6bit
		} bits,*p;			

		bits.a = 2;			//��ʼ������ֵ

		p = &bits;			//��ȡ��ַ
	
		//ͨ������/ָ�����ֵ
		printf("%d %d\n",bits.a,p -> a);		//2 2
	

	# һ��λ�����洢��ͬһ���ֽ���,���ܿ������ֽ�
		* ��'һ���ֽ���ʣ�ռ䲻�������һλ��ʱ,Ӧ����һ��Ԫ���Ÿ�λ��',Ҳ��������ʹĳλ�����һ��Ԫ��ʼ
		struct Bits {
			unsigned a :4;	//һ�����ݴ洢����4bit
			unsigned   :4;	//ʣ�µ�4bit����
			unsigned b :4;	//����һ����Ԫ��ʼ���
			unsigned c :4;
		};
	
	# ����λ������������ֽ�,���λ��ĳ��Ȳ��ܴ���һ���ֽڵĳ���,Ҳ����˵���ܳ���8λ����λ
		* �����󳤶ȴ��ڼ�����������ֳ���һЩ���������ܻ���������ڴ��ص�
		* ����һЩ���������ܻ�Ѵ���һ����Ĳ��ִ洢����һ������

	# λ�����������λ��,��ʱ��ֻ�������������λ��,������λ���ǲ���ʹ�õ�
		struct k{
			int a:1;
			int  :2;    /* �� 2 λ����ʹ�� */
			int b:3;
			int c:2;
		};

		

	# bitλ������С�ڵ�������������
		struct Bits{
			int a:1;		//1С�ڵ���int��λ��,��������ǺϷ���
		};
	
	
	# ��ֵ�������λ��,���ܻᶪʧ����
		struct {
			unsigned int age :3;
		} Age;

		Age.age = 8; // �����Ʊ�ʾΪ 1000 ����4λ,����
		printf("Age.age : %d\n", Age.age);		//0