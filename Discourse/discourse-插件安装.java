--------------------------------
�����װ						|
--------------------------------
	# �ٷ��̳�
		https://meta.discourse.org/t/install-plugins-in-discourse/19157
	
	# ����
		1. ��ȡ�������git��ַ(github/bitbucket)
		2. �������Ŀ¼, �༭: app.yml
			cd /var/discourse
			vim containers/app.yml
		
		3. ��Ӳ����ַ��hock
			hooks:
			  after_code:
				- exec:
					cd: $home/plugins
					cmd:
					  - git clone https://github.com/discourse/docker_manager.git
					  - git clone https://github.com/discourse/discourse-spoiler-alert.git
		4. �ع�����
			cd /var/discourse
			./launcher rebuild app
		
--------------------------------
һЩ���ͳ��					|
--------------------------------
	# �ٷ��Ĳ��ͳ��
		https://meta.discourse.org/c/plugin
	
	# ����/������
		https://github.com/discourse/discourse-solved.git
	
	# �ȸ�����
		https://github.com/discourse/discourse-adplugin

		* CSP������Ҫ��Ӱ�����
			pagead2.googlesyndication.com
			adservice.google.com
			www.googletagservices.com
			'unsafe-inline'

