------------------------
��Ϣ�ı����			|
------------------------
	# ��Ͻ������ͱ�������һ��(��һ����)
		* ���ܻ�������������

	# �漰���
		|-ChannelDuplexHandler
			|-CombinedChannelDuplexHandler<I,O>
			|-ByteToMessageCodec<T>
			|-MessageToMessageCodec<I,O>
		
	# ϵͳ���ṩ���� byte[] �� ByteBuf �ı������
		ByteArrayEncoder<T>
		ByteArrayDecoder<T>
	
------------------------
ByteToMessageCodec<T>	|
------------------------
	# �������� byte-to-message �� message-to-byte
	# �����ֽ���Ϣ�� POJO ����� POJO ��Ϣ���ֽ�
	# ��ͬ�� ByteToMessageDecoder �� MessageToByteEncoder �����,�����࣬������ 2 ��������Ҫ�����Լ�ʵ��
		void encode(ChannelHandlerContext ctx, I msg, ByteBuf out) throws Exception;
			* ���뷽��

		void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception;
			* ���뷽��

--------------------------
MessageToMessageCodec<I,O>|
--------------------------
	# ���� message-to-message �ı���ͽ���,����ת��Ϊ����
	# ���Կ����� MessageToMessageDecoder �� MessageToMessageEncoder �������
	# ������,��Ҫʵ����������
		void encode(ChannelHandlerContext ctx, I msg, List<Object> out)throws Exception;
			* ����

		void decode(ChannelHandlerContext ctx, O msg, List<Object> out)throws Exception;
			* ���� 
	
	# ���ֵı���ͽ���
		public class NumberCodec extends MessageToMessageCodec<Integer, Long> {
			@Override
			public Long decode(ChannelHandlerContext ctx, Integer msg, List<Object> out)throws Exception {
				// ��Integerת��ΪLong
				out.add(msg.longValue());
			}

			@Override
			public Integer encode(ChannelHandlerContext ctx, Long msg, List<Object> out)throws Exception {
				// ��Longת��ΪInteger
				out.add(msg.intValue());
			}
		}
----------------------------------
CombinedChannelDuplexHandler<I,O> |
----------------------------------
	# CombinedChannelDuplexHandler<I extends ChannelInboundHandler, O extends ChannelOutboundHandler> extends ChannelDuplexHandler
	# ��Ȼ����಻�Ǳ������ API ��һ����,��������������������һ���������
	# �����԰�һ���������ͽ�����,���ȿ��Դ�����վ��Ϣ,Ҳ���Դ����վ��Ϣ
	# �Զ���� Char �� Byte
		public class CharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {
			public CharCodec(){
				super(new ByteToCharDecoder(), new CharToByteEncoder());
			}
		}
