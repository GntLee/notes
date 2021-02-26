---------------------------
ģ��
---------------------------
	# ģ��
		https://blog.golang.org/using-go-modules
	
	# �鿴�������
		go help mod
		go help modules
		go help module-ge

	# ����
		* ���û�������
			GO111MODULE=on

	# �ֿ����
		* ���û�������
			export GOPROXY=https://goproxy.io
		
		* ͨ����������
			go env -w GOPROXY=https://goproxy.io,direct
	
	# ��ʼ��һ��go mod
		go mod init [module]
			module
					* ָ��ģ�����ƣ������Ǵ�·����
	
	# �鿴ģ�������
		go list -m all
			-m 
				* ����ָ��ֻ�鿴ָ����������Ϣ��
			
			all
				* ��ʾ�鿴����
			main
				* ��ִ�г���Ķ����
			std
				* ��׼��İ�
			cmd
				* go�ֿ���������й��ߺ����ǵ��ڲ���

			-versions
				* �г������İ汾���б�
			
			-json
				* ָ����ʽ�����ΪJSON
			
			-deps
				* �г���������

			
		
	# ͨ�������޸������汾
		go get [module]@[version]
			module
				* ָ��ģ��
			version
				* ָ���汾

			-u
				* ���صĿ�ʹ�����µ�minor �� patch���°�
	
	# ͨ���޸�go.mod�ļ��޸������汾
		go mod edit -require="[module]@[version]"
			module
				* ָ��ģ��
			version
				* ָ���汾
	# ����������Ϣ
		go mod tidy
			* ���ظ�������
			* ���Զ����������Ҫ�������ͬʱ���Խ���������µ���ǰ�汾

---------------------------
����
---------------------------
	#  go mod <command> [arguments]
	
	# command
		download    
			* �������������ػ���
			* Ĭ�ϻ����� $GOPATH/pkg/mod
			
        edit        edit go.mod from tools or scripts
			* �༭go.mod�ļ�
        graph       
			* �������
        init        
			* �ڵ�ǰĿ¼��ʼ��һ��mod
        tidy        
			* ��������Ϣ�����һ�ɾ��ûʹ�õ�������
        vendor      
			* ����������ϵ�Ŀ���
        verify     
			* ��֤������ϵ������
        why         
			* ����Ϊʲô��Ҫgo mod
	
---------------------------
go.mod
---------------------------
module go-project

go 1.15

replace (
	golang.org/x/crypto v0.0.0-20180820150726-614d502a4dac => github.com/golang/crypto v0.0.0-20180820150726-614d502a4dac
	golang.org/x/net v0.0.0-20180821023952-922f4815f713 => github.com/golang/net v0.0.0-20180826012351-8a410e7b638d
	golang.org/x/text v0.3.0 => github.com/golang/text v0.3.0
)

require (
    github.com/gin-gonic/gin v1.6.3 // indirect
)


replace
	* �����滻�����İ����൱����д
	* ��һЩû�����ص�����£������滻�ɿ��Է��ʵ�������
		golang.org/x/text v0.3.0 => github.com/golang/text v0.3.0

require
	* �����Ͱ汾����
	* ����� indirect ��ʾ�������
