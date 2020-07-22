------------------------------------
Queue								|
------------------------------------
	# ��ϵ
		Queue(java.util)
			|-Deque
				|-ArrayDeque
				|-ConcurrentLinkedDeque
			|-BlockingQueue
			|-AbstractQueue
				|-PriorityQueue
				|-DelayQueue
				|-SynchronousQueue
				|-PriorityBlockingQueue
				|-LinkedTransferQueue
				|-LinkedBlockingDeque
				|-ArrayBlockingQueue
					* ��������ṹʵ��
					* ��ӳ��Ӳ���һ����,������ӳ����໥����,Ч�ʵ���

				|-LinkedBlockingQueue
					* ���õ��������ʽʵ��
					* �����������������뼼��ʵ����ӳ��ӻ�������
					* ���н����,����������ʱĬ��Ϊ���intֵ

	
	# ���õ�һЩQueue
		+----------------------+----------------+-----------+-----------------+
		|����				   |	������ʽ	|	�Ƿ��н�|	���ݽṹ
		+----------------------+----------------+-----------+-----------------+
		|ArrayBlockingQueue	   |����			|�н�		|ArrayList		  |
		+----------------------+----------------+-----------+-----------------+
		|LinkedBlockingQueue   |����			|�޽�		|LinkedList		  |
		+----------------------+----------------+-----------+-----------------+
		|ConcurrentLinkedQueue |CAS				|�޽�		|LinkedList		  |
		+----------------------+----------------+-----------+-----------------+
		|ConcurrentLinkedDeque |CAS				|�޽�		|				  |
		+----------------------+----------------+-----------+-----------------+
		|LinkedTransferQueue   |CAS				|�޽�		|LinkedList		  |
		+----------------------+----------------+-----------+-----------------+
		|PriorityBlockingQueue |													
		+----------------------+----------------+-----------+-----------------+
		|DelayQueue            |
		+----------------------+----------------+-----------+-----------------+
		|SynchronousQueue	   |
		+----------------------+----------------+-----------+-----------------+
------------------------------------
Queue								|
------------------------------------
	# Queue �ӿ�, ������˻����Ĳ���
		public interface Queue<E> extends Collection<E> {
			boolean add(E e);

			boolean offer(E e);

			E remove();

			E poll();

			E element();

			E peek();
		}
