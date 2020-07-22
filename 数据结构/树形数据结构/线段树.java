--------------------
�߶���				|
--------------------
	# Ҳ����������(Segment Tree)

	# �߶���ÿһ���ڵ�,���Ǳ�ʾ��һ������

	# �߶�����һ����һ�����Ķ�����,Ҳ��һ����һ����ȫ������,�߶�����ƽ�������



--------------------
ʵ��				|
--------------------

public class SegmentTree<E> {

	public static interface Merger<E> {
		E merge(E a, E b);
	}

	private E[] data;

	private E[] tree;

	private Merger<E> merger;

	@SuppressWarnings("unchecked")
	public SegmentTree(E[] arr, Merger<E> merger) {
		this.data = arr;
		this.tree = (E[]) new Object[4 * arr.length];
		this.merger = merger;
		this.bildeSegmentTree(0, 0, this.data.length - 1);
	}

	public E get(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IllegalArgumentException("�Ƿ�����");
		}
		return this.data[index];
	}

	/**
	 * ��treeIndex��λ�ô�����ʾ������߶��� [l....r]
	 * 
	 * @param treeIndex
	 *            ����ȫ�����������ʾ���Ǹ����±�
	 * @param l
	 *            ��߿�ʼ������
	 * @param r
	 *            �ұ߿�ʼ������
	 */
	private void bildeSegmentTree(int treeIndex, int l, int r) {
		if (l == r) {
			this.tree[treeIndex] = this.data[l];
			return;
		}

		int lefTreeIndex = this.leftChild(treeIndex);
		int rightTreeIndex = this.rightChild(treeIndex);

		int mid = l + (r - l) / 2;

		this.bildeSegmentTree(lefTreeIndex, l, mid);
		this.bildeSegmentTree(rightTreeIndex, mid + 1, r);

		this.tree[treeIndex] = this.merger.merge(this.tree[lefTreeIndex], this.tree[rightTreeIndex]);
	}

	private int size() {
		return this.data.length;
	}

	// ������ȫ�������������ʾ��,һ����������ʾ��Ԫ�ص����ӽڵ������
	private int leftChild(int index) {
		return 2 * index + 1;
	}

	// ������ȫ�������������ʾ��,һ����������ʾ��Ԫ�ص��Һ��ӽڵ������
	private int rightChild(int index) {
		return 2 * index + 2;
	}

	/**
	 * ��������[queryL,queryR]��ֵ
	 * 
	 * @param queryL
	 * @param queryR
	 * @return
	 */
	public E query(int queryL, int queryR) {
		if (queryL < 0 || queryL >= this.data.length || queryR < 0 || queryR >= this.data.length || queryL > queryR) {
			throw new IllegalArgumentException("�Ƿ�����");
		}
		return this.query(0, 0, this.data.length - 1, queryL, queryR);
	}

	/**
	 * [l..r]��Χ����������[queryL..queryR]��ֵ
	 * 
	 * @param treeIndex
	 * @param l
	 * @param r
	 * @param queryL
	 * @param queryR
	 * @return
	 */
	private E query(int treeIndex, int l, int r, int queryL, int queryR) {
		if (l == queryL && r == queryR) {
			return this.tree[treeIndex];
		}
		int mid = l + (r - l) / 2;

		int leftTreeIndex = this.leftChild(treeIndex);
		int rightTreeIndex = this.rightChild(treeIndex);

		if (queryL >= mid + 1) {
			return this.query(rightTreeIndex, mid + 1, r, queryL, queryR);
		} else if (queryR <= mid) {
			return this.query(leftTreeIndex, l, mid, queryL, queryR);
		}

		E leftResult = this.query(leftTreeIndex, l, mid, queryL, mid);
		E rightResult = this.query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
		return this.merger.merge(leftResult, rightResult);
	}

	// ��treeIndexΪ�����߶����и���index��ֵΪe
	private void set(int treeIndex, int l, int r, int index, E e) {
		if (l == r) {
			this.tree[treeIndex] = e;
			return;
		}

		int mid = l + (r - l) / 2;
		int leftTreeIndex = this.leftChild(treeIndex);
		int rightTreeIndex = this.rightChild(treeIndex);

		if (index >= mid + 1) {
			this.set(rightTreeIndex, mid + 1, r, index, e);
		} else {
			this.set(leftTreeIndex, l, mid, index, e);
		}
		
		this.tree[treeIndex] = this.merger.merge(this.tree[leftTreeIndex], this.tree[rightTreeIndex]);
	}

	/**
	 * ����
	 * 
	 * @param index
	 * @param E
	 */
	public void set(int index, E e) {
		if (index < 0 || index >= this.data.length) {
			throw new IllegalArgumentException("�Ƿ�����");
		}
		this.data[index] = e;
		this.set(0, 0, this.data.length - 1, index, e);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");
		for (int x = 0; x < this.tree.length; x++) {
			stringBuilder.append(this.tree[x]);
			if (x != (this.tree.length - 1)) {
				stringBuilder.append(",");
			}
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
