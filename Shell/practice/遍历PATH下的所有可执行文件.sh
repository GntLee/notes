#!/bin/bash
# ============================================= 2019��1��22�� 13:56:43
IFS_OLD=$IFS
IFS=:
# ����path��������
for folder in ${PATH} ; do
	echo "${folder}"
	# ����ÿһ��Ŀ¼�µ��ļ�
	for file in ${folder}/* ; do
		# ����ǿ�ִ���ļ����ӡ
		if [ -x $file ] ; then
			echo "	$file"
		fi
	done
done
IFS=$IFS_OLD