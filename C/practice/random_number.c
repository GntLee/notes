#include <stdlib.h>
#include <stdio.h>
#include <time.h>

//��ȡ�����
int random_number(){
	return rand();
}

//�������
void fill_random_number(int *arr,size_t size,int (* r)(void)){
	for(int x = 0 ;x < size ; x++){
		*(arr + x) = r();
	}
}
int main(void){
	srand(time(NULL));
	int length = 10;
	int arr[length];
	fill_random_number(arr,length,&random_number);
	for(int x = 0 ;x < length; x++){
		printf("%d\n",arr[x]);
	}
}
