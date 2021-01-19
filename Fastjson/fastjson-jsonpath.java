-----------------
jsonpath		 |
-----------------
	# ������XPATH, ���ڽ������� json 
		* ���json��ǳ��ĸ���, Ƕ�׺���Ļ�, һ����ȥ���ʷǳ�������,
		* �Ϳ���ѡ��ʹ�����ֿ��ٲ��ҵı��ʽ
	
	
-----------------
JSONPath		 |
-----------------
	# ���췽��
		public JSONPath(String path)
		public JSONPath(String path, SerializeConfig serializeConfig, ParserConfig parserConfig)
	
	# ��̬����/����
		public static Object eval(Object rootObject, String path)
		public static Object extract(String json, String path, ParserConfig config, int features, Feature... optionFeatures)
		public static Object extract(String json, String path) 
			* ����path����ֵ
			* extract,�������, ���ܻ����

		public static int size(Object rootObject, String path) 
			* ����Size
			* Map�ǿ�Ԫ�ظ���, ����ǿ�Ԫ�ظ���, Collection��Size, ����ĳ���
			* �����޷���ֵ����-1

		public static Set<?> keySet(Object rootObject, String path)	
			* ��ȡ, Map��KeySet, ����ǿ����Ե�����
			* ����, Collection�Ȳ�֧�����ͷ���null

		public static boolean contains(Object rootObject, String path)
			* �Ƿ����, path���Ƿ���ڶ���
			
		public static boolean containsValue(Object rootObject, String path, Object value)
			* �Ƿ����, path���Ƿ����ָ��ֵ
			* ����Ǽ��ϻ�������, �ڼ����в���value�Ƿ����

		public static void arrayAdd(Object rootObject, String path, Object... values)
			 * ��������߼��������Ԫ��, ��ӳɹ����� true,ʧ�ܷ��� false

		public static boolean set(Object rootObject, String path, Object value)
			* �޸��ƶ�·����ֵ, ����޸ĳɹ�, ����true, ���򷵻�false

		public static boolean remove(Object root, String path)
			* ɾ��ָ��path��Ԫ��, ɾ���ɹ����� true,ʧ�ܷ��� false

		public static JSONPath compile(String path)
			* ����һ��jsonpathΪ����

		public static Object read(String json, String path)
			* ��һ��json�ַ�����, ����ָ����path��ȡΪJson����
		
		public static Map<String, Object> paths(Object javaObject)
		public static Map<String, Object> paths(Object javaObject, SerializeConfig config)
			* ����ָ��Java��������Ե�����json����path

	# ʵ������
		* ʵ�������������ľ�̬�������, ֻ�ǲ���Ҫ path ����
		* ��Ϊ�����ʱ���Ѿ�����, ������ Pattern
	

		
-----------------
֧���﷨		 |
-----------------
	JSONPATH					����
	$							������, ����$.name
	[num]						�������, ����num������,�����Ǹ���, ����:$[0].leader.departments[-1].name
	[num0,num1,num2...]			������Ԫ�ط���, ����num������, �����Ǹ���, ���������еĶ��Ԫ��,����:$[0,3,-2,5]
	[start:end]					���鷶Χ����, ����start��end�ǿ�ʼС��ͽ����±�, �����Ǹ���, ���������еĶ��Ԫ��, ����:$[0:5]
	[start:end :step]			���鷶Χ����, ����start��end�ǿ�ʼС��ͽ����±�, �����Ǹ���,step�ǲ���, ���������еĶ��Ԫ������:$[0:5:2]
	[?(key)]					�������Էǿչ���, ����:$.departs[?(name)]
	[key > 123]					��ֵ���Ͷ������ԱȽϹ���,����:$.departs[id >= 123],�Ƚϲ�����֧��:=,!=,>,>=,<,<=
	[key like 'aa%']			�ַ�������like����,����:$.departs[name like 'sz*'], ͨ���ֻ֧��:% ֧��:not like
	[key rlike 'regexpr']		�ַ�����������ƥ�����,:����departs[name like 'aa(.)*'], �����﷨Ϊjdk�������﷨, ֧��:not rlike
	[key in ('v0', 'v1')]		IN����, ֧���ַ�������ֵ����,����: $.departs[name in ('wenshao','Yako')] $.departs[id not in (101,102)]
	[key between 234 and 456]	BETWEEN����, ֧����ֵ���ͣ�֧��not between ����: $.departs[id between 101 and 201] $.departs[id not between 101 and 201]
	length() ���� size()		���鳤��, ����$.values.size() ֧������java.util.Map��java.util.Collection������
	keySet()					��ȡMap��keySet���߶���ķǿ���������, ����$.val.keySet() ֧�����ͣ�Map����ͨ����,��֧�֣�Collection������(���� null)
	.							���Է���, ����$.name
	['key']						���Է���, ����$['name']
	['key0','key1']				������Է���, ����$['id','name']
	..							deepScan���Է���,����$..name
	*							�������������, ����$.leader.*
