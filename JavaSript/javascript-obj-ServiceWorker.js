
----------------------------
ServiceWorer				|
----------------------------
	# �ο�
		https://developer.mozilla.org/zh-CN/docs/Web/API/Service_Worker_API
		https://worktile.com/blog/tech/tech-service-worker

	# �ȸ��Demo
		https://github.com/GoogleChrome/samples/tree/gh-pages/service-worker/basic

	# ע��һ��sw
		if ('serviceWorker' in navigator) {
			window.addEventListener('load', function() {
				navigator.serviceWorker.register('/sw.js');
			});
		}
		
		* register ����һ�� Promise ����
		* û�б�Ҫ����һ�δ�ҳ���Ҫ������Դ, ��ȷ��������, ҳ��������Ժ�sw����
	
	# sw������
		* ����ʹ��ͬ����API(XMLHttpRequest,localStorage ...), ����ʹ�� Fetch
		* ����ʹ�� self �ؼ�����ָ�� this
		* ֻ��ʹ�� https ���� localhost
		* ���ܷ���DOM, ��ȫ�첽
		* ���Է���Cache��IndexDB

	# sw��������ͬ, ������ fetch ����Ҳ�ǲ�һ����
		* �����sw�ļ����ڸ�Ŀ¼��λ�� '/sw/sw.js' ·���Ļ�, ��ô��sw��ֻ�ܼ��� '/sw/*'���������
		* �����Ҫ�������������������취, һ���ǽ�sw.js���ڸ�Ŀ¼��

		* ��������ע����ʱ������scope, 
			navigator.serviceWorker.register('/static/service-worker.js', {scope: '/'}).then(registration => {
				console.log('Registered events at scope: ', registration.scope);
			}).catch(err => {
				console.error(err)
			});
		* �������ַ�ʽ, ��Ҫ��������Ӧ��js(WS)���ļ�ʱ��, ���һ��httpͷ������SW���Լ����������·��
			Service-Worker-Allowed: /
	


----------------------------
ServiceWorer �¼�����������	|
----------------------------
	# ��������
		register 
			* ������� client �˷���, ע��һ�� serviceWorker, ����Ҫһ��ר�Ŵ���sw�߼����ļ�

		Parsed 
			* ע����ɣ������ɹ�����δ��װ

		installing 
			* ע���У���ʱ sw �лᴥ�� install �¼���
			* ��֪ sw �ж����¼������ķ�ʽ���е��߼����ã�����¼����� event.waitUntil() ���ȴ������ Promise ��ɲŻ�ɹ�

		installed(waiting) 
			* ע����ɣ�����ҳ�汻�ɵ� Service Worker �ű�����,
			* ���Ե�ǰ�Ľű���δ����ڵȴ��У�����ͨ�� self.skipWaiting() �����ȴ���

		activating 
			* ��װ��Ҫ�ȴ����Ҳ���� activated �¼���ֻҪ register �ɹ���ͻᴥ�� install ���������������� activated
			* ����¼����� event.waitUntil() ���ȴ���� Promise ��ɲŻ�ɹ�����ʱ���Ե��� Clients.claim() �ӹ�����ҳ�档

		activated 
			* �� activated ֮��Ϳ��Կ�ʼ�� client ������������ش���sw ���������õ��� fetch api��XHR�޷�ʹ��

		fetch 
			* �����Ժ�ʼ����ҳ�з��������������ش���

		terminate
			* ��һ���������������жϴ����� sw ��ʱ�䲻��֮�󣬴�������״̬���������Ѹ� sw ��ͣ��ֱ���ٴ�ʹ��

		update
			* ��������Զ���� sw �ļ��ĸ��£����и���ʱ�����ز� install����ҳ���л����ϵ� sw �ڿ��ƣ�ֻ�е��û��¿����ں��µ� sw ���ܼ������ҳ��

		redundant
			* ��װʧ��, ���߼���ʧ��, ���߱��µ� Service Worker �����

	# sw���Լ������¼�
		self.addEventListener('install', (event) => {
			
		});

	install
		* ��Ҫ��������, ץȡ��Դ���л���

	activate
		* ��������, ������ڵ���Դ

	fetch
		* ��������, ��ѯ�����������, �����������Դ
		


----------------------------
ServiceWorer API			|
----------------------------
	# ����: navigator �����һ������

	register(js, options)
		* ע��һ��ws, ���ص��� Promise
		* ��1��������js��·��
		* ��2�����������ö���
			scpe
				* ���������scope

		* Promise���������Ժͷ���
			scope	
				* ���������scope

	getRegistration(js)
		* ��ȡ�Ѿ�ע�ᵽ��ws, ����Promise
		* Promise���������Ժͷ���
			unregister()
				* ȡ��ע��(ע��)
