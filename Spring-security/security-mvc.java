# Mvc����
	1, web.xml���� Filter
		<filter>
			<filter-name>springSecurityFilterChain</filter-name>
			<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
		</filter>

		* ��filter�����Ʊ�����: springSecurityFilterChain
		* ����·��: '/*'
	
	2, contextɨ���ʱ��, ���ɨ������:classpath*:spring-security.xml
		<context-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath*:application-*.xml,classpath*:spring-security.xml</param-value>
		</context-param>

	
	3, �������: spring-security.xml
		
	
	