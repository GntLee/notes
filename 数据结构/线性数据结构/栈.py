
# �Ƚ����,����ȳ�
	* Lst In First Out(LIFO)


# Python

	# ����ʵ��
	class Stack():

		def __init__(self):
			self.__arr = []
		
		def push(self, item):
			self.__arr.append(item);

		def pop(self):
			return self.__arr.pop()
		
		def peek(self):
			return self.__arr[0] if len(self.__arr) > 0 else None
		
		@property
		def size(self):
			return len(self.__arr)
		
		@property
		def empty(self):
			return self.size == 0