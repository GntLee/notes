--------------------
��� - state & props
--------------------
	# ����
		state
			* ������ڲ�ά��������
			* ���ĸ���ֻ�����Լ����

		props
			* �Ǹ�������ҵ�����
			* ��ֻ�ܶ�, ���ܸ�, �޸Ĳ���ֻ�ܵ��ø�����Ļص��������޸�
	
	# �޸Ĳ���, ����Ҫ���ú���, ����ֱ�Ӹ�ֵ
		this.state.key = value;			// ������Ч
		this.setState({key: value});	// ��ȷ
	
	# state�ĸ��¿������첽��
		* ����޸ĵ�����, �����ھɵ�����, ���ܻ����BUG
			this.setState({
			  counter: this.state.counter + this.props.increment,
			});
				
		* Ҫ����������, ������ setState() ����һ������������һ������
			this.setState((state, props) => {
				return {
					counter: state.counter + props.increment
				}
			});

			* ��һ���޸ĺ��state��props����Ϊ����, 
			* ����ֱ���޸Ĳ���, ����һ��������ֵ�� state ����
	
	# ��������Ҳ��ʵ����Ψһ��������


--------------------
��� - �������ڷ���
--------------------
	componentDidMount();
		* ������Ѿ�����Ⱦ�� DOM �к�����
		* һ��������ͨ����������, ��ȡ������

		* ����ֱ�ӵ��� setState(), ��������������Ⱦ, ����Ⱦ�ᷢ���������������Ļ֮ǰ(��ʹ�� render() ���ε��õ������, �û�Ҳ���ῴ���м�״̬)
		* ����ʹ�ø�ģʽ, ��Ϊ���ᵼ����������, ��û����� constructor() �г�ʼ�� state

	componentWillUnmount();
		* �������ж�ؼ�����֮ǰֱ�ӵ���
		* ������������Դ, ��Ӧ�õ��� setState(), ��Ϊ���������Զ����������
		* ���ʵ��ж�غ�, ����Զ�����ٹ�����
	
	componentDidCatch(error, info)
		error	�׳��Ĵ���
		info	���� componentStack key �Ķ���, ���а����й�������������ջ��Ϣ

		* ��Ⱦ�����쳣��ʱ������
		* ��Ӧ�����ڼ�¼����֮������
	
	getSnapshotBeforeUpdate(prevProps, prevState)
		* ���һ����Ⱦ���(�ύ�� DOM �ڵ�)֮ǰ����
		* ��ʹ��������ڷ�������֮ǰ�� DOM �в���һЩ��Ϣ(����, ����λ��), ���������ڵ��κη���ֵ����Ϊ�������ݸ� componentDidUpdate()

	shouldComponentUpdate(nextProps, nextState)
		prevProps	����֮���props
		prevState	����֮���state

		* �����ݱ�����֮ǰ����, ���ݷ���ֵ�ж� React ���������Ƿ��ܵ�ǰ state �� props ���ĵ�Ӱ��
		* Ĭ����Ϊ�� state ÿ�η����仯�������������Ⱦ(û�¶���ȥϹjb��), ����ֵĬ��Ϊ true

		* ����˷������� false, �򲻻���� componentDidUpdate(), render(); 
		* ���� false ��������ֹ������� state ����ʱ������Ⱦ

		* ���Կ����Ƿ�Ҫ������Ⱦ, �����Ż����ܵ�һ���ֶ�

		* �״���Ⱦ��ʹ�� forceUpdate() ʱ������ø÷���

	componentDidUpdate(prevProps, prevState, snapshot)
		prevProps	����֮ǰ��props
		prevState	����֮ǰ��state
		snapshot	����û�ʵ���� getSnapshotBeforeUpdate() ����, ��ô�ò������Ǵ˷����ķ���ֵ, ��֮Ϊ undefined

		* �����ݸ��º�ᱻ��������
		* �״���Ⱦ����ִ�д˷���

		* ������ componentDidUpdate() ��ֱ�ӵ��� setState(),��ע�������뱻������һ�����������
		* ����ᵼ����ѭ��(����������->�����¼�->����������->�����¼�.....)
			componentDidUpdate(prevProps) {
			  // �����÷�����Ҫ���ǱȽ� props����
			  if (this.props.userID !== prevProps.userID) {
				this.fetchData(this.props.userID); // ���¼�������
			  }
			}


	
	static getDerivedStateFromError(erro)
		* ��Ⱦ�����쳣��ʱ������
		* ����Ϊ�׳����쳣, ������һ��ֵ�Ը��� state
		* ��Ӧ����������ui

	static getDerivedStateFromProps(props, state)
		
	
	# �����ʵ�������������� DOM ��ʱ�����������ڵ���˳�����£�
		constructor()
		static getDerivedStateFromProps()
		render()
		componentDidMount()
	
	# ������� props �� state �����仯ʱ�ᴥ������, ������µ��������ڵ���˳������
		static getDerivedStateFromProps()
		shouldComponentUpdate()
		render()
		getSnapshotBeforeUpdate()
		componentDidUpdate()

--------------------
��� - ��������
--------------------
	constructor(props)
		* ���캯��
		* �������ʼ�� state �򲻽��з�����, ����ҪΪ React ���ʵ�ֹ��캯��
		* ������ state �Ĳ���, ֻ���ǳ�ʼ����ֵ
			this.state = {k: v}
		
		* ���⽫ props ��ֵ���Ƹ� state, ����һ�������Ĵ���, ��Ȼ�Ӵ�������˵����û����
	
	setState(updater, [callback])
		* ���캯���в���ִ���������

		* callbackΪ��ѡ�Ļص�����, ������ setState ��ɺϲ���������Ⱦ�����ִ��(����ʹ��componentDidUpdate()������)


	forceUpdate(callback)
		* forceUpdate() ����ʹ������� render() ����
		* �˲���������������� shouldComponentUpdate(), ����������ᴥ���������������ڷ���, ���� shouldComponentUpdate() ����
		* �����Ƿ����仯��React �Խ�ֻ���� DOM
		* Ӧ�ñ���ʹ�� forceUpdate(), ������ render() ��ʹ�� this.props �� this.state

--------------------
��� - ������
--------------------
	defaultProps 
		* ��Ϊ Class ������Ĭ�� props
		* һ������ props δ����, ���ֲ���Ϊ null �����
		class CustomButton extends React.Component {
		}
		CustomButton.defaultProps = {
		  color: 'blue'
		};
		
		<CustomButton /> ; // props.color ������Ϊ 'blue'
		 <CustomButton color={null} /> ; // props.color �������� null
	
	displayName
		* ������, �����������, û��

--------------------
��� - ������this
--------------------
	# �ڹ��캯�����Ƚ��а�
		constructor(props){
			super(props);
			this.handler = this.handler.bind(this);
		}
		handler (event){
			console.log(this);
		}
		<div onClick={this.handler}></div>

	# �ڵ��õ�ʱ����а�
		handler (event){
			console.log(this);
		}
		<div onClick={this.handler.bind(this)}></div>
	
	# д�ɼ�ͷ����(es6�Ķ���)
		handler = (event) => {
			console.log(this);
		}
		<div onClick={this.handler}></div>
	
	# ʹ�ü�ͷ��������
		handler (e) {
			console.log(event, id, this);
		}
		<button onClick={(e) => this.handler(e)}>
		</button>

--------------------
��� - ����������
--------------------
	# ʹ��lambdaִ��
		handler (e, arg1) {
			console.log(event, arg1);
		}
		<button onClick={(e) => this.deleteRow(e, '��������')}>Delete Row</button>

		* lambda �Ĳ������� event �¼�����, ���԰��������������λ�ô��ݸ�Ŀ�꺯��
	
	# ʹ�� bind ִ��
		handler (arg, event) {
			console.log(arg, event);
		}
		<button onClick={this.deleteRow.bind(this, id)}>Delete Row</button>
		
		* �¼�����, Ĭ�������һ��������λ��