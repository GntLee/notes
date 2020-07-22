
# collection ����Ϊ�����������������,�����ڷ��ظ���

# ʹ��ѭ��ʵ��
def binary_search(collection, value):
    low = 0
    high = len(collection) - 1
    while low <= high:
        mid = (low + high) // 2
        item = collection[mid]
        if item > value:
            high = mid - 1
        elif item < value:
            low = mid + 1
        else:
            return mid
    return -1

# ʹ�õݹ�ʵ��
def binary_search(collection, left, right, value):
    if left > right:
        return -1
    mid = left + (right - left) // 2
    mid_val = collection[mid]
    if value > mid_val:
        return binary_search(collection, mid + 1, right, value)
    elif value < mid_val:
        return binary_search(collection,left, mid - 1, value)
    else:
        return mid


arr = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

ret = binary_search(arr, 0, len(arr) - 1, 15)

print(ret)

# ���ؾ�����ͬԪ����ֵ�У�ָ��ֵ�������±�

def binary_search(collection, left, right, value):
    if left > right:
        return []
    mid = left + (right - left) // 2
    mid_val = collection[mid]
    if value > mid_val:
        return binary_search(collection, mid + 1, right, value)
    elif value < mid_val:
        return binary_search(collection,left, mid - 1, value)
    else:
        ret_val = []
        temp = mid - 1
        while True:
            if temp < 0 or not arr[temp] == value:
                break
            ret_val.append(temp)
            temp -= 1

        ret_val.append(mid)

        temp = mid + 1
        while True:
            if temp > len(collection) - 1 or not arr[temp] == value:
                break
            ret_val.append(temp)
            temp += 1

        return ret_val


arr = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9]

ret = binary_search(arr, 0, len(arr) - 1, 9)

print(ret) # [9, 10]





# ��ȡ��λ�����±�,��� start �� end ����Ļ�,  ��ĳЩ�����п��ܻᵼ�����, ��Ҫ��һ�ּ��㷽ʽ
	mid = low + (high - low) // 2
	

# ���֧���޷���λ��������ŵĻ�(Java), ����
	 mid = (low + high) >>> 1;