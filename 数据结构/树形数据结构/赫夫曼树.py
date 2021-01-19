# �շ�����

	* Ҳ������Ϊ�� ��������
	
	* ����n��Ȩֵ��Ϊn��Ҷ�ӽڵ㣬����һ��������
	* ��������� '��Ȩ·������(wpl)' �ﵽ��С����ô�����Ķ�����Ϊ���Ŷ����������ǻ�������

	* ����������'��Ȩ·������'��̵�����Ȩֵ�ϴ�Ľڵ�����ȽϽ�
	

	* ͨ�׵������ǣ�һ����������ÿ���ӽڵ㣬����һ��ֵ
		�Ӹ��ڵ㵽���ӽڵ��·������ * �ýڵ��ֵ = �ڵ��'��Ȩ·������'
	
	* һ�����Ĵ�Ȩ·������(wpl) = ����Ҷ�ӽڵ��'��Ȩ·������'֮��

	* wpl��С�ģ����Ǻշ�����


# �շ������Ĺ�������
	1. ��С���������Ȩֵ, ÿһ�����ݶ���һ���ڵ㣬ÿ���ڵ���Ե���һ����򵥵Ķ�����
	2. ȡ�����ڵ�Ȩֵ��С�����Ŷ�����
	3. ���һ���µĶ����������µĶ������ĸ��ڵ�Ȩֵ��ǰ�����Ŷ��������ڵ�Ȩֵ�ĺ�
	4. �ٰ�����ĵĶ��������Ը��ڵ��Ȩֵ��С���ٴ����򣬲����ظ�:1,2,3,4 �Ĳ��裬ֱ�����������е����ݶ�������
	5. ���͵õ���һ�Ż�������



# pyʵ��



class Node(object):
    def __init__(self, value, left, right):
        self.value = value    # �ڵ�Ȩֵ
        self.left = left    # ���ӽڵ�
        self.right = right  # ���ӽڵ�

    def __str__(self):
        return "Node[value=%s]" % self.value

    # �ݹ�ǰ�������
    def pre_foreach(self):
        print(self)
        if self.left is not None:
            self.left.pre_foreach()
        if self.right is not None:
            self.right.pre_foreach()


# ������
def foreach_tree(node):
    queue = [node]
    while len(queue) > 0:
        item = queue.pop(0)
        print(item)
        if item.left is not None:
            queue.append(item.left)
        if item.right is not None:
            queue.append(item.right)


# ������������
def create_huffman_tree(array):
    # �����ݷ�װΪnode����
    nodes = []
    for i in array:
        nodes.append(Node(i, None, None))

    while len(nodes) > 1:
        # ����node
        nodes = sorted(nodes, key=lambda x:  x.value)

        # ȡ��Ȩֵ��С�������ڵ㣬������һ���µĶ�����
        left = nodes.pop(0)
        right = nodes.pop(0)
        root = Node(left.value + right.value, left, right)

        # ����Ԫ����ӵ�����
        nodes.append(root)

    return nodes[0]


tree = create_huffman_tree([13, 7, 8, 3, 29, 6, 1])

# foreach_tree(tree)
tree.pre_foreach()