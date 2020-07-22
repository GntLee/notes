{
	"query":{

		"match_all":{
			
		},

		"match":{
			"field":"value"
		},

		"term":{
			"field":"value"		
		},
		"terms":{
			"field":["value1","value2"]	
		}
		"match_phrase":{
			"field":"value"
		}
		"multi_match":{
			"query":"value",
			"fields":["field1,field2"]
		},
		"exists":{
			"field":"field"
	    },
		"bool":{
			"must":{
				"match":{
					"key":"value"
				}
			},
			"filter":{
				"range":{
					"field":{"gt/lt/le/ge/ne":"value"}
				}
			}
		}
	},
	"sort":[
		{"key1":"desc"},
		{"key2":"asc"}
	],
	"from":1,
	"size":2,
	"_source":[],
	"highlight":{
		"fields":{
			"field":{}
		}
	},
	"aggs":{
	},
	"properties":{
	
	}
}

==============================================================================================
query
	query.match_all
		* ��������,��ֵ��һ���ն���{}

	query.match
		* ��������������
		* ��ֵ��һ������,ͨ��kv���������
		* ȫ�ļ���,value�����ж��,ʹ�ÿո����,ֻҪkey�а���������value�ؼ��ּ���������

	query.match_phrase
		* �������,��ȫ�ļ����෴,������ȫ������key=value,�ŷ�������
	
	query.multi_match
		* ����ͬһ��ֵ�Ķ���ֶ�
	query.term
		* �� match_phrase ,��ȫ��ƥ��
	query.terms
		* ��һ�����ֵ��һ���ֶ�
		* ����ƥ����ֵ,����һ��ֵ������������

	query.exists
		* ͨ��field����ָ�������Բ���Ϊnull

==============================================================================================
query.bool
	* ����һ������,������N������������
	query.bool.must
		* һ�����߶������,����ȫ������
	query.bool.should
		* һ�����߶������,����һ������
	query.bool.must_not
		* һ�����߶������,����ȫ��������
	query.bool.filter
		* һ�����߶����������

==============================================================================================

from
	* �ӵڼ������ݿ�ʼ����,limit�ĵ�һ������
size
	* ÿҳ��ʾ�ļ�¼��
_source
	* ��ֵ��һ������,ָ��Ҫ�������ֶ�,�����Ǽ�������
	* ͨ���ַ���ִ������,֧��.���Ե���

==============================================================================================
highlight.fields
	* ��������



--------------------
���				|
--------------------
match
match_all
match_phrase
multi_match
term
terms
exists

bool
must
should
must_not
filter

range
constant_score

