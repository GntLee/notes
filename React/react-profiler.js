------------------------
Profiler
------------------------
	# ���ڲ��Ի����еĶ���, �����������ܵ�
		* ����������CPU, �ڴ�����, �����Ͻ�������������ʹ��
	
	# �÷�
		render(
		  <App>
			<Profiler id="Navigation" onRender={callback}>
			  <Navigation {...props} />
			</Profiler>
			<Main {...props} />
		  </App>
		);

		
		* ʹ�� Profiler ���, ������Ҫ���������(�������)
		* ����ʹ�ö�� Profiler ������ͬ��λ��
		* Ҳ����Ƕ�׷���, ͬһ��������еĲ�ͬ�����
	
	# �ص� 
		* Profiler ��Ҫһ�� onRender ������Ϊ����
		* React ���� profile ��������������κ���� ���ύ�� һ�����µ�ʱ������������
		* ���Ĳ�����������Ⱦ��ʲô�ͻ����˶�á�

		function onRenderCallback(
		  id, // �����ύ�� Profiler ���� ��id��
		  phase, // "mount" �����������ռ��أ� ���� "update" �����������Ⱦ�ˣ�֮һ
		  actualDuration, // ���θ��� committed ���ѵ���Ⱦʱ��
		  baseDuration, // ���Ʋ�ʹ�� memoization ���������Ⱦ����������Ҫ��ʱ��
		  startTime, // ���θ����� React ��ʼ��Ⱦ��ʱ��
		  commitTime, // ���θ����� React committed ��ʱ��
		  interactions // ���ڱ��θ��µ� interactions �ļ���
		) {
		  // �ϼƻ��¼��Ⱦʱ�䡣����
		}
		
		id 
			 * string - �����ύ�� Profiler ���� id�� ����ж�� profiler�����������ֱ�������һ���ַ����ˡ��ύ����
		phase
			* "mount" | "update" - �ж���������ĵ�һ��װ�����������Ⱦ�������� props��state ���� hooks �ı����������Ⱦ��
		actualDuration
			* number - ���θ�������Ⱦ Profiler �������Ӵ��ϻ��ѵ�ʱ�䡣 �����ֵ����ʹ�� memoization ֮���ܱ��ֵö�á�
			* ������ React.memo��useMemo��shouldComponentUpdate���� ��������£������Ӵ�ֻ�����ض��� prop �ı������Ⱦ��������ֵӦ���ڵ�һ��װ��֮�������½���
		baseDuration
			* number - �� Profiler �������һ��ÿһ����� render �ĳ���ʱ�䡣 ���ֵ������������Ⱦʱ�䡣�����統���ǵ�һ�μ��ػ��������û��ʹ�� memoization����
		startTime
			* number - ���θ����� React ��ʼ��Ⱦ��ʱ�����
		commitTime
			* number - ���θ����� React commit �׶ν�����ʱ����� ��һ�� commit �����ֵ�����е� profiler ֮���ǹ���ģ����Խ����ǰ�����顣
		interactions
			* Set - �����±��ƶ�ʱ����interactions�� �ļ��ϻᱻ׷�١������統 render ���� setState ������ʱ����
			* ������ʶ���������ʲô����ģ��������׷�ٸ��µ� API ��Ȼ��ʵ�����ʵ�
