---------------------------
��־					   |
---------------------------
	# ��־Handler,���̳�:ChannelDuplexHandler
		LoggingHandler
	
	# ���캯��
		LoggingHandler(LogLevel level) 

		* level ��־����ö��:LogLevel(Ĭ�� DEBUG)
			TRACE(InternalLogLevel.TRACE)
			DEBUG(InternalLogLevel.DEBUG) 
			INFO(InternalLogLevel.INFO)
			WARN(InternalLogLevel.WARN)
			ERROR(InternalLogLevel.ERROR)
	
	
	# ֻ�����־Handler��ӵ� pipeline ��


	# ����netty��loggerFactory��ʹ�ò�ͬ����־���
		* ʹ�þ�̬����:InternalLoggerFactory.setDefaultFactory(InternalLoggerFactory defaultFactory)

		InternalLoggerFactory.setDefaultFactory(new Log4JLoggerFactory());
	
