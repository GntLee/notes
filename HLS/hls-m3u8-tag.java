#EXTM3U
	* �����ڿ�ͷ��һ������ʾ��ǰ�ļ���һ��m3u8�ļ�

#EXT-X-VERSION:3
	* ָ���汾��һ����3�������������µĶ���7��

#EXT-X-MEDIA-SEQUENCE:64
	* ��һ����Ƭ�����кţ�����ֵ����
	* ���M3U8�ļ���û�и��ֶΣ���playlist�е�һ����Ƭ�����кű�����0

#EXT-X-TARGETDURATION:12
	* ��Ƭ���ʱ������λ����

#EXTINF:<duration>,[<title>]
	* ��Ƭ��ʵ��ʱ����������������� URI ��Ч������ÿ����Ƭ�������и��ֶ�
	* duration����ʾ������ʱ�䣨�룩

	* #EXT-X-VERSIONС��3������£�duration���������������������duration�����Ǹ�������������
	* title��һ����ѡ�ֶΣ���������ǿ�ɶ���


#EXT-X-BYTERANGE:<n>[@o]
	* ��ʾý�����һ��ý�� URI ��Դ�е�һ�Σ�ֻ������ media URI ��Ч

#EXT-X-ENDLIST 
	* ���ֶα�ʾ��Ƭ����������һ���㲥Դ��������playlist�ļ�������µķ�Ƭ

#EXT-X-STREAM-INF:<attribute-list>
	* ��master playlist�У���ʶһ��Variant Stream
	* ������һϵ�е�Redition��ɵġ��ñ�ǩ�������б��а�����Variant Stream��������Ϣ

	BANDWIDTH
		* ��ʾVariant Stream�еķ�ֵ�����ʣ���λbits/s
	AVERAGE-BANDWIDTH
		* ��ʾVariant Stream�е�ƽ�������ʣ���λbits/s
	CODECS
		* ����Variant Stream������Ƶ�����ʽ��ص���Ϣ
	RESOLUTION
		* ����Variant Stream�ж�Ӧ��Ƶ���ķֱ���
	FRAME-RATE
		* ��ʾVariant Stream�е���Ƶ֡��
	
#EXT-X-PLAYLIST-TYPE
	* �ñ�ǩֻ������ֵ��EVENT��VOD
	* EVENTָ���Ƿ�Ƭ����ֻ����M3U8ĩβ����µķ�Ƭ����Ϣ��������ɾ���ϵķ�Ƭ��ͨ���Ƚ�������ֱ��+¼�����������Ҫ�ṩ���ͻ��˵㲥���ܣ�ҲҪ��ʵʱ��������¼�ƣ�ֱ�����֮��EVENT����Ȼ�˻�ΪVOD��

	* M3U8�д���#EXT-X-ENDLIST��ǩʱ�����Ժ���EXT-X-PLAYLIST-TYPE
	* ���M3U8�в�����#EXT-X-ENDLIST�Լ�#EXT-X-PLAYLIST-TYPE��ǩ����������˿����������playlist���ݡ�


#EXT-X-ALLOW-CACHE:NO
	* �Ƿ�����ͻ��˶����ص���Ƶ�ֶλ��������Ժ󲥷�

#EXT-X-DISCONTINUITY
	* ��ʾ��ǰ����Ƶ�ֶκ���֮ǰ��֮�����Ƶ�ֶ��в�ͬ�ı��벻������