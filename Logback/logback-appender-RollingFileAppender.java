---------------------------------
RollingFileAppender
---------------------------------
	# ���ݴ�С�鵵�ɹ̶���������־
		<appender name="fixed" class="ch.qos.logback.core.rolling.RollingFileAppender">
			<file>D:/log/app.log</file>
			<rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
				<!-- �鵵���ļ���ʽ -->
				<fileNamePattern>D:/log/app.%i.log.zip</fileNamePattern>
				<!-- ��ʼ�ͽ����Ĺ�����־�ķ�Χ -->
				<minIndex>1</minIndex>
				<maxIndex>3</maxIndex>
			</rollingPolicy>

			<triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
				<!-- 10Kb��С������һ�ι鵵 -->
				<maxFileSize>10KB</maxFileSize>
			</triggeringPolicy>

			<encoder>
				<pattern>${FILE_LOG_PATTERN}</pattern>
			</encoder>
		</appender>

	# ���ݴ�С�����ڽ��й鵵
		<appender name="rolling" class="ch.qos.logback.core.rolling.RollingFileAppender">
			<file>D:/log/app.log</file>
			<encoder>
				<pattern>${FILE_LOG_PATTERN}</pattern>
			</encoder>
			<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
				<maxFileSize>100KB</maxFileSize>
				<fileNamePattern>D:/log/%d{yyyy-MM-dd}/%d{yyyy-MM-dd}-%i.log.zip</fileNamePattern>
				<maxHistory>50</maxHistory>
				<totalSizeCap>10MB</totalSizeCap>
				<cleanHistoryOnStart>false</cleanHistoryOnStart>
			</rollingPolicy>
		</appender>


---------------------------------
rollingPolicy
---------------------------------
	# FixedWindowRollingPolicy
	# TimeBasedRollingPolicy
	# SizeAndTimeBasedRollingPolicy
		* �������ںʹ�С�����洢
		* ���� rollingPolicy ���� triggeringPolicy

		<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
			<!-- �����ļ�����С�����������С���ļ��������һ�ι鵵 -->
			<maxFileSize>100KB</maxFileSize>
			<!-- 
				�ļ��Ĺ鵵���ƣ����Ը�ʽ��
				���õ�
					�����չ鵵��D:/log/%d{yyyy-MM-dd}/%d{yyyy-MM-dd}-%i.log.zip		/log/2020-11-04/2020-11-04-4.log.zip
				
				��� fileNamePattern �� .gz ���� .zip ��β�������Զ�ѹ��
			 -->
			<fileNamePattern>D:/log/%d{yyyy-MM-dd}/%d{yyyy-MM-dd}-%i.log.zip</fileNamePattern>
			<!-- 
				���浵��־����������첽ɾ������һɾ�����������ļ����ƹҹ����ļ������ǰ��չ鵵����������Ǳ������־������ 
			-->
			<maxHistory>50</maxHistory>
			<!-- ������־���ܴ�С����������첽ɾ�����ڶ�ɾ������ -->
			<totalSizeCap>10MB</totalSizeCap>
			<!-- �������Ϊtrue����ô�� appender ������ʱ�򣬹鵵�ļ����ᱻɾ���� -->
			<cleanHistoryOnStart>false</cleanHistoryOnStart>
		</rollingPolicy>
	
	# SocketAppender and SSLSocketAppender


---------------------------------
triggeringPolicy
---------------------------------
	# TimeBasedRollingPolicy
	# SizeAndTimeBasedRollingPolicy
	# TriggeringPolicyBase
	# SizeBasedTriggeringPolicy
	# DefaultTimeBasedFileNamingAndTriggeringPolicy
	# SizeAndTimeBasedFNATP
