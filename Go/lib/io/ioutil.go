---------------------
ioutil
---------------------
	# ���ķ������Զ��� io/os ���ж����ˣ�����ֱ��ʹ��io/os��

---------------------
����
---------------------
	* �ڶ�Writer
		var Discard io.Writer = io.Discard
		* io����

---------------------
type
---------------------


---------------------
����
---------------------
	func NopCloser(r io.Reader) io.ReadCloser
		* ��Reader��װ��ReadClose�����ṩһ����ʵ�ֵ�Close����
		* * io����

	func ReadAll(r io.Reader) ([]byte, error)
		* ��ȡr�е��������ݷ���
		* io����

	func ReadDir(dirname string) ([]os.FileInfo, error)
		* os����

	func ReadFile(filename string) ([]byte, error)
		* ��ȡ�ļ��е��������ݷ���
		* os����

	func TempDir(dir, pattern string) (name string, err error)
	func TempFile(dir, pattern string) (f *os.File, err error)
		* ������ʱĿ¼/�ļ�����Ҫ�Լ�ɾ��
		* ��� dir �ǿ��ַ�������ô��Ĭ�ϵ���ϵͳ����ʱĿ¼ȥ����
		* os����

	func WriteFile(filename string, data []byte, perm os.FileMode) error
		* ���ٵ�д�����ݵ��ļ���
		* os����
	
