----------------------------
�ļ����					|
----------------------------
	# �ļ�������
		* �豸�ļ�,�豸Ҳ���Ե����ļ�,�߱�io
		* �����ļ�

	# ���ļ�
		FILE *fopen( const char * filename, const char * mode );
		
		* ��һ���ļ�,nameָ���ļ���ַ,modelָ���򿪵�����
		* model��ö���ַ���
			r	��һ�����е��ı��ļ�,�����ȡ�ļ�

			w	��һ���ı��ļ�,����д���ļ�,����ļ�������,��ᴴ��һ�����ļ�
				������,�������ļ��Ŀ�ͷд������,����ļ�����,��ûᱻ�ض�Ϊ�㳤��,����д��

			a	��һ���ı��ļ�,��׷��ģʽд���ļ�,����ļ�������,��ᴴ��һ�����ļ�
				������,����������е��ļ�������׷������

			r+	��һ���ı��ļ�,�����д�ļ�

			w+	��һ���ı��ļ�,�����д�ļ�,����ļ��Ѵ���,���ļ��ᱻ�ض�Ϊ�㳤��
				����ļ������ڣ���ᴴ��һ�����ļ�

			a+	��һ���ı��ļ�,�����д�ļ�,����ļ�������,��ᴴ��һ�����ļ�,��ȡ����ļ��Ŀ�ͷ��ʼ,д����ֻ����׷��ģʽ
		
		*  ������Ƕ������ļ�,����ʹ������ķ���ģʽ��ȡ������ķ���ģʽ��
			"rb", "wb", "ab", "rb+", "r+b", "wb+", "w+b", "ab+", "a+b"
		
		* �����ʧ��,���� NULL
			FILE *file = fopen("d.txt","r");
			if (file == NULL){
				perror("fopen");	//fopen: No such file or directory
				return EXIT_FAILURE;
			}
		* ע��,�ýṹ�����ڶѿռ�����,ʹ����ɺ�Ҫ�ͷ�
		* �ļ��Ĵ�,���������·��,Ҳ�����Ǿ���·��
			fopen("./d.txt","r");


	# �ر��ļ�
		int  fclose (FILE *);
		* �ر��ļ���ˢ��������
		* �ɹ����� 0,ʧ�ܷ��� -1
	
	# ˢ��������
		int fflush (FILE *);
		* �ɹ����� 0,ʧ�ܷ��� -1
	
	# �ض��������ļ�
		FILE * freopen (const char *filename, const char *mode, FILE *stream);
		* filename ��Ҫ�ض��򵽵��ļ������ļ�·��
		* mode �����ļ�����Ȩ�޵��ַ���,���磬"r"��ʾ"ֻ������","w"��ʾ"ֻд����","a"��ʾ"׷��д��"
		* stream ��Ҫ���ض�����ļ���
		* ����ɹ����򷵻ظ�ָ�����������ļ�ָ�룬���򷵻�ΪNULL
			if(freopen("D:\\output.txt", "w", stdout) == NULL){
				fprintf(stderr,"error redirecting stdout\n");
			}
			/* this output will go to a file */
			printf("This will go into a file.\n");
			/*close the standard output stream*/
			fclose(stdout);
		
	# �����ļ��Ļ�����
		int setvbuf (FILE *file, char *buf, int model, size_t size);
		* �����ļ��Ļ�����,���óɹ�����0
		* file �ļ�
		* buf ������ָ��
		* model ö��ֵ,��������ģʽ
			_IOFBF		0x0000	/* ��ȫ����,�ڻ���������ʱ��ˢ�� */
			_IOLBF		0x0040	/* �л���,�ڻ�����������д��һ�����з�ʱˢ�� */
			_IONBF		0x0004	/* �޻��� */
		* size �������Ĵ�С
	

	# FILE �ṹ�������
		typedef struct _iobuf
		{
		  char	*_ptr;
		  int	 _cnt;
		  char	*_base;
		  int	 _flag;
		  int	 _file;
		  int	 _charbuf;
		  int	 _bufsiz;
		  char	*_tmpfname;
		} FILE;

		typedef struct 
		{
			short			level;		//������״̬,�����߿�
			unsigned		flags;		//�ļ�״̬��ʶ
			char			fd;			//�ļ�������
			unsigned char	hold;		//���޻���������ȡ�ַ�
			short			bsize;		//��������С
			unsigned char	*buffer;	//����������λ��
			unsigned		ar;			//ָ��,��ǰ��ָ��
			unsigned		istemp;		//��ʱ�ļ�,ָʾ��
			short			token;		//������Ч�Եļ��
		} FILE;
	
	# ��ָ�����ַ��Ż�����
		int ungetc (int, FILE *)
		* �о�ûɶ��...
			ch = getchar();
			ungetc(cg,stdin);
	
	# �쳣�ж�
		int ferror (FILE *);
		* ����ļ�IO�������쳣,�ú������ط�0,���򷵻�0
	
	# �ļ���β�ж�
		* �ı��ļ���ĩβ����һ�����ص�-1(EOF),��ʾ�ļ��Ѿ�����
		* �������ļ�ĩβû��-1��ʶ,��Ϊ-1�������ļ��е�����,ͬ�� -1 ���жϲ�����
		* ����ͨ�� feof() ���ж��ļ��Ƿ��ȡ����ĩβ,�����Ƕ������ļ������ı��ļ�
		* feof(); ���� bool
			FILE *file = fopen("c.txt","r");

			bool end = feof(file);
			printf("%d",end);		//0

		* ����ʵ���ж����ȡ�������,�ǲ���ĩβ��ʶ,Ҳ����˵Ҫ�ȶ�ȡ��ĩβ��ʶ,����,һ���ȶ�ȡ,���ж�
			fgetc(file);
			if(feof(file)){
				break;
			}
			....

----------------------------
�ı��ļ��Ķ�д				|
----------------------------
	# ��ȡ/д�뵥���ַ�
		fgetc( FILE * fp );
			* fgetc() ������ fp ��ָ��������ļ��ж�ȡһ���ַ�,����ֵ�Ƕ�ȡ���ַ�
			* ������������򷵻� EOF,(-1)
		
		fputc( int c, FILE *stream );
			* ���ڰѵ����ַ�д��streamָ�����ļ���
			* ��д��ɹ�,���᷵��д����ַ�,�����������,���߶�ȡ����ĩβ,��᷵�� EOF(-1)
		
	# ��ȡ/д���ַ���
		fgets(char *s,int size,FILE stream)
			* ����
				s: �ַ���
				size:ָ����ȡ����ַ����ĳ���(Ĭ�� -1,������)
				stram:�ļ�ָ��,'�����ȡ����������ַ���,�̶�Ϊstdin'
			* ��streamָ�����ļ��ڶ����ַ�,���浽sָ�����ڴ�ռ�,ֱ�����ֻ����ַ�,��ȡ���ļ���β,�����Ѿ���ȡ��size - 1 ���ַ�Ϊֹ
			* ���Զ��������� '\0' ��ʶ,��ѻ��з�Ҳһ���ȡ��ȥ
			* s���ֻ��װ length - 1���ַ�,��Ϊ����Ҫ��һ�����ַ��������� '\0',����������ݴ����� size ���� sizeof(s) ��ô�������ֻᱻ�ض�
			* ��ȡ�ɹ����ض�ȡ�����ַ���,��ȡ���ļ�ĩβ���߳���,���� NULL
			* ��ȡ�ļ�
				FILE *file = fopen("E:\\c-lang.txt","r");
				char buf[1024];
				char *r = fgets(buf,sizeof(buf) - 1,file);
				while(r != NULL){
					printf("%s",buf);
					r = fgets(buf,sizeof(buf) - 1,file);
				}
		
		fputs(const char *str,FILE *stream)
			* �� str �ַ�д�뵽streamָ�����ļ���,�ַ��������� '\0' ��д���ļ�
			* �ɹ����� 0,ʧ�ܷ��� EOF(-1)
			* ���԰�stream�滻Ϊ stdout,ʹstr���������Ļ
		
	

	# ��ʽ�������		fprintf(FILE *fp,const char *format, ...)
		* Դ�Ǳ���,Ŀ�����ļ�

		FILE *file = fopen("E:\\c-lang.txt","w+");
		fprintf(file,"Hello KevinBlany ,Im %s","Java");
		fclose(file);
		
	
	# ��ʽ��������		int fscanf(FILE *fp, const char *format, ...) 
		* Դ���ļ�,Ŀ���Ǳ���
		* ���������ļ��ж�ȡ�ַ���,������������һ���ո��ַ�ʱ,����ֹͣ��ȡ

		FILE *file = fopen("E:\\c-lang.txt","r");
		int x,y,z;
		fscanf(file,"%d %d %d",&z,&y,&z);
		printf("z=%d,y=%d,z=%d",z,y,z);		//z=36,y=12,z=36
		fclose(file);
	
	
	
----------------------------
���io						|
----------------------------
	int fseek (FILE *file, long offset, int whence); 
		* fseek ���õ�ǰ��д�㵽 offset ��
		* ��������ɹ�����0,����ʧ�ܷ���-1(�ƶ��ķ�Χ�������ļ���С)
		* whence ������ SEEK_SET,SEEK_CUR,SEEK_END ��Щֵ�����Ǵ��ļ�ͷ,��ǰ��,�ļ�β	����ƫ���� offset
		* ��Ե�ǰλ�������ƶ�һ���ֽ�:fseek(fp,1,SEEK_CUR);
		* ��ǰ�ƶ�һ���ֽ�.ֱ�Ӹ�Ϊ��ֵ�Ϳ���:fseek(fp,-1,SEEK_CUR)

			FILE *file = fopen("E:\\c-lang.txt","r+");
			//ָ���ƶ�����10���ֽ�
			fseek(file,10,SEEK_SET);
			//�ѵ�10���ֽ��滻Ϊ'A'
			if(fputc('A',file) == EOF){
				printf("�쳣");
			}
			fclose(file);

		* ֻ���� r+ ģʽ���ļ����ܲ�������,w �� w+ ģʽ������յ�ԭ���ļ�����������д
		* a �� a+ ģʽ���ܻ����ļ���β�������,������ fseek() �ƶ����ļ�ָ��λ��

	long ftell(FILE *file);
		* ��ȡ�ļ�����λ��
		* ���ʧ�ܷ��� -1
	
	void rewind(FILE *file);
		* ���ļ�����ƶ����ļ���ͷ(����ָ��λ��)

	# ͨ�����io��ȡ�ļ��Ĵ�С
		//���ļ�
		FILE *file = fopen("D:\\springboot.sql","r+");
		//�ƶ�ָ�뵽ĩβ
		fseek(file,0,SEEK_END);
		//��ȡ�ļ��Ĺ��λ��,��ʵ���Ǵ�С(KB)
		long size = ftell(file);
		printf("size=%ld",size);
	
	# fgetpos(); �� fsetpos();
		int fgetpos (FILE *, fpos_t *);
			* ��ȡ�ļ�ָ��λ��,ע��,������Ҫ��������ָ��,��ͨ��ָ�븳ֵ��
		int fsetpos (FILE *, const fpos_t *);
			* �����ļ�ָ��λ��,ע��,������Ҫ��������ָ��,��ͨ��ָ���ȡ������

		* fseek() �� fetll() ��������,���Ƕ�ʹ�� long �����������,����ʾ20�ڸ��ֽ�
		* �������ڵ��ļ�Խ��Խ��,���ܳ����� 20 ��
		* �µ��Զ�����������: fpos_t  == typedef long long  fpos_t;
		* ���ִ�гɹ�����0,ʧ�ܷ���-1

----------------------------
�������ļ��Ķ�д			|
----------------------------
	
	# ��ȡ�������ļ�
		* size_t fread(void *ptr, size_t size_of_elements, size_t number_of_elements, FILE *a_file);
			* ptr ��Ŷ�ȡ���ݵ��ڴ�ռ�
			* size_of_elements ָ����ȡ�ļ����ݵĿ����ݴ�С(unsigned int)
			* number_of_elements ��ȡ�ļ��Ŀ���,��ȡ�ļ������ܴ�СΪ: size_of_elements * number_of_elements
			* a_file �Ѿ��򿪵��ļ�ָ��
		* �ɹ����أ�number_of_elements(��ȡ�����ļ�����),�����ֵ��number_of_elementsС,���Ǵ���0,���ʾ��ȡ�����ļ�ĩβ
		* ����û�ָ����ȡ�Ŀ����С,�������ļ��ɶ��Ĵ�С,���ؿ���Ϊ0
			* ��ȡ1���(һ�������Ϊ��10���ֽ�)
			* �����ļ�ֻ��5���ֽ��ˡ�ֻ�ܶ�ȡ0.5���������0.5����Ϊ�����int��������ת������Ϊ 0
			* '������С��Զ����Ϊ1,�򲻻���ָ�����'
		* ʧ��,����0
	
	# д�����������
		* size_t fwrite(const void *ptr, size_t size_of_elements,size_t number_of_elements, FILE *a_file);
			* ptr ׼��д���ļ�������
			* size_of_elements ָ��д���ļ����ݵĿ����ݴ�С(unsigned int)
			* number_of_elements д���ļ��Ŀ���,д���ļ����ܴ�С = size_of_elements * number_of_elements
			* a_file �Ѿ��򿪵��ļ�ָ��
		* �ɹ����أ�number_of_elements(�ɹ�д���ļ����ݵĿ���Ŀ),ʧ�ܷ��� 0
		



	# �����������������ڴ洢��Ķ�д (д��Ͷ�ȡ��ͨ���������ṹ��)

	# �򵥵�copy
		FILE *source = fopen("D:\\20181009153347.jpg","rb");
		FILE *target = fopen("D:\\cp.jpg","wb");

		char buf[1024];

		int result = fread(buf,1,1024,source);
		while(result > 0){
			printf("��ȡ����:%d\n",result);
			fwrite(buf,1,result,target);
			fflush(target);
			result = fread(buf,1,1024,source);
		}

		fclose(target);
		fclose(source);
	
	# struct �Ķ�д
		struct Lang {
			unsigned int id;
			char name[10];
		};
		struct Lang lang = {2,"Python"};

		FILE *file = fopen("D:\\lang.d","wb");
		fwrite(&lang,sizeof(struct Lang),1,file);
		fflush(file);
		fclose(file);

		file = fopen("D:\\lang.d","rb");
		fread(&lang,sizeof(struct Lang),1,file);
		printf("lang=%d,name=%s\n",lang.id,lang.name);
	
	# struct ����Ķ�д
		struct Lang {
			unsigned int id;
			char name[11];
		};

		struct Lang langs[] = {
			{1,"Java"},
			{2,"Python"},
			{3,"C"},
			{4,"Javascript"}
		};

		FILE *file = fopen("D:\\langs.d","wb");
		fwrite(langs,sizeof(langs),1,file);
		fflush(file);
		fclose(file);

		file = fopen("D:\\langs.d","rb");
		fread(langs,sizeof(langs),1,file);
		for(int x = 0 ;x < 4 ; x++){
			printf("id=%d,name=%s\n",langs[x].id,langs[x].name);
		}
	

----------------------------
Linux��Window�µ��ļ�����	|
----------------------------
	# fopen("c.txt","rb") �ڶ��������е� "b"
		* "b"��ʾ��2����ģʽ���ļ�,��ʵ��Linuxϵͳ��,ʹ�øú������ü�"b",Ҳ�ǿ��Ե�
			fopen("c.txt","r");	//��Linux��ͬ�����Զ����Ƶ���ʽ���ļ�
		* Ϊ��ϵͳ����,һ�㻹�Ǽ�һ��"r"�ȽϺ�
	
	# Unix��Linux�µ������ı��ļ�������:"\n"��β,��Windows�µ��ı��ļ���:"\r\n"��β
	# ��Windowsƽ̨��,��ȡ�ı��ļ���ʱ��,ϵͳ������е�"\r\n"ת��Ϊ"\n"
	# ��Windowsƽ̨��,д���ı��ļ���ʱ��,ϵͳ������е�"\n"ת��Ϊ"\r\n"
	# �ж��ļ���Linux�ļ�����Windows�ļ�

----------------------------
ɾ����������				|
----------------------------
	# ɾ���ļ�
		int remove (const char file*);
		* �ɹ�����0,ʧ�ܷ���-1

	# ������
		int rename (const char *old, const char *new_);
		* �ɹ�����0,ʧ�ܷ���-1

----------------------------
��ȡ�ļ�������				|
----------------------------
	# ͨ�� <sys/stat.h> �µ� int stat (const char *path, struct stat *); ����
		* ��ȡ�ļ�������,�ɹ�����0,ʧ�ܷ���-1
		* path �ļ���·��,stat ����״̬�Ľṹ��
		* �ṹ��Ķ���
			{ _dev_t	st_dev; 	/* Equivalent to drive number 0=A 1=B ... */ 
			  _ino_t	st_ino; 	/* Always zero ? */			   
			  _mode_t	st_mode;	/* See above constants */		     
			   short 	st_nlink;	/* Number of links. */			     
			   short 	st_uid; 	/* User: Maybe significant on NT ? */	     
			   short 	st_gid; 	/* Group: Ditto */			    
			  _dev_t	st_rdev;	/* Seems useless (not even filled in) */    
			  __st_off_t	st_size;	/* File size in bytes */		    
			  __st_time_t	st_atime;	/* Access time (always 00:00 on FAT) */	    
			  __st_time_t	st_mtime;	/* Modified time */			    
			  __st_time_t	st_ctime;	/* Creation time */			    
			}

	# ͨ���÷�����ȡ�ļ��Ĵ�С
		#include <stdlib.h>
		#include <stdio.h>
		#include <sys/stat.h>

		int main(int argc, char **argv) {
			struct stat st;
			int result = stat("D:\\springboot.sql",&st);
			printf("size=%d\n",st.st_size);
			return EXIT_SUCCESS;
		}
	
	# ͨ�� 'st_mode' ���Կ��Ի�ȡ����������ļ�����Ϣ
		*  �����жϺ������� <sys/stat.h> �궨���,�����ж�,���� bool
			S_ISDIR(m)	(((m) & S_IFMT) == S_IFDIR)			//�Ƿ���Ŀ¼
			S_ISFIFO(m)	(((m) & S_IFMT) == S_IFIFO)
			S_ISCHR(m)	(((m) & S_IFMT) == S_IFCHR)
			S_ISBLK(m)	(((m) & S_IFMT) == S_IFBLK)
			S_ISREG(m)	(((m) & S_IFMT) == S_IFREG)			//�Ƿ�����ͨ�ļ�

		*  demo
			S_ISDIR(fileStat.st_mode);		//�Ƿ���һ��Ŀ¼
			S_ISREG(fileStat.st_mode)		//�Ƿ���һ����ͨ�ļ�
		
-----------------------------
����Ŀ¼(�ļ���)			|
----------------------------
# <dirent.h> 
	# ��һ��Ŀ¼,����һ��Ŀ¼ָ�� typedef struct __dirstream_t DIR;
		DIR * opendir (const char *__dirname)
	
	# ��ȡĿ¼��Ϣ,������һ��Ŀ¼ָ��,���� struct dirent
		struct dirent*  readdir (DIR *__dir)
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

# <io.h>
	# ����һ���ļ���
		int mkdir (const char *);
		* �����ɹ�����0,���򷵻�-1(Ŀ¼�Ѿ�����)
	
	# ��ȡ��ǰִ�г������ڵ�Ŀ¼
		char *getcwd (char *buf, int size);
		
		* �ѵ�ǰ·��д��buf,��󳤶�Ϊsize
			char buf[1024] = { 0 };
			getcwd(buf, 1024);
			printf("%s", buf);	//D:\workspace\clang-practice

----------------------------
�����ļ�������				|
----------------------------
	# ANSI C��׼����"�������ļ�ϵͳ"
