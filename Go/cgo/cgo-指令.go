
--------------------
cgo ָ��
--------------------
	# �����������ע���У����һЩ����׶κ����ӽ׶ε���ز�����ʹ�� #cgo ָ����
		// #cgo CFLAGS: -DPNG_DEBUG=1
		// #cgo amd64 386 CFLAGS: -DX86=1
		// #cgo LDFLAGS: -lpng
		// #include <png.h>
		import "C"

		* ����׶εĲ�����Ҫ���ڶ�����غ��ָ��ͷ�ļ�����·��
		* ���ӽ׶εĲ�����Ҫ��ָ�����ļ�����·����Ҫ���ӵĿ��ļ�
	
	#cgo CFLAGS: -DPNG_DEBUG=1 -I./include
		* �������C���Դ������ñ������

		-D �����˺�PNG_DEBUG��ֵΪ1��
		-I ������ͷ�ļ������ļ���Ŀ¼��
	
	#cgo LDFLAGS: -L/usr/local/lib -lpng
		* ��������ʱ�Ĳ���
		* �����ӽ׶Σ�C��C++������ѡ����ͨ�õģ�������ʱ���Ѿ�������C��C++���Ե��������ǵ�Ŀ���ļ�����������ͬ��

		-L
			* ָ��������ʱ���ļ�����Ŀ¼��-lָ��������ʱ��Ҫ����png�⡣

		* ��ΪC/C++���������⣬Cͷ�ļ�����Ŀ¼���������Ŀ¼�����ǿ��ļ�����Ŀ¼����Ҫ����·����
		* �ڿ��ļ��ļ���Ŀ¼�п���ͨ��${SRCDIR}������ʾ��ǰ��Ŀ¼�ľ���·����
			#cgo LDFLAGS: -L${SRCDIR}/libs -lfoo
			չ���� ��
			#cgo LDFLAGS: -L/go/src/foo/libs -lfoo
	
	#cgo CXXFLAGS
		* C++���еı���ѡ��

	#cgo FFLAGS
		* C�������еı���ѡ��

	#cgo CPPFLAGS
		* C��C++���еı���ѡ��
	
	# ����ָ��

		* ������ĳ������ϵͳ��ĳ��CPU�ܹ�����ʱ����ı��������ѡ����Ч

		#cgo [os] CFLAGS: -DX86=1
			* ����ָ����os�£������ָ����Ч
			* os֧��
				windows
				darwin
				linux
			* Ҳ����ͨ��!ȡ��
				#cgo !windows LDFLAGS: -lm
				* ��windows�����£������������Ч
		
		* �ڲ�ͬ��ϵͳ��cgo��Ӧ�Ų�ͬ��c����
			* ������ʹ��#cgoָ��岻ͬ��C���Եĺ꣬Ȼ��ͨ���������ֲ�ͬ��
			package main
			/*
			#cgo windows CFLAGS: -DCGO_OS_WINDOWS=1
			#cgo darwin CFLAGS: -DCGO_OS_DARWIN=1
			#cgo linux CFLAGS: -DCGO_OS_LINUX=1
			#if defined(CGO_OS_WINDOWS)
				const char* os = "windows";
			#elif defined(CGO_OS_DARWIN)
				const char* os = "darwin";
			#elif defined(CGO_OS_LINUX)
				const char* os = "linux";
			#else
			#    error(unknown os)
			#endif
			*/
			import "C"

			func main() {
				print(C.GoString(C.os))
			}

--------------------
 build tag ��������
--------------------
	