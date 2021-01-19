---------------------
heap
---------------------
	# ��

---------------------
����
---------------------

---------------------
type
---------------------
	# type Interface interface {
			sort.Interface
			Push(x interface{}) // ���Ԫ�ص� len() λ��
			Pop() interface{}   // �� Len() - 1 λ��ɾ��Ԫ�ز��ҷ���
		}
		
		* ��ʵ���� sort ��������ӿ�



---------------------
func
---------------------
	func Fix(h Interface, i int)

	func Init(h Interface)

	func Pop(h Interface) interface{}
	func Push(h Interface, x interface{})
	func Remove(h Interface, i int) interface{}



---------------------
��������ʵ��
---------------------
import (
	"container/heap"
	"fmt"
)

// �Զ�������
type Value []int

// ʵ�� heap.Interface �ӿ�
func (v *Value) Pop() interface{}{
	arr := *v
	lastIndex := len(arr) - 1
	retVal := arr[lastIndex]
	*v = arr[0: lastIndex]
	return retVal
}
func (v *Value) Push(val interface{}){
	*v = append(*v, val.(int))
}

// ʵ�� sort.Interface �ӿ�
func (v Value) Len () int {
	return len(v)
}

// ��������Ǿ�����ѻ���С�ѵĹؼ�
func (v Value) Less(i, j int) bool{
	return v[i] < v[j]
}
func (v Value) Swap(i, j int){
	v[i], v[j] = v[j], v[i]
}
func main(){
	h := &Value{1,5,4,7,9,3,6,2,8,0}
	fmt.Println(h)	// &[1 5 4 7 9 3 6 2 8 0]
	// ��ʼ��
	heap.Init(h)
	// �Ѿ����մ�С�ѽ���������
	fmt.Println(h)	// &[0 1 3 2 5 4 6 7 8 9]
	// ���Ԫ��
	heap.Push(h, 10)
	fmt.Println(h)	// &[0 1 3 2 5 4 6 7 8 9 10]
	for len(*h) > 0 {
		fmt.Print(heap.Pop(h), ", ") // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
	}
}