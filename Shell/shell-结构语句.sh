---------------------
if then				 |
---------------------
	# �﷨
		if cmd 
		then
			cmds
		fi


		* cmd ��ʵ������һ�� bool���ʽ
		* �ϸ���˵,����һ��shell����
		* �������Ľ���״̬�������0,�ͻ�ִ�� cmds 
	
	# �������ŵ��﷨
		if cmd; then
			coms
		fi

		* ��cmd�����һ���ֺ�,�Ϳ��԰�thenд�ں�����
			if pwd > /dev/null;then
				echo "success"
			fi
	
	# if ������Ը��������(ʹ�÷ֺŸ���), ����ֻҪ���һ�������0���ͻ�ִ��then�Ĳ���
		if false; true; then echo 'hello world'; fi
		hello world # �ᱻִ��
	
---------------------
if then else		 |
---------------------
	# ����else�﷨
		if cmd; then
			coms
		else
			coms
		fi
	
	# ���elseif�﷨
		if cmd; then
			coms
		elif cmd; then
			coms
		else
			coms
		fi
	
	

	# С��ϰ
		testuser="kevinblandy"
		if grep ${testuser} /etc/passwd; then
			echo "�û� :${testuser} ��ϵͳ�д���"
		elif ls -d /home/${testuser}; then
			echo "�û� :${testuser} ��ϵͳ�в�����"
			echo "���Ǵ������ļ�Ŀ¼"
		else
			echo "�û������ڣ�Ҳû�����ļ�Ŀ¼"
		fi
	

---------------------
test				 |
---------------------
	# if then �����,����shell����ص�״̬����ȷ���Ƿ�Ҫִ�д����
	# ����if then�����ж�����״̬��֮�������,��ʱ����ʹ�� test ָ��
		if test condition;then
			cmds
		fi
		
		* condition ��һϵ�еĲ�����ֵ
		* ��ʵcondition�ǰѼ�������trueת��Ϊ��״̬��0,��falseת��Ϊ�˷�0
	
	# ������� condition,��ô���ͻ��Է�0״̬�˳�,ִ��else��
		if test; then
			echo "success"
		else
			echo "error"	# ִ��
		fi
	
	# ���Ա����Ƿ�������
		var=" "
		test var	# false

		* ȫ�ǿհ��ַ�����ֵtest������Ϊfalse
	
	
	# �������ŵ�д��,ʹ��[]������ test ָ��
		if [ condition ]; then
			echo "success"
		else
			echo "error"
		fi

		* ע����,��һ��������֮��,�͵ڶ���������֮ǰ�������һ���ո�
	
	# test ��������ж���������
		* ��ֵ�ıȽ�
		* �ַ����ıȽ�
		* �ļ��ıȽ�

	
	# ������test������ʹ�ø�����

	# ����д��
		test expression
		[ expression ]
		[[ expression ]]
		
		* ����д���ǵȼ۵�, ���ǵ�����֧������, �����Ĳ�֧��
		* ע��[] �ͱ��ʽ֮���пո�
	
	# test�����ڲ���>��<���������������������������÷�б��ת�壩���������ǻᱻ shell ����Ϊ�ض����������

	# ʹ�÷񶨲�����!ʱ�������Բ����ȷ��ת��ķ�Χ
		if [ ! \( $INT -ge $MIN_VAL -a $INT -le $MAX_VAL \) ]; then
			echo "$INT is outside $MIN_VAL to $MAX_VAL."
		else
			echo "$INT is in range."
		fi

		* test�����ڲ�ʹ�õ�Բ���ţ�����ʹ�����Ż���ת�壬����ᱻ Bash ����
	


----------------------------
if-then �ĸ߼�����			|
----------------------------
	# ������ѧ���ʽ��˫����
		* ���ʽ
			(( expression ))
		
		* ����test�������ʹ�õ��������Ϊ,��(())���滹����ʹ�õ������
			val++				����
			varl--				���	
			++val				����
			--val				�ȼ�
			!					��
			~					λ��
			**					������
			<<					��λ��
			>>					��λ��
			&					λ����
			|					λ�ƻ�	
			&&					�߼���
			||					�߼���
	
		*  ˫�����е�><���㲻��Ҫת��
			val=10
			if (( $val ** 2 > 90 )); then
				(( val1=$val ** 2 ))	# ˫���Ÿ�ֵ
				echo "the square of ${val} is ${val1}"
			fi
		
		* ����������if�������˫��������,Ҳ�����ڽű�����ͨ������ʹ��˫��������ֵ
			val=10
			(( val1=$val ** 2 ))
			echo ${val1}
		
		* �������Ծ���,��������������ʹ�ø��ֱ��ʽ,�������е�shell��֧������˫����Ҫע��

	# ���ڸ߼��ַ��������ܵ�˫������
		* ���ʽ
			[[ expression ]]
		
		* expression ʹ�� test�����в��õı�׼�ַ����Ƚ�
		* ���ṩ����һ������:ģʽƥ��
			reg="r*"	# ������ʽ
			if [[ ${USER} == ${reg} ]] ; then
				echo "Hello ${user}"
			fi
		
		
		* �������Ծ��ǿ�����[[]]����ʹ��������ʽ
		* �������е�shell��֧��˫������

	
	# ����
		[[ string1 =~ regex ]]

		INT=-5
		if [[ "$INT" =~ ^-?[0-9]+$ ]]; then
		  echo "INT is an integer."
		  exit 0
		else
		  echo "INT is not an integer." >&2
		  exit 1
		fi

----------------------------
case						|
----------------------------
	# �﷨(��ѧ���﷨)
		case variable in 
		pattern | pattern) 
			command;;
		pattern) 
			command;;
		*) 
			default command;;
		esac

		variable:��ʾ����
		pattern:��ʾһ��ֻ,�����ж��,ʹ��|�ָ�
		command:���ֵƥ�����˾�ִ�е�����
		default command:��ûƥ����ʱִ�е����
	
		
		* �������,���һ�д���ĩβ��Ҫ��������ֺ�
		* demo
			val="c"
			case ${val} in
			"a") 
				echo "ƥ�䵽��"
				echo "a";;
			"b" | "c") 
				echo "ƥ�䵽��"
				echo "b or c";;
			*) 
				echo "������";;
			esac 

	# case��ƥ��ģʽ����ʹ�ø���ͨ�����������һЩ���ӡ�
			a)��			ƥ��a��
			a|b)��			ƥ��a��b��
			[[:alpha:]])��	ƥ�䵥����ĸ��
			???)��			ƥ��3���ַ��ĵ��ʡ�
			*.txt)��		ƥ��.txt��β��
			*)��			ƥ���������룬ͨ����Ϊcase�ṹ�����һ��ģʽ��
		
	# Bash 4.0֮ǰ��case�ṹֻ��ƥ��һ��������Ȼ��ͻ��˳�case�ṹ��
		* Bash 4.0֮������ƥ������������ʱ������ ;;& ��ֹÿ��������

			read -n 1 -p "Type a character > "
			echo
			case $REPLY in
			  [[:upper:]])    echo "'$REPLY' is upper case." ;;&
			  [[:lower:]])    echo "'$REPLY' is lower case." ;;&
			  [[:alpha:]])    echo "'$REPLY' is alphabetic." ;;&
			  [[:digit:]])    echo "'$REPLY' is a digit." ;;&
			  [[:graph:]])    echo "'$REPLY' is a visible character." ;;&
			  [[:punct:]])    echo "'$REPLY' is a punctuation symbol." ;;&
			  [[:space:]])    echo "'$REPLY' is a whitespace character." ;;&
			  [[:xdigit:]])   echo "'$REPLY' is a hexadecimal digit." ;;&
			esac

		* ��������β�����;;&�Ժ���ƥ��һ������֮�󣬲�û���˳�case�ṹ�����Ǽ����ж���һ��������


----------------------------
for							|
----------------------------
	# �﷨
		for var in list
		do
			commands
		done

		var :��ʱ�ĵ�������
			* ����һֱ����(ֵ�����һ�ε���ֵ)ֱ���ű���ֹ(jsһ��)
			* ��Ȼ,�����������޸����ֵ

		list :�������������
			* Ĭ�������ʹ�������ַ���Ϊ�ָ���:�ո�,�Ʊ��,����

		commands: ÿ�ε���ִ�еĴ���
	
	# Ҳ���԰� do �ؼ��ַ��ڵ�һ��
		for var in list; do
			commands
		done
		
		��������������������������������������������������������������������
		for var in 1 2 3 "Hello"; do
			echo "value=${var}"
		done

		value=1
		value=2
		value=3
		value=Hello
		��������������������������������������������������������������������
	
	# list�����������ŵ�����
		for var in I don't know if this'll work; do
			echo "word:${var}"
		done

		word:I
		word:dont know if thisll	# ����������֮�������,����һ�����������
		word:work

		* shell���԰��б��еĵ��������ݿ�����һ�ݵ���������

		* ���Ը����������ת���ַ�
			for var in I don\'t know if this\'ll work; do
				echo "word:${var}"
			done
		
		* Ҳ����ʹ��˫�����������õ������ŵ�ֵ
			for var in I "don't" know if "this'll" work; do
				echo "word:${var}"
			done

	# list�������пո������
		for var in Hello World; do
			echo "word:${var}"
		done
		word:Hello
		word:World	# ��Ϊ�Կո�ָ�,����,�ᱻ���Ϊ2���ʶ�

		* ����ʹ��˫���ŰѺ��пո������������
			for var in "Hello World"; do
				echo "word:${var}"
			done
	
	# ��һ��������,���߶���"��ʱ,shell�������"����ֵ��һ����(����������)
		* ���Ҫ���",��ʹ��ת��
			for var in \"Hello\" \"World\"; do
				echo "word:${var}"
			done
	

	# �ӱ�����ȡ�б�
		list="KevinBlandy is a pythonista"
		list="${list} !"
		for var in $list; do
			echo "word:${var}"
		done

	# �������ȡֵ
		file="demo.sh"
		for var in `cat ${file}`; do
			echo "word:${var}"
		done
	
	# �����ֶεķָ���
		* Ĭ��ʹ�õ���:����,�ո�,�Ʊ��
		* �ڽű�����Ӵ���,ָ��Ψһ��Ĭ�Ϸָ���
			IFS=$'\n'	# ָ���ָ���Ϊ����,��ô�ո���Ʊ������������

		* ����������ܴ�,��Ҫ�ڵ�һ���ط��޸�IFSֵ,Ȼ���������޸�
		* �ýű��������ط�����ʹ��IFS��Ĭ��ֵ,����ʹ��һ���Ƚϰ�ȫ�򵥵Ĳ���
			
			IFS_OLD=$IFS	# �ȼ�¼ԭʼֵ
			IFS=$'\n'		# �޸ķָ���
			IFS=$IFS_OLD	# ����ɵ���������,��ԭΪԭʼֵ
		
		
		* ʹ��ð��(:)�ָ��ֵ(����:/etc/passwd �ļ�)
			IFS=:
			value="H:e:l:l:o"
			for var in ${value}; do
				echo "word:${var}"
			done
		
		* ʹ�ö��IFS�ַ�
			IFS=$'\n':;" #ʹ�û���,ð��,�ֺ�,˫���� ��Ϊ�ָ���
		
	# ��ͨ�����ȡĿ¼
		for file in /usr/local/*; do
			if [ -d ${file} ]; then
				echo "Ŀ¼��${file}"
			else
				echo "�ļ���${file}"
			fi
		done

		* �������ĩβ��� *,��ô·���ͻᱻ����һ���ַ���
		* Ҳ������Ӷ��·��,�ϲ�Ϊһ��list���е���
			for file in /usr/local/* /usr/*; do
				if [ -d ${file} ]; then
					echo "Ŀ¼��${file}"
				else
					echo "�ļ���${file}"
				fi
			done
		
		* ������Ŀ¼/�ļ�������,forҲ�᳢��ȥ�����б��е�����
		* ������ִ�в���֮ǰ,�������ж�һ��Ŀ¼/�ļ��Ƿ����

----------------------------
C ����for					|
----------------------------
	# �����﷨
		for ((  var  ; condition ; process ))
		do
			cmd
		
		done

		* ��ֱ����ģ��C�����(���ò���C��shell�������±�)
		* �����ĸ�ֵ�����пո�
		* �����еı�������$��ͷ
		* �������̵���ʽû���� expr �������	

		for ((i = 0;i < 10 ; i++)); do	# ����Ȼ,��Ҳ����ʹ�����ŵ�д��
			echo "${i}"
		done
	
	# Ҳ����ʹ�ö������
		for ((i = 0,x = 10 ;i < 10 ; i++, x--)); do
			echo "i=${i},x=${x}"
		done
	
	# ��������
		arr=(9 8 7 6 5 4 3 2 1 0)
		len=${#arr[*]}
		for (( i = 0; i < ${len}; i++ )); do
			echo "[${i}] = ${arr[${i}]}"
		done
	

----------------------------
while						|
----------------------------
	# �﷨
		while test
		do
			common
		done

		* test �� if �� test һ��,����ʹ��shell������� test ���������������

		val=5
		while (( $val > 0 )); do # Ҳ����ʹ�����ָ������ŵ��﷨�͸߼�������
			echo "${val}"
			val=$[${val} - 1]
		done

	
	# while ������ while ����ж�������������
		* ֻ�����һ������������˳�״̬�������Ƿ�Ҫ�˳�ѭ��

		val=5
		while echo $val 
			[ $val -ge 0 ]		# �ڶ���������д����һ��,�ᱻ���� echo �Ĳ�����ӡ��
		do
			echo "${val}"
			val=$[ ${val} - 1 ]
		done
		��������������������������������������������������������
		5
		5
		4
		4
		3
		3
		2
		2
		1
		1
		0
		0
		-1
		��������������������������������������������������������

----------------------------
until ����					|
----------------------------
	# ��while�Ĺ�����ʽ�෴,ֻ������������,�Ż�ִ��,�����������˳�ѭ��
	# �﷨
		until test
		do
			command
		done
		
	# demo
		until (( $val < 0 )) ; do	# ����ʹ�ø߼��﷨,�����ŵı��ʽ
			echo "${val}"
			val=$[${val} - 1]
		done
	
	 # ��������Ҳ���Դ��ڶ������,����ֻ�����һ���������ʱ�����ѭ��
	


----------------------------
����ѭ��					|
----------------------------
	# ûɶ�ý��͵�
		break continue

	# breakĬ��������ǰѭ��,Ҳ���Ը���ָ��һ������,�����ڼ���ѭ��
		break val

		* val Ĭ����1,����������ǰ��ѭ������
		* �����޸ĸ�ֵ,�Ϳ�������n��ѭ��(Ƕ��ѭ��)
	
	# continue Ҳ��breakһ��,����ָ��һ������,������ֹ�ڼ������һ��ѭ��
		continue val

		* valĬ����1,��ʾ��ֹ��ǰ��ε���һ��ѭ��
	
----------------------------
����ѭ�������				|
----------------------------
	# ������done�����һ���ض�������ʵ��

		val=10
		for (( i = 1 ;i < ${val} ; i++)); do
			for (( y = 1; y <= ${i}; y ++ )); do
				sum=$[${i} * ${y}]
				echo -n "${y} x ${i} = ${sum}    "
			done
			echo ""
		done > 99.log

		* ѭ���ڲ��� echo �����ӡ����Ļ,�ᱻ����� �ļ�
	
	# Ҳ���԰�ѭ���Ľ��,ͨ���ܵ�����һ������
		val=10
		for (( i = 1 ;i < ${val} ; i++)); do
			for (( y = 1; y <= ${i}; y ++ )); do
				sum=$[${i} * ${y}]
				echo -n "${y} x ${i} = ${sum}    "
			done
			echo ""
		done | sort


----------------------------
select
----------------------------
	# select�ṹ��Ҫ�������ɼ򵥵Ĳ˵��������﷨��for...inѭ������һ�¡�
		select name [in list]; do
		  commands
		done
	
	# Bash ���select���ν�������Ĵ���
		1, select����һ���˵����������б�list��ÿһ�����ÿһ��ǰ�滹��һ�����ֱ�š�
		2, Bash ��ʾ�û�ѡ��һ��������ı�š�
		3, �û������Ժ�Bash �Ὣ��������ݴ��ڱ���name������ı�Ŵ��뻷������REPLY������û�û�����룬�Ͱ��س�����Bash ����������˵������û�ѡ��
		4, ִ��������commands��
		5, ִ�н����󣬻ص���һ�����ظ�������̡�




















