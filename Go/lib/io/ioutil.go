---------------------
ioutil
---------------------

---------------------
����
---------------------
	* �ڶ�Writer
		var Discard io.Writer = devNull(0)

---------------------
type
---------------------


---------------------
����
---------------------
	func NopCloser(r io.Reader) io.ReadCloser
		* ��Reader��װ��ReadClose�����ṩһ����ʵ�ֵ�Close����

	func ReadAll(r io.Reader) ([]byte, error)
		* ��ȡr�е��������ݷ���

	func ReadDir(dirname string) ([]os.FileInfo, error)
	func ReadFile(filename string) ([]byte, error)
		* ��ȡ�ļ��е��������ݷ���

	func TempDir(dir, pattern string) (name string, err error)
	func TempFile(dir, pattern string) (f *os.File, err error)
		* ������ʱĿ¼/�ļ�����Ҫ�Լ�ɾ��
		* ��� dir �ǿ��ַ�������ô��Ĭ�ϵ���ϵͳ����ʱĿ¼ȥ����

	func WriteFile(filename string, data []byte, perm os.FileMode) error
		* ���ٵ�д�����ݵ��ļ���