--------------------------
user
--------------------------
	# ϵͳ�û���ص�Api


--------------------------
var
--------------------------


--------------------------
type
--------------------------
	# type Group struct {
			Gid  string // group ID
			Name string // group name
		}
		
		* �û���

		func LookupGroup(name string) (*Group, error)
		func LookupGroupId(gid string) (*Group, error)

	# type UnknownGroupError string
		func (e UnknownGroupError) Error() string
	
	# type UnknownGroupIdError string
		func (e UnknownGroupIdError) Error() string
	
	# type UnknownUserError string
		func (e UnknownUserError) Error() string
	
	# type UnknownUserIdError int
		func (e UnknownUserIdError) Error() string
	
	# type User struct {
			Uid string			// �û�ID
			Gid string			// �û���ID
			Username string		// ��¼�˻���
			Name string			// �Զ�������
			HomeDir string		// HomeĿ¼
		}

		* ϵͳ�û�

		func Current() (*User, error)
		func Lookup(username string) (*User, error)
		func LookupId(uid string) (*User, error)
			* ��ȡ��ǰ�û�/�����û���/�û�ID��ѯ�û�


		func (u *User) GroupIds() ([]string, error)


--------------------------
func
--------------------------

