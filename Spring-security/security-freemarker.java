-------------------------
freemarker��			 |
-------------------------
	# ����
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-taglibs</artifactId>
		</dependency>
		
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>javax.servlet.jsp-api</artifactId>
			<version>2.3.3</version>
			<scope>provided</scope>
		</dependency>

	
	# ���� spring-security-taglibs.jar �е��ļ�: /META-INF/security.tld ��Ŀ¼ /resources/tld/security.tld ��
	
	# �������
		import java.util.Arrays;
		import javax.annotation.PostConstruct;

		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

		import freemarker.ext.jsp.TaglibFactory;

		@Configuration
		public class FreemarkerConfiguration {

			@Autowired
			private FreeMarkerConfigurer configurer;

			@PostConstruct
			public void freeMarkerConfigurer() {
				TaglibFactory taglibFactory = configurer.getTaglibFactory();
				taglibFactory.setClasspathTlds(Arrays.asList("/tld/security.tld"));
				if (taglibFactory.getObjectWrapper() == null) {
					taglibFactory.setObjectWrapper(configurer.getConfiguration().getObjectWrapper());
				}
			}
		}


	# freemarker ҳ������ʹ��
		<#assign security=JspTaglibs["/WEB-INF/classes/tld/security.tld"] />
	
		<@security.authorize access="hasRole('ADMIN')">
		</@security.authorize>

-------------------------
freemarker��			 |
-------------------------
	<@security.authorize access="hasRole('ADMIN')">
	</@security.authorize>


	<@security.csrfInput/>