---------------------------
dom����
---------------------------
	dangerouslySetInnerHTML 
		* React Ϊ����� DOM �ṩ innerHTML ���滻����
		* ����ֱ���� React ������ HTML, ���� dangerouslySetInnerHTML ʱ, ��Ҫ���䴫�ݰ��� key Ϊ __html �Ķ���
		* ���ܻᵼ��XSS����, ����
			function App(props){
			  return (
				<div dangerouslySetInnerHTML = {{ __html: props.content }} />
			  );
			}

			<App content={'<h1>HelloWorld</h1>'}></App>
		
