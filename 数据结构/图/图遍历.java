------------------------
������ȵı���			|
------------------------
	# ��ȱ���
		* ��������, ����һ���ع�Ĺ���
	
	# �㷨����
		1. ���ʳ�ʼ�ڵ�v, ���ұ��Ϊ�ѷ���
		2. ���ҽڵ�v�ĵ�һ���ڽӽڵ�w
		3. ���w����, ִ��4, ���������, ����1, ��v����һ���ڵ����
		4. ���wδ������, ��w����������ȱ����ݹ�(��w����v, �ظ�ִ��123)
		5. ���ҽڵ�w���ڽӽڵ����һ���ڽӽڵ�, ת������3

------------------------
������ȵı���			|
------------------------
	# ��ȱ���
		* �ֲ�����, ��Ҫʹ��һ��������ά�����ʹ��Ľڵ���˳��
		* �Ա㰴���˳����������д�ڵ���ڽӽڵ�
	
	# �㷨����
		1.  ���ʳ�ʼ�ڵ�v, ���ұ��Ϊ�ѷ���
		2.  �ڵ�v�����
		3.  ���зǿ�, ����ִ��, �����㷨����
		4.  ������, ȡ��ͷ�ڵ�u
		5.  ���ҽڵ�u�ĵ�һ���ڽӽڵ�w
		6.  ����ڵ�u���ڽӽڵ�w������, ���ظ�����3, ����ִ����3������
		6.1 ����ڵ�wδ������, ����w�ڵ��Ѿ�������
		6.2 �ڵ�w�����
		6.3 ��ѯ�ڵ�u�ļ�w�ڽӽڵ�����һ���ڽӽڵ�w, �ظ�����6

------------------------
����ʵ��				|
------------------------
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
	
	// �洢����
	private ArrayList<String> vertex;
	
	// ͼ���ڽӾ���
	private int[][] edges;
	
	// �ߵ���Ŀ
	private int numberOfEdge;
	
	private boolean[] visited;
	
	/**
	 * �������
	 * @param numberOfVertex
	 */
	public Graph(int numberOfVertex) {
		// ��ʼ��edges��vertex��
		this.edges = new int[numberOfVertex][numberOfVertex];
		this.vertex = new ArrayList<String>(numberOfVertex);
		this.numberOfEdge = 0;
		this.visited = new boolean[numberOfVertex];
	}
	
	/**
	 * ����ڵ�
	 * @param vertex
	 */
	public void insertVertex(String vertex) {
		this.vertex.add(vertex);
	}
	
	/**
	 * ��ӱ�
	 * @param v1	������±�
	 * @param v2	������±�
	 * @param wight
	 */
	public void insertEdge(int v1, int v2, int wight) {
		this.edges[v1][v2] = wight;
		this.edges[v2][v1] = wight; // ����ͼ
		this.numberOfEdge ++;
	}
	
	/**
	 * ���ض������Ŀ
	 * @return
	 */
	public int numberOfVertex() {
		return this.vertex.size();
	}

	/**
	 * ���رߵ���Ŀ
	 * @return
	 */
	public int numberOfEdge() {
		return this.numberOfEdge;
	}
	
	/**
	 * ����ָ���ڵ��Ӧ������
	 * @param index
	 * @return
	 */
	public String getValueByIndex(int index) {
		return this.vertex.get(index);
	}
	
	/**
	 * ��ȡ�����ڵ��Ȩֵ
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int getWight(int v1, int v2) {
		return this.edges[v1][v2];
	}
	
	/**
	 * ��ӡͼ�ṹ
	 */
	public void showGraph() {
		for (int[] link : this.edges) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	/**
	 * �õ���һ���ڽӽڵ���±�
	 * @param index
	 * @return
	 */
	public int getFirstNeighbor(int index) {
		for (int x = 0;x < this.vertex.size(); x ++) {
			if (this.edges[index][x] > 0) {
				return x;
			}
		}
		return -1;
	}
	
	/**
	 * ����ǰһ���ڽӽڵ���±�����ȡ��һ���ڽӽڵ�
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int getNextNeighbor(int v1, int v2) {
		for (int x = v2 + 1; x < this.vertex.size() ; x ++) {
			if (this.edges[v1][x] > 0) {
				return x;
			}
		}
		return -1;
	}
	
	public void dfs(boolean[] visited, int i) {
		System.out.print(this.getValueByIndex(i) + "->");
		visited[i] = true;
		
		int w = this.getFirstNeighbor(i);
		
		while (w != -1) {
			if (!this.visited[w]) {
				this.dfs(visited, w);
			}
			w = this.getNextNeighbor(i, w);
		}
	}
	
	/**
	 * ������ȱ���
	 * @param visited
	 * @param i
	 */
	public void dfs() {
		for (int i = 0; i < this.numberOfVertex(); i ++) {
			if (!visited[i]) {
				dfs(visited, i);
			}
		}
	}
	
	private void bfs(boolean[] visited, int i) {
		
		int u; // ���е�ͷ�ڵ��Ӧ���±�
		
		int w; // �ڽӽڵ���±�
		
		LinkedList<Integer> queue = new LinkedList<>();
		
		System.out.print(this.getValueByIndex(i) + "->");
		// ���Ϊ����
		visited[i] = true;
		// ��ӵ�����
		queue.addLast(i);
		
		while (!queue.isEmpty()) {
			// ȡ������ͷ
			u = queue.removeFirst();
			w = this.getFirstNeighbor(u);
			while (w != -1) {
				// �Ƿ���ʹ�
				if (!visited[w]) {
					System.out.print(this.getValueByIndex(w) + "->");
					visited[w] = true;
					// �����
					queue.addLast(w);
				}
				// �������
				w = getNextNeighbor(u, w);
			}
		}
	}

	/**
	 * ������ȱ���
	 */
	public void bfs() {
		for (int i = 0; i < this.numberOfVertex(); i ++) {
			if (!visited[i]) {
				bfs(visited, i);
			}
		}
	}
	
	public static void main(String[] args) {
		
		// Ԥ����ڵ�
		String[] vertexVal = {"A", "B", "C", "D", "E"};
		
		Graph graph = new Graph(vertexVal.length);
		
		// ��ʼ���ڵ�
		for (String vertex : vertexVal) {
			graph.insertVertex(vertex);
		}
		
		
		// ������ϵ
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		
		// ��ʾͼ�Ĺ�ϵ
		graph.showGraph();
		
		// ������ȱ���
		// graph.dfs();
		
		// ������ȱ���
		graph.bfs();
	}
}
