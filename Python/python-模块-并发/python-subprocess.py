----------------------------
subprocess					|
----------------------------
	* �����ӽ���,����ִ��ϵͳ����
	* �����ӽ��̹���

----------------------------
subprocess-����				|
----------------------------
	PIPE = -1
	STDOUT = -2
	DEVNULL = -3

----------------------------
subprocess-����				|
----------------------------
	Popen def __init__(self, args, bufsize=-1, executable=None,
                 stdin=None, stdout=None, stderr=None,
                 preexec_fn=None, close_fds=True,
                 shell=False, cwd=None, env=None, universal_newlines=None,
                 startupinfo=None, creationflags=0,
                 restore_signals=True, start_new_session=False,
                 pass_fds=(), *, encoding=None, errors=None, text=None)
		
		* ����(����)һ���ӽ���,ִ��һ��shell����,����һ�� Popen ����
		* ��������������,��һ��Ԫ��������,ʣ�µ������������
			Popen(["mkdir","t1"])
		* ����Ҳ����ֱ�Ӿ���һ������
			Popen("mkdir t2", shell=True)
		* ����ʹ�� with ���
			with subprocess.Popen(...) as popen:
				pass

		* �ؼ��ֲ���
			shell
				* bool ֵ
				* shell��ΪҪִ�еĳ���,���shellֵΪTrue,���齫args������Ϊһ���ַ������ݶ���Ҫ��Ϊһ�����д���

			stdout	
				* ö��ֵ,ָ������ִ�к�������ݵĹܵ�
				* subprocess.PIPE		:���ӽ���ת��������
			
			cwd
				* �����ֵ��ΪNone,���л�����Ŀ¼ִ������

			env
				* ��������,�����ֵΪNone��ô�ӽ��̵Ļ����������Ӹ������м̳�
				* ���env!=None,����ֵ������һ��ӳ�����
			
			universal_newlines
				* ����ò���ֵΪTrue,����ļ������stdin,stdout��stderr������Ϊ�ı�������
				* �������ǽ��ᱻ��Ϊ������������
			

	str getoutput(commond)
		* ִ��SHELL����,����ִ�к�Ľ��

	tuple getstatusoutput(commond)
		* ִ��shell����,����Ԫ��,��һ��������״̬intֵ,�ڶ���������ִ�к�Ľ��
	
	check_output()
		* �����̵ȴ��ӽ������
		* ���ִ���쳣���׳�:subprocess.CalledProcessError
		* �����ӽ������׼�����������
		* �ؼ��ֲ���
			stderr
			shell
		* demo
			subprocess.check_output('dir',stderr=subprocess.STDOUT,shell=True)
	
	run(*popenargs, input=None, capture_output=False, timeout=None, check=False, **kwargs)
		* ִ��ָ��������,�ȴ�����ִ����ɺ󷵻�һ������ִ�н���� CompletedProcess ���ʵ��
			popenargs 
				* Ҫִ�е�shell����,Ĭ��Ӧ����һ���ַ������У���['df', '-Th']��('df', '-Th')
				* Ҳ������һ���ַ���,��'df -Th',���Ǵ�ʱ��Ҫ��shell������ֵ��ΪTrue

			check
				* ���check������ֵ��True,��ִ������Ľ����Է�0״̬���˳�,����׳�һ��CalledProcessError���쳣,�Ҹ��쳣�������� ����,�˳�״̬��,�Լ�stdout��stderr(��������б�����Ļ�)


		
----------------------------
subprocess.Popen			|
----------------------------
	# ʵ������
		stdout
		stdint
		stderr
			* ���ر�׼������,���,�쳣��
		pid
			* ����pid

	# ʵ������
		stdout.read()
			* ��ȡִ�н��,���صĽ�����ֽ���������
			* ��׼���
		
		wait()
			* ��ǰ�߳�����,ֱ���ӽ��̵�����ִ�����
			* �ؼ��ֲ���
				timeout
					*  ָ����ʱ��ʱ��(��),�����ʱ���׳��쳣:TimeoutExpired
	
		poll()
			* ����ӽ����Ƿ����,��������˷���״̬��,���򷵻� None
		
		terminate()
			* ֹͣ�ý���
		
		kill()
			* ɱ���ý���
		
		send_signal(signal)
			* �����̷����ź�
		
		communicate(input=None, timeout=None)	
			* ����̽��н���,���緢�����ݵ�stdin,��stdout��stderr��ȡ����,ֱ�������ļ�ĩβ
			* �÷����еĿ�ѡ���� input Ӧ���ǽ������͸��ӽ��̵�����,������û�����ݷ��͸��ӽ���,�ò���Ӧ����None
			* input�������������ͱ������ֽڴ�,��� universal_newlines ����ֵΪTrue,��input�������������ͱ������ַ���
			* �÷�������һ��Ԫ��(stdout_data, stderr_data),��Щ���ݽ������ֽڴ����ַ���(���universal_newlines��ֵΪTrue)
			* �����timeoutָ����������ý��̻�û�н���,�����׳�һ��TimeoutExpired�쳣,��������쳣,Ȼ�����³���ͨ�Ų��ᶪʧ�κ����������
			* ���ǳ�ʱ֮���ӽ��̲�û�б�ɱ��,Ϊ�˺���������Ӧ������,һ���õ�Ӧ��Ӧ���ֶ�ɱ������ӽ���������ͨ��
			* Ҫע�����,�����ȡ�������ǻ������ڴ��е�,����,������ݴ�С�ǳ�����������޵�,�Ͳ�Ӧ��ʹ���������


	# demo
		import subprocess
		with subprocess.Popen('dir',shell=True,stdout=subprocess.PIPE,stderr=subprocess.PIPE) as popen:
			# stdout=subprocess.PIPE ����ӽ���ִ�еĽ������,���ݸ�������.���ո�ֵ��ִ�к�Ķ���.���û�иò���,��ֱ�Ӵ�ӡ,�����ض����ܶ�ȡ��ִ�н��
			result = popen.stdout.read()	# ��ȡ���
			error  = popen.stderr.read()	# ��ȡ�쳣���,���û���쳣,��ֵΪNone
			print(str(result,'GBK'))

		
		with subprocess.Popen("python",shell=True,stdin=subprocess.PIPE,stdout=subprocess.PIPE,stderr=subprocess.PIPE) as popen:
			popen.stdin.write(b"print \"Hello\"")
			(out,err) = popen.communicate()
			print(out)#b'Hello\n'
			print(err)#b''