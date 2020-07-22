class NumArray(object):

    def __init__(self, arr):
		# �½�һ������,���洢��
        self.sum = [0 for i in range(0, len(arr) + 1)]
        for i in range(1, len(arr) + 1):
            self.sum[i] = self.sum[i - 1] + arr[i - 1]
    
    def sumRange(self, start, end):
        return self.sum[end + 1] - self.sum[start]


# Ҳ����ʹ���߶����������ݽṹ�����