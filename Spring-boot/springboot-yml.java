----------------------------
yml	�����ļ�				|
----------------------------
	# springbootĬ��ʹ�� properties��yml���͵������ļ�
		application.yml
		application.properties
	
	# yml(YAML Ain t,Markup Language)
		* �������,��JSON,xml,properties ���ʺ��������ļ�
	

----------------------------
yml	�﷨���				|
----------------------------
	# ʹ��������ʾ�㼶��ϵ
	# ����������ʹ��Tab,ֻ����ո�
	# �����ո���Ŀ����Ҫ,������ͬ�㼶��Ԫ�ر���������(pythonһ��)
	# ��Сд����

	# yml֧���������ݽṹ
		1,����
			* ��ֵ�Եļ���
				frends:
				  name: Litch
				  age: 23

			* ��ҵд��Ҳ�������
				frends: {name:Kevin,age23}

		2,����
			* һ�鰴���������ֵ,ʹ��:�̺��� �ո� ֵ
				skill:
				 - Java
				 - Python
				 - Javascript
			
			* ֧������д��
				skill: [Java,Python,Javascript]

		3,������
			* ������,�����ٷֵ�ֵ
				Kevin��
					name: Kevin
					age: 23
			
			* ˫���Ų���ת���ַ�������������ַ�
				name: "Kevin\n"		//\n�ỻ��

			* ������,��ת�������ַ�
				name: 'Kevin\n'		//\n �ᱻת�����
			
	# ���ݽṹ�����໥�Ľ���
		class Dog{
			String name;
			Integer age;
		}

		@ConfigurationProperties(prefix = "Person")
		class Person{
			String lastName;
			Integer age;
			Boolean boss;
			Date birth;
			Map<String,Object> maps;
			List<Object> lists;
			Dog dog;
		}

		
		Person:
		  lastName: Kevin
		  last-name: Kevin

		  age: 23
		  boss: false
		  birth: 2017/12/12

		  maps:
			key1: value1
			key2: value2
		  maps: {key1: value1,key2: values}

		  lists:
			- item1
			- item2
		  lists: [item1,item2]

		  dog:
			name: Litch
			age: 23

	# ͬ������ʹ�� @Value ע��
		@Value("${Person.lastName}")

