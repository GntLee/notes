-----------------------
Basic
-----------------------
	# ����
		* �ͻ��˻���û��������룬�����м���ð�ţ�:�����Ӻ��پ��� Base64 ���봦��
		* ���һ��Header
			Authorization	Basic YWRtaW46YWRtaW4=
		
		* BASIC ��֤��Ҫ���HTTPS����֤��Ϣ����İ�ȫ
		
	# ����
		// ��ȡHeader��Authorization
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		String value = HttpHeaders.encodeBasicAuth("admin", "admin", StandardCharsets.UTF_8);
		value = "Basic " + value;

		if (!value.equals(token)) {
			//����Header�� WWW-Authenticate, realm �������Ϊ��ʾ��
			response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Authorization Required\"");
			// 403
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		// TODO ��֤�ɹ�

-----------------------
Digest
-----------------------
	# ����
		* �ֲ�Basic��ȱ��
	
