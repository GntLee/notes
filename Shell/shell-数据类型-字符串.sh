-----------------
���������ַ���	 |
-----------------
	# Bash ֻ��һ���������ͣ������ַ����������û�����ʲô���ݣ�Bash ����Ϊ�ַ�������ˣ��ַ�����ص����ź�ת�壬�� Bash ��˵�ͷǳ���Ҫ��
	# �ַ�����shell�������������õ���������(�������ֺ��ַ���,Ҳûɶ�������ͺ�����)
	# �ַ��������õ�����,Ҳ������˫����,Ҳ���Բ�������,��˫���ŵ������PHP����

	# ������
		str='this is a string'

		* ����������κ��ַ�����ԭ�����,�������ַ����еı�������Ч��
			name="KevinBlandy"
			echo 'Hello ${name}'	-> Hello ${name}
			
		* �������ִ��в��ܳ��ֵ���һ���ĵ�����(�Ե�����ʹ��ת�����Ҳ����)
				str='hello, \'' -> unexpected EOF while looking for matching `''

		* �����ſɳɶԳ���,��Ϊ�ַ���ƴ��ʹ��
			name='v'
			str='hello, '$name' !'	-> hello, v !
		
		* ���Ҫ�ڵ���������������ţ�����ʹ��ת�壬��Ҫ��������ߵĵ�����ǰ�����һ����Ԫ���ţ�$����Ȼ���ٶ����ĵ�����ת�塣
			echo $'\'Hello\''
			'Hello'
			* ������ķ����Ǹ���˫����֮��ʹ�õ����š�
		
	
	# ˫����
		your_name='vin'
		str="Hello, I know you are \"$your_name\"! \n"
		echo -e $str

		* ˫���ű���ԭʼ����������ʽ
			echo `free -m`		# ��ʽ�����ң�һ��
			echo "`free -m`"	# ԭ֭ԭζ
	
	# �ַ�����ƴ��
		name="vin"

		greeting="hello, "$name" !"		-> hello, vin !
		greeting_1="hello, ${name} !"	-> hello, vin !

		greeting_2='hello, '$name' !'	-> hello, vin !
		greeting_3='hello, ${name} !'	-> hello, ${name} !
	
	#  ��ȡ�ַ����ĳ���
		#!/bin/bash
		name="KevinBlandy"
		echo ${#name}			-> 11

		S="KevinBlandy"
		echo "${S} size = ${#S}"	-> 11
	
	# ��ȡ���ַ���
		string="KevinBlandy"
		echo ${string:1:4} -> evin

		* ${:x:y} ,x��ʾ��ʼ�ĽǱ�,y��ʾȡ���ٸ��ַ��������дy����ʾ��ȡ��ĩβ

		* ���xΪ��ֵ�����ʾ�Ӻ��濪ʼ��ȡ��ע�⣬����ǰ�������һ���ո� �Է�ֹ��${variable:-word}�ı���������Ĭ��ֵ�﷨����
			echo ${foo: -5}
		* ��ʱ�������ָ��y����y����С���㡣

		* �����﷨����ֱ�Ӳ����ַ�����ֻ��ͨ����������ȡ�ַ���
	
	# �������ַ����Ǳ�
		#!/bin/bash
		NAME="KevinBlandy"
		S="B"
		index=`expr index ${NAME} ${S}`
		echo "${index}"	# -> 6

		* ���صĿ�ʼλ���Ǵ�1��ʼ�����
		* ����Ҳ�������0
		* ʵ����ʹ�õ���shell����
			expr index "KevinBlandy" "B" 

	

	# Here �ĵ� 
		* ��һ����������ַ����ķ�������ʽ����
			<< token
			�Ҿ��Ƕ���
				�ַ���
			token

			[root@KevinBlandy ~]# <<token
			> �Ҿ���
			>         �����ַ���
			> token
			[root@KevinBlandy ~]# 

		* token�Զ���
		* Here �ĵ�Ҳ������Ϊ������ֵ��ֻ����������Ĳ�����

		* Here �ĵ�����һ�����壬���� Here �ַ�����Here string����ʹ������С�ںţ�<<<����ʾ��
		* ���������ǽ��ַ���ͨ����׼���룬���ݸ����
			$ cat <<< 'hi there'	->	$ echo 'hi there' | cat
			$ md5sum <<< 'ddd'	->	$ echo 'ddd' | md5sum
	
	# ��Сдת��
		* ת��Ϊ��д ${varname^^}
		* ת��ΪСд ${varname,,}

	# �ַ���ͷ����ģʽƥ��
		* �����﷨���Լ���ַ�����ͷ���Ƿ�ƥ�������ģʽ��
		* ���ƥ��ɹ�����ɾ��ƥ��Ĳ��֣�����ʣ�µĲ��֡�ԭʼ�������ᷢ���仯

			${variable#pattern}
				* ��� pattern ƥ����� variable �Ŀ�ͷ��
				* ɾ�����ƥ�䣨��̰��ƥ�䣩�Ĳ��֣�����ʣ�ಿ��
				
			${variable##pattern}
				* ��� pattern ƥ����� variable �Ŀ�ͷ��
				* ɾ���ƥ�䣨̰��ƥ�䣩�Ĳ��֣�����ʣ�ಿ��

		* ƥ��ģʽpattern����ʹ��*��?��[]��ͨ���
		* ɾ���ļ�·����Ŀ¼���֣�ֻ�����ļ�����
			path=/home/cam/book/long.file.name
			echo ${path##*/}	# long.file.name

		* ƥ�䲻�ɹ�������ԭʼ�ַ���
		* ��ͷ��ƥ��Ĳ��֣��滻����������
			${variable/#pattern/string} # ģʽ����������ַ����Ŀ�ͷ

			foo=JPG.JPG
			echo ${foo/#JPG/jpg} # jpg.JPG


	# �ַ���β����ģʽƥ��
		* �����﷨���Լ���ַ�����β���Ƿ�ƥ�������ģʽ��
		* ���ƥ��ɹ�����ɾ��ƥ��Ĳ��֣�����ʣ�µĲ��֡�ԭʼ�������ᷢ���仯��
			${variable%pattern}
				* ��� pattern ƥ����� variable �Ľ�β��
				* ɾ�����ƥ�䣨��̰��ƥ�䣩�Ĳ��֣�����ʣ�ಿ��

			${variable%%pattern}
				* ��� pattern ƥ����� variable �Ľ�β
				* ɾ���ƥ�䣨̰��ƥ�䣩�Ĳ��֣�����ʣ�ಿ��
		
		* ������ͬ��
	
	# ����λ�õ�ģʽƥ��
		* �����﷨���Լ���ַ����ڲ����Ƿ�ƥ�������ģʽ��
		* ���ƥ��ɹ�����ɾ��ƥ��Ĳ��֣������������ַ������ء�ԭʼ�������ᷢ���仯��
			${variable/pattern/string}
				* ��� pattern ƥ����� variable ��һ���֣�
				* �ƥ�䣨̰��ƥ�䣩���ǲ��ֱ� string �滻�������滻��һ��ƥ��

			${variable//pattern/string}
				* ��� pattern ƥ����� variable ��һ���֣�
				* �ƥ�䣨̰��ƥ�䣩���ǲ��ֱ� string �滻������ƥ�䶼�滻 
		


				