----------------------
cobra
----------------------
	# Doc
		https://cobra.dev/
		https://github.com/spf13/cobra
		https://pkg.go.dev/github.com/spf13/cobra
	
	
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

----------------------
����
----------------------

	# ����(Command)��	������Ҫִ�еĲ������������������Ӹ�����ϵ
		* ������������ڷ������ᰴ������˳��ִ��
			1. PersistentPreRun
			2. PreRun
			3. Run
			4. PostRun
			5. PersistentPostRun

			* PersistentPreRun ������ PersistentPostRun ����������κ��������ִ��

	# ����(Arg)��		����Ĳ�������Ҫ�����Ķ���
		* Ĭ�ϵ���֤����
			NoArgs
				* ��������κ�λ�ò��������������
			ArbitraryArgs
				* �����������κ�λ�ò���
			OnlyValidArgs
				* ������κ�λ�ò������������ ValidArgs �ֶ��У����������
			MinimumNArgs(int)
				* ����Ҫ�� N ��λ�ò��������򱨴�
			MaximumNArgs(int)
				* ���λ�ò������� N ��������
			ExactArgs(int)
				* ������ N ��λ�ò��������򱨴�
			ExactValidArgs(int) 
				* ������ N ��λ�ò������Ҷ�������� ValidArgs �ֶ��У����򱨴�
			RangeArgs(min, max)
				* ���λ�ò����ĸ����������� min �� max ֮�У�����
		
		
	# ѡ��(Flag)��		����ѡ����Ե����������Ϊ
		* persistent ���͵�ѡ��ȿ������ø��� Command���ֿ������ø��� Command ���� Command
		* local ���͵�ѡ��ֻ�����ø�ָ���� Command
		* Ĭ������µ�ѡ��ǿ�ѡ�ģ��������Ҫ���û��ṩѡ�����ͨ�� MarkFlagRequired ָ��

	