
# �鿴���е�����
	git config --list
		* �г����п����õ�������

	core.symlinks=false
	core.autocrlf=true
		* LF ��Linux �µĻ��з�, ��ӦASCII��ת���ַ�\n ��ʾ����
		* CRLF ��windows �µĻ��з�, \r\n ��ʾ�س�������
		* ���û��з��Ĵ���ʽ,��ѡ����ֵ:
			true :�ύʱת��ΪLF, ���ʱת��ΪCRLF
			false:�ύʱת��ΪLF, ���ʱ��ת��
			input:�ύ�������ת��
		
		* һ�����з���Դ�ֿ�Ĳ�һ��ʱ, git ����Ϊ����޸���ɾ����������, ������� code review �����޴���鷳

	core.fscache=true
	color.diff=auto
	color.status=auto
	color.branch=auto
	color.interactive=true
	help.format=html
	rebase.autosquash=true
	http.sslcainfo=C:/Program Files/Git/mingw64/ssl/certs/ca-bundle.crt
	http.sslbackend=openssl
	diff.astextplain.textconv=astextplain
	filter.lfs.clean=git-lfs clean -- %f
	filter.lfs.smudge=git-lfs smudge -- %f
	filter.lfs.process=git-lfs filter-process
	filter.lfs.required=true
	credential.helper=manager

	user.email=747692844@qq.com
	user.name=KevinBlandy
		* �û����ʼ�������
	
	core.quotepath=false
		* ����Ϊfalse, ����� git bash ��,�����Ա�����ʽ���ֵ�����

	color.ui=true
		* ������������ִ���ɫ
	
	gui.encoding=utf-8
		* ����ҳ��ı���



# ��������
	git config --global [������] [����ֵ]

	--global 
		* ��ʾȫ��,�����вֿ���Ч
		* ����ӵĻ�,�����Ե�ǰ�ֿ���Ч
	
	