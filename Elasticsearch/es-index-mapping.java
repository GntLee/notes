------------------------
mapping					|
------------------------
	# �ĵ�
		https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping.html
	
	# ����ϵ�����ݿ��DDLһ��, ����洢����������, ����, ʹ�õķִ����ȵ�
		* ��ͬ����������, �ڽ�������, �ִʵ�ʱ�����������ͬ
		* ��Ӱ�쵽�����Ľ�����������Ϊ

		* mapping �����ڴ��� index ֮ǰ����
		* mapping �����ڴ��� index ֮��༭, �༭ֻ��������, �������޸�


	# �鿴ָ��������mapping
		GET /<index>/_mapping

	# mapping�Ľṹ
		{
			"<index>":{
				"mappings":{
					"properties":{
						"<field>":{

						}
					}
				}
			}
		}

		* properties �¾���ÿ���ֶζ�Ӧ��'����'
			- ��������
			- ��ηִ�
			- ... ...
		* properties �����¿���Ƕ�� properties
	


	# ����������ʱ��, ���� mapping
		PUT /<index>
		{
			"mappings":{
				"properties":{
					"<field>":{
						...
					}
				}
			}
		}
	
	# ����������, �޸� mapping
		PUT /<index>/_mapping
		{
			"properties":{
				"<field>":{
					...
				}
			}
		}
	
------------------------
type ��������			|
------------------------
	# ָ���ֶε���������, ������ֶ�����, ���Զ��Ʋ�
		
	# long
	# boolean
	# double
	# string
	# byte
	# short
	# integer
	# float
	# text
	# date
	# keyword

------------------------
index �Ƿ���Ա�����	|
------------------------
	# ָ���ֶ��Ƿ���Ա�����
		* Ĭ�ϼ���������, ҲҪ���зִ�

	# not_analyzed
		* ���Ա�����, ���ǲ��ִ�

	# no
		* �����Ա�����, Ҳ�����зִ�

------------------------
analyzer ָ���ִ���		|
------------------------
	# Ĭ��: standard

------------------------
settings				|
------------------------

------------------------
_source					|
------------------------

------------------------
_all					|
------------------------

------------------------
dynamic					|
------------------------

------------------------
mapping	�ֶ�����		|
------------------------
	{
	  "<index>" : {
		"mappings" : {
		  "properties" : {
			"<field>" : {
			  "properties" : {
				"<field>" : {
				  "type" : "text",
				  "fields" : {
					"keyword" : {
					  "type" : "keyword",
					  "ignore_above" : 256
					}
				  }
				}
			  }
			},
			"<field>" : {
			  "type" : "text",
			  "fields" : {
				"keyword" : {
				  "type" : "keyword",
				  "ignore_above" : 256
				}
			  }
			},
			"<field>" : {
			  "type" : "date"
			},
			"<field>" : {
			  "type" : "long"
			}
		  }
		}
	  }
	}
