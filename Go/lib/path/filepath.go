-------------------------
filepath
-------------------------

-------------------------
����
-------------------------
	const (
		Separator     = os.PathSeparator
		ListSeparator = os.PathListSeparator
	)

	var ErrBadPattern = errors.New("syntax error in pattern")
	
	* ����Ŀ¼��ʱ�򣬿��Է�������쳣����������ǰĿ¼
		var SkipDir = errors.New("skip this directory")


-------------------------
type
-------------------------
	# type WalkFunc func(path string, info os.FileInfo, err error) error

-------------------------
����
-------------------------
	func Abs(path string) (string, error)
		* ���� path ����ľ���·������� path ���Ǿ���·��������뵱ǰ����Ŀ¼��ʹ֮��Ϊ����·����


	func Base(path string) string
		* ����·�������һ��Ԫ�ء�����ȡԪ��ǰ��ȥ��ĩβ��б�ܡ�
		* ���·���� ""���᷵�� "."�����·����ֻ��һ��б�˹��ɵģ��᷵�� "/"��
			"C:\go-project"			-> go-project
			"C:\go-project\"		-> go-project
			"C:\go-project\a.jpg"	-> a.jpg
			""						-> .

	func Clean(path string) string
	func Dir(path string) string
		* ����·���г�ȥ���һ��·��Ԫ�صĲ��֣�����·�����һ��Ԫ�����ڵ�Ŀ¼


	func EvalSymlinks(path string) (string, error)
	func Ext(path string) string
		* ��ȡ��׺����
	
	func FromSlash(path string) string
	func Glob(pattern string) (matches []string, err error)
	func HasPrefix(p, prefix string) bool
	func IsAbs(path string) bool
		* ����·���Ƿ���һ������·��
	
	func Join(elem ...string) string
		* ƴ��·��

	func Match(pattern, name string) (matched bool, err error)
	func Rel(basepath, targpath string) (string, error)
	func Split(path string) (dir, file string)
		* ��path�ָ�ΪĿ¼���ļ���������
		* ���û��Ŀ¼���� dir �ǿ��ַ���

	func SplitList(path string) []string
	func ToSlash(path string) string
	func VolumeName(path string) string

	func Walk(root string, walkFn WalkFunc) error
		* ����Ŀ¼�������walkFn���� SkipDir ����������ǰĿ¼����������
		* ��Ŀ¼Walk��Ч�ʻ�ܵͣ����������ݷ�ʽ
