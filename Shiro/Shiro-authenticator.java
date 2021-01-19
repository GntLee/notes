--------------------------------
authenticator					|
--------------------------------
	# Authenticator ��ְ������֤�û��ʺ�,�� Shiro API �������֤���ĵ���ڵ�
		public AuthenticationInfo authenticate(AuthenticationToken authenticationToken)throws AuthenticationException;

		* ��֤�ɹ�,������ AuthenticationInfo ��֤��Ϣ,��Ϣ�а�������ݼ�ƾ֤
		* �����֤ʧ�ܽ��׳���Ӧ�� AuthenticationException ʵ��
	
	# SecurityManager �ӿڼ̳��� Authenticator

	# Authenticator ����һ��ʵ��:ModularRealmAuthenticator 
		* ����ί�ж�� Realm ������֤,��֤����ͨ�� AuthenticationStrategy �ӿ�ָ��
		* Ĭ���ṩ AuthenticationStrategy ��ʵ��:
			FirstSuccessfulStrategy
				* ֻҪ��һ�� Realm ��֤�ɹ�����
				* ֻ���ص�һ�� Realm �����֤�ɹ�����֤��Ϣ,�����ĺ���

			AtLeastOneSuccessfulStrategy
				* ֻҪ��һ�� Realm ��֤�ɹ�����('Ĭ��')
				* �������� Realm �����֤�ɹ�����֤��Ϣ

			AllSuccessfulStrategy
				* ���� Realm ��֤�ɹ�����ɹ�
				* ֻҪ��һ����֤ʧ��,�׳��쳣
				* �������� Realm �����֤�ɹ���
		
			

	# ini���� Authenticator
		authenticator=org.apache.shiro.authc.pam.ModularRealmAuthenticator					//ʵ���� ModularRealmAuthenticator
		securityManager.authenticator=$authenticator
			* ָ�� securityManager �� authenticator ʵ��

		allSuccessfulStrategy=org.apache.shiro.authc.pam.AllSuccessfulStrategy				//ʵ���� AllSuccessfulStrategy
		securityManager.authenticator.authenticationStrategy=$allSuccessfulStrategy
			* ���� securityManager.authenticator �� authenticationStrategy
		
		myRealm1=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm1
		myRealm2=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm2
		myRealm3=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm3
		securityManager.realms=$myRealm1,$myRealm3
			* ����n��realm
	
	# ����
		//1����ȡ SecurityManager �������˴�ʹ�� Ini �����ļ���ʼ�� SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		//2���õ� SecurityManager ʵ�� ���󶨸� SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//3���õ� Subject �������û���/���������֤ Token�����û����/ƾ֤��
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("Kevin", "123");
		subject.login(token);
		
		//4���õ�һ����ݼ��ϣ�������� Realm ��֤�ɹ��������Ϣ��У��ģʽ����	securityManager.authenticator �� authenticationStrategy ������
		//У��ʧ��,�׳��쳣
		PrincipalCollection principalCollection = subject.getPrincipals();
		
		System.out.println(principalCollection);
	
	# �Զ���:AuthenticationStrategy
		public interface AuthenticationStrategy {
			//������ Realm ��֤֮ǰ����
			AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException;
			//��ÿ�� Realm ֮ǰ����
			AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException;
			//��ÿ�� Realm ֮�����
			AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t)throws AuthenticationException;
			//������ Realm ֮�����
			AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException;
		}
		
		* ÿ�� AuthenticationStrategy ʵ��������״̬��,����ÿ�ζ�ͨ���ӿڽ���Ӧ����֤��
		* Ϣ������һ������,ͨ�����Ͻӿڿ��Խ�����ϲ�/���ص�һ����֤�ɹ�����֤��Ϣ
	
