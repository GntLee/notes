-------------------------------
MappedByteBuffer				|
-------------------------------
	# ���ļ���ĳһ���ֻ�ȫ��ӳ�䵽�ڴ���, ӳ���ڴ滺�����Ǹ�ֱ�ӻ�����
	# ���̳�:ByteBuffer
	# ͨ�� FileChannel �� api ��ȡ
		MappedByteBuffer map(MapMode mode,long position, long size);

		MapMode
			READ_ONLY
				* ��ͼ�޸ĵõ��Ļ������������׳� ReadOnlyBufferException
			READ_WRITE
				* �Եõ��Ļ������ĸ������ս��������ļ�
				* �ø��Ķ�ӳ�䵽ͬһ�ļ�����������һ���ǿɼ���
			PRIVATE
				* �Եõ��Ļ������ĸ��Ĳ��ᴫ�����ļ�, ���Ҹø��Ķ�ӳ�䵽ͬһ�ļ�����������Ҳ���ǿɼ���
				* �ᴴ�����������޸Ĳ��ֵ�ר�ø���
		
	
	# ����
		MappedByteBuffer force()
			* ��������READ_WRITEģʽ��, �˷����Ի��������ݵ��޸�ǿ��д���ļ�

		boolean isLoaded()
			* ����������������������ڴ���, �򷵻���, ���򷵻ؼ�

		MappedByteBuffer load()
			* load()�������������������ڴ�, �����ظû�����������
		

