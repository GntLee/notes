------------------------
errors
------------------------


------------------------
var
------------------------

------------------------
type
------------------------


------------------------
func
------------------------
    func As(err error, target interface{}) bool
		* target ����Ϊnil�����ұ�����ʵ����error�Ľӿ�

    func Is(err, target error) bool
		* ���� targer ���쳣���У��Ƿ���err����
		* ���targetʵ���� Is ��������ͨ������ӿ��жϣ����targetʵ����Unwrap�������᲻�ϰ������װ���쳣���бȽ�

    func New(text string) error
		* ����text����һ���쳣

    func Unwrap(err error) error
		* ����쳣��һ����װ�쳣��Ҳ����ʵ����: Unwrap() error 
		* ��ô�ͻ᷵�ذ�װ���쳣�����򷵻�nil
