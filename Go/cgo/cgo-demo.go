----------------------------
ֱ����Go��дC������
----------------------------
package main

/*
#include <stdio.h>
void hello(char* s){ // ��������
	puts(s);
}
*/
import "C"

func main() {
	// ֱ�ӵ��ú���
	C.hello(C.CString("Hello Cgo"))
}

----------------------------
ͨ��c�ӿڣ���������ʵ��
----------------------------
	# Go ���÷�
		package main

		/*
		#include "hello.h"  // ���������õĽӿ�
		*/
		import "C"

		func main() {
			// ���ýӿ��еĺ���
			C.hello(C.CString("Hello Cgo"))
		}
	
	# hello.h ������ӿ�
		// hello.h
		void hello(const char* s);  // ��������
	
	# hello.c ��ΪC��ʵ��
		// hello.c
		#include <stdio.h>
		#include "hello.h"		// ����ӿ�

		// ʹ��C����ʵ�ֽӿ��еķ���
		void hello(const char* s){
			puts(s);
		}
	
	# hello.cpp ��Ϊc++��ʵ��
		#include <iostream>
		extern "C" {
			#include "hello.h"		// ����C�ӿ�
		}

		// ʹ��c++ ʵ�ֽӿ��еķ���
		void hello(const char* s) {
			std::cout << s;
		}
	
	# hello.go ��Ϊgo��ʵ��
		package main

		import "C"
		import "fmt"

		// ʹ�� export ����Ϊc����
		//export hello
		func hello(s *C.char){
			fmt.Println(C.GoString(s))
		}
		
		* export �������//֮�󣬲����пո񣬲��Ҷ�ռһ�У����治�����κ���������
	
	# ���ʵ�֣�ֻ�ܴ���һ��

----------------------------
����C�ӿڵ�Go���
----------------------------
	package main
	/*
	void hello(_GoString_ s); // ��������
	*/
	import "C"

	import "fmt"

	func main() {
		// ���ú���
		C.hello("Hi Cgo")
	}

	// ʵ�ֲ��ҵ�������
	//export hello
	func hello(s string){
		fmt.Println(s)
	}

	* _GoString_Ԥ�����C�������ͣ�������ʾGo�����ַ���