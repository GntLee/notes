# ̰���㷨
	* Ҳ����̰���㷨,
	* �ڶ������������ʱ��, ÿһ������ȡ��õ�ѡ��
	* �Ӷ�"ϣ��"���ռ�����������õ�

	* ̰���㷨, �õ��Ľ����һ������õĽ��
	* ���Ƕ�����Խ�����õĽ��

	* ͨ�׵�������, ̰���㷨���Խ������
	* ���Ǽ�����Ľ������, ��һ�������н����������õ�


# ������ϸ�������
	* ����������еĵ�̨, �Լ���̨�źſ��Ը��ǵĵ���

		K1		����, �Ϻ�, ���
		K2		����, ����, ����
		K3		�ɶ�, �Ϻ�, ����
		K4		�Ϻ�, ���
		K5		����, ����
	
	* ���ѡ�����ٵĹ㲥��̨, �����е��������Խ���(��������)



# ����ʵ��

# �㲥��̨
broadCasts = dict()

# ��������
broadCasts['K1'] = {"����", "�Ϻ�", "���"}
broadCasts['K2'] = {"����", "����", "����"}
broadCasts['K3'] = {"�ɶ�", "�Ϻ�", "����"}
broadCasts['K4'] = {"�Ϻ�", "���"}
broadCasts['K5'] = {"����", "����"}


# ��Ҫ���ǵ����е���
allArea = {"����", "�Ϻ�", "���", "����", "����", "�ɶ�", "����", "����"}

# ѡ��ĵ�̨����
selects = []

# ��ʱ����
tempSet = set()

maxKey = None

while len(allArea) != 0:
    
    # ��δ���ǵ����еĵ���
    for key, value in broadCasts.items():
        
        tempSet.clear()
        
        #  ����
        tempSet = tempSet.union(value)
        # ����
        tempSet = tempSet.intersection(allArea)
        
        # ̰���㷨�ĺ���
        if len(tempSet) > 0 and (maxKey is None or (len(tempSet) > len(broadCasts[maxKey]))):
            maxKey = key
        
    if maxKey is not None:
        selects.append(maxKey)
        allArea = allArea.difference(broadCasts[maxKey])
    
    maxKey = None

# �������еĽ��
print(selects)
    
        

