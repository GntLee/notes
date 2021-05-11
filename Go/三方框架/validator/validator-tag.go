--------------------------
tag
--------------------------
	# �ο��ĵ�
		https://github.com/go-playground/validator/blob/master/README.md#baked-in-validations

--------------------------
����
--------------------------

	-
		* ��������ֶΣ�����֤

	|
		* �����֤��ϵ��OR
	
	required
		* ����ѡ��
	
	omitempty
		* ����ֶ�û�����ã������У��
		* Ҳ����˵0ֵ����У��

	email
		* ����������

--------------------------
��Χ
--------------------------
	lte
		* С�ڵ��ڲ���ֵ��validate:"lte=3" (С�ڵ���3)
		* �������������ַ����ĳ���
	gte
		* ���ڵ��ڲ���ֵ��validate:"lte=120,gte=0" (���ڵ���0С�ڵ���120)
	lt
		* С�ڲ���ֵ��validate:"lt=3" (С��3)
	gt
		* ���ڲ���ֵ��validate:"lt=120,gt=0" (����0С��120)
	eq
		* ���ڲ���ֵ��validate:"eq=2"
	ne
		* �����ڣ�validate:"ne=2" (������2)
	max
		* ���ֵ��С�ڵ��ڲ���ֵ��validate:"max=20" (С�ڵ���20)
		* ���������ж��ַ�������
	min
		* ��Сֵ�����ڵ��ڲ���ֵ��validate:"min=2,max=20" (���ڵ���2С�ڵ���20)
		* ���������ж��ַ�������
	oneof
		* ֻ�����оٳ���ֵ����һ������Щֵ��������ֵ���ַ������Կո�ָ�������ַ������пո񣬽��ַ����õ����Ű�Χ��validate:"oneof=red green"
	len
		* �ж��ַ���/map/slice �ĳ���

--------------------------
�ַ���
--------------------------
	contains
		* ���������Ӵ���validate:"contains=tom" (�ֶε��ַ���ֵ����tom)
	excludes
		* ���������Ӵ���validate:"excludes=tom" (�ֶε��ַ���ֵ������tom)
	startswith
		* �Բ����Ӵ�Ϊǰ׺��validate:"startswith=golang"
	endswith
		* �Բ����Ӵ�Ϊ��׺��validate:"startswith=world"
	alpha
		* ֻ������ĸ
	alphanum
	alphanumunicode
	alphaunicode
	ascii
	containsany
	containsrune
	endswith
	lowercase
	multibyte
	number
	numeric
	printascii
	uppercase



--------------------------
�ֶ���֤
--------------------------
	eqcsfield
		* �粻ͬ�ṹ���ֶ���֤������˵ Struct1 Filed1����ṹ��Struct2 Field2���
			type Struct1 struct {
				Field1 string `validate:eqcsfield=Struct2.Field2`
				Struct2 struct {
					Field2 string 
				}
			}

	necsfield
		* �粻ͬ�ṹ���ֶβ����
	
	eqfield
		* ͬһ�ṹ���ֶ���֤��ȣ�����ľ�������2��������֤
			type User struct { 
				Password string `validate:"min=10"`
				Password2 string `validate:"eqfield=Password"`
			}

	nefield
		* ͬһ�ṹ���ֶ���֤�����
	
	gtefield
		* ���ڵ���ͬһ�ṹ���ֶΣ�validate:"gtefiled=Field2"
	
	ltefield
		* С�ڵ���ͬһ�ṹ���ֶ�
	
--------------------------
������֤
--------------------------
	ip
		* �ֶ�ֵ�Ƿ������Ч��IP��ַ��validate:"ip"
	ipv4
		* �ֶ�ֵ�Ƿ������Ч��ipv4��ַ��validate:"ipv4"
	ipv6
		* �ֶ�ֵ�Ƿ������Ч��ipv6��ַ��validate:"ipv6"
	uri
		* �ֶ�ֵ�Ƿ������Ч��uri��validate:"uri"
	url
		* �ֶ�ֵ�Ƿ������Ч��uri��validate:"url"

--------------------------
��ʽ��
--------------------------
	base64
		* �ֶ�ֵ�Ƿ������Ч��base64ֵ
	
	base64url
	btc_addr
	btc_addr_bech32
	datetime
	e164
	email
	eth_addr
	hexadecimal
	hexcolor
	hsl
	hsla
	html
	html_encoded
	isbn
	isbn10
	isbn13
	json
	latitude
	longitude
	rgb
	rgba
	ssn
	uuid
