


'''
��ά�����ʾ�Թ� ��8�У�7�� [8][7]
Ԫ��: 1 ��ʾǽ
Ԫ��: 0 ��ʾ���Դ���
'''
arr = []

for i in range(8):
    arr.append([])
    for x in range(7):
        arr[i].append(0)


# ��ʼ��
for i in range(7):
    arr[0][i] = 1
    arr[7][i] = 1

for i in range(8):
    arr[i][0] = 1
    arr[i][6] = 1


# ����ǽ
arr[3][1] = 1
arr[3][2] = 1


def setWay(map, i, j):
    '''
    :param map:         ��ͼ
    :param i:       ��ʼ������
    :param j:         ��ʼ������
    :return:            �ҵ����� Ture ��֮���� False

    map[i][j] =
        0 : û���߹�
        1 ��ǽ
        2 ������ͨ��
        3 ���Ѿ��߹������ǲ�ͨ

    �ƶ�����
        �� -> �� -> �� -> ��
    '''
    if map[6][5] == 2:
        return True
    else:
        if map[i][j] == 0: # ��û�߹�
            map[i][j] = 2 # �ٶ��õ������ͨ
            if setWay(map, i + 1, j): # ������
                return True
            elif setWay(map, i, j + 1 ): # ������
                return True
            elif setWay(map, i - 1, j): # ������
                return True
            elif setWay(map, i, j - 1): # ������
                return True
            else:
                map[i][j] = 3   # ��·
                return False
        else:
            return False

setWay(arr, 1, 1)


for i in arr:
    print(i)
