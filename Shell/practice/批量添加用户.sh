#!/bin/bash
# ============================================= 2019��1��22�� 13:56:43
:<<EOF
users.csv
rich,Richard Blum
christine,Christine Bresnahan
barbara,Barbara Blum
tim,Timothy Bresnahan
EOF

input="users.csv"

while IFS=',' read -r userid name ; do
	echo "adding $userid"
	# -r ��ʾϵͳ�û� -c ��ʾ˵��  -m ��ʾ������Ŀ¼
	useradd -r -c "$name" -m "$userid"
done < "$input"