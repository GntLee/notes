---------------------
ring
---------------------
	# ѭ������û�п�ͷ�ͽ�β

---------------------
����
---------------------

---------------------
type
---------------------
	# type Ring struct {
			Value interface{} // for use by client; untouched by this library
			// contains filtered or unexported fields
		}

		func New(n int) *Ring

		func (r *Ring) Do(f func(interface{}))
			* �ѻ��ϵ�ÿ��Ԫ�أ������ݸ�����f����һ��

		func (r *Ring) Len() int
			* ��ȡ����

		func (r *Ring) Link(s *Ring) *Ring
		func (r *Ring) Move(n int) *Ring
		func (r *Ring) Next() *Ring
			* ��ȡ��һ��Ԫ�أ���Ϊ�ǻ�������������޴ε���

		func (r *Ring) Prev() *Ring
			* ��ȡ��һ��Ԫ��

		func (r *Ring) Unlink(n int) *Ring


---------------------
func
---------------------
	