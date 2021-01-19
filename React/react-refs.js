-------------------
refs
-------------------
	
	# �����Ĵ���, ��, ����
		class MyCompnent extends React.Component {
		  constructor(props){
			super(props);
			// ����ref
			this.myRef = React.createRef();
		  }
		  onClick = () => {
			// ͨ�� ref��current���ԣ���ȡ���󶨵�Ԫ��
			const node = this.myRef.current; 
			console.log(node);  // <div>REF</div>
		  }
		  render (){
			return (
			  // ��divԪ�ص�ref����
			  <div ref={this.myRef} onClick={this.onClick}>
				REF
			  </div>
			)
		  }
		}

	# ref ��ֵ���ݽڵ�����Ͷ�������ͬ
		* �� ref �������� HTML Ԫ��ʱ��current����	��DOMԪ��
			<div ref={this.myRef1} ></div> // <div>....

		* �� ref ���������Զ��� class ���ʱ��current�����������ʵ�� 
			<Foo ref={this.myRef2}></Foo>  //Foo {....}
		
	
	# ��������
		* React �����������ʱ�� current ���Դ��� DOM Ԫ�أ��������ж��ʱ���� null ֵ��
		* ref ���� componentDidMount �� componentDidUpdate �������ڹ��Ӵ���ǰ���¡�

	
	# Refs �뺯�����
		* �����ں��������ʹ�� ref ���ԣ���Ϊ����û��ʵ��
		* ���Ҫ�ں��������ʹ�� ref������ʹ�� forwardRef������ useImperativeHandle ���ʹ�ã�
		* ���߿��Խ������ת��Ϊ class ���
		
		* �ں��������, Ҳ����ʹ�� ref��������ʹ�� React.useRef(null) ������ʼ������
			function Test () {
			  // ����ref
			  const ref = useRef(null);			  // ����������� textInput������ ref �ſ���������
			  function onClick(){
				// ����ref
				ref.current.focus();
			  }
			  return (
				<div>
				  // ��ref
				  <input ref={ref} placeholder="������"></input><br/>
				  <button onClick={onClick}>����һ�ý���</button>
				</div>
			  )
			}


			ReactDOM.render(<Test/>, element);
	

	# �ص� Refs
		* React ֧����һ������ refs �ķ�ʽ����Ϊ���ص� refs����
		* ���ܸ���ϸ�ؿ��ƺ�ʱ refs �����úͽ��

		* �� createRef() ������ ref ���Բ�һ��
		* �ص� Refs �ᴫ��һ����������������н��� React ���ʵ���� HTML DOM Ԫ����Ϊ��������ʹ�������������ط����洢�ͷ���

			class App extends React.Component {
			  constructor(props){
				super(props);
				
				// ����һ��ref����ʵ��������һ���������������Ѿ��� domԪ�ػ������ʵ�����󣬲���Ҫͨ�� current ��ȡ
				this.setTextRef = node => {
				  this.textElement = node;
				}
			  }
			  onClick = () => {
				if (this.textElement){
				  this.textElement.focus();
				}
			  }
			  render (){
				return (
				  <div>
					  // �� ref����ִ�лص�����
					  <input ref={this.setTextRef}></input><br/>
					  <button onClick={this.onClick}>��ȡ����</button>
				  </div>
				)
			  }
			}

		* React �����������ʱ������� ref �ص����������� DOM Ԫ�أ���ж��ʱ������������ null��
		* �� componentDidMount �� componentDidUpdate ����ǰ��React �ᱣ֤ refs һ�������µġ�
	
		
		* ����������䴫�ݻص���ʽ�� refs��������Դ���ͨ�� React.createRef() �����Ķ��� refs һ����
			function Sub (props){
			  return(
				// ����ref��Ϊ����������� inputRef ����
				<input ref={props.inputRef}></input>
			  )
			}

			class App extends React.Component {
			  onClick = () => {
				if(this.inputRef){
				  this.inputRef.focus()
				} 
			  }
			  render (){
				return (
				  <div>
					<Sub inputRef={element => this.inputRef = element}></Sub>
					<button onClick={this.onClick}>���</button>
				  </div>
				)
			  }
			}
		
		* ��� ref �ص������������������ķ�ʽ����ģ��ڸ��¹��������ᱻִ�����Σ���һ�δ������ null��Ȼ��ڶ��λᴫ����� DOM Ԫ�ء�
		* ������Ϊ��ÿ����Ⱦʱ�ᴴ��һ���µĺ���ʵ�������� React ��վɵ� ref ���������µġ�
		* ͨ���� ref �Ļص���������� class �İ󶨺����ķ�ʽ���Ա����������⣬���Ǵ��������������޹ؽ�Ҫ�ġ�

-------------------
refsת��
-------------------
	# �� DOM Refs ��¶�������
		* ͨ������������������Ϊ�����������ķ�װ

		* ���ʹ�� 16.3 ����߰汾�� React, ����������Ƽ�ʹ�� refs ת��
		* Ref ת��ʹ���������¶�Լ��� ref һ����¶������� ref

		* �汾���͵Ļ�, ʹ���������: https://gist.github.com/gaearon/1a018a023347fe1c2476073330cc5509
	

	# ����ת�� React.forwardRef((props, ref) => {return ....});
		const FancyButton = React.forwardRef((props, ref) => {
		  return (
			// �Ѹ��������ref���󶨵�button�ڵ�
			<button ref={ref} {...props}></button>
		  )
		})

		class App extends React.Component {
		  constructor(props){
			super(props);
			// ����ref
			this.ref = React.createRef();
		  }
		  onClick = () => {
			// ��ȡ���������ref
			const node = this.ref.current;
			console.log(node); // <button>�����</button>
		  }
		  render(){
			// ��ref�������
			return <FancyButton ref={this.ref} onClick={this.onClick}>�����</FancyButton>
		  }
		}

		ReactDOM.render(<App></App>, element);

		* React.forwardRef ������һ������, �ú�����һ���������Ǹ�������� props, �ڶ����������� ref
		* �ú������ص��ǿ������, Ҳ������html, ref �����Խ��а�
	
--------------------------
�ڸ߽������ת�� refs
--------------------------


