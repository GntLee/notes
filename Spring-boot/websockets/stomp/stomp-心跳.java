
------------------------
����
------------------------
	# �ڴ������ӵ�ʱ������heart-beat������������
		heart-beat:<cx>,<cy>
	
	# Req/Resp
		CONNECT
		accept-version:1.2
		heart-beat:<cx>,<cy>
		host:stomp.github.org

		^@

		CONNECTED
		version:1.2
		heart-beat:<sx>,<sy>

		^@

		* ����<cx>, <cy>, <sx>, <sy>�ֱ����һ���Ժ���Ϊ��λ������.

		* <cx>:client�ܱ�֤�ķ�����������С���, �����0����client����������.
		* <cy>:clientϣ���յ�server�����ļ��, �����0����client��ϣ���յ�server������.
		
		* <sx>:server�ܱ�֤�ķ�����������С���, �����0����server����������.
		* <sy>:serverϣ���յ�client�����ļ��, �����0����server��ϣ���յ�client������.

		* ����ڽ�������ʱû������header, Ĭ�ϵ���heart-beat:0,0. Ҳ���ǲ�������, Ҳ��ϣ���Է�������

	# ��������header�������Ӻ�, ����Э�̵ó�����������Ƶ�ʵ��߼�����
			
		* ����client��˵, ȡ <cx> �� <sy> �����ֵ, Ҳ����˵client��ȡclient��С�ܷ��͵ļ����serverϣ��client���ͼ�������ֵ����������.
		* ���<cx>��<sy>���κ�һ��Ϊ0, client������������.

		* ����server��˵, ȡ <sx> �� <cy> �����ֵ, Ҳ����˵serverȡserver��С�ܷ��͵ļ����clientϣ��server���ͼ�������ֵ����������.
		* ���<sx>��<cy>���κ�һ��Ϊ0, server������������.

	# client��server�����������ж��Է��Ѿ��ҵ��˵��߼�����
		
		* ��serverΪ��, ���辭��Э��, clientÿ10�뷢��һ������.
		
		* client������10�����ڸ�server���ٷ���һ������, ����������������������.
		* �����10����clientδ��������, ��ôserver��Ϊ��client��stomp�����Ѿ��ҵ�.
	
