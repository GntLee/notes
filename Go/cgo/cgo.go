--------------------
cgo
--------------------
	# Go����ͨ���Դ���һ����CGO�Ĺ�����֧��C���Ժ������ã�ͬʱ������Go���Ե���C��̬��ӿڸ���������ʹ��
		https://golang.org/cmd/cgo/
		https://github.com/golang/go/wiki/cgo
		https://blog.golang.org/cgo
		https://golang.org/misc/cgo/
		https://golang.org/src/runtime/cgocall.go

	# ѧϰ�ο�
		https://colobu.com/2018/06/13/cgo-articles/

	# ͨ�� import "C" �������CGO���ԣ����뼴����
		package main
		import (
			"C" 		// go build������ڱ�������ӽ׶�����gcc������
			"fmt"
		)
		func main() {
			fmt.Println("Hello")
		}
		
		* �������û���κ���C�йصĴ��룬���������ʣ�����ȷ��һ��Cgo����
	

	
	# import "C"���
		* �����Go�����г�����import "C"������ʾʹ����CGO���ԣ�����(�����пո�)���������ǰ���ע����һ�������﷨�������������������C���Դ��롣
		* ��ȷ��CGO���õ�����£��������ڵ�ǰĿ¼�а���C/C++��Ӧ��Դ�ļ���
			package main

			/*
			#include <stdio.h>
			void printint(int v) {
				printf("printint: %d\n", v);
			}
			*/
			import "C"

			func main() {
				v := 42
				C.printint(C.int(v))
			}


		* ͷ�ļ���include֮����������е�C����Ԫ�ض��ᱻ���뵽 "C" �������İ���
		* ��Ҫע����ǣ�import "C" ���������Ҫ����һ�У�������������һͬimport��
	
	# CGO����������Զ�������ǰĿ¼�µ�CԴ�ļ�
		* Ҫע��C��������ͻ
	
	# Ҫ��֤�������� CGO_ENABLED ������Ϊ1
		* ���ʾCGO�Ǳ����õ�״̬
		* �ڱ��ع���ʱCGO_ENABLEDĬ�������õģ������湹��ʱCGOĬ���ǽ�ֹ��

	
	# cgo����ǰ�����õ�C���Է��Ŷ��ŵ��������C����
		* ����������������һ���������ͣ��ṩһ������
			package cgo_helper
			//#include <stdio.h>
			import "C"

			type CChar C.char

			func (p *CChar) GoString() string {
				return C.GoString((*C.char)(p))
			}

			func PrintCString(cs *C.char) {
				C.puts(cs)
			}

		* ��main�����ã���ʧ��
			package main
			/*
			static const char* cs = "hello Cgo";
			*/
			import "C"

			import (
				"go-project/cgo_helper"
			)

			func main() {
				cgo_helper.PrintCString(C.cs)  // undefined reference to `cs'
			}


		* ��ǰ������������Go���԰��ڲ�����Ҳͨ��cgo���������Ƶ�����C������ͬ��Go���԰�����������C��֮��������ǲ���ͨ�õ�
		* ��Go�����з��������������ʹ��ڵģ���ͬGo��������������C��������ȴ�ǲ�ͬ�ģ�main.C����cgo_helper.C�����⵼�´��������������Go����Ҳ�ǲ�ͬ�����ͣ�*main.C.char����*cgo_helper.C.char��
		* ����֮��һ��������ڹ����Ľӿ���ֱ��ʹ����*C.char�����Ƶ�����C�������ͣ�������Go�����޷�ֱ��ʹ����Щ���͵ģ��������Go��ͬʱҲ�ṩ��*C.char���͵Ĺ��캯��




		
	

