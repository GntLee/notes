------------------------------
����						  |
------------------------------
	# ��֧�ֶ�ά����,û����������Ĵ�С,�±��0��ʼ,���ҿ������ַ���������ֵ
	# Խ�粻�ᱨ��,���ؿ�
	# ʹ�����ű�ʾ����,ÿ��Ԫ��ʹ�ÿո�ֿ�
		ARR=(1 2 3 4)
	
	# Ҳ����ʹ�ûس�������
		ARR=(
			Hello



			World
		)
		echo ${ARR[*]}	# -> Hello World
		echo ${ARR[1]}	# -> world
		echo ${ARR[2]}	# -> ��

		* �հ׵�Ԫ�ػᱻ����,�����Ԫ�ػ����Ͽ�£
	
	# �����ȶ���,�ٳ�ʼ��
		ARR=()
		ARR[0]=0
		ARR[1]="Hello"
		ARR[3]="World"
		echo ${ARR[*]}	# -> 0 Hello World
		echo ${ARR[2]}	# ��
		echo ${ARR[3]}	# World

		* ���Բ�����˳��������,���������Ĳ���Ϊ��
	
	# ����ͨ���Ǳ�����ȡ������ֵ
		ARR=(1 2 3 4)
		ARR[0]=1
	
	# ����ͨ�� @/* ��������ȡ�����е�ֵ
		ARR=(1 2 3)
		echo ${ARR} # -> 1
		echo ${ARR[@]} # -> 123
		echo ${ARR[*]} # -> 123

		* ���Ԫ��Ϊ��,ȫ����ȡʱ�ᱻ����
	
	# ��ȡ����ĳ���
		ARR=()
		ARR[0]=0
		ARR[15]="1"
		SIZE=${#ARR[*]}
		echo ${SIZE}	# 2

		* ���ַ�ʽ������������п�Ԫ�صĸ���
	
	# ���������ʱ�򣬿���ʹ��ͨ���
		files=(.*)				# ��ǰĿ¼�µ������ļ�
		echo "${files[*]}"
	
	# ��ȡ����������
		* ${!array[@]}��${!array[*]}�����Է�������ĳ�Ա��ţ�����Щλ������ֵ�ġ�

		arr=([5]=a [9]=b [23]=c)
		echo ${!arr[@]} # 5 9 23
		echo ${!arr[*]}	# 5 9 23

		* ��������﷨��Ҳ����ͨ��forѭ���������顣
			for i in ${!arr[@]};do
				echo ${arr[i]}
			done
	

	# ��ȡ�����Ա
		* ${array[@]:position:length}���﷨������ȡ�����Ա��
			${food[@]:1:1}	# ���ش�����1��λ�ÿ�ʼ��1����Ա
			${food[@]:1:3}	# ���ش�1��λ�ÿ�ʼ��3����Ա��
		
		* ���ʡ�Գ��Ȳ���length���򷵻ش�ָ��λ�ÿ�ʼ�����г�Ա
	
	# ׷�������Ա
		* ����ĩβ׷�ӳ�Ա������ʹ��+=��ֵ�����
			foo=(a b c)
			echo ${foo[@]} # a b c

			foo+=(d e f)
			echo ${foo[@]} # a b c d e f
	
	# ɾ������
		foo=(a b c d e f)
		echo ${foo[@]} # a b c d e f

		unset foo[2]
		echo ${foo[@]} # a b d e f
	
		
		* ��ĳ����Ա��Ϊ��ֵ�����Դӷ���ֵ�С����ء������Ա��
			foo=(a b c d e f)
			foo[1]=''		# ���Ǳ�1��ֵ, ����Ϊ''����
			echo ${foo[@]} # a c d e f
		
		* �����ǡ����ء���������ɾ������Ϊ�����Ա��Ȼ���ڣ�ֻ��ֵ����˿�ֵ��
	
		* unset ArrayName���������������

------------------------------
��������
------------------------------
	# �����ڶ���
	# declare -A���������������顣
		declare -A colors
		colors["red"]="#ff0000"
		colors["green"]="#00ff00"
		colors["blue"]="#0000ff"
	
	# ���ʹ��������Ա�ķ�ʽ����������������������ͬ
		echo ${colors["blue"]}
	
	# ����
		declare -A langs
		langs["java"]="System.out.println(\"Hello\")"
		langs["Python"]="print('Hello')"
		langs["Javascript"]="console.log('Hello')"

		*  ����key
			for i in ${!langs[*]}; do
				echo "${i}"
			done
	
		* ����val
			for i in ${langs[*]}; do
				echo "${i}"
			done
	
		* ����Key��Val
			for i in ${!langs[*]}; do
				key="${i}"
				val="${langs[${i}]}"
				echo "key=${key}, val=${val}"
			done

------------------------------
����		����				  
------------------------------
	# �±����
		arr=(9 8 7 6 5 4 3 2 1 0)
		len=${#arr[*]}
		for (( i = 0; i < ${len}; i++ )); do
			echo "[${i}] = ${arr[i]}"
		done

		[0] = 9
		[1] = 8
		[2] = 7
		[3] = 6
		[4] = 5
		[5] = 4
		[6] = 3
		[7] = 2
		[8] = 1
		[9] = 0
	
	# for in ����
		arr=(9 8 7 6 5 4 3 2 1 0)
		for item in ${arr[*]}; do
			echo "${item}"
		done

		9
		8
		7
		6
		5
		4
		3
		2
		1
		0