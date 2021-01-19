--------------------------------
Effect Hook
--------------------------------
	
	function App (props){
		const[count, setCount] = React.useState(0);
		React.useEffect(() => {    // �൱�� componentDidMount �� componentDidUpdate:
			window.document.title = `����� ${count} ��`;
			return () => {
				console.log('ÿ�θ��¶���ִ����');
			}
		});
		return (
			<div>
				<div>����ˣ� { count } �� </div>
				<button onClick={ () => setCount(count + 1) }>�����</button>
			</div>
		)
	}
	
	# ���� class ����е� componentDidMount��componentDidUpdate ������ͬ����;��ֻ�������ϲ�����һ�� API	
		* ���ڵ�һ����Ⱦ֮���ÿ�θ���֮�󶼻�ִ��
		* ÿ������ effect ��ͬʱ��DOM ���Ѿ�������ϡ�

		* �� componentDidMount �� componentDidUpdate ��ͬ��ʹ�� useEffect ���ȵ� effect �������������������Ļ������Ӧ�ÿ�������Ӧ����
		* ���������£�effect ����Ҫͬ����ִ�С��ڸ�������£�����������֣����е����� useLayoutEffect Hook ��ʹ�ã��� API �� useEffect ��ͬ��
	
	# ��� effect ����һ��������React ������ִ���������ʱ���������� componentWillUnmount ������ͬ����;
		* ���� effect ��ѡ��������ơ�ÿ�� effect �����Է���һ�����������
		* effect ��ÿ����Ⱦ��ʱ�򶼻�ִ�С�������componentWillUnmountֻ������ڱ����ٵ�ʱ��Ż�ִ��һ�Σ�
			React.useEffect(() => {
				return () => {
					console.log('ÿ�θ��¶���ִ����');
				}
			});
		
		* �������Ҫ����߼�����ô�Ͳ��÷����������
	
	
	# ͨ������ Effect ���������Ż�
		* ÿ����Ⱦ��ִ���������ִ�� effect ���ܻᵼ����������
		* �� class ����У�����ͨ���� componentDidUpdate ����Ӷ� prevProps �� prevState �ıȽ��߼����


		* �� useEffect �� Hook API �С����ĳЩ�ض�ֵ����������Ⱦ֮��û�з����仯������֪ͨ React ������ effect �ĵ���
		* ֻҪ����������Ϊ useEffect �ĵڶ�����ѡ��������
			React.useEffect(() => {
			  document.title = `You clicked ${count} times`;
			}, [count]);	// ���� count ����ʱ����
		
		* ����������ж��Ԫ�أ���ʹֻ��һ��Ԫ�ط����仯��React Ҳ��ִ�� effect��
		
		* �����ִ��ֻ����һ�ε� effect������������غ�ж��ʱִ�У������Դ���һ�������飨[]����Ϊ�ڶ���������
		* ��͸��� React �� effect �������� props �� state �е��κ�ֵ����������Զ������Ҫ�ظ�ִ�С�


	# �ܽ�
		* React.useEffect(); �������βκ���, �������������, ���º�ִ��
		* �������βκ���, ������һ������, ��ô��������������ÿ�θ��º�ִ��