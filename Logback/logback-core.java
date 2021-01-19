------------------
core
------------------
	# 3������
		Logger
		Appender
		Layouts

------------------
Logger
------------------
	# Logger ����һ��ʵ�壬���ǵ������Ǵ�Сд���е�
	# ������νṹ
		* ���һ�� logger �����ּ���һ�� . ��Ϊ��һ�� logger ���ֵ�ǰ׺����ô�� logger ������һ�� logger �����ȡ�
		* com.foo �� logger ����Ϊ com.foo.Bar �� logger �ĸ���
		* java �� logger ����Ϊ java.util �ĸ���������Ϊ java.util.Vector �����ȡ�
	

	
	# ROOT LOGGER
		* root logger ��Ϊ logger ��νṹ����߲�
		* ����һ������� logger����Ϊ����ÿһ����νṹ��һ����
			Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
	
	# �ȼ��̳�
		* ��ͬ�ĵȼ���TRACE, DEBUG, INFO, WARN, ERROR�������ڣ�ch.qos.logback.classic.Level ����
		* ���һ�������� logger û��ָ��һ���㼶����ô���ͻ�̳����������һ�����ȵĲ㼶��
		* Ϊ��ȷ�����е� logger ����һ���㼶��root logger ����һ��Ĭ�ϲ㼶 --- DEBUG
	

	# ������ӡ�Լ�����ѡ�����
		* һ������־�Ĵ�ӡ������� logger ����Ч���𣬸�����־�ſ��Ա���ӡ����
		* ���������� logbakc �ĺ��ġ������������Ϊ��TRACE < DEBUG < INFO < WARN < ERROR
	
	# logger ������
		* logback �����ϸ����� logger ������������ȫ���Ը����Լ���ϲ�������㿪�ľͺ�
		* �������ȫ�޶������� logger ������������Ŀǰ��õķ�ʽ��û��֮һ��
	
------------------
Appender
------------------
	# ��־���Ŀ�ĵؽ��� appender��
		* appender ����console��file��remote socket server��MySQL��PostgreSQL��Oracle 
		* �������������ݿ⡢JMS��remote UNIX Syslog daemons ��
	
	# һ�� logger �����ж�� appender��
		* ÿһ�������������־���ᱻת������ logger ������ appender ��ȥ��
	
	# appender �� logger �Ĳ㼶�ṹ��ȥ�̳е����ԡ�
		* ��� root logger �����һ�� console appender�����������������־���ٻ��ڿ���̨��ӡ����
		* �ٸ�һ������ L �� logger �����һ�� file appender����ô L �Լ� L ���Ӽ� logger ���������ļ��Ϳ���̨��ӡ��־
		
		* logger L ����־���������� L �������Ӽ������е� appender���������ν�� appender �����ԣ�appender additivity��

		* ����ͨ������ additivity = false ����дĬ�ϵ����ã�Ĭ������ additivity = true�������� appender �����پ��е����ԡ�
		* ��Logger �Ƿ�̳и�����Logger �� ���Դ��appender�� �ı�־λ��

		* ��� L ��ĳ���ϼ� logger Ϊ P���� P ������ additivity = false����ô L ����־���ڲ㼶�� L �� P ֮������� logger �� appender������ P ����� appender �������
		* ���ǲ����� P ���ϼ� appender �������
		
		* root logger Ϊ logger �㼶�е���߲㣬additivity ����������


------------------
Layout
------------------
	# layout �������ǽ���־��ʽ��

	# PatternLayout 
		* ���õľ�����
		* �ܹ������û�ָ���ĸ�ʽ����ʽ����־�������� C ���Ե� printf ������


	# ���������
		Object[] paramArray = {newVal, below, above};
		logger.debug("Value {} was inserted between {} and {}.", paramArray);
