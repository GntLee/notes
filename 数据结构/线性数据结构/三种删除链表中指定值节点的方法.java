class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
	}
}

/**
 * 
 * ɾ���ڵ��е�ָ��ֵ������Ԫ��
 * 
 * @author KevinBlandy
 *
 */
public class Solution01 {

	// ��ͨʵ��
	public ListNode removeElement(ListNode head, int val) {
		while (head != null && head.val == val) {
			// ����ͷ�ڵ�
			ListNode delNode = head;
			head = head.next;
			delNode.next = null;
		}
		if (head == null) {
			return head;
		}
		ListNode prev = head;
		while (prev.next != null) {
			// �����м�Ԫ��
			if (prev.next.val == val) {
				prev.next = prev.next.next;
			} else {
				prev = prev.next;
			}
		}

		return head;			//���ص����Ƴ��ڵ���ͷ�ڵ�
	}

	// ʹ������ͷ�ڵ�,�����ʽ���ӵļ��
	public ListNode removeElement1(ListNode head, int val) {
		// ��������ͷ�ڵ�
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;

		ListNode prev = dummyHead;
		while (prev.next != null) {
			// �����м�Ԫ��
			if (prev.next.val == val) {
				prev.next = prev.next.next;
			} else {
				prev = prev.next;
			}
		}

		return dummyHead.next;		//���ص����Ƴ��ڵ���ͷ�ڵ�
	}

	// ʹ�õݹ�ʵ��
	public ListNode removeElement2(ListNode head, int val) {
		if (head == null) {
			return null;
		}
		head.next = removeElement2(head.next, val);
		return head.val == val ? head.next : head;
	}
}
