------------------
jsx
------------------
	# jsx
		* �﷨�Ƕ���, ���ջ�����js����, �� {} �������ִ�������js���ʽ, ���ߺ�������
		* JSX �����Ϊ React.createElement ������ʽ, ���� React ��Ҳ��������

		* �Զ�������, �����Դ�д��ͷ, ��������ᵱ��html��ǩ
	
	# ���ֺϷ��ı��ʽ
		const name = 'React';
		const element = <h1>Hello, {name}</h1>;

		function foo(user) {
		  if (user) {
			return <h1>Hello, {user)}!</h1>;
		  }
		  return <h1>no body</h1>;
		}
	
	
	# ���Ը�ֵ, ���շ�
		const element = <div tabIndex="0"></div>;
		const element = <div tabIndex={index}></div>;
	
	
	# ��������, Null �Լ� Undefined �������
		<div />
		<div></div>
		<div>{false}</div>
		<div>{null}</div>
		<div>{undefined}</div>
		<div>{true}</div>

		* false, null, undefined, and true �ǺϷ�����Ԫ��, �����ǲ����ᱻ��Ⱦ
		* ���ϵ� JSX ���ʽ��Ⱦ�����ͬ

		* ������Ⱦ false,true,null,undefined ��ֵ, ��Ҫ�Ƚ�����ת��Ϊ�ַ���
			<div>
				My JavaScript variable is {String(myVariable)}.
			</div>

	# propsĬ��ֵΪtrue
		<MyTextBox autocomplete />
		<MyTextBox autocomplete={true} />

		* �������ֻ����, û��ֵ, ��Ĭ��Ϊtrue, ������һ��
	
	# ����չ��
		function App1() {
			return <Greeting firstName="Ben" lastName="Hector" />;
		}
		function App2() {
			const props = {firstName: 'Ben', lastName: 'Hector'};
			return <Greeting {...props} />;
		}

		* es6ͦ�򵥵Ķ���, �⹹��ֵ
		
		* ���Թ��˵�ĳЩ������
			const { kind, ...other } = props;
			return <button {...other} />;  // button��keind�����������������
		
		* �� children ����, ������ͨ�����ַ�ʽ������
			function Foo(props){
				return <div {...props}></div>
			}
			ReactDOM.render(<Foo>Hello</Foo>, element); // <div>Hello</div>

------------------
jsx - ������Ⱦ
------------------
	# �������ض���������Ⱦ������ React Ԫ��
		{ true &&
			<div>Hello</div>
		}

		*  && ֮ǰ�ı��ʽ������booleanֵ,(����������0, Ҳ�ᴥ����Ⱦ)
	
	# ���ӵ�����
		* ��js�������
	
	# ��Ŀ����
		{user.length > 0
			? <div>{user.length} ����</div>
			: <div>û����</div>
		}
	
	# ��ֹ������Ⱦ, ���� null
		function User(props){
			if (props.user){
				return <h1>{props.user}</h1>
			}
			return null;
		}

		* ���� null ������Ӱ���������������, �ñ����õĻ��ǻᱻ����


------------------
jsx - ѭ����Ⱦ
------------------
	# ʹ�� map(item, index) ���б���
		<ul>
			{val.map((item, index) => {
				return <li key={index}>{item}</li>
			})}
		</ul>
	

------------------
jsx - ��Ԫ��
------------------
	# ͨ�� props.children ���Է�����Ԫ��
		function App(props){
		  return <div>{props.children}</div>
		}
		<App>Helo</App>
	
	# ��Ԫ��Ҳ����һ������
		function App(props){
		  const val = []
		  for (let x = 0 ;x < 10 ;x ++){
			val.push(props.children(x));
		  }
		  return <ul>{val.map((item, index) => {
			return <li key={index}>{item}</li>
		  })}</ul>
		}
		<App>{(number) => number * 2}</App>
			
