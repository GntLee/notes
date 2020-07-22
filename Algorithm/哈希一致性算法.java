
# Hashһ�����㷨
	* �㷨Ŀ��:�����ݾ��ȵķ�ɢ�������ڵ���,���Ҿ������ڼӼ��ڵ�ʱ��ʹ��Ӱ�����������

	* �ͻ�����N�����˽ڵ���ѡ��һ����������
	* ��ͨ�� hash(key) % nodes.size �����һЩ����
		* ���ڵ�����,ɾ����,���д����Ŀͻ��˻ᱻ���·���

	* һ�� Hash �㷨�ǽ����еĹ�ϣֵ������һ����,�䷶Χ�� 0 ~ 2^32-1(˳ʱ�뷽��)
		* 0 - 4294967295
		* �����ýڵ�� IP,hostname ������Ψһ���ֶ���Ϊ Key ���� hash(key)
		* �ѽڵ�ɢ������ϣ����
	
	* ֮����Ҫ�����ݶ�λ����Ӧ�Ľڵ���,ʹ��ͬ���� hash ���� �� Key Ҳӳ�䵽�������
		* �����������ֱ���ҵ�һ�����ݴ��ڵ��ڵ�ǰ�ͻ��˵� hash ֵ,�ͽ���ǰ�ڵ���Ϊ�ÿͻ�����·�ɵĽڵ�
		* ���û�з��ֱȿͻ��˴�����ݾͷ��ص�һ���ڵ�(���㻷������)
		* ˳ʱ�뷽��
	
	* ʹ������ڵ�,ʹ�����ݷֲ����ӵľ���
		* ��ÿһ���ڵ㶼���ж�� hash,���ɶ���ڵ�����ڻ��ϳ�Ϊ����ڵ�
		* ������ IP ����ϱ�������ɹ�ϣֵ

# Java Demo

import java.util.SortedMap;
import java.util.TreeMap;


public class HashDemo {
	public static void main(String[] args) {
		System.out.println(consistentHashAlgorithmVirtual());
	}
	
	// ����ڵ�,�������Ȧ���зֲ�����������
	public static String consistentHashAlgorithmVirtual() {
		// ÿ���ڵ������ڵ�����
		int virtualCount = 5;
		// �ڵ�����
		TreeMap<Integer,String> treeMap = new TreeMap<>();
		// �ڵ�
		String[] servers = new String[] {
			"192.168.0.1:1024",
			"192.168.0.2:1024",
			"192.168.0.3:1024",
			"192.168.0.4:1024",
			"192.168.0.5:1024"
		};
		for(String server : servers) {
			// Ϊÿ���ڵ���������ڵ�
			for(int x = 0 ;x < virtualCount ; x ++) {
				String finalName = server + "&&" + x;
				// �����ÿ���ڵ��hash
				int hashCode = Math.abs(finalName.hashCode());
				treeMap.put(hashCode, finalName);
				System.out.println("hashCode=" + hashCode +   " server=" + finalName);
			}
		}

		// �ͻ��˱��ʶ�Լ�hashCode
		String key = "client";
		int hashCode = Math.abs(key.hashCode());
		
		System.out.println("hashCode=" + hashCode);
		
		// ���ڸ�hash��map
		SortedMap<Integer, String> tailMap = treeMap.tailMap(hashCode);
		if(!tailMap.isEmpty()) {
			// �У���ʹ�õ�һ��(���ڸ�hash�ĵ�һ��)
			return tailMap.get(tailMap.firstKey());
		}else {
			//û��ʹ�����нڵ�ĵ�һ��
			return treeMap.get(treeMap.firstKey());
		}
	}
	
	public static String consistentHashAlgorithm() {
		// �ڵ�����
		TreeMap<Integer,String> treeMap = new TreeMap<>();
		// �ڵ�
		String[] servers = new String[] {
			"192.168.0.1:1024",
			"192.168.0.2:1024",
			"192.168.0.3:1024",
			"192.168.0.4:1024",
			"192.168.0.5:1024"
		};
		for(String server : servers) {
			int hashCode = Math.abs(server.hashCode());
			treeMap.put(hashCode, server);
			// �����ÿ���ڵ��hash
			System.out.println("hashCode=" + hashCode +   " server=" + server);
		}

		// �ͻ��˱��ʶ�Լ�hashCode
		String key = "client";
		int hashCode = Math.abs(key.hashCode());
		
		System.out.println("hashCode=" + hashCode);
		
		
		// ���ڸ�hash��map
		SortedMap<Integer, String> tailMap = treeMap.tailMap(hashCode);
		if(!tailMap.isEmpty()) {
			// �У���ʹ�õ�һ��(���ڸ�hash�ĵ�һ��)
			return tailMap.get(tailMap.firstKey());
		}else {
			//û��ʹ�����нڵ�ĵ�һ��
			return treeMap.get(treeMap.firstKey());
		}
	}
}
