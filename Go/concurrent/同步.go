------------------
sync
------------------
	# ͬ����
		* ����ʹ��channel��ͨ�ţ�����Ҳ�ṩ�������ƣ����������ķ���ͬһ���ڴ�
		* ����� Lokcer �ӿ�
			type Locker interface {
				Lock()
				Unlock()
			}

		* �� sync �����ṩ��2���������Ƕ�ʵ���� Locker �ӿ�

		* ������
			struct Mutex
				func (m *Mutex) Lock()
				func (m *Mutex) Unlock()
					* �������ͷ���
					
		
		* ��д������д���⣬����������

		struct RWMutex
			func (rw *RWMutex) RLock()
			func (rw *RWMutex) RUnlock()
				* �����������ͷ���

			func (rw *RWMutex) Lock() 
			func (rw *RWMutex) Unlock()
				* д������д�ͷ���

			func (rw *RWMutex) RLocker() Locker
				* ��ȡ������
	
	
	# ȫ��Ψһ�Ե��ò���
		* ��ȫ�ֵĽǶ���˵��ֻ��Ҫ����һ�εĴ��룬����ȫ�ֳ�ʼ������
		* sync�����ṩ��һ��Once��������֤ȫ�ֵ�Ψһ�Բ���
			
		struc Once 
			func (o *Once) Do(f func())

		
		* demo
			var val string
			// ��ȡonce
			var once sync.Once
			func Init(){
				val = "Hello World"
				fmt.Println("init")
			}
			func Test(){
				// ͨ��onceִ�� ����
				once.Do(Init)
				fmt.Println(val)
			}
			func main(){
				go Test()
				go Test()
			}
			
			* once��Do()�������Ա�֤��ȫ�ַ�Χ��ֻ����ָ���ĺ���һ�Σ�����ָsetup()��������
	
	# WaitGroup��ȫ�ֵȴ���������Java�� CountDownLatch
		struct WaitGroup
			func (wg *WaitGroup) Add(delta int)
				* ���������ǳ�ʼ��һ��ֵ�������ڿ�ʼ�첽ִ��֮ǰ��ʼ�����

			func (wg *WaitGroup) Done()
				* ��ֵ���� -1 ����

			func (wg *WaitGroup) Wait() 
				* ������ֱ��group�е�ֵ����Ϊ��0
	

		

		
