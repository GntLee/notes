#!/bin/bash
# ============================================= 2019��1��22�� 13:56:43

function factorial()
	if [ ${1} -eq 1 ] ; then
		echo 1
	else
		local temp=$[${1} - 1]
		local result=`factorial ${temp}`
		echo $[${result} *  ${1}]
	fi

read -p "����һ��ֵ:" value
result=`factorial ${value}`
echo "${value}�Ľ׳˵���:${result}"