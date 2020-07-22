/**
 *
 * ����
 *
 */
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

struct Node {
	void *value;
	struct Node *next;
};

struct LinkedList {
	int size;
	struct Node *head;
};

typedef struct LinkedList * list_t;

int init(list_t); /** ��ʼ������ **/

int add(list_t, int, void *);/** ���Ԫ�ص�ָ���±� **/
int addFirst(list_t, void *);/** ���Ԫ�ص��ײ� **/
int addLast(list_t, void *);/** ���Ԫ�ص�β�� **/

void * get(list_t, int);/** ��ȡָ���±��Ԫ�� **/
void * getFirst(list_t);/** ��ȡ�ײ�Ԫ�� **/
void * getLast(list_t);/** ��ȡβ��Ԫ�� **/

int set(list_t, int, void *);/** �޸�ָ���±��ֵ **/
bool contains(list_t, void *, bool (*)(void *, void *));/** �ж��Ƿ����ָ����ֵ **/

void * removeIndex(list_t, int);/** ɾ��ָ���±��ֵ **/
void * removeFirst(list_t);/** ɾ���׽ڵ� **/
void * removeLast(list_t);/** ɾ��β�ڵ� **/

void consumer(list_t, void (*)(void *));/** ���ѽӿ� **/

int init(list_t list) {
	struct Node * node = (struct Node *) calloc(sizeof(struct Node), 1);
	if (node == NULL) {
		return 1;
	}
	node->next = NULL;
	node->value = NULL;

	list->head = node;
	list->size = 0;
	return 0;
}

int add(list_t list, int index, void *value) {
	if (index < 0 || index > list->size) {
		return -1;
	}
	struct Node * pre = list->head;
	for (int i = 0; i < index; i++) {
		pre = pre->next;
	}
	struct Node * node = calloc(sizeof(struct Node), 1);
	if (node == NULL) {
		return -1;
	}

	node->value = value;
	node->next = pre->next;

	pre->next = node;
	list->size++;
	return 0;
}
int addFirst(list_t list, void *value) {
	return add(list, 0, value);
}
int addLast(list_t list, void *value) {
	return add(list, list->size, value);
}

void * get(list_t list, int index) {
	printf("ִ��");
	if (index < 0 || index >= list->size) {
		return NULL;
	}
	struct Node * cur = list->head->next;
	for (int x = 0; x < index; x++) {
		cur = cur->next;
	}
	return cur->value;
}
void * getFirst(list_t list) {
	return get(list, 0);
}
void * getLast(list_t list) {
	return get(list, list->size - 1);
}

int set(list_t list, int index, void *value) {
	if (index < 0 || index >= list->size) {
		return -1;
	}
	struct Node * cur = list->head->next;
	for (int x = 0; x < index; x++) {
		cur = cur->next;
	}
	cur->value = value;
	return 0;
}

bool contains(list_t list, void *value, bool (*equals)(void *, void *)) {
	struct Node * cur = list->head->next;
	while (cur != NULL) {
		if (equals(cur->value, value)) {
			return true;
		}
		cur = cur->next;
	}
	return false;
}

void * removeIndex(list_t list, int index) {
	if (index < 0 || index >= list->size) {
		return NULL;
	}
	struct Node * pre = list->head;
	for (int x = 0; x < index; x++) {
		pre = pre->next;
	}

	struct Node * deleteNode = pre->next;
	pre->next = deleteNode->next;

	void * ret = deleteNode->value;
	free(deleteNode);
	list->size--;
	return ret;
}
void * removeFirst(list_t list) {
	return removeIndex(list, 0);
}
void * removeLast(list_t list) {
	return removeIndex(list, list->size - 1);
}

void consumer(list_t list, void (*accept)(void *)) {
	struct Node * cur = list->head->next;
	printf("start ==========================\n");
	printf("size=%d \n", list->size);
	while (cur != NULL) {
		accept(cur->value);
		cur = cur->next;
	}
	printf("end ==========================\n");
}

//static
static void printValue(void *value) {
	int *p = (int *) value;
	printf("%d\n", *p);
}
static bool equals(void *var1, void *var2) {
	if (*((int *) var1) == *((int *) var2)) {
		return true;
	}
	return false;
}

//main test
int main(int argc, char **argv) {
	struct LinkedList linkedList;
	list_t list = &linkedList;
	init(list);

	int arr[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	for (int x = 0; x < 10; x++) {
		if (x % 2 == 0) {
			addFirst(list, &arr[x]);
		} else {
			addLast(list, &arr[x]);
		}
	}
	consumer(list, &printValue);

	printf("��Ԫ��:%d βԪ��:%d \n", *((int *) getFirst(list)),
			*((int *) getLast(list)));
	removeFirst(list);
	removeLast(list);
	consumer(list, &printValue);

	int x = 99;
	printf("�Ƿ����0:%d\n", contains(list, &x, &equals));
	set(list, 0, &x);
	consumer(list, &printValue);

	removeIndex(list, 3);
	consumer(list, &printValue);

	removeFirst(list);
	removeLast(list);
	consumer(list, &printValue);
	return EXIT_SUCCESS;
}

