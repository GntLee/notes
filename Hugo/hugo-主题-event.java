----------------------
event
----------------------
https://github.com/olOwOlo/hugo-theme-even

----------------------
config.toml
----------------------

baseURL = "https://www.myblog.net/"
languageCode = "en"
defaultContentLanguage = "en"                             # en / zh-cn / ... (This field determines which i18n file to use)
title = "myblog - Code Changes the World"
preserveTaxonomyNames = true
enableRobotsTXT = true
enableEmoji = true
theme = "even"
enableGitInfo = false # use git commit log to generate lastmod record # 可根据 Git 中的提交生成最近更新记录。

# Syntax highlighting by Chroma. NOTE: Don't enable `highlightInClient` and `chroma` at the same time!
pygmentsOptions = "linenos=table"
pygmentsCodefences = true
pygmentsUseClasses = true
pygmentsCodefencesGuessSyntax = true

hasCJKLanguage = true     # has chinese/japanese/korean ? # 自动检测是否包含 中文\日文\韩文
paginate = 10                                              # 首页每页显示的文章数
disqusShortname = ""      # disqus_shortname
googleAnalytics = ""      # UA-XXXXXXXX-X
copyright = ""            # default: author.name ↓        # 默认为下面配置的author.name ↓

# [author]                  # essential                     # 必需
  name = "olOwOlo"

[sitemap]                 # essential                     # 必需
  changefreq = "weekly"
  priority = 0.5
  filename = "sitemap.xml"

[[menu.main]]             # config your menu              # 配置目录
  name = "Home"
  weight = 10
  identifier = "home"
  url = "/"
[[menu.main]]
  name = "Archives"
  weight = 20
  identifier = "archives"
  url = "/post/"
[[menu.main]]
  name = "Tags"
  weight = 30
  identifier = "tags"
  url = "/tags/"
[[menu.main]]
  name = "Categories"
  weight = 40
  identifier = "categories"
  url = "/categories/"

[params]
  version = "4.x"           # Used to give a friendly message when you have an incompatible update
  debug = false             # If true, load `eruda.min.js`. See https://github.com/liriliri/eruda

  since = "2021"            # Site creation time          # 站点建立时间
  # use public git repo url to link lastmod git commit, enableGitInfo should be true.
  # 指定 git 仓库地址，可以生成指向最近更新的 git commit 的链接，需要将 enableGitInfo 设置成 true.
  gitRepo = ""

  # site info (optional)                                  # 站点信息（可选，不需要的可以直接注释掉）
  logoTitle = "myblog"        # default: the title value    # 默认值: 上面设置的title值
  keywords = ["Java", "Golang","Javascript"]
  description = "so byte"

  # paginate of archives, tags and categories             # 归档、标签、分类每页显示的文章数目，建议修改为一个较大的值
  archivePaginate = 50

  # show 'xx Posts In Total' in archive page ?            # 是否在归档页显示文章的总数
  showArchiveCount = false

  # The date format to use; for a list of valid formats, see https://gohugo.io/functions/format/
  dateFormatToUse = "2006-01-02"

  # show word count and read time ?                       # 是否显示字数统计与阅读时间
  moreMeta = false

  # Syntax highlighting by highlight.js
  highlightInClient = false

  # 一些全局开关，你也可以在每一篇内容的 front matter 中针对单篇内容关闭或开启某些功能，在 archetypes/default.md 查看更多信息。
  # Some global options, you can also close or open something in front matter for a single post, see more information from `archetypes/default.md`.
  toc = true                                                                            # 是否开启目录
  autoCollapseToc = false   # Auto expand and collapse toc                              # 目录自动展开/折叠
  fancybox = true           # see https://github.com/fancyapps/fancybox                 # 是否启用fancybox（图片可点击）

  # mathjax
  mathjax = true           # see https://www.mathjax.org/                              # 是否使用mathjax（数学公式）
  mathjaxEnableSingleDollar = false                                                     # 是否使用 $...$ 即可進行inline latex渲染
  mathjaxEnableAutoNumber = false                                                       # 是否使用公式自动编号
  mathjaxUseLocalFiles = false  # You should install mathjax in `your-site/static/lib/mathjax`

  postMetaInFooter = false   # contain author, lastMod, markdown link, license           # 包含作者，上次修改时间，markdown链接，许可信息
  linkToMarkDown = false    # Only effective when hugo will output .md files.           # 链接到markdown原始文件（仅当允许hugo生成markdown文件时有效）
  contentCopyright = ''     # e.g. '<a rel="license noopener" href="https://creativecommons.org/licenses/by-nc-nd/4.0/" target="_blank">CC BY-NC-ND 4.0</a>'

  changyanAppid = ""        # Changyan app id             # 畅言
  changyanAppkey = ""       # Changyan app key

  livereUID = ""            # LiveRe UID                  # 来必力

  baiduPush = false        # baidu push                  # 百度
  baiduAnalytics = ""      # Baidu Analytics
  baiduVerification = ""   # Baidu Verification
  googleVerification = ""  # Google Verification         # 谷歌

  # Link custom CSS and JS assets
  #   (relative to /static/css and /static/js respectively)
  customCSS = []
  customJS = []

  uglyURLs = false          # please keep same with uglyurls setting

  # Show language selector for multilingual site.
  showLanguageSelector = false

  [params.publicCDN]        # load these files from public cdn                          # 启用公共CDN，需自行定义
    enable = true
    jquery = '<script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1/dist/jquery.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>'
    slideout = '<script src="https://cdn.jsdelivr.net/npm/slideout@1.0.1/dist/slideout.min.js" integrity="sha256-t+zJ/g8/KXIJMjSVQdnibt4dlaDxc9zXr/9oNPeWqdg=" crossorigin="anonymous"></script>'
    fancyboxJS = '<script src="https://cdn.jsdelivr.net/npm/@fancyapps/fancybox@3.1.20/dist/jquery.fancybox.min.js" integrity="sha256-XVLffZaxoWfGUEbdzuLi7pwaUJv1cecsQJQqGLe7axY=" crossorigin="anonymous"></script>'
    fancyboxCSS = '<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fancyapps/fancybox@3.1.20/dist/jquery.fancybox.min.css" integrity="sha256-7TyXnr2YU040zfSP+rEcz29ggW4j56/ujTPwjMzyqFY=" crossorigin="anonymous">'
    timeagoJS = '<script src="https://cdn.jsdelivr.net/npm/timeago.js@3.0.2/dist/timeago.min.js" integrity="sha256-jwCP0NAdCBloaIWTWHmW4i3snUNMHUNO+jr9rYd2iOI=" crossorigin="anonymous"></script>'
    timeagoLocalesJS = '<script src="https://cdn.jsdelivr.net/npm/timeago.js@3.0.2/dist/timeago.locales.min.js" integrity="sha256-ZwofwC1Lf/faQCzN7nZtfijVV6hSwxjQMwXL4gn9qU8=" crossorigin="anonymous"></script>'
    flowchartDiagramsJS = '<script src="https://cdn.jsdelivr.net/npm/raphael@2.2.7/raphael.min.js" integrity="sha256-67By+NpOtm9ka1R6xpUefeGOY8kWWHHRAKlvaTJ7ONI=" crossorigin="anonymous"></script> <script src="https://cdn.jsdelivr.net/npm/flowchart.js@1.8.0/release/flowchart.min.js" integrity="sha256-zNGWjubXoY6rb5MnmpBNefO0RgoVYfle9p0tvOQM+6k=" crossorigin="anonymous"></script>'
    sequenceDiagramsCSS = '<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bramp/js-sequence-diagrams@2.0.1/dist/sequence-diagram-min.css" integrity="sha384-6QbLKJMz5dS3adWSeINZe74uSydBGFbnzaAYmp+tKyq60S7H2p6V7g1TysM5lAaF" crossorigin="anonymous">'
    sequenceDiagramsJS = '<script src="https://cdn.jsdelivr.net/npm/webfontloader@1.6.28/webfontloader.js" integrity="sha256-4O4pS1SH31ZqrSO2A/2QJTVjTPqVe+jnYgOWUVr7EEc=" crossorigin="anonymous"></script> <script src="https://cdn.jsdelivr.net/npm/snapsvg@0.5.1/dist/snap.svg-min.js" integrity="sha256-oI+elz+sIm+jpn8F/qEspKoKveTc5uKeFHNNVexe6d8=" crossorigin="anonymous"></script> <script src="https://cdn.jsdelivr.net/npm/underscore@1.8.3/underscore-min.js" integrity="sha256-obZACiHd7gkOk9iIL/pimWMTJ4W/pBsKu+oZnSeBIek=" crossorigin="anonymous"></script> <script src="https://cdn.jsdelivr.net/gh/bramp/js-sequence-diagrams@2.0.1/dist/sequence-diagram-min.js" integrity="sha384-8748Vn52gHJYJI0XEuPB2QlPVNUkJlJn9tHqKec6J3q2r9l8fvRxrgn/E5ZHV0sP" crossorigin="anonymous"></script>'

  # Display a message at the beginning of an article to warn the readers that it's content may be outdated.
  # 在文章开头显示提示信息，提醒读者文章内容可能过时。
  [params.outdatedInfoWarning]
    enable = true
    hint = 30               # Display hint if the last modified time is more than these days ago.    # 如果文章最后更新于这天数之前，显示提醒
    warn = 180              # Display warning if the last modified time is more than these days ago.    # 如果文章最后更新于这天数之前，显示警告

  #[params.gitment]          # Gitment is a comment system based on GitHub issues. see https://github.com/imsun/gitment
  #  owner = ""              # Your GitHub ID
  #  repo = ""               # The repo to store comments
  #  clientId = ""           # Your client ID
  #  clientSecret = ""       # Your client secret

  #[params.utterances]       # https://utteranc.es/
  #  owner = ""              # Your GitHub ID
  #  repo = ""               # The repo to store comments

  #[params.gitalk]           # Gitalk is a comment system based on GitHub issues. see https://github.com/gitalk/gitalk
  #  owner = ""              # Your GitHub ID
  #  repo = ""               # The repo to store comments
  #  clientId = ""           # Your client ID
  #  clientSecret = ""       # Your client secret

  # Valine.
  # You can get your appid and appkey from https://leancloud.cn
  # more info please open https://valine.js.org
  [params.valine]
    enable = false
    appId = '你的appId'
    appKey = '你的appKey'
    notify = false  # mail notifier , https://github.com/xCss/Valine/wiki
    verify = false # Verification code
    avatar = 'mm'
    placeholder = '说点什么吧...'
    visitor = false

  [params.flowchartDiagrams]# see https://blog.olowolo.com/example-site/post/js-flowchart-diagrams/
    enable = false
    options = ""

  [params.sequenceDiagrams] # see https://blog.olowolo.com/example-site/post/js-sequence-diagrams/
    enable = false
    options = ""            # default: "{theme: 'simple'}"

  [params.busuanzi]         # count web traffic by busuanzi                             # 是否使用不蒜子统计站点访问量
    enable = false
    siteUV = true
    sitePV = true
    pagePV = true

  [params.reward]                                         # 文章打赏
    enable = false
    wechat = "/path/to/your/wechat-qr-code.png"           # 微信二维码
    alipay = "/path/to/your/alipay-qr-code.png"           # 支付宝二维码

  #[params.social]                                         # 社交链接
  #  a-email = "mailto:your@email.com"
  #  b-stack-overflow = "http://localhost:1313"
  #  c-twitter = "http://localhost:1313"
  #  d-facebook = "http://localhost:1313"
  #  e-linkedin = "http://localhost:1313"
  #  f-google = "http://localhost:1313"
  #  g-github = "http://localhost:1313"
  #  h-weibo = "http://localhost:1313"
  #  i-zhihu = "http://localhost:1313"
  #  j-douban = "http://localhost:1313"
  #  k-pocket = "http://localhost:1313"
  #  l-tumblr = "http://localhost:1313"
  #  m-instagram = "http://localhost:1313"
  #  n-gitlab = "http://localhost:1313"
  #  o-bilibili = "http://localhost:1313"

# See https://gohugo.io/about/hugo-and-gdpr/
[privacy]
  [privacy.googleAnalytics]
    anonymizeIP = true      # 12.214.31.144 -> 12.214.31.0
  [privacy.youtube]
    privacyEnhanced = true

# see https://gohugo.io/getting-started/configuration-markup
[markup]
  [markup.tableOfContents]
    startLevel = 1
  [markup.goldmark.renderer]
    unsafe = true

# 将下面这段配置取消注释可以使 hugo 生成 .md 文件
# Uncomment these options to make hugo output .md files.
#[mediaTypes]
#  [mediaTypes."text/plain"]
#    suffixes = ["md"]
#
#[outputFormats.MarkDown]
#  mediaType = "text/plain"
#  isPlainText = true
#  isHTML = false
#
#[outputs]
#  home = ["HTML", "RSS"]
#  page = ["HTML", "MarkDown"]
#  section = ["HTML", "RSS"]
#  taxonomy = ["HTML", "RSS"]
#  taxonomyTerm = ["HTML"]