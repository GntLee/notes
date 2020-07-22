------------------------------------
ƽ����								|
------------------------------------
	# ƽ����
		* ��֤��ѯЧ��, �����˻�Ϊ����
		* �������������ĸ߶Ȳ�ֵ���ᳬ�� 1
		* ����������������һ��ƽ�������

	# ƽ������ʵ��
		AVL
		����� 
		��������
		Treap
		��չ��
		...

------------------------------------
ƽ����								|
------------------------------------
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class AVLTree<K extends Comparable<K>, V> {
	private class Node {
		K key;
		V value;
		private Node left;
		private Node right;
		private int height;

		public Node(K key, V value, Node left, Node right) {
			super();
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.height = 0;
		}

		public Node(K key, V value) {
			this(key, value, null, null);
		}
	}

	private Node root;

	private int size;

	public int size() {
		return this.size;
	}

	public boolean empty() {
		return this.size == 0;
	}

	private int getHeight(Node node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}

	// ����ƽ������
	private int getBalanceFactor(Node node) {
		if (node == null) {
			return 0;
		}
		return this.getHeight(node.left) - this.getHeight(node.right);
	}

	// �ж��Ƿ��Ƕ���������
	public boolean isBinarySearchTree() {
		ArrayList<K> keys = new ArrayList<>();
		inOrder(this.root, keys);
		for (int i = 1; i < keys.size(); i++) {
			int result = keys.get(i - 1).compareTo(keys.get(i));
			if (result > 0) {
				return false;
			}
		}
		return true;
	}

	// �ж��Ƿ���һ��ƽ�������
	public boolean isBalanced() {
		return this.isBalanced(this.root);
	}

	private boolean isBalanced(Node node) {
		if (node == null) {
			return true;
		}
		int blanceFactor = this.getBalanceFactor(node);
		if (Math.abs(blanceFactor) > 1) {
			return false;
		}
		return isBalanced(node.left) && isBalanced(node.right);
	}

	private void inOrder(Node node, ArrayList<K> keys) {
		if (node == null) {
			return;
		}
		this.inOrder(node.left, keys);
		keys.add(node.key);
		this.inOrder(node.right, keys);
	}

	private Node getNode(Node node, K key) {
		if (node == null) {
			return node;
		}
		int result = node.key.compareTo(key);
		if (result > 0) {
			return this.getNode(node.left, key);
		} else if (result < 0) {
			return this.getNode(node.right, key);
		} else {
			return node;
		}
	}

	public boolean contains(K key) {
		return this.getNode(this.root, key) != null;
	}

	public V get(K key) {
		Node node = this.getNode(this.root, key);
		return node == null ? null : node.value;
	}

	public V set(K key, V value) {
		Node node = this.getNode(this.root, key);
		if (node == null) {
			throw new IllegalArgumentException("not found key");
		}
		V retVallue = node.value;
		node.value = value;
		return retVallue;
	}

	// ����ת
	private Node rightRotate(Node y) {
		Node x = y.left;
		Node t3 = x.right;

		x.left = y;
		y.left = t3;

		y.height = Math.max(this.getHeight(y.left), this.getHeight(y.right)) + 1;
		x.height = Math.max(this.getHeight(x.left), this.getHeight(x.right)) + 1;

		return x;
	}

	//����ת
	private Node leftRotate(Node y) {
		Node x = y.right;
		Node t2 = x.left;

		x.left = y;
		y.right = t2;

		y.height = Math.max(this.getHeight(y.left), this.getHeight(y.right)) + 1;
		x.height = Math.max(this.getHeight(x.left), this.getHeight(x.right)) + 1;

		return x;
	}

	private Node add(Node node, K key, V value) {
		if (node == null) {
			this.size++;
			return new Node(key, value);
		}
		int result = node.key.compareTo(key);

		if (result > 0) {
			node.left = this.add(node.left, key, value);
		} else if (result < 0) {
			node.right = this.add(node.right, key, value);
		} else {
			node.value = value; // ����
		}

		node.height = 1 + Math.max(this.getHeight(node.left), this.getHeight(node.right));
		int blanceFactor = this.getBalanceFactor(node);
		if (blanceFactor > 1 && this.getBalanceFactor(node.left) >= 0) {
			// ����ת LL
			return this.rightRotate(node);
		}
		if (blanceFactor < -1 && this.getBalanceFactor(node.right) <= 0) {
			// ����ת RR
			return this.leftRotate(node);
		}
		if(blanceFactor > 1 && this.getBalanceFactor(node.left) < 0) {
			// LR
			node.left = this.leftRotate(node.left);
			return this.rightRotate(node);
		}
		if(blanceFactor < -1 && this.getBalanceFactor(node.right) > 0) {
			// RL
			node.right = this.rightRotate(node.right);
			return this.leftRotate(node.left);
		}
		return node;
	}

	public void add(K key, V value) {
		this.root = this.add(this.root, key, value);
	}

	protected Node minNode(Node node) {
		if (node.left == null) {
			return node;
		}
		return this.minNode(node.left);
	}

	public Node removeMin(Node node) {
		if (node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			this.size--;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	protected Node remove(Node node, K key) {
		if (node == null) {
			return node;
		}
		Node retNode = null;
		int result = node.key.compareTo(key);
		if (result > 0) {// left
			node.left = remove(node.left, key);
			retNode = node;
		} else if (result < 0) { // right
			node.right = remove(node.right, key);
			retNode = node;
		} else {
			if (node.left == null) {
				// ɾ���ڵ�������Ϊnull
				Node rightNode = node.right;
				node.right = null;
				this.size--;
				retNode = rightNode;
			}else if (node.right == null) {
				// ɾ���ڵ�������Ϊnull
				Node leftNode = node.left;
				node.left = null;
				this.size--;
				retNode = leftNode;
			}else {
				// ���ҽڵ㶼��Ϊ��

				// ��ȡ���ұ���С��ֵ�Ľڵ�
				Node successor = minNode(node.right);
				successor.right = this.remove(node.right, successor.key);
				successor.left = node.left;
				node.left = node.right = null;
				retNode = successor;
			}
		}
		
		if(retNode == null) {
			return retNode;
		}
		
		retNode.height = 1 + Math.max(this.getHeight(retNode.left), this.getHeight(retNode.right));
		int blanceFactor = this.getBalanceFactor(retNode);
		if (blanceFactor > 1 && this.getBalanceFactor(retNode.left) >= 0) {
			// ����ת LL
			return this.rightRotate(retNode);
		}
		if (blanceFactor < -1 && this.getBalanceFactor(retNode.right) <= 0) {
			// ����ת RR
			return this.leftRotate(retNode);
		}
		if(blanceFactor > 1 && this.getBalanceFactor(retNode.left) < 0) {
			// LR
			retNode.left = this.leftRotate(retNode.left);
			return this.rightRotate(retNode);
		}
		if(blanceFactor < -1 && this.getBalanceFactor(retNode.right) > 0) {
			// RL
			retNode.right = this.rightRotate(retNode.right);
			return this.leftRotate(retNode.left);
		}
		
		return retNode;
	}

	public V remove(K key) {
		Node node = this.getNode(this.root, key);
		if (node != null) {
			this.root = remove(this.root, key);
			return node.value;
		}
		throw new IllegalArgumentException("not found key");
	}

	private void forEach(Node node, BiConsumer<K, V> biConsumer) {
		if (node == null) {
			return;
		}
		this.forEach(node.left, biConsumer);
		biConsumer.accept(node.key, node.value);
		this.forEach(node.right, biConsumer);
	}

	public void forEach(BiConsumer<K, V> biConsumer) {
		this.forEach(this.root, biConsumer);
	}
}