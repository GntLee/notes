----------------------
embed 
----------------------

----------------------
type 
----------------------
	# type FS struct {
		}

		* ʵ���� fs.FS �ӿ�
	
		func (f FS) Open(name string) (fs.File, error)
		func (f FS) ReadDir(name string) ([]fs.DirEntry, error)
			* �ļ�·������Ҫ��ȷд���Լ��ĸ���Ŀ¼������ᱨ��
			* ��ΪǶ����Դ�ǰ����洢·����ͬ�Ľṹ�洢�ģ���ͨ�����ôָ���޹ء�

		func (f FS) ReadFile(name string) ([]byte, error)

		* ʵ���� fs.FS �ӿڣ����������ı�������
			func WalkDir(fsys FS, root string, fn WalkDirFunc) error 

----------------------
func 
----------------------