	
--------------------
handler��ϵ			|
--------------------
ChannelHandler
	* ���hanlder

	|-ChannelHandlerAdapter
	|-ChannelInboundHandler
		* ������ӿ�

		|-ChannelInboundHandlerAdapter
			|-ByteToMessageDecoder
				|-ReplayingDecoder<S> 
				|-LineBasedFrameDecoder
				|-LengthFieldBasedFrameDecoder
				|-DelimiterBasedFrameDecoder
				|-FixedLengthFrameDecoder
				|-SslHandler
					* ʵ���� ChannelOutboundHandler
			|-MessageToMessageDecoder
				|-StringDecoder
			|-ChannelInitializer
			|-SimpleChannelInboundHandler<I>
				* ʵ�����͵�ǿ��ת��,�����Զ��ͷ�buf

	|-ChannelOutboundHandler
		* д����ӿ�

		|-ChannelOutboundHandlerAdapter
			|-MessageToByteEncoder<I>
			|-MessageToMessageEncoder<I>
				|-LengthFieldPrepender
				|-StringEncoder
	
	|-ChannelDuplexHandler
		* ʵ���˶�д�ӿ�
		* �̳�:ChannelInboundHandlerAdapter,ʵ��:ChannelOutboundHandler

		|-ChunkedWriteHandler
		|-CombinedChannelDuplexHandler

			
--------------------
buf��ϵ				|
--------------------
ReferenceCounted
	|-ByteBuf
		|-CompositeByteBuf
	|-ByteBufHolder
	|-FileRegion
		|-DefaultFileRegion
ByteBufProcessor
ByteBufAllocator
Unpooled
ByteBufUtil


--------------------
����				|
--------------------
ChunkedInput
	|-ChunkedFile
	|-ChunkedNioFile
	|-ChunkedStream
	|-ChunkedNioStream	
