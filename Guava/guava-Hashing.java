--------------------------------
Hashing							|
--------------------------------
	# Guava�ṩ��hash�㷨���(https://docs.google.com/spreadsheets/d/1_q2EVcxA2HjcrlVMbaqXwMj31h9M5-Bqj_m8vITOwwk/edit#gid=0)
		+----------------------------------------------------------------------------------------------
		| Hash �㷨							����(Bit)	ʱ��	�Ƽ�	��ע 
		+----------------------------------------------------------------------------------------------
		| Hashing#adler32					32			1.00	No		����У��ͣ������ٶȵĿɿ��ԣ�
		+----------------------------------------------------------------------------------------------
		| Hashing#crc32						32			1.52	No		
		+----------------------------------------------------------------------------------------------
		| Hashing#goodFastHash(32)			32			2.73	Yes		VM����֮�䲻�ȶ�
		+----------------------------------------------------------------------------------------------
		| Hashing#murmur3_32				32			2.75	Yes		
		+----------------------------------------------------------------------------------------------
		| Hashing#goodFastHash(64)			64			5.25	Yes		VM����֮�䲻�ȶ�
		+----------------------------------------------------------------------------------------------
		| Hashing#murmur3_128				128			5.26	Yes		
		+----------------------------------------------------------------------------------------------
		| Hashing#goodFastHash(128)			128			5.41	Yes		VM����֮�䲻�ȶ�
		+----------------------------------------------------------------------------------------------
		| Hashing#md5						128			6.03	No		�����м��ܰ�ȫ�Ի򿹳�ͻ��
		+----------------------------------------------------------------------------------------------
		| Hashing#sha1						160			9.78	No		�����ܰ�ȫ
		+----------------------------------------------------------------------------------------------
		| Hashing#goodFastHash(256)			256			10.41	Yes		VM����֮�䲻�ȶ�
		+----------------------------------------------------------------------------------------------
		| Hashing#sha256					256			17.58	No		�����Ǽ��ܰ�ȫ��
		+----------------------------------------------------------------------------------------------
		| Hashing#sha512					512			43.78	Yes		
		+----------------------------------------------------------------------------------------------
		| Hashing#goodFastHash(int bits)	N			n/a		Yes		���ȶ�,�û�������NλHashCodes
		+----------------------------------------------------------------------------------------------
		


	# ���ṩ��N��ľ�̬����,���ڴ�����ͬ��Hash�㷨(�Լ���ͬ�汾,λ��)ʵ��
		
		HashFunction crc32()

		HashFunction crc32c()

		HashFunction goodFastHash(int minimumBits)

		HashFunction farmHashFingerprint64()

		HashFunction hmacMd5(byte[] key)

		
		HashFunction murmur3_128()
		HashFunction murmur3_128(int seed)
		HashFunction murmur3_32()
		HashFunction murmur3_32(int seed)

		HashFunction sha256()
		HashFunction sha384()
		HashFunction sipHash24()

--------------------------------
HashFunction							|
--------------------------------
	# ����һ���ӿ�,�ɲ�ͬ��hash�㷨ʵ��
	# �ӿ� ����
		Hasher newHasher();

		Hasher newHasher(int expectedInputSize);

		HashCode hashInt(int input);

		HashCode hashLong(long input);

		HashCode hashBytes(byte[] input);
			* ���ֽ����ݽ���hash����

		HashCode hashBytes(byte[] input, int off, int len);

		HashCode hashBytes(ByteBuffer input);

		HashCode hashUnencodedChars(CharSequence input);

		HashCode hashString(CharSequence input, Charset charset);
			* ���ַ�������hash����

		<T> HashCode hashObject(T instance, Funnel<? super T> funnel);

		int bits();
--------------------------------
Hashing							|
--------------------------------
	# ��hash�������ĳ���ӿ�
	# ��̬����
		HashCode fromInt(int hash)
	
	# ʵ�����շ�
		int asInt();
			* ����hash��intֵ
		
		long asLong();
			* ����hash��longֵ

		long padToLong();
		byte[] asBytes();
		int bits();

--------------------------------
����Hash�㷨��ʵ��				|
--------------------------------
	# murmur3 128λhash�㷨
		HashFunction function = Hashing.murmur3_128();
		HashCode hascode = function.hashString("murmur3_128", StandardCharsets.UTF_8);
		System.out.println(hascode.asInt());