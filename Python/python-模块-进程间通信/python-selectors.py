------------------------------
selectors					  |
------------------------------
	# selectorsģ������python3.4�汾�������ģ�����װ��IO��·�����е�select��epoll���ܹ����죬�������ʵ�ֶಢ��Ч��
	# ��ģ������߲��Ч�� I/O ��·����
		https://yiyibooks.cn/xx/python_352/library/selectors.html
		https://docs.python.org/3/library/selectors.html#module-selectors
	# ������ ѡ�� select ����֮��,����ʹ�ô�ģ��,�����뾫ȷ����ϵͳ����ԭ������ʹ��
	# ��ģ�鶨����һ����������Լ�N���ʵ��
		BaseSelector
			SelectSelector	����select.select()��ѡ����
			PollSelector	����select.poll()��ѡ����
			EpollSelector	����select.epoll()��ѡ����
			DevpollSelector	����select.devpoll()��ѡ����
			KqueueSelector	����select.kqueue()��ѡ����
	
	# ��java��nioûɶ����
	# ģ���µ�������
		class Mapping(Collection)

------------------------------
ģ�����Ժͷ���				  |
------------------------------
	EVENT_READ
	EVENT_WRITE
		* ��д�¼���ʶ
	DefaultSelector
		* Ĭ��ѡ������ʹ���ڵ�ǰƽ̨�Ͽ��õ�����Ч��ʵ��,������û���Ĭ��ѡ��
		* Ĭ�ϻ���epoll,���ϵͳ��û��epoll(����windows)����Զ�ʹ��select

------------------------------
ģ����	SelectorKey			  |
------------------------------
	# ���ļ������������ײ��ļ�������
	
	fileobj
		* ע����ļ�����(����)

	fd
		* �ļ���ʶ��

	events
		* ���ع�ע���¼�

	data
		* ע��ʱ���õĹ�������

------------------------------
ģ����	BaseSelector		  |
------------------------------
	# ����
	# ����
		SelectorKey register(fileobj, events, data=None)
			* ע��

		SelectorKey unregister(fileobj)
			*  ȡ��ע��

		SelectorKey modify(fileobj, events, data=None)
			* �޸Ĺ��ĵ��¼�����data

		[(SelectorKey, events)] select(timeout=None)
			* ��ʼ��ѯ

		None close()
			* �ر�

		SelectorKey get_key(fileobj)
			*  ��ȡָ��fileobj��SelectorKey

		Mapping get_map()
		

	
------------------------------
demo						  |
------------------------------
	
import selectors
from selectors import SelectorKey
import socket

# Ĭ�ϵ�selector
selector = selectors.DefaultSelector()

server = socket.socket(family=socket.AF_INET, type=socket.SOCK_STREAM)
server.bind(('0.0.0.0', 1024))
server.listen()
server.setblocking(False)  # ����Ϊ������ģʽ

# ע����¼�
selector.register(server, selectors.EVENT_READ) 


# ���������¼�
def accept(key,event):
    connection,address = key.fileobj.accept()           # ��ȡ���ͻ�������
    connection.setblocking(False)                       # ����Ϊ������ģʽ
    selector.register(connection, selectors.EVENT_READ) # ע����¼�

# �����ȡ�¼�
def readable(key,event):
    connection = key.fileobj
    data = connection.recv(1024)    # ��ȡ����
    if data:
        print('�յ�����:%s'%(data.decode('UTF_8')))
    else:
        selector.unregister(connection) # ���ӹر�,�Ƴ�ע��
        connection.close()

while True:
    events = selector.select()
    for key,event in events:
        print(key,event)
        if key.fileobj == server:
            accept(key,event)       # �µ�����
        else:
            readable(key, event)    # �ɶ�����