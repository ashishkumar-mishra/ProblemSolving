package com.algo.linkedlist;

public class LinkedList {

	Node head;

	class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}

	public void addToHead(int data) {
		if (head == null) {
			head = new Node(data);
		} else {
			Node node = new Node(data);
			node.next = head;
			head = node;
		}
	}

	public void insertNext(int data) {
		if (head == null) {
			head = new Node(data);
		} else {
			Node node = new Node(data);
			Node n1 = head;
			while (n1.next != null) {
				n1 = n1.next;
			}
			n1.next = node;
		}
	}

	public LinkedList addTwoNumbers(LinkedList l1, LinkedList l2) {
		Node node1 = l1.head;
		Node node2 = l2.head;
		LinkedList resultList = new LinkedList();
		int carry = 0;
		while (node1 != null || node2 != null) {
			int data = carry;
			if (node1 != null) {
				data = data + node1.data;
				node1 = node1.next;
			}
			if (node2 != null) {
				data = data + node2.data;
				node2 = node2.next;
			}
			carry = data / 10;
			resultList.insertNext(data % 10);
		}
		if (carry != 0) {
			resultList.insertNext(carry);
		}
		return resultList;
	}

	public void printNode(Node head) {
		while (head != null) {
			System.out.print(head.data + " ->");
			head = head.next;
		}
		System.out.println();
	}

	public void insertInSortedLinkedList(int data) {
		if (head == null) {
			Node node = new Node(data);
			head = node;
		} else if (head.data >= data) {
			addToHead(data);
		} else {
			Node node1 = head;
			while(node1 != null && node1.data < data) {
				node1 = node1.next;
			}
			Node node = new Node(data);
			node.next = node1.next;
			node1.next = node;
		}

	}

	public static void main(String args[]) {
		LinkedList linkedList1 = new LinkedList();
		linkedList1.addToHead(5);
		linkedList1.addToHead(4);
		linkedList1.addToHead(2);
		linkedList1.printNode(linkedList1.head);

		LinkedList linkedList2 = new LinkedList();
		linkedList2.addToHead(4);
		linkedList2.addToHead(6);
		linkedList2.addToHead(5);
		linkedList2.printNode(linkedList2.head);

		LinkedList linkedList3 = new LinkedList();
		linkedList3 = linkedList3.addTwoNumbers(linkedList1, linkedList2);
		linkedList3.printNode(linkedList3.head);
	}
}
