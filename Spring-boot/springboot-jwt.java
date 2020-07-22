---------------------
jwt
---------------------
	# Json Web Token
		https://jwt.io/
		https://github.com/jwtk/jjwt
	
	# JWT������������
		Header ͷ��(������������Ƶ�Ԫ����, ���Ұ���ǩ����/������㷨������)
			{ 
				"alg": "HS256",
				"typ": "JWT"
			} 

			* ͷ������������Ϣ, token���ͺͲ��õļ����㷨
			* ���ܵ��㷨ͨ��ʹ��: HMAC, SHA256, MD5
			* ���м��������õ�ͷ
				cty
				kid
			
			* ���������˽ӿ�:PublicClaims ��

		Payload ����(������, ��Ϣ����)

			{
			  "sub": "1234567890",			// ��׼����
			  "name": "John Doe",			// ˽�е�����
			  "admin": true					// ˽������
			}
			
			* Payload ����Ҳ��һ�� JSON ����, �������ʵ����Ҫ���ݵ�����
			* JWT �涨��7���ٷ��ֶ�, ���������˽ӿ�:PublicClaims ��
					iss (issuer)ǩ����
					exp (expiration time)����ʱ��
					sub (subject)����
					aud (audience)����
					nbf (Not Before)��Чʱ��
					iat (Issued At)ǩ��ʱ��
					jti (JWT ID)���

			* ��������������ֶ���˽���ֶ�
			* JWT Ĭ���ǲ����ܵ�, �κ��˶����Զ���, ���Բ�Ҫ��������Ϣ�����������

			
		Signature ǩ��/ǩ֤
			* ���������Ҫbase64���ܺ��header��base64���ܺ��payloadʹ��,
			* ������ɵ��ַ���, Ȼ��ͨ��header�������ļ��ܷ�ʽ���м���secret��ϼ���, Ȼ��͹�����jwt�ĵ�������
			* ��Կsecret�Ǳ����ڷ���˵�, ����˻���������Կ��������token�ͽ�����֤, ������Ҫ������
	
	# Header
		Authorization: Bearer <token>

---------------------
jwt - springboot
---------------------
	# Maven
		<!-- https://mvnrepository.com/artifact/com.auth0/java-jwt -->
		<dependency>
			<groupId>com.auth0</groupId>
			<artifactId>java-jwt</artifactId>
			<version>3.10.1</version>
		</dependency>
	
	# ǩ��Token
		// ���һ����ݺ��ʱ���
		long expiresTimestamp = LocalDateTime.now().plusWeeks(1).toEpochSecond(ZoneOffset.UTC);
		
		// ʹ��˽Կ���������㷨
		Algorithm  algorithm = Algorithm.HMAC256("HellWorld");
		
		String token = JWT.create()
			.withHeader(Map.ofEntries(Map.entry("alg", "HS256"), Map.entry("typ", "JWT")))
			
			.withClaim("name", "KevinBlandy")				// ���һ�����߶���Զ�����Ϣ
			.withClaim("id", 1000L)
			
			.withIssuer("springboot��������")				// ǩ����
			.withExpiresAt(new Date(expiresTimestamp))		// ����ʱ��
			.withSubject("") 								// ����
			.withAudience("1", "2", "n") 					// ����
			.withNotBefore(new Date()) 						// ��Чʱ��
			.withIssuedAt(new Date()) 						// ǩ��ʱ��
			.withJWTId("123456") 							// ���
			.sign(algorithm);
	
	# Token У��
		
		// DecodedJWT ������ header/body ��һϵ�е���Ϣ
		DecodedJWT decodedJWT = JWT.require(algorithm)
						.build().verify(token);
		
		// ��׼��Ϣ
		String issuser = decodedJWT.getIssuer();
		Date expiresAt = decodedJWT.getExpiresAt();
		String subject = decodedJWT.getSubject();
		List<String> audiences = decodedJWT.getAudience();
		Date notBefore = decodedJWT.getNotBefore();
		Date issuedAt = decodedJWT.getIssuedAt();
		String id = decodedJWT.getId();
		
		// ˽����Ϣ
		String name = decodedJWT.getClaim("name").asString();
		long id = decodedJWT.getClaim("id").asLong();
		
		// ���е�˽����Ϣ
		Map<String, Claim> claims = decodedJWT.getClaims();
	

		// ��У��ǩ���������, ����token
		DecodedJWT decodedJWT = JWT.decode(String token)

	#  ���token�Ѿ�����, ������У��ʧ��, ���׳��쳣
			JWTVerificationException
				|-AlgorithmMismatchException
				|-InvalidClaimException
				|-JWTCreationException
				|-JWTDecodeException
				|-SignatureGenerationException
				|-SignatureVerificationException
				|-TokenExpiredException

