------------------------
Shiro-����Spring		|
------------------------
	

------------------------
Shiro-Shiro Filter		|
------------------------
	# ��web.xml������һ��filter
		<!-- shiro filter -->
			<filter>
			<filter-name>shiroFilter</filter-name>
			<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
			<init-param>
				<param-name>targetFilterLifecycle</param-name>
				<param-value>true</param-value>
			</init-param>
		</filter>

		<filter-mapping>
			<filter-name>shiroFilter</filter-name>
			<url-pattern>/*</url-pattern>			*/
		</filter-mapping>
	
		* ��������������Shiro��filter
	
	# 
	
