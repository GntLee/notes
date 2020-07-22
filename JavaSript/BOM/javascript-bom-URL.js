------------------
URL					|
------------------
	# ��̬����
		createObjectURL()
			* �����Ƕ�����ͼƬ��Ϣ/ͼƬ����,���ص���ͼƬ��BASE64����
		
		revokeObjectURL(objectURL)
			* ����֮ǰʹ��URL.createObjectURL()������URL����
			* ���в���objectURL��ʾ֮ǰʹ��URL.createObjectURL()������URL����ֵ

	# ʵ������
		hash
			* URL��ַ�е�ê��ֵ�������ַ���'#'

		host
			* URL��ַ��host������ַ������Э��˿ں�

		hostname
			* URL��ַ���������ƣ��������˿ں�

		href
			* ������URL��ַ

		origin [ֻ��]
			* ����URL��ַ����Դ�������URLЭ�飬�����Ͷ˿�

		password
			* ����URL��ַ����ǰ�����롣ftpЭ���бȽϳ���

		username
			* ����URL��ַ����ǰ���û�����ftpЭ���бȽϳ�����

		pathname
			* ����URL�е�Ŀ¼+�ļ�������������ftp.pathname�ķ���ֵ��'/path/file'��

		port
			* ����URL��ַ�еĶ˿ںš�

		protocol
			* ����URL��ַ��Э�飬���������ð��':'

		search
			* ����URL��ַ�Ĳ�ѯ�ַ���������в������򷵻�ֵ���ʺ�'?'��ͷ

		searchParams
			* ����һ��URLSearchParams���󣬿��Ե���URLSearchParams������ַ�����

	# ʵ������
		toString()
			* ���ص�������URL��ַ����������ΪURL.href������һ����ʽ���������ֻ������������޸�ֵ��

		toJSON()
			* ͬ������������URL��ַ�����ص��ַ�����href����һ����
		��̬����
		URL.createObjectURL(object)
		���԰�File��Blob����MediaSource������һ��һ��Ψһ��blob URL�����в���object������File��Blob����MediaSource����
		

------------------
URL	- Demo			|
------------------
	# WEB��ʾ����ͼƬ
		var url = window.URL.createObjectURL(files[0]);		//���ڴ��и����ı�������һ�������ƶ���
		$('#img').attr({'src':url});						//ֱ�Ӱ���������ƶ�����ʾ��img,Ҫע������ʾ֮��,�ͷŵ��ڴ�
		window.URL.revokeObjectURL(url);					//�ͷ��ڴ�
	