-----------------
ClassLoader		 |
-----------------
	# ��������ļ����ؼ�����
		protected final Class<?> defineClass(String name, byte[] b, int off, int len)
		protected final Class<?> defineClass(String name, byte[] b, int off, int len,ProtectionDomain protectionDomain)
		protected final Class<?> defineClass(String name, java.nio.ByteBuffer b, ProtectionDomain protectionDomain)
			* ���ڰ��ֽڻ����ݽ���ΪJVM�ܹ�ʶ���Class����
			* �÷���ֻ�ǻ����ɶ���, ���ǻ�û resolve

		protected Class<?> findClass(String name) 
			* �Զ��������, һ�㸲д����� findClass �����������ļ���

		
		protected Class<?> loadClass(String name, boolean resolve)
		public Class<?> loadClass(String name)

		protected final void resolveClass(Class<?> c) 
			* ������ʼ����, ��������

	# �Զ����������
		class MyClassLoader extends ClassLoader {
			@Override
			protected Class<?> findClass(String name) throws ClassNotFoundException {
				// ����ί�м���
				Class<?> clazz = super.loadClass(name);
				if (clazz != null){
					return clazz;
				}
				/**
				 * TODO �Լ����Լ���, ���������, �׳��쳣:ClassNotFoundException
				 * TODO ����������ȡ���ֽ�����, ת��Ϊ�����
				 */

				byte[] data = new byte[1024];
				clazz = defineClass("", data, 0, data.length);
				return clazz;
			}
		}

		* ��ò�Ҫ��д loadClass ����, ��Ϊ���������ƻ�˫��ί��ģʽ
	
	# һ�㶼�����Կ��Ǽ̳�:URLClassLoader
		* ��Ϊ������������˴󲿷ֵļ��ع���

---------------------------------------------
Ӧ�ó���-�����Զ���·���µ�class�ļ�		 |
---------------------------------------------

---------------------------------------------
Ӧ�ó���-��Ҫ���Լ����ص��������⴦��		 |
---------------------------------------------

---------------------------------------------
Ӧ�ó���-�Ȳ���								 |
---------------------------------------------