-------------------
gzip
-------------------
	# gzipʵ��RFC 1952��ָ����gzip��ʽѹ���ļ��Ķ�д


-------------------
var
-------------------
	const (
		NoCompression      = flate.NoCompression
		BestSpeed          = flate.BestSpeed
		BestCompression    = flate.BestCompression
		DefaultCompression = flate.DefaultCompression		// Ĭ�ϼ���
		HuffmanOnly        = flate.HuffmanOnly
	)

	* ѹ���ȼ�

	var (
		// ErrChecksum is returned when reading GZIP data that has an invalid checksum.
		ErrChecksum = errors.New("gzip: invalid checksum")
		// ErrHeader is returned when reading GZIP data that has an invalid header.
		ErrHeader = errors.New("gzip: invalid header")
	)

-------------------
type
-------------------
	# type Header struct {
			Comment string    // comment
			Extra   []byte    // "extra data"
			ModTime time.Time // modification time
			Name    string    // file name
			OS      byte      // operating system type
		}
	
	# type Reader struct {
			Header // valid after NewReader or Reader.Reset
		}
		func NewReader(r io.Reader) (*Reader, error)
		func (z *Reader) Close() error
		func (z *Reader) Multistream(ok bool)
		func (z *Reader) Read(p []byte) (n int, err error)
		func (z *Reader) Reset(r io.Reader) error
	
	# type Writer struct {
			Header // written at first call to Write, Flush, or Close
		}
		func NewWriter(w io.Writer) *Writer
		func NewWriterLevel(w io.Writer, level int) (*Writer, error)
		func (z *Writer) Close() error
		func (z *Writer) Flush() error
		func (z *Writer) Reset(w io.Writer)
		func (z *Writer) Write(p []byte) (int, error)


-------------------
func
-------------------
	# gzipѹ�����ѹ��
		import (
			"bytes"
			"compress/gzip"
			"io"
			"log"
			"os"
		)

		func main(){
			buffer := &bytes.Buffer{}

			// ѹ������
			writer := gzip.NewWriter(buffer)
			writer.Write([]byte("�����������Ĺ���޻��޾Ƴ����"))
			writer.Close()

			// ��ѹ����
			reader, err := gzip.NewReader(buffer)
			if err != nil {
				log.Fatal(err)
			}
			if _, err := io.Copy(os.Stdout, reader); err != nil {		// �����������Ĺ���޻��޾Ƴ����
				log.Fatal(err)
			}
			reader.Close()
		}
	
