
# ̫���ˣ���˵

def bubble(arr):
    length = len(arr)
    for i in range(length):
        for x in range(i, length):
            if arr[i] > arr[x]:
                temp = arr[i]
                arr[i] = arr[x]
                arr[x] = temp


arr = [5, 6, 8, 7, 6, 3, 2, 4, 0, 1, 9]
bubble(arr)
print(arr)



# ð��������Ż�
	* �������ĳһ�ε����������û�з�������, ������ǰ������������

	def bubble(arr):

		# ���ñ�ʶ���Ƿ���������
		flag = False

		length = len(arr)
		for i in range(length):
			for x in range(i, length):
				if arr[i] > arr[x]:
					# ����������
					flag = True
					temp = arr[i]
					arr[i] = arr[x]
					arr[x] = temp

			if not flag:
				# û�з������������Ѿ��������������
				break
			else:
				# ���ñ�ʶ
				flag = False


	arr = [5, 6, 8, 7, 6, 3, 2, 4, 0, 1, 9]
	bubble(arr)
	print(arr)