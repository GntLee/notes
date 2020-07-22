/*
https://leetcode-cn.com/problems/unique-morse-code-words/submissions/
 ����Ħ��˹���붨��һ�ֱ�׼���뷽ʽ����ÿ����ĸ��Ӧ��һ����һϵ�е�Ͷ�����ɵ��ַ����� ����: "a" ��Ӧ ".-", "b" ��Ӧ "-...", "c" ��Ӧ "-.-.", �ȵȡ�

 Ϊ�˷��㣬����26��Ӣ����ĸ��ӦĦ��˹��������£�

 [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 ����һ�������б�ÿ�����ʿ���д��ÿ����ĸ��ӦĦ��˹�������ϡ����磬"cab" ����д�� "-.-..--..."��(�� "-.-." + "-..." + ".-"�ַ����Ľ��)�����ǽ�����һ�����ӹ��̳������ʷ��롣

 �������ǿ��Ի�����дʲ�ͬ���ʷ����������

 ����:
 ����: words = ["gin", "zen", "gig", "msg"]
 ���: 2
 ����:
 �����ʷ�������:
 "gin" -> "--...-."
 "zen" -> "--...-."
 "gig" -> "--...--."
 "msg" -> "--...--."

 ���� 2 �ֲ�ͬ����, "--...-." �� "--...--.".


 ע��:

 �����б�words �ĳ��Ȳ��ᳬ�� 100��
 ÿ������ words[i]�ĳ��ȷ�ΧΪ [1, 12]��
 ÿ������ words[i]ֻ����Сд��ĸ��

 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

char * morse[] = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
		"..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
		"...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };

struct Node {
	char *value;
	struct Node * next;
};

struct List {
	int size;
	struct Node * head;
};

bool contains(struct List * list, char *value) {
	struct Node * node = list->head;
	while (node != NULL) {
		if (strcmp(node->value, value) == 0) {
			return true;
		}
		node = node->next;
	}
	return false;
}

void add(struct List * list, char *value) {
	if (list->head == NULL) {
		list->head = (struct Node *) calloc(sizeof(struct Node), 1);
		list->head->value = value;
		list->head->next = NULL;
		list->size++;
	} else {
		if (!contains(list, value)) {
			struct Node * node = list->head;
			while (node->next != NULL) {
				node = node->next;
			}
			node->next = (struct Node *) calloc(sizeof(struct Node), 1);
			node->next->value = value;
			node->next->next = NULL;
			list->size++;
		}
	}
}

void clear(struct List * list) {
	if (list != NULL) {
		if (list->head != NULL) {
			struct Node * node = list->head;
			while (node != NULL) {
				struct Node * freeNode = node;
				node = node->next;
				free(freeNode->value);
				free(freeNode);
			}
		}
	}
}

int uniqueMorseRepresentations(char** words, int wordsSize) {
	struct List list = { 0, NULL };
	for (int i = 0; i < wordsSize; i++) {
		char *word = words[i];
		//Ħ˹���4���ַ�,һ���������12���ַ�
		char * buf = (char *) calloc(sizeof(char), (4 * 12) + 1);
		for (int x = 0; x < strlen(word); x++) {
			char ch = word[x];
			//ÿ���ַ���Ӧ��Ħ˹��
			char * key = morse[ch - 'a'];
			strcat(buf,key);
		}
		add(&list, buf);
	}
	int count = list.size;
	clear(&list);
	return count;
}
int main(int argc, char **argv) {
	char * arr[] = { "gin", "zen", "gig", "msg" };
	int result = uniqueMorseRepresentations(arr, 4);
	printf("%d\n", result);
	return EXIT_SUCCESS;
}

