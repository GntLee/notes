# ��������
	* ����˼����ǰ�n���������Ԫ��, ����һ��������һ�������
	* ��ʼ�����ʱ��, �������ֻ����һ��Ԫ��, ������а��� n - 1 ��Ԫ��(�������ѡ��һ��Ԫ����Ϊ�����)
	* Ȼ��ÿ�δ��������ȡ��һ��Ԫ��, �������뵽������к��ʵ�λ��, �γ��µ������
	* �Ӷ��������



# ����ʵ��

def insertSort(arr):
    length = len(arr)
    for i in range(1, length):
        val = arr[i]
        # �����б�����һ���±�
        valIndex = i - 1

        while valIndex >= 0 and val < arr[valIndex]:
            arr[valIndex + 1] = arr[valIndex]
            valIndex -= 1

        if not valIndex + 1 == i:
            arr[valIndex + 1] = val


arr = [0, 5, 4, 8, 1, 2, 3, 7, 9, 6]

insertSort(arr)

print(arr)


	