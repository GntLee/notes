------------------------
����
------------------------
	# IF ����
		{{ if [condition]}}
            <h1>true</h1>
		{{ else if [condition] }}
			 <h1>true</h1>
        {{ else }}
            <h1>false</h1>
        {{ end  }}

		* ������������ڣ��᷵��false
		* �� .condition Ϊbool���͵�ʱ����Ϊtrue��ʾִ��
		* �� .conditionΪstring���͵�ʱ����ǿձ�ʾִ��
		* �� .conditionΪ��ֵ���͵�ʱ�����0��ʾִ��
		* �� .conditionΪָ�����͵�ʱ�����nil��ʾִ��
	
	# ��ϵ
		and/or/not
			{{ if and .Admin .Enabled}}
				<h1>{{ .Name }}</h1>
			{{- else }}
				<h1>{{ "�Ƿ��û�" }}</h1>
			{{- end  }}	
		
	# �Ƚϣ�{{ function arg1 arg2 }}
		eq	arg1 == arg2
		ne	arg1 != arg2
		lt	arg1 < arg2
		le	arg1 <= arg2
		gt	arg1 > arg2
		ge	arg1 >= arg2

		{{ if eq .Name "KevinBlandy"}}
            <h1>�Ǳ���</h1>
        {{- end  }}

		* eq�����Ƚ����⣬�����ö�������͵�һ���������бȽϣ������ϵ�ǻ�
			{{ eq arg1 arg2 arg3 arg4 }}�߼���: arg1==arg2 || arg1==arg3 || arg1==arg4
