-------------------------
�ϸ�ģʽ
-------------------------
	# React.StrictMode ��һ������ͻ����ʾӦ�ó�����Ǳ������Ĺ���
	# �ϸ�ģʽ�����ڿ���ģʽ������,����Ӱ����������

		import React from 'react';

		function ExampleApplication() {
		  return (
			<div>
			  <Header />
			  <React.StrictMode>
				<div>
				  <ComponentOne />
				  <ComponentTwo />
				</div>
			  </React.StrictMode>
			  <Footer />
			</div>
		  );
		}
	
	# StrictMode Ŀǰ������
		ʶ�𲻰�ȫ����������
		����ʹ�ù�ʱ�ַ��� ref API �ľ���
		����ʹ�÷����� findDOMNode �����ľ���
		�������ĸ�����
		����ʱ�� context API
	
