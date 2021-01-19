-------------------------
Render Prop
-------------------------
	# һ���� React ���֮��ʹ��һ��ֵΪ������ prop �������ļ򵥼���
		* ���� render prop ���������һ������, �ú�������һ�� React Ԫ�ز�������������ʵ���Լ�����Ⱦ�߼�

		* ͨ�׵�˵����, ����ṩ��һЩ���㹦�ܣ����ݣ�, ���ǲ�֪��Ҫ����Щ���ݸ���Щ������ã�д�����ⲻ�á�
		* ����Ҫ��, ˭�������������ͱ����ṩһ�������������������һ�����������������������Ҫ�����ݡ�
		* �Ǻ��ˣ���ֱ�ӵ����⺯�����Ҽ���������ݶ�����������Ⱦɶ������������ġ�
		

	# ����ʹ�ô��� render prop �ĳ��������ʵ�ִ�����߽���� (HOC)


		

-------------------------
Render Prop demo
-------------------------
	import React from 'react';
	import ReactDOM from 'react-dom';

	const element = document.querySelector('#root');

	// ------------------------------------------------------------

	class Image extends React.Component {
	  render (){
		const mouse = this.props;
		return (
		  <img src="/favicon.ico" style={{ position: 'absolute', left: mouse.x, top: mouse.y }} alt={"React Logo"}/>
		)
	  }
	}

	class MouseWithImage extends React.Component {
	  constructor(props){
		super(props);
		this.state = {
		  x: 0,
		  y: 0
		}
	  }
	  onMouseMove = (event) => {
		this.setState({
		  x: event.clientX,
		  y: event.clientY
		});
	  }
	  render (){
		return (
		  <div style={{ height: '100vh' }} onMouseMove={this.onMouseMove}>
			  {/*
				�ҷ����ṩ��x��y�ļ��㡣���ﲻд��ĳ�������
				˭�����ң����Ҹ�����������������㣩���Ұ�xy���㡣���Լ�����Ⱦɶ������Ⱦɶ��
				��������������� & �����ԡ�
				<Image {...this.state}></Image>
			   */}
			{this.props.render(this.state)}
		  </div>
		)
	  }
	}

	class MouseTracker  extends React.Component {
	  render (){
		return (
		  <>
			<h1>���ƶ����</h1>
			<MouseWithImage render={mouse => {
			  return <Image {...mouse}/>
			}}/>
		  </>
		)
	  }
	}

	ReactDOM.render(<MouseTracker/>, element);


	# Render Prop �е� Render����ֻ��һ������
		* �������κ��Լ�ϲ���������������������������Ͼ��ǰѺ���ͨ�� props ���ݸ���������ѣ�
		* ���������� children ������
			 <div style={{ height: '100vh' }} onMouseMove={this.onMouseMove}>
				{this.props.children(this.state)}
			 </div>

			  <>
				<h1>���ƶ����</h1>
				  <MouseWithImage>
					{
					  mouse => {
						return <Image {...mouse}/>
					  }
					}
				  </MouseWithImage>
			  </>
	
	# �� Render Props �� React.PureComponent һ��ʹ��ʱҪС��
		* render �����ﴴ����������ôʹ�� render prop ���� React.PureComponent ʧЧ��
		* ��Ϊǳ�Ƚ� props ��ʱ���ܻ�õ� false�����������������ÿһ�� render ���� render prop ��������һ���µ�ֵ��
			class Mouse extends React.PureComponent { // �̳� PureComponent
			  // ��������ͬ�Ĵ���......
			}

			class MouseTracker extends React.Component {
			  render() {
				return (
				  <div>
					<h1>Move the mouse around!</h1>

					{/*
					  ÿ����Ⱦ�� `render` prop��ֵ�����ǲ�ͬ�ġ���Ϊÿ�ζ����µ�һ��������������
						Mouse ���ͨ��ǳ�Ƚ� ==��ʼ�շ��� false
					*/}
					<Mouse render={mouse => (
					  <Cat mouse={mouse} />
					)}/>
				  </div>
				);
			  }
			}		
		* ���������⣬���Զ���һ�� prop ��Ϊʵ������
			class MouseTracker extends React.Component {
			  // ����Ϊʵ��������`this.renderTheCat`ʼ��
			  // ����������Ⱦ��ʹ����ʱ����ʼ��ָ�������ͬ�ĺ���
			  renderTheCat(mouse) {
				return <Cat mouse={mouse} />;
			  }

			  render() {
				return (
				  <div>
					<h1>Move the mouse around!</h1>
					<Mouse render={this.renderTheCat} />
				  </div>
				);
			  }
			}