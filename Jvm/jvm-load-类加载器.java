-----------------
ClassLoader		 |
-----------------
	# ����������
		* ÿ����, ��Ҫ�ͼ������ļ�����һ��, ȷ����jvm�е�Ψһ��

		* �Ƚ��������Ƿ����, ֻ�����������඼��ͬһ������������ص�ǰ���²�������
		* ����, һ��class�ļ�, ��һ��jvm����, ���Ǵ����������������м���, ��ô�������غ����Ϳ϶������

		* �������Ȱ����� Class �����: equals(), isAssignableForm(), instance() ���صĽ��
		* Ҳ����ʹ�ùؼ���: instanceof �жϽ��


		
	
	# ���������
		* ����������� Bootstrap ClassLoader
			* �� $JAVA_HOME\lib Ŀ¼��, ����ͨ�� -Xbootclasspath ����ָ��
			* ʹ�� C++ ʵ��, ��JVM�����һ����, ���ܱ���������ȡ��
			* �鿴���ļ���Ŀ¼:System.getProperty("sun.boot.class.path")
		
		* ��չ������� Extension ClassLoader
			* ��: sun.misc.Launcher$ExtClassLoader ʵ��, ʵ�ֽӿ�:ClassLoader
			* ��������� $JAVA_HOME\lib\ext Ŀ¼�е�, ���߱� java.ext.dirs ϵͳ����ָ����·���е��������
			* �����߿���ֱ��ʹ����չ�������
			* �鿴���ļ���Ŀ¼:System.getProperty("java.ext.dirs")

		* Ӧ��������� Application ClassLoader
			* ��: sun.misc.Launcher$AppClassLoader ʵ��, ʵ�ֽӿ�:ClassLoader
			* ���������������� ClassLoader �е� getSystemClassLoader() �����ķ���ֵ, ����Ҳ����Ϊϵͳ�������
				ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			
			* ����������û���·��(CLASS_PATH)����ָ�������
			* ���������û�Զ�����������, һ��������������Ĭ�ϵ��������
			* �鿴���ļ���Ŀ¼:System.getProperty("java.class.path")
			
				Class<?> driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println(driverClass.getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
		
		* ���������ȡ����������
			ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
			System.out.println(appClassLoader); //sun.misc.Launcher$AppClassLoader@18b4aac2

			ClassLoader extClassLoader = appClassLoader.getParent();
			System.out.println(extClassLoader);	//sun.misc.Launcher$ExtClassLoader@cc34f4d

			ClassLoader bootstrapClassLoader = extClassLoader.getParent();
			System.out.println(bootstrapClassLoader);	// null ,C++ʵ��,�û������޷���ȡ

		
	# ˫��ί�ɻ���
		* ˫��ί�ɻ���, Ҫ��������������������, ������Ҫ�и��������
		* �Ӹ���ϵ�������ɼ̳�ʵ�ֵ�, ����ʹ����ϵķ�ʽʵ�ֵ�

		* ���һ����������յ������������, �����Ȼ���������ί�и����������ȥ���
		* ÿһ����������������, �������ί�е���������������м���
		* ����������������ɹ�����, ��ô��ֱ�ӷ��ؼ��غ�Ľ��
		* ��������޷�����, �ͳ����Լ�����, �������ʧ��, ���׳��쳣: ClassNotFoundException

		* ���ֻ��Ƶĺô�����, ��û��ʹ���Լ��ļ�����,�����Լ��������Щ��ϵͳ����������, ��ȫ
		* ClassLoader �� loadClass(String name) ����ʵ�ִ���
			protected Class<?> loadClass(String name, boolean resolve)throws ClassNotFoundException {
				synchronized (getClassLoadingLock(name)) {
					// ����, ������Ƿ񱻼��ع�
					Class<?> c = findLoadedClass(name);
					if (c == null) {
						long t0 = System.nanoTime();
						try {
							if (parent != null) {
								c = parent.loadClass(name, false);
							} else {
								c = findBootstrapClassOrNull(name);
							}
						} catch (ClassNotFoundException e) {
							// �����������׳��� ClassNotFoundException , ˵����������ʧ��
						}

						if (c == null) {
							// ����������, ����ʧ�ܵ������, ���ñ���� findClass() ���м���

							long t1 = System.nanoTime();
							c = findClass(name);

							// �������������ļ�¼ͳ������
							sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
							sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
							sun.misc.PerfCounter.getFindClasses().increment();
						}
					}
					if (resolve) {
						resolveClass(c);
					}
					return c;
				}
			}
		
		* �������Լ��������������, ʹ�� defineClass() ȥ����һ���� "java.lang" ��ͷ����, Ҳ����ɹ�
		* ��õ�JVM�׳��İ�ȫ�쳣
	
	# �ƻ�˫��ί�л���
		* ˫��ί�ɻ���, ������ǿ���Ե�, �����Ƽ��������ߵļ�����ʵ��ģʽ
		
		* ˫��ί�ɻ��Ƶ�һ�α��ƻ�, ����Ϊ��ʷ��������
		* �û����� jdk1.2 �ű�����, ������������ͳ�����: ClassLoader �� jdk1.0 �ʹ���
		* ������Щ, �Ѿ����ڵ��û��Զ��������ʵ��, ���ò�������Э,

		* Ϊ����ǰ����, JDK1.2 ���� ClassLoader �����һ������: protected Class<?> findClass(String name)
		* �ڴ�֮ǰ, ���ڼ̳� ClassLoader ����, ΨһĿ�ľ���Ϊ�˸�д: loadClass() ����, ��ΪJVM�ڽ�������ص�ʱ������˽�з���:private Class<?> loadClassInternal(String name) 
		* �����������Ψһ�߼�����ȥ�����Լ���: loadClass() ����

		* JDK1.2�󲻽��鸲д: loadClass() ����, ���ǰѼ����߼�д�� findClass() ������
		* loadClass() ����������߼�, ��������������ʧ��, �Ż���� findClass() ��ɼ���
		* �����Ĺ����Ƿ���˫��ί�ɻ��Ƶ�

	
	
		* ˫��ί�ɻ��Ƶڶ��α��ƻ�, ����Ϊ���ģ�������ȱ�ݵ��µ�
		* �û��ƺܺõĽ���˻������ͳһ���ص�����(Խ�ǻ�������,Խ�����ϲ�ļ��������м���)

		* ���ǻ�������, ������Ҫ�ص��û��Ĵ���, ����: JNDI
		* JNDI��Ŀ��������Դ���м��й���Ͳ���, ����Ҫ�����ɳ���ʵ��, ���Ҳ�����classpath�µ�JNDI�ӿ��ṩ�ߵĴ���
		* ���������������, ����ʶ��Щ����

		* Ϊ�˽���������, Java����Ŷӹĵ���һ�� �߳������ļ���: Thread Context ClassLoader(��ʵ��Ƶò�̫����)
		* Thread ��ʵ����һ������:public void setContextClassLoader(ClassLoader cl)
		* ��������̵߳�ʱ��, �ü�������û����, ������Ӹ����̼̳߳�һ��, ���Ӧ�ó���ȫ�ַ�Χ�ڶ�û���ù��Ļ�, ��ô����������Ĭ�Ͼ���Ӧ�ó����������

		* JDNI����ʹ������߳������ҵļ�����ȥ��������Ҫ��SPI����, Ҳ���Ǹ���������������������ȥ��������
		* ������Ϊ, ʵ���Ͼ��Ǵ�ͨ��˫��ί��ģ�͵Ĳ�νṹ������ʹ�ü�����
		* �Ѿ�Υ����˫��ί��ģ�͵�һ����ԭ��, ����û��, JAVA�漰 SPI�ļ��ض���, �������������ַ�ʽ



		* ˫��ί�ɻ��Ƶĵ����α��ƻ�, ����Ϊ�Գ���Ķ�̬��׷���µ�
		* ��̬��: �������滻, ģ���Ȳ���
		* ĿǰOSGi�Ѿ���ҵ����ʵ�ϵ�Javaģ���׼, ��OSGiʵ��ģ�黯�Ȳ���Ĺؼ��������Զ��������������Ƶ�ʵ��

		* ÿһ������ģ�鶼��һ���Լ����������, ����Ҫ����һ�� Bundle ��ʱ��, �Ͱ� Bundle ��������һ�𻻵�, ʵ�ִ�������滻
		* ��OSGi������, �����������˫��ί��ģ���е����ṹ,���Ǹ��Ӹ��ӵ���״�ṹ

		* OSGi�����������˳��
			1. �� java.* ��ͷ����ί�ɸ��������������
			2. ����, ��ί���б������ڵ���ί�ɸ��������������
			3. ����, �� import �б��е���ί�ɸ� Export ������ Bundle �������������
			4. ����, ���ҵ�ǰ Bundle �� ClassPath, ʹ���Լ��������������
			5. ����, �������Ƿ����Լ��� Fragment Bundle ��, �����, ��ί�и� Fragment Bundle �������������
			6. ����, ���� Dynamic import �б�� Bundle, ί�ɸ���Ӧ�� Bundle �������������
			7. ����, �����ʧ��

		* ֻ�п�ͷ2�����˫��ί�ɹ���, ���������Ҷ�����ƽ������������н��е�
		





		