-----------------------------
�ִ���						 |
-----------------------------
	# �ִ��������˼�������
		* ���ı����зִ�֮ǰ,�Ƚ���Ԥ����
			- ����html��ǩ
			- & ת��Ϊ���� and

		* �ִ�,�������Ϊ����

		* �Էִʽ���һЩͬ���,����,ʱ̬,��Сд...֮���ת��
		* ������̽���normalization(�����ܹ��������Ľ������)
	

	# ���õļ��ִַ���
		1,Standard analyzer(Ĭ��)
		2,Simple analyzer
		3,Whitespace analyzer
		4,Language analyzer

	
-----------------------------
query String�ִ���			 |
-----------------------------
	# query tring Ĭ�������,es��ʹ������Ӧ��field������������ʱʹ�õķִ���ȥ���зִ�
		* �ִ�,normalizationm,ֻ�����ֲ�����ȷ������
		* query string Ҳ�ᱻ���зִ�,�ִʲ��Ծ���document������������ʱ�ķִʲ���
	
	# ��ͬ���͵�����(exact value,full text),��ͬ�Դ�
		/_search?q=name:KevinBlandy
		/_all/_search?q=KevinBlandy

-----------------------------
�ִ����Ĳ���				 |
-----------------------------
	# ֱ�Ӳ��Էִ���
		GET /_analyze
		{
			"analyzer": "standard",			//ָ���ִ���
			"text":"Hello KevinBlandy"		//ָ���ʾ�
		}
	
	# ����ָ��index��ָ���ֶεķִ���
		GET /index/_analyze
		{
		  "field":"desc",					//ָ���ֶ�
		  "text":"hhhhh"					//���ֶ�ָ��ֵ
		}

	# ��Ӧ�ִ���Ϣ
		{
		  "tokens": [
			{
			  "token": "hello",
			  "start_offset": 0,
			  "end_offset": 5,
			  "type": "<ALPHANUM>",
			  "position": 0
			},
			{
			  "token": "kevinblandy",
			  "start_offset": 6,
			  "end_offset": 17,
			  "type": "<ALPHANUM>",
			  "position": 1
			}
		  ]
		}