# ��ֵ����
	* Ҫ�󱻲��ҵļ�¼������ģ����������ε�����
	* �����ڶ��ֲ��ң�Ψһ��ͬ���ǲ�ֵ����ÿ�δ�����Ӧ mid ����ʼ����
		mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low])
	
		key: ���Ǽ�����ֵ
		arr: ��������
		low: �ͽǱ�
		high: �߽Ǳ�
		mid: �����������Ӧmid
		



	
# ����ʵ��


def insert_value_search(collection, low, high, value):
    if low > high or value < collection[0] or value > arr[len(arr) - 1]:
        return -1

    mid = low + (high - low) * (value - arr[low]) // (arr[high] - arr[low])
    mid_value = arr[mid]

    if value > mid_value:
        return insert_value_search(collection, mid + 1, high, value)
    elif value < mid_value:
        return insert_value_search(collection, low, mid - 1, value)
    else:
        return mid


arr = [i for i in range(100)]

retVal = insert_value_search(arr, 0, len(arr) - 1, 1000)
print(retVal)