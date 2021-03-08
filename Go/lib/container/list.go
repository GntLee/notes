---------------------
list
---------------------
	# ˫������

---------------------
����
---------------------

---------------------
type
---------------------
	# type Element struct {
			Value interface{} // contains filtered or unexported fields
		}
		
		* �����еĽڵ�

		func (e *Element) Next() *Element
		func (e *Element) Prev() *Element

	# type List struct
		
		* ����

		func New() *List

		func (l *List) Back() *Element
		func (l *List) Front() *Element
			* ����β/ͷ���

		func (l *List) Init() *List
			*  ���һ�����е�������߳�ʼ��һ���µ�����

		func (l *List) InsertAfter(v interface{}, mark *Element) *Element
			* ��ָ���Ľڵ�󣬲�������

		func (l *List) InsertBefore(v interface{}, mark *Element) *Element
			* ��ָ���Ľڵ�ǰ����������

		func (l *List) Len() int
			* ���س���

		func (l *List) MoveAfter(e, mark *Element)
		func (l *List) MoveBefore(e, mark *Element)
		func (l *List) MoveToBack(e *Element)
		func (l *List) MoveToFront(e *Element)
		func (l *List) PushBack(v interface{}) *Element
		func (l *List) PushBackList(other *List)
			* ������β���������

		func (l *List) PushFront(v interface{}) *Element
		func (l *List) PushFrontList(other *List)
			* ������ͷ��������󣬷��ص�����
		
		func (l *List) Remove(e *Element) interface{}

---------------------
func
---------------------



---------------------
demo
---------------------
	# ���е�ʹ��
		queue := list.New()

		// ���Ԫ�ص�β��
		queue.PushBack("1")
		queue.PushBack("2")
		queue.PushBack("3")

		for queue.Len() > 0{

			// ��ȡͷ���ڵ�
			var element = queue.Front()
			fmt.Println(element.Value)

			// �Ƴ�ͷ���ڵ�
			queue.Remove(element)
		}

		// ��� ---------------
		1
		2
		3
