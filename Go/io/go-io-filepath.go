-----------------
filepath
-----------------
	# �ļ�·����ص�api
	# ����
		const (
			Separator     = os.PathSeparator			// ��ƽ̨���ļ��ָ���
			ListSeparator = os.PathListSeparator
		)

	# ����
		func Abs(path string) (string, error)
		func IsAbs(path string) bool
		func HasPrefix(p, prefix string) bool 
		func Match(pattern, name string) (matched bool, err error) 
		func Glob(pattern string) (matches []string, err error)
		func Clean(path string) string
		func ToSlash(path string) string 
		func FromSlash(path string) string
		func SplitList(path string) []string 
		func Split(path string) (dir, file string) 
		func Join(elem ...string) string
			* ����ļ�·������ϳ�һ��·�������Զ���ӿ�ƽ̨�ķָ���

		func Ext(path string) string 
		func EvalSymlinks(path string) (string, error)
		func Rel(basepath, targpath string) (string, error) 
		func Base(path string) string 
		func Dir(path string) string
		func VolumeName(path string) string 

		func Walk(root string, walkFn WalkFunc) error
			* ����ָ�����ļ���
			WalkFunc 
				* ��һ��������type WalkFunc func(path string, info os.FileInfo, err error) error