----------------------------
validate					|
----------------------------
	# ������Դ��ı���֤����
	
	# form�����3������, ��һ������
		checkValidity()
			* ����������֤��ǰ���ؼ�Ԫ��, �����������Ƿ���֤ͨ��
			* ����ֵ�ǲ���ֵ: true����false
				document.querySelector('select').checkValidity(); 
				document.forms[0].checkValidity();

		reportValidity()
			* ��������������õ���֤��ʾ����, ���ز���ֵ: true����false
			* reportValidity()����IE���������֧��, Edge 17+��ʼ֧��

		setCustomValidity()
			* ����(reportValidity����ʱ)�Զ������ʾ����
			* ������һ���ַ���, ��ʾ������ʾ��������Ϣ, ����ǿ��ַ���, ���ʾ��ʹ���Զ���Ĵ�����ʾ
				var eleSelect = document.querySelector('select');
				if (eleSelect.validity.valueMissing == true) {
					eleSelect.setCustomValidity('��ѡ�����');
				}
				eleSelect.reportValidity();

		validity
			* ÿһ����׼�ı��ؼ�������������������Լ�����ѡ������һ��validity����(���Ǹ�ֻ������)
			* ���ص�ǰԪ�ظ�����֤״̬����������Ҳ��ֻ��
				badInput: false
					* ����ֵΪ�������͡�����������ֵ�����û�취����ת����
					* ����'number'��������������������ַ���MDN��˵���������Լ������޷����֣�����ʱֵ��true��������IE���������֧�֡������Խ����˽⼴�ɡ�

				customError: false
					* ��ֵΪ�������͡����Ԫ�ص���setCustomValidity()�����������Զ������֤��Ϣ��ֵ��true��

				patternMismatch: false
					* ����ֵΪ�������͡���ָ����pattern��������ֵ��ƥ���ʱ��ֵ��true��
					* ��ƥ��: invalid ���CSSα�ࡣ
						input:invalid {  /** invalid ѡ���������ڱ�Ԫ���е�ֵ�ǷǷ�ʱ����ָ����ʽ�� **/
							border:2px solid pink;
						}
						// invalid ѡ����ֻ��������ָ������ֵ��Ԫ�أ����� input Ԫ���е� min �� max ���ԣ�����ȷ�� email �ֶ�, �Ϸ��������ֶεȡ�

				rangeOverflow: false
					* ����ֵΪ�������͡�����max�������õ�ֵ��ʱ�����true
					* ͬʱ��ƥ��CSS :invalid��:out-of-rangeα��

				rangeUnderflow: false
					* ����ֵΪ�������͡�С��min�������õ�ֵ��ʱ�����true
					* ͬʱ��ƥ��CSS :invalid��:out-of-rangeα��

				stepMismatch: false
					* ����ֵΪ�������͡������ǰֵ��step����ֵ��ƥ���ʱ��stepMismatch��ֵ����true
					* ͬʱԪ�ػ�ƥ��CSS :invalid��:out-of-rangeα�ࡣ

				tooLong: false
					* ����ֵΪ�������͡��������ݳ��ȳ���maxlength������ʱ�����true��
					* ͬʱ��ƥ��CSS :invalid��:out-of-rangeα�ࡣ

				tooShort: false
					* ����ֵΪ�������͡��������ݳ��Ȳ���minlength������ʱ�����true��
					* ͬʱ��ƥ��CSS :invalid��:out-of-rangeα�ࡣ������IE���������֧�֣���Ϊ��֧��minlength���ԡ�

				typeMismatch: false
					* ����ֵΪ�������͡�������ֵ����������Ͳ�ƥ���ʱ�������ֵ����true��
					* ���������type������email����urlʱ�����ֵ��true����ƥ��:invalid���CSSα�ࡣ

				valid: true
					* ����ֵΪ�������͡���ǰԪ���Ƿ���ȫ��֤ͨ��
					* ͨ����true����ƥ��:valid���CSSα�ࣻ��ͨ����false����ƥ��:invalid���CSSα�ࡣ

				valueMissing: false
					* ����ֵΪ�������͡����Ԫ��������required����
					* ͬʱ������ֵΪ�գ��������ֵ��true�����ֵ��true����ƥ��:invalid���CSSα�ࡣ
		

	
