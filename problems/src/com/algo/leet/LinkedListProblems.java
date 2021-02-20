package com.algo.leet;

class LinkedListProblems {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode result = null;
		ListNode node = null;
		while (l1 != null || l2 != null) {
			int data = carry;
			if (l1 != null) {
				data = data + l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				data = data + l2.val;
				l2 = l2.next;
			}
			if (result == null) {
				result = new ListNode(data % 10);
				node = result;
			} else {
				node.next = new ListNode(data % 10);
				node = node.next;
			}
			carry = data / 10;
		}
		if (carry > 0) {
			node.next = new ListNode(carry);
		}
		return result;
	}

	public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
		ListNode result = null;
		ListNode current = null;
		if(l1 == null) {
			return l2;
		}
		if(l2 == null) {
			return l1;
		}
		while (l1 != null || l2 != null) {
			if (l1.val < l2.val) {
				if (result == null) {
					result = new ListNode(l1.val);
					current = result;
				} else {
					current.next = new ListNode(l1.val);
					current = current.next;
				}
				l1 = l1.next;
			} else {
				if (result == null) {
					result = new ListNode(l2.val);
					current = result;
				} else {
					current.next = new ListNode(l2.val);
					current = current.next;
				}
				l2 = l2.next;
			}
		}
		while (l1 != null) {
			current.next = new ListNode(l1.val);
			current = current.next;
			l1 = l1.next;
		}
		while (l2 != null) {
			current.next = new ListNode(l2.val);
			current = current.next;
			l2 = l2.next;
		}
		return result;
	}
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
        return null;
    }
}