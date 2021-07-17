----------------------------
BCryptPasswordEncoder
----------------------------
	# �������/У�鹤����
		public class BCryptPasswordEncoder implements PasswordEncoder

	# ����
		public BCryptPasswordEncoder()
		public BCryptPasswordEncoder(int strength)
		public BCryptPasswordEncoder(BCryptVersion version)
		public BCryptPasswordEncoder(BCryptVersion version, SecureRandom random)
		public BCryptPasswordEncoder(int strength, SecureRandom random)
		public BCryptPasswordEncoder(BCryptVersion version, int strength)
		public BCryptPasswordEncoder(BCryptVersion version, int strength, SecureRandom random) 

		public String encode(CharSequence rawPassword)
		public boolean matches(CharSequence rawPassword, String encodedPassword)
		public boolean upgradeEncoding(String encodedPassword)
			* �������������Ӧ���ٴα�������߰�ȫ�ԣ��򷵻�true�����򷵻�false��Ĭ��ʵ�����Ƿ���false��
			* �������������Ӧ�����±�������߰�ȫ�ԣ��򷵻�true�����򷵻�false��
			encodedPassword 
				* Ҫ���ı�������
			
			
		* BCryptVersion
			$2A("$2a"), ö��
			$2Y("$2y"),
			$2B("$2b");

		
	# ʹ��
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        // ��ԭʼ������ܣ���ȡ��hash���
        String chipper = bCryptPasswordEncoder.encode("123456");
        System.out.println(chipper);  // $2a$10$NSwn3QgNPTHk/33Wl6kye.4LMGdbDnc1YJI48O/a6D5NmLBzewLzO

        // ʹ��ԭʼ�����hash������жԱȣ��ж��Ƿ�OK
        boolean match = bCryptPasswordEncoder.matches("123456", chipper);
        System.out.println(match); // true
	
	# ԭ��
		* �ⶫ���и��ص���ǣ���ͬһ���������N�� encode ���ص�hash����һ��
		* ���ǶԲ�ͬ��hash����У�飬����У��ɹ�

		* ԭ����ǣ�hash������Ѿ�������salt��Ϣ
	
