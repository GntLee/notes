---------------------
outil
---------------------
	# io�Ĺ��߰�

	# ����
		func readAll(r io.Reader, capacity int64) (b []byte, err error)
		func ReadAll(r io.Reader) ([]byte, error) 
		func ReadFile(filename string) ([]byte, error)
		func WriteFile(filename string, data []byte, perm os.FileMode) error
		func ReadDir(dirname string) ([]os.FileInfo, error)
		func NopCloser(r io.Reader) io.ReadCloser
	

	# ����Ҫ�رյ� Reader
		type nopCloser struct {
			io.Reader
		}

		func (nopCloser) Close() error 
			* ��ʵ�ֵ�close������ nil

		func NopCloser(r io.Reader) io.ReadCloser
			* ����ͨ�������������װ����һ������Ҫִ����Դ�ͷŵ�Reader

	# һ���������������ڰ�����д�뵽 /dev/null ��
		
		type devNull int

		var Discard io.Writer = devNull(0)	// ʵ����Writer�ӿ�

		var _ io.ReaderFrom = devNull(0)	// ʵ����ReaderFrom�ӿ�

		func (devNull) Write(p []byte) (int, error)
		func (devNull) WriteString(s string) (int, error) 
		func (devNull) ReadFrom(r io.Reader) (n int64, err error)
	
