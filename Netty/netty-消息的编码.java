----------------------------
����						|
----------------------------
	# ������ǰѶ���ת��Ϊ���紫��Ķ���������(ByteBuf)
	# �漰���
		|-MessageToByteEncoder<I>
		|-MessageToMessageEncoder<I>
			|-LengthFieldPrepender
			|-StringEncoder

----------------------------
MessageToByteEncoder<T>		|
----------------------------
	# �Ѷ���ת��ΪByteBuf,������,��Ҫʵ�ֳ��󷽷�
		void encode(ChannelHandlerContext ctx, I msg, ByteBuf out) throws Exception;

		msg
			* ����
		out
			* �Ѷ���ת��Ϊ�ֽ����ݺ�,д�뵽��Buf
	
	# �� Integer ת��Ϊ Byte
		public class IntegerToByteEncoder extends MessageToByteEncoder<Integer> {
			@Override
			protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) throws Exception {
				out.writeInt(msg);
			}
		}
----------------------------
MessageToMessageEncoder<T>	|
----------------------------
	# ������,�Ѷ���ת��Ϊ����,��Ҫʵ�ֳ��󷽷�
		void encode(ChannelHandlerContext ctx, I msg, List<Object> out)

