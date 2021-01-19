------------------
��֤			  |
------------------
	# �������
		UsernamePasswordAuthenticationFilter


		UsernamePasswordAuthenticationToken

		AuthenticationManager

		AuthenticationProvider

		GrantedAuthority
			* ��ʾ��ɫ��Ϣ�ӿ�
			* ʵ��
				JaasGrantedAuthority
				SimpleGrantedAuthority
				SwitchUserGrantedAuthority

		UserDetailsService
			* �����û���, �����û��Ľӿ�
			* ֻ��һ������
				UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
				
				* �÷���ֻҪ����null, �ͱ�ʾ��֤ʧ��
			
			
		UserDetails
			* ��ʾ�û�������ӿ�
			* ���󷽷�
				Collection<? extends GrantedAuthority> getAuthorities();
				String getPassword();
				String getUsername();
				boolean isAccountNonExpired();
				boolean isAccountNonLocked();
				boolean isCredentialsNonExpired();
				boolean isEnabled();
			
			* ��ʵ����
				User
					private String password;							// ����
					private final String username;						// �û���
					private final Set<GrantedAuthority> authorities;	// ��Ȩ��Ϣ
					private final boolean accountNonExpired;			// �˻�δ����
					private final boolean accountNonLocked;				// �˻�δ����
					private final boolean credentialsNonExpired;		// ����δ����
					private final boolean enabled;						// �˻�δ����
		
		PasswordEncoder
			* ����ı���ӿ�
			* ʵ����
				BCryptPasswordEncoder
				DelegatingPasswordEncoder
		

		RememberMeServices
			* ��ס�ҵĽӿ�
				Authentication autoLogin(HttpServletRequest request, HttpServletResponse response);
				void loginFail(HttpServletRequest request, HttpServletResponse response);
				void loginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication);
			
			* ʵ����
				NullRememberMeServices
				PersistentTokenBasedRememberMeServices
				TokenBasedRememberMeServices
			
