----------------------------
_source Ԫ����				|
----------------------------
	# �������ݵ�ʱ��,���ص�����ǰ����� _ ,�ľ���Ԫ����
		{
		  "_index": "user",
		  "_type": "coder",
		  "_id": "3",
		  "_version": 2,
		  "found": true,
		  "_source": {
			"id": 3,
			"name": "Rocco",
			"age": 21,
			"gender": "girl",
			"hobby": [
			  "basketball",
			  "Sing",
			  "Write the code"
			],
			"skill": {
			  "java": {
				"level": "9"
			  },
			  "python": {
				"level": "9"
			  },
			  "javascript": {
				"level": "6"
			  }
			}
		  }
		}
		
		_source
			* document����

----------------------------
_source ���Ʒ��ؽ��		|
----------------------------
	# ָ�� _source �з����ĸ�field

	# ������ѯname����
		GET /user/coder/3?_source=name
		{
		  ...
		  "_source": {
			"name": "Rocco"
		  }
		}
	
	# ������ѯname���Ժ� skill �����е� java����
		GET /user/coder/3?_source=name,skill.java
		{
		  ...
		  "_source": {
			"skill": {
			  "java": {
				"level": "9"
			  }
			},
			"name": "Rocco"
		  }
		}

		* ʹ�ö��ŷָ����field,֧�����Ե���

