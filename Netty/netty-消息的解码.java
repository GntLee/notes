
----------------------------
����						|
----------------------------
	# ��������ʵ����ʵ����ChannelInboundHandler��handler,��Ҫ�����þ��ǰ���Ϣת��Ϊ�Զ���Ķ���
	# �漰���
		|-ByteToMessageDecoder
			|-ReplayingDecoder<S> 
			|-LineBasedFrameDecoder
			|-LengthFieldBasedFrameDecoder
			|-DelimiterBasedFrameDecoder
			|-FixedLengthFrameDecoder
		|-MessageToMessageDecoder<T>
			|-StringDecoder

----------------------------
ByteToMessageDecoder		|
----------------------------
	# �Ѷ���������ת��Ϊ�Զ���Ķ���
	# ������,���ĵĳ��󷽷���Ҫ�û��ֶ��ĸ�д
		void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception;
		
		* ͨ���÷���,�ֶ���buf���������ת��Ϊ�Զ���Ķ���,��ӵ�out��������
		* ���buf��������ݲ������,ֱ�� return,ֱ��������װ��һ������ʱ�Ŵ���
		* ����һ��handler��������Ϳ���ǿ�ƵİѶ���ת��Ϊ�Լ�����Ķ�����(����ʹ��SimpleChannelInboundHandler<T>)
	
	# ���л���
		* ÿ���������ݽ��յ�ʱ��,ByteToMessageDecoder ������� decode() �����������ڲ����ۻ�����
		* decode() �������Ծ������ۻ�������û���㹻����ʱ������ out ���������������
		* ���и�������ݱ������� ByteToMessageDecoder ����һ�ε��� decode() ����
		* ����� decode() ������������һ������ out ������,����ζ�Ž�����������Ϣ�ɹ�
		* ByteToMessageDecoder ���ᶪ�����ۻ��������Ѿ�������������
		* ����Ҫ�Զ�����Ϣ���� decode(),ByteToMessageDecoder ��������� decode() ֱ�������κ����ݵ� out ��
	
	# Integer ������
		public class IntegerDecoder extends ByteToMessageDecoder {
			@Override
			protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
				if(in.readableBytes() >= 4){
					// �������빻�ĸ��ֽ�,�Ŷ�ȡΪint����
					out.add(in.readInt());
				}
			}
		}
		//����һ������������Ϳ���ʹ����,SimpleChannelInboundHandler<Integer>

----------------------------
ReplayingDecoder<T>			|
----------------------------
	# �̳��� ByteToMessageDecoder ������
	# �� ByteToMessageDecoder ��Ⱦ���,����ȡ�����������ݵ�ʱ��,����Ҫȥ�ж��Ƿ����㹻���ֽ�
		* ���ڲ�ʹ����һ������� ByteBuf ʵ��:ReplayingDecoderByteBuf
		* �����decode()��ִ�ж�ȡ���ֽ�������,ByteBuf�Լ����׳��쳣Ȼ,���쳣�׳���ᱻ ReplayingDecoder catchס
			* �쳣ʵ��:Signal REPLAY = Signal.valueOf(ReplayingDecoder.class, "REPLAY");
			* ÿ�ε��쳣�׳�,�����׳�ͬһ��ʵ��,�����쳣�����δ����ĸ���
		* ReplayingDecoder catchס�쳣��,��Ѷ��Ǳ�����Ϊ��ʼֵ
		* ���и�������ݺ�,���ٴε��� decode() ����

	# Դ�뵼��
		// ����ɶ��ĳ���С��4�ֽ�,����
		if (buf.readableBytes() < 4) {
			return;
		}
		// ��Ƕ�����
		buf.markReaderIndex();

		// ��ȡһ��int,��ʾ���ݵĳ���
		int length = buf.readInt();

		if (buf.readableBytes() < length) {
			// ����ɶ�����,С�����ݵĳ���,����
			buf.resetReaderIndex();	// �������ö�����
			return;
		}

		out.add(buf.readBytes(length));

----------------------------
MessageToMessageDecoder<T>	|
----------------------------
	# �Ѷ���ת��Ϊ����,������
	# ������,���ĵĳ��󷽷���Ҫ�û��ֶ��ĸ�д
		void decode(ChannelHandlerContext ctx, T msg, List<Object> out) throws Exception;
		
		* ������ǰ���handler���Ѿ��Ѷ���������ת���˶���
		* ����,��Ҫ�ѽ����Ķ����ٴ�ͨ��handlerת��Ϊ�����Ķ���,���Ǿͳ��������

