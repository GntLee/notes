----------------------
�쳣����
----------------------
	# �����2���쳣������
		componentDidCatch(error, info)
			error	�׳��Ĵ���
			info	���� componentStack key �Ķ���, ���а����й�������������ջ��Ϣ

			* ��Ⱦ�����쳣��ʱ������
			* ��Ӧ�����ڼ�¼����֮������
	
		static getDerivedStateFromError(erro)
			* ��Ⱦ�����쳣��ʱ������
			* ����Ϊ�׳����쳣, ������һ��ֵ�Ը��� state
			* ��Ӧ����������ui
	
	# �����ʵ���� �������������ڷ����е�����һ��(������)ʱ, ��ô���ͱ��һ������߽�
		class ErrorBoundary extends React.Component {
		  constructor(props) {
			super(props);
			this.state = { hasError: false };
		  }
		  static getDerivedStateFromError(error) {
			// ���� state ʹ��һ����Ⱦ�ܹ���ʾ������� UI
			return { hasError: true };
		  }
		  componentDidCatch(error, errorInfo) {
			// ��ͬ�����Խ�������־�ϱ���������
			logErrorToMyService(error, errorInfo);
		  }

		  render() {
			if (this.state.hasError) {
			  // ������Զ��彵����� UI ����Ⱦ
			  return <h1>������һЩ����</h1>;
			}

			return this.props.children; 
		  }
		}
		

		<ErrorBoundary>
		  <MyWidget />
		</ErrorBoundary>
	
	# �� React 16 ��, �κ�δ������߽粶��Ĵ��󽫻ᵼ������ React �������ж��

	# ����߽��޷������¼��������ڲ��Ĵ���

		* �����Ҫ���¼��������ڲ��������, ʹ����ͨ�� JavaScript try / catch ���

		class MyComponent extends React.Component {
		  constructor(props) {
			super(props);
			this.state = { error: null };
			this.handleClick = this.handleClick.bind(this);
		  }

		  handleClick() {
			try {
			  // ִ�в��������д�������׳�
			} catch (error) {
			  this.setState({ error });
			}
		  }

		  render() {
			if (this.state.error) {
			  return <h1>Caught an error.</h1>
			}
			return <button onClick={this.handleClick}>Click Me</button>
		  }
		}
