---------------------------
log
---------------------------
	# ��־���ѡ��
		* ��������õ� vertx.logger-delegate-factory-class-name ϵͳ���Ա�ʾ��������
		* ������·���´��� vertx-default-jul-logging.properties �ļ�ʱ����ʹ��JDK logging
		* ��������·���д�������ʵ�֣�������������˳�����ѡ��
			SLF4J
			Log4J
			Log4J2
		
		* Ĭ��ʹ��JDK��־��¼
	
	# ǿ������netty����־���Ϊlog4j2
		InternalLoggerFactory.setDefaultFactory(Log4J2LoggerFactory.INSTANCE);