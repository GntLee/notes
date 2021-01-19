-------------------------
�쳣����
-------------------------
	# Go������У������еĴ��󣬶�����ֵ�����д���Ŀǰ(V1.12)û���쳣�������

	#һ��ʹ��panic/recover ģʽ�������쳣
		func panic(v interface{})
			* �����׳��쳣���������κεط�����
			* �쳣�׳��󣬳��򲻻�����ִ��
				func main() {
					fmt.Println("start")
					panic("�쳣��")
					// ����Ĵ��벻��ִ��
				}
				// -----------
				start
				panic: �쳣��

				goroutine 1 [running]:
				main.main()
					d:/golang/main
			

		
		func recover() interface{}
			* ��������������Ի�ȡpanic�׳����쳣��Ϣ
			* ֻ���� defer ���õĺ�������Ч������Ҫ�����ڿ������� panic �����֮ǰ
			
				func main() {
					fmt.Println("start")
					defer func(){
						err := recover()
						if err != nil {
							fmt.Println(err)
							fmt.Printf("%T\n", err)
						}
					}()
					panic("�쳣��")
					// ����Ĵ��벻��ִ��
				}
				// -------------
				start
				�쳣��
				string
			
			* ���������޸��쳣�ķ���ֵ
				func foo() (err error) {
					defer func (){
						if p := recover(); p != nil{
							// ��װ�쳣��Ϣ������ֵ
							err = fmt.Errorf("�쳣�ˣ�%v", p)
						}
					}()
					// ����ִ�п��ܻ��쳣�Ĵ���
				}

			* һ��panic��Ӧһ��recover
			* һ��panicֻ�ᱻ�Լ����������һ��recover����
			* ��Ƕ�׵�defer�����е���recoverҲ�������޷������쳣
				func main() {
					defer func() {
						defer func() {
							// �޷������쳣
							if r := recover(); r != nil {
								fmt.Println(r)
							}
						}()
					}()
					panic(1)
				}
			* defer�е��õ���recover�����İ�װ�����Ļ����쳣�Ĳ�������ʧ��
				func main() {
					defer func() {
						// �޷������쳣
						if r := MyRecover(); r != nil {
							fmt.Println(r)
						}
					}()
					panic(1)
				}

				func MyRecover() interface{} {
					log.Println("trace...")
						return recover()
				}
			* ����Ҫ�����쳣��ջֻ֡��һ��ջ֡��recover�����������������쳣
			* Ҳ������defer�м���panic����ô���panicҲ��ֻ�ᱻ�Լ����������һ��recover����


-------------------------
error �ӿ�
-------------------------
	# Go�����ж����Error�ӿ�
		type error interface {
			Error() string
		}

	# ϵͳ��errors��Ԥ������һЩ�쳣��صķ���
		func New(text string) error 
			* ��������ϵͳ�ṩ��һ��errorʵ��
				type errorString struct {
					s string
				}
				func (e *errorString) Error() string {
					return e.s
				}

		func As(err error, target interface{}) bool
			* target ����Ϊnil�����ұ�����ʵ����error�Ľӿ�

		func Is(err, target error) bool
			* ���� targer ���쳣���У��Ƿ���err����
			* ���targetʵ���� Is ��������ͨ������ӿ��жϣ����targetʵ����Unwrap�������᲻�ϰ������װ���쳣���бȽ�

		func Unwrap(err error) error
			* ����쳣��һ����װ�쳣��Ҳ����ʵ����: Unwrap() error 
			* ��ô�ͻ᷵�ذ�װ���쳣�����򷵻�nil

	
	# ͨ�� fmt��Errorf��ʽ��һ��error����
		func Errorf(format string, a ...interface{}) error {
			p := newPrinter()
			p.wrapErrs = true
			p.doPrintf(format, a)
			s := string(p.buf)
			var err error
			if p.wrappedErr == nil {
				err = errors.New(s)
			} else {
				err = &wrapError{s, p.wrappedErr}
			}
			p.free()
			return err
		}
		type wrapError struct {
			msg string
			err error
		}

		func (e *wrapError) Error() string {
			return e.msg
		}

		func (e *wrapError) Unwrap() error {
			return e.err
		}


	# ����ʱ�쳣�������� rumtime ��
		type Error interface {
			error
			RuntimeError()
		}


		