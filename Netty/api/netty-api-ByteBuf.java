-----------------------------
ByteBuf						 |
-----------------------------
	#  abstract class ByteBuf implements ReferenceCounted, Comparable<ByteBuf>

-----------------------------
����						 |
-----------------------------
	// �Ǳ�Ĳ���
	int readerIndex();
		* ���ض��Ǳ�
	
	ByteBuf readerIndex(int readerIndex);
		* �����µĶ��Ǳ�

	int writerIndex();
		* ����д�Ǳ�
	
	ByteBuf writerIndex(int writerIndex)
		* �����µ�д�Ǳ�
	
	ByteBuf setIndex(int readerIndex, int writerIndex);
		* ͬʱ���ö�д�Ǳ�
	
	int arrayOffset();
		* ���صײ�����洢���ݵ�ƫ����(һ�㶼��0)

	// ���������Բ���
	int capacity();
		* ������������

	boolean isReadable();
		* �Ƿ�������1���ֽڵĿɶ��ռ�
		* wi > ri

	boolean isReadable(int size);
		* �Ƿ���ָ�����ȵĿɶ��ռ�
		* wi - ri >= size
	
	boolean isWritable();
		* �Ƿ����뻹��1���ֽڵĿ�д�ռ�
	
	boolean isWritable(int size);
		* �Ƿ����뻹��ָ�����ֽڵĿ�д�ռ�

	int writableBytes();
		* ���ؿ�д�Ŀռ��С

	ByteBuf clear();
		* ���ö�д��ʶ��Ϊ1,����δ�������
	
	int readableBytes();
		* ���ؿɶ��ֽ���(д���� - ������)

	
	// ���ݶ�ȡ
	long  readUnsignedInt()
	ByteBuf retainedDuplicate()
	short readUnsignedByte()
	CharSequence getCharSequence(int index, int length, Charset charset);
		*  ��ָ���ĽǱ꿪ʼ,��ȡָ�����ȵ�����,ʹ��ָ������󷵻�

	// ���ݶ���
	ByteBuf discardReadBytes();
		* ��� ByteBuf ���Ѷ�ȡ������,δ��������ǰ�ƶ�,�Ӷ�ʹ ByteBuf �ж���Ŀռ������µ�����
		* ���ܻ��漰�ڴ渴��,��Ϊ����Ҫ�ƶ� ByteBuf �пɶ����ֽڵ���ʼλ��,�����Ĳ�����Ӱ������
		* һ������Ҫ�����ͷ��ڴ��ʱ��ʹ�������Ƚϴ�
	
	ByteBuf discardSomeReadBytes();

	// ��ǲ���
	ByteBuf markReaderIndex()
		* ��Ƕ��Ǳ�

	ByteBuf resetReaderIndex()
		* ���ö��Ǳ�Ϊ��ǽǱ�

	// ���ƺ�����
	ByteBuf duplicate();
		* ����һ���µĻ�����,���е�����,����index����һ����
		* �����ڴ�,���ݱ仯�ụ��Ӱ��

	ByteBuf slice();
		* �����µĻ�����,�ж�����index
			capacity = 'ԭ��buffer�Ŀɶ�����'
			rindex = 0
			windex = 'ԭbuffer��windex'
		* �����ڴ�,���ݱ仯�ụ��Ӱ��
		* ��δ�������ݸ��Ƴ���

	ByteBuf slice(int index, int length);

	ByteBuf order(ByteOrder endianness);
		

	// ����
	boolean hasArray()
		* �Ƿ�֧�ַ�������(����,�Ƕѵ�buf)
		* ���ʷǶѻ����� ByteBuf ������,���׳��쳣 UnsupportedOperationException

	byte[] array();
		* ��ȡ����������
	
	int indexOf(int fromIndex, int toIndex, byte value);
		* �ж�value֪�������buffer,��from��ʼ,��to����
	
	int bytesBefore(byte value);
	