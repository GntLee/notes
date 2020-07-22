----------------------------
session						|
----------------------------
	# �ο�
		https://docs.spring.io/spring-session/docs/current/reference/html5/
	
	# ����
		<dependency>
			<groupId>org.springframework.session</groupId>
			<artifactId>spring-session-data-redis</artifactId>
		</dependency>
	
	# ע������ @EnableRedisHttpSession (���Ǳ����)
		int maxInactiveIntervalInSeconds() default MapSession.DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS;
			* session�������ʱ��, Ĭ������

		String redisNamespace() default RedisOperationsSessionRepository.DEFAULT_NAMESPACE;
			* redis�Ĵ洢�ռ�key����

		RedisFlushMode redisFlushMode() default RedisFlushMode.ON_SAVE;
			* ˢ��ģʽ, ö��
				ON_SAVE
					* ֻ�е� SessionRepository.save(Session)����������ʱ, �ŻὫsession�е�����ͬ����redis��.
					* ��web Ӧ����, �����������Ӧ��, �ſ�ʼͬ��. Ҳ����˵��ִ��response ֮ǰsession���ݶ��ǻ����ڱ��ص�.

				IMMEDIATE
					* ʵʱͬ��session ���ݵ�redis. 
					* ��ִ��SessionRepository.createSession()ʱ, �Ὣsession����ͬ����redis��; 
					* ����session��attribute����set/remove �Ȳ���ʱ, Ҳ��ͬ��session�е����ݵ�redis��

		String cleanupCron() default RedisHttpSessionConfiguration.DEFAULT_CLEANUP_CRON;
			* ִ��session�����cron���ʽ
			* Ĭ��: 0 * * * * * (һ����ִ��һ��)

	
	# Cookie��Ϣ���Զ���
		* ���ǿ���ͨ��: server.servlet.session.cookie ������Cookie���������Ե���Ϣ
		* Ҳ����ͨ������, �Զ�����: CookieSerializer

		@Bean
		public CookieSerializer cookieSerializer() {
			DefaultCookieSerializer serializer = new DefaultCookieSerializer();
			serializer.setCookieName("JSESSIONID"); 
			serializer.setCookiePath("/"); 
			serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$"); 
			return serializer;
		}
			


	# �Զ���Session�Ľ�����ʽ
		* Session�Ľ���������һ���ӿ�: HttpSessionIdResolver

			CookieHttpSessionIdResolver	ʹ��Cookie(Ĭ��)
			HeaderHttpSessionIdResolver	ʹ��Header

			List<String> resolveSessionIds(HttpServletRequest request)
				* ���������ȡ���Ựid

			void setSessionId(HttpServletRequest request, HttpServletResponse response,String sessionId)
				* ���ûỰid

			void expireSession(HttpServletRequest request, HttpServletResponse response);
				* ���ڻỰid

		* ͨ���Զ�����������ʵ���Զ���Ľ���
			@Bean
			public HttpSessionIdResolver httpSessionIdResolver() {
				return HeaderHttpSessionIdResolver.xAuthToken();  // ʹ�� X-Auth-Token ����Cookie
			}
		
		* HeaderHttpSessionIdResolver ��N�������������ز�ͬ��ʵ��

			HeaderHttpSessionIdResolver xAuthToken()
				* ʹ��: X-Auth-Token ͷ��Ϊsession��id

			HeaderHttpSessionIdResolver authenticationInfo()
				* ʹ��: Authentication-Info ͷ��Ϊsession��id

		* Ҳ�����Զ�������ͷ, ͨ�����췽������
			HeaderHttpSessionIdResolver(String headerName)

			
	# Redis��key�ṹ
		spring:session:expirations:1570672200000
		spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:1
		spring:session:sessions:d82bf2bb-deb3-474c-89bb-96c2bfa646c4
		spring:session:sessions:expires:94b2ce1f-053e-4c20-a8b7-f4d69102a114


			
----------------------------
����						|
----------------------------
# ������: RedisSessionProperties

spring:
  session:
    timeout: 1800
		* �Ự�ĳ�ʱʱ��, ��λ����,Ĭ��: 1800
		* ���������, Ĭ��ʹ��: server.servlet.session.timeout
    store-type: REDIS
		* ָ��session�Ĵ洢��ʽ, ö��
			REDIS
			MONGODB
			JDBC
			HAZELCAST
			NONE
    redis:
      namespace: "spring:session"
      flush-mode: IMMEDIATE
      cleanup-cron: "0 * * * * *"

	
	* ���ʹ����ע��: @EnableRedisHttpSession, �������ʧЧ, Ĭ��ʹ��ע�����������
	* ʹ������, ����Ҫ�ֶ����ע��


-------------------------------------
SessionRepository<S extends Session> |
-------------------------------------
	# Session�Ĺ���ӿ�
		S createSession();
			* ����һ��
		void save(S session);
			* �洢/����
		S findById(String id);
			* ����id����
		void deleteById(String id);
			* ����idɾ��
	
	# ����ʵ��
		SessionRepository
			|-MapSessionRepository
			|-FindByIndexNameSessionRepository
				|-RedisOperationsSessionRepository
				|-RedissonSessionRepository

--------------------------------
FindByIndexNameSessionRepository|
--------------------------------
	# ͨ�׵�˵, ���ǿ��Ը����û���ȡ������Session
	# �û���¼��Ҫ����һ�����ݵ�Session���洢������Ϣ
		String username = "username";
		this.session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);

		* һ�������ID֮������ݹ���, ��һ����name
	
	# �����û���Ϣ�����û�
		@Autowired
		FindByIndexNameSessionRepository<? extends Session> sessions;

		@RequestMapping("/")
		public String index(Principal principal, Model model) {
			Collection<? extends Session> usersSessions = this.sessions.findByPrincipalName(principal.getName()).values();
			model.addAttribute("sessions", usersSessions);
			return "index";
		}
	
	# FindByIndexNameSessionRepository �ķ���������

		String PRINCIPAL_NAME_INDEX_NAME = FindByIndexNameSessionRepository.class.getName().concat(".PRINCIPAL_NAME_INDEX_NAME");

		Map<String, S> findByIndexNameAndIndexValue(String indexName, String indexValue);
			* ����ָ����key/value����session

		default Map<String, S> findByPrincipalName(String principalName) {
			return findByIndexNameAndIndexValue(PRINCIPAL_NAME_INDEX_NAME, principalName);
		}
			
	
	# ԭ��
		* FindByIndexNameSessionRepository ���������ṩ��һ��api, ���и���key��value������session
			findByIndexNameAndIndexValue(key, value)
		
		* ���Խ������������, ����ʵ���û��ͻỰ�İ�

	

----------------------------
�¼�����						|
----------------------------
	# Session�����spring�¼�
		SessionCreatedEvent				����
		SessionDestroyedEvent
			|-SessionExpiredEvent		����
			|-SessionDeletedEvent		ɾ��(�û����� invalidate())
		
		* ���ں�ɾ���¼�, ������REDIS��֪ͨ�¼�
			notify-keyspace-events "Egx"
		
		* ���ʹ����: @EnableRedisHttpSession, SessionMessageListener ����Զ���ɹ�������ñ�Ҫ��Redis Keyspace�¼�
		* �ڰ�ȫ��Redis������, һ������config���� (����ζ��Spring Session�޷�Ϊ����Redis Keyspace�¼�)
		* Ҫ�����Զ�����, �������
			@Bean
			public static ConfigureRedisAction configureRedisAction() {
				return ConfigureRedisAction.NO_OP;
			}
	
	# ʹ��Spring��Event����
		@Component
		public class SpringSessionListener {
			
			private static final Logger LOGGER = LoggerFactory.getLogger(SpringSessionListener.class);
			
			@EventListener(SessionCreatedEvent.class)
			public void sessionCreatedEvent(SessionCreatedEvent sessionCreatedEvent) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("�������µ�session:{}", sessionCreatedEvent.getSessionId());
				}
			}
			
			@EventListener(SessionExpiredEvent.class)
			public void sessionExpiredEvent(SessionExpiredEvent sessionCreatedEvent) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("session����:{}", sessionCreatedEvent.getSessionId());
				}
			}
			
			@EventListener(SessionDeletedEvent.class)
			public void sessionDeletedEvent(SessionDeletedEvent sessionCreatedEvent) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("ɾ����session:{}", sessionCreatedEvent.getSessionId());
				}
			}
		}
	
	# Ҳ����ʹ�� Servlet�ļ�����
		* ��Ҫ���Bean��ioc, ͨ�����bean�Ĺ��캯��, ��Ӷ�� HttpSessionListener ʵ��
			SessionEventHttpSessionListenerAdapter
			SessionEventHttpSessionListenerAdapter(List<HttpSessionListener> listeners)
		
		* �������Bean��ʵ�Ѿ��������Զ������, ��û�ٴ���ӻᵼ���쳣
		* ���߾ȹ�, ��IOC�����ȡ�����bean, ͨ������, ��˽������ listeners ��Ӽ�����
			@Configuration
			public class SpringSessionConfiguration {
				
				private static final Logger LOGGER = LoggerFactory.getLogger(SpringSessionConfiguration.class);
				
				@Autowired SessionEventHttpSessionListenerAdapter sessionEventHttpSessionListenerAdapter;
				
				@PostConstruct
				public void addHttpSessionListener() {
					try {
						Field field = SessionEventHttpSessionListenerAdapter.class.getDeclaredField("listeners");
						field.setAccessible(Boolean.TRUE);
						
						@SuppressWarnings("unchecked")
						List<HttpSessionListener> listeners = (List<HttpSessionListener>) field.get(sessionEventHttpSessionListenerAdapter);
						listeners.add(new SessionListener());
						
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("���SESSION������");
						}
						
					} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				
				
			//	@Bean
			//	public SessionEventHttpSessionListenerAdapter sessionEventHttpSessionListenerAdapter() {
			//		SessionEventHttpSessionListenerAdapter sessionEventHttpSessionListenerAdapter = new SessionEventHttpSessionListenerAdapter(Arrays.asList(new SessionListener()));
			//		return sessionEventHttpSessionListenerAdapter;
			//	}
			}
	
	# BUG
		* ʹ�� redisson ��Ϊredis�Ŀͻ���(redisson-spring-boot-starter), ���ֲ��ܼ�����SESSION�Ĺ����¼�, �汾��: 3.11.4

---------------------------
��̬������cookie��ʵ��		|
----------------------------

# �Զ���cookie�����л�ʵ��

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.util.StringUtils;

// @Component
public class DynamicCookieMaxAgeCookieSerializer extends DefaultCookieSerializer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicCookieMaxAgeCookieSerializer.class);
	
	public static final String COOKIE_MAX_AGE = "cookie.max-age";
	
	@Value("${server.servlet.session.cookie.max-age}")
	private Integer cookieMaxAge;
	
	@Override
	public void writeCookieValue(CookieValue cookieValue) {
		
		// if ("".equals(this.cookieValue) this.cookieMaxAge = 0;
		if (!StringUtils.isEmpty(cookieValue.getCookieValue())) {
			
			HttpServletRequest request = cookieValue.getRequest();
			
			// ��request���ȡ��cookie��maxAge����
			Object attribute = request.getAttribute(COOKIE_MAX_AGE);
			if (attribute != null) {
				cookieValue.setCookieMaxAge((int) attribute);
			} else {
				cookieValue.setCookieMaxAge(this.cookieMaxAge);
			}
			
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("��̬����cooke.max-age={}", cookieValue.getCookieMaxAge());
			}
		}
		
		super.writeCookieValue(cookieValue);
	}
}


# ���õ�IOC
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;

import com.video.manager.spring.session.DynamicCookieMaxAgeCookieSerializer;

@Configuration
public class SpringSessionConfiguration {
	
	@Value("${server.servlet.session.cookie.name}")
	private String cookieName;
	
	@Value("${server.servlet.session.cookie.secure}")
	private Boolean cookieSecure;
	
//	@Value("${server.servlet.session.cookie.max-age}")
//	private Integer cookieMaxAge;
	
	@Value("${server.servlet.session.cookie.http-only}")
	private Boolean cookieHttpOnly;

	@Value("${server.servlet.session.cookie.same-site}")
	private String cookieSameSite;
	
	@Bean
	public CookieSerializer cookieSerializer() {
		DynamicCookieMaxAgeCookieSerializer serializer = new DynamicCookieMaxAgeCookieSerializer();
		serializer.setCookieName(this.cookieName);
		// serializer.setCookieMaxAge(this.cookieMaxAge);
		serializer.setSameSite(this.cookieSameSite);
		serializer.setUseHttpOnlyCookie(this.cookieHttpOnly);
		serializer.setUseSecureCookie(this.cookieSecure);
		return serializer;
	}
}



# ��¼���߼�

// ʧЧ�ɻỰ & �����»Ự
httpSession.invalidate();
httpSession = request.getSession();

if (!remember) {
	// �ǡ���ס�ҡ��������£�cookie������������Ϊ-1 & session����30����
	request.setAttribute(DynamicCookieMaxAgeCookieSerializer.COOKIE_MAX_AGE, -1);
	httpSession.setMaxInactiveInterval(60 * 30);
}

