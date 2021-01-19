----------------------------
requests					|
----------------------------
	# http ��
	# �ĵ���ַ
		http://docs.python-requests.org/zh_CN/latest/user/quickstart.html
		http://cn.python-requests.org/zh_CN/latest/

	# ��װ
		pip install requests

----------------------------
requests	 ִ������		|
----------------------------
	# ִ�и��ַ���������
		requests.get()
		requests.post()
		requests.delete()
		requests.put()
		requests.heade()
		requests.options()
		requests.patch()
	
	# ִ������Ĳ���
		��һ������
			* ����url
		params
			* ��һ�� dict,��get������,�ᱻ����ΪURL����
			* ���л�Ϊ�������,��ӵ�uri����
			* None ���ᱻ��ӵ�����
			* ����ֵ������һ������
				payload = {'key1': 'value1', 'key2': ['value2', 'value3']}
				http://xxx.org/get?key1=value1&key2=value2&key2=value3
		stream
			* bool ֵ,��Ӧ�Ƿ���һ��������
			* �����ֵΪ True,��ô����ͨ��response��raw���Ի�ȡ����������ԭʼ�׽�����Ӧ
			* Ĭ�������,���������������,��Ӧ�������������,����ͨ������ stream=True �ı������Ϊ,�Ƴ�������Ӧ��ֱ������ response.content ����
			* ���stream=True,Requests �޷��������ͷŻ����ӳ�,�������������е�����,���ߵ����� Response.close �������������Ч�ʵ��µ�����
			* Ӧ�ÿ���ʹ�� with ��䷢������,�������Ա�֤����һ���ᱻ�ر�
				with requests.get('http://xxx.org/get', stream=True) as response:
					pass

		headers 
			* ������һ�� dict ,��ʾ�Զ��������ͷ 
			*  header ֵ������ string,bytestring ���� unicode,���ܴ��� unicode header Ҳ�������,��������������
		
		data
			* ������,�� post ��ʱ��,�ᱻ����Ϊ��
			* ��һ��Ӧ���� dict ,���� tuple(���key),�����ַ���(�Լ����ַ�����ʽ)
				requestBody = (('key1', 'value1'), ('key1', 'value2'))
				# key1=value1&key1=value2
			
		json
			* �����������ΪJSON�ַ���
	
		files
			* ContentType = multipart/form-data ʱ��������
			* �����е����ıʼ�
		
		cookies
			* ����cookie
			* ������һ�� dict

		allow_redirects
			* �Ƿ������ض���
			*  bool ֵ,�����ֵΪ True,����Զ����������� 302/1 �Ľ��
		
		timeout
			* �������ӳ�ʱʱ��,��λΪ��
			* ��ʱ����׳��쳣:requests.exceptions.Timeout
			* ��������һ�� tuple,�����Ļ���һ��������ʾ���ӳ�ʱʱ��,�ڶ���������ʾ��ȡ���ݳ�ʱʱ��
			* �����ֵΪ None,���ʾ��Զ�ȴ�
		
		verify
			* ������һ�� bool ֵ,��ʾ�Ƿ���֤ ssl (https) ����Ч��
			* Ҳ������һ�� string,��ʾ���������� CA ֤���ļ����ļ���·��
			* �����Ϊ�ļ���·��,�ļ��б���ͨ�� OpenSSL �ṩ�� c_rehash ���ߴ���

		cert
			* ������һ�� tuple,��ʾ�ͻ���֤��ĵ�ַ
			* [0] = cert,[1] = key
			* ����֤���˽�� key �����ǽ���״̬,Ŀǰ��Requests ��֧��ʹ�ü��ܵ� key(���� .keystory�ļ�)
		
		proxies
			* ���ô���
		
		auth
			* BasicAuth ��֤��ʽ
			* ������һ��:HTTPBasicAuth ʵ��
				from requests.auth import HTTPBasicAuth
				auth = HTTPBasicAuth('fake@example.com', 'not_a_real_password')
			
----------------------------
requests	 ������Ӧ		|
----------------------------
	# ��ȡ����Ӧ
		response = request.get(....)
			
	# ����
		status_code
			* ״̬��
			* ��ģ��������״̬���ö��
				requests.codes.ok
				...
		text
			* ��������Ӧ���ַ�����
		encoding 
			* �������Ӧ�ַ������ݵı���
			* �������޸ĸ�����,��ȥ��ȡ text ����(����text ������ʹ�� encoding ����ֵ)

		content
			* ���صĶ���������(��Ӧ��)
			* �������Ӧ��һ��ͼƬ
				image = Image.open(BytesIO(response.content))

		json()
			* ��JSON��ʽ������Ӧ��
		raw
			* ��������ԭʼ�׽�����Ӧ
			* ����ȷ���ڳ�ʼ������������ stream=True 

		iter_content()
			* �����ص�ʱ��,�Ƽ�ʹ�õ�,������raw,���Ҵ����˺ܶණ��
			* ���ı������浽�ļ�
				chunk_size = 1024 # ���ݿ���Ĵ�С,�������ɵĸ���Ӧ��������
				with open(filename, 'wb') as fd:
					for chunk in response.iter_content(chunk_size):
						fd.write(chunk)
		iter_lines()
			*  ?

		raise_for_status()
			* ��40x,50x�����,�����׳��쳣,��������¸÷������� None

		headers
			* ��ȡ����Ӧͷ,���ص���һ�� dict
		
		cookies
			* ��ȡ��Ӧ��Cookie,���ص���һ�� dict
		
		history
			* ���ص���һ�� list,ÿһ��Ԫ�ض���һ�� response ����,Ϊ������������������Щ����
			* һ��'�ض���ʱ��'����ͨ�������Ի�ȡ��ִ�е��ض����б�
			* ��������б��մ����ϵ�����������������
		
		url
			* ���������url

		close()
			* �ر���ͻ��˵�����

----------------------------
multipart/form-data	����	|
----------------------------
	# ���ļ�����������
		requestBody = {
			# ��ͨ������(ֵ,None)
			'phone':(None,'18545478548'),
			# �ļ������� (�ļ�����,�������ļ�����,�ļ���Content-Type)
			'attachment':  open('C:\\Users\\Administrator\\Desktop\\222.mp4', 'rb')
		}

		# ִ������
		response = requests.post('http://xxxx/upload', files = requestBody)

		# ��������ӦJSON
		print(response.json())

		* �Լ����headerʱ,��Ҫ��д 'Content-Type' ����

	# �ļ�����Ĺ������Ը��ӵĸ���,�Լ������ļ�������,ContentType,�����header
		(filename, fileobj, contentype, custom_headers)

		requestBody = {
			'attachment': ('222.mp4',open('C:\\Users\\Administrator\\Desktop\\222.mp4', 'rb'),'audio/mp4', {'Expires': '0'})
		}
	
	# �����ͬ������(�ļ�)������
		requestBody = [
			('name',(None,'KevinBlandy0')),
			('name',(None,'KevinBlandy1')),
			('name',(None,'KevinBlandy2')),
			('files',('file1.jpg',open('C:\\Users\\Administrator\\Desktop\\QQͼƬ20180703102725.jpg', 'rb'))),
			('files',('file2.jpg',open('C:\\Users\\Administrator\\Desktop\\΢��ͼƬ_20180716103554.png', 'rb')))
		]

----------------------------
�ļ�����					|
----------------------------
import requests
# �ļ����ص�ַ
url = 'http://xxx.down'
# �����ļ���ַ
file = 'D:\\ħ��ʦ.mp4'
# ������ݿ�Ĵ�С
chunkSize = 1024
with requests.get(url, stream=True) as response:
    with open(file, 'wb') as file:
        for chunk in response.iter_content(chunkSize):
            file.write(chunk)
