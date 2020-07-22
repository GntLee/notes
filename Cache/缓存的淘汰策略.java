----------------------------
������̭����				|
----------------------------

	# LFU
		* Least Frequency Used, ��̭ʹ��Ƶ����͵�
		* Ϊÿ��entryά��һ��������, ÿ����һ��+1, ��̭ʱ����С��

	# LRU
		* Least Recently Used, ��̭�������ʱ������ ��entry, �����û�б�ʹ�ù���

		* ֱ�ӵ�ʵ����ҪΪÿ��entryά�����һ�����е�ʱ��
		
		* Ҳ��һ�����õ�ʵ�ַ���, ά��һ������
		* ÿ�����н�entry�Ƶ�����ͷ��,��̭ʱ�Ҷ�β����, ��Ԫ�ؼ����û��ʹ�ù���Ԫ��

		* ���㷨�ȿ��ǵ���ʱЧ��, ������ʵ��, ���õ�����evict����
	

----------------------------
LFU							|
----------------------------
	#ʹ��JDK�ṩ��ʵ��
		public class LRUCache extends LinkedHashMap {
			private static final int MAX_ENTRIES = 3;

			public LRUCache2(){
				super(MAX_ENTRIES+1, .75F,true);    // ���һ������Ϊtrue, ��ʾά������access order ���� insertion order
			}
			
			// ��ÿ��ִ�в����, �жϸ÷���, ������� true, ִ��ɾ����������Ԫ��
			protected boolean removeEldestEntry(Map.Entry eldest){
				return this.size() > MAX_ENTRIES;
			}
		}