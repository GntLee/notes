---------------
io os��
---------------
	# ����
		* �ļ�����/����ö��
			type FileMode uint32
			const (
				// The single letters are the abbreviations
				// used by the String method's formatting.
				ModeDir        FileMode = 1 << (32 - 1 - iota) // d: ��һ��Ŀ¼
				ModeAppend                                     // a: append-only
				ModeExclusive                                  // l: exclusive use
				ModeTemporary                                  // T: temporary file; Plan 9 only
				ModeSymlink                                    // L: symbolic link
				ModeDevice                                     // D: ��һ���豸�ļ�
				ModeNamedPipe                                  // p: named pipe (FIFO)
				ModeSocket                                     // S: Unix domain socket
				ModeSetuid                                     // u: setuid
				ModeSetgid                                     // g: setgid
				ModeCharDevice                                 // c: Unix character device, when ModeDevice is set
				ModeSticky                                     // t: sticky
				ModeIrregular                                  // ?: non-regular file; nothing else is known about this file

				// Mask for the type bits. For regular files, none will be set.
				ModeType = ModeDir | ModeSymlink | ModeNamedPipe | ModeSocket | ModeDevice | ModeCharDevice | ModeIrregular

				ModePerm FileMode = 0777 // Linux�µ���ߵ�Ȩ���ˣ�chmod 777
			)
		
		* ��ƽ̨�ķָ����������� os��
			const (
				PathSeparator     = '\\' // OS-specific path separator
				PathListSeparator = ';'  // OS-specific path list separator
			)
		
		* �ļ��������쳣
			var EOF = errors.New("EOF")
		
		* ���ļ���IO��ʽ
				const (
					O_RDONLY int = syscall.O_RDONLY // ֻ��
					O_WRONLY int = syscall.O_WRONLY // ֻд
					O_RDWR   int = syscall.O_RDWR   // ��д
					O_APPEND int = syscall.O_APPEND // ׷��ģʽ
					O_CREATE int = syscall.O_CREAT  // �ļ������ڣ��ʹ����µ�
					O_EXCL   int = syscall.O_EXCL   // �� O_CREATE���ʹ�ã�����ļ��������׳��쳣
					O_SYNC   int = syscall.O_SYNC   // ���첽IO
					O_TRUNC  int = syscall.O_TRUNC  // �򿪾ͻ�����ļ�
				)
		
		* ϵͳ��׼������/���/������
			Stdin  = NewFile(uintptr(syscall.Stdin), "/dev/stdin")
			Stdout = NewFile(uintptr(syscall.Stdout), "/dev/stdout")
			Stderr = NewFile(uintptr(syscall.Stderr), "/dev/stderr")
		
	# ���÷���
		* ���ļ�
			func OpenFile(name string, flag int, perm FileMode) (*File, error)
				* ���ļ�
					name �ļ�����
					flag IO��ʽ�����IO��ʽ������ | �����ʾ
					perm ����ļ������ڵ�����£�����ʹ�õ��� O_CREATE����ô����Ҫͨ���������ָ�������ļ���Ȩ����Ϣ(Linux�õ�һ��)��������ʹ��8���Ʊ�ʾ
				
					file, err := os.OpenFile("D:\\test.txt", os.O_EXCL | os.O_CREATE | os.O_RDWR, os.ModePerm) // �ļ�������ڣ��׳��쳣�������ھʹ������򿪶�дģʽ

					

			func Open(name string) (*File, error)
				* ��ֻ����ʽ���ļ�
				* �����Ͼ��Ƿ���: OpenFile(name, O_RDONLY, 0)
					
			
		* �����ļ�
			func Create(name string) (*File, error) 
		
		* ��ȡָ���������ļ�
			func NewFile(fd uintptr, name string) *File 
		
		* ��ȡָ���ļ�����Ϣ
			func Stat(name string) (FileInfo, error) 
		
		
		* �������ļ�
			func Rename(oldpath, newpath string) error
		
		* �����ļ���
			func Mkdir(name string, perm FileMode) error
				* �����ļ��У���������ļ��в����ڣ��׳��쳣
			func MkdirAll(path string, perm FileMode) error
				* �����༶�ļ�����������ڶ��ᴴ��
		
		
		* ɾ���ļ�
			func Remove(name string) error
			func RemoveAll(path string) error
		
		* ��ȡ�ܵ���
			func Pipe() (r *File, w *File, err error)
		
		* �ض��ļ�
			func Truncate(name string, size int64) error
		
		* �л�����Ŀ¼
			func Chdir(dir string) error
		
		* �޸��ļ����û�/�û���/Ȩ����Ϣ
			func Chown(name string, uid, gid int)
			func Lchown(name string, uid, gid int) error
			func Chmod(name string, mode FileMode) error
		
		* �޸��ļ���ʱ������
			func Chtimes(name string, atime time.Time, mtime time.Time) error
	
	# ϵͳĿ¼���
		* ��ȡ��ʱĿ¼
			func TempDir() string 
		
		* ��ȡ���ݻ���Ŀ¼
			func UserCacheDir() (string, error)
		
		* ��ȡ����Ŀ¼
			func UserConfigDir() (string, error)
		
		* ��ȡ�û���homeĿ¼
			func UserHomeDir() (string, error)
		
	# �������
		* ����Ӳ����
			func Link(oldname, newname string) error
			
		* ���������ķ������ӵ�Ŀ��
			func Readlink(name string) (string, error)
			
		
		func Symlink(oldname, newname string) error
		

	# �ļ� File �ĳ��÷���
		func (f *File) Name() string 
		func (f *File) Close() error
			* �ͷ���Դ
		
		func (f *File) Read(b []byte) (n int, err error)
			* ��ȡ�ֽ����ݵ��ļ������ض�ȡ�����ֽ������쳣��Ϣ
			* �����ȡ��ĩβ������ (0, io.EOF)

		func (f *File) ReadAt(b []byte, off int64) (n int, err error)
			

		func (f *File) ReadFrom(r io.Reader) (n int64, err error)
		func (f *File) Write(b []byte) (n int, err error)
		func (f *File) WriteAt(b []byte, off int64) (n int, err error)
		func (f *File) Seek(offset int64, whence int) (ret int64, err error)
			* �ƶ�ָ��
				offset ƫ��
				whence λ��
						os.SEEK_SET int = 0 // �ļ���ͷ
						os.SEEK_CUR int = 1 // ��ǰλ��
						os.SEEK_END int = 2 // �ļ�����

		func (f *File) WriteString(s string) (n int, err error)
		func (f *File) Chmod(mode FileMode) error
		func (f *File) SetDeadline(t time.Time) error
		func (f *File) SetReadDeadline(t time.Time) error
		func (f *File) SetWriteDeadline(t time.Time) error
		func (f *File) Stat() (FileInfo, error)
			* �����ļ���Ϣ����һ���ӿ�
				type FileInfo interface {
					Name() string       // base name of the file
					Size() int64        // length in bytes for regular files; system-dependent for others
					Mode() FileMode     // file mode bits
					ModTime() time.Time // modification time
					IsDir() bool        // abbreviation for Mode().IsDir()
					Sys() interface{}   // underlying data source (can return nil)
				}

		func (f *File) Chown(uid, gid int) error
			* �޸�������/��

		func (f *File) Truncate(size int64) error
		func (f *File) Sync() error
		func (f *File) SyscallConn() (syscall.RawConn, error)
		func (f *File) Chdir()
		func (f *File) Fd() uintptr
		func (f *File) Readdir(n int) ([]FileInfo, error)
		func (f *File) Readdirnames(n int) (names []string, err error)
		


------------------
�쳣�Ĵ���
------------------
	# �����ļ����쳣�������˼����ӿ�
		* PathError
			type PathError struct {
				Op   string
				Path string
				Err  error
			}
			func (e *PathError) Error() string { return e.Op + " " + e.Path + ": " + e.Err.Error() }
			func (e *PathError) Unwrap() error { return e.Err }
			// Timeout reports whether this error represents a timeout.
			func (e *PathError) Timeout() bool {
				t, ok := e.Err.(timeout)
				return ok && t.Timeout()
			}
		* SyscallError
			type SyscallError struct {
				Syscall string
				Err     error
			}
			func (e *SyscallError) Error() string { return e.Syscall + ": " + e.Err.Error() }
			func (e *SyscallError) Unwrap() error { return e.Err }
			// Timeout reports whether this error represents a timeout.
			func (e *SyscallError) Timeout() bool {
				t, ok := e.Err.(timeout)
				return ok && t.Timeout()
			}
	
	# Ԥ������쳣
		var (
			// ErrInvalid indicates an invalid argument.
			// Methods on File will return this error when the receiver is nil.
			ErrInvalid = errInvalid() // "invalid argument"

			ErrPermission       = errPermission()       // "permission denied"
			ErrExist            = errExist()            // "file already exists"
			ErrNotExist         = errNotExist()         // "file does not exist"
			ErrClosed           = errClosed()           // "file already closed"
			ErrNoDeadline       = errNoDeadline()       // "file type does not support deadline"
			ErrDeadlineExceeded = errDeadlineExceeded() // "i/o timeout"
		)
	
	# �쳣���жϷ���
		func IsExist(err error) bool 
		func Lstat(name string) (FileInfo, error) 
		func IsNotExist(err error) bool 
			* �жϸ��쳣�ǲ�����Ϊ�ļ������ڵ��µ�
				if _, err := os.Open("D:\\noExists"); os.IsNotExist(err){
					fmt.Println("�ļ�������")
				}

		func IsPermission(err error) bool 
		func IsTimeout(err error) bool 
		

		

---------------
demo
---------------
	# ��ȡ�ļ�
		file, err := os.Open("D:\\javaweb_community.sql")
		if err != nil {
			fmt.Println("�쳣��" + err.Error())
		}
		defer file.Close()
		// ���ݻ�������
		buffer := make([]byte, 1024)
		for  {
			count, err := file.Read(buffer)
			if err == io.EOF || count == 0{
				// ������ĩβ
				break
			}
			// ��ȡ�����ֽ�����
			data := buffer[:count]
			fmt.Println(string(data))
		}
	

