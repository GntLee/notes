# ��������
	* �����������Է���ʽ�����ֳ�Ϊ��Ͱ����
	* ͨ����ֵ�ĸ���λ��ֵ����Ҫ�����Ԫ�ط��䵽ĳЩͰ�У��ﵽ���������

	* ���������������ȶ��Ե����򣬻���������Ч�ʸߵ�����

	* ����������Ͱ�������չ��1887�꣬�ն���-����������
	* ������λ�и�ɲ�ͬ�����֣�Ȼ����ÿ��λ���ֱ�Ƚ�

	* ����и�����������ʹ�û�������
		- ���������������Ҫȥִ�У�ȡ����ֵ�ȵȲ������Ƚ��鷳



# ����ʵ��

def radixSort(arr):
    # �õ����ֵ
    maxVal = arr[0]
    for x, z in enumerate(arr):
        if z > maxVal:
            maxVal = z
    # ���ֵ��λ��
    maxLength = len(str(maxVal))

    bucket = []
    for i in range(10):
        bucket.append([0 for i in range(len(arr))])

    bucketElementCounts = [0 for i in range(10)]

    n = 1
    for l in range(maxLength):
        for i, v in enumerate(arr):
            digitOfElement = v // n % 10
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = v
            bucketElementCounts[digitOfElement] += 1
        index = 0

        for i, v in enumerate(bucketElementCounts):
            if not v == 0:
                for _i in range(v):
                    arr[index] = bucket[i][_i]
                    index += 1
            bucketElementCounts[i] = 0
        n *= 10
    print(arr)


arr = [53, 3, 542, 748, 14, 214]

radixSort(arr)
