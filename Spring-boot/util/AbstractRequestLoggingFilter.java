---------------------------------
AbstractRequestLoggingFilter	 |
---------------------------------
	# ���������, ��Ӧ��־������
		void setIncludeQueryString(boolean includeQueryString)
		void setIncludeClientInfo(boolean includeClientInfo)
		void setIncludeHeaders(boolean includeHeaders)
		void setIncludePayload(boolean includePayload)
		void setMaxPayloadLength(int maxPayloadLength) 

		void setBeforeMessagePrefix(String beforeMessagePrefix)
		void setBeforeMessageSuffix(String beforeMessageSuffix)

		void setAfterMessagePrefix(String afterMessagePrefix)
		void setAfterMessageSuffix(String afterMessageSuffix)


		protected void setAfterMessageSuffix(String afterMessageSuffix)

		protected boolean shouldNotFilterAsyncDispatch()
		protected boolean shouldLog(HttpServletRequest request)
			* �жϵ�ǰ�������Ƿ�Ҫ��ӡ��־��Ϣ

