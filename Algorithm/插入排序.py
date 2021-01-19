

# ʵ��1
def insertSort1(arr):
    # �ӵ�1��Ԫ�ؿ�ʼ
    i = 1
    while i < len(arr):
        # ��ǰԪ��
        temp = arr[i]
        # ��һ��Ԫ�ش��ڵ�ǰԪ��
        while i >= 0 and arr[i - 1] > temp:
            # ��һ��Ԫ�ش��ڵ�ǰԪ��
            arr[i] = arr[i - 1]
            # �Ǳ����
            i -= 1
        arr[i] = temp
        i += 1


# ʵ��2
def inset(arr,n):
    value = arr[n]
    i = n
    while arr[i - 1] > value:
        arr[i] = arr[i - 1]
        i -= 1
        if i == 0:
            break
    arr[i] = value

def insertSort2(arr):
    i = 1
    while i < len(arr):
        inset(arr,i)
        i += 1


arr = [1, 4, 7, 5, 3, 6]
insertSort1(arr)
print(arr)




    
###############��C

#include <stdio.h>
#include <stdlib.h>
void printArr(int *arr, size_t size) {
    for(int x = 0 ;x < size ; x++){
        printf("%d\n", arr[x]);
    }
}
void insetSort1(int *arr, size_t size) {
    for (int i = 1; i < size; i++) {
        int temp = arr[i];
        while (i >= 0 && arr[i - 1] > temp) {
            arr[i] = arr[i - 1];
            i--;
        }
        arr[i] = temp;
    }
}
void inset(int *arr,int n){
    int value = arr[n];
    int i = n;
    while(arr[i - 1] > value){
        arr[i] = arr[i - 1];
        i--;
        if (i == 0){
            break;
        }
    }
    arr[i] = value;
}
void insetSort2(int *arr,size_t size){
    for (int i = 1; i < size; i++) {
        inset(arr, i);
    }
}
int main(int argc, char **argv) {
    int arr[] = {1,2,6,4,7,1,5,9};
    size_t size = sizeof(arr) / sizeof(arr[0]);
    insetSort2(arr,size);
    printArr(arr, size);
    return EXIT_SUCCESS;
}