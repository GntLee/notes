--------------------------------
 Session
 --------------------------------
 	# Session�ӿڣ� interface Session 

	Session regenerateId();
	String id();
	Session put(String key, Object obj);
	Session putIfAbsent(String key, Object obj);
	Session computeIfAbsent(String key, Function<String, Object> mappingFunction);
	<T> T get(String key);
	<T> T remove(String key);
	Map<String, Object> data();
	boolean isEmpty();
	long lastAccessed();
	void destroy();
	boolean isDestroyed();
	boolean isRegenerated();
	String oldId();
	long timeout();
	void setAccessed();
		* �ֶ����Session�Ѿ����ʹ�
		* Session�������Լ���Ӧ����һỰ���洢�ش洢��ʱ���Ự�����Զ����Ϊ�ѷ��ʡ�

	default String value()

