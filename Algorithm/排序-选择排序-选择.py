
# ѡ������
	* ���ĵ�˼�����, ��������, ��ȡ����С��
	* ÿ�α���������С��Ԫ���ƶ���ͷ��



# ����ʵ��

def minIndex(arr, start):
    length = len(arr)
    if length == start:
        # �����������
        return None

    minVal = arr[start]
    index = start

    for i in range(start + 1, length):
        if arr[i] < minVal:
            minVal= arr[i]
            index = i

    return index

def selectsSrt(arr):
    length = len(arr)

    for i in range(length):
        index = minIndex(arr, i)
        if index:
            if arr[index] < arr[i]:
                temp = arr[i]
                arr[i] = arr[index]
                arr[index] = temp

arr = [1, 3, 6, 9, 8, 5, 7, 2, 0, 4]

selectsSrt(arr)

print(arr)
