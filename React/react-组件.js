--------------------
���
--------------------
	# �������
		function Welcome(props) {
		  return <h1>Hello, {props.name}</h1>;
		}

		* ��������Ҫ��д
	
	# �����
		class Foo extends React.Component {
		  constructor(props){
			super(props);
		  }
		  render (){
			return (
			  <div>
				${this.props.content}
			  </div>
			)
		  }
		}


----------------------------	
Fragments
----------------------------
	# ����� render, ֻ�ܷ���һ���ڵ�, ����һ������Ҫ���ض��, ���ֲ�����html�ڵ�, ���� Fragments
		render (){
			return (
				<React.Fragment>
					<td>Hello</td>
					<td>World</td>
				</React.Fragment>
			)
		}

		* Fragment ������ key ������
	 
	# ����ʹ�ö��﷨ <> </>
		render (){
			return (
				<>
					<td>Hello</td>
					<td>World</td>
				</>
			)
		}

		* ע��, ���﷨��֧�� key ����
		

----------------------------	
React.PureComponent
----------------------------
	# ����� shouldComponentUpdate(nextProps, nextState) ����
		* ʹ����Ƚ�, ������ݱ��޸���, �ͷ��� true, ����UI
		* ���ܻ����һЩ�����ϵ�����

	#  React.PureComponent ����������д shouldComponentUpdate()
		* ����д�� shouldComponentUpdate() ��ʵ��
		* ʹ��ǳ�Ƚ�, ���ǿ��ܻ��������������, �ر���  state �� props ���ݽ⹹���ڸ��ӵ������
	

----------------------------	
���ɱ������
----------------------------
	# ����һЩ����: ����, ����, Ӧ�ñ�����Ĳ���
	# ��������
		handleClick() {
		  this.setState(state => ({
			words: [...state.words, 'marklar'],
		  }));
		};
		
		* ֱ�Ӵ���һ���µ�, ʹ�ý⹹��ֵ�Ѿ�������ӽ�ȥ
		* ������µ�����
	
	# ���ڶ���
		function updateColorMap(colormap) {
		  return Object.assign({}, colormap, {right: 'blue'});
		}

		* ����ʹ�� ES6�� Object.assign({}, o1, o2); �Ѷ����������Ժϲ�Ϊһ��
		* ������ͬ�����Ը���ǰ���

