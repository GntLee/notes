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
		func (l *List) Init() *List
			*  ���һ�����е�������߳�ʼ��һ���µ�����

		func (l *List) InsertAfter(v interface{}, mark *Element) *Element
			* ��ָ���Ľڵ�󣬲�������

		func (l *List) InsertBefore(v interface{}, mark *Element) *Element
			* ��ָ���Ľڵ�ǰ����������

		func (l *List) Len() int
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
	