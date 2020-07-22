#!/bin/bash
# ============================================= 2019��1��22�� 13:56:43
IFS_OLD=$IFS

IFS=$'\n'	# ���зָ�

for entry in `cat /etc/passwd`; do
	echo "values in :${entry}"
	IFS=:	# ð��ָ�
	for value in $entry ; do
		echo "${value}	"
	done
done

# ��ԭԭʼ�ķָ��
IFS=$IFS_OLD


--------------------------------
ʹ���ض���
--------------------------------
file=/root/app.log
while read line; do
	echo "${line}"
done < ${file}



--------------------------------
ʹ��cat�ܵ�
--------------------------------
file=/root/springboot.py
line_number=1
cat ${file} | while read line; do
	echo "$((line_number++)) ${line}"
done