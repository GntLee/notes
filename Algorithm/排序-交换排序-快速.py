# ��������
	* ���������Ƕ�ð�������һ�ָĽ�

	* ͨ��һ�����򣬰�Ҫ��������ݷָ�ɶ�����������
	* ����һ���ֵ��������ݶ�������һ���ֵ��������ݶ�ҪС
	* Ȼ���մ˷����������������ݷֱ���п�������
	* ����������̿��Եݹ���У��Դ˴ﵽ�������ݱ����������


# ����ʵ��

def quickSort(arr, left, right):
    l = left
    r = right
    mid = arr[(left + right) // 2]
    while l < r:
        while arr[l] < mid:
            l += 1
        while arr[r] > mid:
            r -= 1
        if l >= r:
            break

        temp = arr[l]
        arr[l] = arr[r]
        arr[r] = temp

        if arr[l] == mid:
            r -= 1

        if arr[r] == mid:
            l += 1

    if l == r:
        l += 1
        r -= 1

    # ��ݹ�
    if left < r:
        quickSort(arr, left, r)
    # �ҵݹ�
    if right > l:
        quickSort(arr, l, right)


arr = [5, 4, 2, 9, 8, 7, 6, 1, 2, 3, 0]
quickSort(arr, 0, len(arr) - 1)
print(arr)