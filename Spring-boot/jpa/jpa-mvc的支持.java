---------------------------
mvc��֧��
---------------------------
	# ͨ��ע�⿪��
		@EnableSpringDataWebSupport
	
	# ������, spring�������Զ����ü������
		DomainClassConverter
		HandlerMethodArgumentResolver
	

------------------------------
HandlerMethodArgumentResolver
------------------------------
	# �����Զ���������еķ�ҳ������Ϣ
	
	# ������ӿ��ж��� Pageable �β�
		@GetMapping("/user")
		@ResponseBody
		public Object user (Pageable pageable){}

		* �������
			page		ҳ��, Ĭ��0
			size		ÿҳ��ʾ����, Ĭ��20
			sort		����
				* sort=��������,�������
					http://localhost/user?sort=id,asc&sort=name,desc
				
				* Ҳ���Խ�����������, Ĭ���������Ϊ: asc
					http://localhost/user?sort=id,name
					http://localhost/user?sort=id&sort=name
				
	# ͨ�� @PageableDefault ע���޸�Ĭ��ֵ
		@PageableDefault
			|-int value() default 10;
			|-int size() default 10;
			|-int page() default 0;
			|-String[] sort() default {};
			|-Direction direction() default Direction.ASC;
	
	
		* ��ӵ���ҳ������
			public Object user (@PageableDefault(page = 0, size = 10) Pageable pageable)
	
