--------------------------
React ��
--------------------------
	# �ܿ����
		* ���� <input type="file" /> ����, ����������ܿ����
	
		* ��Ҫ�� value �󶨵������state/props
		* value���޸�, ��Ҫͨ���¼�����, ȥ�޸� state/props ���ﵽ
	
	# select ��ǩ
		* React ������ʹ�� selected ����, �����ڸ� select ��ǩ��ʹ�� value ����
		* ����Ƕ�ѡ��Ļ�, ����������
			<select multiple={true} value={['B', 'C']}>
	
	# ָ���� value, �������Կɱ༭
		* ����������ؽ� value ����Ϊ undefined �� null
			ReactDOM.render(<input value={null} />, mountNode);
	

	# �ڷ��ܿ������Ĭ��ֵ
		* ��Ԫ���ϵ� value ���Ḳ�� DOM �ڵ��е�ֵ
		* �ڷ��ܿ�����У���Ҫ React �ܸ������һ����ʼֵ�����ǲ�ȥ���ƺ����ĸ��¡�
		* �����������, �����ָ��һ�� defaultValue ���ԣ������� value��
			render() {
			  return (
				<form onSubmit={this.handleSubmit}>
				  <label>
					Name:
					<input
					  defaultValue="Bob"
					  type="text"
					  ref={this.input} />
				  </label>
				  <input type="submit" value="Submit" />
				</form>
			  );
			}
		
			* ͬ����<input type="checkbox"> �� <input type="radio"> ֧�� defaultChecked��<select> �� <textarea> ֧�� defaultValue��
