------------------------
����������			|
------------------------
	# ʵ��˼·
		* �ȴ�����һ���ڵ㣬��firstָ��ýڵ㣬�γɻ���
	
	# ������˼·
		
	# Լɪ������
		* ���ñ��Ϊ: 1, 2, 3...n����Χ����һȦ��
		* Լ�����Ϊk���˴�1��ʼ����������mλ�õ��˳���(1 <= k <= n)
		* m����һλ�ִ�1��ʼ����������m���Ǹ����ֳ���
		* �������ƣ�ֱ�����е��˶�����Ϊֹ���ɴ˲���һ�����ӱ�ŵ�����
	
	

------------------------
Python ʵ��				|
------------------------
	class Node(object):
		def __init__(self, value, next):
			self.value = value
			self.nex = next


	class Joseph(object):
		def __init__(self):
			self.first = None
			self.current = None
			self.size = 0

		def add(self, value):
			node = Node(value, None)
			if self.first is None:
				self.first = node
				self.first.next = self.first
				self.current = self.first
			else:
				self.current.next = node
				node.next = self.first
				self.current = node

			self.size += 1


		def forEach(self, handler):
			if self.first is None:
				return
			temp = self.first
			while True:
				handler(temp.value)
				if temp.next == self.first:
					break
				temp = temp.next

		def joseph(self, start, count):
			if self.first is None:
				return

			# ��lastָ�����һ���ڵ�
			last = self.first
			while True:
				if last.next == self.first:
					break
				last = last.next

			for x in range(start - 1):
				self.first = self.first.next
				last = last.next

			while True:
				if last == self.first:
					break
				for x in range(count - 1):
					self.first = self.first.next
					last = last.next

				# ����
				print(self.first.value)
				self.size -= 1

				self.first = self.first.next
				last.next = self.first

			# ���µ����һ��
			print('last = %s' % self.first.value)


	queue = Joseph()

	for i in range(1, 6):
		queue.add(i)

	queue.joseph(1, 2)

	print(queue.size)






