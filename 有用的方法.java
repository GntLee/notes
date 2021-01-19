------------------------------------------
���ֽ�����,ת��Ϊ16�����ַ���			  |
------------------------------------------
private String bytesToHexString(byte[] src) {
	StringBuilder stringBuilder = new StringBuilder();
	if (src == null || src.length <= 0) {
		return null;
	}
	for (int i = 0; i < src.length; i++) {
		int v = src[i] & 0xFF;
		String hv = Integer.toHexString(v);
		if (hv.length() < 2) {
			stringBuilder.append(0);
		}
		stringBuilder.append(hv);
	}
	return stringBuilder.toString();
}

------------------------------------------
��һ���ֽ�,ת��Ϊ16�����ַ���			  |
------------------------------------------
private static String toHex(byte b) {  
	String result = Integer.toHexString(b & 0xFF);  
	if (result.length() == 1) {  
		result = '0' + result;  
	}  
	return result;  
}  

------------------------------------------
��16�����ַ���,ת��Ϊ�ֽ�����			  |
------------------------------------------
private byte[] hexStringToBytes(String hexString) {
	if (hexString == null || hexString.equals("")) {
		return null;
	}
	hexString = hexString.toUpperCase();
	int length = hexString.length() / 2;
	char[] hexChars = hexString.toCharArray();
	byte[] d = new byte[length];
	for (int i = 0; i < length; i++) {
		int pos = i * 2;
		d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
	}
	return d;
}
/**
 * Char -> byte
 * */
private byte charToByte(char cha) {
	return (byte) "0123456789ABCDEF".indexOf(cha);
}

------------------------------------------
int ���ֽ������ת��(�������紫��)		  |
------------------------------------------
	public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

------------------------------------------
��ȡ�ͻ���IP							  |
------------------------------------------
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	

------------------------------------------
 AES�㷨								 |
------------------------------------------
	/**
	 * AES�㷨
	 * 128λ��Կ,ECB�㷨,PKCS5Padding���ģʽ����
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] sSrc, byte[] sKey) throws Exception {  
		SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES"); 
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);  
		byte[] encrypted = cipher.doFinal(sSrc);  
		return encrypted;
	}  



------------------------------------------
MD5										  |
------------------------------------------
private static String encodeMd5(byte[] source) {
	try {
		return encodeHex(MessageDigest.getInstance("MD5").digest(source));
	} catch (NoSuchAlgorithmException e) {
		throw new IllegalStateException(e.getMessage(), e);
	}
}

private static String encodeHex(byte[] bytes) {
	StringBuffer buffer = new StringBuffer(bytes.length * 2);
	for (int i = 0; i < bytes.length; i++) {
		if (((int) bytes[i] & 0xff) < 0x10)
			buffer.append("0");
		buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
	}
	return buffer.toString();
}


------------------------------------------
���ַ���������������					  |
------------------------------------------
	public static final Collator collator = Collator.getInstance(java.util.Locale.CHINA);
	
	//���ַ���������������
	public static void sortedChinese(List<String> collection) {
		
		collection.sort(collator::compare);
		
//		collection.sort((s1,s2) -> {
//			return collator.compare(s1, s2);
//		});
		
//		collection.sort(new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return collator.compare(o1, o2);
//			}
//		});
	}
	
	
	public static void main(String[] args) throws Exception {

		List<String> list = new ArrayList<>();

		list.add("p2");
		list.add("p1");
		list.add("method");
		list.add("pn");
		
		sortedChinese(list);

		for (String i : list) {
			System.out.println(i);
		}
	}

------------------------------------------
��ȡ�쳣�Ķ�ջ��Ϣ						  |
------------------------------------------

	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}

------------------------------------------
У���û����Ƿ�Ϸ�						  |
------------------------------------------
	/**
	 * У���û����Ƿ�Ϸ�
			ֻ�������� ���� Ӣ�� �»���
			1 - 14����
				һ������ռ2������
			����Ϊ���»���
			�����Ǵ�����
	 * @param name
	 * @return
	 */
	public boolean nameValidate(String name) {
		return name.replaceAll("[\\u4e00-\\u9fa5]", "aa").matches("^(?!\\d+$)(?!_+$)\\w{1,14}$");
	}

	
	function checkUserName(name){
		return /^(?!\d+$)(?!_+$)\w{1,14}$/.test(name.replace(/[\u4e00-\u9fa5]/g,"aa"));
	}

------------------------------------------
���� InputStream ��						  |
------------------------------------------
//��ȡ��
InputStream inputStream = Files.newInputStream(Paths.get("E:\\404.jpg"));
//���ڴ涨�������
ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
byte[] buffer = new byte[1024];
int length = 0;
while((length = inputStream.read(buffer)) != -1) {
    //����Ҫ�����õ���,д�뵽�ڴ������
    byteArrayOutputStream.write(buffer,0,length);
    byteArrayOutputStream.flush();
}
inputStream.close();
//�����ڴ����������N����ڴ�������,�Ӷ�ʵ����������
ByteArrayInputStream byteArrayInputStream1 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
System.out.println(byteArrayInputStream1.available());        //105335
System.out.println(byteArrayInputStream2.available());        //105335