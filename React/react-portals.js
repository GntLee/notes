
------------------------
Portal
------------------------
	# Portal �ṩ��һ�ֽ��ӽڵ���Ⱦ�������ڸ��������� DOM �ڵ������ķ���
		ReactDOM.createPortal(child, container)

		* ��һ��������child�����κο���Ⱦ�� React ��Ԫ�أ�����һ��Ԫ�أ��ַ����� fragment
		* �ڶ���������container����һ�� DOM Ԫ�ء�
	
		render() {
		  // React ��*û��*����һ���µ� div����ֻ�ǰ���Ԫ����Ⱦ�� `domNode` �С�
		  // `domNode` ��һ���������κ�λ�õ���Ч DOM �ڵ㡣
		  return ReactDOM.createPortal(
			this.props.children, // React 
			domNode				//  DOM�ڵ�
		  );
		}
	
		
		* ������������������� overflow: hidden �� z-index ��ʽʱ������Ҫ������ܹ����Ӿ��ϡ��������������������磬�Ի����������Լ���ʾ��
	

------------------------
Portal �¼�ð��
------------------------
	* portal ���Ա������� DOM ���е��κεط������������棬����Ϊ����ͨ�� React �ӽڵ���Ϊһ��
	* portal �Դ����� React ���� ���� DOM �� �е�λ���޹أ���ô�������ӽڵ��Ƿ��� portal���� context �����Ĺ������Զ��ǲ���ġ�
	* �����¼�ð�ݡ�һ���� portal �ڲ��������¼���һֱð�������� React �������ȣ�������ЩԪ�ز����� DOM �� �е�����



	import React from 'react';
	import ReactDOM from 'react-dom';

	// const element = document.querySelector('#root');
	// -------------------
	const appRoot = document.getElementById('app-root');
	const modalRoot = document.getElementById('modal-root');

	class Modal extends React.Component {
		constructor(props){
			super(props);
			this.el = document.createElement('div');
		}
		componentDidMount(){
			// �� Modal ��������Ԫ�ر����غ�
			// ��� portal Ԫ�ػᱻǶ�뵽 DOM ���У�
			// ����ζ����Ԫ�ؽ������ص�һ������� DOM �ڵ��С�
			// ���Ҫ��������ڹ���ʱ�������̽��� DOM ����
			// �������һ�� DOM �ڵ㣬
			// �����ں���ڵ���ʹ�� ��autoFocus����
			// ������� state �� Modal �У�
			// ���� Modal ������ DOM ���в�����Ⱦ��Ԫ�ء�
			modalRoot.appendChild(this.el);
		}
		componentWillUnmount(){
			modalRoot.removeChild(this.el);
		}
		render (){
			return ReactDOM.createPortal(
				this.props.children,
				this.el
			);
		}
	}  

	function Child (props){
		return (
			// �����ť�ĵ���¼���ð�ݵ���Ԫ��
			// ��Ϊ����û�ж��� 'onClick' ����
			<div>
				<button>Click</button>
			</div>
		)
	}

	class Parent extends React.Component {
		constructor(props){
			super(props);
			this.state = {
				clicks: 0
			}
		}
		handlerClick = () => {
			 // ����Ԫ����İ�ť�����ʱ��
			// ������ᱻ�������¸�Ԫ�ص� state��
			// ��ʹ�����ť�� DOM �в���ֱ�ӹ����ĺ��
			this.setState((state) => {
				return {
					clicks: state.clicks + 1
				}
			});
		}
		render (){
			return (
				<div onClick={this.handlerClick}>
					<p>�������: {this.state.clicks}</p>
					<Modal>
						<Child />
					</Modal>
				</div>
			)
		}
	}


	ReactDOM.render(<Parent />, appRoot);

	* ͨ��������˵, ReactDOM.createPortal �ѽڵ���ص���������node�ڵ�
	* ��Ȼhtml�ṹ��, node�ڵ�֮�䲻�Ǹ��ӹ�ϵ, ������������Ǹ��ӹ�ϵ, ��������¼�һ����ð�ݵ������������


