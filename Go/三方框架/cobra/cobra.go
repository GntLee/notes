----------------------
cobra
----------------------
	# Doc
		https://cobra.dev/
		https://github.com/spf13/cobra
		https://pkg.go.dev/github.com/spf13/cobra
	
	# ���ĵĸ���
		* ����(Command)��	������Ҫִ�еĲ������������������Ӹ�����ϵ
		* ����(Arg)��		����Ĳ�������Ҫ�����Ķ���
		* ѡ��(Flag)��		����ѡ����Ե����������Ϊ
	
	# ���ʵ��
		* ��Ŀ�ṹ
			app
			  |-cmd
				|-add.go
				|-your.go
				|-commands.go
				|-here.go
				|-root.go
			  |-main.go
			
			* ÿ������ʵ��һ���ļ������������ļ������cmdĿ¼��
			* ����main.go����ʼ�� cobra
			
		* ��main.go �г�ʼ��cobra
			package main
			import (
			  "{pathToYourApp}/cmd"
			)

			func main() {
			  cmd.Execute()
			}
	
	
	# ˵��
