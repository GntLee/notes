#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define NAME_SIZE 20
#define MAX_LIST_SIZE 100

struct Item {
	int id;
	char name[NAME_SIZE];
};
struct Node {
	struct Item * item;
	struct Node * next;
};
struct List {
	struct Node * head;
	int size;
};

extern struct Node * createNode(struct Item *);
extern struct Item * createItem(int, char *);
extern bool addItem(struct List *, struct Item *);
extern struct Item * getItem(struct List *, int);
extern struct Node * getNode(struct List *, int);

extern struct Item * removeItem(struct List *, int);

extern void consumer(struct List *, void (*)(void *));
extern void console(void *);

int main(int argc, char **argv) {
	//������һ������
	struct List list = { .head = NULL, .size = 0 };

	addItem(&list, createItem(1, "a"));
	addItem(&list, createItem(2, "b"));
	addItem(&list, createItem(3, "c"));
	addItem(&list, createItem(4, "d"));
	addItem(&list, createItem(5, "e"));
	addItem(&list, createItem(6, "f"));
	addItem(&list, createItem(7, "g"));
	addItem(&list, createItem(8, "h"));
	addItem(&list, createItem(9, "i"));

	consumer(&list, &console);
	return EXIT_SUCCESS;
}

//������ݵ�����
bool addItem(struct List *list, struct Item *item) {
	//�����׽ڵ�
	if (list->head == NULL) {
		struct Node * node = createNode(item);
		list->head = node;
		list->size = 1;
		return true;
	} else if (list->size >= MAX_LIST_SIZE) {
		//������󳤶�
		return false;
	}
	//���������ڵ�
	struct Node * node = createNode(item);

	struct Node * next = list->head;
	while (next->next != NULL) {
		next = next->next;
	}
	next->next = node;
	list->size += 1;
	return true;
}
//�����ڵ�
struct Node * createNode(struct Item *item) {
	struct Node * node = calloc(1, sizeof(struct Node));
	node->item = item;
	node->next = NULL;
	return node;
}
//����item
struct Item * createItem(int id, char *name) {
	struct Item * item = calloc(1, sizeof(struct Item));
	item->id = id;
	strncpy(item->name, name, NAME_SIZE - 1);
	return item;
}
//�����±��ȡnode
struct Node * getNode(struct List *list, int index) {
	if (list->size == 0) {
		return NULL;
	} else if (list->size <= index) {
		return NULL;
	}

	struct Node * node = list->head;
	while ((index--) >= 1) {
		node = node->next;
	}
	return node;
}
//�����±��ȡnode
struct Item * getItem(struct List *list, int index) {
	struct Node * node = getNode(list, index);
	return node == NULL ? NULL : node->item;
}
//ɾ��ָ���±�Ľڵ�
struct Item * removeItem(struct List * list, int index) {
	if (list->size == 0) {
		return NULL;
	} else if (list->size <= index) {
		return NULL;
	}
	struct Item * ret_val = NULL;
	if (index == 0) {
		struct Node * node = list->head;
		ret_val = node->item;
		list->head = node->next;
		free(node);
	} else {
		struct Node * preNode = getNode(list, index - 1);
		struct Node * node = getNode(list, index);

		ret_val = node->item;
		if (node->next != NULL) {
			preNode->next = node->next;
		} else {
			preNode->next = NULL;
		}
		free(node);

	}
	list->size -= 1;
	return ret_val;
}


//�����нڵ���д���
void consumer(struct List *list, void (*consumer)(void *)) {
	struct Node * node = list->head;
	while (node != NULL) {
		consumer(node->item);
		node = node->next;
	}
}
//��ӡһ��item
void console(void *valule) {
	struct Item * item = ((struct Item *) valule);
	printf("id=%d,name=%s\n", item->id, item->name);
}
