-------------------------------
Spring-boot Web������			|
-------------------------------
	# Spring boot Ĭ����tomcat ��Ϊ����
	# ����Tomcat�����ö���һ������
		* org.springframework.boot.autoconfigure.web.ServerProperties
	# ���ǽ�����Ҫ��:application.properties �н������ü���
		* tomcat������,����һ��:server.tomcat ��ǰ׺

-------------------------------
Spring-boot Web������	������	|
-------------------------------
	# ͨ������
		server.port=80
			* ���ʶ˿�,Ĭ����8080
		server.session-timeout=1800
			* session�����¼�,��λ����
		server.context-path=/
			* ����·��,Ĭ���� /
	
	#  Tomcat����
		server.tomcat.uri-encoding=UTF-8
			* Tomcat����,Ĭ���� UTF-8
		server.tomcat.compression=on
			* Tomcat�Ƿ���ѹ��,Ĭ���� off

	# Jetty����
		
-------------------------------
Spring-bootͨ����������Servlet����|
-------------------------------
	# ��ʹ��properties ,�����ʹ�ô���ķ�ʽ����������
	# '�����ö����е�Servlet��������Ч'
		1,�Զ�����,ʵ�ֽӿ�,
			EmbeddedServletContainerCustomizer
			* '���@Componentע��,����spring����'

		2,��д����
			void customize(ConfigurableEmbeddedServletContainer container);
		3,�����Զ��������
			* ��ʵ���Ƕ� ConfigurableEmbeddedServletContainer �������һЩ�еĲ�������
			setPort(int port);
				* ���ö˿�
			addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));
				* ���ô���ҳ��
			setSessionTomeout(10,TimeUnit.MINUTES);
				* ����Session����
	
	# Ҳ���Ը��ݲ�ͬ��Servlet���������ض�������
		* ��Ҫȥ������ͬ����
		EmbeddedServletContainerFactory(�����ӿ�)
			TomcatEmbeddedServletContainerFactory
				* Tomcat
			JettyEmbeddedServletContainerFactory
				* Jetty
			UndertowEmbeddedServletContainerFactory
				* Undertow


-------------------------------
Spring-boot �ض����� Tomcat		|
-------------------------------
	# ͨ�������� @Configuration �� TomcatEmbeddedServletContainerFactory ����Spring ���й���
		@Configuration
		public class TomcatConfiguration {
			@Bean
			public EmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory(){
				TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = new TomcatEmbeddedServletContainerFactory();
				//�˿�
				tomcatEmbeddedServletContainerFactory.setPort(1234);
				//�쳣ҳ��
				tomcatEmbeddedServletContainerFactory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404/404.html"));
				//session����
				tomcatEmbeddedServletContainerFactory.setSessionTimeout(30, TimeUnit.MINUTES);
				return tomcatEmbeddedServletContainerFactory;
			}
		}

-------------------------------
Spring-boot �ض����� Jetty		|
-------------------------------
	
	# ͨ�������� @Configuration �� JettyEmbeddedServletContainerFactory ����Spring ���й���
	# ��Tomcatһ�����е�����,ֻ��API��ͬ
	

-------------------------------
Spring-boot ʹ��Jetty������		|
-------------------------------
	# Spring boot Ĭ��ʹ�� Tomcat ��Ϊ������
	# �滻Ϊ Jetty ������,ֻ��Ҫ�滻��ص���������
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<!-- �ų�Tomcat���� -->
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<!-- ���jetty���� -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jetty</artifactId>
		</dependency>

-------------------------------
Spring-boot ����SSL				|
-------------------------------
	# HTTPS,������,������Ҫ�� properties �ļ��н�������
		server.ssl.key-store=.keystore
		server.ssl.key-store-password=111111
		server.ssl.keyStoreType=JKS
		server.ssl.keyAlias=tomcat

	# httpת��HTTPS
		* �ܶ�ʱ��,��ַ�������http,���ǻᱻת����https
		* ʵ�����������Ҫ'���������ض�'������ʵ��,��������˵���ض�����(��ͬ�������ò�ͬ��)
			TomcatEmbeddedServletContainerFactory
			JettyEmbeddedServletContainerFactory

		* ����
				import org.apache.catalina.Context;
				import org.apache.catalina.connector.Connector;
				import org.apache.tomcat.util.descriptor.web.SecurityCollection;
				import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
				import org.springframework.beans.factory.annotation.Value;
				import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
				import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
				import org.springframework.boot.web.server.WebServerFactoryCustomizer;
				import org.springframework.context.annotation.Configuration;
				/**
				 * 
				 * 
				 * @author Administrator
				 *
				 */
				@Configuration
				public class TomcatConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

					@Value("${server.ssl.enabled:false}")
					private boolean sslEnable;

					@Value("${server.port}")
					private Integer port;

					static final Integer HTTP_PORT = 80;

					@Override
					public void customize(TomcatServletWebServerFactory factory) {
						if (!sslEnable) {
							return;
						}
						factory.addContextCustomizers(new TomcatContextCustomizer() {

							@Override
							public void customize(Context context) {
								
								SecurityConstraint securityConstraint = new SecurityConstraint();
								securityConstraint.setUserConstraint("CONFIDENTIAL");
								
								SecurityCollection collection = new SecurityCollection();
								collection.addPattern("/*");
								
								securityConstraint.addCollection(collection);
								
								context.addConstraint(securityConstraint);
							}

						});
						Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
						connector.setPort(HTTP_PORT);
						connector.setRedirectPort(this.port);
						factory.addAdditionalTomcatConnectors(connector);
					}
				}

-------------------------------
Spring-boot Favicon				|
-------------------------------
	# Сͼ��,spring boot �ṩ��һ��С��ͼ��
	# �ر� favicon
		spring.mvc.favicon.enabled=false
	# �Զ��� favicon
		*ֻҪ�ѡ�favicon.ico (�ļ��������޸�),������һ��Ŀ¼������
			1,��·����Ŀ¼
			2,��·�� /META-INF/resources
			3,��·�� resources Ŀ¼
			4,��·�� static Ŀ¼
			5,��·�� public Ŀ¼