--------------------------
cgo����ת��
--------------------------
	# Go��������ֵ���ͺ�C�����������ͻ����������Ƶ�
		C��������				CGO����		Go��������
		char					C.char		byte
		singed char				C.schar		int8
		unsigned char			C.uchar		uint8
		short					C.short		int16
		unsigned short			C.ushort	uint16
		int						C.int		int32
		unsigned int			C.uint		uint32
		long					C.long		int32
		unsigned long			C.ulong		uint32
		long long int			C.longlong	int64
		unsigned long long int	C.ulonglong	uint64
		float					C.float		float32
		double					C.double	float64
		size_t					C.size_t	uint
	
		
		* C������int��short������û����ȷ�����ڴ��С��������CGO�����ǵ��ڴ��С��ȷ���ġ�
		* ��CGO�У�C���Ե�int��long���Ͷ��Ƕ�Ӧ4���ֽڵ��ڴ��С��size_t���Ϳ��Ե���Go����uint�޷����������ͶԴ���
	

