# �鲢����
	* Merge Sort, �����ù鲢��˼��ʵ�ֵ����򷽷�
	
	* ���þ���ķ��β��ԣ�������ֽ��С����
	* �ݹ���⣬�����������ϵ�һ��
	

# ����ʵ��


def merge(arr, left, mid, right, temp):
    '''
    :param arr:     ԭʼ��Ҫ���������
    :param left:    ��߳�ʼ����
    :param mid:     �м�����
    :param right:   �ұ�����
    :param temp:    ��ת������
    :return:
    '''

    i = left    # ����������еĳ�ʼ����
    j = mid + 1 # �ұ��������еĳ�����
    t = 0       # ָ��ǰtemp������

    while i <= mid and j <= right:
        if arr[i] <= arr[j]:
            temp[t] = arr[i]
            t += 1
            i += 1
        else:
            temp[t] = arr[j]
            t += 1
            j += 1

    while i <= mid:
        temp[t] = arr[i]
        t += 1
        i += 1

    while j <= right:
        temp[t] = arr[j]
        t += 1
        j += 1

    t = 0
    tempLeft = left
    while tempLeft <= right:
        arr[tempLeft] = temp[t]
        t += 1
        tempLeft += 1


def mergeSort(arr, left, right, temp):
    if left < right:
        mid = (left + right) // 2
        # ��ݹ�ֽ�
        mergeSort(arr, left, mid, temp)
        # �ҵݹ�ֽ�
        mergeSort(arr, mid + 1, right, temp)
        # �ϲ�
        merge(arr, left, mid, right, temp)



arr = [8, 4, 5, 7, 1, 3, 6, 2]
temp = [0 for i in range(len(arr))]

mergeSort(arr, 0, len(arr) - 1, temp)

print(arr)

