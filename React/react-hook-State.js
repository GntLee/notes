--------------------------------
State Hook
--------------------------------
	function App (props){
		const[count, setCount] = React.useState(0);  // [0, function(fiber, queue, action)){.....}]
		return (
			<div>
				<div>����ˣ� { count } �� </div>
				<button onClick={ () => setCount(count + 1) }>�����</button>
			</div>
		)
	}

	
	# ͨ��Ψһ�Ĳ���, ����һ����ʼ��ֵ, ��������������
	# useState �᷵��һ��ֵ: ��ʼֵһ���������ĺ���
	# ������һ������ж��ʹ�� State Hook
		* ��ε��� useState ��ʱ���ܱ�֤ÿ����Ⱦʱ���ǵĵ���˳���ǲ����

	#  state ֻ������״���Ⱦ��ʱ�򱻴���
		* ����һ��������Ⱦʱ��useState ���ظ����ǵ�ǰ�� state
	
	