
-----------------------
ѭ������				|
-----------------------
	# �ײ�ʹ������
	# �������ܱȽϺ�,�����������'λ��'����,ͨ������ָ�뽻��λ������д����
	
	# ��Ҫ���弸�����������ƶ���
		size
			* ���е�����ݻ�

		front
			* ͷָ�룬ָ����еĵ�һ��Ԫ�أ���ʼֵΪ 0
			* ���еĵ�һ��Ԫ�أ�arr[front]

		tail
			* ���е����һ��λ�õ�ǰһ��λ�ã���ʼֵΪ 0
		
		
		* �ж϶����Ƿ����
			(tail + 1) % size = front 
		
		* �ж϶����Ƿ�Ϊ��
				tail == front

		* ��ȡ�����е�������
			(tail + size - front) % size 
		

-----------------------
ʵ��Demo				|
-----------------------
@SuppressWarnings("unchecked")
public class LoopQueue<E> {
	private E[] array;
	private int front; // ͷָ��
	private int tail; 	// βָ��
	private int size;

	public LoopQueue(int capacity) {
		this.array = (E[]) new Object[capacity + 1];
		this.front = 0;
		this.tail = 0;
		this.size = 0;
	}

	public int capacity() {
		return this.array.length - 1;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.front == this.tail;
	}

	public void enqueue(E e) {
		if ((this.tail + 1) % this.array.length == this.front) {
			this.resize(this.capacity() * 2);
		}
		this.array[this.tail] = e;
		this.tail = (this.tail + 1) % this.array.length;
		this.size++;
	}

	public E dequeue() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("empty queue.");
		}
		E value = this.array[front];
		this.array[front] = null;
		this.front = (this.front + 1) % this.array.length;
		this.size--;
		if (this.size == this.capacity() / 4 && this.capacity() / 2 != 0) {
			this.resize(this.capacity() / 2);
		}
		return value;
	}

	public E front() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("empty queue.");
		}
		return this.array[this.front];
	}

	private void resize(int capacity) {
		E[] newArray = (E[]) new Object[capacity + 1];
		for (int i = 0; i < this.size; i++) {
			newArray[i] = this.array[(i + this.front) % this.array.length];
		}
		this.array = newArray;
		this.front = 0;
		this.tail = this.size;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("Queue: size = %d , capacity = %d\n", size, this.capacity()));
		stringBuilder.append("front [");
		for (int i = front; i != tail; i = (i + 1) % this.array.length) {
			stringBuilder.append(this.array[i]);
			if ((i + 1) % this.array.length != tail) {
				stringBuilder.append(", ");
			}
		}
		stringBuilder.append("] tail");
		return stringBuilder.toString();
	}
}


