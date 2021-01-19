----------------------------
�����ճ���Ľ������		|
----------------------------
	# ���ȷ��֪����Ϣ�Ĺ̶�����,���Լ򵥵�ʹ��ByteToMessageDecoder
		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
			if (in.readableBytes() < 4) {
				// ��Ϣ������4,���ܽ���
				return; 
			}
			// ���Գɹ�������һ����Ϣ
			out.add(in.readBytes(4)); 
		}
	
	# Ҳ����ʹ���ֳ��ṩ�Ľ�����
		LineBasedFrameDecoder
			* �س����з���Ϊ��Ϣ��������TCP��������

		LengthFieldBasedFrameDecoder
			* �̶�����

		DelimiterBasedFrameDecoder
			* ��ָ���ķ��ŷָ���Ϣ
				
		FixedLengthFrameDecoder
			* �̶����ȵ���Ϣͷ
	
	# �Զ���ӳ���ͷ�ı�����
		LengthFieldPrepender

----------------------------
LengthFieldBasedFrameDecoder|
----------------------------
	# ר��Ϊ�̶���ͷ�ṩ�Ľ�����
		public LengthFieldBasedFrameDecoder(int maxFrameLength,int lengthFieldOffset, int lengthFieldLength)
		public LengthFieldBasedFrameDecoder(int maxFrameLength,int lengthFieldOffset, int lengthFieldLength,int lengthAdjustment, int initialBytesToStrip)
		public LengthFieldBasedFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,int lengthAdjustment, int initialBytesToStrip, boolean failFast)
		public LengthFieldBasedFrameDecoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,int lengthAdjustment, int initialBytesToStrip, boolean failFast)


		
		maxFrameLength
			* ���������ĳ���,���ֵ����ʵ�ʳ�������

		lengthFieldOffset
			* ��ʾ���ݳ����ֶο�ʼ��ƫ����
			* �ڼ����ֽڿ�ʼ,�Ǳ�ʾ���ݳ��ȵ�(0)
		
		lengthFieldLength
			* ���ݳ����ֶε���ռ���ֽ���
		
		lengthAdjustment
			* ��ӵ������ֶεĲ���ֵ(0)
			* lengthAdjustment + ���ݳ���ȡֵ = ���ݳ����ֶ�֮��ʣ�°����ֽ���
			* ����ĳЩЭ��,�����ֶλ���������Ϣͷ�ĳ���,������Ӧ�ó�����,������Ҫʹ��lengthAdjustment��������
				����������Ϣ(������Ϣͷ)�ĳ�������������Ϣ��ĳ���,������Ҫ����Ϊ����(���ݳ����ֶεĳ���ȡ��)

		initialBytesToStrip
			* ��ʾ����������һ���ֽڿ�ʼ,�����Ե��ֽ���(0)
			* �������øò���,�����Ե���ͷ��Ϣ,�����������ݰ���,����һ��Handler����
	
	# ����Э��
		lengthFieldOffset   = 0
		lengthFieldLength   = 2		// ͨ�õ�ǰ��2�ֽڱ�ʾ���ݳ���
		lengthAdjustment    = 0
		initialBytesToStrip = 0 

		BEFORE DECODE (14 bytes)         AFTER DECODE (14 bytes)
		+--------+----------------+      +--------+----------------+
		| Length | Actual Content |----->| Length | Actual Content |
		| 0x000C | "HELLO, WORLD" |      | 0x000C | "HELLO, WORLD" |
		+--------+----------------+      +--------+----------------+
		
		 lengthFieldOffset   = 0
		 lengthFieldLength   = 2
		 lengthAdjustment    = 0
		 initialBytesToStrip = 2	// ����ǰ��2�ֽڵİ�ͷ

		 BEFORE DECODE (14 bytes)         AFTER DECODE (12 bytes)
		 +--------+----------------+      +----------------+
		 | Length | Actual Content |----->| Actual Content |
		 | 0x000C | "HELLO, WORLD" |      | "HELLO, WORLD" |
		 +--------+----------------+      +----------------+
	
		 lengthFieldOffset   =  0
		 lengthFieldLength   =  2
		 lengthAdjustment    = -2 // ����ͷ��ʾ�ĳ���,����������ͷ���ĳ���
		 initialBytesToStrip =  0

		 BEFORE DECODE (14 bytes)         AFTER DECODE (14 bytes)
		 +--------+----------------+      +--------+----------------+
		 | Length | Actual Content |----->| Length | Actual Content |
		 | 0x000E | "HELLO, WORLD" |      | 0x000E | "HELLO, WORLD" |
		 +--------+----------------+      +--------+----------------+
	
		 lengthFieldOffset   = 2	// ��ʾ��Ϣ���ȵ�ͷ,�����ײ�
		 lengthFieldLength   = 3
		 lengthAdjustment    = 0
		 initialBytesToStrip = 0

		 BEFORE DECODE (17 bytes)                      AFTER DECODE (17 bytes)
		 +----------+----------+----------------+      +----------+----------+----------------+
		 | Header 1 |  Length  | Actual Content |----->| Header 1 |  Length  | Actual Content |
		 |  0xCAFE  | 0x00000C | "HELLO, WORLD" |      |  0xCAFE  | 0x00000C | "HELLO, WORLD" |
		 +----------+----------+----------------+      +----------+----------+----------------+
	
		 lengthFieldOffset   = 0
		 lengthFieldLength   = 3
		 lengthAdjustment    = 2	// ������Ϣ��ĳ���,��Ҫ����һ��ͷ���ĳ���,��Ϊ���滹��һ��ͷ��
		 initialBytesToStrip = 0

		 BEFORE DECODE (17 bytes)                      AFTER DECODE (17 bytes)
		 +----------+----------+----------------+      +----------+----------+----------------+
		 |  Length  | Header 1 | Actual Content |----->|  Length  | Header 1 | Actual Content |
		 | 0x00000C |  0xCAFE  | "HELLO, WORLD" |      | 0x00000C |  0xCAFE  | "HELLO, WORLD" |
		 +----------+----------+----------------+      +----------+----------+----------------+

		 lengthFieldOffset   = 1 (= the length of HDR1)			// ��2���ֽڱ�ʾ���ݳ���
		 lengthFieldLength   = 2								
		 lengthAdjustment    = 1 (= the length of HDR2)			// ����֮��,����1���ֽڵ���Ϣͷ
		 initialBytesToStrip = 3 (= the length of HDR1 + LEN)	// �Ƴ�ǰ��3���ֽڵ�����

		 BEFORE DECODE (16 bytes)                       AFTER DECODE (13 bytes)
		 +------+--------+------+----------------+      +------+----------------+
		 | HDR1 | Length | HDR2 | Actual Content |----->| HDR2 | Actual Content |
		 | 0xCA | 0x000C | 0xFE | "HELLO, WORLD" |      | 0xFE | "HELLO, WORLD" |
		 +------+--------+------+----------------+      +------+----------------+
	
		 lengthFieldOffset   =  1
		 lengthFieldLength   =  2								
		 lengthAdjustment    = -3 (= the length of HDR1 + LEN, negative)	//����ͷ��ʾ����������Ϣ��ĳ���
		 initialBytesToStrip =  3

		 BEFORE DECODE (16 bytes)                       AFTER DECODE (13 bytes)
		 +------+--------+------+----------------+      +------+----------------+
		 | HDR1 | Length | HDR2 | Actual Content |----->| HDR2 | Actual Content |
		 | 0xCA | 0x0010 | 0xFE | "HELLO, WORLD" |      | 0xFE | "HELLO, WORLD" |
		 +------+--------+------+----------------+      +------+----------------+


----------------------------
LengthFieldPrepender		|
----------------------------
	# �Զ�Ϊ���ݰ���ӳ���ͷ�ı�����

		LengthFieldPrepender(int lengthFieldLength)
		LengthFieldPrepender(int lengthFieldLength, boolean lengthIncludesLengthFieldLength)
		LengthFieldPrepender(int lengthFieldLength, int lengthAdjustment)
		LengthFieldPrepender(int lengthFieldLength, int lengthAdjustment, boolean lengthIncludesLengthFieldLength)
		LengthFieldPrepender(ByteOrder byteOrder, int lengthFieldLength,int lengthAdjustment, boolean lengthIncludesLengthFieldLength)
		
		lengthFieldLength
			* �����ֶε���ռ���ֽ���
			* ֻ����:1, 2, 3, 4, 8 
		
		lengthIncludesLengthFieldLength
			* �����Ƿ��������Ϣͷ�ĳ���
		
		lengthAdjustment
			* ��ӵ������ֶεĲ���ֵ
			* ������Ϣ�л���������ͷ
		
