----------------
driver
----------------
	# ��������Ҳ����sql�ӿڵ�ʵ�֣���ͨ��Ҳ��ù���ʵ

----------------
var
----------------
	var Bool boolType
	var DefaultParameterConverter defaultConverter
	var ErrBadConn = errors.New("driver: bad connection")
	var ErrRemoveArgument = errors.New("driver: remove argument from query")
	var ErrSkip = errors.New("driver: skip fast-path; continue as if unimplemented")
	var Int32 int32Type
	var ResultNoRows noRows
	var String stringType

----------------
type
----------------

----------------
func
----------------
	func IsScanValue(v interface{}) bool
	func IsValue(v interface{}) bool