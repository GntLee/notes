---------------------------
ͼ						   |
---------------------------
	# ΪʲôҪ��ͼ
		1. ���Ա������һ��ֱ��ǰ����һ��ֱ�Ӻ�̵Ĺ�ϵ
		2. ��Ҳֻ����һ��ֱ��ǰ��(Ҳ����һ�����ڵ�)
		3. ����Ҫ��ʾ��Զ�Ĺ�ϵʱ, ���õ���ͼ
	
	
	# ͼ
		* ����һ�����ݽṹ, �ڵ���Ծ���0�����߶�����ڵ�Ԫ��
		* �����ڵ�֮������ӳ�Ϊ��, �ڵ�Ҳ���Գ�Ϊ����
	
	# ͼ�ĸ���
		1 ����
			* ĳ���ڵ�
		2 ��
			* ĳ���ڵ����һ���ڵ�Ĺ���
		3 ·��
			* ��һ���ڵ㵽��һ���ڵ���Ҫ�����Ľڵ�ͱ�
		4 ����ͼ
			* �ڵ�ͽڵ�֮�������û�з���ĸ���
			* ������ A -> B Ҳ������ B -> A
		5 ����ͼ
			* �ڵ���ڵ�֮��������з���
			* ֻ���� A -> B ������ B -> A
		6 ��Ȩͼ
			* �ڵ���ڵ�֮�������(��), ����Ȩֵ
			* ����, �����ñߵĳ�������ʾȨֵ
		
	# ͼ�ı�ʾ, ������
		1. ��ά�����ʾ(�ڽӾ���)
			* ��ʾͼ���ж���֮�����ڹ�ϵ�ľ���
			* ����N�������ͼ����, �����row��col��ʾ����1...N���㡢
			
			* ͨ�������ǰ�ͼ�е����нڵ�, ������, ����ʽ�γ�һ����ά����
			* ���Ƕ�Ӧ�Ĺ�ϵ: 0��ʾ�޹�ϵ, 1��ʾ�й�ϵ(����ֱ��)
				  0 1 2 3 4 5
				0 0 1 1 1 1 0 
				1
				2
				3
				4
				5

				* ͼ�Ľڵ���: 0,1,2,3,4,5
				* �õ�һ����˵, ��ʾ0��0�ڵ�û�й�ϵ(�Լ����Լ���0), 0��1�ڵ��й�ϵ(����ֱ�����ӵ�)
				* �Դ�����
			



		2. �����ʾ(�ڽӱ�)
			* �ڽӾ�����ҪΪÿ���ڵ㶼����n���ߵĿռ�, ��ʵ�кܶ�߶��ǲ����ڵ�, ����ɿռ��һ����ʧ
			* �ڽӱ��ʵ��, ֻ���Ĵ��ڵı�, �����Ĳ����ڵı�, ���û�пռ��˷�
			* �ڽӱ������� + �������

				0 -> 1 -> 1 -> 2 -> 3 -> 4  // ���Ϊ0�Ľڵ�͹����Ľڵ�Ϊ 1 2 3 4
				1 -> 0 -> 4					// ���Ϊ1�Ľڵ��������ڵ�Ϊ 0 4
				2 -> 0 -> 4 -> 5			// ���Ϊ2�Ľڵ�������Ľڵ�Ϊ 0 4 5
				3 -> 0 -> 5
				4 -> 0 -> 1 -> 2 -> 5
				5 -> 2 -> 3 -> 4
			
			* ֻ�п���ֱ���Ľڵ�, �Ż��������
		





---------------------------
Java����ͼ				   |
---------------------------

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
	
	// �洢����
	private ArrayList<String> vertex;
	
	// ͼ���ڽӾ���
	private int[][] edges;
	
	// �ߵ���Ŀ
	private int numberOfEdge;
	
	/**
	 * �������
	 * @param numberOfVertex
	 */
	public Graph(int numberOfVertex) {
		// ��ʼ��edges��vertex��
		this.edges = new int[numberOfVertex][numberOfVertex];
		this.vertex = new ArrayList<String>(numberOfVertex);
		this.numberOfEdge = 0;
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
	}
}
