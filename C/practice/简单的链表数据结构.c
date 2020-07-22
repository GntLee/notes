#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct List {
	void *value;
	struct List *next;
};

void listForEach(struct List **lists,size_t size){
	for(int x = 0 ;x  < size ; x++){
		if(x == (size - 1)){
			printf("value = %s ,next = NULL\n",lists[x] -> value);
		}else{
			printf("value = %s ,next = %s\n",lists[x] -> value,lists[x] -> next -> value);
		}
	}
}

int main(int argc,char **argv) {

	struct List *lists[10];

	for(int x = 0 ;x < 10 ;x++){

		//�Ӷ��ڴ��ȡ����ռ�
		struct List *list = (struct List *)malloc(sizeof(struct List));

		//����valueֵ
		char *value = (char *)malloc(4);
		sprintf(value,"[%d]",x);
		list -> value = value;

		//����ڵ��ϵ
		if (x > 0){
			lists[x - 1] -> next =  list;
		}
		lists[x] = list;
	}

	listForEach(lists,10);

	//�ڴ��ͷ�
	for(int x = 0 ;x < 10 ;x++ ){
		free(lists[x] -> value);
		free(lists[x]);
	}
	return EXIT_SUCCESS;
}
