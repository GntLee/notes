# ϣ������
	* ���ǲ������򾭹��Ľ���һ�����°汾, Ҳ����: ��С��������
	* ����������ڵ�����
		* ����Ҫ����������ǽ�С����ʱ�����ƵĴ����������࣬��Ӱ��Ч��
	
	* ����˼��: 
		1. �Ѽ�¼�����±��һ����������
			gap = length / 2
		2. ��ÿһ��ʹ��ֱ�Ӳ��������㷨����
		3. �����������𽥼���, ÿ������Ĺؼ���Խ��Խ��
			gap = gap / 2
		4. ����������1��ʱ��, �����ļ�ǡ�ñ��ֳ�1��, �㷨��ֹ
			if gap = 1 : break
	

# ʹ�ý����ķ�ʽ������ʵ��
def shellSort(arr):
    gab = len(arr) // 2

    while gab > 0:
        for i in range(gab, len(arr)):
            x = i - gab
            while x >= 0:
                if arr[x] > arr[x + gab]:
                    temp = arr[x]
                    arr[x] = arr[x + gab]
                    arr[x + gab] = temp
                x -= gab

        gab = gab // 2


arr = [5, 4, 7, 1, 8, 2, 9, 3, 6, 0]


shellSort(arr)

print(arr)

# ʹ����λ�ķ�ʽ������ʵ�֣�������ߣ�
def shellSort(arr):

    gab = len(arr) // 2

    while gab > 0:
        for i in range(gab, len(arr)):
            # �ӵ�gab��Ԫ�أ�����������ڵ���ֱ�ӽ��в�������
            x = gab
            while x < len(arr):
                j = x
                val = arr[x]
                if arr[j] < arr[j - gab]:
                    while (j - gab) >= 0 and val < arr[j - gab]:
                        arr[j] = arr[j - gab]
                        j -= gab
                    # �ҵ���val�����λ��
                    arr[j] = val
                x += 1
        gab = gab // 2


arr = [5, 4, 7, 1, 8, 2, 9, 3, 6, 0]


shellSort(arr)

print(arr)