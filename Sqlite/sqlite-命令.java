----------------------------
sqlite��������				|
----------------------------
	.backup [db] [file]	
		* ���� db ���ݿ�(Ĭ���� "main")�� file �ļ�

	.open [file]
		* ��ָ�����ļ�
	
	.databases
		* �г����ݿ�����Ƽ������������ļ�
	
	.dump [table]	
		* �� SQL �ı���ʽת�����ݿ�
		* ���ָ���� table ��,��ֻת��table��(table֧��ģ�����ʽ)
	
	.exit
		* �˳� SQLite ��ʾ��
	
	.header(s) ON|OFF
		* ������ر�ͷ����

	.import [file] [tables]
		* �������� file �ļ������ݵ� tables ����
	
	.show
		* �鿴sqliteĬ�ϵ�����

	.timer ON|OFF	
		* ������ر� CPU ��ʱ��
	
	.mode [mode]
		* �������ģʽ,[mode] ����������֮һ
			csv			���ŷָ���ֵ
			column		��������
			html		HTML �� <table> ����
			insert		TABLE ��� SQL ���루insert�����
			line		ÿ��һ��ֵ
			list		�� .separator �ַ����ָ���ֵ
			tabs		�� Tab �ָ���ֵ
			tcl			TCL �б�Ԫ��


