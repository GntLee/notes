---------------
io ����������io
---------------
	# bufio ��
		* Ĭ�ϵĻ�������С�� 4096 �ֽ�
			const defaultBufSize = 4096
		
		* ��С�Ķ���������С�� 16 �ֽ�
			const minReadBufferSize = 16
		

		const maxConsecutiveEmptyReads = 100

	# ����
		func ScanBytes(data []byte, atEOF bool) (advance int, token []byte, err error)
		func ScanLines(data []byte, atEOF bool) (advance int, token []byte, err error)
		func ScanRunes(data []byte, atEOF bool) (advance int, token []byte, err error)
		func ScanWords(data []byte, atEOF bool) (advance int, token []byte, err error)
	
	# ���������� Writer/Reader ��װ��һ�� io.Writer/io.Reader�����Ҷ�ʵ����Writer/Reader�ӿ�
	
	# Reader
		type Reader struct {
			buf          []byte
			rd           io.Reader // reader provided by the client
			r, w         int       // buf read and write positions
			err          error
			lastByte     int // last byte read for UnreadByte; -1 means invalid
			lastRuneSize int // size of last rune read for UnreadRune; -1 means invalid
		}

		func NewReader(rd io.Reader) *Reader 
		func NewReaderSize(rd io.Reader, size int) *Reader 
		
		func (b *Reader) Size() int { return len(b.buf)
		func (b *Reader) Reset(r io.Reader
		func (b *Reader) fill()
		func (b *Reader) Peek(n int) ([]byte, error) 
		func (b *Reader) Discard(n int) (discarded int, err error) 
		func (b *Reader) Buffered() int 
		func (b *Reader) ReadSlice(delim byte) (line []byte, err error) 
		func (b *Reader) ReadLine() (line []byte, isPrefix bool, err error)
			* ��ȡһ�����ݣ����ǱȽϵͼ���api��
			* ����ʹ�� ReadBytes('\n') / ReadString('\n') / Scanner

		func (b *Reader) ReadBytes(delim byte) ([]byte, error)
		func (b *Reader) ReadString(delim byte) (string, error) 
			* ����ָ���ķָ������ж�ȡ���ݣ��������ݰ����ָ���
			* �����ȡ��ĩβ��error = io.EOF

	# Writer
		type Writer struct {
			err error
			buf []byte
			n   int
			wr  io.Writer
		}

		
		func NewWriter(w io.Writer) *Writer
		func NewWriterSize(w io.Writer, size int) *Writer

		func (b *Writer) Size() int 
		func (b *Writer) Reset(w io.Writer)
		func (b *Writer) Flush() error 
		func (b *Writer) Available() int
		func (b *Writer) Buffered() int 

		func (b *Writer) WriteByte(c byte) error 
		func (b *Writer) Write(p []byte) (nn int, err error) 
		func (b *Writer) WriteRune(r rune) (size int, err error)
		func (b *Writer) WriteString(s string) (int, error) 
		func (b *Writer) ReadFrom(r io.Reader) (n int64, err error) 


	# ReadWriter
		type ReadWriter struct {
			*Reader
			*Writer
		}

		func NewReadWriter(r *Reader, w *Writer) *ReadWriter 

		* �̳���Reader/Writer
	
	# Scanner
		func NewScanner(r io.Reader) *Scanner

		func (s *Scanner) Err() error 
		func (s *Scanner) Bytes() []byte 
		func (s *Scanner) Text() string 
		func (s *Scanner) Scan() bool
		func (s *Scanner) Buffer(buf []byte, max int) 
		func (s *Scanner) Split(split SplitFunc) {
