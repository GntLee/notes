
		 
			

------------------
��������
------------------

	protoc --proto_path=IMPORT_PATH --cpp_out=DST_DIR --java_out=DST_DIR --python_out=DST_DIR --go_out=DST_DIR --ruby_out=DST_DIR --objc_out=DST_DIR --csharp_out=DST_DIR [path/to/file.proto]

	--proto_path
		* ָ��protobuf��Ŀ¼
		* ���ʡ��, ��ʹ�õ�ǰĿ¼, ͨ����δ��ݲ��� --proto_path, ����ʵ���ڶ������Ŀ¼�а�˳�����

	--java_out
		* ���Java���Ŀ¼, �����ṩһ���������ָ��, ͬʱ����N������ԵĴ���
		* �����Ŀ¼�������Ѿ����ڵ�
		* ���Ŀ���ļ��� .zip ��β���Զ�ѹ�����
		* ���Ŀ���ļ��� .jar ��β�����Զ����ݹ淶���Ϊjar��
		* ���Ŀ���ļ��Ѿ�����, ���Զ���ɾ��

	
	path/to/file.proto
		* ָ��proto�ļ�
		* �����ṩһ������.proto�ļ���Ϊ����. ���.proto�ļ�����һ��ָ��.
	
	