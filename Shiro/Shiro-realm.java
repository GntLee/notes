----------------------------
realm						|
----------------------------
	# ����ӿ�
		public interface Realm {
			//����Ψһ�� realm ����
			String getName();
			//��ǰrealm�Ƿ�֧�ִ�token
			boolean supports(AuthenticationToken token);
			//����token,��ȡ��֤��Ϣ
			AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException;
		}

	
	# ini ����һ�����߶��realm
		[main]
		myRealm1=practice.shiro.realm.MyRealm1
		myRealm2=practice.shiro.realm.MyRealm2
		securityManager.realms=$myRealm1,$myRealm2

		* securityManager �ᰴ�� realms ָ����˳����������֤
		* ���ɾ��"securityManager.realms=$myRealm1,$myRealm2",��ô securityManager �ᰴ�� realm ������˳�����ʹ��(�Զ�����)
		* ����һ���ֶ���������:securityManager.realms ����,��ô�Զ����ֻ�ʧЧ,���ϸ��� securityManager.realms ������˳�������������֤
	
	
	# Shiro�ṩ��Ĭ��Realm
													Realm
														|
													CachingRealm
														|
													CachingRealm
														|
													AuthenticatingRealm
														|
													AuthorizingRealm
														|
								|---------------|--------------|---------------|
						SimpleAccountRealm	JDBCRealm	JndiLdapRealm	AbstractLdapRealm
							|													|
				TextConfigurationRealm									ActiveDirectoryRealm
					|-------------|
			PropertiesRealm		IniRealm

			* һ��̳� AuthorizingRealm(��Ȩ)����

			* IniRealm
				* [users]����ָ���û���/���뼰���ɫ
				* [roles]����ָ����ɫ��Ȩ����Ϣ
			
			* PropertiesRealm
				* user.username=password,role1,role2 ָ���û���/���뼰���ɫ
				* role.role1=permission1,permission2 ָ����ɫ��Ȩ����Ϣ
			
			* JdbcRealm
				* ͨ��jdbc��������֤����Ȩ
			

----------------------------
JdbcRealm ��ʹ��			|
----------------------------
	