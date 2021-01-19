

# �������ʹ���㷨(Least Recently Used �������ʹ��)

# һ�����ڻ��泡��
	1. �����ݲ��뵽����ͷ����
	2. ÿ����������(���������ݱ�����),�������Ƶ�����ͷ��
	3. ����������ʱ��,������β�������ݶ���


# Java ʵ��
	* LinkedHashMap ������ṩ�����ݷ��������Լ��Ƴ����ٷ���key�Ĺ���

	class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {  
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 4318100161701136149L;
		
		public static final float DEFAULT_LOAD_FACTOR = 0.75f;
		
		// �������,��������������Ƴ��������ʹ�õ�Ԫ��
		private int maxCapacity;
		
		public LRULinkedHashMap(int maxCapacity) {
			// ����maxCapacity�ͼ������Ӽ���hashmap��capactiy
			// +1ȷ�����ﵽcacheSize����ʱ���ᴥ��hashmap������
			super((int) Math.ceil(maxCapacity / DEFAULT_LOAD_FACTOR) + 1, DEFAULT_LOAD_FACTOR, true);
			this.maxCapacity = maxCapacity;
		}

		// �������������,�����Ƴ�����ʹ�õ�key�Ĳ���

		// �÷�������ÿ��ִ����Ӳ�����ص�,�������true,��ɾ����������һ��Ԫ��
		@Override 
		protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {  
			return super.size() > this.maxCapacity;  
		}  
	}  

# python ʵ��

import collections
class LRUDict (object):
    def __init__(self,size):
        self.size = size
        self.dict = collections.OrderedDict()
    
    def set(self,key,value):
        if key in self.dict:
            # key����,ֱ���滻
            self.dict[key] = value
        else:
            if len(self.dict) > self.size:
                # �Ƴ����һ��Ԫ��,Ҳ���ǲ����õ�
                self.dict.popitem()
            # key ������,ִ������
            self.dict[key] = value
        # ����/�޸ĵ�Ԫ�ض��ƶ�������
        self.dict.move_to_end(key,False)
    
    def get(self,key):
        if key in self.dict:
            # ���л���,�Ƶ�����
            self.dict.move_to_end(key,False)
            return self.dict.get(key)
        else:
            return None
        
    def __str__(self, *args, **kwargs):
        return self.dict.__str__()


