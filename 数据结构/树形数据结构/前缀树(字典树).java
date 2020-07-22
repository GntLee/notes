--------------------------------
ǰ׺��							|
--------------------------------
	# trie,Ҳ�����ֵ���
	# �����Ƕ�����,��һ�������
	# ��һ��ֻ���������ַ���

	# ÿ���ڵ��ж��ָ���¸��ڵ��ָ��
		* 26��Ӣ����ĸ(ƴ��)
		* ������Ǵ�Сд�Ļ�,��Ҫ52���ӽڵ�

	# �ڵ�����
		class Node {
			char c;
			Map<Character,Node> next;
		}

	# ������
		* �˷���̫��Ŀռ�
	
	# ��չ
		* ѹ���ֵ���



--------------------------------
Javaʵ��						|
--------------------------------
import java.util.Map;
import java.util.TreeMap;

public class Trie {

	private class Node {
		// �Ƿ��ǵ���
		private boolean isWord;
		// �ӵ�����
		private TreeMap<Character, Node> next;

		public Node(boolean isWord) {
			this.isWord = isWord;
			this.next = new TreeMap<>();
		}

		public Node() {
			this(false);
		}
	}

	private Node root;
	// �ж��ٸ��ʶ�
	private int size;

	public Trie() {
		this.root = new Node();
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean empty() {
		return this.size == 0;
	}

	public void add(String word) {
		Node current = this.root;
		// �����ַ���
		for (int i = 0; i < word.length(); i++) {
			Character character = word.charAt(i);
			if (!current.next.containsKey(character)) {
				current.next.put(character, new Node(false));
			}
			current = current.next.get(character);
		}
		if (!current.isWord) {
			current.isWord = true;
			this.size++;
		}
	}

	// trie�Ƿ����ĳ������
	public boolean contains(String word) {
		Node current = this.root;
		for (int i = 0; i < word.length(); i++) {
			Character character = word.charAt(i);
			if (!current.next.containsKey(character)) {
				return false;
			}
			current = current.next.get(character);
		}
		return current.isWord;
	}

	// �Ƿ���ָ��ǰ׺�ĵ���
	public boolean prefix(String prefix) {
		Node current = this.root;
		for (int i = 0; i < prefix.length(); i++) {
			Character character = prefix.charAt(i);
			if (!current.next.containsKey(character)) {
				return false;
			}
			current = current.next.get(character);
		}
		return true;
	}

	/**
	 * . ���Ա�ʾ�����һ���ַ�
	 * 
	 * @param regex
	 * @return
	 */
	public boolean match(String regex) {
		return this.match(this.root, regex, 0);
	}

	private boolean match(Node node, String regex, int index) {
		if (index == regex.length()) {
			return node.isWord; // �ҵ�����
		}
		Character character = regex.charAt(index);
		if (!character.equals('.')) {
			if (!node.next.containsKey(character)) {
				return false;
			} else {
				return this.match(node.next.get(character), regex, index + 1);
			}
		} else {
			for (Map.Entry<Character, Node> entry : node.next.entrySet()) {
				if (this.match(entry.getValue(), regex, index + 1)) {
					return true;
				}
			}
			return false;
		}
	}
}