------------------------
����������				|
------------------------
	# �����������Ƕ�����
	# ������������ÿ���ڵ�ֵ 
		* ��Ҫ���������������нڵ�ֵ
		* ��ҪС�������������нڵ�ֵ
		* �����ظ�

	# �洢�����е�������Ҫ������
	
	# �������������Ԫ�صķǵݹ�д��,���������
		
	# �����ظ�Ԫ�ص���
		* ������С����ڽڵ�,�������������ڵ��ڽڵ�
		* ÿ���ڵ�����һ��:count����,���������һ���ظ�Ԫ��,������ count++

------------------------
������������ʵ��		|
------------------------
	
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * 
 * ����������
 * <p>
 * �����������Ƕ�����
 * </p>
 * <p>
 * ������������ÿ���ڵ�ֵ ��Ҫ���������������нڵ�ֵ ,��ҪС�������������нڵ�ֵ
 * </p>
 * <p>
 * �洢�����е�������Ҫ������
 * </p>
 * 
 */
public class BinarySearchTree<E extends Comparable<E>> {

	private class Node {
		private E value;
		private Node left;
		private Node right;

		public Node(E value) {
			this(value, null, null);
		}

		public Node(E value, Node left, Node right) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

	private Node root;
	private int size;

	public BinarySearchTree() {
		this.root = null;
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void addRecursion(E value) {
		this.root = this.addRecursion(this.root, value);
	}

	// �ݹ����
	private Node addRecursion(Node node, E value) {
		if (node == null) {
			this.size++;
			return new Node(value);
		}
		int result = value.compareTo(node.value);
		if (result < 0) {
			node.left = this.addRecursion(node.left, value);
		} else if (result > 0) {
			node.right = this.addRecursion(node.right, value);
		}
		return node;
	}

	public boolean contains(E value) {
		return this.contains(this.root, value);
	}

	// �ݹ�����ж��Ƿ����
	private boolean contains(Node node, E value) {
		if (node == null) {
			return false;
		}
		int result = node.value.compareTo(value);
		if (result < 0) {
			return this.contains(node.left, value);
		} else if (result > 0) {
			return this.contains(node.right, value);
		}
		return true;
	}

	// �ݹ�forEach
	public void forEach(Consumer<E> consumer) {
		this.forEach(this.root, consumer);
	}

	private void forEach(Node node, Consumer<E> consumer) {
		if (node != null) {
			this.forEach(node.left, consumer);
			/**
			 * д�м�����������(�����������) дǰ�����ǰ����� д������Ǻ������
			 */
			consumer.accept(node.value);
			this.forEach(node.right, consumer);
		}
	}

	// �ǵݹ��ǰ�����,����ջ�ṹ(�������)
	public void forEach() {
		Stack<Node> stack = new Stack<>();
		stack.push(this.root);
		while (!stack.isEmpty()) {
			Node current = stack.pop(); // ջ��Ԫ��
			System.out.println(current.value); // ����Ԫ��
			if (current.right != null) {
				stack.push(current.right);
			}
			if (current.left != null) {
				stack.push(current.left);
			}
		}
	}

	// �ǵݹ����,��������,���ڲ������(�������)
	public void forEach1() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			Node current = queue.remove(); // ����βԪ��
			System.out.println(current.value);// ����Ԫ��
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
		}
	}

	private Node min(Node node) {
		if (node.left == null) {
			return node;
		}
		return this.min(node.left);
	}

	// ��ȡ��С��ֵ
	public E min() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("null map");
		}
		return this.min(this.root).value;
	}

	public Node max(Node node) {
		if (node.right == null) {
			return node;
		}
		return this.max(node.right);
	}

	// ��ȡ����ֵ
	public E max() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("null map");
		}
		return this.max(this.root).value;
	}

	// ɾ����Сֵ�ڵ�,���ҷ��ؽ�����ڵ�
	private Node removeMin(Node node) {
		if (node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			this.size--;
			return rightNode;
		}
		node.left = this.removeMin(node.left);
		return node;
	}

	// ɾ����������Сֵ
	public E removeMin() {
		E ret = this.min();
		this.root = this.removeMin(this.root);
		return ret;
	}

	// ɾ�����ֵ�ڵ�,���ҷ��ؽ�����ڵ�
	private Node removeMax(Node node) {
		if (node.right == null) {
			Node leftNode = node.left;
			node.left = null;
			this.size--;
			return leftNode;
		}
		node.right = this.removeMax(node.right);
		return node;
	}

	// ɾ�����������ֵ
	public E removeMax() {
		E ret = this.max();
		this.root = this.removeMax(this.root);
		return ret;
	}

	// ɾ��ָ�������µ�ָ���ڵ㣬����ɾ���ڵ��ĸ�
	private Node remove(Node node, E value) {
		if (node == null) {
			return node;
		}
		int result = value.compareTo(node.value);
		if (result < 0) {
			node.left = this.remove(node.left, value);
			return node;
		} else if (result > 0) {
			node.right = this.remove(node.right, value);
			return node;
		} else {
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				this.size--;
				return rightNode;
			}
			if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				this.size--;
				return leftNode;
			}

			Node rightMin = min(node.right);
			rightMin.right = removeMin(node.right);
			rightMin.left = node.left;
			
			node.left = node.right = null;

			return rightMin;
		}
	}

	// ɾ������ڵ�
	public void remove(E e) {
		this.root = this.remove(this.root, e);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		this.generateBinarySearchTree(this.root, 0, stringBuilder);
		return stringBuilder.toString();
	}

	// ���ɽڵ������ַ���
	private void generateBinarySearchTree(Node node, int depth, StringBuilder stringBuilder) {
		if (node == null) {
			stringBuilder.append(this.generateDepthString(depth) + "null\n");
			return;
		}
		stringBuilder.append(generateDepthString(depth) + node.value + "\n");
		generateBinarySearchTree(node.left, depth + 1, stringBuilder);
		generateBinarySearchTree(node.right, depth + 1, stringBuilder);
	}

	// ���ɲ���ַ���
	private String generateDepthString(int depth) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			stringBuilder.append("--");
		}
		return stringBuilder.toString();
	}
}
