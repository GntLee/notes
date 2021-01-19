--------------------
�㿽��				|
--------------------
	# NIO ��"zero-copy(�㿽��)"����,�����ƶ�һ���ļ������ݴ��ļ�ϵͳ�������ջ�ĸ��Ʋ���
	# ���
		FileRegion
			DefaultFileRegion

	# ֱ�Ӵ���һ���ļ�������,û��ִ�е�����Ӧ�ó���Ĵ���
		// ��ȡ FileInputStream
		FileInputStream in = new FileInputStream(file); 

		// ����һ���µ� DefaultFileRegion �����ļ�����������
		FileRegion region = new DefaultFileRegion(in.getChannel(), 0, file.length()); 

		// ���� DefaultFileRegion ����ע��һ�� ChannelFutureListener
		channel.writeAndFlush(region).addListener(new ChannelFutureListener() { 
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				// ������ʧ��
				if (!future.isSuccess()) {
					Throwable cause = future.cause(); 
				}
			}
		});

	
	# ssl �����,����ʹ���㿽��
		* ssl ��Ҫ�����ݽ��м��� , �����ܾ���Ҫ�����û��ռ�ʹ��CPU����,�����޷������㿽��

--------------------
�ֶδ���			|
--------------------
	# �����ļ���ָ�������ݿ�
	# Handler
		ChunkedWriteHandler
	
	# ���
		ChunkedInput(�ӿ�)
			ChunkedFile
				ƽ̨��֧�� zero-copy ��������Ҫת������,���ļ���һ��һ��Ļ�ȡ����

			ChunkedNioFile
				�� ChunkedFile ����,����ʹ����NIOFileChannel

			ChunkedStream
				�� InputStream ��һ��һ���ת������

			ChunkedNioStream
				�� ReadableByteChannel ��һ��һ���ת������
	
	# demo
		// ��ʼ������ ChunkedWriteHandler �� �Լ���Handler
		ChannelPipeline p = ...;
		p.addLast("streamer", new ChunkedWriteHandler());
		p.addLast("handler", new MyHandler());

		// ���Լ�Handler������� ChunkedFile �����
		Channel ch = ...;
		ch.write(new ChunkedFile(new File("video.mkv"));

--------------------
���䷽ʽ��ѡ��		|
--------------------
	@Override
    public void channelRead0(ChannelHandlerContext ctx, String filePath) throws Exception {
        RandomAccessFile raf  = new RandomAccessFile(filePath, "r");
        if (ctx.pipeline().get(SslHandler.class) == null) {
			// δʹ��ssl,����ʹ���㿽��
            ctx.write(new DefaultFileRegion(raf.getChannel(), 0, length));
        } else {
			// ʹ����ssl,����ʹ���㿽��,����ʹ�÷ֶδ����е�ʵ��
            ctx.write(new ChunkedFile(raf));
        }
		ctx.flush();
    }


----------------------
ChunkedInput		  |
----------------------
	# �ӿڳ��󷽷�

	boolean isEndOfInput() throws Exception;
		*  �Ƿ��Ѿ���ȡ���

	void close() throws Exception;
		* �ر�

	@Deprecated
	B readChunk(ChannelHandlerContext ctx) throws Exception;
	B readChunk(ByteBufAllocator allocator) throws Exception;
		* ��ȡ����,���ط���

	long length();
		* �ɶ�����

	long progress();
		* Ŀǰ�Ĵ������

--------------------------------------------
������ȵļ���								|
--------------------------------------------
	# ��� 
		ChannelProgressiveFutureListener
			* ���ڼ���������ȵļ�����
			* �� GenericProgressiveFutureListener �Ŀ�ʵ��


	ChannelFuture sendFileFuture = ctx.write(new DefaultFileRegion(file.getChannel(), 0, fileLength), ctx.newProgressivePromise());
	sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
		// �������
		@Override
		public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) {
			if (total < 0) { // δ֪�ܴ�С
				System.err.println(future.channel() + " Transfer progress: " + progress);
			} else {
				System.err.println(future.channel() + " Transfer progress: " + progress + " / " + total);
			}
		}

		// �������
		@Override
		public void operationComplete(ChannelProgressiveFuture future) {
			System.err.println(future.channel() + " Transfer complete.");
		}
	});