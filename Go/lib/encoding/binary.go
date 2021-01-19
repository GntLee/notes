----------------------------
binary
----------------------------

----------------------------
����
----------------------------
	const (
		MaxVarintLen16 = 3
		MaxVarintLen32 = 5
		MaxVarintLen64 = 10
	)

	* �����ֽ���
		var BigEndian bigEndian
		var LittleEndian littleEndian

----------------------------
type
----------------------------
	# type ByteOrder interface {
			Uint16([]byte) uint16
			Uint32([]byte) uint32
			Uint64([]byte) uint64
			PutUint16([]byte, uint16)
			PutUint32([]byte, uint32)
			PutUint64([]byte, uint64)
			String() string
		}

		* �ֽ���ӿ�

----------------------------
����
----------------------------
	func PutUvarint(buf []byte, x uint64) int
	func PutVarint(buf []byte, x int64) int
	func Read(r io.Reader, order ByteOrder, data interface{}) error
		* ��ָ����Reader����ָ�����ֽ��򣬶�ȡ���ݵ�data��

	func ReadUvarint(r io.ByteReader) (uint64, error)
	func ReadVarint(r io.ByteReader) (int64, error)
	func Size(v interface{}) int
	func Uvarint(buf []byte) (uint64, int)
	func Varint(buf []byte) (int64, int)
	func Write(w io.Writer, order ByteOrder, data interface{}) error 