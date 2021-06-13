------------------
yaml����
------------------
archetypeDir: "archetypes"
assetDir: "assets"
baseURL: "http://localhost/"
blackfriday: 
build:
  noJSConfigInAssets: false
  useResourceCacheWhen: fallback
  writeStats: false
buildDrafts: false
buildExpired: false
buildFuture: false
caches: 
canonifyURLs: false
contentDir: "content"
dataDir: "data"
defaultContentLanguage: "en"
defaultContentLanguageInSubdir: false
disableAliases: false
disableHugoGeneratorInject: false
	* �Ƿ����hugo�����ɱ��(��ҳ����Hugo����)

disableKinds: []
disableLiveReload: false 
	* �Ƿ�����ļ�ϵͳ��أ����ļ��ı��������Ⱦ
disablePathToLower: false
	* �Ƿ����ת��URLΪСд

enableEmoji: false
	* ���ñ���

enableGitInfo: false
enableInlineShortcodes: false
	* ���������̴���

enableMissingTranslationPlaceholders: false
	* ���ȱ�ٷ��룬����ʾռλ���������ǿ��ַ���

enableRobotsTXT: false
	* �Ƿ�����robot.txt
frontmatter:
footnoteAnchorPrefix: ""
footnoteReturnLinkContents: ""
googleAnalytics: ""
	* Google Analytics ���� ID��

hasCJKLanguage: ""
	* ���Ϊ true�����Զ���������е�����/����/���ġ��⽫ʹ.Summary��.WordCount��ȷ��Ϊ��CJK���ԡ�

imaging: 
languages:
languageCode: "en-us"
languageName: ""
disableLanguages:
layoutDir: "layouts"
	* Hugo ���ж�ȡ���֣�ģ�壩��Ŀ¼��

log: false
	* ������־��¼

logFile: ""
	* ������־��¼����£���־����ļ�

markup:
menu:
minify:
module:
newContentEditor: ""
	* ����������ʱʹ�õı༭��

noChmod: false
	* ��ͬ���ļ���Ȩ��

noTimes: false
paginate: 10
	* ÿҳĬ�ϴ�С

paginatePath: "page"
	* ��ҳ·��

permalinks:
	* ��������

pluralizeListTitles: true
	* ���б��еı��⸴����

publishDir: "public"
	* ���ù�����ľ�̬��Դ���Ŀ¼
related:
relativeURLs: false
refLinksErrorLevel: "ERROR"
refLinksNotFoundURL:
rssLimit: 
	* RSS ��Ҫ�е������Ŀ����
	* Ĭ�ϲ�����

sectionPagesMenu: ""
sitemap:
	* վ���ͼ

staticDir: "static"
	* ��̬��ԴĿ¼

summaryLength: 70
	* ժҪ����

taxonomies: ""
	* ���෨

theme: ""
	* ����ʹ�õ�����

themesDir: "themes"
	* ���������ļ����ڵ�Ŀ¼

timeout: 10000
title: "blog"
	* վ�����

titleCaseStyle: "AP"
uglyURLs: false
verbose: false
verboseLog: false
watch: false
	

------------------
yaml����
------------------
baseURL: "https://blog.springboot.io/"
defaultContentLanguage: "en"
languageCode: "en-us"
title: "springboot"
theme: "ananke"
enableEmoji: true

------------------
����Ŀ¼
------------------
	# ����ʹ�õ���վ�������ļ�֮�⣬������ʹ��configDirĿ¼��Ĭ��Ϊconfig/����ά�������ɵ���֯�ͻ����ض�����

	# ÿ���ļ�����һ�����ø������ļ����ƣ���������������
		params.tomlfor [Params]
		menu(s).tomlfor [Menu]
		languages.tomlfor[Languages]
	
	# ÿ���ļ������ݱ����Ƕ�����


	# Demo
		* �ṹ
			������ config
			��   ������ _default
			��   ��   ������ config.toml
			��   ��   ������ languages.toml
			��   ��   ������ menus.en.toml
			��   ��   ������ menus.zh.toml
			��   ��   ������ params.toml
			��   ������ production
			��   ��   ������ config.toml
			��   ��   ������ params.toml
			��   ������ staging
			��       ������ config.toml
			��       ������ params.toml
		
		* ����
			hugo --environment staging

			Hugo ��ʹ������config/_default �ͺϲ� staging ����������
		
