----------------------------
���ȶ���					|
----------------------------
	# ��ͨ����:�Ƚ��ȳ�,������
	# ���ȶ���
		* ����˳������˳���޹�
		* �����ȼ����
	

	# �ײ����ʹ�ö���ʵ��


----------------------------
��������ʵ��				|
----------------------------
/**
 * 
 * ���ȶ���
 * @author KevinBlandy
 *
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> {
	
	private MaxHeap<E> maxHeap;
	
	public PriorityQueue() {
		this.maxHeap = new MaxHeap<>();
	}
	
	public int size() {
		return this.maxHeap.size();
	}
	
	public boolean empty() {
		return this.maxHeap.empty();
	}
	
	/**
	 * �鿴����Ԫ��
	 */
	public E getFront() {
		return this.maxHeap.findMax();
	}
	
	/**
	 * ���
	 * �ɵͲ�������Լ�ά��������
	 */
	public void enqueue(E e) {
		this.maxHeap.add(e);
	}
	
	/**
	 * ����
	 * �ɵͲ������ά���Լ��Ķ�����
	 */
	public E dequeue() {
		return this.maxHeap.extractMax();
	}
}