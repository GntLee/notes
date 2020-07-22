
# KMP�㷨
	* ������������, �ж��ַ������Ƿ����ĳ���Ӵ���, �����, ���ص�һ�γ��ֵ�λ��
	* KMP(��ëƬ)�������˵����Ͽ�ͷ, ��Ϊ���������Ϸ����





# ����ƥ���ʵ��
	def violenceMatch(source, target):

		slen = len(source)
		tlen = len(target)
		
		s = 0
		t = 0
		
		while s < slen and t < tlen:
			if source[s] == target[t]:
				s += 1
				t += 1
			else:
				s = s - (t - 1)
				t = 0
		
		if t == tlen:
			return s - t
		return -1
	
# KMP��ʵ��
	# ��ȡһ���ַ����Ĳ���ƥ��ֵ��
	def kmpNext(dest):
		retVal = [0 * i for i in range(len(dest))]
		retVal[0] = 0 # ����ǳ���Ϊ1������ƥ��ֵ����0
		i = 1
		j = 0
		while i < len(dest):
			# kmp�㷨����
			while j > 0 and not dest[i] == dest[j]:
				j = retVal[j - 1]
			# ����ƥ��ֵ����Ҫ + 1
			if dest[i] == dest[j]:
				j += 1
			retVal[i] = j
			i += 1
		return retVal

	# KMP����
	"""
		@param source:    Դ�ַ���
		@param target:    �Ӵ���     
		@param arr:       �Ӵ����Ĳ���ƥ��ֵ��
	"""
	def kmpMatch(str1, str2):
		arr = kmpNext(str2)
		j = 0
		for i, char in enumerate(str1):
			# kmp�㷨����
			while j > 0 and not char == str2[j]:
				j = arr[j - 1]
			if char == str2[j]:
				j += 1
			if j == len(str2):
				return i - j + 1
		return -1

	s1 = 'BBC ABCDAB ABCDABCDABDE'
	s2 = 'ABCDABD'

	print(kmpMatch(s1, s2))