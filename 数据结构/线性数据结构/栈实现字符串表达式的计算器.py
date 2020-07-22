# ʹ��ʵ�ֵļ�����

def priority(operation):
    if operation == '*' or operation == '/':
        return 1
    elif operation == '+' or operation == '-':
        return 0
    else:
        return -1


# �ж��Ƿ���һ�������
def isOperation(operation):
    return operation in ['+', '-', '*', '/']


def calculation(val1, val2, operation):
    retVal = 0
    if operation == '+':
        retVal = val1 + val2
    elif operation == '-':
        # ��ջ˳��
        retVal = val2 - val1
    elif operation == '*':
        retVal = val1 * val2
    elif operation == '/':
        # ��ջ˳��
        retVal = val2 / val1
    else:
        raise Exception("unknown operation %s" % operation)
    return retVal


class Stack(object):
    def __init__(self):
        self.queue = []

    def push(self, item):
        self.queue.insert(0, item)

    def pop(self):
        if len(self.queue) == 0:
            raise Exception("empty stack")
        return self.queue.pop(0)

    def peek(self):
        if len(self.queue) == 0:
            raise Exception("empty stack")
        return self.queue[0]

    @property
    def size(self):
        return len(self.queue)

    @property
    def empty(self):
        return self.size == 0

    def foreach(self, action):
        for i in self.queue:
            action(i)


# ���ʽ
express = '30+500*2'

# ����ջ
numberStack = Stack()
# �������ջ
operationStack = Stack()

# ��ʱ����
temp = []

for i, v in enumerate(express):
    if isOperation(v):
        if operationStack.empty:
           # ����ջΪ�գ�ֱ����ջ
           operationStack.push(v)
        else:
            # ��ǰ�����������ȼ�С�����ջ�������������ȼ�
            if priority(v) <= priority(operationStack.peek()):
               val1 = numberStack.pop()
               val2 = numberStack.pop()
               operation = operationStack.pop()
               retVal = calculation(val1, val2, operation)
               # �����ջ
               numberStack.push(retVal)
               # ��ǰ��������ջ
               operationStack.push(v)
            else:
               operationStack.push(v)
    else:
        temp.append(v)
        if i == len(express) - 1:
            # ���һ��Ԫ�أ�ֱ����ջ
            numberStack.push(int(''.join(temp)))
        else:
            # ���Է�����һ��Ԫ��
            nextVal = express[i + 1]
            if isOperation(nextVal):
                # ��һ��Ԫ���Ƿ��ţ�������ջ
                numberStack.push(int(''.join(temp)))
                # �����ʱ����
                temp.clear()



while True:
    if operationStack.empty:
        break
    val1 = numberStack.pop()
    val2 = numberStack.pop()
    operation = operationStack.pop()
    retVal = calculation(val1, val2, operation)
    # �����ջ
    numberStack.push(retVal)

# ����ջ�����һ��Ԫ�أ��������յĽ��
result = numberStack.pop()
print(result)







