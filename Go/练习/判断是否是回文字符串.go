
import (
	"fmt"
)

func main() {
	val := test("112212211")
	fmt.Println(val)
}
// �Ƿ��ǻ����ַ���
func test(str string) bool {

	// ת��Ϊ�ַ���Ƭ
	chars := []rune(str)

	// ��ȡ��Ƭ����
	length := len(chars)

	// ��������
	for i := 0; i < (length / 2); i ++ {
		// �ж�ǰ���Ӧλ�õ��ַ��Ƿ�һ��
		if chars[i] != chars[length - 1 - i] {
			return false
		}
	}
	return true
}
