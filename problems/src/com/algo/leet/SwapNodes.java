package com.algo.leet;

public class SwapNodes {

	/**
	 * Given a linked list, swap every two adjacent nodes and return its head. You
	 * may not modify the values in the list's nodes, only nodes itself may be
	 * changed.
	 */

	public ListNode swapPairsRecursive(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode firstNode = head;
		ListNode secondNode = head.next;

		firstNode.next = swapPairsRecursive(secondNode.next);
		secondNode.next = firstNode;
		return secondNode;
	}
	
	public ListNode swapPairsIterative(ListNode head) {
		ListNode dummyNode = new ListNode(-1);
		dummyNode.next = head;
		ListNode previousNode = dummyNode;
		
		while(head != null && head.next != null) {
			ListNode firstNode = head;
			ListNode secondNode = head.next;
			
			//swapping the nodes
			previousNode.next = secondNode;
			firstNode.next = secondNode.next;
			secondNode.next = firstNode;
		
			previousNode = firstNode;
			head = firstNode.next;
			
		}
		
		return dummyNode.next;
	}
	
	public void printList(ListNode head) {
		ListNode node = head;
		while(node.next != null) {
			System.out.print(node.val + "->");
			node = node.next;
		}
		System.out.print(node.val);
	}
	
	public static void main(String[] agrs) {
		ListNode head = new ListNode(1);
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(3);
		ListNode node3 = new ListNode(4);
		ListNode node4 = new ListNode(5);
		ListNode node5 = new ListNode(6);
		head.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		SwapNodes swapNodes = new SwapNodes();
		swapNodes.printList(head);
		System.out.println();
		swapNodes.printList(swapNodes.swapPairsIterative(head));
	}
}
