---------------------------------------
RequestContextHolder
---------------------------------------
	# �����˵�ǰ�� RequestContext, ��Contenxt��request/reponse
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	
	# ��̬����
		static RequestAttributes currentRequestAttributes()
		static RequestAttributes getRequestAttributes() 

		static void resetRequestAttributes()

		void setRequestAttributes(@Nullable RequestAttributes attributes)
		static void setRequestAttributes(@Nullable RequestAttributes attributes, boolean inheritable)