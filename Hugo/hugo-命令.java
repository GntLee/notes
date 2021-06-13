---------------------
����
---------------------
Usage:
  hugo [flags]
  hugo [command]

Available Commands:
  config      Print the site configuration
  convert     Convert your content to different formats
  deploy      Deploy your site to a Cloud provider.
  env         Print Hugo version and environment info
  gen         A collection of several useful generators.
  help        Help about any command
  	* �����������

  import      Import your site from others.
  list        Listing out various types of content
  mod         Various Hugo Modules helpers.
  new         Create new content for your site
  	* �����µ�վ�㣬ָ������
		
  server      A high performance webserver
  	* ����һ�����صķ���

  version     Print the version number of Hugo
  	* ����汾��

Flags:
  -b, --baseURL string             hostname (and path) to the root, e.g. http://spf13.com/
  -D, --buildDrafts                include content marked as draft
  		* ������ʱ�򣬰����ݸ��ļ�

  -E, --buildExpired               include expired content
  		* ������ʱ�򣬰����Ѿ����ڵ��ļ�

  -F, --buildFuture                include content with publishdate in the future
  		* ������ʱ�򣬰���δ�����ļ�

      --cacheDir string            filesystem path to cache directory. Defaults: $TMPDIR/hugo_cache/
      --cleanDestinationDir        remove files from destination not found in static directories
      --config string              config file (default is path/config.yaml|json|toml)
	  	* ָ�������ļ���������json,toml��ʽ�������ж����ʹ�ö���:, �ָ�

      --configDir string           config dir (default "config")
  -c, --contentDir string          filesystem path to content directory
      --debug                      debug output
  -d, --destination string         filesystem path to write files to
  		* ָ��������ľ�̬HTML�ļ����·����Ĭ��ΪĿ¼�µ�public�ļ���

      --disableKinds strings       disable different kind of pages (home, RSS etc.)
      --enableGitInfo              add Git revision, date and author info to the pages
  -e, --environment string         build environment
  		* ������ʱ��ָ�����û���������ָ�������ļ����е��ļ�������
			hugo --environment staging

      --forceSyncStatic            copy all files when static is changed.
      --gc                         enable to run some cleanup tasks (remove unused cache files) after the build
  -h, --help                       help for hugo
      --i18n-warnings              print missing translations
      --ignoreCache                ignores the cache directory
      --ignoreVendor               ignores any _vendor directory
      --ignoreVendorPaths string   ignores any _vendor for module paths matching the given Glob pattern
  -l, --layoutDir string           filesystem path to layout directory
      --log                        enable Logging
      --logFile string             log File path (if set, logging enabled automatically)
      --minify                     minify any supported output format (HTML, XML etc.)
      --noChmod                    don't sync permission mode of files
      --noTimes                    don't sync modification time of files
      --path-warnings              print warnings on duplicate target paths etc.
      --print-mem                  print memory usage to screen at intervals
      --quiet                      build in quiet mode
      --renderToMemory             render to memory (only useful for benchmark testing)
  -s, --source string              filesystem path to read files relative from
      --templateMetrics            display metrics about template executions
      --templateMetricsHints       calculate some improvement hints when combined with --templateMetrics
  -t, --theme strings              themes to use (located in /themes/THEMENAME/)
      --themesDir string           filesystem path to themes directory
      --trace file                 write trace to file (not useful in general)
  -v, --verbose                    verbose output
      --verboseLog                 verbose logging
  -w, --watch                      watch filesystem for changes and recreate as needed
		* �Ƿ�ʵʱ����ļ�ϵͳ�޸ģ�����޸��˻���������ҳ��
		* Ĭ�Ͽ���������
			hugo server --watch=false
		* ����Ҳ������
			hugo server --disableLiveReload
 
---------------------
����
---------------------
	hugo
		* ִ�й����������̬��Դ��publicĿ¼
		* ������ʱ�򣬲���ɾ��Ŀ¼�е���Դ���Լ�Ӧ����ɾ������������ָ��һ���µ�Ŀ¼

	hugo new site [folder-path]
		* ��folder-path�г�ʼ��һ���µĲ�����Ŀ
	

	hugo new [file]
		* �½�һ�����£�fileָ���������ƣ�����:  HelloWorld.md
		* Ҳ����ָ��·���� post/HelloWorld.md
	
	hugo server
		* �ڱ����ṩHTTPԤ������Ĭ�϶˿�: 1313