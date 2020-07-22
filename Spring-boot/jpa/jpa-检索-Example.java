------------------
Example
------------------
	# Example api�����
		Example				��Probe��ExampleMatcher��ɣ����ڲ�ѯ��
		Probe				���ж�Ӧ�ֶε�ʵ������
		ExampleMatcher		ExampleMatcherЯ���й����ƥ���ض��ֶε���ϸ��Ϣ,�൱��ƥ������

	# ����
		* ���Բ�֧��Ƕ�׻��߷���Լ��,���������Ĳ�ѯ firstname = ? 0 or (firstname = ? 1 and lastname = ? 2)
		* ���ƥ��ֻ֧���ַ������ͣ���������ֻ֧�־�ȷƥ��

		 // ƥ�����з� null �ֶ�
        ExampleMatcher.matching();
        // ͬ��
        ExampleMatcher.matchingAll();
        // ƥ���κη� null �ֶ�
        ExampleMatcher.matchingAny();

		// matcher�������Ƕ������������(�շ�),������DB����
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                //ģ����ѯƥ�俪ͷ����{username}%
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())
                //ģ����ѯƥ���β����%{username}
                .withMatcher("userName", ExampleMatcher.GenericPropertyMatchers.endsWith())
                //ȫ��ģ����ѯ����%{address}%
                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())

                //�����ֶΣ�������password��ʲôֵ���������ѯ����
                .withIgnorePaths("password");

        Example<UserDTO> userDTOExample = Example.of(userDTO,exampleMatcher);