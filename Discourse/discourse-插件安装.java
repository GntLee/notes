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
		https://meta.discourse.org/tag/official
	
	# ����/������
		https://github.com/discourse/discourse-solved.git
	
	# �ȸ�����
		https://github.com/discourse/discourse-adplugin

		* CSP������Ҫ��Ӱ�����
			pagead2.googlesyndication.com
			adservice.google.com
			www.googletagservices.com
			'unsafe-inline'
	
	# �����ĵ������
		https://github.com/discourse/discourse-custom-header-links
	
	# matomo ͳ�Ʋ�
		https://github.com/discourse/discourse-matomo-analytics.git 
		
	# Discourseʹ�õ�һ�ѳ��������UXԪ��
		https://github.com/discourse/discourse-styleguide
	
	# Sitemap���
		https://github.com/discourse/discourse-sitemap
	
	# ��ѧ���ʽ֧�ֲ��
		https://github.com/discourse/discourse-math
	
	# �û��ġ�ר�ҡ���ʶ���
		https://github.com/discourse/discourse-category-experts
	
	# �����û��ڽ������л�����
		https://github.com/discourse/discourse-hamburger-theme-selector

--------------------------------
һЩ���						|
--------------------------------
	Custom Header Links
		* �Զ���header
	
