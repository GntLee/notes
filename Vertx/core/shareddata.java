---------------------------
shareddata
---------------------------
	# �������ݵķ��ʽӿڣ������̰߳�ȫ��
		SharedData sharedData = vertx.sharedData();

	# ���ĵ����
		synchronous maps (local-only)
		asynchronous maps
		asynchronous locks
		asynchronous counters
	
	# Map�ĸ�ʽ��2��
		LocalMap	ͬ����д
		AsyncMap	�첽��д
	
---------------------------
map
---------------------------
	# Local maps
		* ������ͬһ�� Vert.x ʵ���еĲ�ͬ�¼�ѭ�����粻ͬ�� verticle��֮�䰲ȫ�ع������ݡ�
			LocalMap<String, String> localMap = sharedData.getLocalMap("m1");
			localMap.put("name", "vvvvvv");
			
			localMap = sharedData.getLocalMap("m1");
			System.out.println(localMap.get("name"));
		
		* �����ŵ�����
			* ���ɱ������ ���� String��boolean�����ȵ�

			* ʵ���� Shareable �ӿڵ����� ������Buffer��JSON���飬JSON���󣬻���д��Shareableʵ���ࣩ��
			* ��������£���/ֵ�������ƣ�Ȼ���ٷŵ�Map�С�
				map2.put("eek", Buffer.buffer().appendInt(123)); // Buffer��������ӵ�Map֮ǰ����
		
		
	# �첽����� maps
		* �������ݱ��ŵ� map �У����ӱ��ػ��κ������ڵ��ȡ��
		* �� Vert.x �Ǽ�Ⱥģʽʱ, �Ž�map�����ݣ��ӱ����Լ��Ӽ�Ⱥ�е�������Ա���ﶼ���Է��ʵ���



---------------------------
lock
---------------------------
	# ������
	# ������

---------------------------
counter
---------------------------
	# ���ؼ�����
	# �첽������
