----------------------------
Ԥ�����					|
----------------------------
	# ANSI C ����������,�ڱ���п���ʹ����Щ��,���ǲ���ֱ���޸���ЩԤ����ĺ�

	__DATE__	��ǰ����,һ���� "MMM DD YYYY" ��ʽ��ʾ���ַ�����
	__TIME__	��ǰʱ��,һ���� "HH:MM:SS" ��ʽ��ʾ���ַ�����
	__FILE__	��������ǰ�ļ���,һ���ַ�������
	__LINE__	��������ǰ�к�,һ��ʮ���Ƴ���
	__STDC__	���������� ANSI ��׼����ʱ,����Ϊ 1


	#include <stdio.h>

	main()
	{
		printf("File :%s\n", __FILE__ );	File :test.c
		printf("Date :%s\n", __DATE__ );	Date :Jun 2 2012
		printf("Time :%s\n", __TIME__ );	Time :03:36:24
		printf("Line :%d\n", __LINE__ );	Line :8
		printf("ANSI :%d\n", __STDC__ ); ANSI :1
	}






