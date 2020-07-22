---------------------------------------------
��������ʵ�ֵĴ���βָ��Ķ���				|
---------------------------------------------
	# ������,ʱ�临�Ӷȼ�����,����Ҫ����,��Ϊ����ָ��


public class LinkedListQueue<E> {
	
	private class Node {
		public E e;
		public Node next;

		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}

		public Node(E e) {
			this(e, null);
		}

		@Override
		public String toString() {
			return this.e.toString();
		}
	}

	private Node head;		//ͷָ��
	private Node tail;		//βָ��
	private int size;

	public LinkedListQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void equeue(E e) {
		if (this.tail == null) {
			this.tail = new Node(e);
			this.head = this.tail;
		} else {
			this.tail.next = new Node(e);
			this.tail = this.tail.next;
		}
		this.size++;
	}

	public E dequeue() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("�յ�queue");
		}
		Node retNode = this.head;
		this.head = this.head.next;
		retNode.next = null;
		if (this.head == null) {
			this.tail = null;
		}
		this.size--;
		return retNode.e;
	}

	public E front() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("�յ�queue");
		}
		return this.head.e;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Queue front ");

		Node cur = head;
		while (cur != null) {
			stringBuffer.append(cur + " -> ");
			cur = cur.next;
		}

		stringBuffer.append("NULL tail");
		return stringBuffer.toString();
	}
}
