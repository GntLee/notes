----------------
logstash		|
----------------
	# ����
		https://www.elastic.co/cn/downloads/logstash
	



----------------
�����������	|
----------------
	# ���ز�������
		https://grouplens.org/datasets/movielens/
		http://files.grouplens.org/datasets/movielens/ml-latest.zip
	
	# ��ѹ��������, Ȼ���ڽ�ѹĿ¼��������ļ�: logstash.conf
		input {
		  file {
			path => "D:\elasticsearch\ml-latest\ml-latest\movies.csv"
			start_position => "beginning"
			sincedb_path => "/dev/null"
		  }
		}

		* ͨ�� path, ָ�� movies.csv �����ļ�
	
	# ����logstash
		logstash -f logstash.conf

		* ͨ�� -f ָ�� logstash.conf �����ļ���Ŀ¼
	


		