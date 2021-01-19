------------------------
PropTypes
------------------------
	# React Ҳ���������ͼ��Ĺ���,Ҫ������� props �Ͻ������ͼ�飬ֻ�������ض��� propTypes ����
		import PropTypes from 'prop-types';

		class Greeting extends React.Component {
		  render() {
			return (
			  <h1>Hello, {this.props.name}</h1>
			);
		  }
		}

		Greeting.propTypes = {
		  name: PropTypes.string  // 
		};
		
		* ������� prop ֵ���Ͳ���ȷʱ��JavaScript ����̨������ʾ����
		* �������ܷ���Ŀ��ǣ�propTypes ���ڿ���ģʽ�½��м��
	
	# ��֤������
		import PropTypes from 'prop-types';

		MyComponent.propTypes = {
		  // ����Խ���������Ϊ JS ԭ�����ͣ�Ĭ�������
		  // ��Щ���Զ��ǿ�ѡ�ġ�
		  optionalArray: PropTypes.array,
		  optionalBool: PropTypes.bool,
		  optionalFunc: PropTypes.func,
		  optionalNumber: PropTypes.number,
		  optionalObject: PropTypes.object,
		  optionalString: PropTypes.string,
		  optionalSymbol: PropTypes.symbol,

		  // �κοɱ���Ⱦ��Ԫ�أ��������֡��ַ�����Ԫ�ػ����飩
		  // (�� Fragment) Ҳ������Щ���͡�
		  optionalNode: PropTypes.node,

		  // һ�� React Ԫ�ء�
		  optionalElement: PropTypes.element,

		  // һ�� React Ԫ�����ͣ�����MyComponent����
		  optionalElementType: PropTypes.elementType,

		  // ��Ҳ�������� prop Ϊ���ʵ��������ʹ��
		  // JS �� instanceof ��������
		  optionalMessage: PropTypes.instanceOf(Message),

		  // ���������� prop ֻ�����ض���ֵ��ָ����Ϊ
		  // ö�����͡�
		  optionalEnum: PropTypes.oneOf(['News', 'Photos']),

		  // һ����������Ǽ��������е�����һ������
		  optionalUnion: PropTypes.oneOfType([
			PropTypes.string,
			PropTypes.number,
			PropTypes.instanceOf(Message)
		  ]),

		  // ����ָ��һ��������ĳһ���͵�Ԫ�����
		  optionalArrayOf: PropTypes.arrayOf(PropTypes.number),

		  // ����ָ��һ��������ĳһ���͵�ֵ���
		  optionalObjectOf: PropTypes.objectOf(PropTypes.number),

		  // ����ָ��һ���������ض�������ֵ���
		  optionalObjectWithShape: PropTypes.shape({
			color: PropTypes.string,
			fontSize: PropTypes.number
		  }),
		  
		  // An object with warnings on extra properties
		  optionalObjectWithStrictShape: PropTypes.exact({
			name: PropTypes.string,
			quantity: PropTypes.number
		  }),   

		  // ��������κ� PropTypes ���Ժ������ `isRequired` ��ȷ��
		  // ��� prop û�б��ṩʱ�����ӡ������Ϣ��
		  requiredFunc: PropTypes.func.isRequired,

		  // �������͵�����
		  requiredAny: PropTypes.any.isRequired,

		  // �����ָ��һ���Զ�����֤����������֤ʧ��ʱӦ����һ�� Error ����
		  // �벻Ҫʹ�� `console.warn` ���׳��쳣����Ϊ���� `onOfType` �в��������á�
		  customProp: function(props, propName, componentName) {
			if (!/matchme/.test(props[propName])) {
			  return new Error(
				'Invalid prop `' + propName + '` supplied to' +
				' `' + componentName + '`. Validation failed.'
			  );
			}
		  },

		  // ��Ҳ�����ṩһ���Զ���� `arrayOf` �� `objectOf` ��֤����
		  // ��Ӧ������֤ʧ��ʱ����һ�� Error ����
		  // ��֤������֤���������е�ÿ��ֵ����֤����ǰ��������
		  // ��һ��������������
		  // �ڶ��������ǵ�ǰ�ļ���
		  customArrayProp: PropTypes.arrayOf(function(propValue, key, componentName, location, propFullName) {
			if (!/matchme/.test(propValue[key])) {
			  return new Error(
				'Invalid prop `' + propFullName + '` supplied to' +
				' `' + componentName + '`. Validation failed.'
			  );
			}
		  })
		};

	# ���Ƶ���Ԫ��
		* ����ͨ�� PropTypes.element ��ȷ�����ݸ������ children ��ֻ����һ��Ԫ�ء�

		import PropTypes from 'prop-types';
		class MyComponent extends React.Component {
		  render() {
			// �����ֻ��һ��Ԫ�أ��������̨���ӡ���档
			const children = this.props.children;
			return (
			  <div>
				{children}
			  </div>
			);
		  }
		}

		MyComponent.propTypes = {
		  children: PropTypes.element.isRequired
		};
	
	# Ĭ�� Prop ֵ
		* ����ͨ�������ض��� defaultProps ���������� props ��Ĭ��ֵ��

		class Greeting extends React.Component {
		  render() {
			return (
			  <h1>Hello, {this.props.name}</h1>
			);
		  }
		}

		// ָ�� props ��Ĭ��ֵ��
		Greeting.defaultProps = {
		  name: 'Stranger'
		};

		// ��Ⱦ�� "Hello, Stranger"��
		ReactDOM.render(
		  <Greeting />,
		  document.getElementById('example')
		);