--------------------------------
shtool							|
--------------------------------
	# ��װ
		ftp://ftp.gnu.org/gnu/shtool/shtool-2.0.8.tar.gz
		tar zxvf shtool-2.0.8.tar.gz 
		cd shtool-2.0.8 
		./configure
		make
		make test
		make install
	
	# shtool ������ʹ�÷���
		shtool [options] [function [options] [args]]

	# �ṩ�ķ���
		�� ��		�� ��
		Arx			�����鵵�ļ�������һЩ��չ���ܣ�
		Echo		��ʾ�ַ��������ṩ��һЩ��չ����
		fixperm		�ı�Ŀ¼���е��ļ�Ȩ��
		install		��װ�ű����ļ�
		mdate		��ʾ�ļ���Ŀ¼���޸�ʱ��
		mkdir		����һ�������Ŀ¼
		Mkln		ʹ�����·����������
		mkshadow	����һ����Ӱ��
		move		�����滻���ܵ��ļ��ƶ�
		Path		�������·��
		platform	��ʾƽ̨��ʶ
		Prop		��ʾһ�����ж���Ч���Ľ�����
		rotate		ת����־�ļ�
		Scpp		�����CԤ������
		Slo			���ݿ����𣬷���������ѡ��
		Subst		ʹ��sed���滻����
		Table		�Ա�����ʽ��ʾ���ֶηָ��� field-separated��������
		tarball		���ļ���Ŀ¼�д���tar�ļ�
		version		�����汾��Ϣ�ļ�
		
	
	# ���Լ���shell�ű���ʹ��
		#!/bin/bash
		shtool platform # ��ӡ������ϵͳ
	
