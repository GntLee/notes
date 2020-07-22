------------------------------
CompositeByteBuf			  |
------------------------------
	# ����Buffer,������Ҳ��ʵ����ByteBuf�ӿ�,�������������ڴ�й©������
		* ��ʵ���ǰѶ��Buffer��ͳ�һ��Buffer

	# CompositeByteBuf.hasArray()���Ƿ��� false,��Ϊ�����ܰ���һЩֱ�ӻ��ӵĲ�ͬ���͵� ByteBuf
	
	# ���캯��
		CompositeByteBuf(ByteBufAllocator alloc, boolean direct, int maxNumComponents)
		CompositeByteBuf(ByteBufAllocator alloc, boolean direct, int maxNumComponents, ByteBuf... buffers) 
		CompositeByteBuf(ByteBufAllocator alloc, boolean direct, int maxNumComponents, Iterable<ByteBuf> buffers) 
	
	# ����
		CompositeByteBuf addComponent(boolean increaseWriterIndex, int cIndex, ByteBuf buffer)
		CompositeByteBuf addComponent(boolean increaseWriterIndex, ByteBuf buffer)
		CompositeByteBuf addComponent(int cIndex, ByteBuf buffer)
		CompositeByteBuf addComponent(ByteBuf buffer)
		CompositeByteBuf addComponents(boolean increaseWriterIndex, ByteBuf... buffers)
		CompositeByteBuf addComponents(boolean increaseWriterIndex, Iterable<ByteBuf> buffers)
		CompositeByteBuf addComponents(int cIndex, ByteBuf... buffers)
		CompositeByteBuf addComponents(int cIndex, Iterable<ByteBuf> buffers) 
		CompositeByteBuf addComponents(ByteBuf... buffers)
		CompositeByteBuf addComponents(Iterable<ByteBuf> buffers)



	# �򵥵�ʹ��
		// �������ϻ�����
		CompositeByteBuf compBuf = Unpooled.compositeBuffer();

		// ������buffer
		ByteBuf heapBuf = Unpooled.buffer(8);
		ByteBuf directBuf = Unpooled.directBuffer(16);

		//��� ByteBuf �� CompositeByteBuf
		compBuf.addComponents(heapBuf,directBuf);

		//ɾ����һ�� ByteBuf
		compBuf.removeComponent(0);

		// ���Ե������ϻ������е�ÿһ��������
		Iterator<ByteBuf> iter = compBuf.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().toString());
		}

		//ʹ�������������
		if(!compBuf.hasArray()){
			int len = compBuf.readableBytes();
			byte[] arr = new byte[len];
			compBuf.getBytes(0, arr);
		}

