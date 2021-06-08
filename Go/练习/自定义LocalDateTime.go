import (
	"database/sql/driver"
	"fmt"
	"time"
)

var ZeroTime time.Time

type LocalDateTime time.Time

var format = "2006-01-02 15:04:05"
var formatStr = fmt.Sprintf(`"%s"`, format)

// MarshalJSON ���л�Ϊjson
func (l LocalDateTime) MarshalJSON () ([]byte, error){
	return []byte(time.Time(l).Format(formatStr)), nil
}
// UnmarshalJSON �����л�Ϊ����
func (l *LocalDateTime) UnmarshalJSON (bytes []byte) error {
	val, err := time.Parse(formatStr, string(bytes))
	if err != nil {
		return err
	}
	*l = LocalDateTime(val)
	return nil
}

// Value Go����ת��Ϊ���ݿ�����
func (l LocalDateTime) Value() (driver.Value, error) {
	return time.Time(l), nil
}

// Scan ���ݿ�����ת��ΪGo����
func (l *LocalDateTime) Scan(src interface{}) error {
	switch t := src.(type) {
		case time.Time: {
			*l = LocalDateTime(t)
			return nil
		}
	}
	return fmt.Errorf("�޷����� %v ΪLocalDateTime", src)
}