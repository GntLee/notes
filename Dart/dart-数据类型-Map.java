---------------------------
Map
---------------------------
	# Map ��һ����������ֵ�Ķ���, ����ֵ���������κ����͵Ķ���, ÿ����ֻ����һ��(���߸���ǰ��)

	# ��ʼ��
		var map = {1: 'KevinBlandy', 'name': '123'};

		Map map = {'name': 'KevinBlandy', 1: 456};

		Map map = new Map();
		map['1'] = 'KevinBlandy';
		map[2] = 'Hello';
		print(map);  // {1: KevinBlandy, 2: Hello}
	
		* �ڲ����÷��͵������, map���Դ��ڶ����ͬ�������͵�k/v
	
	# Ԫ�صķ��ʺ��޸�, ͨ��ͨ�� [] ���ʽ
		Map map = new Map();
		map['1'] = 'KevinBlandy';
		map[2] = 'Hello';
		print(map['1']);  // KevinBlandy
		print(map[2]);	// Hello

		* ͨ�� [] ���ʲ����ڵ�Ԫ��, ���� null		
		* Ԫ�ص�key����Ϊ null
			Map map = new Map();
			map[null] = 'foo'; //  foo
			print(map[null]);
	
	# Mapʵ����һЩ����
		length
			* ����Ԫ�صĸ���
	
	# ͨ������<>������map��key��value������
		Map<String, String> map = new Map();
		map[1] = '15'; // Error: A value of type 'int' can't be assigned to a variable of type 'String'.
		
		* ʹ�÷Ƿ���ָ������������������map, �����쳣
			Map<String, String> map = new Map();
			print(map[1]);  // null
		
	
---------------------------
Map ʵ������
---------------------------
---------------------------
Map ��̬����
---------------------------

			