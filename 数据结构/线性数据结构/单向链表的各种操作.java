--------------------------
��������ķ�ת			  |
--------------------------
	# ʵ��˼·

	# ����ʵ��
		class Node (object):
			def __init__(self, value, next):
				self.next = next
				self.value = value


		def console(node):
			while node is not None:
				if node.value :
					# ��������ͷ�ڵ�
					print(node.value)
				node = node.next


		def reverse(node):
			if node is None or node.next is None:
				return

			current = node.next
			next = None
			reverseNode = Node(None, None)

			while current is not None:
				next = current.next
				current.next = reverseNode.next
				reverseNode.next = current
				current = next

			node.next = reverseNode.next


		# ����ͷ�ڵ�
		dummyNode = Node(None, None)
		node1 = Node(1, None)
		node2 = Node(2, None)
		node3 = Node(3, None)
		node4 = Node(4, None)

		# ��֯�����ϵ
		dummyNode.next = node1
		node1.next = node2
		node2.next = node3
		node3.next = node4

		# ���ԭʼ������
		console(dummyNode)

		# ��ת����
		reverse(dummyNode)

		print("------------��ת��------------")

		# ���
		console(dummyNode)




--------------------------
�����ӡ����			  |
--------------------------
	# ʹ��ջ���ݽṹ�����б���
		* �ȰѸ����ڵ���ջ
		* �ٱ���ջ�����¶�
	

	