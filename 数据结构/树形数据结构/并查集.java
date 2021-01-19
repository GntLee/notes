-------------------------
���鼯					 |
-------------------------
	# UnionFind
	# ���Ը�Ч�Ĵ�����������
		* �Թ�ͼ��,�������Ƿ�������
	
	# �����м��Ǹ��ڵ������״̬
		* �罻����(�û�֮���γɵ�����)
		* A �� B�Ƿ����ͨ��������ʶ...
	
	# ��ѧ�еļ�����ʵ��
		
	# ��Ҫ֧��������
		union(p,q);
		isConneted(p,q);

		
-------------------------
javaʵ��				 |
-------------------------

public class UnionFind {
	
	private int[] parent;
	
	private int[] rank;	//rank[i] ��ʾ��iΪ���ļ�����Ԫ�ظ���

	public UnionFind(int size) {
		super();
		this.parent = new int[size];
		this.rank = new int[size];
		for (int i = 0; i < this.parent.length; i++) {
			this.parent[i] = i;
			this.rank[i] = 1;
		}
	}
	
	public int size() {
		return this.parent.length;
	}
	
//	private int find(int p) {
//		if(p < 0 || p >= this.parent.length) {
//			throw new IllegalArgumentException("�Ƿ�����");
//		}
//		while(p != this.parent[p]) {
//			this.parent[p] = this.parent[this.parent[p]];
//			p = this.parent[p];
//		}
//		return p;
//	}
	
	private int find(int p) {
		if(p < 0 || p >= this.parent.length) {
			throw new IllegalArgumentException("�Ƿ�����");
		}
		if(p != this.parent[p]) {
			this.parent[p] = this.find(this.parent[p]);
		}
		return this.parent[p];
	}
	
	public boolean isConnected(int p,int q) {
		return this.find(p) == this.find(q);
	}
	
	public void unionElements(int p,int q) {
		int pRoot = this.find(p);
		int qRoot = this.find(q);
		if(pRoot == qRoot) {
			return;
		}
		if(this.rank[pRoot] < this.rank[qRoot]) {
			this.parent[pRoot] = qRoot;	
		}else if(this.rank[pRoot] > this.rank[qRoot]){
			this.parent[qRoot] = pRoot;	
		}else{
			this.parent[qRoot] = pRoot;
			this.rank[pRoot] += 1;
		}
	}
}
