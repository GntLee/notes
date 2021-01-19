-----------------
ģ�鳣��
-----------------
	const Compiler = "gc"
	const GOARCH string = sys.GOARCH
	const GOOS string = sys.GOOS

	var MemProfileRate int = 512 * 1024


-----------------
�ṹ��
-----------------

-----------------
�Զ�������
-----------------
	# type MemStats struct {
			Alloc uint64
			TotalAlloc uint64
			Sys uint64
			Lookups uint64
			Mallocs uint64
			Frees uint64
			HeapAlloc uint64
			HeapSys uint64
			HeapIdle uint64
			HeapInuse uint64
			HeapReleased uint64
			HeapObjects uint64
			StackInuse uint64
			StackSys uint64
			MSpanInuse uint64
			MSpanSys uint64
			MCacheInuse uint64
			MCacheSys uint64
			BuckHashSys uint64
			GCSys uint64
			OtherSys uint64
			NextGC uint64
			LastGC uint64
			PauseTotalNs uint64
			PauseNs [256]uint64
			PauseEnd [256]uint64
			NumGC uint32
			NumForcedGC uint32
			GCCPUFraction float64
			EnableGC bool
			DebugGC bool
			BySize [61]struct {
				Size uint32
				Mallocs uint64
				Frees uint64
			}
		}
	
	
	# type Error interface {
			error
			RuntimeError()
		}

		* ����ʱ�쳣

	# type Func struct {
		}
		func FuncForPC(pc uintptr) *Func
			* ����pc����fanc��Ϣ

		func (f *Func) Entry() uintptr
		func (f *Func) FileLine(pc uintptr) (file string, line int)
		func (f *Func) Name() string
			* ���غ���������
	
	# type Frames struct {
		}
		* �������ջ��Ϣ

		func CallersFrames(callers []uintptr) *Frames
		func (ci *Frames) Next() (frame Frame, more bool)

-----------------
����
-----------------
	func BlockProfile(p []BlockProfileRecord) (n int, ok bool)
	func Breakpoint()
	func CPUProfile() []byte
	func Caller(skip int) (pc uintptr, file string, line int, ok bool)
		* ����ָ���ĵ���ջ��ָ�룬�ļ����ƣ��кţ��Ƿ��ȡ�ɹ�

	func Callers(skip int, pc []uintptr) int
		* ���ص���ջ�ĳ��������, �ŵ�һ��uintptr��

	func GC()
	func GOMAXPROCS(n int) int
		* ���ÿ���ͬʱִ�е����CPU������������ǰ�����á����n <1���򲻻���ĵ�ǰ���á�
		* ����ʹ��NumCPU��ѯ���ؼ�����ϵ��߼�CPU������
		* ʵ���߳���Ҫ�������õ������Ҫ����ʱ������ԶԶ�������õ���ֵ

	func GOROOT() string
	func Goexit()
	func GoroutineProfile(p []StackRecord) (n int, ok bool)
	func Gosched()
	func KeepAlive(x interface{})

	func UnlockOSThread()
	func LockOSThread()
		* ��ѵ�ǰgoroutine���ڵ�ǰ��ϵͳ�߳��ϣ����goroutine����������߳���ִ�У�����Ҳ����������goroutine������߳���ִ�С�
		* ֻ�����goroutine��������ͬ������ UnlockOSThread ����֮�󣬲Ż���н��
		* ���goroutine���˳���ʱ��û��unlock����̣߳���ô����̻߳ᱻ��ֹ��

	func MemProfile(p []MemProfileRecord, inuseZero bool) (n int, ok bool)
	func MutexProfile(p []BlockProfileRecord) (n int, ok bool)
	func NumCPU() int
		* ��ȡCPU�ں˵�����

	func NumCgoCall() int64
	func NumGoroutine() int
	func ReadMemStats(m *MemStats)
	func ReadTrace() []byte
	func SetBlockProfileRate(rate int)
	func SetCPUProfileRate(hz int)
	func SetCgoTraceback(version int, traceback, context, symbolizer unsafe.Pointer)
	func SetFinalizer(obj interface{}, finalizer interface{})
	func SetMutexProfileFraction(rate int) int
	func Stack(buf []byte, all bool) int
		* д�����ջ��Ϣ��buf
	
	func StartTrace() error
	func StopTrace()
	func ThreadCreateProfile(p []StackRecord) (n int, ok bool)
	
	func Version() string
		* ����Go�İ汾


-----------------
Demo
-----------------
	# ��ȡ���÷���������
		func printMyName() string {
			pc, _, _, _ := runtime.Caller(1)
			return runtime.FuncForPC(pc).Name()
		}