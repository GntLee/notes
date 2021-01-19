#include <string.h>

struct IntArray {
	int size;			//���ݳ���
	int capacity;		//��������
	int * arr;			//��Ԫ��ָ��
};

int init(struct IntArray *, int);		/** ��ʼ�� **/
int addFirst(struct IntArray *,int);	/** ���Ԫ�ص���ͷ **/
int addLast(struct IntArray *,int);		/** ���Ԫ�ص�ĩβ **/
int add(struct IntArray *, int, int);	/** ���Ԫ�ص�ָ����λ�� **/
int set(struct IntArray *, int, int);	/** ����Ԫ�ص�ָ����λ�� **/
void forEach(struct IntArray *);		/** ����,��ӡԪ�� **/
int indexOf(struct IntArray *,int);		/** ��ȡָ��Ԫ�ص�һ�γ��ֵ��±� **/
int removeIndex(struct IntArray *, int);/** �����±�ɾ��Ԫ�� **/
void removeItem(struct IntArray *, int);/** ɾ����һ��ָ��ֵ��Ԫ�� **/
int removeFirst(struct IntArray *);		/** ɾ����һ��Ԫ�� **/
int removeLast(struct IntArray *);		/** ɾ�����һ��Ԫ�� **/


int init(struct IntArray *arr, int capacity) {
	if(capacity <= 0){
		return -1;
	}
	arr->capacity = capacity;
	arr->size = 0;
	arr->arr = (int *) calloc(sizeof(int), arr->capacity);
	return 0;
}

int addFirst(struct IntArray *arr,int value){
	return add(arr, 0, value);
}

int addLast(struct IntArray *arr,int value){
	return add(arr, arr->size, value);
}

int add(struct IntArray *arr, int index, int value) {
	if (index < 0 || index > arr->size) {
		return -1;
	}
	if (arr->size == arr->capacity) {
		arr->capacity = arr->capacity * 2;
		realloc(arr->arr, arr->capacity);
	}

	for (int i = arr->size - 1; i >= index; i--) {
		arr->arr[i + 1] = arr->arr[i];
	}
	arr->arr[index] = value;
	arr->size++;
	return 0;
}

int set(struct IntArray *arr, int index, int value) {
	if (index < 0 || index >= arr->size) {
		return 1;
	}
	arr->arr[index] = value;
	return 0;
}

int indexOf(struct IntArray *arr,int value){
	for (int i = 0; i < arr->size; i++) {
		if(arr->arr[i] == value){
			return i;
		}
	}
	return -1;
}

int removeIndex(struct IntArray *arr, int index) {
	if (index < 0 || index >= arr->size) {
		return -1;
	}
	int value = arr->arr[index];
	for (int i = index + 1; i < arr->size; i++) {
		arr->arr[i - 1] = arr->arr[i];
	}
	arr->size--;
	return value;
}

int removeFirst(struct IntArray *arr){
	return removeIndex(arr, 0);
}
int removeLast(struct IntArray *arr){
	return removeIndex(arr, arr->size - 1);
}

void removeItem(struct IntArray *arr, int value) {
	int index = indexOf(arr, value);
	if(index != -1){
		removeIndex(arr, index);
	}
}

void forEach(struct IntArray *arr) {
	printf("size=%d capacity=%d [", arr->size, arr->capacity);
	for (int i = 0; i < arr->size; i++) {
		printf("%d", arr->arr[i]);
		if (i != (arr->size - 1)) {
			printf(",");
		}
	}
	printf("]\n");
}





