--------------------------------
��Ϣѹ��						|
--------------------------------
	# ����һ��zip��ѹ������ͽ�����
		pipeline.addLast(ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP));
		pipeline.addLast(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));
	
	# ֧�ֵ�ѹ������
		ZLIB
		GZIP
		NONE
		ZLIB_OR_NONE

--------------------------------
�ͻ���							|
--------------------------------


--------------------------------
�����							|
--------------------------------
