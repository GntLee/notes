---------------------
�ִ���				 |
---------------------
	# �ִ���

	# �зִ��� & normalization(����recall�ٻ���)
		* �����ݽ��зִ�, ���Ҷ�ÿ�����ʽ���normalization
		* �ٻ���:������ʱ��, �����ܹ��������Ľ������
	

	# �ִ��������˼�������
		character filter
			* �ִ�֮��, ����Ԥ����, ����:����html��ǩ

		tokenizer
			* �ִ�

		token filter
			* ��ִ��normalization��һЩ����, ����:ͬ���ת��, ��Сдת��
	

		
	
---------------------
���õķִ���		 |
---------------------
	Standard analyzer(Ĭ��)
	Simple analyzer
	Witespace analyzer
	Language analyzer

---------------------
ִ�зִ�ָ��		 |
---------------------
	# ����
		GET /<index>/_analyze

		{
		  "analyzer": "<analyzer>",
		  "text":"<text>"
		}

		* index �Ǳ���, ���ָ���Ļ�, ����ʹ��ָ��index��mapping������
		* analyzer ָ��Ҫʹ�õķִ���, �Լ�Ҫ���зִʵ��ı�(text)

		{
		  "tokens" : [
			{
			  "token" : "hello", //��ֵ�һ���ʶ�
			  "start_offset" : 0, // �ô����ı��е�λ��(���Ķ��ַ�����ʼ���ĸ��ַ���)
			  "end_offset" : 5,
			  "type" : "<ALPHANUM>",
			  "position" : 0  // �ô������ı��е�λ��(������ĵڼ����ʶ�)
			},
			{
			  "token" : "world",
			  "start_offset" : 6,
			  "end_offset" : 11,
			  "type" : "<ALPHANUM>",
			  "position" : 1
			}
		  ]
		}

---------------------
���Ʒִ���			 |
---------------------
	
	