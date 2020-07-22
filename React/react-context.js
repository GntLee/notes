------------------------------
context
------------------------------
	# Context �ṩ��һ������Ϊÿ������ֶ���� props, �������������������ݴ��ݵķ���
		* ���͵ĳ�������: ��¼����û���Ϣ, ������Ϣ, ��������������
	
	
	# ͨ�� React.createContext ����һ�� Context
		const MyContext = React.createContext(defaultValue);

			* ͨ������, ָ��һ��Ĭ��ֵ
			* �� React ��Ⱦһ����������� Context ��������, ����������������������������Ǹ�ƥ��� Provider �ж�ȡ����ǰ�� context ֵ
				<MyContext.Provider value={/* ĳ��ֵ */}>

			* ֻ�е��������������û��ƥ�䵽 Provider ʱ, �� defaultValue �����Ż���Ч
			* �� undefined ���ݸ� Provider �� value ʱ, ��������� defaultValue ������Ч

			* ��� Provider Ҳ����Ƕ��ʹ�ã����ĻḲ����������
			* �� Provider �� value ֵ�����仯ʱ, ���ڲ������������������������Ⱦ
			* Provider �����ڲ� consumer ������������� shouldComponentUpdate ����, ��˵� consumer ���������������˳����µ������Ҳ�ܸ���
	
	# �������, ͨ�� Class.contextType ������ Context
		class MyClass extends React.Component {
		  componentDidMount() {
			let value = this.context;
			/* �����������ɺ󣬶�ȡ�� MyContext �����ֵ */
		  }
		  componentDidUpdate() {
			let value = this.context;
			/* ... */
		  }
		  componentWillUnmount() {
			let value = this.context;
			/* ... */
		  }
		  render() {
			let value = this.context;
			/* ���� MyContext �����ֵ������Ⱦ */
		  }
		}
		// ��������� contextType Ϊָ���� Context
		MyClass.contextType = MyContext;
		
		* ��������� contextType Ϊָ���� Context
		* ���������ʹ�� this.context ��������� Context �ϵ��Ǹ�ֵ, �������κ����������з��ʵ���, ���� render ����

		* Ҳ����ʹ��ʵ���Ե� es6 �﷨������ Context
			class MyClass extends React.Component {
			  static contextType = MyContext; // ͨ�� static ��������� contextType Ϊָ���� Context
			  render() {
				let value = this.context;
				/* �������ֵ������Ⱦ���� */
			  }
			}
	
	
	# Consumer �������ֵ�仯
		* ����ܶ��ĵ� context ���, �������ں���ʽ(������Ϊ��Ԫ��)�������ɶ��� context
			<MyContext.Consumer>
			  {value => /* ���� context ֵ������Ⱦ*/}
			</MyContext.Consumer>

		* ����������յ�ǰ�� context ֵ, ����һ�� React �ڵ�
		* ���ݸ������� value ֵ��ͬ���������������� context ����� Provider �ṩ�� value ֵ
		* ���û�ж�Ӧ�� Provider, value ������ͬ�ڴ��ݸ� createContext() �� defaultValue
	
		* �ڴ���Context��ʱ��, �����Ƕ���, �����ж��ٸ�, ��ô�� Consumer �ص�����������, ���Ծ��ж��ٸ�(�⹹��ֵ)
		* �����������ַ�ʽ, �����Ƕ�׺����������и��� context
			const ThemeContext =  React.createContext({ // ������
				theme: themes.light,		
				toggleTheme: () => {}	
			});
			<ThemeContext.Consumer>
            {
                ({theme, toggleTheme}) => { // ������
                    return (
                        <button onClick={toggleTheme} style={{backgroundColor: theme.background}}>Toggle Theme</button>
                    )
                }
            }
			</ThemeContext.Consumer>
	
	# �ܽ�
		* ����
			ʹ�� React.createContext(defaultValue); ����Ĭ��ֵ�� Context
			Provider	������дĬ�ϵ�Contextֵ
		
		* ʹ��
			�������������: contextType ֵΪָ���� Context, �������ͨ�� this.context ��ȡ��ֵ
			ͨ�� Consumer, ���庯��������ȡ��ֵ



------------------------------
context - ע������
------------------------------
	# context ��ʹ�òο���ʶ(reference identity)��������ʱ������Ⱦ, ������ܻ���һЩ����
		* �� provider �ĸ������������Ⱦʱ, ���ܻ��� consumers ����д����������Ⱦ

			class App extends React.Component {
			  render() {
				return (
				  <MyContext.Provider value={{something: 'something'}}>
					<Toolbar />
				  </MyContext.Provider>
				);
			  }
			}

			* ��ÿһ�� Provider ����Ⱦʱ, ���µĴ��������Ⱦ��������� consumers ���, ��Ϊ value �������Ǳ���ֵΪ�µĶ���
	
	# Ϊ�˷�ֹ�������, �� value ״̬���������ڵ�� state ��
		class App extends React.Component {
		  constructor(props) {
			super(props);
			this.state = {
			  value: {something: 'something'},
			};
		  }

		  render() {
			return (
			  <Provider value={this.state.value}>
				<Toolbar />
			  </Provider>
			);
		  }
		}

	

------------------------------
context - api
------------------------------
	React.createContext

	Context.Provider
	Class.contextType
	Context.Consumer
	Context.displayName
